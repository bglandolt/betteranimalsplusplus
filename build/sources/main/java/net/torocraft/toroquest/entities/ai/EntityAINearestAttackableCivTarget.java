package net.torocraft.toroquest.entities.ai;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.torocraft.toroquest.civilization.CivilizationType;
import net.torocraft.toroquest.civilization.player.PlayerCivilizationCapabilityImpl;
import net.torocraft.toroquest.entities.EntityGuard;

public class EntityAINearestAttackableCivTarget extends EntityAITarget {

	protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
	protected final Predicate<EntityPlayer> targetEntitySelector;
	protected EntityPlayer targetEntity;

	protected EntityGuard taskOwner;

	public EntityAINearestAttackableCivTarget(EntityGuard npc)
	{
		super(npc, true, false);
		this.taskOwner = npc;
		this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(npc);
		this.setMutexBits(1);
		
		this.targetEntitySelector = new Predicate<EntityPlayer>()
		{
			public boolean apply(@Nullable EntityPlayer target)
			{

				if ( !isSuitableTarget(taskOwner, target, false, true) )
				{
					return false;
				}

//				if (!EntitySelectors.NOT_SPECTATING.apply(target))
//				{
//					return false;
//				}
				
				return shouldAttackPlayerBasedOnCivilization(target);
			}
		};
	}

	protected boolean shouldAttackPlayerBasedOnCivilization(EntityPlayer player)
	{
		if ( !this.taskOwner.playerGuard.equals("") )
		{
			if ( player.getName().equals(this.taskOwner.playerGuard) )
			{
				if ( player.getRevengeTarget() != null )
				{
					if ( player.getRevengeTarget() instanceof EntityPlayer )
					{
						this.taskOwner.setAnnoyed((EntityPlayer)player.getRevengeTarget());
					}
					this.taskOwner.setAttackTarget(player.getRevengeTarget());
				}
				return false;
			}
			else if ( player.getRevengeTarget() instanceof EntityPlayer && player.getRevengeTarget().getName().equals(this.taskOwner.playerGuard) )
			{
				if ( this.taskOwner.actionReady() ) this.taskOwner.insult(player);
				return true;
			}
		}
		else for ( ItemStack itemStack : player.getArmorInventoryList() )
		{
			if ( itemStack.getItem().equals(Item.getByNameOrId("toroquest:bandit_helmet") ) || itemStack.getItem().equals(Item.getByNameOrId("toroquest:legendary_bandit_helmet") ) )
			{
				if ( this.taskOwner.actionReady() ) this.taskOwner.chat(player, "bandit", null);
				return true;
			}
		}

		CivilizationType civ = this.taskOwner.getCivilization();

		if ( civ == null )
		{
			return false;
		}
		
		int rep = PlayerCivilizationCapabilityImpl.get(player).getReputation(civ);

		if ( this.taskOwner.murderWitness() == player || this.taskOwner.underAttack() == player )
		{
			if ( this.taskOwner.actionReady() ) this.taskOwner.insult(player);
			return true;
		}
		
		if ( rep > -50 )
		{
			return false;
		}
		
		rep = -(1000/rep);
		
		if ( rep < 1 )
		{
			if ( this.taskOwner.actionReady() ) this.taskOwner.insult(player);
			return true;
		}

		if ( this.taskOwner.world.rand.nextInt(rep) == 0 )
		{
			if ( this.taskOwner.actionReady() ) this.taskOwner.insult(player);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if ( this.taskOwner.world.rand.nextInt(30) != 0 || this.taskOwner.getAttackTarget() != null )
        {
			return false;
	    }
		
//		if (this.taskOwner.getCivilization() == null)
//		{
//			return false;
//		}

//		if (shouldExecuteNonPlayer()) {
//			return true;
//		}

		return shouldExecutePlayer();
	}
	
	protected boolean shouldExecutePlayer()
	{
		targetEntity = taskOwner.world.getNearestAttackablePlayer(taskOwner.posX, taskOwner.posY, taskOwner.posZ, 25, 15, null, targetEntitySelector);

		if ( targetEntity != null && this.taskOwner.canEntityBeSeen(targetEntity ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
//	protected AxisAlignedBB getTargetableArea(double targetDistance)
//	{
//		return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 32, targetDistance);
//	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		if ( this.targetEntity != null && this.targetEntity.isEntityAlive() )
		{
			this.taskOwner.setAttackTarget(this.targetEntity);
		}
		super.startExecuting();
	}

	
}