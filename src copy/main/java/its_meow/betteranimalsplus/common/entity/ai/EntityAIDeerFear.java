package its_meow.betteranimalsplus.common.entity.ai;


import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class EntityAIDeerFear extends EntityAIBase
{
    /** The entity we are attached to */
    protected EntityCreature entity;
    private final double farSpeed;
    private final double nearSpeed;
    protected EntityLivingBase closestLivingEntity;
    /** The PathEntity of our entity */
    private Path path;
    /** The PathNavigate of our entity */
    private final PathNavigate navigation;
    /** Class of entity this behavior seeks to avoid */

    public EntityAIDeerFear(EntityCreature entityIn, double farSpeedIn, double nearSpeedIn)
    {
        this.entity = entityIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.navigation = entityIn.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
//    	if ( this.entity.world.rand.nextInt(10) != 0 )
//    	{
//    		return false;
//    	}
    	
    	if ( this.entity.getRevengeTarget() != null )
		{
			this.closestLivingEntity = this.entity.getRevengeTarget();
			Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3d == null)
            {

            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
            {
            	
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
			return true;
		}
    	
    	if ( this.entity.ticksExisted % 18 != 0 )
    	{
    		return false;
    	}
    	
    	List<EntityPlayer> list = this.entity.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.entity.getPosition()).grow(18, 6, 18), new Predicate<EntityPlayer>()
	    {
    		public boolean apply(@Nullable EntityPlayer entity)
	    	{
				return true;
			}
		});
        
        for ( EntityPlayer player : list )
        {
        	if ( !this.entity.canEntityBeSeen(player) )
        	{
        		continue;
        	}
        	
        	if ( this.entity.getDistance(player) < 8.0D + (abs(player.motionX)+abs(player.motionY)+abs(player.motionZ))*10.0D )
        	{
        		
        	}
        	else
        	{
        		continue;
        	}
        	
            this.closestLivingEntity = player;
            this.entity.setRevengeTarget(player);

            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 4, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3d == null)
            {
                continue;
            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
            {
            	continue;
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
        }
    	
    	List<EntityAnimal> animals = this.entity.world.getEntitiesWithinAABB(EntityAnimal.class, new AxisAlignedBB(this.entity.getPosition()).grow(12, 4, 12), new Predicate<EntityAnimal>()
	    {
    		public boolean apply(@Nullable EntityAnimal entity)
	    	{
				return true;
			}
		});
        
        for ( EntityAnimal animal : animals )
        {
        	if ( animal.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null && animal.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() >= 1 )
			{
			    return true;
			}
        	
            this.closestLivingEntity = animal;
            this.entity.setRevengeTarget(animal);

            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 4, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

            if (vec3d == null)
            {
                continue;
            }
            else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.entity))
            {
            	continue;
            }
            else
            {
                this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
                return this.path != null;
            }
        }
        
        return false;
    }

    public static double abs(double value)
    {
        return value >= 0.0D ? value : -value;
    }
    
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.navigation.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.navigation.setPath(this.path, this.farSpeed);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
		this.entity.setRevengeTarget(this.closestLivingEntity);

        if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0D)
        {
            this.entity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.entity.getNavigator().setSpeed(this.farSpeed);
        }
    }
}