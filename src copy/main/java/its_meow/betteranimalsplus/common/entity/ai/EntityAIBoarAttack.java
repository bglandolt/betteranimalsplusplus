package its_meow.betteranimalsplus.common.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import its_meow.betteranimalsplus.common.entity.EntityBoar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class EntityAIBoarAttack extends EntityAITarget
{
	
    protected final EntityAINearestAttackableTarget.Sorter sorter;
	protected final Predicate<EntityLivingBase> targetEntitySelector;
	protected EntityLivingBase targetEntity;

	protected EntityBoar taskOwner;
	//protected int threat;
	
	public EntityAIBoarAttack( EntityBoar npc )
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
				
				if ( target instanceof EntityBoar )
				{
					return false;
				}
				
				if ( target instanceof EntityPlayer || ( taskOwner.isHungry && ( (target instanceof EntityAnimal && target.height <= 0.75F) || target instanceof EntityVillager ) && rand.nextInt(10) == 0 ) )
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
		if ( !this.taskOwner.isChild() && this.taskOwner.getAttackTarget() != null && rand.nextInt(20) != 0 )
        {
			return false;
	    }
				
		List<EntityLivingBase> list = this.taskOwner.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.taskOwner.getPosition()).grow(12, 4, 12), this.targetEntitySelector);
	
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
					if ( this.taskOwner.getDistance(npc) <= 6 )
					{
						this.targetEntity = npc;
						return true;
					}
					else if ( !npc.isSneaking() && !npc.isInvisible() && this.taskOwner.canEntityBeSeen(npc) )
					{
						if ( !this.taskOwner.hasPath() )
						{
							Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.taskOwner, 8, 4, new Vec3d(npc.posX, npc.posY, npc.posZ));
	
				            if ( vec3d != null )
				            {
				            	if ( npc.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) >= npc.getDistanceSq(this.taskOwner) )
				            	{
				            		this.taskOwner.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.7D);
				            	}
				            }
						}
					}
				}
				else if ( this.taskOwner.canEntityBeSeen(npc) && !npc.isInvisible() )
				{
					this.targetEntity = npc;
					this.taskOwner.isHungry = false;
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
	
    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
    	this.taskOwner.setSprinting(false);
        super.resetTask();
    }
	
	@Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}