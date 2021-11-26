package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.ModItems;
import its_meow.betteranimalsplus.init.ModLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityPheasant extends EntityAnimalWithTypes {

    protected static final DataParameter<Integer> PECK_TIME = EntityDataManager.<Integer>createKey(EntityPheasant.class, DataSerializers.VARINT);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 0.3F;
    //public int timeUntilNextEgg;

    public EntityPheasant(World worldIn) {
        super(worldIn);
        this.setPeckTime(this.getNewPeck());
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.setSize(0.4F, this.isChild() ? 0.4F : 0.7F);
        //this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (this.world.isRemote)
        {
            return false;
        }
    	
        return super.attackEntityFrom(source, amount);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.PUMPKIN_SEEDS, true)
        {
        	@Override
        	protected boolean isTempting(ItemStack stack)
            {
                return stack.getItem() instanceof ItemSeeds;
            }
        });
        this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 9.0F, 1.0D, 1.4D)
		{
			@Override
        	public void startExecuting()
            {
				if ( rand.nextInt(3) == 0 )
				{
					if ( this.entity.onGround && !this.entity.world.isRemote )
					{
						if ( this.closestLivingEntity != null && this.entity.getDistance(this.closestLivingEntity) < 6 )
						{
							Vec3d velocityVector = new Vec3d(this.entity.posX-this.closestLivingEntity.posX, 0, this.entity.posZ-this.closestLivingEntity.posZ);
							double push = 2.0D+this.entity.getDistanceSq(this.closestLivingEntity);
							this.entity.addVelocity(velocityVector.x/push, 0.22D+rand.nextDouble()/8.0D, velocityVector.z/push);
							this.entity.velocityChanged = true;
						}
						else
						{
							this.entity.addVelocity(0.0D, 0.22D+rand.nextDouble()/8.0D, 0.0D);
							this.entity.velocityChanged = true;
						}
					}
				}
                super.startExecuting();
            }
	
		});
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    private int getNewPeck()
    {
        return this.rand.nextInt(600) + 30;
    }

    @Override
    public float getEyeHeight() {
        return this.height;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) (this.destPos + (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if(!this.onGround && this.wingRotDelta < 1.0F)
        {
            this.wingRotDelta = 0.3F;
        }

        this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

        if(!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;

        if ( this.getPeckTime() <= 61 && !this.onGround || (this.motionX*this.motionX+this.motionZ*this.motionZ) > 0.008D )
        {
        	this.setPeckTime(100);
        }

        if ( !this.world.isRemote )
        {
	        if ( this.getPeckTime() <= 60 && this.getPeckTime() % 4 == 0 && this.rand.nextBoolean() )
	        {
	            this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	        }
	        
	        if ( this.setPeckTime(this.getPeckTime() - 1) <= 0 )
	        {
	            this.setPeckTime(this.getNewPeck());
	        }
        }

//        if(!this.world.isRemote && !this.isChild() && --this.timeUntilNextEgg <= 0) {
//            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
//            this.dropItem(ModItems.PHEASANT_EGG, 1);
//            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
//        }
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() instanceof ItemSeeds;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
    	this.playSound(SoundEvents.ENTITY_CHICKEN_HURT, 0.3F, 1.25F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
    	this.playSound(SoundEvents.ENTITY_CHICKEN_DEATH, 0.3F, 1.25F);
        return null; // SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
    	//this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 0.8F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTables.pheasant;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(PECK_TIME, Integer.valueOf(0));
    }

    public int getPeckTime() {
        return this.dataManager.get(PECK_TIME).intValue();
    }

    public int setPeckTime(int time) {
        this.dataManager.set(PECK_TIME, Integer.valueOf(time));
        return time;
    }

    @Override
    public int getVariantMax() {
        return 2;
    }
    
    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
    	this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
    	this.dropItem(ModItems.PHEASANT_EGG, 1);
    	this.resetInLove();
    	return null;
//        if ( !(ageable instanceof IVariantTypes) )
//        {
//        	return null;
//        }
//        IVariantTypes child = getBaseChild();
//        
//        if ( child == null )
//        {
//        	return null;
//        }
//        
//        return (EntityAgeable) child.setType(this.getOffspringType(this, (IVariantTypes) ageable));
    }
    
    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        int i = this.rand.nextInt(3);

        if (lootingModifier > 0)
        {
            i += this.rand.nextInt(lootingModifier + 1);
        }

        this.dropItem(Items.FEATHER, i);
        
        if ( this.isBurning() ) 
        {
        	this.dropItem(ModItems.PHEASANT_COOKED, 1);
        }
        else
        {
        	this.dropItem(ModItems.PHEASANT_RAW, 1);
        }
        
        this.dropItem(ModItems.PHEASANT_EGG, lootingModifier>0?1:rand.nextInt(2));

        
        //super.dropFewItems(wasRecentlyHit, lootingModifier);
    }

    @Override
    protected IVariantTypes getBaseChild()
    {
    	return new EntityPheasant(this.world);
    }

    @Override
    protected String getContainerName() {
        return "pheasant";
    }

}
