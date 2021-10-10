package net.torocraft.toroquest.entities;

import java.util.Random;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AIAttackWithSword extends EntityAIBase
{
    World world;
    protected EntityCreature attacker;
    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
    protected int attackTick = 0;
    /** The speed with which the mob will approach the target */
    double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    boolean longMemory = true;
    /** The PathEntity of our entity. */
    Path path;
    //private int delayCounter = 2;
//    private double targetX;
//    private double targetY;
//    private double targetZ;
//    protected final int attackInterval = 22;
//    private int failedPathFindingPenalty = 0;
//    private boolean canPenalize = false;
    protected boolean offhandAttack = false;
    
	protected float range = 3.0F;

    public AIAttackWithSword(EntityCreature creature, double speedIn)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        //this.longMemory = true;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	ItemStack iStack = this.attacker.getHeldItemMainhand();
    	
		if (iStack != null && iStack.getItem() instanceof ItemBow)
		{
    		return false;
    	}
        		
        if ( iStack != null )
    	{
        	String s = iStack.getItem().getRegistryName().toString();
        		 if ( s.contains("lance") ) 		{range = 6.0F;}
        	else if ( s.contains("pike") )  	 	{range = 6.0F;}
        	else if ( s.contains("glaive") ) 		{range = 4.5F;}
        	else if ( s.contains("halberd") ) 		{range = 4.5F;}
        	else if ( s.contains("greatsword") ) 	{range = 4.0F;}
        	else if ( s.contains("spear") ) 		{range = 4.0F;}
    	}
		        
        if ( !shouldContinueExecuting() )
        {
        	return false;
        }
        
        if ( this.attacker instanceof EntitySentry )
        {
        	
        }
        else
        {
	        this.path = this.attacker.getNavigator().getPathToEntityLiving(this.attacker.getAttackTarget());
	
	        if ( this.path != null )
	        {
	            return true;
	        }
        }
        return this.getAttackReachSqr(this.attacker.getAttackTarget()) >= this.attacker.getDistanceSq(this.attacker.getAttackTarget().posX, this.attacker.getAttackTarget().getEntityBoundingBox().minY, this.attacker.getAttackTarget().posZ);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
    	ItemStack iStack = this.attacker.getHeldItemMainhand();
    	
		if ( iStack != null && iStack.getItem() instanceof ItemBow )
		{
    		return false;
    	}
		
        if (this.attacker.getAttackTarget() == null)
        {
            return false;
        }
        
        if (!this.attacker.getAttackTarget().isEntityAlive())
        {
            return false;
        }

        return true; // !(this.attacker.getAttackTarget() instanceof EntityPlayer) || !((EntityPlayer)this.attacker.getAttackTarget()).isSpectator() && !((EntityPlayer)this.attacker.getAttackTarget()).isCreative();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
//    	ItemStack iStack = this.attacker.getHeldItemMainhand();
//    	
//		if ( iStack == null || iStack.getItem() instanceof ItemBow )
//		{
//    		return;
//    	}

		this.attacker.getNavigator().setPath( this.path, this.speedTowardsTarget );

        //this.delayCounter = 2;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.attacker.setAttackTarget(null);
        this.attacker.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
    	if ( !shouldContinueExecuting() )
    	{
    		return;
    	}
        
        this.attacker.faceEntity(attacker, 20.0F, 20.0F);
        this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
        double d0 = this.attacker.getDistanceSq(this.attacker.getAttackTarget().posX, this.attacker.getAttackTarget().getEntityBoundingBox().minY, this.attacker.getAttackTarget().posZ);
        //this.attackTick = Math.max(this.attackTick - 1, 0);
        this.attackTick--;
        this.checkAndPerformAttack(this.attacker.getAttackTarget(), d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase victim, double distanceSq)
    {        
        boolean backPeddaling = false;
        
        if ( this.attacker instanceof EntitySentry )
        {
        	EntitySentry e = (EntitySentry)this.attacker;
        	if ( e.isDrinkingPotion() )
			{
            	this.attackTick = 10;
    	        this.attacker.setSprinting(false);
        		backPeddaling = true;
				return;
			}
        	if ( e.stance < 5 )
        	{
    	        this.attacker.setSprinting(false);
        		backPeddaling = true;
        	}
        }
        else if ( this.attacker instanceof EntityGuard )
        {
    		EntityGuard e = (EntityGuard)this.attacker;
    		if ( e.stance < 5 )
         	{
    	        this.attacker.setSprinting(false);
         		backPeddaling = true;
         	}
        }
        
        this.attacker.setSprinting(false);
        
        if ( !this.attacker.collidedHorizontally && !backPeddaling && !this.attacker.isHandActive() && distanceSq <= 36 && distanceSq >= 3 && this.attacker.getNavigator().getPathToEntityLiving(victim) != null )
        {
        	int tt = Math.abs(this.attackTick-3) % 40;
        	
        	if ( tt < 5 )
        	{
        		this.attacker.setSprinting(true);
        	}
        	if ( tt == 0 )
        	{
		        Vec3d velocityVector = new Vec3d(victim.posX - this.attacker.posX, 0, victim.posZ - this.attacker.posZ);
		        if ( !this.world.isRemote ) this.attacker.addVelocity((velocityVector.x)/16.0,0.0D,(velocityVector.z)/16.0);
        	}
        }
        
        double attackDistance = this.getAttackReachSqr(victim);

        if ( this.attacker.getActiveHand().equals(EnumHand.OFF_HAND) )
        {
        	this.attackTick = 6; // for adding an attack delay after blocking
        }
        else if ( distanceSq <= attackDistance )
        {
        	if ( this.attackTick <= 0 )
        	{
	            if ( !offhandAttack )
	            {
	            	this.attacker.swingArm(EnumHand.MAIN_HAND);
	            	this.attackTick = 20+rand.nextInt(9);
	            	this.attacker.attackEntityAsMob(victim);
	            }
	            else
	            {
	            	this.attacker.swingArm(EnumHand.OFF_HAND);
	            	this.attackTick = 12+rand.nextInt(7);
	            	offhandAttack = false;
	            	this.attacker.attackEntityAsMob(victim);
	            	return;
	            }
	        	
	            ItemStack iStack = this.attacker.getHeldItem(EnumHand.OFF_HAND);
	            
	            if ( iStack != null && !( iStack.isEmpty() ) && !( iStack.getItem() instanceof ItemBow ) && !( iStack.getItem() instanceof ItemPotion ) && !( iStack.getItem() instanceof ItemShield ) )
	            {
	            	offhandAttack = true;
	            	this.attackTick = 12+rand.nextInt(7);
	            }
        	}
        }
    }
    
//    private int getOffHandAttackTimer()
//    {
//    	
//    }
    
    Random rand = new Random();
    
    protected double getAttackReachSqr(EntityLivingBase attackTarget)
    {
        return (double)(this.attacker.width * range * this.attacker.width * range + attackTarget.width);
    }

	public static boolean canReach(EntityCreature creature)
	{
		return !creature.getNavigator().noPath();
	}
	
	
}