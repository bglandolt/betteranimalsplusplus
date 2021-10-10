package its_meow.betteranimalsplus.common.entity.ai;
/*
 * GNU GENERAL PUBLIC LICENSE Version 3 --- FROM MO CREATURES MOD
 */

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowHerd extends EntityAIBase {

    /** The child that is following its parent. */
    EntityLiving theAnimal;
    EntityLiving herdAnimal;
    double moveSpeed;
    double maxRange;
    double minRange;
    private int delayCounter;
    private int executionChance;

    public EntityAIFollowHerd(EntityLiving animal, double speed, double minRangeIn, double maxRangeIn, int chance) 
    {
        this.theAnimal = animal;
        this.moveSpeed = speed;
        this.minRange = minRangeIn; //4D;
        this.maxRange = maxRangeIn; //20D;
        this.executionChance = chance;
    }

    public EntityAIFollowHerd(EntityLiving animal, double speed)
    {
        this(animal, speed, 4.0D, 20.0D, 120);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {

        if (this.theAnimal.getRNG().nextInt(this.executionChance) != 0)
        {
            return false;
        }

        List<EntityLiving> entities = this.theAnimal.world.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.getEntityBoundingBox().expand(this.maxRange, 4.0D, this.maxRange));
        
        EntityLiving entityliving = null;
        double furthest = 0.0D;

        for ( EntityLiving entity : entities )
        {
            double dist = this.theAnimal.getDistanceSq(entity);
            if ( dist > furthest && dist > this.minRange && dist <= this.maxRange && this.theAnimal != entity)
            {
            	furthest = dist;
                entityliving = entity;
            }
        }

        if (entityliving == null)
        {
            return false;
        }
        else
        {
            this.herdAnimal = entityliving;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()
    {
    	if (!this.herdAnimal.isEntityAlive())
    	{
            return false;
        }
    	else
        {
            double d0 = this.theAnimal.getDistanceSq(this.herdAnimal);
            return d0 >= this.minRange && d0 <= this.maxRange;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.delayCounter = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        this.herdAnimal = null;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 20;
            //System.out.println("moving " + this + " to " + this.herdAnimal);
            this.theAnimal.getNavigator().tryMoveToEntityLiving(this.herdAnimal, this.moveSpeed);
        }
    }

    /**
     * Changes task random possibility for execution
     */
    public void setExecutionChance(int newchance) {
        this.executionChance = newchance;
    }
}
