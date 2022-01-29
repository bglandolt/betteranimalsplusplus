package its_meow.betteranimalsplus.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.ModEntities;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class EntityAnimalWithTypes extends EntityAnimal implements IVariantTypes {

    protected static final DataParameter<Integer> TYPE_NUMBER = EntityDataManager.<Integer>createKey(EntityAnimalWithTypes.class, DataSerializers.VARINT);
    
    public EntityAnimalWithTypes(World worldIn)
    {
        super(worldIn);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.registerTypeKey();
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        this.writeType(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.readType(compound);
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        return this.initData(super.onInitialSpawn(difficulty, livingdata));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        if(!(ageable instanceof IVariantTypes)) return null;
        IVariantTypes child = getBaseChild();
        if(child == null) return null;
        return (EntityAgeable) child.setType(this.getOffspringType(this, (IVariantTypes) ageable));
    }
    
    protected abstract IVariantTypes getBaseChild();
    
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
    
    protected abstract String getContainerName();

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey(this.getContainerName()) ? ModEntities.entityMap.get(this.getContainerName()).despawn && !this.hasCustomName() : false;
    }
    
}