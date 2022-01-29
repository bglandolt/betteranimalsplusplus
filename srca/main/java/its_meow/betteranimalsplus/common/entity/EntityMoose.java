package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIEatGrassCustom;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIMooseAttack;
import its_meow.betteranimalsplus.common.entity.ai.PublicEntityAIAttack;
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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMoose extends EntityAnimalEatsGrassWithTypes implements IMob
{
	
	public class AIMeleeAttack extends PublicEntityAIAttack
    {
        public AIMeleeAttack()
        {
            super(EntityMoose.this, 1.0D); // XXX
        }
        
        public void resetTask()
        {
        	this.attacker.setSprinting(false);
        	super.resetTask();
        }
        
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	double attackRange = this.getAttackReachSqr(entity);

            if ( this.attackTick <= 6 )
            {
            	if ( this.attackTick <= 0 )
            	{
    		        this.attackTick = 36;
    		        
            		if ( dist <= attackRange )
            		{
	    		        this.attacker.attackEntityAsMob(entity);
	    	        	this.attacker.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 2.0F, 0.4F);
	    	    		this.attacker.playSound(SoundEvents.BLOCK_SAND_FALL, 1.0F, 0.7F);
	    	    		this.attacker.motionX /= 4.0D;
                		this.attacker.motionZ /= 4.0D;
                		this.attacker.velocityChanged = true;
        	    		this.attacker.world.setEntityState(this.attacker, (byte) 28); // HEAD DOWN
            		}
            		
            		if ( dist <= attackRange * 4.0D )
             	    {
            			EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 28); // HEAD DOWN
                    }
            		else
            		{
            			this.attackTick = 36;
        		        EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 29); // HEAD RESET
            		}
            	}
            	else if ( this.attackTick == 6 )
            	{
            		if ( dist <= attackRange * 2.0D )
            		{
                		EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 27); // HEAD RAM
                		this.attacker.motionX *= 1.5D;
                		this.attacker.motionZ *= 1.5D;
                		this.attacker.velocityChanged = true;
            		}
            	}
            	else if ( this.attackTick == 3 )
            	{
            		if ( dist <= attackRange * 2.0D )
            		{
                		// EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 27); // HEAD RAM
                		this.attacker.motionX *= 1.5D;
                		this.attacker.motionZ *= 1.5D;
                		this.attacker.velocityChanged = true;
            		}
            	}
    	    }
            else if ( dist <= attackRange * 4.0D )
    	    {
            	if ( this.attackTick % 10 == 0 )
            	{
    		        EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 28); // HEAD DOWN
    	    	}
            }
            else
            {
            	this.attackTick = 35;
		        EntityMoose.this.world.setEntityState(EntityMoose.this, (byte) 29); // HEAD RESET
            }
        }

        @Override
        public void updateTask()
        {
        	if ( EntityMoose.this.fleeTimer > 0 )
        	{
        		return;
        	}
        	super.updateTask();
        }
        
        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return attackReachModifier() + attackTarget.width * 2.0D;
        }
    }
    
    protected double attackReachModifier()
    {
    	return 14.0D;
    }
    
    public int headDirection = -1;
    
    public EntityMoose(World worldIn)
    {
        super(worldIn, 5);
        this.setSize(1.4F, 1.95F);
        this.stepHeight = 2.05F;
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) 
    {
        this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.5F, 1.0F);
    }
    
//    @Override
//    public boolean attackEntityFrom(DamageSource source, float amount)
//    {
//		if ( source != null && source.getTrueSource() instanceof EntityPlayer )
//		{
//			EntityPlayer p = (EntityPlayer)source.getTrueSource();
//			if ( this.getNavigator().getPathToEntityLiving(p) == null )
//			{
//				Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, p.getPositionVector());
//				
//                if ( vec3d != null )
//                {
//                	this.setAttackTarget(null);
//                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.8D);
//                }
//			}
//		}
//		return super.attackEntityFrom(source, amount);
//    }

    
    
    // TODO
    
    
    
    // add flee to moose
    
    // goose / turkey like pheasant
    
    // test server
    
    
    
    
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if (this.world.isRemote)
        {
            return false;
        }
    	
    	// AGGRO
    	if ( source.getTrueSource() instanceof EntityLivingBase )    		
    	{
    		if ( this.getAttackTarget() == null )
    		{
	    		if ( this.getDistance(source.getTrueSource()) > 25 )
	    		{
	    			this.cannotReachTimer = 200;
	    		}
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    		else if ( this.rand.nextInt(3) == 0 )
    		{
	    		this.setAttackTarget((EntityLivingBase) source.getTrueSource());
    		}
    	}
    	
       if ( this.isRiding() && source == DamageSource.IN_WALL )
       {
            return false;
       }
       else
       {
            if ( this.motionY > 0 )
            {
            	this.motionY = 0.0D;
            }
            
            this.cannotReachTimer += 30;
            
            if ( this.fleeTimer > 0 && ( source.getTrueSource() != null && this.getDistance(source.getTrueSource()) < 4 ) )
            {
                this.cannotReachTimer = 0;
                this.fleeTimer = 0;
            }
            
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {   	
    	//this.faceEntity(entityIn, 30.0F, 30.0F);
    	//this.getLookHelper().setLookPositionWithEntity(entityIn, 30.0F, 30.0F);
    	
    	this.cannotReachTimer = 0;
		this.fleeTimer = 0;
    	this.setSprinting(false);
		
//		if ( !this.world.isRemote && this.getDistance(entityIn) < 4 )
//		{
//			Vec3d velocityVector = new Vec3d(entityIn.posX-this.posX, 0, entityIn.posZ-this.posZ);
//			double push = 0.5D;
//			entityIn.addVelocity(velocityVector.x*push, -0.02D, velocityVector.z*push);
//			entityIn.velocityChanged = true;
//        }
				
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        Vec3d pos = this.getPositionVector();
        Vec3d targetPos = entityIn.getPositionVector();
        ((EntityLivingBase) entityIn).knockBack(entityIn, 1.0F+this.rand.nextFloat()/8.0F, pos.x - targetPos.x, pos.z - targetPos.z);
        entityIn.motionY += 0.2D;
        entityIn.velocityChanged = true;
        //float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        // return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        
        return flag;
    }
    
    
    
    public int cannotReachTimer = 0;
    public int fleeTimer = 0;
    
    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(2, new EntityMoose.AIMeleeAttack());
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, (Class<?>) null));
        this.targetTasks.addTask(1, new EntityAIMooseAttack(this));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.36D); // XXX 35/h
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }
    
    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	
    	if ( this.world.isRemote )
    	{
            if ( this.headRam > 0 )
            {
            	if ( this.headRam == 20 )
            	{
            		this.headDirection = (this.world.rand.nextBoolean()?1:-1);
            	}
            	
            	if ( this.headRam-- > 6 )
            	{
            		this.headRam--;
            	}
            	
            	this.headDownDuration = 0;
            }
            else if ( this.headDownDuration > 0 )
            {
            	this.headRam = 0;
            	this.headDownDuration -= 2;
            }
    		return;
    	}
    	
    	if ( this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive() )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 200 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 2 || this.getDistance(this.getAttackTarget()) > 6 ) )
        	{
        		this.fleeTimer = 150 + rand.nextInt(50);
        		this.getNavigator().clearPath();
        		this.cannotReachTimer = this.fleeTimer;
        	}
            
        	if ( this.fleeTimer > 0 )
    		{
				if ( this.getNavigator().noPath() )
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
					
	                if ( vec3d != null && this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0) )
                    {

                    }
	                else
	                {
	                	vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 12, 6, this.getAttackTarget().getPositionVector());
						
		                if ( vec3d != null && this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0) )
		                {

		                }
		                else
		                {
		                	this.fleeTimer -= 20;
		                }
	                }
    				AIHelper.faceMovingDirection(this);
				}
    		}
    		else
    		{
    	    	if ( !this.collidedHorizontally )
    	    	{
    	    		AIHelper.faceEntitySmart(this, this.getAttackTarget());
    	    	}
    	    	else
    	    	{
    	    		if ( this.motionY < 0.3D && this.ticksExisted % 20 == 0 )
    	    		{
    	    			this.addVelocity(0, 0.3D, 0);
    	    			this.velocityChanged = true;
    	    		}
    	    	}
				this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 30.0F, 30.0F);
    		}
    	}
         
         if ( this.fleeTimer > 0 )
         {
         	this.fleeTimer--;
         }
         
         if ( this.cannotReachTimer > 0 )
         {
         	this.cannotReachTimer--;
         	if ( this.cannotReachTimer <= 1 && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > 12 )
         	{
         		this.setAttackTarget(null);
         	}
         }
         
    }

    @Override
    protected float getWaterSlowDown()
    {
        return 0.7F;
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
				this.headDownDuration = 90 + this.headDownDuration;
			}
			else
			{
				this.headDownDuration = 90;
			}
		}
		else if (id == 29) // head reset
		{
			if ( this.headDownDuration > 90 )
			{
				this.headDownDuration = this.headDownDuration - 90;
			}
			else if ( this.headDownDuration > 30 )
			{
				this.headDownDuration = 30;
			}
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}
    
    public int headRam = 0;
    public int headDownDuration = 0;

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
