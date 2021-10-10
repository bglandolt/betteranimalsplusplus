package net.torocraft.toroquest.entities.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.torocraft.toroquest.civilization.CivilizationType;
import net.torocraft.toroquest.civilization.CivilizationUtil;
import net.torocraft.toroquest.civilization.Province;
import net.torocraft.toroquest.civilization.player.PlayerCivilizationCapabilityImpl;
import net.torocraft.toroquest.config.ToroQuestConfiguration;
import net.torocraft.toroquest.entities.EntityGuard;
import net.torocraft.toroquest.entities.EntityOrc;
import net.torocraft.toroquest.entities.EntitySentry;
import net.torocraft.toroquest.entities.EntityToroMob;
import net.torocraft.toroquest.entities.EntityToroNpc;
import net.torocraft.toroquest.item.armor.ItemBanditArmor;
import net.torocraft.toroquest.item.armor.ItemLegendaryBanditArmor;

public class EntityAIBanditAttack extends EntityAITarget
{

	protected final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
	protected final Predicate<EntityLivingBase> targetEntitySelector;
	protected EntityLivingBase targetEntity;

	protected EntityToroMob taskOwner;

	public EntityAIBanditAttack(EntityToroMob npc)
	{
		// checkSight, onlyNearby
		super(npc, false, false);
		this.taskOwner = npc;
		this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter(taskOwner);
		this.setMutexBits(1);
		
		this.targetEntitySelector = new Predicate<EntityLivingBase>()
		{
			public boolean apply(@Nullable EntityLivingBase target)
			{
				if (!isSuitableTarget(taskOwner, target, false, false))
				{
					return false;
				}
				
				if ( target instanceof EntityGuard )
				{
					EntityGuard g = (EntityGuard)target;
					if ( !g.playerGuard.equals("") && g.spawnedNearBandits )
					{
						return false;
					}
				}
				
				if ( target instanceof EntityToroNpc || target instanceof EntityVillager || ( target instanceof EntityPlayer && shouldAttackPlayer(target) ) )
				{
					return true;
				}
				
				return false;
			}
		};
	}
	
	protected Province province;
	
	public boolean shouldAttackPlayer( EntityLivingBase target )
    {
		EntityPlayer player = (EntityPlayer)target;
		
		if ( taskOwner.getRevengeTarget() != null && taskOwner.getRevengeTarget() == target )
		{
			return true;
		}
		
//		if ( npc.tame )
//		{
//			return true;
//		}
		
		if ( this.taskOwner instanceof EntityOrc )
		{
			if ( !ToroQuestConfiguration.orcsDropMasks )
			{
				return true;
			}
		}
		else
		{
			if ( player.getHeldItemMainhand().getItem() == Items.EMERALD || player.getHeldItemOffhand().getItem() == Items.EMERALD )
			{
				if ( ((EntitySentry)(taskOwner)).passiveTimer == -1 )
				{
					((EntitySentry)(taskOwner)).passiveTimer = 4;
					this.taskOwner.getNavigator().tryMoveToEntityLiving(player, 0.45D);
					if ( rand.nextInt(3) == 0 ) ((EntitySentry)(taskOwner)).chat(player, "emeralds", null);
					return false;
				}
			}
			
			if ( ((EntitySentry)(taskOwner)).getTame() || ((EntitySentry)(taskOwner)).passiveTimer > 0 )
			{
				return false;
			}
			else if ( ((EntitySentry)(taskOwner)).passiveTimer == 0 )
			{
				((EntitySentry)(taskOwner)).chat(player, "betray", null);
				return true;
			}
				
		}
		
		for ( ItemStack i: player.inventory.armorInventory )
		{
			if ( i.getItem() instanceof ItemBanditArmor || i.getItem() instanceof ItemLegendaryBanditArmor )
			{
				return false;
			}
		}
		
		
//		Items.EMERALD
//		
//		passiveTimer
		
//		if ( village == null ) village = taskOwner.world.getVillageCollection().getNearestVillage(new BlockPos(taskOwner.getPosition()), 208);
//		
//		if ( village != null )
//		{
//			province = CivilizationUtil.getProvinceAt(taskOwner.world, village.getCenter().getX()/16,village.getCenter().getZ()/16);
//		}
//		else
		if ( province == null )
		{
			province = CivilizationUtil.getProvinceAt(taskOwner.world, (int)(target.posX/16),(int)(target.posZ/16));
		}
		
		if ( province == null )
		{
			return true;
		}
		
		CivilizationType civ = province.civilization;
		
		if ( civ == null )
		{
			return true;
		}
		
		int rep = PlayerCivilizationCapabilityImpl.get((EntityPlayer)target).getReputation(civ);
		
		if ( rep < -50 )
		{
			return false;
		}
		
		return true;
    }
	
	Random rand = new Random();
	
	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if ( this.taskOwner.getAttackTarget() != null || this.rand.nextInt(16) != 0 )
		{
			return false;
	    }
		
		List<EntityLivingBase> list = this.taskOwner.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.taskOwner.getPosition()).grow(32, 16, 32), this.targetEntitySelector);
		
		if (list.isEmpty())
		{
			return false;
		}

		Collections.sort(list, this.theNearestAttackableTargetSorter);

		for (EntityLivingBase npc : list)
		{
			if ( this.taskOwner.canEntityBeSeen(npc) )
			{
				targetEntity = npc;
				return true;
			}
		}
		for (EntityLivingBase npc : list)
		{
			if ( npc instanceof EntityVillager || this.taskOwner.getDistance(npc) <= 6.0D )
			{
				targetEntity = npc;
				return true;
			}
		}
		return false;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}

	public static class Sorter implements Comparator<Entity>
	{
		private final Entity theEntity;

		public Sorter(Entity theEntityIn)
		{
			this.theEntity = theEntityIn;
		}

		public int compare(Entity p_compare_1_, Entity p_compare_2_) {
			double d0 = this.theEntity.getDistanceSq(p_compare_1_);
			double d1 = this.theEntity.getDistanceSq(p_compare_2_);
			return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
		}
	}
	
 	@Nullable
    private BlockPos getRandPos(World worldIn, Entity entityIn, int horizontalRange, int verticalRange)
    {
        BlockPos blockpos = new BlockPos(entityIn);
        int i = blockpos.getX();
        int j = blockpos.getY();
        int k = blockpos.getZ();
        float f = (float)(horizontalRange * horizontalRange * verticalRange * 2);
        BlockPos blockpos1 = null;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int l = i - horizontalRange; l <= i + horizontalRange; ++l)
        {
            for (int i1 = j - verticalRange; i1 <= j + verticalRange; ++i1)
            {
                for (int j1 = k - horizontalRange; j1 <= k + horizontalRange; ++j1)
                {
                    blockpos$mutableblockpos.setPos(l, i1, j1);
                    IBlockState iblockstate = worldIn.getBlockState(blockpos$mutableblockpos);

                    if (iblockstate.getMaterial() == Material.WATER)
                    {
                        float f1 = (float)((l - i) * (l - i) + (i1 - j) * (i1 - j) + (j1 - k) * (j1 - k));

                        if (f1 < f)
                        {
                            f = f1;
                            blockpos1 = new BlockPos(blockpos$mutableblockpos);
                        }
                    }
                }
            }
        }

        return blockpos1;
    }
}