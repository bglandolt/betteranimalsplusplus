package its_meow.betteranimalsplus.common.entity;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAICallForHelp;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIWolfAttack;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import its_meow.betteranimalsplus.init.ModItems;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFeralWolf extends EntityTameableWithSelectiveTypes implements IMob {

    protected static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityFeralWolf.class, DataSerializers.FLOAT);

    public EntityFeralWolf(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 0.9F);
        this.setTamed(false);
        this.stepHeight = 1.55F;
        this.dismountZotz();
    }
    
    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityFeralWolf.AIMeleeAttack());
        this.tasks.addTask(3, new EntityAIWander(this, 0.6D, 40));
        //this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(1, new EntityAICallForHelp(this, 16, new Class[0]));
        this.targetTasks.addTask(2, new EntityAIWolfAttack(this));
    }
    
    public int cannotReachTimer = 0;
    public int fleeTimer = 0;
    
    protected double jumpModifier()
    {
    	return 0.36D;
    }
    
//    @Override
//    protected boolean canDespawn()
//    {
//    	return false;
//    }
    
    protected double forwardModifier()
    {
    	return 0.32D;
    }
    
    public class AIMeleeAttack extends PublicEntityAIAttack
    {
        public AIMeleeAttack()
        {
            super(EntityFeralWolf.this, 1.4D, true); // ttt
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist) // aaa
        {
        	double attackRange = this.getAttackReachSqr(entity);
        	         	
 	        if ( dist <= attackRange && this.attackTick < 7 )
 	        {
 	        	if ( this.attackTick == 0 )
 	        	{
 		    		this.attackTick = 22 + rand.nextInt(11);
 	        		
 			        this.attacker.attackEntityAsMob(entity);
 			        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);
 			        
 			        this.attacker.motionX /= 2.0D;
 			        this.attacker.motionY /= 2.0D;
 			        this.attacker.motionZ /= 2.0D;
 	        	}
 	        	else if ( this.attackTick == 3 ) // dist > 2
 			    {
 			        double d0 = entity.posX - this.attacker.posX;
 	                double d1 = entity.posZ - this.attacker.posZ;
 	                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
 	                
                    this.attacker.motionX += d0 / f * 0.2D;
                    this.attacker.motionZ += d1 / f * 0.2D;
                    this.attacker.faceEntity(entity, 20.0F, 20.0F);
 		    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
                     
                    if ( entity.posY - this.attacker.posY <= -1 )
 	                {
 	                	this.attacker.motionY -= 0.16D;
 	                }
 	                else
 	                {
 	                	this.attacker.motionY += 0.16D;
 	                }
 		        }
 		    }
 	    	else if ( this.attackTick <= 0 )
 	        {
 	    		this.attackTick = 22 + rand.nextInt(11);
 	        }
 	    	else if ( this.attackTick == 7 && dist <= attackRange*2 )
         	{
                this.attacker.world.setEntityState(this.attacker, (byte) 28);

             	double d0 = entity.posX - this.attacker.posX;
                double d1 = entity.posZ - this.attacker.posZ;
                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);
                
             	if ( EntityFeralWolf.this.latchTimer <= 0 )
             	{
			        this.attacker.motionX += d0 / f * 1.25D + this.attacker.motionX * forwardModifier();
                    this.attacker.motionZ += d1 / f * 1.25D + this.attacker.motionZ * forwardModifier();
                    //this.attacker.faceEntity(entity, 20.0F, 20.0F);
                    AIHelper.faceEntitySmart(this.attacker, entity);
		    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
		    		//EntityFeralWolf.this.latchTimer = 20 + EntityFeralWolf.this.rand.nextInt(7)*5;
             	}
             	else
             	{
			        this.attacker.motionX += d0 / f * 0.75D + this.attacker.motionX * forwardModifier();
                    this.attacker.motionZ += d1 / f * 0.75D + this.attacker.motionZ * forwardModifier();
                    //this.attacker.faceEntity(entity, 20.0F, 20.0F);
                    AIHelper.faceEntitySmart(this.attacker, entity);
		    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
             	}
             	
                if ( entity.posY - this.attacker.posY <= -0.5D )
                {
                	this.attacker.motionY -= jumpModifier();
                }
                else
                {
                	this.attacker.motionY += jumpModifier();
                }
         	}
        }
        
        @Override
        public void updateTask()
        {
        	if ( this.attacker.isRiding() || EntityFeralWolf.this.fleeTimer > 0 )
        	{
        		return;
        	}
        	
        	EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            if ( entitylivingbase == null ) return;
            
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
                
                if ( d0 >= 16 )
                {
	                if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
	                {
	                    this.delayCounter += 15;
	                }
	                
	                if ( d0 <= 32 )
	                {
	                	this.attacker.setSprinting(true);
	                }
	                else
	                {
	                	this.attacker.setSprinting(false);
	                }
                }
                else
                {
                	this.attacker.setSprinting(false);
                	
                	if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget*(1.0D+(d0-16.0D)/20.0D) ) )
	                {
	                    this.delayCounter += 15;
	                }
                	
                	Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
					double push = (2.0D+d0)*2.0D;
					this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
                	this.attacker.velocityChanged = true;
                }

//                if ( d0 >= 25 )
//                {
//	                if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
//	                {
//	                    this.delayCounter += 15;
//	                }
//                }
//                else if ( d0 <= 9 )
//                {
//                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, 0.0D);
//                	Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
//					double push = (2.0D+d0)*2.0D;
//					this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
//                	this.attacker.velocityChanged = true;
//                }
//                else
//                {
//                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget);
//                }
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
    	return 7.0D;
    }
    
    public int leaping = -1;
    
    @SideOnly(Side.CLIENT)
   	public void handleStatusUpdate(byte id)
    {
    	if ( id == 23 )
		{
			if ( this.oldCameraMode != -1 ) Minecraft.getMinecraft().gameSettings.thirdPersonView = this.oldCameraMode;
			this.oldCameraMode = -1;
		}
    	else if ( id == 25 )
   		{
    		this.oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
   		}
		else if (id == 27)
   		{
   			this.latchSnapshot = this.ticksExisted;
   		}
    	else if (id == 28)
   		{
   			this.leaping = 20;
   		}
   		else
   		{
   			super.handleStatusUpdate(id);
   		}
   	}

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }
    
    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
        
    	if ( this.world.isRemote ) return;
    	
    	if ( this.getAttackTarget() != null )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 200 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 2 || this.getDistance(this.getAttackTarget()) > 6 ) )
        	{
        		this.fleeTimer = 150 + rand.nextInt(50);
        		this.getNavigator().clearPath();
        		this.cannotReachTimer = this.fleeTimer;
        	}
            
    		if ( this.isRiding() )
    		{
    			this.rotationYaw = -this.getAttackTarget().rotationYaw;
	    		this.prevRotationYaw = -this.getAttackTarget().prevRotationYaw;
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
    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    	}
    	else
    	{
    		this.cannotReachTimer = 0;
    	}
    	
//    	super.onLivingUpdate();
//    	    	
//    	if ( this.getAttackTarget() != null )
//    	{
//    		if ( this.isRiding() )
//    		{
//    			this.rotationYaw = -this.getAttackTarget().rotationYaw;
//	    		this.prevRotationYaw = -this.getAttackTarget().prevRotationYaw;
//    		}
//    		else if ( this.fleeTimer > 0 )
//    		{
//				AIHelper.faceMovingDirection(this);
//    		}
//    		else
//    		{
//    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
//	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
//	    	}
//    	}
    	
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
                     this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 18, 7, true, false));
	                 this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 18, 128, true, false));
                     this.spawnSweepParticles();
                 }
                 
            	 if ( this.motionY > 0 )
         		 {
         			 this.motionY = 0;
         		 }
            	 
            	 if ( this.getAttackTarget().motionY > 0 )
         		 {
         			 this.getAttackTarget().motionY = 0;
         		 }
            	 
         		 this.getAttackTarget().motionX = 0;
        		 this.getAttackTarget().motionZ = 0;
        		 this.getAttackTarget().velocityChanged = false;
        		 this.getAttackTarget().lastTickPosX = this.getAttackTarget().posX;
        		 this.getAttackTarget().lastTickPosY = this.getAttackTarget().posY;
        		 this.getAttackTarget().lastTickPosZ = this.getAttackTarget().posZ;
        		 this.getAttackTarget().setSprinting(false);
        		 this.getAttackTarget().setSneaking(true);
        		 this.getAttackTarget().resetActiveHand();
     			 Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
             }
         }
         
         if ( this.latchTimer > 0 )
         {
         	this.latchTimer--;
         }
         
         if ( this.fleeTimer > 0 )
         {
 	    	this.setSprinting(false);
         	this.fleeTimer--;
         }
         
         if ( this.cannotReachTimer > 0 )
         {
         	this.cannotReachTimer--;
         	if ( this.cannotReachTimer == 1 && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > 12 )
         	{
         		this.setAttackTarget(null);
         	}
         }
         
         if ( this.isRiding() && this.ticksExisted - this.latchSnapshot > 80 )
         {
             this.dismountZotz();
         }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	// AGGRO
    	if ( source.getTrueSource() instanceof EntityLivingBase )    		
    	{
    		if ( this.getAttackTarget() == null )
    		{
	    		if ( this.getDistance(source.getTrueSource()) > 25 )
	    		{
	    			this.cannotReachTimer = 200;
	    		}
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    		else if ( this.rand.nextInt(3) == 0 )
    		{
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    	}
    	
       if ( this.isRiding() && source == DamageSource.IN_WALL )
       {
            return false;
       }
       else
       {
    	   
	   	   	if ( this.isRiding() )
	   	   	{
	   	   		this.fleeTimer = 0;
	   	   		this.cannotReachTimer = 0;
	   	   	}
	   	   	else
	   	   	{
	   	   		if ( amount > 2 )
	   	   		{
		   	   		float doubledHealthPercentage = (this.getHealth()/this.getMaxHealth())*1.5F;
		   	   		
			   	   	List<EntityFeralWolf> nearbyWolves = world.getEntitiesWithinAABB(EntityFeralWolf.class, new AxisAlignedBB(this.getPosition()).grow(12, 4, 12));
					
			   		float mod = 1.0F + (nearbyWolves.size()-1.0F)/10.0F;
			   		
			   		if ( mod > 1.5F )
					{
						mod = 1.5F;
					}
		   		
		   	   		if ( source.getTrueSource() instanceof EntityPlayer && this.rand.nextFloat() >= doubledHealthPercentage*mod )
		   	   		{
		   	   			this.getNavigator().clearPath();
		   	   			this.fleeTimer = (int)(30.0F + (200.0F / (1.0F+doubledHealthPercentage)) - ((nearbyWolves.size()-1.0F)*20.0F));
		   	   		}
	   	   		}
	   	   	}
   	   	
            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }

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
    	this.playSound(SoundEvents.ENTITY_WOLF_GROWL, 1.0F, 0.7F + this.world.rand.nextFloat() * 0.3F );
    	
//    	this.faceEntity(entityIn, 20.0F, 20.0F);
//    	this.getLookHelper().setLookPositionWithEntity(entityIn, 20.0F, 20.0F);
    	
    	this.cannotReachTimer = 0;
		this.fleeTimer = 0;
    	this.setSprinting(false);
				
   		float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
   		List<EntityFeralWolf> nearbyWolves = world.getEntitiesWithinAABB(EntityFeralWolf.class, new AxisAlignedBB(this.getPosition()).grow(12, 4, 12));

   		float mod = 1.0F + (nearbyWolves.size()-1)/10.0F;
   		
   		if ( mod > 1.5F )
		{
			mod = 1.5F;
		}
   		
   		float doubledHealthPercentage = (this.getHealth()/this.getMaxHealth())*1.5F;

   		if ( entityIn instanceof EntityPlayer && this.rand.nextFloat() >= doubledHealthPercentage*mod )
   		{
   			this.getNavigator().clearPath();
   			this.fleeTimer = 30 + this.rand.nextInt(5)*10;
    		this.getNavigator().clearPath();
   		}
   		
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), damage*mod);

        if ( flag )
        {
            this.applyEnchantments(this, entityIn);

            if ( this.latchTimer <= 0 && this.getDistanceSq(this.getAttackTarget()) <= 2.0D && this.grabTarget(this.getAttackTarget()) )
            {
            	this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 36, 7, true, false));
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 36, 128, true, false));
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 18, 0, true, false));
    			this.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 2.0F, 0.3F);
    			this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.6F+this.world.rand.nextFloat()/10.0F, 1.2F+this.world.rand.nextFloat()/5.0F);
            }
        }

        return flag;
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
    }
    
    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }

    //@SideOnly(Side.CLIENT)
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
    	this.world.setEntityState(this, (byte)25);
    	
    	this.latchSnapshot = this.ticksExisted;
        
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
    	this.latchTimer = 70 + this.rand.nextInt(4)*10; // 70 - 100
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
            
        	this.world.setEntityState(this, (byte)23);
    	}
    }
    
//    public boolean grabTarget(EntityLivingBase entity)
//    {
//    	this.fleeTimer = 0;
//    	
//    	if ( !entity.isPotionApplicable(new PotionEffect(MobEffects.SLOWNESS)) ) // || !entity.isPotionActive(MobEffects.SLOWNESS) )
//    	{
//    		return false;
//    	}
//    	
//    	this.world.setEntityState(this, (byte)27);
//    	this.latchSnapshot = this.ticksExisted;
//    	
//    	if ( this.world.isRemote )
//    	{
//			this.oldCameraMode = Minecraft.getMinecraft().gameSettings.thirdPersonView;
//			Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
//    	}
//		
//        //if(!world.isRemote)
//        {
//            if ( !this.isRiding() )
//            {
//                this.startRiding(entity, true);
//                if ( !world.isRemote && entity instanceof EntityPlayerMP)
//                {
//                    ((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
//                }
//            }
//        }
//        
//        return true;
//    }
//
//    public void dismountZotz()
//    {
//		if ( this.world.isRemote && this.oldCameraMode != -1 )
//		{
//			Minecraft.getMinecraft().gameSettings.thirdPersonView = this.oldCameraMode;
//			this.oldCameraMode = -1;
//		}
//
//    	this.latchTimer = 100 + this.rand.nextInt(7)*10;
//    	Entity mount = this.getRidingEntity();
//    	if ( mount != null )
//    	{
//            this.dismountRidingEntity();
//            this.dismountEntity(mount);
//
//            if ( !world.isRemote ) // SERVER
//            {
//                if(mount instanceof EntityPlayerMP)
//                {
//                    ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
//                }
//            }
//            
//    	}
//    }

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
    		return -this.getRidingEntity().height+0.8D;
    	}
        return -1.0D;
    }
    
//    @Override
//    public double getYOffset()
//    {
//        return -1.0D;
//    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
    	if ( this.getAttackTarget() == null && entitylivingbaseIn != null )
    	{
    		this.latchTimer = 60 * this.rand.nextInt(3);
    		this.fleeTimer = 0;
    		this.cannotReachTimer = 0;
    		this.playAttackSound();
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
protected void updateAITasks() {
    this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
}

@Override
protected void entityInit() {
    super.entityInit();
    this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
}

@Override
protected void playStepSound(BlockPos pos, Block blockIn)
{
    this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.2F, 1.0F);
}

public void playAttackSound()
{
    this.playSound(SoundEvents.ENTITY_WOLF_GROWL, 1.0F, 0.8F+rand.nextFloat()/5.0F);
}

@Override
protected SoundEvent getAmbientSound() 
{
	this.playSound(SoundEvents.ENTITY_WOLF_PANT, 0.8F, 0.4F + this.world.rand.nextFloat() / 8.0F );
	return null;
}

@Override
protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
    return SoundEvents.ENTITY_WOLF_HURT;
}

@Override
protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_WOLF_DEATH;
}

@Override
protected float getSoundVolume() {
    return 0.4F;
}

@Override
public float getEyeHeight() {
    return this.height * 0.8F;
}

//    @Override
//    public void setTamed(boolean tamed) {
//        super.setTamed(tamed);
//
//        if (tamed) {
//            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
//        } else {
//            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
//        }
//
//        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
//    }
    
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();

                    if (itemfood.isWolfsFavoriteMeat() && this.dataManager.get(DATA_HEALTH_ID).floatValue() < 20.0F) {
                        if (!player.capabilities.isCreativeMode) {
                            itemstack.shrink(1);
                        }

                        this.heal(itemfood.getHealAmount(itemstack));
                        return true;
                    }
                }
            }

            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack) && (!(itemstack.getItem() instanceof ItemFood) || !((ItemFood) itemstack.getItem()).isWolfsFavoriteMeat())) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase) null);
            }
        } else if(this.isTamingItem(itemstack.getItem())) {
            boolean wearingPowerHead = false;
            ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            if (stack.getItem() == Items.SKULL) {
                if (stack.getMetadata() == 5) { // 5 = "dragon"
                    wearingPowerHead = true;
                }
            }
            if (stack.getItem() == ModItems.HIRSCHGEIST_SKULL_WEARABLE) {
                wearingPowerHead = true;
            }

            if (wearingPowerHead) { // player.isWearing(part))

                if (!player.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                if (!this.world.isRemote) {
                    if (this.rand.nextInt(100) <= 14 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                        this.setTamedBy(player);
                        this.navigator.clearPath();
                        this.setAttackTarget((EntityLivingBase) null);
                        this.aiSit.setSitting(true);
                        this.setHealth(20.0F);
                        this.playTameEffect(true);
                        this.world.setEntityState(this, (byte) 7);
                    } else {
                        this.playTameEffect(false);
                        this.world.setEntityState(this, (byte) 6);
                    }
                }

                return true;
            } else {
                if (!this.world.isRemote) {
                    //player.sendMessage(new TextComponentString("You cannot tame feral wolves without proving your prowess. Discover a mighty enemy, defeat it, and wear its head. Feral Wolves only bow to the protector of the forests."));
                }
            }
        }

        return super.processInteract(player, hand);
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return this.isTamed() ? (1F - (this.getMaxHealth() - this.dataManager.get(DATA_HEALTH_ID).floatValue()) * 0.04F) : 0.25F;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() instanceof ItemFood && ((ItemFood) stack.getItem()).isWolfsFavoriteMeat();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        return false;
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
        if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast)) {
            if (target instanceof EntityFeralWolf) {
                EntityFeralWolf entityferalwolf = (EntityFeralWolf) target;

                if (entityferalwolf.isTamed() && entityferalwolf.getOwner() == owner) {
                    return false;
                }
            }

            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target)) {
                return false;
            } else {
                return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return this.isTamed() && super.canBeLeashedTo(player);
    }

    @Override
    public int getVariantMax() {
        return 6;
    }

    @Override
    protected IVariantTypes getBaseChild() {
        return null;
    }
    
    @Override
    protected int[] getTypesFor(Set<BiomeDictionary.Type> types) {
        if(types.contains(Type.FOREST) && !types.contains(Type.CONIFEROUS)) {
            return new int[] {3, 6};
        } else if(types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) {
            return new int[] {1, 3, 6};
        } else if(types.contains(Type.CONIFEROUS) && types.contains(Type.SNOWY)) {
            return new int[] {2, 3};
        } else if(types.contains(Type.SNOWY) && !types.contains(Type.FOREST)) { 
            return new int[] {2, 4};
        } else if(types.contains(Type.FOREST) && types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) { 
            return new int[] {5, 6, 3, 1};
        } else {
            return new int[] {1, 2, 3, 4, 5, 6};
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        switch(this.getTypeNumber()) {
        case 1: return ModLootTables.WOLF_BLACK;
        case 2: return ModLootTables.WOLF_SNOWY;
        case 3: return ModLootTables.WOLF_TIMBER;
        case 4: return ModLootTables.WOLF_ARCTIC;
        case 5: return ModLootTables.WOLF_BROWN;
        case 6: return ModLootTables.WOLF_RED;
        default: return super.getLootTable();
        }
    }

    @Override
    protected String getContainerName() {
        return "feralwolf";
    }
    


    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        this.doHeadDrop();
    }
    
   public void doHeadDrop()
   {
        if (!world.isRemote && !this.isChild() && !(this instanceof EntityCoyote)) 
        {
            if (this.rand.nextInt(12) == 0)
            {
                ItemStack stack = new ItemStack(HeadTypes.WOLFHEAD.getItem(this.getTypeNumber()));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }

    public boolean isPreventingPlayerRest(EntityPlayer playerIn) 
    {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && !this.isTamed() && this.getAttackTarget() != null && playerIn.getDistanceSq(this) <= 50D;
    }

    protected boolean isValidLightLevel()
    {
        return true;
    }
    
    @Override
    protected float getWaterSlowDown() {
        return 0.9F;
    }
    

}
