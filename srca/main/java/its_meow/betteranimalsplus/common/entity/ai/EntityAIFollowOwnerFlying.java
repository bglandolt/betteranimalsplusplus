package its_meow.betteranimalsplus.common.entity.ai;

import its_meow.betteranimalsplus.common.entity.EntityLammergeier;
import its_meow.betteranimalsplus.util.SimpleTeleporter;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowOwnerFlying extends EntityAIBase {
    private final EntityLammergeier tameable;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed;
    private final PathNavigateFlying petPathfinder;
    private int timeToRecalcPath;
    float maxDist;
    float minDist;
    private float oldWaterCost;

    public EntityAIFollowOwnerFlying(EntityLammergeier tameableIn, double followSpeedIn, float minDistIn, float maxDistIn) {
        // super(tameableIn, followSpeedIn, minDistIn, maxDistIn);
        this.tameable = tameableIn;
        this.world = tameableIn.world;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = (PathNavigateFlying) tameableIn.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setMutexBits(4);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.tameable.getOwner();

        if (entitylivingbase == null) {
            return false;
        } else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer) entitylivingbase).isSpectator()) {
            return false;
        } else if (this.tameable.isSitting()) {
            return false;
        } else if (this.tameable.getDistanceSq(entitylivingbase) < this.minDist * this.minDist) {
            return false;
        } else if (this.tameable.getAttackTarget() != null && this.tameable.getAttackTarget().isEntityAlive()) {
            return false;
        } else if (!this.tameable.getNavigator().noPath()) {
            return false;
        } else {
            this.owner = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        if (this.tameable.getAttackTarget() != null) {
            return false;
        }
        return !this.petPathfinder.noPath() && this.tameable.getDistanceSq(this.owner) > this.maxDist * this.maxDist && !this.tameable.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.getPathPriority(PathNodeType.WATER);
        this.tameable.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by
     * another one
     */
    @Override
    public void resetTask() {
        this.owner = null;
        this.petPathfinder.clearPath();
        this.tameable.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        this.tameable.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, 20);

        if (!this.tameable.isSitting()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;

                // Attempt to find a path
                if (!this.petPathfinder.tryMoveToXYZ(this.owner.posX, this.owner.posY + 2, this.owner.posZ, this.followSpeed)) {
                    // Failed to find path
                    if (!this.tameable.getLeashed() && !this.tameable.isRiding()) {
                        // Distance too large, teleport!
                        if (this.tameable.getDistanceSq(this.owner) >= 144.0D || this.tameable.getEntityWorld() != this.owner.getEntityWorld()) {
                            int i = MathHelper.floor(this.owner.posX) - 2;
                            int j = MathHelper.floor(this.owner.posZ) - 2;
                            int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                            for (int l = 0; l <= 4; ++l) {
                                for (int i1 = 0; i1 <= 4; ++i1) {
                                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.isTeleportFriendlyBlock(i, j, k, l, i1)) {
                                        if (this.tameable.getEntityWorld() != this.owner.getEntityWorld()) {
                                            this.tameable.changeDimension(this.owner.getEntityWorld().provider.getDimension(), new SimpleTeleporter());
                                        }
                                        this.tameable.setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, this.tameable.rotationYaw, this.tameable.rotationPitch);
                                        this.petPathfinder.clearPath();
                                        return;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    protected boolean isTeleportFriendlyBlock(int x, int p_192381_2_, int y, int p_192381_4_, int p_192381_5_) {
        BlockPos blockpos = new BlockPos(x + p_192381_4_, y - 1, p_192381_2_ + p_192381_5_);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(this.tameable) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
    }
}