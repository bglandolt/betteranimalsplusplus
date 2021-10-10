package net.torocraft.toroquest.entities.ai;


import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIZombieLeap extends EntityAIBase
{
    /** The entity that is leaping. */
    EntityLiving leaper;
    /** The entity that the leaper is leaping towards. */
    EntityLivingBase leapTarget;
    /** The entity's motionY after leaping. */
    float leapMotionY;
    boolean immuneWhileLeaping = false;

    public EntityAIZombieLeap(EntityLiving leapingEntity, float leapMotionYIn, boolean immuneWhileLeaping)
    {
        this.leaper = leapingEntity;
        this.leapMotionY = leapMotionYIn;
        this.immuneWhileLeaping = immuneWhileLeaping;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        this.leapTarget = this.leaper.getAttackTarget();

        if (this.leapTarget == null)
        {
            return false;
        }
        else
        {
            double d0 = this.leaper.getDistanceSq(this.leapTarget);

            if (d0 >= 6.0D && d0 <= 20.0D)
            {
                if ( !this.leaper.onGround )
                {
                    return false;
                }
                else
                {
                    return ( this.leaper.getRNG().nextInt(40) == 0 || this.leaper.getNavigator().getPathToEntityLiving(this.leapTarget) == null );
                }
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.leaper.onGround;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        double d0 = this.leapTarget.posX - this.leaper.posX;
        double d1 = this.leapTarget.posZ - this.leaper.posZ;
        float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        if ((double)f >= 1.0E-4D)
        {
            this.leaper.motionX += d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D;
            this.leaper.motionZ += d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D;
        }

        this.leaper.motionY = (double)this.leapMotionY;
    	if ( immuneWhileLeaping && this.leaper.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getBaseValue() < 2.22D ) this.leaper.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.22D);

    }
}