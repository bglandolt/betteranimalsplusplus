package its_meow.betteranimalsplus.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIDeerFear;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIFollowHerd;
import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDeer extends EntityAnimalEatsGrassWithTypes
{

    public EntityDeer(World worldIn)
    {
        super(worldIn, 5);
        this.setSize(0.7F, 1.9F);
        this.stepHeight = 2.05F;
    }

//    @Override
//    public boolean processInteract(EntityPlayer player, EnumHand hand) {
//        ItemStack stack = player.getHeldItem(hand);
//        boolean isEmpty = stack.isEmpty();
//        if (!isEmpty) {
//            if (stack.getItem() == Items.WHEAT || stack.getItem() == Items.CARROT) {
//                this.setInLove(player);
//            }
//        }
//
//        return super.processInteract(player, hand);
//    }
    
//    public static void init(int entityId)
//	{
//		EntityRegistry.registerModEntity(new ResourceLocation(ToroQuest.MODID, NAME), EntitySentry.class, NAME, entityId, ToroQuest.INSTANCE, 80, 1,
//				true, 0x8f3026, 0xe0d359);
//	}

    @SideOnly(Side.CLIENT)
   	public void handleStatusUpdate(byte id)
    {
    	if (id == 27)
   		{
   			this.underAttack = 0;
   		}
    	else if (id == 28)
   		{
   			this.underAttack = 300;
   			this.eatTimer = 0;
   		}
    	
   		super.handleStatusUpdate(id);
   	}
    
    public int underAttack = 0;
    
//    @Override
//    public void setAttackTarget(EntityLivingBase livingBase)
//    {
//        this.world.setEntityState(this, (byte) 28);
//    	super.setAttackTarget(livingBase);
//    	//this.setAttackTarget(null);
//    }

    @Override
    public void setAttackTarget(EntityLivingBase livingBase)
    {
        if ( livingBase == null )
        {
    		this.underAttack = 0;
        	this.world.setEntityState(this, (byte) 27);
        }
        else
        {
    		this.underAttack = 300;
        	this.world.setEntityState(this, (byte) 28);
        }
        
        super.setAttackTarget(livingBase);
    }
    
    @Override
    protected float getWaterSlowDown()
    {
        return 0.95F;
    }
    
  @Override
  public boolean attackEntityFrom(DamageSource source, float amount)
  {
	  if (this.world.isRemote)
      {
          return false;
      }
	  
	  if ( source == null ) return false;
	  
	  if ( source == DamageSource.FALL )
	  {
		  amount /= 2.0F;
		  if ( amount <= 2 )
		  {
			  return false;
		  }
		  return super.attackEntityFrom(source, amount);
	  }
	  
	  if ( source.isProjectile() )
	  {
		  amount *= 2.0F;
	  }
	  
	  if ( source.getTrueSource() instanceof EntityLivingBase )
	  {
		  this.setAttackTarget((EntityLivingBase)source.getTrueSource());

//  	  EntityLivingBase player = (EntityLivingBase) source.getTrueSource();
//
//  	  this.setAttackTarget(player);
//	  	
//		  Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 7, new Vec3d(player.posX,player.posY,player.posZ));
//	
//	      if ( vec3d != null )
//	      {
//	    	  this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
//	      }
//	      
//	      List<EntityDeer> guards = this.world.getEntitiesWithinAABB(EntityDeer.class, new AxisAlignedBB(this.getPosition()).grow(16, 12, 16), new Predicate<EntityDeer>()
//	      {
//	    	  public boolean apply(@Nullable EntityDeer entity)
//	    	  {
//					return true;
//			  }
//		  });
//			
//		  for (EntityDeer guard: guards)
//		  {
//			  guard.setAttackTarget(player);
//			  vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 7, new Vec3d(player.posX,player.posY,player.posZ));
//				
//		      if ( vec3d != null )
//		      {
//		    	  this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
//		      }
//		  }
	  }
	  return super.attackEntityFrom(source, amount);
  }
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 0.6D)
        {
            public boolean shouldExecute()
            {
            	if ( getAttackTarget() != null )
            	{
            		return false;
            	}
            	return super.shouldExecute();
            }
        });
        this.tasks.addTask(3, new EntityAIFollowHerd(this, 0.6D, 4, 16, 80));
        //this.tasks.addTask(2, new EntityAIPanic(this, 0.65D));
//        Set<Item> temptItems = new HashSet<Item>();
//        temptItems.add(Items.APPLE);
//        temptItems.add(Items.GOLDEN_APPLE);
//        temptItems.add(Items.CARROT);
//        temptItems.add(Items.CARROT_ON_A_STICK);
//        temptItems.add(Items.GOLDEN_CARROT);
//        this.tasks.addTask(3, new EntityAITempt(this, 0.5D, false, temptItems));
        // Eat Grass at Priority 5
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D, 1)
        {
            public boolean shouldExecute()
            {
            	if ( getAttackTarget() != null )
            	{
            		return false;
            	}
            	return super.shouldExecute();
            }
        });
        this.tasks.addTask(7, new EntityAILookIdle(this)
        {
        	@Override
        	public boolean shouldExecute()
            {
        		if ( getAttackTarget() != null )
        		{
        			return false;
        		}
                return getRNG().nextFloat() < 0.04F;
            }
        });
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(BetterAnimalsPlusConfig.deerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(BetterAnimalsPlusConfig.deerSpeed);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!world.isRemote && !this.isChild()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(HeadTypes.DEERHEAD.getItem(this.getTypeNumber()));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTables.deer;
    }

    @Override
    public int getVariantMax() {
        return 4;
    }

    @Override
    protected IVariantTypes getBaseChild() {
        return new EntityDeer(this.world);
    }

    @Override
    protected String getContainerName() {
        return "deer";
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        if (!this.isChild())
        {
            int variant = this.getBiasedRandomType();
            
            if(livingdata instanceof TypeData)
            {
                variant = ((TypeData) livingdata).typeData;
            }
            else
            {
                livingdata = new TypeData(variant);
            }
            this.setType(variant);
        }
        return livingdata;
    }

    private int getBiasedRandomType()
    {
    	if ( this.world.canSnowAt(this.getPosition(), false) )
        {
        	if ( rand.nextBoolean() )
        	{
        		return 3;
        	}
        	else
        	{
        		return 4;
        	}
        }
        else
        {
        	if ( rand.nextBoolean() )
        	{
        		return 1;
        	}
        	else
        	{
        		return 2;
        	}
        }
    	
//        int[] validTypes = new int[] { 1, 2, 3, 4 };
//        
//        int r = validTypes[this.getRNG().nextInt(validTypes.length)];
//        
//        if(r > 2)
//        {
//            r = validTypes[this.getRNG().nextInt(validTypes.length)];
//        }
//        
//        if(r > 2)
//        {
//            r = validTypes[this.getRNG().nextInt(validTypes.length)];
//        }
//        
//        return r;
    }
    
    private int readyToJumpTimer;
    public float upTimer = 0.0F;
    
    public static float getUpTimer( EntityDeer deer )
    {
    	return deer.upTimer;
    }
    
    public static float getUnderAttackTimer( EntityDeer deer )
    {
    	return deer.underAttack;
    }
    
    public static void doAnimationTick( EntityDeer deer )
    {
		if ( deer.onGround || deer.isInWater() )
		{
			if ( deer.upTimer > 0 )
			{
    			deer.upTimer -= 1.0F;
    			
	    		if ( deer.upTimer <= 0 )
	    		{
	    			deer.upTimer = 0.0F;
	    		}
			}
		}
		else
	    {
			deer.upTimer += 0.5F;
			
			if ( deer.upTimer >= 25 )
			{
				deer.upTimer = 25;
			}
	    }
    }
    
    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();    
    	
    	if ( this.world.isRemote ) return;
    	
    	float ms = getMyMovementSpeed(this);
    	
        if ( this.onGround && this.readyToJumpTimer-- <= 0 )
        {
            if ( ms > 0.17F )
            {
                float velX = 0.6F * MathHelper.cos(((this.rotationYaw - 90.0F) % 360.0F) / 60.0F);
                float velZ = 0.6F * MathHelper.sin(((this.rotationYaw - 90.0F) % 360.0F) / 60.0F);
                this.motionX -= velX;
                this.motionZ -= velZ;
                this.motionY = 0.34D;
                this.readyToJumpTimer = this.rand.nextInt(12) + 12;
                this.onGround = false;
                this.velocityChanged = true;
                this.getNavigator().clearPath();
            }
        }
    	
    	List<EntityArrow> arrows = this.world.getEntitiesWithinAABB(EntityArrow.class, new AxisAlignedBB(this.getPosition()).grow(8, 8, 8), new Predicate<EntityArrow>()
		{
			public boolean apply(@Nullable EntityArrow entity)
			{
				if ( entity.lastTickPosX == 0 )
				{
					return true;
				}
				return false;
			}
		});
    			
		for ( EntityArrow a : arrows )
		{
			if ( a.shootingEntity instanceof EntityLivingBase )
			{
				this.setAttackTarget((EntityLivingBase)a.shootingEntity);
			}
		}
		
		if ( this.underAttack > 0 )
		{
			this.underAttack--;
			
			if ( this.underAttack <= 0 )
			{
				this.setAttackTarget(null);
			}
		}
    	
		if ( this.ticksExisted % 20 == 0 )
    	{
	        this.getHostiles();

			if ( this.getAttackTarget() != null )
			{
			    EntityLivingBase closestLivingEntity = this.getAttackTarget();
			    double dist = this.getDistance(closestLivingEntity);
				double speed = 1.0D;
				
				if ( dist < 9 )
				{
					speed = 1.25;
	            	this.underAttack = 300;
				}
				
				Vec3d away = new Vec3d(this.posX+(this.posX-closestLivingEntity.posX)*2.0D, closestLivingEntity.posY, this.posZ+(this.posZ-closestLivingEntity.posZ)*2.0D);
				
	            Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this, 8, 4, away);
	
	            if ( vec3d != null && this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, speed) )
	            {
	            	// this.world.setBlockState(new BlockPos(vec3d.x, vec3d.y, vec3d.z), Blocks.GOLD_BLOCK.getDefaultState());
	            }
	            else
	            {
	            	this.getNavigator().clearPath();
	            }
			}
    	}
		
		if ( this.getAttackTarget() != null )
		{
			EntityLivingBase closestLivingEntity = this.getAttackTarget();
		    double dist = this.getDistance(closestLivingEntity);
	    	
	        if ( this.onGround && ( this.getNavigator().noPath() ) )
	        {
				Vec3d velocityVector = new Vec3d(this.posX-closestLivingEntity.posX, 0,this.posZ-closestLivingEntity.posZ);
	     		double push = 8.0D+dist*dist/2.0D;
	     		this.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
	         	this.velocityChanged = true;
	        }
	        else if ( dist > 4 && ms > 0.05F )
	        {
		        AIHelper.faceAwayEntity(this, this.getAttackTarget());
	        }
		}
    }
    
    public void getHostiles()
    {
    	List<EntityPlayer> list = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPosition()).grow(18, 6, 18), new Predicate<EntityPlayer>()
	    {
    		public boolean apply(@Nullable EntityPlayer entity)
	    	{
				return true;
			}
		});
        
        for ( EntityPlayer player : list )
        {
        	if ( !this.canEntityBeSeen(player) )
        	{
        		continue;
        	}
        	
        	if ( this.getDistance(player) < (player.isSneaking()?6.0D:12.0D) + 8.0D * ( abs(player.motionX) + abs(player.motionY) + abs(player.motionZ) ) )
        	{
        		this.setAttackTarget(player);
            	return;
        	}
        }
        
    	List<EntityAnimal> animals = this.world.getEntitiesWithinAABB(EntityAnimal.class, new AxisAlignedBB(this.getPosition()).grow(12, 4, 12), new Predicate<EntityAnimal>()
	    {
    		public boolean apply(@Nullable EntityAnimal entity)
	    	{
    			return true;
			}
		});
        
        for ( EntityAnimal animal : animals )
        {
        	if ( animal.height > 0.7 || animal.getAttackTarget() instanceof EntityDeer )
			{
        		if ( !this.canEntityBeSeen(animal) )
            	{
            		continue;
            	}
        		
            	this.setAttackTarget(animal);
            	return;
			}
        	
			if ( animal instanceof EntityDeer && animal.getAttackTarget() != null )
			{
            	this.setAttackTarget(animal);
				return;
			}
        }
    }
    
    public static double abs(double value)
    {
        return value >= 0.0D ? value : -value;
    }
    
    public static float getMyMovementSpeed(Entity entity)
    {
        return MathHelper.sqrt((entity.motionX * entity.motionX) + (entity.motionZ * entity.motionZ));
    }

}
