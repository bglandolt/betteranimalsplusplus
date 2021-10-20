package its_meow.betteranimalsplus.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
import its_meow.betteranimalsplus.init.ModItems;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.init.ModTriggers;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGoat extends EntityAnimalEatsGrassWithTypes {
	
    protected static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityGoat.class, DataSerializers.BOOLEAN);
    public EntityPlayer friend = null;
    public boolean hasBeenFed = false;
    private static final Set<Item> TEMPT_ITEMS = Sets.newHashSet(Items.WHEAT, Items.POTATO, Items.CARROT, Items.BEETROOT);

    public EntityGoat(World worldIn) {
        super(worldIn, 5);
        this.world = worldIn;
        this.setSize(0.8F, 0.7F);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote && (this.getAttackTarget() == null || this.getAttackTarget().isDead)) {
            this.setAttackingOnClient(false);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        Vec3d pos = this.getPositionVector();
        Vec3d targetPos = entityIn.getPositionVector();
        ((EntityLivingBase) entityIn).knockBack(entityIn, 0.8F, pos.x - targetPos.x, pos.z - targetPos.z);

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
    public void setAttackTarget(EntityLivingBase entitylivingbaseIn) {
        this.setAttackingOnClient(entitylivingbaseIn != null);
        super.setAttackTarget(entitylivingbaseIn);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }

    public boolean isAttackingFromServer() {
        return this.dataManager.get(ATTACKING).booleanValue();
    }

    public void setAttackingOnClient(boolean in) {
        this.dataManager.set(ATTACKING, Boolean.valueOf(in));
    }

    public float getHeadPitch() {
        return this.isAttackingFromServer() ? 0.15F : -0.698F;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.8D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.7D, true));
        this.tasks.addTask(3, new EntityAITempt(this, 0.6D, false, TEMPT_ITEMS));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.6D));
        // Eats grass at priority 5
        this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
        this.targetTasks.addTask(1, new GoatAIAttackForFriend(this));
        this.targetTasks.addTask(1, new AIHurtByTarget());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.8D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.world.setEntityState(this, (byte) 10);
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPT_ITEMS.contains(stack.getItem());
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if(stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            stack.shrink(1);
            
            Item milk = BetterAnimalsPlusConfig.goatVanillaMilk ? Items.MILK_BUCKET : ModItems.GOAT_MILK;

            if(stack.isEmpty()) {
                player.setHeldItem(hand, new ItemStack(milk));
            } else if(!player.inventory.addItemStackToInventory(new ItemStack(milk))) {
                player.dropItem(new ItemStack(milk), false);
            }

            return true;
        } else if(this.isBreedingItem(stack) && !this.isChild()) {
            this.hasBeenFed = true;
            this.friend = player;
        }
        return super.processInteract(player, hand);
    }

    @Override
    public float getEyeHeight() {
        return this.isChild() ? this.height : 0.5F;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTables.goat;
    }

    public static class GoatAIAttackForFriend extends EntityAIBase {
        EntityGoat goat = null;

        public GoatAIAttackForFriend(EntityGoat entity) {
            this.goat = entity;
        }

        @Override
        public boolean shouldExecute() {
            return this.goat.hasBeenFed && this.goat.friend != null && this.goat.friend.getAttackingEntity() != null;
        }

        @Override
        public void startExecuting() {
            this.goat.setAttackTarget(this.goat.friend.getAttackingEntity());
            if(this.goat.friend instanceof EntityPlayerMP) {
                ModTriggers.GOAT_FIGHT_FRIEND.trigger((EntityPlayerMP) this.goat.friend);
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return false;
        }

    }

    class AIHurtByTarget extends EntityAIHurtByTarget {
        public AIHurtByTarget() {
            super(EntityGoat.this, false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            super.startExecuting();

            if (EntityGoat.this.isChild()) {
                this.alertOthers();
                this.resetTask();
            }
        }

        @Override
        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) {
            if (creatureIn instanceof EntityGoat && !creatureIn.isChild()) {
                super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);
            }
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("AttackSync", this.isAttackingFromServer());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAttackingOnClient(compound.getBoolean("AttackSync"));
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setAttackingOnClient(false);
        return livingdata;
    }

    @Override
    public int getVariantMax() {
        return 7;
    }

    @Override
    protected IVariantTypes getBaseChild() {
        return new EntityGoat(this.world);
    }

    @Override
    protected String getContainerName() {
        return "goat";
    }

}
