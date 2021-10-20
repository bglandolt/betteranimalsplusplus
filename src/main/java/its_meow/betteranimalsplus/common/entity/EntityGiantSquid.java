package its_meow.betteranimalsplus.common.entity;

import com.google.common.base.Predicates;

import its_meow.betteranimalsplus.init.ModEntities;
import net.minecraft.world.World;

public class EntityGiantSquid extends EntityBAPSquid
{


//    @Override
//    protected void registerGoals() {
//        super.registerGoals();
//        this.targetSelector.addGoal(2, new PeacefulNearestAttackableTargetGoal<>(this, PlayerEntity.class, 100, true, true, Predicates.alwaysTrue()));
//    }

//    @Override
//    public EntityTypeContainer<?> getContainer() {
//        return ModEntities.SQUID_GIANT;
//    }

	public EntityGiantSquid(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected String getContainerName()
    {
        return "giantsquid";
    }

}