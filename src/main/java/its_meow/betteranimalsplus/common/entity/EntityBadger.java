package its_meow.betteranimalsplus.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBadgerAttack;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBoarAttack;
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

	public EntityBadger(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 0.7F);
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBadgerDigDirtThrow(this));
		this.tasks.addTask(2, new EntityBadger.AIMeleeAttack());
		this.tasks.addTask(3, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		
        this.targetTasks.addTask(1, new EntityAIBadgerAttack(this));

		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true, new Class[0])
		{
			public boolean shouldExecute()
		    {
				if ( isChild() )
				{
					return false;
				}
		        return super.shouldExecute();
		    }
		});
	}
	
	public boolean isHungry = this.rand.nextBoolean();

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.36D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5);
	}
	
    @Override
    protected float getWaterSlowDown()
    {
        return 0.7F;
    }

	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if (this.world.isRemote)
        {
            return false;
        }
		
		// AGGRO
    	if ( source.getTrueSource() instanceof EntityLivingBase )    		
    	{
    		if ( this.getAttackTarget() == null )
    		{
	    		if ( this.getDistance(source.getTrueSource()) > 10 )
	    		{
	    			this.cannotReachTimer = 200;
	    		}
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    		else if ( this.rand.nextInt(3) == 0 )
    		{
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    	}
    	
		if ( source == DamageSource.FALL )
		{
			if ( amount <= 3 )
			{
				return false;
			}
			return super.attackEntityFrom(source, amount/3.0F);
		}
		
		this.fleeTimer = 100 + rand.nextInt(80);

		return super.attackEntityFrom(source, amount);
    }
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		// if ( this.rand.nextInt() == 0 ) this.fleeTimer = 80 + rand.nextInt(80);

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
			if ( badger.abilityCooldown > 0 || badger.fleeTimer > 0 )
			{
				return false;
			}
			
			if ( badger.getAttackTarget() instanceof EntityPlayer )
			{
				World world = badger.world;
				BlockPos below = badger.getPosition().down();
				
				if ( world.isBlockLoaded(below) )
				{
					IBlockState state = world.getBlockState(below);
					double dist = Math.sqrt(badger.getPosition().distanceSq(badger.getAttackTarget().getPosition()));
					return dist < 12 && dist > 3 && onDirt(state);
				}
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
					badger.abilityTick = 40 + world.rand.nextInt(3)*20;
				}
			}
		}

		@Override
		public void updateTask()
		{
			tick--;
			EntityLivingBase t = badger.getAttackTarget();    
			
			if ( tick % 20 == 0 )
			{ // Throw dirt every second (15 ticks)
				EntityBadgerDirt proj = new EntityBadgerDirt(badger.world, badger, stateId);
				proj.setLocationAndAngles(badger.posX, badger.posY + 1, badger.posZ, 0, 0);
				double d0 = t.posY + t.getEyeHeight() - 1.100000023841858D;
				double d1 = t.posX - badger.posX;
				double d2 = d0 - proj.posY;
				double d3 = t.posZ - badger.posZ;
				float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
				proj.shoot(d1, d2 + f, d3, 0.6F, 4.8F);
				badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.8F, 1.0F / (badger.getRNG().nextFloat() * 0.4F + 0.8F));
				badger.world.spawnEntity(proj);
			}
			else if (tick % 5 == 0)
			{
				badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.6F, 1.0F / (badger.getRNG().nextFloat() * 0.4F + 0.8F));
			}
		}

		@Override
		public void resetTask()
		{
			badger.abilityTick = 0;
			badger.getNavigator().clearPath();
			badger.abilityCooldown = 60 + badger.world.rand.nextInt(7)*10;
		}

	}
	
	public int abilityTick = 0;
	
    @Override
    protected int[] getTypesFor(Set<BiomeDictionary.Type> types)
    {
        if(types.contains(Type.SAVANNA))
        {
            return new int[] {3};
        }
        else if(types.contains(Type.FOREST) && !types.contains(Type.CONIFEROUS))
        {
            return new int[] {2};
        } 
       	else if(types.contains(Type.CONIFEROUS) || types.contains(Type.SNOWY))
       	{
            return new int[] {1};
        }
//       	else if(types.contains(Type.CONIFEROUS) && types.contains(Type.SNOWY))
//        {
//            return new int[] {1};
//        }
       	else 
        {
            return new int[] {2, 3};
        }
    }

    @Override
    protected String getContainerName()
    {
        return "badger";
    }
    
    public int fleeTimer = 0;
    public int abilityCooldown = 0;
    public int cannotReachTimer = 0;
    
    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
    	if ( this.getAttackTarget() == null && entitylivingbaseIn != null )
    	{
    		this.fleeTimer = 0;
    		this.cannotReachTimer = 0;
    	}
        super.setAttackTarget(entitylivingbaseIn);
    }
    
    public class AIMeleeAttack extends PublicEntityAIAttack
    {
        public AIMeleeAttack()
        {
            super(EntityBadger.this, 1.0D);
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

                this.attacker.faceEntity(entity, 20.0F, 20.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
		        this.attacker.motionX /= 4.0D;
		        this.attacker.motionZ /= 4.0D;
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
                this.attacker.faceEntity(entity, 20.0F, 20.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 20.0F, 20.0F);
             	
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
        	if ( EntityBadger.this.fleeTimer > 0 )
        	{
        		return;
        	}
        	super.updateTask();
        }
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 3.5D + attackTarget.width;
        }
    }
    
    
    
    // LIVING UPDATE ***
    
    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	if ( this.world.isRemote ) return;

    	if ( this.getAttackTarget() != null )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 100 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 1 || this.getDistance(this.getAttackTarget()) > 5 ) )
        	{
        		this.fleeTimer = 150 + rand.nextInt(50);
        		this.getNavigator().clearPath();
        		this.cannotReachTimer = this.fleeTimer;
        	}
        	if ( this.fleeTimer > 0 )
    		{
				if ( this.getNavigator().noPath() )
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
					
	                if ( vec3d != null )
	                {
	                	this.setAttackTarget(null);
	                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
						AIHelper.faceMovingDirection(this);
	                }
	                else
	                {
	                	vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
						
		                if ( vec3d != null )
		                {
		                	this.setAttackTarget(null);
		                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
							AIHelper.faceMovingDirection(this);
		                }
		                else
		                {
		                	this.fleeTimer -= 50;
		                }
	                }
				}
				else
				{
					AIHelper.faceMovingDirection(this);
				}
    		}
    		else
    		{
    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    	}
    	else
    	{
    		this.cannotReachTimer = 0;
    	}
    	
    	    	
//    	if ( this.getAttackTarget() != null )
//    	{
//    		if ( this.fleeTimer > 0 )
//    		{
//				AIHelper.faceMovingDirection(this);
//    		}
//    		else
//    		{
//    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
//	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
//	    	}
//    	}
    	
	    if ( this.fleeTimer > 0 )
	    {
	    	this.fleeTimer--;
	    }
    	
        if ( this.abilityCooldown > 0 )
        {
        	this.abilityCooldown--;
        }
        
        if ( this.cannotReachTimer > 0 )
        {
        	this.cannotReachTimer--;
        	if ( this.cannotReachTimer == 1 && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > 10 )
        	{
        		this.setAttackTarget(null);
        	}
        }
        
        if ( !this.isHungry && this.world.getWorldTime() % 2000 == 0 && this.rand.nextBoolean() )
	    {
	    	this.isHungry = true;
	    }
         
    }
    

}