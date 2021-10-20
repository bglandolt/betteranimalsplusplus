package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBearAttack;
import its_meow.betteranimalsplus.common.entity.ai.EntityAICallForHelp;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import its_meow.betteranimalsplus.init.ModEntities;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBear extends EntityMob
{

    public int warningSoundTicks;

    public EntityBear(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.9F);
        this.stepHeight = 2.05F;
        this.dismountZotz();
    }
    
    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityBear.AIMeleeAttack());
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D, 20));
        //this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.targetTasks.addTask(1, new EntityAICallForHelp(this, 16, new Class[0]));
        this.targetTasks.addTask(2, new EntityAIBearAttack(this));
    }
    
    public int cannotReachTimer = 0;
    public int fleeTimer = 0;

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(14.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }
    
    
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
       if ( this.isRiding() && source == DamageSource.IN_WALL )
       {
            return false;
       }
       else
       {
            if ( this.motionY > 0 )
            {
            	this.motionY = 0.0D;
            }
            
            this.cannotReachTimer += 30;
            
            if ( this.fleeTimer > 0 && ( source.getTrueSource() != null && this.getDistance(source.getTrueSource()) < 4 ) )
            {
                this.cannotReachTimer = 0;
                this.fleeTimer = 0;
            }
            
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {   	
    	this.faceEntity(entityIn, 20.0F, 20.0F);
    	this.getLookHelper().setLookPositionWithEntity(entityIn, 20.0F, 20.0F);
    	
        this.cannotReachTimer = 0;
        
    	if ( this.isRiding() )
    	{
    		this.fleeTimer = 0;
    	}
    	else
    	{	
    		// FLEEING
    		
//    		float doubledHealthPercentage = (this.getHealth()/this.getMaxHealth())*2.0F;
//    		
//    		if ( entityIn instanceof EntityPlayer && this.rand.nextFloat() >= doubledHealthPercentage )
//    		{
//    			this.getNavigator().clearPath();
//    			this.fleeTimer = (int)(20.0F + 20.0F * doubledHealthPercentage);
//    		}
    	}
    	
		if ( !this.world.isRemote && this.knockBackAttack && this.getDistance(entityIn) < 4 )
		{
			Vec3d velocityVector = new Vec3d(entityIn.posX-this.posX, 0, entityIn.posZ-this.posZ);
			double push = 0.5D;
			entityIn.addVelocity(velocityVector.x*push, -0.02D, velocityVector.z*push);
			entityIn.velocityChanged = true;
        }
		
		this.knockBackAttack = false;
		
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
    
    public boolean knockBackAttack = false;
    
    public class AIMeleeAttack extends PublicEntityAIAttack
    {
        public AIMeleeAttack()
        {
            super(EntityBear.this, 1.3D, true);
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	double attackRange = this.getAttackReachSqr(entity);

	        if ( dist <= attackRange && this.attackTick <= 0 )
	        {
		        this.attackTick = 30;
		        this.attacker.attackEntityAsMob(entity);
                AIHelper.faceEntitySmart(this.attacker, this.attacker.getAttackTarget());
			    this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);
                this.attacker.motionX /= 2.0D;
			    this.attacker.motionY /= 2.0D;
			    this.attacker.motionZ /= 2.0D;
		    }
		    else if ( dist <= attackRange * 2.0D )
		    {
		        if ( this.attackTick <= 0 )
		        {
		        	this.attackTick = 15;
		        }
		        else if ( this.attackTick <= 10 )
		        {
		        	if ( this.attackTick == 10 )
		        	{
		        		if ( rand.nextBoolean() )
		        		{
		        			this.attacker.world.setEntityState(this.attacker, (byte) 26);
		        			EntityBear.this.knockBackAttack = false;
		        		}
		        		else
		        		{
		        			this.attacker.world.setEntityState(this.attacker, (byte) 28);
		        			EntityBear.this.knockBackAttack = true;
		        		}
		        	}
		        	EntityBear.this.playWarningSound();
		        }
	        } 
	        else
	        {
	        	this.attackTick = 30;
	        }
        }

        @Override
        public void updateTask()
        {
        	if ( this.attacker.isRiding() || EntityBear.this.fleeTimer > 0 )
        	{
        		return;
        	}
        	
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            double d0 = 1+this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            --this.delayCounter;

            if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
            {
                this.targetX = entitylivingbase.posX;
                this.targetY = entitylivingbase.getEntityBoundingBox().minY;
                this.targetZ = entitylivingbase.posZ;
                this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

                if (this.canPenalize)
                {
                    this.delayCounter += failedPathFindingPenalty;
                    if (this.attacker.getNavigator().getPath() != null)
                    {
                        net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                        if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    }
                    else
                    {
                        failedPathFindingPenalty += 10;
                    }
                }

                if (d0 > 1024.0D)
                {
                    this.delayCounter += 10;
                }
                else if (d0 > 256.0D)
                {
                    this.delayCounter += 5;
                }

                if ( d0 >= 25 )
                {
	                if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
	                {
	                    this.delayCounter += 15;
	                }
                }
                else if ( d0 <= 9 )
                {
                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, 0.0D);
                	Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
					double push = (2.0D+d0)*2.0D;
					this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
                	this.attacker.velocityChanged = true;
                }
                else
                {
                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget/(17.0D-16.0D));
                }
            }

            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPerformAttack(entitylivingbase, d0);
        }
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return attackReachModifier() + attackTarget.width;
        }
    }
    
    protected double attackReachModifier()
    {
    	return 14.0D;
    }
    
    public int attackAnimationTimer = -1;
    public int attackAnimationTimerStanding = -1;

    @SideOnly(Side.CLIENT)
   	public void handleStatusUpdate(byte id)
    {
   		if (id == 26)
   		{
   			this.attackAnimationTimer = 50;
   		}
   		else if (id == 27)
   		{
   			this.latchSnapshot = this.ticksExisted;
   		}
   		else if (id == 28)
   		{
   			this.attackAnimationTimerStanding = 50;
   		}
   		else if (id == 29)
   		{
   			this.warningSoundTicks = 40;
   		}
   		else
   		{
   	   		super.handleStatusUpdate(id);
   		}
   	}
    
    @Override
    public void onLivingUpdate()
    {
    	if ( this.getAttackTarget() != null )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 120 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 1 || this.getDistance(this.getAttackTarget()) > 4 ) )
        	{
        		this.fleeTimer = 80 + rand.nextInt(80);
        		this.cannotReachTimer = 0;
        	}
            
    		if ( this.isRiding() )
    		{
//    			this.rotationYaw = -this.getAttackTarget().rotationYaw;
//	    		this.prevRotationYaw = -this.getAttackTarget().prevRotationYaw;
	    		this.rotationYaw = 0;
	    		this.prevRotationYaw = 0;
    		}
    		else if ( this.fleeTimer > 0 )
    		{
				if ( this.getNavigator().noPath() )
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
					
	                if ( vec3d != null )
	                {
	                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);
	                }
	                else
	                {
	                	vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 12, 6, this.getAttackTarget().getPositionVector());
						
		                if ( vec3d != null )
		                {
		                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);
		                }
		                else
		                {
		                	this.fleeTimer -= 20;
		                }
	                }
				}
				AIHelper.faceMovingDirection(this);
    		}
    		else
    		{
	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    	}
    	else
    	{
    		this.cannotReachTimer = 0;
    	}
    	
    	super.onLivingUpdate();
    	    	
    	if ( this.getAttackTarget() != null )
    	{
    		if ( this.isRiding() )
    		{
//    			this.rotationYaw = -this.getAttackTarget().rotationYaw;
//	    		this.prevRotationYaw = -this.getAttackTarget().prevRotationYaw;
    			this.rotationYaw = 0;
	    		this.prevRotationYaw = 0;
    		}
    		else if ( this.fleeTimer > 0 )
    		{
				AIHelper.faceMovingDirection(this);
    		}
    		else
    		{
	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    	}
    	}
    	
    	if ( !this.world.isRemote && this.getAttackTarget() != null && !this.getAttackTarget().isDead ) // SERVER
        {
             if ( this.isRiding() && this.getRidingEntity() == this.getAttackTarget() )
             {
             	 if ( this.ticksExisted % 4 == 0 )
         		 {
             		 if ( this.rand.nextInt(4) != 0 )
             		 {
             			 this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 2.0F+this.rand.nextFloat()/10.0F, 0.5F+this.rand.nextFloat()*0.3F);
             			 if ( rand.nextBoolean() ) this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F+this.rand.nextFloat()/10.0F, 1.2F+this.rand.nextFloat()*0.3F);
             		 }
         		 }
                 
                 if ( this.ticksExisted % 14 == 0 ) // ATTACK
                 {
                     this.attackEntityAsMob(this.getAttackTarget());
                     this.spawnSweepParticles();
         			 if ( rand.nextBoolean() ) this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.2F, 0.8F + rand.nextFloat()/5.0F);
                 }

            	 if ( this.motionY > 0 )
         		 {
         			 this.getAttackTarget().motionY = 0;
         		 }
            	 
         		 this.getAttackTarget().motionX = 0;
        		 this.getAttackTarget().motionZ = 0;
        		 this.getAttackTarget().setVelocity(0, -0.25D, 0);
        		 this.getAttackTarget().velocityChanged = false;
        		 this.getAttackTarget().lastTickPosX = this.getAttackTarget().posX;
        		 this.getAttackTarget().lastTickPosY = this.getAttackTarget().posY;
        		 this.getAttackTarget().lastTickPosZ = this.getAttackTarget().posZ;
        		 this.getAttackTarget().setSprinting(false);
        		 this.getAttackTarget().setSneaking(true);
        		 this.getAttackTarget().resetActiveHand();
     			 Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
             }
             else if ( this.latchTimer <= 0 && this.getDistanceSq(this.getAttackTarget()) <= 1.6 )
             {
                 if ( this.grabTarget(this.getAttackTarget()) )
                 {
	                 this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 90, 7, true, false));
	                 this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 90, 128, true, false));
	                 this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 18, 0, true, false));
	     			 this.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 2.0F, 0.3F);
	     			 this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.6F+this.world.rand.nextFloat()/10.0F, 1.2F+this.world.rand.nextFloat()/5.0F);
                 }
             }
         }
         
         if ( this.latchTimer > 0 )
         {
         	this.latchTimer--;
         }
         
         if ( this.fleeTimer > 0 )
         {
         	this.fleeTimer--;
         }
         
         if ( this.isRiding() && this.ticksExisted - this.latchSnapshot > 100 )
         {
             this.dismountZotz();
         }
    }
    
//    @Override
//    public void onLivingUpdate()
//    {
//
//    	if ( this.getAttackTarget() != null )
//    	{
//    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    	}
//    	else
//    	{
//        	AIHelper.faceMovingDirection(this);
//    	}
//    	
//    	super.onLivingUpdate();
//    	
//    	if ( this.getAttackTarget() != null )
//    	{
//    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    	}
//    	else
//    	{
//        	AIHelper.faceMovingDirection(this);
//    	}
//    }
    
 // ==========================================================================================
    // ==========================================================================================
    //
    // OVERPOWER
    //
    // ==========================================================================================
    // ==========================================================================================
    
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if ( (this.isDead && this.isRiding()) || (this.isRiding() && this.getRidingEntity() != null && this.getRidingEntity().isDead) )
        {
            this.dismountZotz();
        }
        if (this.warningSoundTicks > 0)
        {
            --this.warningSoundTicks;
        }
    }
    
    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int oldCameraMode = -1;
    
    public int latchTimer = 0;
    public int latchSnapshot = 0;
    
    public boolean grabTarget(EntityLivingBase entity)
    {
    	this.fleeTimer = 0;
    	
    	if ( !entity.isPotionApplicable(new PotionEffect(MobEffects.SLOWNESS)) ) // || !entity.isPotionActive(MobEffects.SLOWNESS) )
    	{
    		return false;
    	}
    	
    	this.world.setEntityState(this, (byte)27);
    	this.latchSnapshot = this.ticksExisted;
    	
        if ( this.world.isRemote && entity instanceof EntityPlayerSP )
        {
			this.oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
        }
        
        if ( !this.isRiding() )
        {
            this.startRiding(entity, true);
            if ( !world.isRemote && entity instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
            }
        }
        
        return true;
    }

    public void dismountZotz()
    {
    	this.latchTimer = 100 + this.rand.nextInt(6)*10;
    	Entity mount = this.getRidingEntity();
    	
    	if ( mount != null )
    	{
            this.dismountRidingEntity();
            this.dismountEntity(mount);

            if ( !world.isRemote ) // SERVER
            {
                if ( mount instanceof EntityPlayerMP )
                {
                    ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
                }
            }
            else if ( this.oldCameraMode != -1 && mount instanceof EntityPlayerMP )
    		{
    			Minecraft.getMinecraft().gameSettings.thirdPersonView = this.oldCameraMode;
    			this.oldCameraMode = -1;
    		}
    	}
    }

    @Override
    public void dismountRidingEntity()
    {
    	super.dismountRidingEntity();
    }

    @Override
    public boolean canRiderInteract()
    {
        return true;
    }

    @Override
    public boolean shouldRiderSit()
    {
        return false;
    }
    
    @Override
    public double getYOffset()
    {
    	if ( this.getRidingEntity() != null )
    	{
    		return -this.getRidingEntity().height+0.5;
    	}
        return 0.5D;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
    	if ( this.getAttackTarget() == null && entitylivingbaseIn != null )
    	{
    		this.latchTimer = 80 * this.rand.nextInt(3);
    		//this.playAttackSound();
    	}
        super.setAttackTarget(entitylivingbaseIn);
    }

    public void spawnSweepParticles()
    {
    	double xx = this.posX + (double) (MathHelper.sin(this.rotationYaw * 0.017453292F))/2.0F;
    	double yy = this.posY + (double) this.height * 0.3D;
    	double zz = this.posZ + (double) -MathHelper.cos(this.rotationYaw * 0.017453292F)/2.0F;

    	if (this.world instanceof WorldServer)
    	{
    		for ( int i = 16; i > 0; i-- )
    		{
    			((WorldServer) this.world).spawnParticle(EnumParticleTypes.REDSTONE, xx, yy, zz, 0, 0, 0, 0, 0.08D, new int[0]);
    		}
    	}
    }
    
    // ==========================================================================================
    // ==========================================================================================
    //
    // OTHER
    //
    // ==========================================================================================
    // ==========================================================================================
    

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey("brownbear") ? ModEntities.entityMap.get("brownbear").despawn && !this.hasCustomName() : false;
    }

    public void playWarningSound()
    {
        if (this.warningSoundTicks <= 0)
        {
            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, 0.9F + rand.nextFloat()/5.0F);
			this.world.setEntityState(this, (byte) 29);
            this.warningSoundTicks = 40;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.8F, 0.95F + rand.nextFloat()/10.0F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        return false;
    }

    @Override
    public boolean isPreventingPlayerRest(EntityPlayer playerIn) {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.getAttackingEntity() == playerIn;
    }
    

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTables.BEAR_BROWN;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        this.doDropHead();
    }
    
    @Override
    protected float getWaterSlowDown() {
        return 0.9F;
    }
    
    protected void doDropHead() {
        if (!world.isRemote && !this.isChild()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(HeadTypes.BEARHEAD.getItem(1));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }
}
