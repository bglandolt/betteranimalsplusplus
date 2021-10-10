package its_meow.betteranimalsplus.init;

import java.util.HashMap;
import java.util.Map;

import its_meow.betteranimalsplus.util.ModSoundEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvents {


    public static final Map<ResourceLocation, SoundEvent> SOUNDS = new HashMap<ResourceLocation, SoundEvent>();

    public static final SoundEvent CRAB_RAVE = sound("record.crabrave", "crabrave");

    public static final SoundEvent GOOSE_DEATH = sound("entity.goose.death");
    public static final SoundEvent GOOSE_HURT = sound("entity.goose.hurt");
    public static final SoundEvent GOOSE_AMBIENT = sound("entity.goose.ambient");

    public static final SoundEvent WALRUS = sound("record.walrus");

    private static SoundEvent sound(String id) {
        ModSoundEvent event = new ModSoundEvent(id);
        SOUNDS.put(event.getRegistryName(), event);
        return event;
    }

    private static SoundEvent sound(String id, String reg) {
        ModSoundEvent event = new ModSoundEvent(id, reg);
        SOUNDS.put(event.getRegistryName(), event);
        return event;
    }

}
