package its_meow.betteranimalsplus.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.ModEntities;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class EntityWaterCreatureAirBreathingWithTypes extends EntityCreature implements IVariantTypes {

    protected static final DataParameter<Integer> TYPE_NUMBER = EntityDataManager.<Integer>createKey(EntityWaterCreatureAirBreathingWithTypes.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> MOISTNESS = EntityDataManager.createKey(EntityWaterCreatureAirBreathingWithTypes.class, DataSerializers.VARINT);

    public EntityWaterCreatureAirBreathingWithTypes(World world) {
        super(world);
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateSwimmer(this, worldIn);
    }

    public void travel(float strafe, float vertical, float forward) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(!this.inWater) {
            this.motionX *= 0.2F;
            this.motionZ *= 0.2F;
            if(!this.hasNoGravity()) {
                this.motionY -= 0.08D;
            }

            this.motionY *= 0.9800000190734863D;
        } else if(!world.isRemote) {
            if(!this.navigator.noPath()) {
                Vec3d target = this.navigator.getPath().getCurrentPos();
                this.motionX = (target.x - this.posX) * 0.05F;
                this.motionY = (target.y - this.posY) * 0.05F;
                this.motionZ = (target.z - this.posZ) * 0.05F;
            } else if(this.getMoveHelper().isUpdating()) {
                this.motionX = (this.getMoveHelper().getX() - this.posX) * 0.05F;
                this.motionZ = (this.getMoveHelper().getZ() - this.posZ) * 0.05F;
                this.motionY = (this.getMoveHelper().getY() - this.posY) * 0.05F;
            }
            this.motionY -= 0.01D;
        }
        this.motionX *= 0.8F;
        this.motionZ *= 0.8F;
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    public boolean getCanSpawnHere() {
        return true;
    }

    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    public int getTalkInterval() {
        return 120;
    }

    protected abstract String getContainerName();

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey(this.getContainerName()) ? ModEntities.entityMap.get(this.getContainerName()).despawn && !this.hasCustomName() : false;
    }

    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.world.rand.nextInt(3);
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();
        if(!this.isAIDisabled()) {
            if(this.world.isRainingAt(this.getPosition()) || this.isInWater()) {
                this.setMoistness(2400);
            } else {
                this.setMoistness(this.getMoistness() - 1);
                if(this.getMoistness() <= 0) {
                    this.attackEntityFrom(DamageSource.DROWN, 1.0F);
                }
            }
        }
    }

    public boolean isPushedByWater() {
        return false;
    }

    protected void entityInit() {
        super.entityInit();
        this.registerTypeKey();
        this.dataManager.register(MOISTNESS, 2400);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        this.writeType(compound);
        compound.setInteger("Moistness", this.getMoistness());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.readType(compound);
        this.setMoistness(compound.getInteger("Moistness"));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        return this.initData(super.onInitialSpawn(difficulty, livingdata));
    }

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
    public DataParameter<Integer> getDataKey() {
        return TYPE_NUMBER;
    }

    public int getMoistness() {
        return this.dataManager.get(MOISTNESS);
    }

    public void setMoistness(int p_211137_1_) {
        this.dataManager.set(MOISTNESS, p_211137_1_);
    }

}
