package its_meow.betteranimalsplus.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBadgerAttack;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBoarAttack;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
import its_meow.betteranimalsplus.common.entity.projectile.EntityBadgerDirt;
import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBadger extends EntityAnimalWithSelectiveTypes implements IMob
{

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
		this.tasks.addTask(3, new EntityAIWander(this, 0.6D));
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(BetterAnimalsPlusConfig.badgerHealth);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(BetterAnimalsPlusConfig.badgerSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(BetterAnimalsPlusConfig.badgerAttackDamage);
	}
	
    @Override
    protected float getWaterSlowDown()
    {
        return 0.8F;
    }

	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if (this.world.isRemote)
        {
            return false;
        }
		
		if ( this.motionY >= 0.3D )
		{
			this.motionY = 0.3D;
			this.velocityChanged = true;
		}
		
    	if ( source.getTrueSource() instanceof EntityLivingBase )    		
    	{
    		if ( this.getDistance(source.getTrueSource()) > 12 || this.rand.nextInt(4) == 0 )
    		{
    			this.cannotReachTimer = 200;
    		}
    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		return super.attackEntityFrom(source, amount);
    	}
    	
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
	public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }
	
	public float abilityTick = 0.0F;
    public float upTimer = 0.0F;
	
	public static float getUpTimer( EntityBadger badger )
    {
    	return badger.upTimer;
    }
    
    public static float getAbilityTick( EntityBadger badger )
    {
    	return badger.abilityTick;
    }
    
    public static void doAnimationTick( EntityBadger badger )
    {
    	if ( badger.onGround || badger.isInWater() )
		{
			if ( badger.upTimer > 0 )
			{
    			badger.upTimer -= 1.0F;
    			
	    		if ( badger.upTimer <= 0 )
	    		{
	    			badger.upTimer = 0.0F;
	    		}
			}
		}
		else
	    {
			badger.upTimer += 0.5F;
			
			if ( badger.upTimer >= 25 )
			{
				badger.upTimer = 25;
			}
	    }
    }

    @SideOnly(Side.CLIENT)
   	public void handleStatusUpdate(byte id)
    {
    	if ( id == 23 )
    	{
    		this.abilityTick = 0;
    	}
    	if (id == 25)
   		{
   			this.abilityTick = 60;
   		}
    	if (id == 27)
   		{
   			this.abilityTick = 80;
   		}
    	if (id == 28)
   		{
   			this.abilityTick = 100;
   		}
   		else
   		{
   			super.handleStatusUpdate(id);
   		}
   	}
    
	@Override
	public int getVariantMax()
	{
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
				BlockPos below = badger.getPosition().down();
				Block block = badger.world.getBlockState(below).getBlock();

				if ( badger.world.isBlockLoaded(below) )
				{
					double dist = Math.sqrt(badger.getPosition().distanceSq(badger.getAttackTarget().getPosition()));
					if ( dist < 9 && onDirt(block) )
					{
						badger.abilityCooldown = 150 + badger.world.rand.nextInt(7)*20;
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			
			return false;
		}
		
		protected boolean onDirt(Block b)
		{
			return (b instanceof BlockDirt || b instanceof BlockSand || b instanceof BlockGrass || b instanceof BlockSnow || b == Blocks.GRAVEL || b == Blocks.MYCELIUM);
		}

		@Override
		public boolean shouldContinueExecuting()
		{
			if ( badger.getAttackTarget() == null )
			{
		    	badger.world.setEntityState(badger, (byte) 23);
				badger.abilityTick = 0;
				return false;
			}
			
			BlockPos below = badger.getPosition().down();
			Block block = badger.world.getBlockState(below).getBlock();
			if ( badger.world.isBlockLoaded(below) )
			{
				if ( onDirt(block) )
				{
					stateId = Block.getStateId(block.getDefaultState());
				}
			}
			
			double dist = Math.sqrt(badger.getPosition().distanceSq(badger.getAttackTarget().getPosition()));
			
			if ( badger.abilityTick > 0 && dist < 16 )
			{
				return true;
			}
			else
			{
				this.resetTask();
				return false;
			}
		}

		@Override
		public void startExecuting()
		{
			BlockPos below = badger.getPosition().down();
			Block block = badger.world.getBlockState(below).getBlock();
			if ( badger.world.isBlockLoaded(below) )
			{
				if ( onDirt(block) )
				{
					stateId = Block.getStateId(block.getDefaultState());
				}
			}
			
			if ( badger.world.rand.nextFloat() > 0.67F )
			{
		    	badger.world.setEntityState(badger, (byte) 25);
				badger.abilityTick = 30;
			}
			if ( badger.world.rand.nextBoolean() )
			{
		    	badger.world.setEntityState(badger, (byte) 27);
				badger.abilityTick = 40;
			}
			else
			{
		    	badger.world.setEntityState(badger, (byte) 28);
				badger.abilityTick = 50;
			}

		}

		@Override
		public void updateTask()
		{			
			if ( badger.abilityTick % 5 == 0 )
			{
				if ( badger.abilityTick % 10 == 0 )
				{
					EntityBadgerDirt proj = new EntityBadgerDirt(badger.world, badger, stateId);
					proj.setLocationAndAngles(badger.posX, badger.posY + 1, badger.posZ, 0, 0);
					double d0 = badger.getAttackTarget().posY + badger.getAttackTarget().getEyeHeight() - 1.100000023841858D;
					double d1 = badger.getAttackTarget().posX - badger.posX;
					double d2 = d0 - proj.posY;
					double d3 = badger.getAttackTarget().posZ - badger.posZ;
					float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
					proj.shoot(d1, d2 + f, d3, 0.6F, 4.8F);
					badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.75F, 0.8F + badger.getRNG().nextFloat() * 0.4F );
					badger.world.spawnEntity(proj);
				}
				badger.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.5F, 0.8F + badger.getRNG().nextFloat() * 0.4F );
			}
		}

		@Override
		public void resetTask()
		{
			badger.world.setEntityState(badger, (byte) 23);
			badger.abilityTick = 0;
			badger.getNavigator().clearPath();
			if ( badger.world.rand.nextBoolean() )
			{
				badger.fleeTimer = 150 + badger.world.rand.nextInt(50);
			}
		}

	}
		
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
    		this.abilityTick = 0;
    	}
    	
    	if ( entitylivingbaseIn instanceof EntityBadger )
    	{
    		super.setAttackTarget(null);
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
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist) // XXX
        {
        	double attackRange = this.getAttackReachSqr(entity);
        	         	
 	        if ( dist <= attackRange && this.attackTick == 0 )
 	        {
	    		this.attackTick = 16 + rand.nextInt(16);
        		
		        this.attacker.attackEntityAsMob(entity);
		        this.attacker.playSound(SoundEvents.ENTITY_BAT_AMBIENT, 0.7F, 0.35F);
		        this.attacker.playSound(SoundEvents.ENTITY_GENERIC_BIG_FALL, 0.7F, 0.7F);

                this.attacker.faceEntity(entity, 30.0F, 30.0F);
	    		this.attacker.getLookHelper().setLookPositionWithEntity(entity, 30.0F, 30.0F);
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

                this.attacker.motionX += d0 / f * 0.32D + this.attacker.motionX * 0.16D;
                this.attacker.motionZ += d1 / f * 0.32D + this.attacker.motionZ * 0.16D;
             	
                if ( entity.posY - this.attacker.posY <= - 1.5D )
                {
                	this.attacker.motionY -= 0.1D;
                }
                else if ( this.attacker.onGround )
                {
                	this.attacker.motionY += 0.3D;
                }
                this.attacker.velocityChanged = true;
         	}
        }
        
        @Override
        public void updateTask()
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
    	
        if ( this.abilityTick > 0 )
        {
        	this.abilityTick--;
        }
    	
        if ( this.world.isRemote )
		{
			return;
		}

    	if ( this.getAttackTarget() != null )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 100 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 1 || this.getDistance(this.getAttackTarget()) > 5 ) )
        	{
        		this.fleeTimer = 150 + rand.nextInt(50);
        		this.getNavigator().clearPath();
        		this.cannotReachTimer = this.fleeTimer;
        	}
        	
        	if ( this.abilityTick > 0 )
        	{
        		AIHelper.faceAwayEntity(this, this.getAttackTarget());
        		this.getNavigator().clearPath();
        	}
        	else if ( this.fleeTimer > 0 )
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
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		}
    	}
    	else
    	{
    		this.cannotReachTimer = 0;
			AIHelper.faceMovingDirection(this);
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
//	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
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