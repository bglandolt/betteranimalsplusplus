package its_meow.betteranimalsplus.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import its_meow.betteranimalsplus.common.entity.projectile.EntityBadgerDirt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityBadger extends EntityAnimalWithSelectiveTypes implements IMob {

	public EntityBadger(World worldIn) {
		super(worldIn);
		this.setSize(0.8F, 0.8F);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		if (!this.isChild() && this.getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL) {
			this.tasks.addTask(1, new EntityAIBadgerDigDirtThrow(this));
			this.tasks.addTask(2, new EntityBadger.AIMeleeAttack());
		}
		this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		if (!this.isChild() && this.getEntityWorld().getDifficulty() != EnumDifficulty.PEACEFUL) {
			this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true, new Class[0]));
			this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityAnimal>(this, EntityAnimal.class, 90, true, true, (@Nullable Entity in) -> in instanceof EntityChicken || in instanceof EntityPheasant || (in instanceof EntityAnimal && ((EntityAnimal) in).isChild() && !(in instanceof EntityBadger))));
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5);
	}

	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if ( source == DamageSource.FALL )
		{
			if ( amount <= 3 )
			{
				return false;
			}
			return super.attackEntityFrom(source, amount/3.0F);
		}
		return super.attackEntityFrom(source, amount);
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		// Vanilla attack code for mobs
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {
			if (i > 0 && entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0) {
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
					float f1 = 0.25F + EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (this.rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
						this.world.setEntityState(entityplayer, (byte) 30);
					}
				}
			}

			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}

	@Override
	public int getVariantMax() {
		return 3;
	}

	@Override
	protected IVariantTypes getBaseChild()
	{
		return new EntityBadger(this.world);
	}

	public static class EntityAIBadgerDigDirtThrow extends EntityAIBase
	{

		private final EntityBadger badger;
		public int tick = 0;
		private int stateId = -1;

		public EntityAIBadgerDigDirtThrow(EntityBadger badger)
		{
			this.badger = badger;
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute()
		{
			if ( badger.abilityCooldown > 0 || badger.fleeing )
			{
				return false;
			}
			World world = badger.world;
			BlockPos below = badger.getPosition().down();
			
			if(world.isBlockLoaded(below))
			{
				IBlockState state = world.getBlockState(below);
				double dist = badger.getAttackTarget() == null ? 0 : Math.sqrt(badger.getPosition().distanceSq(badger.getAttackTarget().getPosition()));
				return badger.getRevengeTarget() != null && badger.getAttackTarget() == badger.getRevengeTarget() && dist < 10 && dist > 2 && onDirt(state);
			}
			return false;
		}
		
		protected boolean onDirt(IBlockState state)
		{
			return (state.getBlock() instanceof BlockDirt || state.getBlock() instanceof BlockSand || state.getBlock() instanceof BlockGrass || state.getBlock() instanceof BlockSnow || state.getBlock() == Blocks.GRAVEL || state.getBlock() == Blocks.MYCELIUM);
		}

		@Override
		public boolean shouldContinueExecuting()
		{
			boolean onDiggable = false;
			World world = badger.world;
			BlockPos below = badger.getPosition().down();
			if(world.isBlockLoaded(below)) {
				IBlockState state = world.getBlockState(below);
				if ( onDirt(state) )
				{
					if(state.getBlock() == Blocks.GRASS)
					{
						state = Blocks.DIRT.getDefaultState();
					}
					stateId = Block.getStateId(state);
					onDiggable = true;
				}
			}
			double dist = badger.getAttackTarget() == null ? 0 : Math.sqrt(badger.getPosition().distanceSq(badger.getAttackTarget().getPosition()));
			return badger.getRevengeTarget() != null && badger.getAttackTarget() == badger.getRevengeTarget() && tick > 0 && dist < 10 && dist > 2 && onDiggable;
		}

		@Override
		public void startExecuting()
		{
			World world = badger.world;
			BlockPos below = badger.getPosition().down();
			if(world.isBlockLoaded(below))
			{
				IBlockState state = world.getBlockState(below);
				if ( onDirt(state) )
				{
					if(state.getBlock() == Blocks.GRASS)
					{
						state = Blocks.DIRT.getDefaultState();
					}
					stateId = Block.getStateId(state);
					tick = 30 + world.rand.nextInt(60);
				}
			}
		}

		@Override
		public void updateTask()
		{
			tick--;
			EntityLivingBase t = badger.getAttackTarget();    
			
			if ( tick % 15 == 0 )
			{ // Throw dirt every second (20 ticks)
				EntityBadgerDirt proj = new EntityBadgerDirt(badger.world, badger, stateId);
				proj.setLocationAndAngles(badger.posX, badger.posY + 1, badger.posZ, 0, 0);
				double d0 = t.posY + t.getEyeHeight() - 1.100000023841858D;
				double d1 = t.posX - badger.posX;
				double d2 = d0 - proj.posY;
				double d3 = t.posZ - badger.posZ;
				float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
				proj.shoot(d1, d2 + f, d3, 0.6F, 4.8F);
				badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 1.0F, 1.0F / (badger.getRNG().nextFloat() * 0.4F + 0.8F));
				badger.world.spawnEntity(proj);
			}
			else if (tick % 5 == 0)
			{
				badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.8F, 1.0F / (badger.getRNG().nextFloat() * 0.4F + 0.8F));
			}
		}

		@Override
		public void resetTask()
		{
			tick = 0;
			badger.getNavigator().clearPath();
			badger.fleeing = true;
			badger.abilityCooldown = 10 + badger.world.rand.nextInt(60);
		}

	}
	
    @Override
    protected int[] getTypesFor(Set<BiomeDictionary.Type> types) {
        if(types.contains(Type.SAVANNA)) {
            return new int[] {3};
        } else if(types.contains(Type.FOREST) && !types.contains(Type.CONIFEROUS)) {
            return new int[] {2};
        } else if(types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) {
            return new int[] {1};
        } else if(types.contains(Type.CONIFEROUS) && types.contains(Type.SNOWY)) {
            return new int[] {1};
        } else {
            return new int[] {1, 2, 3};
        }
    }

    @Override
    protected String getContainerName()
    {
        return "badger";
    }
    
    public boolean fleeing = true;
    public int abilityCooldown = 0;
    
    public class AIMeleeAttack extends PublicEntityAIAttack
    {
        public AIMeleeAttack()
        {
            super(EntityBadger.this, 1.3D, true);
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist) // aaa
        {
        	double attackRange = this.getAttackReachSqr(entity);
        	         	
 	        if ( dist <= attackRange && this.attackTick == 0 )
 	        {
	    		this.attackTick = 13 + rand.nextInt(19);
        		
		        this.attacker.attackEntityAsMob(entity);
		        this.attacker.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 0.7F, 0.35F);
		        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);

                this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
//		        this.attacker.motionX = 0.0D;
//		        this.attacker.motionZ = 0.0D;
 		    }
 	    	else if ( this.attackTick <= 0 )
 	        {
 	    		this.attackTick = 13;
 	        }
 	    	else if ( dist <= 9.0 && this.attackTick == 6 )
         	{
                //this.attacker.world.setEntityState(this.attacker, (byte) 28);

             	double d0 = entity.posX - this.attacker.posX;
                double d1 = entity.posZ - this.attacker.posZ;
                double f = 1+MathHelper.sqrt(d0 * d0 + d1 * d1);

                this.attacker.motionX += d0 / f * 0.3D + this.attacker.motionX * 0.16D;
                this.attacker.motionZ += d1 / f * 0.3D + this.attacker.motionZ * 0.16D;
                this.attacker.faceEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(this.attacker.getAttackTarget(), 20.0F, 20.0F);
             	
                if ( entity.posY - this.attacker.posY <= -1 )
                {
                	this.attacker.motionY -= 0.3D;
                }
                else
                {
                	this.attacker.motionY += 0.3D;
                }
         	}
        }
        
        @Override
        public void updateTask() // ttt
        {
        	if ( EntityBadger.this.abilityCooldown > 0 )
        	{
        		return;
        	}
        	
            EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
            double d0 = 1+this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            
            if ( d0 < 11.5 )
            {
            	EntityBadger.this.fleeing = false;
            }
            
            if ( EntityBadger.this.fleeing )
        	{
        		return;
        	}
            
            --this.delayCounter;

            if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
            {
                this.targetX = entitylivingbase.posX;
                this.targetY = entitylivingbase.getEntityBoundingBox().minY;
                this.targetZ = entitylivingbase.posZ;
                this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

                if (this.canPenalize)
                {
                    this.delayCounter += failedPathFindingPenalty;
                    if (this.attacker.getNavigator().getPath() != null)
                    {
                        net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                        if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    }
                    else
                    {
                        failedPathFindingPenalty += 10;
                    }
                }

                if (d0 > 1024.0D)
                {
                    this.delayCounter += 10;
                }
                else if (d0 > 256.0D)
                {
                    this.delayCounter += 5;
                }

                if ( d0 >= 25 )
                {
	                if ( !this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget) )
	                {
	                    this.delayCounter += 15;
	                }
                }
                else if ( d0 <= 9 )
                {
                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, 0.0D);
                	Vec3d velocityVector = new Vec3d(this.attacker.posX - entitylivingbase.posX, 0, this.attacker.posZ - entitylivingbase.posZ);
					double push = (2.0D+d0)*2.0D;
					this.attacker.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
                	this.attacker.velocityChanged = true;
                }
                else
                {
                	this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget/(17.0D-16.0D));
                }
            }

            this.attackTick = Math.max(this.attackTick - 1, 0);
            this.checkAndPerformAttack(entitylivingbase, d0);
        }
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 3.5D + attackTarget.width;
        }
    }
    
    
    private void faceAwayEntity(Entity entityIn)
    {
        double d0 = this.posX - entityIn.posX;
        double d2 = this.posZ - entityIn.posZ;
        double d1;

        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            d1 = entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() - (this.posY + (double)this.getEyeHeight());
        }
        else
        {
            d1 = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0D - (this.posY + (double)this.getEyeHeight());
        }

        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        this.rotationPitch = f1;
        this.rotationYaw = f;
    }
    
    @Override
    public void onLivingUpdate()
    {
    	if ( this.getAttackTarget() != null )
    	{
    		if ( this.fleeing )
    		{
    			// face away
    			//this.getNavigator().clearPath();
				if ( this.getNavigator().noPath() )
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
					
	                if ( vec3d != null )
	                {
	                	//this.setAttackTarget(null);
	                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.9D);
	                }
				}
				else
				{
        			// this.faceAwayEntity(this.getAttackTarget());
				}
    		}
    		else
    		{
	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    	}
    	super.onLivingUpdate();
    	
    	if ( this.getAttackTarget() != null )
    	{
    		if ( this.fleeing )
    		{
    			
    		}
    		else
    		{
	    		this.faceEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
	    	}
    	}
    	
         if ( this.abilityCooldown > 0 )
         {
         	this.abilityCooldown--;
         }
         
    }
    

}