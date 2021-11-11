package its_meow.betteranimalsplus.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PublicEntityAIAttack extends EntityAIBase
{
    World world;
    
    public EntityCreature attacker;
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
    public final int attackInterval = 20;

    public PublicEntityAIAttack(EntityCreature creature, double speedIn)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.setMutexBits(3);
    }

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
    public void updateTask()
    {
//    	if ( this.attacker.isRiding() || this.attacker.fleeTimer > 0 )
//    	{
//    		return;
//    	}
    	
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
    		// PUSH TO
    		Vec3d velocityVector = new Vec3d(entitylivingbase.posX-this.attacker.posX, 0,entitylivingbase.posZ-this.attacker.posZ);
    		double push = (2.0D+d0)*2.0D;
    		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
        	this.attacker.velocityChanged = true;
    	}
    	else
    	{
    		// PUSH AWAY
    		Vec3d velocityVector = new Vec3d(this.attacker.posX-entitylivingbase.posX, 0,this.attacker.posZ-entitylivingbase.posZ);
    		double push = (2.0D+d0)*2.0D;
    		this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
        	this.attacker.velocityChanged = true;
    	}

        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase enemy, double distToEnemySqr)
    {
        double d0 = this.getAttackReachSqr(enemy);

        if (distToEnemySqr <= d0 && this.attackTick <= 0)
        {
            this.attackTick = 20;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(enemy);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (this.attacker.width * 2.0F * this.attacker.width * 2.0F + attackTarget.width + 2.0D);
    }
}