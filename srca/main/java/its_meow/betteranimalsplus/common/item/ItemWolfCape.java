package its_meow.betteranimalsplus.common.item;

import its_meow.betteranimalsplus.client.model.ModelWolfCape;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWolfCape extends ItemCape {

    public ItemWolfCape(String variant, Item repairItem) {
        super("wolf_cape_", variant, repairItem);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected ModelBiped getBaseModelInstance() {
        return ModelWolfCape.INSTANCE;
    }

}