package its_meow.betteranimalsplus.common.entity;

import its_meow.betteranimalsplus.config.BetterAnimalsPlusConfig;
import its_meow.betteranimalsplus.init.ModEntities;
import its_meow.betteranimalsplus.util.EntityConfigurationSection;
import its_meow.betteranimalsplus.util.EntityContainer;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public abstract class EntityTameableBetterAnimalsPlus extends EntityTameable {

    public EntityTameableBetterAnimalsPlus(World worldIn) {
        super(worldIn);
    }

    public boolean isTamingItem(Item item) {
        EntityContainer container = ModEntities.entityMap.get(this.getContainerName());
        EntityConfigurationSection section = BetterAnimalsPlusConfig.sections.get(container);
        if(section != null) {
            String[] items = section.tameItems;
            String id = item.getRegistryName().toString();
            for(String itemsId : items) {
                if (itemsId.startsWith("ore:")) {
                    if (OreDictionary.getOres(itemsId.substring(4)).stream().anyMatch(itemStack -> itemStack.getItem() == item)) {
                        return true;
                    }
                }
                if(id.equals(itemsId)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected abstract String getContainerName();

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey(this.getContainerName()) ? ModEntities.entityMap.get(this.getContainerName()).despawn && !this.hasCustomName() : false;
    }
    
    @Override
    public void onLivingUpdate()
    {
    	if ( this.getAttackTarget() != null )
    	{
    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		this.prevRotationPitch = 0;
    		this.prevRotationYaw = 0;
    		this.newPosRotationIncrements = 0;
    	}
    	super.onLivingUpdate();
    	if ( this.getAttackTarget() != null )
    	{
    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		this.prevRotationPitch = 0;
    		this.prevRotationYaw = 0;
    		this.newPosRotationIncrements = 0;
    	}
    }

}
