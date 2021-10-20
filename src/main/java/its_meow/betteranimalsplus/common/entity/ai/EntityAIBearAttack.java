package its_meow.betteranimalsplus.common.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import its_meow.betteranimalsplus.common.entity.EntityBear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIBearAttack extends EntityAITarget
{
	
    protected final EntityAINearestAttackableTarget.Sorter sorter;
	protected final Predicate<EntityLivingBase> targetEntitySelector;
	protected EntityLivingBase targetEntity;

	protected EntityBear taskOwner;
	//protected int threat;
	
	public EntityAIBearAttack( EntityBear npc )
	{
		//    checkSight, onlyNearby
		super( npc, false, false );
		this.taskOwner = npc;
		this.sorter = new EntityAINearestAttackableTarget.Sorter(npc);
		this.setMutexBits(1);
		
		this.targetEntitySelector = new Predicate<EntityLivingBase>()
		{
			public boolean apply(@Nullable EntityLivingBase target)
			{
				if (!isSuitableTarget(taskOwner, target, false, false))
				{
					return false;
				}
				
				if ( target instanceof EntityBear )
				{
					return false;
				}
				
				if ( target instanceof EntityPlayer || ( target instanceof EntityAnimal && target.height <= 1.0F && !target.isInWater() ) || target instanceof EntityVillager )
				{
					return true;
				}
				
				return false;
			}
		};
	}

	Random rand = new Random();
	
	@Override
	public boolean shouldExecute()
	{
		if ( this.taskOwner.getAttackTarget() != null )
		{
			return false;
		}
		
		if ( rand.nextInt(20) != 0 )
        {
			return false;
	    }
				
		List<EntityLivingBase> list = this.taskOwner.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.taskOwner.getPosition()).grow(16, 8, 16), this.targetEntitySelector);
	
		if ( list.isEmpty() )
		{
			return false;
		}
		else
		{
			Collections.sort(list, this.sorter);

			for ( EntityLivingBase npc : list )
			{
				if ( npc instanceof EntityPlayer )
				{
					if ( this.taskOwner.getDistance(npc) <= 8 )
					{
						this.targetEntity = npc;
						return true;
					}
					else if ( this.taskOwner.getDistance(npc) <= 12 && !npc.isInvisible() && this.taskOwner.canEntityBeSeen(npc) )
					{
						if ( !npc.isSneaking() )
						{
							this.taskOwner.faceEntity(npc, 10.0F, 10.0F);
							this.taskOwner.getLookHelper().setLookPositionWithEntity(npc, 20.0F, 20.0F);
							if ( rand.nextInt(12) == 0 )
							{
								this.targetEntity = npc;
								return true;
							}
							else if ( rand.nextBoolean() )
							{
								this.taskOwner.playWarningSound();
							}
						}
					}
				}
				else if ( this.taskOwner.canEntityBeSeen(npc) && !npc.isInvisible() )
				{
					this.targetEntity = npc;
					return true;
				}
			}
			return false;
		}
	}
	
//	protected AxisAlignedBB getTargetableArea(double targetDistance)
//	{
//		return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 32, targetDistance);
//	}

	public static class Sorter implements Comparator<Entity>
	{
		private final Entity theEntity;

		public Sorter(Entity theEntityIn)
		{
			this.theEntity = theEntityIn;
		}

		public int compare(Entity entity1, Entity entity2)
		{
			double d0 = this.theEntity.getDistanceSq( entity1 );
			double d1 = this.theEntity.getDistanceSq( entity2 );
			return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
		}
	}
	
	@Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}