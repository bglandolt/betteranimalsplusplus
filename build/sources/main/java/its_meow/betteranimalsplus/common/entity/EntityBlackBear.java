package its_meow.betteranimalsplus.common.entity;

import java.util.Random;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.init.ModEntities;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBlackBear extends EntityBrownBear implements IVariantTypes
{
    protected static final DataParameter<Integer> TYPE_NUMBER = EntityDataManager.<Integer>createKey(EntityBlackBear.class, DataSerializers.VARINT);

    public EntityBlackBear(World world)
    {
        super(world);
        this.setSize(0.95F, 1.55F);
    }

    protected double attackReachModifier()
    {
    	return 9.0D;
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.6F, 1.0F + rand.nextFloat()/10.0F);
    }
    
    public boolean grabTarget(EntityLivingBase entity)
    {
    	return false;
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
    public int getVariantMax() {
        return 2;
    }

    @Override
    public DataParameter<Integer> getDataKey() {
        return TYPE_NUMBER;
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        int validTypes[] = {1, 2};
        return this.initData(super.onInitialSpawn(difficulty, livingdata), getBiasedRandomType(validTypes));
    }

    private int getBiasedRandomType(int[] validTypes)
    {
        if ( this.world.canSnowAt(this.getPosition(), false) )
        {
        	return 2;
        }
        else
        {
        	return 1;
        }
    	
    	// Double bias against kermode spawn
//        int r = validTypes[this.getRNG().nextInt(validTypes.length)];
//        if(validTypes.length > 1) { // No point if only a single possibility
//            if(r == 2) {
//                r = validTypes[this.getRNG().nextInt(validTypes.length)];
//            }
//            if(r == 2) {
//                r = validTypes[this.getRNG().nextInt(validTypes.length)];
//            }
//        }
//        return r;
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
    public void doDropHead() {
        if (!world.isRemote && !this.isChild()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(HeadTypes.BEARHEAD.getItem(this.getTypeNumber() + 1));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }
    
    @Override
    protected ResourceLocation getLootTable() {
        switch(this.getTypeNumber()) {
        case 1: return ModLootTables.BEAR_BLACK;
        case 2: return ModLootTables.BEAR_KERMODE;
        default: return ModLootTables.BEAR_BLACK;
        }
    }
    
    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey("blackbear") ? ModEntities.entityMap.get("blackbear").despawn && !this.hasCustomName() : false;
    }
    
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.36D); // XXX 35/h
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        //this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
    }

}
