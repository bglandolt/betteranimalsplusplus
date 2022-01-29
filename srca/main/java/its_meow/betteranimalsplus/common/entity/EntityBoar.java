package its_meow.betteranimalsplus.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBoarAttack;
import its_meow.betteranimalsplus.common.entity.ai.EntityAICallForHelp;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIChildFlee;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIFollowHerd;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIMoveToNonFullBlock;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBoar extends EntityAnimalWithSelectiveTypes implements IMob
{

    public EntityBoar(World worldIn)
    {
        super(worldIn);
        this.setSize(0.95F, 0.95F);
        this.stepHeight = 1.05F;
    }
        
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
	    		if ( this.getDistance(source.getTrueSource()) > 10 )
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
    	
    	// FLEE NO TARGET
		if ( source != null && source.getTrueSource() instanceof EntityPlayer )
		{
			EntityPlayer p = (EntityPlayer)source.getTrueSource();
			if ( this.rand.nextInt(4) == 0 || this.getNavigator().getPathToEntityLiving(p) == null || ( Math.abs(this.posY-p.posY) > 2 ) || this.getDistance(p) > 8 )
			{
				Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, p.getPositionVector());
				
                if ( vec3d != null )
                {
                	this.fleeTimer = 80 + rand.nextInt(40);
                	this.setAttackTarget(null);
                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
                else
                {
    				vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 12, 6, p.getPositionVector());
    				if ( vec3d != null )
                    {
                    	this.fleeTimer = 60 + rand.nextInt(30);
    					this.setAttackTarget(null);
    					this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                    }
                }
			}
		}
		
		this.cannotReachTimer += 50;
        
        if ( this.fleeTimer > 0 && ( source.getTrueSource() != null && this.getDistance(source.getTrueSource()) < 4 ) )
        {
            this.cannotReachTimer = 0;
            this.fleeTimer = 0;
        }
        
		return super.attackEntityFrom(source, amount);
    }
    
    
    
    public int cannotReachTimer = 0;
    public int fleeTimer = 0;
    
    @Override
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    	if ( this.world.isRemote ) return;
    	
    	if ( this.getAttackTarget() != null )
    	{
        	this.cannotReachTimer++;
        	if ( this.cannotReachTimer > 200 && ( Math.abs(this.posY-this.getAttackTarget().posY) > 1 || this.getDistance(this.getAttackTarget()) > 6 ) )
        	{
        		this.fleeTimer = 100 + rand.nextInt(50);
        		this.getNavigator().clearPath();
        		this.cannotReachTimer = this.fleeTimer;
        	}
        	if ( this.fleeTimer > 0 )
    		{
				if ( this.getNavigator().noPath() )
				{
					Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 16, 6, this.getAttackTarget().getPositionVector());
					
	                if ( vec3d != null )
	                {
	                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
	                }
	                else
	                {
	                	vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this, 12, 6, this.getAttackTarget().getPositionVector());
						
		                if ( vec3d != null )
		                {
		                    this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
		                }
		                else
		                {
		                	this.fleeTimer -= 20;
		                }
	                }
				}
				AIHelper.faceMovingDirection(this);
    		}
    		else
    		{
    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
    		}
    	}
    	else
    	{
    		this.cannotReachTimer = 0;
    	}
    	
    	
    	
//    	super.onLivingUpdate();
//    	    	
//    	if ( this.getAttackTarget() != null )
//    	{
//    		if ( this.fleeTimer > 0 )
//    		{
//				AIHelper.faceMovingDirection(this);
//    		}
//    		else
//    		{
//    			AIHelper.faceEntitySmart(this, this.getAttackTarget());
//	    		this.getLookHelper().setLookPositionWithEntity(this.getAttackTarget(), 20.0F, 20.0F);
//	    	}
//    	}
    	
	    if ( this.fleeTimer > 0 )
	    {
	    	this.setSprinting(false);
	    	this.fleeTimer--;
	    }
	    
	    if ( this.cannotReachTimer > 0 )
        {
        	this.cannotReachTimer--;
        	if ( this.cannotReachTimer == 1 && this.getAttackTarget() != null && this.getDistance(this.getAttackTarget()) > 10 )
        	{
        		this.setAttackTarget(null);
        	}
        }
	    
	    if ( !this.isHungry && this.world.getWorldTime() % 2000 == 0 && this.rand.nextBoolean() )
	    {
	    	this.isHungry = true;
	    }
    }
    
    /*
    
    adults attack near players
    adults move away from near players
    
    
    child move away from near players

    
    
    
    */
    
    public boolean isHungry = rand.nextBoolean();
    
	@Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityBoar.AIMeleeAttack());
        if ( this.isChild() )
        {
            this.tasks.addTask(1, new EntityAIChildFlee(this, 1.0D));
        }
        this.tasks.addTask(4, new EntityAIMate(this, 0.6D));
        this.tasks.addTask(5, new EntityAIFollowHerd(this, 0.6D));
        this.tasks.addTask(6, new BoarAIEatCrops(this));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        
        this.targetTasks.addTask(0, new EntityAICallForHelp(this, 12, new Class[0])
        {
        	@Override
        	protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
            {
                if ( rand.nextInt(3) == 0 ) creatureIn.setAttackTarget(entityLivingBaseIn);
            }
        });
        this.targetTasks.addTask(1, new EntityAIBoarAttack(this));
    }
	
	@Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
		if ( entitylivingbaseIn == null )
		{
            super.setAttackTarget(null);
		}
		else if ( this.isChild() )
    	{
    		this.setRevengeTarget(entitylivingbaseIn);
            super.setAttackTarget(null);
    	}
    	else if ( this.getAttackTarget() == null )
    	{
    		this.fleeTimer = 0;
    		this.cannotReachTimer = 0;
            super.setAttackTarget(entitylivingbaseIn);
    	}
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
        if (!world.isRemote && !this.isChild())
        {
            if (this.rand.nextInt(12) == 0)
            {
                ItemStack stack = new ItemStack(HeadTypes.BOARHEAD.getItem(this.getTypeNumber()));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_PIG;
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
		this.setSprinting(false);
		
		this.cannotReachTimer = 0;
		this.fleeTimer = 0;
		
        Vec3d pos = this.getPositionVector();
        Vec3d targetPos = entityIn.getPositionVector();
        ((EntityLivingBase) entityIn).knockBack(entityIn, 0.8F, pos.x - targetPos.x, pos.z - targetPos.z);

        // Vanilla attack code for mobs

        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int i = 0;

        if (entityIn instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase) entityIn).getCreatureAttribute());
            i += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag) {
            if (i > 0 && entityIn instanceof EntityLivingBase) {
                ((EntityLivingBase) entityIn).knockBack(this, i * 0.5F, MathHelper.sin(this.rotationYaw * 0.017453292F), (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                entityIn.setFire(j * 4);
            }

            if (entityIn instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entityIn;
                ItemStack itemstack = this.getHeldItemMainhand();
                ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isShield(itemstack1, entityplayer)) {
                    float f1 = 0.25F + EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (this.rand.nextFloat() < f1) {
                        entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
                        this.world.setEntityState(entityplayer, (byte) 30);
                    }
                }
            }

            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    @Override
    public void onStruckByLightning(EntityLightningBolt lightningBolt)
    {
        if (!this.world.isRemote && !this.isDead)
        {
            EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
            entitypigzombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
            entitypigzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entitypigzombie.setNoAI(this.isAIDisabled());

            if (this.hasCustomName()) {
                entitypigzombie.setCustomNameTag(this.getCustomNameTag());
                entitypigzombie.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entitypigzombie);
            this.setDead();
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        if (ageable instanceof EntityBoar)
        {
            EntityBoar boar = new EntityBoar(this.world);
            boar.setType(this.getTypeNumber());
            return boar;
        }
        else if (ageable instanceof EntityPig)
        {
        	if ( rand.nextBoolean() )
        	{
                return new EntityPig(this.world);
        	}
        	else
        	{
        		EntityBoar boar = new EntityBoar(this.world);
                boar.setType(this.getTypeNumber());
                return boar;
        	}
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal != this)
        {
            if (otherAnimal instanceof EntityBoar || otherAnimal instanceof EntityPig)
            {
                if (otherAnimal.isInLove() && this.isInLove())
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() instanceof ItemFood || stack.getItem() instanceof ItemSeeds;
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
    protected int[] getTypesFor(Set<BiomeDictionary.Type> types)
    {
        if(types.contains(Type.FOREST) && !types.contains(Type.CONIFEROUS)) {
            return new int[] {1, 2, 3};
        } else if(types.contains(Type.CONIFEROUS) && !types.contains(Type.SNOWY)) {
            return new int[] {1, 2, 3};
        } else if(types.contains(Type.CONIFEROUS) && types.contains(Type.SNOWY)) {
            return new int[] {1, 4};
        } else if(types.contains(Type.SNOWY) && !types.contains(Type.CONIFEROUS)) { 
            return new int[] {4};
        } else if(types.contains(Type.SAVANNA) || types.contains(Type.PLAINS)) {
            return new int[] {1, 2, 3};
        } else {
            return new int[] {1, 2, 3};
        }
    }

    @Override
    protected String getContainerName()
    {
        return "boar";
    }
    
//    @Override
//    public void setInLove(@Nullable EntityPlayer player)
//    {
//        this.resetInLove(); = 600;
//
//        if (player != null)
//        {
//            this.playerInLove = player.getUniqueID();
//        }
//
//        this.world.setEntityState(this, (byte)18);
//    }
    
    public int headRam = -1;
    
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
    	if ( id == 18 || id == 27 )
    	{
			this.headRam = 20;
    	}
		else if (id == 28)
		{
			super.handleStatusUpdate((byte)18);
		}
		else
		{
			super.handleStatusUpdate(id);
		}
    }
    
//    @Override
//    public void setEntityState(Entity entityIn, byte state)
//    {
//    	
//    }
    
    @Override
    protected float getWaterSlowDown()
    {
        return 0.7F;
    }
    
    public class AIMeleeAttack extends EntityAIAttackMelee
    {
        public AIMeleeAttack()
        {
            super(EntityBoar.this, 1.0D, true); // XXX
        }
        
        @Override
        protected void checkAndPerformAttack(EntityLivingBase entity, double dist)
        {
        	if ( EntityBoar.this.fleeTimer > 0 ) return;
        	
        	double attackRange = this.getAttackReachSqr(entity);

	        if ( dist <= attackRange && this.attackTick <= 0 )
	        {
		        this.attackTick = 22;
		        this.attacker.attackEntityAsMob(entity);
		        this.attacker.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 1.5F, 0.45F);
	    		this.attacker.playSound(SoundEvents.BLOCK_SAND_FALL, 0.8F, 0.75F);
        		this.attacker.setSprinting(false);
		    }
		    else if ( dist <= attackRange * 2.0D )
		    {
		        if ( this.attackTick <= 0 )
		        {
		        	this.attackTick = 14;
	        		this.attacker.setSprinting(false);
		        }
		        else if ( this.attackTick <= 8 )
		        {	        		
		        	this.attacker.setSprinting(true);

		        	if ( this.attackTick == 4 )
		        	{
		        		this.attacker.world.setEntityState(this.attacker, (byte) 27);
		        		this.attacker.getNavigator().clearPath();
		        		if ( !this.attacker.world.isRemote )
		        		{
		        			Vec3d velocityVector = new Vec3d(entity.posX-this.attacker.posX, 0, entity.posZ-this.attacker.posZ);
		        			double push = 0.25D;
		        			this.attacker.addVelocity(velocityVector.x*push, -0.02D, velocityVector.z*push);
		        			this.attacker.velocityChanged = true;
		                }
		        		
//		        		if ( this.attacker.world.rand.nextInt(4) == 0 )
//		        		{
//			        		Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.attacker, 12, 6, entity.getPositionVector());
//	
//			                if ( vec3d != null )
//			                {
//			                	this.attacker.setAttackTarget(null);
//			                    this.attacker.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.8D);
//			                }
//		        		}
		        	}
		        }
		        else
		        {
	        		this.attacker.setSprinting(false);

		        }
	        } 
	        else
	        {
	        	this.attackTick = 22;
        		this.attacker.setSprinting(false);
	        }
        }

        @Override
        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return 7.0D + attackTarget.width;
        }
    }
    
    public static class BoarAIEatCrops extends EntityAIMoveToNonFullBlock
    {
        private final EntityBoar boar;

        public BoarAIEatCrops(EntityBoar boarIn)
        {
            super(boarIn, 0.6D, 16);
            this.boar = boarIn;
        }

        @Override
        public boolean shouldExecute()
        {
        	if ( boar.getAttackTarget() != null )
        	{
        		return false;
        	}
        	
//            if ( this.runDelay <= 0 )
//            {
//                if( !net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.boar.world, this.boar) )
//                {
//                    return false;
//                }
//            }

            return !this.boar.isInLove() && super.shouldExecute();
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return !this.boar.isInLove() && super.shouldContinueExecuting();
        }

        @Override
        public void updateTask()
        {
            super.updateTask();
            this.boar.getLookHelper().setLookPosition((double) this.destinationBlock.getX() + 0.5D, (double) (this.destinationBlock.getY() + 1), (double) this.destinationBlock.getZ() + 0.5D, 10.0F, (float) this.boar.getVerticalFaceSpeed());

            if(this.isAtDestination()) {
                World world = this.boar.world;
                BlockPos blockpos = this.destinationBlock.up();
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                if(!this.boar.isInLove() && block instanceof BlockCrops && ((BlockCrops) block).isMaxAge(iblockstate))
                {
                    this.boar.world.playEvent(2001, blockpos, Block.getIdFromBlock(Blocks.GRASS));
                    world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                    world.destroyBlock(blockpos, true);
                    this.boar.isHungry = false;
                    this.boar.setInLove(null);
                }
            }
        }

        @Override
        protected boolean shouldMoveTo(World worldIn, BlockPos pos)
        {
            Block block = worldIn.getBlockState(pos).getBlock();

            if(block == Blocks.FARMLAND && !this.boar.isInLove()) {
                pos = pos.up();
                IBlockState iblockstate = worldIn.getBlockState(pos);
                block = iblockstate.getBlock();

                if(block instanceof BlockCrops && ((BlockCrops) block).isMaxAge(iblockstate)) {
                    return true;
                }
            }

            return false;
        }
    }
    
    @Override
    protected boolean canEquipItem(ItemStack stack)
    {
        return false;
    }
    
    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
    {
        ItemStack itemstack = itemEntity.getItem();
        //Item item = itemstack.getItem();

        if ( !this.isInLove() && this.isBreedingItem(itemstack) )
        {
            if (itemstack.isEmpty())
            {
                itemEntity.setDead();
            }
            else
            {
                itemstack.setCount(itemstack.getCount()-1);
            }
            this.setInLove(null);
            this.world.setEntityState(this, (byte)28);
        }
    }


}
