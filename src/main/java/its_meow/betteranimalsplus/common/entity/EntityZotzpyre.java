package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityHorse;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        //this.getEyeHeight()
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
        if ( this.dimension == -1 || !this.world.getBiome(this.getPosition()).canRain() ) // TODO f resis
        {
        	this.setType(4);
        }
        else
        {
        	this.setType(this.rand.nextInt(4));
        }
        
        this.dismountZotz();
        this.lastAttack = 0;
        this.latchTimer = 0;
        this.setAttackTarget(null);
        
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
        this.tasks.addTask(2, new EntityZotzpyre.AIMeleeAttack());
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 0, true, true, (EntityLiving entity) -> !(entity instanceof EntityZotzpyre) && !(entity instanceof EntityAmbientCreature) && !(entity instanceof EntityHorse) && !(entity instanceof EntityReindeer) && !(entity instanceof IMob) && entity.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD));
    }

    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateClimber(this, worldIn);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, (byte) 0);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {}

    @Override
    protected void collideWithNearbyEntities() {}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
    }

    @Override
    public void onUpdate()
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

    	super.onUpdate();
                
        if ( !this.world.isRemote )
        {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }
    
//    public float getAIMoveSpeed()
//    {
//        return (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
//    }

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
        
        if (f10 > 1.0F)
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
    	return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }
    
    public boolean isFleeing()
    {
    	return ( this.latchTimer > 80 ); // && !this.latchAttack );
    }

    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	if ( this.world.isRemote ) return;
    	
//    	if ( this.getAttackTarget() != null )
//    	{
//    		if ( this.isFleeing() )
//    		{
//    			// face away
//    			this.getNavigator().clearPath();
//    			this.faceAwayEntity(this.getAttackTarget());
//    		}
//    		else if ( !this.isRiding() )
//    		{
//                AIHelper.faceEntitySmart(this, this.getAttackTarget());
//	    		//this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
//	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
//    		}
//    		else
//    		{
////	    		this.rotationPitch = 0;
////	    		this.prevRotationPitch = 0;
//	    		
//	    		this.rotationYaw = 0;
//	    		this.prevRotationYaw = 0;
//	    		
//	    		this.rotationYawHead = 0;
//	    		this.prevRotationYawHead = 0;
//    		}
//    	}
//    	else
//    	{
//    		AIHelper.faceMovingDirection(this);
//    	}
    	
    	// super.onLivingUpdate();
    	
    	if ( this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive() )
    	{
    		if ( this.isFleeing() )
    		{
    			// face away
    			this.getNavigator().clearPath();
    			this.faceAwayEntity(this.getAttackTarget());
    			
    			double d0 = this.posX - this.getAttackTarget().posX;
                double d1 = this.posZ - this.getAttackTarget().posZ;
                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
                
	    		if ( this.motionY < 0.5D )
	    		{
	    			this.motionY += (0.5D-this.motionY*(1.0D+this.motionY))/6.0F;
	    		}
	    		
	    		if ( f < 5 )
	    		{
	    			this.motionX += d0 / f * 0.08D;
	                this.motionZ += d1 / f * 0.08D;
	    		}
	    		else if ( f < 15 )
	    		{
		    		this.motionX += d0 / f * 0.06D;
	                this.motionZ += d1 / f * 0.06D;
	    		}
	    		else if ( f < 30 )
	    		{
		    		this.motionX += d0 / f * 0.03D;
	                this.motionZ += d1 / f * 0.03D;
	    		}
	    		
	    		if ( this.collidedHorizontally || this.collidedVertically )
    			{
    				this.latchTimer = 0;
    			}
    		}
    		else if ( !this.isRiding() )
    		{
    			// face to
    			double d0 = this.getAttackTarget().posX - this.posX;
                double d1 = this.getAttackTarget().posZ - this.posZ;
                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
                
                if ( this.onGround ) // WALK TO
                {
		    		this.motionX += d0 / f * 0.04D;
	                this.motionZ += d1 / f * 0.04D;
                }
                else
                {                	
                	if ( this.getAttackTarget().posY - this.posY <= -0.5D )
                    {
                    	this.motionY -= 0.008D;
                    }
                    else
                    {
                    	this.motionY += 0.008D;
                    }
                	
                	this.motionX += d0 / f * 0.016D + this.motionX * 0.2D;
	                this.motionZ += d1 / f * 0.016D + this.motionZ * 0.2D;
	                
                }

                AIHelper.faceEntitySmart(this, this.getAttackTarget());
	    		//this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    		else
    		{
//    			this.rotationPitch = 0;
//	    		this.prevRotationPitch = 0;
	    		
	    		this.rotationYaw = 0;
	    		this.prevRotationYaw = 0;
	    		
	    		this.rotationYawHead = 0;
	    		this.prevRotationYawHead = 0;
    		}
    	}
    	else
    	{
    		AIHelper.faceMovingDirection(this);
    	}    	
    	
    	
    	// boolean shouldDrop = false;
    	
        if ( !this.world.isRemote && this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive() && this.isEntityAlive() ) 
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
        			this.getAttackTarget().addVelocity(-this.getHorizontalFacing().getFrontOffsetX()/14.0D, 0.25D+rand.nextDouble()*0.25D, -this.getHorizontalFacing().getFrontOffsetZ()/14.0D);
                	this.getAttackTarget().velocityChanged = true;
        		}
            	else
            	{
            		this.getAttackTarget().addVelocity(0, 0.039D, 0);
                	this.getAttackTarget().velocityChanged = true;
            	}
            	
                
                
//                if ( !this.inWater )
//                {
//                    time *= 2F * (Math.random() + 1F);
//                }
//                else
//                {
//                    time += Math.random() * Math.random() * 2 * ((Math.random() < 0.5) ? -1 : 1);
//                }
                
                if ( this.lastAttack + this.lastAttackTime < this.ticksExisted )
                {
                    this.attackEntityAsMob(this.getAttackTarget());
                    this.heal((float)(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue()/2.0F));
			        this.playSound(SoundEvents.ENTITY_WITCH_DRINK, 2.0F, 1.0F);
                    this.spawnSweepParticles();
                    this.lastAttackTime = 16 + rand.nextInt(9);
                }

            }
//            else if ( this.latchTimer <= 0 && this.getDistanceSq(this.getAttackTarget()) <= 1.2 )
//            {
//                if ( this.rand.nextBoolean() )
//                {
//                	if ( this.grabTarget(this.getAttackTarget()) )
//                	{
//                        this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
//                        this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 10, 0, true, false));
//                	}
//                }
//                else
//                {
//                	this.latchTimer = 20 + rand.nextInt(5)*5;
//                }
//            }
        }
        
        if ( !this.onGround && this.motionY < 0.0D )
        {
            this.motionY *= 0.8D;
        }
        
        if ( this.latchTimer > 0 )
        {
        	this.latchTimer--;
        }
        
//        if ( this.isRiding() && this.ticksExisted - this.latchSnapshot > 100 )
//        {
//            this.dismountZotz();
//        }
        
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
    
    @SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
    {
		if ( id == 23 )
		{
			if ( this.oldCameraMode != -1 ) Minecraft.getMinecraft().gameSettings.thirdPersonView = this.oldCameraMode;
			this.oldCameraMode = -1;
            this.dismountZotz();
		}
		else if ( id == 25 )
		{
			this.oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
		}
//		else if ( id == 26 )
//		{
//	        this.latchAttack = true;
//		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}
    
    public int oldCameraMode = -1;
    public int latchTimer = 0;

    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }
    
    public boolean grabTarget(EntityLivingBase entity)
    {
    	if ( entity.isRiding() || entity.isBeingRidden() || this.isRiding() || this.isBeingRidden() )
    	{
    		return false;
    	}
    	
    	if ( !entity.isPotionApplicable(new PotionEffect(MobEffects.SLOWNESS)) ) // || !entity.isPotionActive(MobEffects.SLOWNESS) )
    	{
    		return false;
    	}
    	
        this.world.setEntityState(this, (byte)25);
        this.playSound(SoundEvents.ENTITY_CAT_HISS, 0.6F, 1.2F);
        
        this.startRiding(entity, true);
        if ( !world.isRemote && entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
        }
        
        return true;
    }
    
    public void resetLatchTimer() // LATCH TIMER OF 80 MEANS FLY AWAY
    {
    	this.latchTimer = 90 + this.rand.nextInt(7)*10;
    }

    public void dismountZotz()
    {
    	this.resetLatchTimer();
    	
    	Entity mount = this.getRidingEntity();
    	
    	if ( mount != null )
    	{
            this.dismountRidingEntity();
            this.dismountEntity(mount);

            if ( !this.world.isRemote ) // SERVER
            {
                if ( mount instanceof EntityPlayerMP )
                {
                    ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
                }
            	this.world.setEntityState(this, (byte)23);
            }
            
        	mount.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	}
    	
    	this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	
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
        if(getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer)
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
        if ( this.isRiding() && source == DamageSource.IN_WALL )
        {
            return false;
        }
        else
        {
            if ( !this.world.isRemote && this.isRiding() && amount >= 1 && this.rand.nextInt(3) == 0 )
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
        
        if ( !this.world.isRemote && !this.isEntityAlive() && entityIn == this.getRidingEntity() )
        {
            this.dismountZotz();
        }
        
    	if ( !this.isEntityAlive() )
    	{
    		return false;
    	}
        
//        if ( !this.isRiding() )
//        {
//        	//if ( this.latchAttack )
//        	{
//	        	if ( this.grabTarget(this.getAttackTarget()) )
//	        	{
//	                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
//	                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 10, 0, true, false));
//	        	}
//        	}
//        }
        
		//this.latchAttack = false;

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
    
    
    private void faceAwayEntity(Entity entityIn)
    {
        double d0 = this.posX - entityIn.posX;
        double d2 = this.posZ - entityIn.posZ;
        double d1;

        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            d1 = entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() - (this.posY + (double)this.getEyeHeight());
        }
        else
        {
            d1 = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0D - (this.posY + (double)this.getEyeHeight());
        }

        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        this.rotationPitch = f1;
        this.rotationYaw = f;
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
    		//this.latchAttack = false;
    	}
        super.setAttackTarget(entitylivingbaseIn);
    }
    
    public class AIMeleeAttack extends PublicEntityAIAttack // TODO
    {
        public AIMeleeAttack()
        {
            super(EntityZotzpyre.this, 1.35D);
        }
        
        protected double d0 = 0.0D;
        protected double d1 = 0.0D;
        protected double f = 0.0D;
        
        @Override
        public void resetTask()
        {
            f = 0.0D;
            super.resetTask();
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	double attackRange = this.getAttackReachSqr(entity);
        	
        	if ( this.attacker.isRiding() || EntityZotzpyre.this.isFleeing() ) return;
        	
        	if ( dist <= attackRange && this.attackTick < 7 )
	        {
	        	if ( this.attackTick <= 0 )
	        	{
			        this.attacker.attackEntityAsMob(entity);
			        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.6F, 0.7F);
		    		this.attackTick = 32 + rand.nextInt(13);
		            f = 0.0D;
		    		return;
	        	}
		    }
	    	
        	if ( this.attackTick <= 0 )
	        {
	    		this.attackTick = 22 + rand.nextInt(13);
	            f = 0.0D;
	        }
	    	else if ( dist <= 100.0D )
	    	{
	    		this.attacker.faceEntity(entity, 20.0F, 20.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
	    		
	    		if ( this.attackTick > 10 && this.attackTick < 15 )
		    	{
		    		if ( this.attackTick == 13 )
	                {
		    			if ( f == 0.0D )
	                	{
		    				d0 = (entity.posX - this.attacker.posX)*1.4D;
			                d1 = (entity.posZ - this.attacker.posZ)*1.4D;
			                f = 1.0D+MathHelper.sqrt(d0 * d0 + d1 * d1);
	                	}
	                }
		    		
		    		if ( this.attacker.onGround )
			        {
			        	this.attacker.motionX = 0.0D;
			        	this.attacker.motionZ = 0.0D;
				        this.attacker.velocityChanged = true;
			        }
		    	}
		    	else if ( this.attackTick == 7 )
	        	{
	                if ( this.attacker.canEntityBeSeen(entity) )
	                {
	                	if ( f == 0.0D )
	                	{
	                		d0 = (entity.posX - this.attacker.posX)*1.4D;
			                d1 = (entity.posZ - this.attacker.posZ)*1.4D;
			                f = 1.0D+MathHelper.sqrt(d0 * d0 + d1 * d1);
	                	}
		                
		                if ( EntityZotzpyre.this.latchTimer <= 0 && dist <= 20.0D )
	                	{
//		                	this.attacker.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 0.5F, 0.4F+rand.nextFloat()/5.0F);
//					        this.attacker.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 1.5F, 1.3F+rand.nextFloat()/5.0F);
//					        
//					        double strength = 0.48D+rand.nextDouble()/12.0D;
//					        this.attacker.motionX += d0 / f * strength + this.attacker.motionX * 0.12D;
//		                    this.attacker.motionZ += d1 / f * strength + this.attacker.motionZ * 0.12D;
//		                    f = 0.0D;
		                    EntityZotzpyre.this.latchTimer = 100+rand.nextInt(4)*10;
	                	}
	                	else
	                	{
					        this.attacker.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.9F+rand.nextFloat()/5.0F, 1.2F+rand.nextFloat()/5.0F);
	
					        double strength = 0.36D+rand.nextDouble()/12.0D;
					        this.attacker.motionX += d0 / f * strength + this.attacker.motionX * 0.12D;
		                    this.attacker.motionZ += d1 / f * strength + this.attacker.motionZ * 0.12D;
		                    f = 0.0D;
	                	}
		                
		                if ( this.attacker.posY - entity.posY > 2.0D )
		                {
		                	this.attacker.motionY += 0.2D;
		                }
		                else if ( this.attacker.posY - entity.posY >= 0.5D )
		                {
		                	this.attacker.motionY -= 0.2D;
		                }
		                else
		                {
		                	this.attacker.motionY += 0.4D;
		                }
		                
				        this.attacker.velocityChanged = true;
	                }
		        }
		    	else if ( this.attackTick == 5 )
			    {
		    		if ( dist <= attackRange )
                	{
                		if ( EntityZotzpyre.this.grabTarget(entity) )
                    	{
                			entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
                			entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 12, 0, true, false));
                    	}
                	}
		    		else
		    		{
				        double strength = 1.4D;
				        
				        this.attacker.motionX *= strength;
	                    this.attacker.motionZ *= strength;
	                    
	                    if ( this.attacker.posY - entity.posY > 2.0D )
		                {
		                	this.attacker.motionY += 0.08D;
		                }
		                else if ( this.attacker.posY - entity.posY >= 0.5D )
		                {
		                	this.attacker.motionY -= 0.08D;
		                }
		                else
		                {
		                	this.attacker.motionY += 0.16D;
		                }
	                    
				        this.attacker.velocityChanged = true;
			        }
		        }
		    	else if ( this.attackTick == 3 )
		    	{
			        double strength = 1.4D;
			        
			        this.attacker.motionX *= strength;
                    this.attacker.motionZ *= strength;
                    
                    if ( this.attacker.posY - entity.posY > 2.0D )
	                {
	                	this.attacker.motionY += 0.08D;
	                }
	                else if ( this.attacker.posY - entity.posY >= 0.5D )
	                {
	                	this.attacker.motionY -= 0.08D;
	                }
	                else
	                {
	                	this.attacker.motionY += 0.16D;
	                }	                
			        this.attacker.velocityChanged = true;
		        }
	    	}
        }
        
        
        // THIS WORKED BEFORE THE CHANGE, FALLBACK
//        @Override
//        public void updateTask()
//        {
//        	if ( this.attacker.isRiding() || EntityZotzpyre.this.isFleeing() )
//        	{
//        		return;
//        	}
//        	
//            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
//            if ( entitylivingbase == null ) return;
//
//            double d0 = 1+this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
//            --this.delayCounter;
//
//            if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
//            {
//                this.targetX = entitylivingbase.posX;
//                this.targetY = entitylivingbase.getEntityBoundingBox().minY;
//                this.targetZ = entitylivingbase.posZ;
//                this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
//
//                if (this.canPenalize)
//                {
//                    this.delayCounter += failedPathFindingPenalty;
//                    if (this.attacker.getNavigator().getPath() != null)
//                    {
//                        net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
//                        if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                            failedPathFindingPenalty = 0;
//                        else
//                            failedPathFindingPenalty += 10;
//                    }
//                    else
//                    {
//                        failedPathFindingPenalty += 10;
//                    }
//                }
//
//                if (d0 > 1024.0D)
//                {
//                    this.delayCounter += 10;
//                }
//                else if (d0 > 256.0D)
//                {
//                    this.delayCounter += 5;
//                }
//
//                if ( d0 >= 20 )
//                {
//	                if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
//	                {
//	                    this.delayCounter += 15;
//	                }
//                }
//                else
//                {                	
////                	if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget*(1.0D+(d0-16.0D)/16.0D) ) )
////	                {
////	                    this.delayCounter += 15;
////	                }
//                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, 0.0D);
//
//                	Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
//					double push = (2.0D+d0)*2.0D;
//					this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
//                	this.attacker.velocityChanged = true;
//                }
//            }
//
//            this.attackTick = Math.max(this.attackTick - 1, 0);
//            this.checkAndPerformAttack(entitylivingbase, d0);
//        }
        
        
        
        
        
        
        

//	    else if ( this.attackTick >= 25 ) // else // if ( dist <= 256.0D ) //( EntityZotzpyre.this.latchTimer <= 0 && dist <= 144.0D ) || dist <= 49.0D )
//    	{
//	    	aaa
//    		double d0 = entity.posX - this.attacker.posX;
//            double d1 = entity.posZ - this.attacker.posZ;
//            double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
//            
//    		this.attacker.motionY += 0.1D;
//    		
//    		this.attacker.faceEntity(entity, 20.0F, 20.0F);
//    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
//    		this.attacker.motionX += d0 / f * 0.1D;
//            this.attacker.motionZ += d1 / f * 0.1D;
//    	}
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 5.5D + rand.nextDouble()/10.0D + attackTarget.width;
        }
    }
    
    
    

}
