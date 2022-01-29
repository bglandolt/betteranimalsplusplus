package its_meow.betteranimalsplus.common.entity.ai;

import its_meow.betteranimalsplus.common.entity.EntityBrownBear;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIBearAttackWithAnimations extends EntityAIBase
{
    World world;
    
    public EntityBrownBear attacker;
    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
    public int attackTick;
    /** The speed with which the mob will approach the target */
    public double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    /** The PathEntity of our entity. */
    Path path;
    public double targetX;
    public double targetY;
    public double targetZ;

    public EntityAIBearAttackWithAnimations(EntityBrownBear creature, double speedIn)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.setMutexBits(3);
    }

    
   // public class AIMeleeAttack extends PublicEntityAIAttack
    

        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	double attackRange = this.getAttackReachSqr(entity);

	        if ( dist <= attackRange && this.attackTick <= 0 )
	        {
		        this.attackTick = 30;
		        this.attacker.attackEntityAsMob(entity);
                // AIHelper.faceEntitySmart(this.attacker, entity);
			    this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);
                this.attacker.motionX /= 2.0D;
			    this.attacker.motionY /= 2.0D;
			    this.attacker.motionZ /= 2.0D;
		    }
		    else if ( dist <= attackRange * 2.0D )
		    {
		        if ( this.attackTick <= 0 )
		        {
		        	this.attackTick = 25;
		        }
		        else if ( this.attackTick > 8 && this.attackTick < 12 )
		        {
		        	this.attacker.setSprinting(true);
		        	if ( this.attackTick == 10 )
			        {
		        		//if ( this.attacker.attackAnimationTimerStanding < 1 && this.attacker.attackAnimationTimer < 1 )
		        		{
			        		if ( this.attacker.world.rand.nextBoolean() || entity.height < 0.8 )
			        		{
			        			this.attacker.world.setEntityState(this.attacker, (byte) 26);
			        			//this.attacker.attackAnimationTimer = 20;
			        			this.attacker.knockBackAttack = false;
			        		}
			        		else
			        		{
			        			this.attacker.world.setEntityState(this.attacker, (byte) 28);
			        			//this.attacker.attackAnimationTimerStanding = 30;
			        			this.attacker.knockBackAttack = true;
			        		}
		        		}
				        this.attacker.playWarningSound();
			        }
		        }
		        else
		        {
		        	this.attacker.setSprinting(false);
		        }
	        } 
	        else
	        {
	        	this.attackTick = 30;
	        	this.attacker.setSprinting(false);
	        }
        }

        public void updateTask()
        {
        	if ( this.attacker.isRiding() || this.attacker.fleeTimer > 0 )
        	{
        		return;
        	}
        	
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            
            if ( entitylivingbase == null ) return;
            
            double d0 = 1+this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);

        	this.attacker.setSprinting(false);
        	
        	if ( d0 > 16 )
        	{
            	if ( this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
                {
                }
        	}
        	else
        	{
            	if ( this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget*(1.0D+(d0-16.0D)/20.0D) ) )
                {
                }
        	}
        	
        	if ( this.attacker.isSprinting() || d0 > this.getAttackReachSqr(entitylivingbase)/2.0D )
        	{
        		Vec3d velocityVector = new Vec3d(entitylivingbase.posX-this.attacker.posX, 0,entitylivingbase.posZ-this.attacker.posZ);
        		double push = (2.0D+d0)*2.0D;
        		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
            	this.attacker.velocityChanged = true;
        	}
        	else
        	{
        		Vec3d velocityVector = new Vec3d(this.attacker.posX-entitylivingbase.posX, 0,this.attacker.posZ-entitylivingbase.posZ);
        		double push = (2.0D+d0)*2.0D;
        		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
            	this.attacker.velocityChanged = true;
        	}

            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPerformAttack(entitylivingbase, d0);
        }
    
    
        
//		Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
//		double push = (2.0D+d0)*2.0D;
//		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
//    	this.attacker.velocityChanged = true;
    
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        else
        {
            this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
            return this.path != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        return true;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
    	this.attacker.setSprinting(false);
        this.attacker.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
//    public void updateTask()
//    {
//        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
//        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
//        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
//        --this.delayCounter;
//
//        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
//        {
//            this.targetX = entitylivingbase.posX;
//            this.targetY = entitylivingbase.getEntityBoundingBox().minY;
//            this.targetZ = entitylivingbase.posZ;
//            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
//
//            if (this.canPenalize)
//            {
//                this.delayCounter += failedPathFindingPenalty;
//                if (this.attacker.getNavigator().getPath() != null)
//                {
//                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
//                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
//                        failedPathFindingPenalty = 0;
//                    else
//                        failedPathFindingPenalty += 10;
//                }
//                else
//                {
//                    failedPathFindingPenalty += 10;
//                }
//            }
//
//            if (d0 > 1024.0D)
//            {
//                this.delayCounter += 10;
//            }
//            else if (d0 > 256.0D)
//            {
//                this.delayCounter += 5;
//            }
//
//            if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget))
//            {
//                this.delayCounter += 15;
//            }
//        }
//
//        this.attackTick = Math.max(this.attackTick - 1, 0);
//        this.checkAndPerformAttack(entitylivingbase, d0);
//    }
    


//    protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
//    {
//        double d0 = this.getAttackReachSqr(enemy);
//
//        if (distToEnemySqr <= d0 && this.attackTick <= 0)
//        {
//            this.attackTick = 20;
//            this.attacker.swingArm(EnumHand.MAIN_HAND);
//            this.attacker.attackEntityAsMob(enemy);
//        }
//    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return 14.0D + attackTarget.width;
    }
}