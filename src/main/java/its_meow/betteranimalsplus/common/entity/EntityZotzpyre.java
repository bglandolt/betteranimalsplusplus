package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityZotzpyre extends EntityMobWithTypes {

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityZotzpyre.class, DataSerializers.BYTE);
    protected int lastAttack = 0;
    // private boolean isFromZotz = false;

    public EntityZotzpyre(World world)
    {
        super(world);
        this.setSize(0.9F, 0.9F);
        this.stepHeight = 2.05F;
        this.dismountZotz();
    }
    
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        if ( !this.world.getBiome(this.getPosition()).canRain() && !this.world.getBiome(this.getPosition()).getEnableSnow() )
        {
        	this.setType(5);
        }
        else if ( this.getTypeNumber() == 5 )
        {
        	this.setType(this.rand.nextInt(5));
        }
        
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
        super.onUpdate();
        if ( (this.isDead && this.isRiding()) || (this.isRiding() && this.getRidingEntity() != null && this.getRidingEntity().isDead) )
        {
            this.dismountZotz();
        }
        if(!this.world.isRemote)
        {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }
    
//    public float getAIMoveSpeed()
//    {
//        return (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
//    }

    /* prevent slowdown in air */
    public void travel(float strafe, float vertical, float forward) {
        double d0 = this.posY;
        float f1 = this.getWaterSlowDown();
        float f2 = 0.02F;
        float f3 = (float) EnchantmentHelper.getDepthStriderModifier(this);
        if(f3 > 3.0F) {
            f3 = 3.0F;
        }
        // normally vanilla puts slowdown here
        if(f3 > 0.0F) {
            f1 += (0.54600006F - f1) * f3 / 3.0F;
            f2 += (this.getAIMoveSpeed() - f2) * f3 / 3.0F;
        }
        this.moveRelative(strafe, vertical, forward, f2);
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= (double) f1;
        this.motionY *= 0.800000011920929D;
        this.motionZ *= (double) f1;
        if(!this.hasNoGravity()) {
            this.motionY -= 0.06D; // TODO
        }
        if(this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ)) {
            this.motionY = 0.30000001192092896D;
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;
        if(f10 > 1.0F) {
            f10 = 1.0F;
        }
        this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
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

    @Override
    public void onLivingUpdate()
    {
    	if ( this.getAttackTarget() != null )
    	{
    		if ( this.latchTimer > 80 )
    		{
    			// face away
    			this.getNavigator().clearPath();
    			this.faceAwayEntity(this.getAttackTarget());
    		}
    		else if ( !this.isRiding() )
    		{
	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    		else
    		{
//	    		this.rotationPitch = 0;
//	    		this.prevRotationPitch = 0;
	    		
	    		this.rotationYaw = 0;
	    		this.prevRotationYaw = 0;
	    		
	    		this.rotationYawHead = 0;
	    		this.prevRotationYawHead = 0;
    		}
    	}
    	else
    	{
        	this.faceMovingDirection();
    	}
    	
    	super.onLivingUpdate();
    	
    	if ( this.getAttackTarget() != null )
    	{
    		if ( this.latchTimer > 80 ) // LATCH TIMER OF 80 MEANS FLY AWAY
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

	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
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
    		this.faceMovingDirection();
    	}    	
    	
    	
    	// boolean shouldDrop = false;
    	
        if ( !this.world.isRemote && this.getAttackTarget() != null && !this.getAttackTarget().isDead ) 
        {
            if ( this.isRiding() && this.getRidingEntity() == this.getAttackTarget() )
            {
            	
            	if ( this.ticksExisted % 5 == 0 )
        		{
                    this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
            		if ( this.rand.nextInt(5) != 0 ) this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.3F+this.rand.nextFloat()/10.0F, 0.8F+this.rand.nextFloat()*0.4F);
        		}
            	
        		if ( this.ticksExisted % 12 == 0 )
        		{
        			this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.6F+this.world.rand.nextFloat()/10.0F, 1.2F+this.world.rand.nextFloat()/5.0F);
        			this.getAttackTarget().addVelocity(-this.getHorizontalFacing().getFrontOffsetX()/14.0D, 0.25D+rand.nextDouble()*0.25D, -this.getHorizontalFacing().getFrontOffsetZ()/14.0D);
                	this.getAttackTarget().velocityChanged = true;
        		}
            	else
            	{
            		this.getAttackTarget().addVelocity(0, 0.04D, 0);
                	this.getAttackTarget().velocityChanged = true;
            	}
            	
                float time = 20F; 
                if ( !this.inWater )
                {
                    time *= 2F * (Math.random() + 1F);
                }
                else
                {
                    time += Math.random() * Math.random() * 2 * ((Math.random() < 0.5) ? -1 : 1);
                }
                
                if ( this.lastAttack + time < this.ticksExisted )
                {
//                	float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
//                  this.getAttackTarget().setHealth(this.getAttackTarget().getHealth()-attackDamage);
                    
                    this.attackEntityAsMob(this.getAttackTarget());
                    this.heal((float)(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue()/2.0F));
			        // this.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.2F, 1.5F);
			        this.playSound(SoundEvents.ENTITY_WITCH_DRINK, 2.0F, 1.0F);
                    //this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1, true, false));
                    this.spawnSweepParticles();
                }

            }
            else if ( this.latchTimer <= 0 && this.getDistanceSq(this.getAttackTarget()) <= 1.4 )
            {
                if ( this.rand.nextBoolean() )
                {
                	this.grabTarget(this.getAttackTarget());
                }
                else
                {
                	this.latchTimer = 20 + rand.nextInt(5)*5;
                }
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 1, true, false));
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 10, 0, true, false));
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
//	public void handleStatusUpdate(byte id)
//    {
//		if (id == 28) // head ram
//		{
//			this.play
//		}
//		else
//		{
//			super.handleStatusUpdate(id);
//		}
//	}
//    
//    protected void playTameEffect(boolean play)
//    {
//        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;
//
//        if (!play)
//        {
//            enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
//        }
//
//        for (int i = 0; i < 7; ++i)
//        {
//            double d0 = this.rand.nextGaussian() * 0.02D;
//            double d1 = this.rand.nextGaussian() * 0.02D;
//            double d2 = this.rand.nextGaussian() * 0.02D;
//            this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
//        }
//    }
    
	@SideOnly(Side.CLIENT)
    public int oldCameraMode = -1;
	
    public int latchTimer = 0;
    
    public void faceMovingDirection()
    {
    	if ( this.isRiding() ) return;
    	
    	try
    	{
	    	PathPoint p = this.getNavigator().getPath().getFinalPathPoint(); // TODO get target..?

	        double d0 = (p.x - this.posX) * 2;
	        double d2 = (p.z - this.posZ) * 2;
	        double d1 = p.y - this.posY;
	
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
	        this.rotationPitch = f1;
	        this.rotationYaw = f;
    	}
    	catch ( Exception e ) {}
    }

    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }

    public void grabTarget(EntityLivingBase entity)
    {
    	
    	if ( this.world.isRemote )
    	{
			this.oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
    	}
		
        //if(!world.isRemote)
        {
            if ( !this.isRiding() )
            {
                this.startRiding(entity, true);
                if ( !world.isRemote && entity instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
                }
            }
        }
    }

    public void dismountZotz()
    {
		if ( this.world.isRemote && this.oldCameraMode != -1 )
		{
			Minecraft.getMinecraft().gameSettings.thirdPersonView = this.oldCameraMode;
			this.oldCameraMode = -1;
		}

    	this.latchTimer = 100 + this.rand.nextInt(7)*10;
    	Entity mount = this.getRidingEntity();
    	if ( mount != null )
    	{
            // this.isFromZotz = true;
            this.dismountRidingEntity();
            // this.isFromZotz  = false;
            this.dismountEntity(mount);
            
//        	if ( mount instanceof EntityLivingBase )
//        	{
//            	((EntityLivingBase) mount).removeActivePotionEffect(Potion.getPotionFromResourceLocation("slowness"));
//        	}

            if ( !world.isRemote )
            {
                if(mount instanceof EntityPlayerMP)
                {
                    ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
                }
            }
            
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
    public double getYOffset() {
        if(getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
            return getRidingEntity().height - 2.25F;
        } else if(getRidingEntity() != null) {
            return (getRidingEntity().getEyeHeight() / 2) - this.height;
        } else {
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
        if (!this.world.isRemote && entityIn.isDead && entityIn == this.getRidingEntity() )
        {
            this.dismountZotz();
        }
        return flag;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
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
    
    public class AIMeleeAttack extends EntityAIAttackMelee
    {
        public AIMeleeAttack()
        {
            super(EntityZotzpyre.this, 1.5D, true);
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	double attackRange = this.getAttackReachSqr(entity);
        	
        	if ( this.attacker.isRiding() || EntityZotzpyre.this.latchTimer > 80 ) return;
        	
	        if ( dist <= attackRange && this.attackTick < 7 )
	        {
	        	if ( this.attackTick == 0 )
	        	{
		    		this.attackTick = 22 + rand.nextInt(11);
	        		
			        this.attacker.attackEntityAsMob(entity);
			        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);
			        
			        //this.attacker.motionX /= 2.0D;
			        //this.attacker.motionZ /= 2.0D;
			        this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
		    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
	        	}
	        	else if ( this.attackTick == 3 ) // dist > 2
			    {
			        double d0 = entity.posX - this.attacker.posX;
	                double d1 = entity.posZ - this.attacker.posZ;
	                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
	                
                    this.attacker.motionX += d0 / f * 0.2D;
                    this.attacker.motionZ += d1 / f * 0.2D;
                    this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
		    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
                    
                    if ( entity.posY - this.attacker.posY <= -0.5D )
	                {
	                	this.attacker.motionY -= 0.2D;
	                }
	                else
	                {
	                	this.attacker.motionY += 0.2D;
	                }
		        }
		    }
//		    else if ( this.attackTick >= 25 ) // else // if ( dist <= 256.0D ) //( EntityZotzpyre.this.latchTimer <= 0 && dist <= 144.0D ) || dist <= 49.0D )
//	    	{
//		    	aaa
//	    		double d0 = entity.posX - this.attacker.posX;
//                double d1 = entity.posZ - this.attacker.posZ;
//                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
//                
//	    		this.attacker.motionY += 0.1D;
//	    		
//	    		this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
//	    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
//	    		this.attacker.motionX += d0 / f * 0.1D;
//                this.attacker.motionZ += d1 / f * 0.1D;
//	    	}
	    	else if ( this.attackTick <= 0 )
	        {
	    		this.attackTick = 22 + rand.nextInt(11);
	        }
	    	else if ( this.attackTick == 7 )
        	{
                //if ( dist > 2 )
                {
                	double d0 = entity.posX - this.attacker.posX;
	                double d1 = entity.posZ - this.attacker.posZ;
	                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
	                
                	if ( EntityZotzpyre.this.latchTimer <= 0 )
                	{
				        this.attacker.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 1.5F, 0.3F+this.attacker.world.rand.nextFloat()/5.0F);
				        this.attacker.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 1.5F, 1.3F+this.attacker.world.rand.nextFloat()/5.0F);
				        this.attacker.motionX += d0 / f * 1.08D + this.attacker.motionX * 0.16D;
	                    this.attacker.motionZ += d1 / f * 1.08D + this.attacker.motionZ * 0.16D;
	                    this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
			    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
                	}
                	else
                	{
				        this.attacker.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.8F+this.attacker.world.rand.nextFloat()/5.0F, 1.2F+this.attacker.world.rand.nextFloat()/5.0F);

				        this.attacker.motionX += d0 / f * 0.368D + this.attacker.motionX * 0.16D;
	                    this.attacker.motionZ += d1 / f * 0.368D + this.attacker.motionZ * 0.16D;
	                    this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
			    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
                	}
                    

	                if ( entity.posY - this.attacker.posY <= -0.5D )
	                {
	                	this.attacker.motionY -= 0.4D;
	                }
	                else
	                {
	                	this.attacker.motionY += 0.4D;
	                }
                }
	        }
//	        else
//	        {
//	    		this.attackTick = 25 + rand.nextInt(10);
//	        }
        }
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 8.0D + attackTarget.width;
        }
    }
    
    
    

}
