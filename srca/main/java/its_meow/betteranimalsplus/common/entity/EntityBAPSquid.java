package its_meow.betteranimalsplus.common.entity;

import java.util.Random;

import com.google.common.base.Predicates;

import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.init.ModTriggers;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class EntityBAPSquid extends EntityBAPCephalopod 
{

    public EntityBAPSquid(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	private float lastAttack = 0;
    private float lastTickHealth = 0;
    private float lastGrab = 0;
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (this.world.isRemote)
        {
            return false;
        }
    	
        return super.attackEntityFrom(source, amount);
    }
    
    
//
//    public EntityBAPSquid(EntityType<? extends WaterMobEntity> type, World world) {
//        super(type, world);
//    }
//
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new MoveTowardsTargetGoal(this, 1D, 50F));
//        this.goalSelector.addGoal(1, new MoveRandomGoal(this));
//        this.targetSelector.addGoal(0, new HurtByTargetGoal(this) {
//            @Override
//            public boolean shouldExecute() {
//                return EntityBAPSquid.this.world.getDifficulty() != Difficulty.PEACEFUL && super.shouldExecute();
//            }
//        });
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, WaterMobEntity.class, 20, true, true, Predicates.alwaysTrue()));
//    }
//
//    @Override
//    protected void registerAttributes() {
//        super.registerAttributes();
//        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
//        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1D);
//        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
//        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
//    }
//
//    @Override
//    public void setAttackTarget(LivingEntity entitylivingbaseIn) {
//        if(!this.isPeaceful()) {
//            if(entitylivingbaseIn instanceof ServerPlayerEntity) {
//                ModTriggers.SQUID_TARGETED.trigger((ServerPlayerEntity) entitylivingbaseIn);
//            }
//            super.setAttackTarget(entitylivingbaseIn);
//        }
//    }
//
//    @Override
//    public void livingTick() {
//        super.livingTick();
//        if(this.getAttackTarget() != null && !this.getAttackTarget().isAlive()) {
//            this.setAttackTarget(null);
//        }
//        if(!this.world.isRemote && this.getAttackTarget() != null && this.getAttackTarget().isAlive() && this.isAlive() && !this.isPeaceful()) {
//            boolean isBoat = this.getAttackTarget() instanceof PlayerEntity && this.getAttackTarget().getRidingEntity() != null && this.getAttackTarget().getRidingEntity() instanceof BoatEntity;
//            float grabDelay = isBoat ? 20F : 60F;
//            if(this.getPassengers().contains(this.getAttackTarget())) {
//                float time = 30F;
//                time *= (Math.random() + 1F);
//                if(this.lastAttack + time < this.ticksExisted) {
//                    this.attackEntityAsMob(this.getAttackTarget());
//                }
//            } else if(lastGrab + grabDelay < this.ticksExisted && this.getDistanceSq(this.getAttackTarget()) < 5) {
//                if(isBoat) {
//                    BoatEntity boat = (BoatEntity) this.getAttackTarget().getRidingEntity();
//                    boat.attackEntityFrom(DamageSource.causeMobDamage(this), 5F);
//                } else if(!this.getAttackTarget().isInvulnerable() && this.getAttackTarget().getWidth() < 2.5 && this.getAttackTarget().getHeight() < 2.5) {
//                    if(this.getAttackTarget() instanceof MobEntity) {
//                        MobEntity el = (MobEntity) this.getAttackTarget();
//                        el.setAttackTarget(null);
//                        el.setRevengeTarget(null);
//                        el.getNavigator().clearPath();
//                        el.setNoAI(true);
//                    }
//                    this.getAttackTarget().startRiding(this, false);
//                } else if(!this.getAttackTarget().isInvulnerable()) {
//                    this.attackEntityAsMob(this.getAttackTarget());
//                }
//                lastGrab = this.ticksExisted;
//            }
//            if(lastTickHealth - 4F > this.getHealth()) {
//                this.getAttackTarget().dismountEntity(this);
//                if(this.getAttackTarget() instanceof MobEntity) {
//                    MobEntity el = (MobEntity) this.getAttackTarget();
//                    el.setNoAI(false);
//                }
//            }
//        }
//        this.lastTickHealth = this.getHealth();
//    }
//
//    @Override
//    protected ResourceLocation getLootTable() {
//        return ModLootTables.BIG_SQUID;
//    }
//
//    public static <T extends EntityBAPSquid> boolean placement(EntityType<T> type, IWorld world, SpawnReason reason, BlockPos pos, Random rng) {
//        return pos.getY() < (world.getSeaLevel() - 31) && world.getBlockState(pos).getBlock() == Blocks.WATER && world.getEntitiesWithinAABB(EntityBAPSquid.class, new AxisAlignedBB(pos).grow(100D)).size() == 0;
//    }
}