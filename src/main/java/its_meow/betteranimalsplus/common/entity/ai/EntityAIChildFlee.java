package its_meow.betteranimalsplus.common.entity.ai;


import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityAIChildFlee extends EntityAIBase
{
    protected final EntityCreature creature;
    protected final double speed;
    protected double randPosX;
    protected double randPosY;
    protected double randPosZ;

    public EntityAIChildFlee(EntityCreature creature, double speedIn)
    {
        this.creature = creature;
        this.speed = speedIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if ( !this.creature.isChild() )
    	{
    		return false;
    	}
    	
        if ( this.creature.getRevengeTarget() == null || this.creature.getRevengeTarget().getPositionVector() == null )
        {
            return false;
        }
        
        if ( this.creature.getNavigator().getPathToEntityLiving(this.creature.getRevengeTarget()) == null )
        {
        	return false;
        }
        
    	this.creature.setAttackTarget(null);
    	
        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.creature, 12, 6, this.creature.getRevengeTarget().getPositionVector());

        if (vec3d != null)
        {
            this.randPosX = vec3d.z;
            this.randPosY = vec3d.y;
            this.randPosZ = vec3d.z;
            return true;
        }
        return false;
    }
    
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.creature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.creature.getNavigator().noPath();
    }
}