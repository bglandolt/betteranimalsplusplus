package its_meow.betteranimalsplus.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.ModEntities;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityJellyfish extends EntitySquid implements IVariantTypes {
	
    protected static final DataParameter<Integer> TYPE_NUMBER = EntityDataManager.<Integer>createKey(EntityJellyfish.class, DataSerializers.VARINT);
    protected static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityJellyfish.class, DataSerializers.FLOAT);
    protected int attackCooldown = 0;

    public EntityJellyfish(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityJellyfish.AIMoveRandom(this));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entity) {
        super.onCollideWithPlayer(entity);
        if (!entity.capabilities.isCreativeMode && this.attackCooldown == 0) {
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
            entity.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 200, 0, false, false));
            entity.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("blindness"), 90, 2, false, false));
            this.attackCooldown = 80;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return null;
    }

    /* NBT AND TYPE CODE: */

    @Override
    protected void entityInit() {
        super.entityInit();
        this.registerTypeKey();
        this.dataManager.register(SIZE, Float.valueOf(1));
    }

    public float getSize() {
        return this.dataManager.get(SIZE).floatValue();
    }

    @Override
    public void setSize(float width, float height) {
        this.dataManager.set(SIZE, Float.valueOf(width));
        super.setSize(width, height);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        this.writeType(compound);
        compound.setFloat("Size", this.getSize());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.readType(compound);
        float size = compound.getFloat("Size");
        this.setSize(size, size);
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        if (!this.isChild()) {
            int i = this.rand.nextInt(6) + 1; // Values 1 to 6
            float rand = (this.rand.nextInt(30) + 1F) / 50F + 0.05F;

            if (livingdata instanceof JellyfishData) {
                i = ((JellyfishData) livingdata).typeData;
                rand = ((JellyfishData) livingdata).size;
            } else {
                livingdata = new JellyfishData(i, rand);
            }

            this.setType(i);
            this.setSize(rand, rand);
        }
        return livingdata;
    }

    public static class JellyfishData implements IEntityLivingData {
        public int typeData;
        public float size;

        public JellyfishData(int type, float size) {
            this.typeData = type;
            this.size = size;
        }
    }

    static class AIMoveRandom extends EntityAIBase {
        private final EntityJellyfish entity;

        public AIMoveRandom(EntityJellyfish entityIn) {
            this.entity = entityIn;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask() {
            int i = this.entity.getIdleTime();

            if (i > 100) {
                this.entity.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.entity.getRNG().nextInt(50) == 0 || !this.entity.inWater || !this.entity.hasMovementVector()) {
                float f = this.entity.getRNG().nextFloat() * ((float) Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.entity.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.entity.setMovementVector(f1 / 3, f2 / 3, f3 / 3);
            }
        }
    }

    @Override
    public DataParameter<Integer> getDataKey() {
        return TYPE_NUMBER;
    }

    @Override
    public int getVariantMax() {
        return 6;
    }

    @Override
    public boolean isChildI() {
        return this.isChild();
    }

    @Override
    public Random getRNGI() {
        return this.getRNG();
    }

    @Override
    public EntityDataManager getDataManagerI() {
        return this.getDataManager();
    }

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey("jellyfish") ? ModEntities.entityMap.get("jellyfish").despawn && !this.hasCustomName() : false;
    }

}
