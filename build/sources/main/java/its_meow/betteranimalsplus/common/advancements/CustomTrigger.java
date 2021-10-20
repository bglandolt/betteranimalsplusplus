package its_meow.betteranimalsplus.common.advancements;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class CustomTrigger implements ICriterionTrigger<CustomTrigger.Instance> {
    private final ResourceLocation id;
    private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

    public CustomTrigger(String id) {
        this.id = new ResourceLocation(id);
    }

    public CustomTrigger(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<CustomTrigger.Instance> listener) {
        CustomTrigger.Listeners myCustomTrigger$listeners = listeners.get(playerAdvancementsIn);

        if(myCustomTrigger$listeners == null) {
            myCustomTrigger$listeners = new CustomTrigger.Listeners(playerAdvancementsIn);
            listeners.put(playerAdvancementsIn, myCustomTrigger$listeners);
        }

        myCustomTrigger$listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
        CustomTrigger.Listeners tameanimaltrigger$listeners = listeners.get(playerAdvancementsIn);

        if(tameanimaltrigger$listeners != null) {
            tameanimaltrigger$listeners.remove(listener);

            if(tameanimaltrigger$listeners.isEmpty()) {
                listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        listeners.remove(playerAdvancementsIn);
    }

    @Override
    public CustomTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new CustomTrigger.Instance(getId());
    }

    public void trigger(EntityPlayerMP parPlayer) {
        CustomTrigger.Listeners tameanimaltrigger$listeners = listeners.get(parPlayer.getAdvancements());

        if(tameanimaltrigger$listeners != null) {
            tameanimaltrigger$listeners.trigger(parPlayer);
        }
    }

    public static class Instance extends AbstractCriterionInstance {

        public Instance(ResourceLocation parRL) {
            super(parRL);
        }

        public boolean test() {
            return true;
        }
    }

    static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners = Sets.newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn) {
            playerAdvancements = playerAdvancementsIn;
        }

        public boolean isEmpty() {
            return listeners.isEmpty();
        }

        public void add(ICriterionTrigger.Listener<Instance> listener) {
            listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<Instance> listener) {
            listeners.remove(listener);
        }

        public void trigger(EntityPlayerMP player) {
            ArrayList<Listener<Instance>> list = null;

            for(ICriterionTrigger.Listener<Instance> listener : listeners) {
                if(listener.getCriterionInstance().test()) {
                    if(list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }
            }

            if(list != null) {
                for(ICriterionTrigger.Listener<Instance> listener1 : list) {
                    listener1.grantCriterion(playerAdvancements);
                }
            }
        }
    }
}