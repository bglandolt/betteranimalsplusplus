package its_meow.betteranimalsplus.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCrab extends EntityCrabLikeBase {

    protected static final DataParameter<Integer> CRAB_RAVE = EntityDataManager.<Integer>createKey(EntityCrab.class, DataSerializers.VARINT);

    public EntityCrab(World world)
    {
        super(world);
        this.setSize(0.5F, 0.3F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIAttackMelee(this, 0.9D, true)
        {
            @Override
            public boolean shouldExecute()
            {
                return super.shouldExecute() && this.attacker.getHealth() > this.attacker.getMaxHealth() / 2F;
            }

            @Override
            public boolean shouldContinueExecuting()
            {
                return super.shouldContinueExecuting() && this.attacker.getHealth() > this.attacker.getMaxHealth() / 2F;
            }
        });
        this.tasks.addTask(1, new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 20F, 0.8F, 1.0F));
        this.tasks.addTask(2, new EntityAIWander(this, 0.3D));
        this.targetTasks.addTask(1, new AIHurtByTarget());
    }
    
    @Override
    public boolean attackEntityAsMob(Entity p)
    {
    	if ( this.rand.nextBoolean() && p != null )
		{
			//if ( this.getNavigator().getPathToEntityLiving(p) == null || ( Math.abs(this.posY-p.posY) > 2 ) || this.getDistance(p) > 8 )
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, p.getPositionVector());
				
                if ( vec3d != null )
                {
                	this.setAttackTarget(null);
                	this.setRevengeTarget(null);
                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.8D);
                }
                else
                {
    				vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 12, 6, p.getPositionVector());
    				if ( vec3d != null )
                    {
    					this.setAttackTarget(null);
                    	this.setRevengeTarget(null);
    					this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.8D);
                    }
                }
			}
		}
		return super.attackEntityAsMob(p);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CRAB_RAVE, Integer.valueOf(0));
    }

    public int getIsCrabRave() {
        return this.dataManager.get(CRAB_RAVE).intValue();
    }

    private void setCrabRave(int in) {
        this.dataManager.set(CRAB_RAVE, Integer.valueOf(in));
    }

    @Override
    protected boolean canDespawn() {
        return this.getIsCrabRave() == 0 && super.canDespawn();
    }

    @Override
    public int getVariantMax() {
        return 4;
    }

    public void crabRave() {
        this.setCrabRave(this.getRNG().nextInt(3) + 1);
        this.tasks.taskEntries.clear();
        this.targetTasks.taskEntries.clear();
        this.setAttackTarget(null);
        this.navigator.clearPath();
    }

    public void unCrabRave() {
        this.setCrabRave(0);
        this.initEntityAI();
    }

    @Override
    protected IVariantTypes getBaseChild() {
        return new EntityCrab(this.world);
    }

    @Override
    protected float getWaterSlowDown() {
        return 0F;
    }
    
    class AIHurtByTarget extends EntityAIHurtByTarget {
        public AIHurtByTarget() {
            super(EntityCrab.this, false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            super.startExecuting();

            if(EntityCrab.this.isChild()) {
                this.alertOthers();
                this.resetTask();
            }
        }

        @Override
        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) {
            if (creatureIn instanceof EntityCrab && !creatureIn.isChild()) {
                super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);
            }
        }
    }

    @Override
    protected String getContainerName() {
        return "crab";
    }

}
