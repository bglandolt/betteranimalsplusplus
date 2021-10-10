package its_meow.betteranimalsplus.fixers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

public class BearDataFixer implements IFixableData {

    @Override
    public int getFixVersion() {
        return 3;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if(compound.hasKey("id")) {
            String id = compound.getString("id");
            if(id.equals("betteranimalsplus:blackbear")) {
                compound.setInteger("TypeNumber", 1);
            } else if(id.equals("betteranimalsplus:kermodebear")) {
                compound.setString("id", "betteranimalsplus:blackbear");
                compound.setInteger("TypeNumber", 2);
            }
        }
        return compound;
    }

}
