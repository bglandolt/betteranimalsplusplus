package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.EntityAIEatGrassCustom;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIMooseAttack;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMoose extends EntityAnimalEatsGrassWithTypes implements IMob
{

    public EntityMoose(World worldIn)
   {
        super(worldIn, 5);
        this.setSize(1.4F, 1.9F);
        this.stepHeight = 2.05F;
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) 
    {
        this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.5F, 1.0F);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
		if ( source != null && source.getTrueSource() instanceof EntityPlayer )
		{
			EntityPlayer p = (EntityPlayer)source.getTrueSource();
			if ( this.getNavigator().getPathToEntityLiving(p) == null )
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, p.getPositionVector());
				
                if ( vec3d != null )
                {
                	this.setAttackTarget(null);
                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.8D);
                }
			}
		}
		return super.attackEntityFrom(source, amount);
    }

    
    
    // TODO
    
    
    
    // add flee to moose
    
    // goose / turkey like pheasant
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    protected void initEntityAI() // TODO =--==--==--=-=-==-=-=
    {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAIWander(this, 0.35D));
        //this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.65D, false));
        this.tasks.addTask(2, new EntityAIMooseAttack(this, 0.65D, true));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, (Class<?>) null));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 75, true, true, e -> e.getDistance(this) < 15));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }
    
    @Override
    public void onLivingUpdate()
    {
    	if ( this.getAttackTarget() != null )
    	{
    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.prevRotationPitch = 0;
//    		this.prevRotationYaw = 0;
//    		this.newPosRotationIncrements = 0;
    	}
    	else
    	{
        	this.faceMovingDirection();
    	}
    	super.onLivingUpdate();
    	if ( this.getAttackTarget() != null )
    	{
    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.prevRotationPitch = 0;
//    		this.prevRotationYaw = 0;
//    		this.newPosRotationIncrements = 0;
    	}
    	else
    	{
        	this.faceMovingDirection();
    	}
    }
    
    public void faceMovingDirection()
    {
    	try
    	{
	    	PathPoint p = this.getNavigator().getPath().getFinalPathPoint();

	        double d0 = (p.x - this.posX) * 2;
	        double d2 = (p.z - this.posZ) * 2;
	        double d1 = p.y - this.posY;
	
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
	        this.rotationPitch = f1;
	        this.rotationYaw = f;
    	}
    	catch ( Exception e ) {}
    }
    
    //public float rotationYaw;
    //public float rotationPitch;
    //public float prevRotationYaw;
    //public float prevRotationPitch;
    
//    @Override
//    protected void setRotation(float yaw, float pitch)
//    {
//    	if ( Math.abs(this.rotationYaw - yaw) > 1.0F )
//    	{
//    		yaw = 1.0F;
//    	}
//    	if ( Math.abs(this.rotationPitch - pitch) > 1.0F )
//    	{
//    		yaw = 1.0F;
//    	}
//        this.rotationYaw = yaw % 360.0F;
//        this.rotationPitch = pitch % 360.0F;
//    }
    
//    @SideOnly(Side.CLIENT)
//    public void turn(float yaw, float pitch)
//    {
//        float f = this.rotationPitch;
//        float f1 = this.rotationYaw;
//        this.rotationYaw = (float)((double)this.rotationYaw + (double)yaw * 0.15D);
//        this.rotationPitch = (float)((double)this.rotationPitch - (double)pitch * 0.15D);
//        this.rotationPitch = MathHelper.clamp(this.rotationPitch, -90.0F, 90.0F);
//        this.prevRotationPitch += this.rotationPitch - f;
//        this.prevRotationYaw += this.rotationYaw - f1;
//    }
    
    
    
    // STOPS THE STUPID SPINNING THAT ENTITIES DO FOR NO REASON
//    @Override
//    public void onLivingUpdate()
//    {
//    	if ( this.getAttackTarget() != null )
//    	{
//    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.prevRotationPitch = 0;
//    		this.prevRotationYaw = 0;
//    	}
//    	super.onLivingUpdate();
//    	if ( this.getAttackTarget() != null )
//    	{
//    		this.faceEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
//    		this.prevRotationPitch = 0;
//    		this.prevRotationYaw = 0;
//    	}
//    }

    @Override
    protected float getWaterSlowDown() {
        return 0.9F;
    }

    @Override
    protected void collideWithNearbyEntities() {
        // prevent pushing
    }

    protected EntityAIEatGrassCustom provideEatTask()
    {
        return new EntityAIEatGrassCustom(this, 100, 250, eater ->
        {
            EnumFacing facing = eater.getHorizontalFacing();
            return eater.getPosition().offset(facing).offset(facing);
        });
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        Vec3d pos = this.getPositionVector();
        Vec3d targetPos = entityIn.getPositionVector();
        ((EntityLivingBase) entityIn).knockBack(entityIn, 2.0F, pos.x - targetPos.x, pos.z - targetPos.z);
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
    }
    
//    @Override
//    public boolean attackEntityFrom(DamageSource source, float amount)
//    {
//        if ( source.getTrueSource() instanceof EntityPlayer && ( this.getDistance(source.getTrueSource()) > 12 || this.getNavigator().getPathToEntityLiving(source.getTrueSource()) == null ) )
//        {
//        	EntityPlayer player = (EntityPlayer) source.getTrueSource();
//        	
//        	Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 7, new Vec3d(player.posX,player.posY,player.posZ));
//
//            if ( vec3d != null )
//            {
//            	this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.55D);
//            }
//            else
//            {
//            	this.world.setEntityState(this, (byte) 28);
//            }
//        }
//        else
//        {
//        	this.world.setEntityState(this, (byte) 28);
//        }
//    	return super.attackEntityFrom(source, amount);
//    }
    
    @SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
    {
		if (id == 27) // head ram
		{
			this.headRam = 20;
			//this.hasAttackTargetTimer = 100;
		}
		else if (id == 28) // head down
		{
			if ( this.headDownDuration <= 0 )
			{
				this.headDownDuration = 120;
			}
			else if ( this.headDownDuration < 30 )
			{
				this.headDownDuration = 120 - this.headDownDuration;
			}
			else
			{
				this.headDownDuration = 90;
			}
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}
    
    public int headRam = -1;
    public int headDownDuration = -1;

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        if( !world.isRemote && !this.isChild() )
        {
            if ( this.rand.nextInt(12) == 0 )
            {
                ItemStack stack = new ItemStack(HeadTypes.MOOSEHEAD.getItem(this.getTypeNumber()));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }

    @Override
    protected void doBlockCollisions()
    {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.minX + 0.001D, axisalignedbb.minY + 0.001D, axisalignedbb.minZ + 0.001D);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos1 = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.maxX - 0.001D, axisalignedbb.maxY - 0.001D, axisalignedbb.maxZ - 0.001D);
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos2 = BlockPos.PooledMutableBlockPos.retain();

        if(this.world.isAreaLoaded(blockpos$pooledmutableblockpos, blockpos$pooledmutableblockpos1)) {
            for(int i = blockpos$pooledmutableblockpos.getX(); i <= blockpos$pooledmutableblockpos1.getX(); ++i) {
                for(int j = blockpos$pooledmutableblockpos.getY(); j <= blockpos$pooledmutableblockpos1.getY(); ++j) {
                    for(int k = blockpos$pooledmutableblockpos.getZ(); k <= blockpos$pooledmutableblockpos1.getZ(); ++k) {
                        blockpos$pooledmutableblockpos2.setPos(i, j, k);
                        IBlockState iblockstate = this.world.getBlockState(blockpos$pooledmutableblockpos2);

                        try {
                            iblockstate.getBlock().onEntityCollidedWithBlock(this.world, blockpos$pooledmutableblockpos2, iblockstate, this);
                            this.onInsideBlock(iblockstate);
                            if(iblockstate.getBlock() == Blocks.WATERLILY) {
                                iblockstate.getBlock().dropBlockAsItem(world, blockpos$pooledmutableblockpos2.toImmutable(), iblockstate, 0);
                                world.setBlockToAir(blockpos$pooledmutableblockpos2.toImmutable());
                            }
                        } catch(Throwable throwable) {
                            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Colliding entity with block");
                            CrashReportCategory crashreportcategory = crashreport.makeCategory("Block being collided with");
                            CrashReportCategory.addBlockInfo(crashreportcategory, blockpos$pooledmutableblockpos2, iblockstate);
                            throw new ReportedException(crashreport);
                        }
                    }
                }
            }
        }

        blockpos$pooledmutableblockpos.release();
        blockpos$pooledmutableblockpos1.release();
        blockpos$pooledmutableblockpos2.release();
    }
    
    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return ModLootTables.MOOSE;
    }

    @Override
    public int getVariantMax() {
        return 4;
    }

    @Override
    protected IVariantTypes getBaseChild() {
        return null;
    }

    @Override
    protected String getContainerName() {
        return "moose";
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        int validTypes[] = { 1, 2, 3, 4 };
        return this.initData(super.onInitialSpawn(difficulty, livingdata), getBiasedRandomType(validTypes));
    }

    private int getBiasedRandomType(int[] validTypes)
    {
//    	if ( this.world.getBiomeProvider() != null && ToroQuestConfiguration.useBiomeSpecificProvinces )
//		{
//			Chunk chunk = new Chunk(this.world, chunkX, chunkZ);
//			BlockPos block = new BlockPos(chunkX*16, 0, chunkZ*16);
//			Biome biome = chunk.getBiome(block, this.world.getBiomeProvider());
//			Set<Type> biomeType = BiomeDictionary.getTypes(biome);
//		}
    	// System.out.println(this.getPosition());
    	// if ( this.world.getBiome(this.getPosition()) || this.world.getBiome(this.getPosition()).getEnableSnow() )
    	
        if ( this.world.canSnowAt(this.getPosition(), false) )
        {
        	if ( rand.nextBoolean() )
        	{
        		return 3;
        	}
        	else
        	{
        		return 4;
        	}
        }
        else
        {
        	if ( rand.nextBoolean() )
        	{
        		return 1;
        	}
        	else
        	{
        		return 2;
        	}
        }
    }
    
    
    
//    @Override
//    protected int[] getTypesFor(Set<BiomeDictionary.Type> types) {
//        if(types.contains(Type.FOREST) && !types.contains(Type.CONIFEROUS)) {
//            return new int[] {3, 6};
//        } else if(types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) {
//            return new int[] {1, 3, 6};
//        } else if(types.contains(Type.CONIFEROUS) && types.contains(Type.SNOWY)) {
//            return new int[] {2, 3};
//        } else if(types.contains(Type.SNOWY) && !types.contains(Type.FOREST)) { 
//            return new int[] {2, 4};
//        } else if(types.contains(Type.FOREST) && types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) { 
//            return new int[] {5, 6, 3, 1};
//        } else {
//            return new int[] {1, 2, 3, 4, 5, 6};
//        }
//    }


}
