package its_meow.betteranimalsplus.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDictSmelting {

    public static final void register() {
        // Smelting
        GameRegistry.addSmelting(new ItemStack(ModItems.VENISON_RAW), new ItemStack(ModItems.VENISON_COOKED), 0.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.PHEASANT_RAW), new ItemStack(ModItems.PHEASANT_COOKED), 0.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.CRAB_MEAT_RAW), new ItemStack(ModItems.CRAB_MEAT_COOKED), 0.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.TURKEY_RAW), new ItemStack(ModItems.TURKEY_COOKED), 0.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.TURKEY_LEG_RAW), new ItemStack(ModItems.TURKEY_LEG_COOKED), 0.0F);
        GameRegistry.addSmelting(new ItemStack(ModItems.GOLDEN_GOOSE_EGG), new ItemStack(Items.GOLD_INGOT), 0.3F);
        GameRegistry.addSmelting(new ItemStack(ModItems.EEL_MEAT_RAW), new ItemStack(ModItems.EEL_MEAT_COOKED), 0.3F);

        // Register oredict
        OreDictionary.registerOre("listAllmeatraw", ModItems.VENISON_RAW);
        OreDictionary.registerOre("listAllmeatcooked", ModItems.VENISON_COOKED);
        OreDictionary.registerOre("listAllmeatraw", ModItems.TURKEY_RAW);
        OreDictionary.registerOre("listAllmeatcooked", ModItems.TURKEY_COOKED);
        OreDictionary.registerOre("listAllmeatraw", ModItems.TURKEY_LEG_RAW);
        OreDictionary.registerOre("listAllmeatcooked", ModItems.TURKEY_LEG_COOKED);
        OreDictionary.registerOre("listAllmeatraw", ModItems.PHEASANT_RAW);
        OreDictionary.registerOre("listAllmeatcooked", ModItems.PHEASANT_COOKED);
        OreDictionary.registerOre("listAllvenisonraw", ModItems.VENISON_RAW);
        OreDictionary.registerOre("listAllvenisoncooked", ModItems.VENISON_COOKED);
        OreDictionary.registerOre("listAllfishraw", ModItems.CRAB_MEAT_RAW);
        OreDictionary.registerOre("listAllfishcooked", ModItems.CRAB_MEAT_COOKED);
        OreDictionary.registerOre("listAllMilk", ModItems.GOAT_MILK);
        OreDictionary.registerOre("listAllGoatMilk", ModItems.GOAT_MILK);
        OreDictionary.registerOre("foodGoatMilk", ModItems.GOAT_MILK);
        OreDictionary.registerOre("foodCheese", ModItems.GOAT_CHEESE);
        OreDictionary.registerOre("foodGoatCheese", ModItems.GOAT_CHEESE);
        OreDictionary.registerOre("foodVenisonraw", ModItems.VENISON_RAW);
        OreDictionary.registerOre("foodVenisoncooked", ModItems.VENISON_COOKED);
        OreDictionary.registerOre("peltWolf", ModItems.WOLF_PELT_BLACK);
        OreDictionary.registerOre("peltWolf", ModItems.WOLF_PELT_SNOWY);
        OreDictionary.registerOre("peltWolf", ModItems.WOLF_PELT_TIMBER);
        OreDictionary.registerOre("peltBear", ModItems.BEAR_SKIN_BROWN);
        OreDictionary.registerOre("peltBear", ModItems.BEAR_SKIN_BLACK);
        OreDictionary.registerOre("peltBear", ModItems.BEAR_SKIN_KERMODE);
        OreDictionary.registerOre("skinBear", ModItems.BEAR_SKIN_BROWN);
        OreDictionary.registerOre("skinBear", ModItems.BEAR_SKIN_BLACK);
        OreDictionary.registerOre("skinBear", ModItems.BEAR_SKIN_KERMODE);
        OreDictionary.registerOre("peltBearBrown", ModItems.BEAR_SKIN_BROWN);
        OreDictionary.registerOre("peltBearBlack", ModItems.BEAR_SKIN_BLACK);
        OreDictionary.registerOre("peltBearKermode", ModItems.BEAR_SKIN_KERMODE);
        OreDictionary.registerOre("skinBearBrown", ModItems.BEAR_SKIN_BROWN);
        OreDictionary.registerOre("skinBearBlack", ModItems.BEAR_SKIN_BLACK);
        OreDictionary.registerOre("skinBearKermode", ModItems.BEAR_SKIN_KERMODE);
        regEgg(ModItems.PHEASANT_EGG);
        regEgg(ModItems.TURKEY_EGG);
        regEgg(ModItems.GOOSE_EGG);

        for(ItemStack item : OreDictionary.getOres("egg")) {
            GameRegistry.addSmelting(item, new ItemStack(ModItems.FRIED_EGG), 0.3F);
        }
    }
    
    private static void regEgg(Item item) {
        OreDictionary.registerOre("egg", item);
        OreDictionary.registerOre("foodEgg", item);
        OreDictionary.registerOre("eggCookable", item);
        OreDictionary.registerOre("listAllEgg", item);
        OreDictionary.registerOre("listAllegg", item);
    }
}
