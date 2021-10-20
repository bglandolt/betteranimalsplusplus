package its_meow.betteranimalsplus.common.entity;

import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCoyote extends EntityFeralWolf
{

    public EntityCoyote(World worldIn)
    {
        super(worldIn);
        this.setSize(0.8F, 0.9F);
        this.setTamed(false);
    }

//    @Override
//    protected void initEntityAI() {
//        this.aiSit = new EntityAISit(this);
//        this.tasks.addTask(1, new EntityAISwimming(this));
//        this.tasks.addTask(2, this.aiSit);
//        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
//        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
//        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
//        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
//        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
//        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
//        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
//        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
//        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityPlayer>(this, EntityPlayer.class, false, Predicates.alwaysTrue()));
//        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityAnimal>(this, EntityAnimal.class, false, (@Nullable Entity e) -> e instanceof EntitySheep || e instanceof EntityRabbit));
//        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityVillager>(this, EntityVillager.class, false, Predicates.alwaysTrue()));
//        this.targetTasks.addTask(4, new EntityAITargetNonTamed<AbstractIllager>(this, AbstractIllager.class, false, Predicates.alwaysTrue()));
//        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityChicken>(this, EntityChicken.class, false, Predicates.alwaysTrue()));
//        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<AbstractSkeleton>(this, AbstractSkeleton.class, false));
//    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
    }
    
    @Override
    public boolean grabTarget(EntityLivingBase entity)
    {
    	return false;
    }
    
    @Override
    protected double jumpModifier()
    {
    	return 0.2D;
    }
    
    @Override
    protected double attackReachModifier()
    {
    	return 5.5D;
    }
    
    @Override
    protected double forwardModifier()
    {
    	return 0.12D;
    }
    
    // Time can go over values of 24000, so divide and take the remainder. WRONG, only getWorldTotalTime does
    public boolean isDaytime()
    {
        return !(this.world.getWorldTime() >= 13000L && this.world.getWorldTime() <= 23000L);
    }

    @Override
    public void setAttackTarget(EntityLivingBase entitylivingbaseIn)
    {
        if (!this.isDaytime() || BetterAnimalsPlusConfig.coyotesHostileDaytime)
        {
            super.setAttackTarget(entitylivingbaseIn);
        } 
        else if (!this.isTamed())
        {
            super.setAttackTarget(null);
        }
        else
        {
            super.setAttackTarget(entitylivingbaseIn);
        }
        
        if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            super.setAttackTarget(null);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if ((!this.isDaytime() || BetterAnimalsPlusConfig.coyotesHostileDaytime) && !this.isTamed())
        {
            return SoundEvents.ENTITY_WOLF_GROWL;
        }
        else if (this.rand.nextInt(3) == 0)
        {
            return this.isTamed() && this.dataManager.get(DATA_HEALTH_ID).floatValue() < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT;
        }
        else if (this.getAttackTarget() != null)
        {
            return SoundEvents.ENTITY_WOLF_GROWL;
        }
        return null;
    }

    @Override
    public void doHeadDrop()
    {
        if (!world.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(12) == 0)
            {
                ItemStack stack = new ItemStack(HeadTypes.COYOTEHEAD.getItem(1));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed())
        {
            if (!itemstack.isEmpty())
            {
                if (itemstack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();

                    if (itemfood.isWolfsFavoriteMeat() && this.dataManager.get(DATA_HEALTH_ID).floatValue() < 20.0F)
                    {
                        if (!player.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }

                        this.heal(itemfood.getHealAmount(itemstack));
                        return true;
                    }
                }
            }

            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack) && (!(itemstack.getItem() instanceof ItemFood) || !((ItemFood) itemstack.getItem()).isWolfsFavoriteMeat()))
            {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase) null);
            }
        }
        else if(this.isTamingItem(itemstack.getItem()))
        {
            if ( BetterAnimalsPlusConfig.coyotesHostileDaytime )
            {
            	
            }
            else if (this.isDaytime())
            {

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                if (!this.world.isRemote)
                {
                   if (this.rand.nextInt(100) <= 14 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                   {
                        this.setTamedBy(player);
                        this.navigator.clearPath();
                        this.setAttackTarget((EntityLivingBase) null);
                        this.aiSit.setSitting(true);
                        this.setHealth(20.0F);
                        this.playTameEffect(true);
                        this.world.setEntityState(this, (byte) 7);
                    } 
                    else
                    {
                        this.playTameEffect(false);
                        this.world.setEntityState(this, (byte) 6);
                    }
                }
                return true;
            }
            else
            {
                return true;
            }
        }

        if (itemstack.getItem() == Items.SPAWN_EGG)
        {
            if (!this.world.isRemote) {
                Class<? extends Entity> oclass = EntityList.getClass(ItemMonsterPlacer.getNamedIdFrom(itemstack));

                if (oclass != null && this.getClass() == oclass)
                {
                    EntityAgeable entityageable = this.createChild(this);

                    if (entityageable != null) 
                    {
                        entityageable.setGrowingAge(-24000);
                        entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                        this.world.spawnEntity(entityageable);

                        if (itemstack.hasDisplayName())
                        {
                            entityageable.setCustomNameTag(itemstack.getDisplayName());
                        }

                        if (!player.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected String getContainerName()
    {
        return "coyote";
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner)
    {
        if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast) && (this.isTamed() || !this.isDaytime() || BetterAnimalsPlusConfig.coyotesHostileDaytime))
        {
            if (target instanceof EntityCoyote)
            {
                EntityCoyote entityferalwolf = (EntityCoyote) target;

                if (entityferalwolf.isTamed() && entityferalwolf.getOwner() == owner)
                {
                    return false;
                }
            }

            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer) owner).canAttackPlayer((EntityPlayer) target))
            {
                return false;
            }
            else
            {
                return !(target instanceof AbstractHorse) || !((AbstractHorse) target).isTame();
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    protected ResourceLocation getLootTable()
    {
        return null;
    }

}
