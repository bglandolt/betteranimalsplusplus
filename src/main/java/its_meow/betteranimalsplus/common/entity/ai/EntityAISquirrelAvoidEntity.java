package its_meow.betteranimalsplus.common.entity.ai;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import its_meow.betteranimalsplus.common.entity.EntityDeer;
import its_meow.betteranimalsplus.common.entity.EntitySquirrel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class EntityAISquirrelAvoidEntity<T extends Entity> extends EntityAIBase
{
    /** The entity we are attached to */
    protected EntitySquirrel entity;
    private final double farSpeed;
    private final double nearSpeed;
    protected EntityLivingBase closestLivingEntity;
    /** The PathEntity of our entity */
    private Path path;
    /** The PathNavigate of our entity */
    private final PathNavigate navigation;
    /** Class of entity this behavior seeks to avoid */

    public EntityAISquirrelAvoidEntity(EntitySquirrel entityIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn)
    {
        this.entity = entityIn;
        this.farSpeed = farSpeedIn;
        this.nearSpeed = nearSpeedIn;
        this.navigation = entityIn.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution
     */
    public boolean shouldExecute()
    {

    	if ( this.entity.world.rand.nextInt(8) != 0 )
    	{
    		return false;
    	}

    	List<EntityLivingBase> list = this.entity.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.entity.getPosition()).grow(8, 4, 8), new Predicate<EntityLivingBase>()
	    {
    		public boolean apply(@Nullable EntityLivingBase entity)
	    	{
    			if ( entity instanceof EntitySquirrel )
    			{
    				return false;
    			}
    			if ( entity.width <= 0.7 && entity.height <= 0.7 )
                {
    				return false;
                }
    			return true;
			}
		});
    	
        for ( EntityLivingBase e : list )
        {
            this.closestLivingEntity = e;
            
            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

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
                
                if ( this.path == null )
                {
                	continue;
                }
                else
                {
                    return true;
                }
            }
        }
        
        this.closestLivingEntity = null;
        return false;
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
        if (this.entity.getDistanceSq(this.closestLivingEntity) < 40.0D)
        {
            this.entity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.entity.getNavigator().setSpeed(this.farSpeed);
        }
    }
}