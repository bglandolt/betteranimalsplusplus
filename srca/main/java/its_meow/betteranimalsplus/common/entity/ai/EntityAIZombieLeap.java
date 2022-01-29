package its_meow.betteranimalsplus.common.entity.ai;

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
    int leapTimer = 0;

    protected final int leapTimerReset;

    public EntityAIZombieLeap(EntityLiving leapingEntity, float leapMotionYIn, int reset, boolean immuneWhileLeaping)
    {
        this.leaper = leapingEntity;
        this.leapMotionY = leapMotionYIn;
        this.immuneWhileLeaping = immuneWhileLeaping;
        this.leapTimerReset = reset;
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
            if ( this.leapTimer < 0 )
            {
            	if ( this.leaper.onGround )
                {
            		double d0 = this.leaper.getDistanceSq(this.leapTarget);
            		
    	            if (d0 >= 4.0D && d0 <= 20.0D)
    	            {
    	            	this.leapTimer = this.leapTimerReset;
                		return true;
    	            }
            	}
            }
            else
            {
            	this.leapTimer--;
            }
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return this.leaper.onGround;
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
            this.leaper.motionX += d0 / (double)f * 0.6D + this.leaper.motionX * 0.3D;
            this.leaper.motionZ += d1 / (double)f * 0.6D + this.leaper.motionZ * 0.3D;
        }

        if ( this.leapTarget.posY - this.leaper.posY >= 0.5 )
        {
        	this.leaper.motionY = (double)this.leapMotionY * 1.2D;
        }
        else
        {
        	this.leaper.motionY = (double)this.leapMotionY;
        }
        if ( immuneWhileLeaping && this.leaper.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getBaseValue() < 2.22D ) this.leaper.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(2.22D);

    }
}