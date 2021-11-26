package its_meow.betteranimalsplus.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import its_meow.betteranimalsplus.common.entity.ai.EntityAIDeerFear;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIFollowHerd;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDeer extends EntityAnimalEatsGrassWithTypes {

    public EntityDeer(World worldIn) {
        super(worldIn, 5);
        this.setSize(0.7F, 0.9F);
        this.stepHeight = 1.55F;
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
   		if (id == 28)
   		{
   			this.underAttack = 300;
   		}
   		super.handleStatusUpdate(id);
   	}
    public int underAttack = 0;
    
    @Override
    public void setRevengeTarget(EntityLivingBase livingBase)
    {
        this.world.setEntityState(this, (byte) 28);
    	super.setRevengeTarget(livingBase);
    	//this.setAttackTarget(null);
    }

    @Override
    public void setAttackTarget(EntityLivingBase livingBase)
    {
    	this.setRevengeTarget(livingBase);
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
  		  EntityLivingBase player = (EntityLivingBase) source.getTrueSource();

  		  this.setRevengeTarget(player);
	  	
		  Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 7, new Vec3d(player.posX,player.posY,player.posZ));
	
	      if ( vec3d != null )
	      {
	    	  this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.7D);
	      }
	      
	      List<EntityDeer> guards = this.world.getEntitiesWithinAABB(EntityDeer.class, new AxisAlignedBB(this.getPosition()).grow(16, 12, 16), new Predicate<EntityDeer>()
	      {
	    	  public boolean apply(@Nullable EntityDeer entity)
	    	  {
					return true;
			  }
		  });
			
		  for (EntityDeer guard: guards)
		  {
			  guard.setRevengeTarget(player);
			  vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 7, new Vec3d(player.posX,player.posY,player.posZ));
				
		      if ( vec3d != null )
		      {
		    	  this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.7D);
		      }
		  }
	      
	  }
	  return super.attackEntityFrom(source, amount);
  }
    
    @Override
    public int getMaxSpawnedInChunk() {
        return 4;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIDeerFear(this, 0.6D, 0.7D));
        this.tasks.addTask(2, new EntityAIMate(this, 0.4D));
        this.tasks.addTask(3, new EntityAIFollowHerd(this, 0.4D, 4, 16, 80));
        //this.tasks.addTask(2, new EntityAIPanic(this, 0.65D));
//        Set<Item> temptItems = new HashSet<Item>();
//        temptItems.add(Items.APPLE);
//        temptItems.add(Items.GOLDEN_APPLE);
//        temptItems.add(Items.CARROT);
//        temptItems.add(Items.CARROT_ON_A_STICK);
//        temptItems.add(Items.GOLDEN_CARROT);
//        this.tasks.addTask(3, new EntityAITempt(this, 0.5D, false, temptItems));
        // Eat Grass at Priority 5
        this.tasks.addTask(5, new EntityAIWander(this, 0.4D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.75D);
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
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote)
        {

            if (this.onGround && --this.readyToJumpTimer <= 0)
            {
                if (getMyMovementSpeed(this) > 0.17F)
                {
                    float velX = (float) (0.6F * Math.cos(((this.rotationYaw - 90F) % 360F) / 60.0F));
                    float velZ = (float) (0.6F * Math.sin(((this.rotationYaw - 90F) % 360F) / 60.0F)); // 57.29578F
                    this.motionX -= velX;
                    this.motionZ -= velZ;
                    this.motionY = 0.36D + this.rand.nextDouble() / 50.0D;
                    this.readyToJumpTimer = this.rand.nextInt(15) + 10;
                    this.getNavigator().clearPath();
                }
            }
        }
    }
    
    private int readyToJumpTimer;

//    public float pitchRotationOffset()
//    {
//        if (!this.onGround && getMyMovementSpeed(this) > 0.08F) {
//            if (this.motionY > 0.5D) {
//                return 25F;
//            }
//            if (this.motionY < -0.5D) {
//                return -25F;
//            }
//            return (float) (this.motionY * 70D);
//        }
//        return 0F;
//    }
    
    private void faceAwayEntity(Entity entityIn)
    {
    	if ( this.getDistance(entityIn) <= 5 )
    	{

    	}
    	else
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
    }
    
    @Override
    public void onLivingUpdate()
    {
    	if ( this.getRevengeTarget() != null && !this.onGround )
    	{
    		this.faceAwayEntity(this.getRevengeTarget());
    	}
    	super.onLivingUpdate();
    	if ( this.getRevengeTarget() != null && !this.onGround )
    	{
    		this.faceAwayEntity(this.getRevengeTarget());
    	}
    }
    
    public static float getMyMovementSpeed(Entity entity)
    {
        return MathHelper.sqrt((entity.motionX * entity.motionX) + (entity.motionZ * entity.motionZ));
    }

}
