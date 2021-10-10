package net.torocraft.toroquest.entities;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.torocraft.toroquest.config.ToroQuestConfiguration;

public class EntityToroMob extends EntityCreature implements IMob
{
    public int fleeModifier = (rand.nextInt(ToroQuestConfiguration.banditAndOrcFleeHealthThreshold*2+1));
	
	public EntityToroMob(World worldIn)
	{
		super(worldIn);
	}

	public SoundCategory getSoundCategory()
	{
		return SoundCategory.HOSTILE;
	}

	@Override
	public void setAttackTarget(EntityLivingBase e)
	{
		if ( !(e instanceof EntityToroMob) )
		{
			super.setAttackTarget(e);
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		this.updateArmSwingProgress();
		super.onLivingUpdate();
	}

	protected SoundEvent getSwimSound()
	{
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	protected SoundEvent getSplashSound()
	{
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
	}

	protected SoundEvent getHurtSound()
	{
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	protected SoundEvent getFallSound(int heightIn)
	{
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	public boolean attackEntityAsMob(Entity entityIn)
	{
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int knockback = 0;

		if (entityIn instanceof EntityLivingBase)
		{
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
			knockback += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean successfulAttack = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (successfulAttack)
		{
			handleSuccessfulAttack(entityIn, knockback);
		}

		return successfulAttack;
	}

	protected void handleSuccessfulAttack(Entity entityIn, int knockback)
	{
		if (knockback > 0 && entityIn instanceof EntityLivingBase)
		{
			((EntityLivingBase) entityIn).knockBack(this, (float) knockback * 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
			this.motionX *= 0.6D;
			this.motionZ *= 0.6D;
		}

		int j = EnchantmentHelper.getFireAspectModifier(this);

		if (j > 0)
		{
			entityIn.setFire(j * 4);
		}

		if (entityIn instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer) entityIn;
			ItemStack itemstack = this.getHeldItemMainhand();
			ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : null;

			if ( itemstack != null && itemstack1 != null && itemstack.getItem() instanceof ItemAxe && itemstack1.getItem() instanceof ItemShield )
			{
				float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

				if (this.rand.nextFloat() < f1)
				{
					entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
					this.world.setEntityState(entityplayer, (byte) 30);
				}
			}
		}

		this.applyEnchantments(this, entityIn);
	}

	@Override
	public float getBlockPathWeight(BlockPos pos)
	{
		return 0.0F;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}
	
	protected void callForHelp(EntityLivingBase attacker)
	{
		List<EntityToroMob> help = world.getEntitiesWithinAABB(EntityToroMob.class, new AxisAlignedBB(getPosition()).grow(32, 16, 32), new Predicate<EntityToroMob>()
		{
			public boolean apply(@Nullable EntityToroMob entity)
			{
				return true;
			}
		});

		for ( EntityToroMob entity : help )
		{
			if ( entity.getAttackTarget() == null )
			{
				entity.setAttackTarget(attacker);
			}
		}
	}

	@Override
	protected boolean canDropLoot()
	{
		return true;
	}
	
	@Override
	protected void updateLeashedState()
	{
	   this.clearLeashed(true, false);
	   return;
	}
		
	@Override
	public boolean canBeLeashedTo(EntityPlayer player)
	{
		return false;
	}
}