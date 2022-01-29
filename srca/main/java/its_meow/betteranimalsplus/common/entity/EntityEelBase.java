package its_meow.betteranimalsplus.common.entity;

import java.util.List;

import com.google.common.base.Predicate;

import its_meow.betteranimalsplus.common.entity.ai.EntityAIWanderWaterEntity;
import its_meow.betteranimalsplus.init.ModLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityEelBase extends EntityWaterCreatureWithTypes {
    
    private static final Predicate<EntityItem> ITEM_SELECTOR = (item) -> {
        return !item.cannotPickup() && !item.isDead && item.getItem().getItem() instanceof ItemFood;
    };
    private int collideWithItemTicks = 0;
    private EntityItem collidedItem = null;

    public EntityEelBase(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAIAttackMelee(this, 0.8D, false) {
            @Override
            public boolean shouldContinueExecuting() {
                if(shouldCheckTarget() && EntityEelBase.this.getAttackTarget() != null && !isHoldingFood(EntityEelBase.this.getAttackTarget())) {
                    EntityEelBase.this.setAttackTarget(null);
                    return false;
                }
                return super.shouldContinueExecuting();
            }
        });
        this.tasks.addTask(2, new EntityEelBase.MoveToFoodItemsGoal());
        //this.tasks.addTask(2, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAIWanderWaterEntity(this, 0.5D, 1));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 0, true, true, EntityEelBase::isHoldingFood));
    }

    protected boolean shouldCheckTarget() {
        return true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5D);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(0.4D), EntityEelBase.ITEM_SELECTOR);
        if(items.size() > 0 && (collidedItem == null || items.contains(collidedItem))) {
            if(collidedItem == null) {
                collidedItem = items.get(this.getRNG().nextInt(items.size()));
            }
            collideWithItemTicks++;
            if(collideWithItemTicks > 35) {
                if(this.getRNG().nextFloat() < 0.1F) {
                    this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    this.world.setEntityState(this, (byte) 45); // calls handleStatusUpdate((byte) 45);
                }
                if(collideWithItemTicks > 40 && this.getRNG().nextInt(20) == 0) {
                    collideWithItemTicks = 0;
                    EntityItem item = collidedItem;
                    ItemStack stack = item.getItem();
                    if(stack.getItem() instanceof ItemFood) {
                        this.heal(((ItemFood) stack.getItem()).getSaturationModifier(stack));
                        item.setDead();
                        collidedItem = null;
                        collideWithItemTicks = 0;
                    }
                }
            }
        } else {
            collideWithItemTicks = 0;
            collidedItem = null;
        }
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if(id == 45) {
            if(collidedItem != null) {
                ItemStack stack = collidedItem.getItem();
                if(!stack.isEmpty()) {
                    for(int i = 0; i < 8; ++i) {
                        Vec3d vec3d = (new Vec3d(((double) this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-this.rotationPitch * ((float) Math.PI / 180F)).rotateYaw(-this.rotationYaw * ((float) Math.PI / 180F));
                        if(stack.getHasSubtypes()) {
                            this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX + this.getLookVec().x / 2.0D, this.posY, this.posZ + this.getLookVec().z / 2.0D, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()), stack.getMetadata());
                        } else {
                            this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX + this.getLookVec().x / 2.0D, this.posY, this.posZ + this.getLookVec().z / 2.0D, vec3d.x, vec3d.y + 0.05D, vec3d.z, Item.getIdFromItem(stack.getItem()));
                        }
                    }
                }
            }
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
//    	if (this.world.isRemote)
//        {
//            return false;
//        }
    	
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (this.world.isRemote)
        {
            return false;
        }
    	
        return super.attackEntityFrom(source, amount);
    }
    
    protected static boolean isHoldingFood(EntityLivingBase entity) {
        return entity.getHeldItemMainhand().getItem() instanceof ItemFood || entity.getHeldItemOffhand().getItem() instanceof ItemFood;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return ModLootTables.EELY;
    }

    public class MoveToFoodItemsGoal extends EntityAIBase {
        public MoveToFoodItemsGoal() {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {
            if(EntityEelBase.this.getAttackTarget() == null && EntityEelBase.this.getRevengeTarget() == null) {
                if(EntityEelBase.this.getRNG().nextInt(2) != 0) {
                    return false;
                } else {
                    List<EntityItem> list = EntityEelBase.this.world.getEntitiesWithinAABB(EntityItem.class, EntityEelBase.this.getEntityBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityEelBase.ITEM_SELECTOR);
                    return !list.isEmpty();
                }
            } else {
                return false;
            }
        }

        @Override
        public void updateTask() {
            List<EntityItem> list = EntityEelBase.this.world.getEntitiesWithinAABB(EntityItem.class, EntityEelBase.this.getEntityBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityEelBase.ITEM_SELECTOR);
            if(!list.isEmpty()) {
                EntityEelBase.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double) 1.2F);
            }

        }

        @Override
        public void startExecuting() {
            List<EntityItem> list = EntityEelBase.this.world.getEntitiesWithinAABB(EntityItem.class, EntityEelBase.this.getEntityBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityEelBase.ITEM_SELECTOR);
            if(!list.isEmpty()) {
                EntityEelBase.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double) 1.2F);
            }
        }
    }

}
