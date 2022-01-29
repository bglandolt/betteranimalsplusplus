package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.BiomeDictionary;

public class EntityZotzpyre extends EntityMobWithTypes {

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityZotzpyre.class, DataSerializers.BYTE);
    protected int lastAttack = 0;
    //public boolean latchAttack = false;
    // private boolean isFromZotz = false;

    public EntityZotzpyre(World world)
    {
        super(world);
        this.setSize(0.9F, 0.9F);
        this.stepHeight = 2.05F;
    	if ( this.getTypeNumber() == this.getVariantMax() )
    	{
    		this.isImmuneToFire = true;
    	}
        this.dismountZotz();
    }
    
    public float getEyeHeight()
    {
        return this.height * 0.5F;
    }
    
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        if ( this.dimension == -1 || !this.world.getBiome(this.getPosition()).canRain() )
        {
        	this.setType(this.getVariantMax());
        	this.isImmuneToFire = true;
        }
        else
        {
        	this.setType(this.rand.nextInt(this.getVariantMax()));
        }
        
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected void initEntityAI()
    {
        // this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityZotzpyre.AIMeleeAttack());
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        
        // this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 0, true, true, (EntityLiving entity) -> !(entity instanceof EntityZotzpyre) && !(entity instanceof EntityAmbientCreature) && !(entity instanceof EntityHorse) && !(entity instanceof EntityReindeer) && !(entity instanceof IMob) && entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD));
    }

    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, (byte) 0);
    }

    @Override
    public boolean canBePushed()
    {
        return true;
    }

//    @Override
//    protected void collideWithEntity(Entity entityIn)
//    {
//    	
//    }

    @Override
    protected void collideWithNearbyEntities()
    {
    	
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(BetterAnimalsPlusConfig.zotzpyreSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(BetterAnimalsPlusConfig.zotzpyreHealth);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(BetterAnimalsPlusConfig.zotzpyreAttackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
    }

    /* prevent slowdown in air */
    public void travel(float strafe, float vertical, float forward)
    {
        double d0 = this.posY;
        float f1 = this.getWaterSlowDown();
        float f2 = 0.02F;
        float f3 = (float) EnchantmentHelper.getDepthStriderModifier(this);
        
        if(f3 > 3.0F)
        {
            f3 = 3.0F;
        }
        
        // normally vanilla puts slowdown here
        
        if (f3 > 0.0F)
        {
            f1 += (0.54600006F - f1) * f3 / 3.0F;
            f2 += (this.getAIMoveSpeed() - f2) * f3 / 3.0F;
        }
        
        this.moveRelative(strafe, vertical, forward, f2);
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= (double) f1;
        this.motionY *= 0.800000011920929D;
        this.motionZ *= (double) f1;
        
        if (!this.hasNoGravity())
        {
            this.motionY -= 0.06D;
        }
        
        if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
        {
            this.motionY = 0.30000001192092896D;
        }
        
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;
        
        if ( f10 > 1.0F )
        {
            f10 = 1.0F;
        }
        
        this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock()
    {
        return (this.dataManager.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = ((Byte) this.dataManager.get(CLIMBING)).byteValue();
        this.dataManager.set(CLIMBING, climbing ? (byte) (b0 | 1) : (byte) (b0 & -2));
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.5F;
    }

    @Override
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.05F;
    }

    @Override
    public SoundEvent getAmbientSound()
    {
    	// if ( rand.nextBoolean() ) this.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 0.9F, 1.0F+this.world.rand.nextFloat()/5.0F);
    	if ( this.getAttackTarget() == null )
    	{
    		return SoundEvents.ENTITY_BAT_AMBIENT;
    	}
    	else return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_BAT_DEATH;
    }
    
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//    	this.playSound(SoundEvents.ENTITY_BAT_HURT, 0.6F, 0.9F+this.world.rand.nextFloat()/5.0F);
//        return null; // SoundEvents.ENTITY_BAT_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//    	this.playSound(SoundEvents.ENTITY_BAT_DEATH, 0.6F, 0.9F+this.world.rand.nextFloat()/5.0F);
//        return null; // SoundEvents.ENTITY_BAT_DEATH;
//    }

    @Override
    public void onLivingUpdate()
    {
    	if ( (this.isRiding() && !this.getRidingEntity().isEntityAlive()) )
    	{
    		this.getRidingEntity().setDead();
    		this.dismountZotz();
    	}
    	
    	if ( !this.isEntityAlive() )
    	{
    		this.dismountZotz();
    	}
    	
    	super.onLivingUpdate();
    	    	
    	if ( this.world.isRemote ) return;
    	
        this.setBesideClimbableBlock(this.collidedHorizontally);
    	
        if ( this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive() && this.isEntityAlive() ) 
        {
            if ( this.isRiding() && this.getRidingEntity() == this.getAttackTarget() )
            {
            	if ( this.ticksExisted % 5 == 0 )
        		{
                    this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
            		if ( this.rand.nextInt(5) != 0 ) this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.2F+this.rand.nextFloat()/12.0F, 0.8F+this.rand.nextFloat()*0.4F);
        		}
            	
        		if ( this.ticksExisted % 12 == 0 )
        		{
        			this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.6F+this.world.rand.nextFloat()/10.0F, 1.2F+this.world.rand.nextFloat()/5.0F);
        			this.getAttackTarget().addVelocity(-this.getHorizontalFacing().getFrontOffsetX()/15.0D, 0.25D+rand.nextDouble()*0.25D, -this.getHorizontalFacing().getFrontOffsetZ()/15.0D);
                	this.getAttackTarget().velocityChanged = true;
        		}
            	else
            	{
            		this.getAttackTarget().addVelocity(0, 0.0385D, 0);
                	this.getAttackTarget().velocityChanged = true;
            	}
                if ( this.lastAttack + this.lastAttackTime < this.ticksExisted )
                {
                    this.attackEntityAsMob(this.getAttackTarget());
                    this.heal((float)(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue()/2.0F));
			        this.playSound(SoundEvents.ENTITY_WITCH_DRINK, 2.0F, 1.0F);
                    this.spawnSweepParticles();
                    this.lastAttackTime = 12 + rand.nextInt(7);
                }

            }
        }
        
        if ( !this.onGround && this.motionY < 0.0D )
        {
            this.motionY *= 0.8D;
        }
        
        if ( this.latchTimer > 0 )
        {
        	this.latchTimer--;
        }
        
    }
    
    public int lastAttackTime = 16 + rand.nextInt(9);
    
    public void spawnSweepParticles()
	{
		double xx = this.posX + (double) (-MathHelper.sin(this.rotationYaw * 0.017453292F));
		double yy = this.posY + 0.5D + (double) this.height * 0.5D;
		double zz = this.posZ + (double) MathHelper.cos(this.rotationYaw * 0.017453292F);

		if (this.world instanceof WorldServer)
		{
			for ( int i = 16; i > 0; i-- )
			{
				((WorldServer) this.world).spawnParticle(EnumParticleTypes.REDSTONE, xx+this.rand.nextGaussian()/6.0D, yy+this.rand.nextGaussian()/6.0D, zz+this.rand.nextGaussian()/6.0D, 0, 0, 0, 0, 0.02D, new int[0]);
			}
		}
	}
    
//    @SideOnly(Side.CLIENT)
//	  public void handleStatusUpdate(byte id) // XXX
//    {
//		if ( id == 23 )
//		{
//			this.dismountZotz();
//		}
//		{
//			super.handleStatusUpdate(id);
//		}
//	}

    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }
    
    
    public boolean isFleeing()
    {
    	return this.latchTimer >= 80;
    }
    
    public void resetLatchTimer()
    {
    	this.latchTimer = 110 + this.rand.nextInt(6)*10;
    }

    public int latchTimer = 0;
    
    public void dismountZotz()
    {
    	this.resetLatchTimer();
    	
    	Entity mount = this.getRidingEntity();
    	
    	if ( mount != null )
    	{
            this.dismountRidingEntity();
            this.dismountEntity(mount);

            if ( mount instanceof EntityPlayerMP )
            {
                ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
            }
            
        	mount.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	}
    	
    	this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	this.velocityChanged = true;
    	
        if ( this.isEntityAlive() )
        {
        	this.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 0.6F+rand.nextFloat()/5.0F, 0.8F+rand.nextFloat()/5.0F);
        }
    	
    }
    
    

    @Override
    public void dismountRidingEntity()
    {
    	super.dismountRidingEntity();
//        if( this.world.isChunkGeneratedAt(this.chunkCoordX, this.chunkCoordZ) )
//        {
//            if (this.getRidingEntity() != null && !this.getRidingEntity().shouldDismountInWater(this))
//            {
//                super.dismountRidingEntity();
//            }
//            else if (this.getAttackTarget() == null || isFromZotz)
//            {
//                super.dismountRidingEntity();
//            }
//        }
    }

    @Override
    public boolean canRiderInteract() {
        return true;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public double getYOffset()
    {
        if ( getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer )
        {
            return getRidingEntity().height - 2.2F; // 2.25
        }
        else if (getRidingEntity() != null)
        {
            return (getRidingEntity().getEyeHeight() / 2) - this.height;
        } 
        else
       {
            return super.getYOffset();
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        if(blockpos.getY() >= this.world.getSeaLevel() && !BiomeDictionary.getTypes(world.getBiome(blockpos)).contains(BiomeDictionary.Type.JUNGLE)) { // allow spawning on surface in jungles
            return false;
        } else {
            return super.getCanSpawnHere();
        }
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (this.world.isRemote)
        {
            return false;
        }
    	    	
        if ( this.isRiding() && source == DamageSource.IN_WALL )
        {
            return false;
        }
        else
        {
            if ( this.isRiding() && amount >= 1 && this.rand.nextInt(3) == 0 )
            {
                this.dismountZotz();
            }
            
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        
        if (flag)
        {
            this.lastAttack = this.ticksExisted;
        }
                
        if ( !this.isEntityAlive() && entityIn == this.getRidingEntity() )
        {
            this.dismountZotz();
        }
        
    	if ( !this.isEntityAlive() )
    	{
    		return false;
    	}

        return flag;
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    	
    }

    @Override
    public int getVariantMax() {
        return 5;
    }

    @Override
    protected String getContainerName()
    {
        return "zotzpyre";
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return false;
    }
    
    @Override
    public boolean hasHome()
    {
    	return false;
    }
    
    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
    	if ( this.getAttackTarget() == null && entitylivingbaseIn != null )
    	{
    		this.latchTimer = this.rand.nextInt(9)*10;
    	}
        super.setAttackTarget(entitylivingbaseIn);
    }
    
    public boolean grabTarget(EntityLivingBase entity) // XXX mount, grab, latch !!!
    {
    	if ( entity.isRiding() || entity.isBeingRidden() || this.isRiding() || this.isBeingRidden() || !this.isEntityAlive() || !entity.isEntityAlive() )
    	{
    		return false;
    	}
    	
    	if ( entity.height > 2.0D )
    	{
    		return false;
    	}
    	
    	if ( !entity.isPotionApplicable(new PotionEffect(MobEffects.SLOWNESS)) ) // || !entity.isPotionActive(MobEffects.SLOWNESS) )
    	{
    		return false;
    	}
    	
        this.playSound(SoundEvents.ENTITY_CAT_HISS, 0.6F, 1.2F);
        this.startRiding(entity, true);
        
    	if ( entity instanceof EntityPlayerMP )
    	{
    		((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
    	}
        
        return true;
    }
    
    EntityPlayer latchedPlayer = null;
    
    public class AIMeleeAttack extends PublicEntityAIAttack // TODO
    {
        public AIMeleeAttack()
        {
            super(EntityZotzpyre.this, 1.2D);
        }
        
        protected double d0 = 0.0D;
        protected double d1 = 0.0D;
        protected double f = 0.0D;
        protected boolean bigLeap = true;
        
        @Override
        public void resetTask()
        {
        	this.resetAttack(0);
			super.resetTask();
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist) // XXX
        {
        	double attackRange = this.getAttackReachSqr(entity);

        	if ( this.attackTick < 8 ) // Attack is ready!
	        {
        		if ( this.leapLocationLockedIn() && dist <= attackRange ) // Within range?
    	        {
			        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.6F, 0.7F);

			        // Latch if the player is not moving fast enough!
			        if ( this.attacker.attackEntityAsMob(entity) && dist <= attackRange && EntityZotzpyre.this.latchTimer <= 0 && ( (entity.motionX*entity.motionX+entity.motionZ*entity.motionZ) < 0.5D || this.bigLeap) && EntityZotzpyre.this.grabTarget(entity) )
                    {
            			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
            			entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 12, 0, true, false));
    	    			return;
                	}
			        
			        if ( this.bigLeap )
			        {
			        	// PUSH TO
	            		Vec3d velocityVector = new Vec3d(this.attacker.getAttackTarget().posX-this.attacker.posX, 0,this.attacker.getAttackTarget().posZ-this.attacker.posX);
	            		double push = 16.0D+dist;
	            		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
	                	this.attacker.velocityChanged = true;
			        }
			        
			        this.resetLeapLocation();
        			this.resetAttack(0);
    		    }
        		else if ( this.attackTick <= 0 ) // Attack timer has completed!
        		{
        			this.resetAttack(dist);
        			return;
        		}
        		
        		// this.attacker.isAirBorne = true;
	    		this.attacker.onGround = false;
	        }
	    	
        	// if ( dist <= 256.0D )
	    	{
	    		if ( this.attackTick == 16 )
                {
	    			if ( !this.leapLocationLockedIn() ) // Set jump location!
                	{
	    				this.d0 = (entity.posX - this.attacker.posX)*1.414D;
		                this.d1 = (entity.posZ - this.attacker.posZ)*1.414D;
		                this.f = MathHelper.sqrt(this.d0 * this.d0 + this.d1 * this.d1);
		                
				        if ( this.bigLeap )
				        {
				        	this.attacker.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 0.75F+rand.nextFloat()/10.0F, 0.8F+rand.nextFloat()/5.0F);
				        }
				        
				        if ( !this.leapLocationLockedIn() )
	                    {
		    				this.resetAttack(dist);
		        			return;
	                    }
                	}
                }
		    	else if ( this.attackTick == 8 )
	        	{
	                if ( this.attacker.canEntityBeSeen(entity) )
	                {
	                	if ( !this.leapLocationLockedIn() )
	                    {
		    				this.resetAttack(dist);
		        			return;
	                    }
	                	
				        // Jump attack!
	                	
				        double xzstrength = rand.nextDouble()/8.0D;
				        double ystrength = rand.nextDouble()/16.0D;

				        if ( this.bigLeap )
				        {
					        xzstrength += 1.25D;
					        ystrength += 1.25D;
				        }
				        else
				        {
					        xzstrength += 0.75D;
				        	ystrength += 0.75D;
				        }
				        
				        if ( !this.attacker.onGround )
				        {
					        xzstrength -= 0.25D;
				        }
				        
				        double blocksAboveTarget = this.attacker.posY - entity.posY;
				        
				        if ( blocksAboveTarget > entity.height * 2.0D )
		                {
					        if ( dist < 9.0 )
				        	{
						        this.attacker.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 0.9F+rand.nextFloat()/5.0F, 1.2F+rand.nextFloat()/5.0F);
					        	EntityZotzpyre.this.latchTimer = 120 + this.attacker.world.rand.nextInt(10);
				        		this.attacker.motionY += 0.2D;
					        	return;
				        	}
				        	else
				        	{
				        		this.attacker.motionY += 0.2D;
				        	}
		                }
				        if ( blocksAboveTarget > entity.height )
		                {
				        	if ( dist > 25.0D ) // Get closer, but stay in the air!
				        	{
				        		this.attacker.motionY += 0.2D;
				        	}
				        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
				        	{
				        		this.attacker.motionY += 0.2D;
				        	}
				        	else
				        	{
				        		this.attacker.motionY -= 0.2D;
				        	}
		                }
		                else if ( blocksAboveTarget > entity.height/2.0D )
		                {
		                	if ( dist > 25.0D ) // Get closer, but stay in the air!
				        	{
				        		this.attacker.motionY += 0.2D;
				        	}
				        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
				        	{
				        		this.attacker.motionY += 0.2D;
				        	}
				        	else
				        	{
				        		this.attacker.motionY -= 0.1D;
				        	}
		                }
		                else
		                {
		                	this.attacker.motionY += 0.44 * ystrength;
		                }
				        
				        this.attacker.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.9F+rand.nextFloat()/5.0F, 1.2F+rand.nextFloat()/5.0F);
				        
				        this.attacker.motionX += this.d0 / this.f * xzstrength;
	                    this.attacker.motionZ += this.d1 / this.f * xzstrength;
		                
				        this.attacker.velocityChanged = true;
	                }
	                else // No target? reset.
	                {
	                	this.resetAttack(dist);
	        			return;
	                }
		        }
		    	else if ( this.attackTick == 5 ) // Additional motion!
			    {
		    		double blocksAboveTarget = this.attacker.posY - entity.posY;
				        
			        if ( blocksAboveTarget > entity.height*2.0D )
	                {
				        if ( dist < 9.0 )
			        	{
			        		this.attacker.motionY += 0.1D;
				        	return;
			        	}
			        	else
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
	                }
			        if ( blocksAboveTarget > entity.height )
	                {
			        	if ( dist > 25.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else
			        	{
			        		this.attacker.motionY -= 0.1D;
			        	}
	                }
	                else if ( blocksAboveTarget > entity.height/2.0D )
	                {
	                	if ( dist > 25.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else
			        	{
			        		this.attacker.motionY -= 0.05D;
			        	}
	                }
	                else
	                {
	                	this.attacker.motionY += 0.1D;
	                }
		    		
			        if ( this.bigLeap )
			        {
			        	if ( this.leapLocationLockedIn() )
			        	{
			        		this.attacker.motionX += this.d0 / this.f * 0.5F;
		                    this.attacker.motionZ += this.d1 / this.f * 0.5F;
			        	}
			        	else
			        	{
				    		this.attacker.motionX *= 1.5D;
		                    this.attacker.motionZ *= 1.5D;
			        	}
			        }
			        else
			        {
			        	if ( this.leapLocationLockedIn() )
			        	{
			        		this.attacker.motionX += this.d0 / this.f * 0.25F;
		                    this.attacker.motionZ += this.d1 / this.f * 0.25F;
			        	}
			        	else
			        	{
				    		this.attacker.motionX *= 1.25D;
		                    this.attacker.motionZ *= 1.25D;
			        	}
			        }
                    
			        this.attacker.velocityChanged = true;
		        }
		    	else if ( this.attackTick == 3 ) // Additional motion!
		    	{
		    		double blocksAboveTarget = this.attacker.posY - entity.posY;
			        
			        if ( blocksAboveTarget > entity.height*2.0D )
	                {
				        if ( dist < 9.0 )
			        	{
			        		this.attacker.motionY += 0.1D;
				        	return;
			        	}
			        	else
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
	                }
			        if ( blocksAboveTarget > entity.height )
	                {
			        	if ( dist > 25.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else
			        	{
			        		this.attacker.motionY -= 0.1D;
			        	}
	                }
	                else if ( blocksAboveTarget > entity.height/2.0D )
	                {
	                	if ( dist > 25.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else if ( dist > 12.0D ) // Get closer, but stay in the air!
			        	{
			        		this.attacker.motionY += 0.1D;
			        	}
			        	else
			        	{
			        		this.attacker.motionY -= 0.05D;
			        	}
	                }
	                else
	                {
	                	this.attacker.motionY += 0.1D;
	                }
		    		
			        if ( this.bigLeap )
			        {
			        	if ( this.leapLocationLockedIn() )
			        	{
			        		this.attacker.motionX += this.d0 / this.f * 0.5F;
		                    this.attacker.motionZ += this.d1 / this.f * 0.5F;
			        	}
			        	else
			        	{
				    		this.attacker.motionX *= 1.5D;
		                    this.attacker.motionZ *= 1.5D;
			        	}
			        }
			        else
			        {
			        	if ( this.leapLocationLockedIn() )
			        	{
			        		this.attacker.motionX += this.d0 / this.f * 0.25F;
		                    this.attacker.motionZ += this.d1 / this.f * 0.25F;
			        	}
			        	else
			        	{
				    		this.attacker.motionX *= 1.25D;
		                    this.attacker.motionZ *= 1.25D;
			        	}
			        }
                    
			        this.attacker.velocityChanged = true;
		        }
	    	}
//	    	else
//	    	{
//    			this.resetAttack(dist);
//    			return;
//	    	}
        }
        
        private void resetAttack(double dist)
        {
        	this.resetLeapLocation();
        	
        	if ( dist <= 20 && dist >= 9 && !EntityZotzpyre.this.isFleeing() && this.attacker.getAttackTarget() != null && this.attacker.posY - this.attacker.getAttackTarget().posY < this.attacker.getAttackTarget().height )
        	{
    			this.bigLeap = true;
        		this.attackTick = 40 + rand.nextInt(12);
        	}
        	else
        	{
    			this.bigLeap = false;
            	this.attackTick = 20 + rand.nextInt(12);
            	
            	if ( EntityZotzpyre.this.latchTimer > 0 )
            	{
                	this.attackTick -= 8;
            	}
        	}
        }
        
        private boolean leapLocationLockedIn()
        {
    		return this.f >= 1.0D;
        }
        
        private void resetLeapLocation()
        {
        	this.d0 = 0.0;
        	this.d1 = 0.0;
        	this.f = 0.0D;
        }
                
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 3.0D + attackTarget.width * 2.0D;
        }
        
        @Override
        public void updateTask()
        {
            if ( !this.attacker.isEntityAlive() )
            {
            	return;
            }

            if ( this.attacker.getAttackTarget() == null )
            {
            	AIHelper.faceMovingDirection(this.attacker);
            	return;
            }
            
            if ( this.attacker.isRiding() )
            {
            	this.attacker.rotationYaw = this.attacker.getAttackTarget().rotationYaw;
            	this.attacker.rotationPitch = this.attacker.getAttackTarget().rotationPitch;
            	this.attacker.rotationYawHead = this.attacker.getAttackTarget().rotationYawHead;
            	
            	this.attacker.prevRotationYaw = this.attacker.getAttackTarget().prevRotationYaw;
            	this.attacker.prevRotationPitch = this.attacker.getAttackTarget().prevRotationPitch;
            	this.attacker.prevRotationYawHead = this.attacker.getAttackTarget().prevRotationYawHead;
            }
            else if ( EntityZotzpyre.this.isFleeing() )
    		{
    			this.attacker.getNavigator().clearPath();
       			AIHelper.faceAwayEntity(this.attacker, this.attacker.getAttackTarget());
    			
    			double dx = this.attacker.posX - this.attacker.getAttackTarget().posX;
                double dz = this.attacker.posZ - this.attacker.getAttackTarget().posZ;
                double xz = MathHelper.sqrt(dx * dx + dz * dz);
                
                if ( xz < 1.0D )
                {
                	xz = 1.0D;
                }
                else
                {
    	    		if ( this.attacker.collidedHorizontally || this.attacker.collidedVertically )
        			{
    	    			EntityZotzpyre.this.latchTimer = 0;
        			}
    	    		
                	if ( this.attacker.posY - this.attacker.getAttackTarget().posY < 1.0D + this.attacker.getAttackTarget().height*3.0D )
	                {
	                	this.attacker.motionY += (0.5D-this.attacker.motionY*(1.0D+this.attacker.motionY))/5.0F;
	                }
                }
                
	    		if ( xz < 3 )
	    		{
	    			this.attacker.motionX += dx / xz * 0.12D;
	                this.attacker.motionZ += dz / xz * 0.12D;
	    		}
	    		else if ( xz < 6 )
	    		{
		    		this.attacker.motionX += dx / xz * 0.09D;
	                this.attacker.motionZ += dz / xz * 0.09D;
	    		}
	    		else if ( xz < 12 )
	    		{
		    		this.attacker.motionX += dx / xz * 0.05D;
	                this.attacker.motionZ += dz / xz * 0.05D;
	    		}
	    		else
	    		{
		    		this.attacker.motionX += dx / xz * 0.02D;
	                this.attacker.motionZ += dz / xz * 0.02D;
	    		}
	    		
	    		this.attacker.velocityChanged = true;
	    		
	    		// this.attacker.isAirBorne = true;
	    		this.attacker.onGround = false;
	    		
	    		// this.resetAttack(distanceSqr);
	    		
	    		return;
    		}
    		else
    		{
            	if ( this.leapLocationLockedIn() )
            	{
            		AIHelper.faceLocationSmart(this.attacker, this.attacker.posX+this.d0, this.attacker.posZ+this.d1);
            		this.attacker.getLookHelper().setLookPosition(this.attacker.posX+this.d0, this.attacker.posY, this.attacker.posZ+this.d1, 30.0F, 30.0F);
            	}
            	else // if ( this.attacker.onGround || !this.attacker.isAirBorne )
            	{
        			AIHelper.faceEntitySmart(this.attacker, this.attacker.getAttackTarget());
            		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 30.0F, 30.0F);
            	}
            	
//            	if ( this.attacker.world.rand.nextInt(64) == 0 )
//            	{
//            		this.strafe = 50;
//            	}
            	
                double distanceSqr = this.attacker.getDistanceSq(this.attacker.getAttackTarget().posX, this.attacker.getAttackTarget().getEntityBoundingBox().minY, this.attacker.getAttackTarget().posZ);

                this.attackTick = Math.max(--this.attackTick, 0);
                this.checkAndPerformAttack(this.attacker.getAttackTarget(), distanceSqr);


                if ( this.attacker.onGround )
                {
                	EntityZotzpyre.this.latchTimer = 0;
                }
                else if ( EntityZotzpyre.this.latchTimer > 0 )
                {
                	double dx = this.attacker.getAttackTarget().posX - this.attacker.posX;
                    double dz = this.attacker.getAttackTarget().posZ - this.attacker.posZ;
                    double xz = MathHelper.sqrt(dx * dx + dz * dz);
                    
                	if ( xz < 4 )
    	    		{
    	    			this.attacker.motionX += dx / xz * 0.02D;
    	                this.attacker.motionZ += dz / xz * 0.02D;
    	    		}
    	    		else if ( xz < 8 )
    	    		{
    		    		this.attacker.motionX += dx / xz * 0.08D;
    	                this.attacker.motionZ += dz / xz * 0.08D;
    	    		}
    	    		else if ( xz < 12 )
    	    		{
    		    		this.attacker.motionX += dx / xz * 0.04D;
    	                this.attacker.motionZ += dz / xz * 0.04D;
    	                
    	                this.attacker.motionY += 0.02D;
    	    		}
    	    		else
    	    		{
    		    		this.attacker.motionX += dx / xz * 0.02D;
    	                this.attacker.motionZ += dz / xz * 0.02D;
    	                
    	                this.attacker.motionY += 0.04D;
    	    		}
                }
                
                if ( this.bigLeap )
        		{
                	if ( distanceSqr < 9 ) // 3
                 	{
                     	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), 0.0D ) )
                        {
                     		
                        }
                     	
                     	// PUSH AWAY
                 		if ( this.attackTick > 10 && ( this.attacker.onGround ) )
                 		{
     	            		Vec3d velocityVector = new Vec3d(this.attacker.posX-this.attacker.getAttackTarget().posX, 0,this.attacker.posZ-this.attacker.getAttackTarget().posZ);
     	            		double push = 16.0D+distanceSqr*3.0D;
     	            		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
     	                	this.attacker.velocityChanged = true;
                 		}
                 	}
                 	else if ( distanceSqr < 16 ) // 4
                 	{
                     	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), this.speedTowardsTarget*(distanceSqr/16.0D) ) )
                         {
                     		
                         }
                 	}
             		else
             		{
                     	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), this.speedTowardsTarget) )
                         {
                     		
                         }
             		}
        		}
                else if ( distanceSqr < 16 ) // 4
            	{
                	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), 0.0D ) )
                    {
                		
                    }
                	
                	// PUSH AWAY
            		if ( this.attackTick > 10 && ( this.attacker.onGround ) )
            		{
	            		Vec3d velocityVector = new Vec3d(this.attacker.posX-this.attacker.getAttackTarget().posX, 0,this.attacker.posZ-this.attacker.getAttackTarget().posZ);
	            		double push = 16.0D+distanceSqr*3.0D;
	            		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
	                	this.attacker.velocityChanged = true;
            		}
            	}
            	else if ( distanceSqr < 25 ) // 5
            	{
                	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), this.speedTowardsTarget*(distanceSqr/25.0D) ) )
                    {
                		
                    }
            	}
        		else
        		{
                	if ( this.attacker.getNavigator().tryMoveToEntityLiving(this.attacker.getAttackTarget(), this.speedTowardsTarget) )
                    {
                		
                    }
        		}
            }
        }
    }    

}
