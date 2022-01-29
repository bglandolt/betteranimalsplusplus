package its_meow.betteranimalsplus.common.entity;

import javax.annotation.Nullable;

import its_meow.betteranimalsplus.common.entity.ai.AIHelper;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBearAttack;
import its_meow.betteranimalsplus.common.entity.ai.EntityAIBearAttackWithAnimations;
import its_meow.betteranimalsplus.common.entity.ai.EntityAICallForHelp;
import its_meow.betteranimalsplus.init.ModEntities;
import its_meow.betteranimalsplus.init.ModLootTables;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBrownBear extends EntityMob
{

    public int warningSoundTicks;

    public EntityBrownBear(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F, 1.95F); // was 1.2
        this.stepHeight = 2.05F;
        this.dismountZotz();
    }
    
    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIBearAttackWithAnimations(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWander(this, 0.5D, 20));
        this.targetTasks.addTask(1, new EntityAICallForHelp(this, 16, new Class[0]));
        this.targetTasks.addTask(2, new EntityAIBearAttack(this));
    }
    
    public int cannotReachTimer = 0;
    public int fleeTimer = 0;

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.36D); // XXX 35/h
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
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
    	else if ( amount < 2 )
    	{
    		return false;
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

    	this.cannotReachTimer = 0;
		this.fleeTimer = 0;
		
		if ( this.knockBackAttack ) 
		{
			AIHelper.spawnSweepHit( this, entityIn );
		}
		
		float damage = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), this.knockBackAttack?damage*2.0F:damage);

        if (flag)
        {
        	if ( this.latchTimer <= 0 &&      ( (entityIn.motionX*entityIn.motionX+entityIn.motionZ*entityIn.motionZ) < 0.5D || this.getDistanceSq(this.getAttackTarget()) <= 3.0D )      && this.grabTarget(this.getAttackTarget()) ) // attackReachModifier
            {
            	this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 44, 7, true, false));
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 44, 128, true, false));
                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 18, 0, true, false));
    			this.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 2.0F, 0.3F);
    			this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.6F+this.world.rand.nextFloat()/10.0F, 1.2F+this.world.rand.nextFloat()/5.0F);
            }
        	else
        	{
            	this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 18, 0, true, false));
                
            	if ( !this.world.isRemote && entityIn instanceof EntityLivingBase )
                {
        	        try
        			{
        	        	if ( this.knockBackAttack )
        	        	{
        	        		Vec3d pos = this.getPositionVector();
        			        Vec3d targetPos = entityIn.getPositionVector();
        			        entityIn.motionX /= 2.0D;
        			        entityIn.motionZ /= 2.0D;
        			        ((EntityLivingBase) entityIn).knockBack(entityIn, 1.0F, pos.x - targetPos.x, pos.z - targetPos.z);
        	                entityIn.motionY /= 2.0D;
        	                entityIn.velocityChanged = true;
        	        	}
        	        	else
        	        	{
        	        		Vec3d pos = this.getPositionVector();
        	                Vec3d targetPos = entityIn.getPositionVector();
        	                entityIn.motionX /= 2.0D;
        	                entityIn.motionZ /= 2.0D;
        	                ((EntityLivingBase) entityIn).knockBack(entityIn, 0.0F, pos.x - targetPos.x, pos.z - targetPos.z);
        	                entityIn.motionY /= 2.0D;
        	                entityIn.velocityChanged = true;
        	        	}
        	        }
        	        catch ( Exception e )
        	        {
        	        	
        	        }
                }
        	}
            
            this.applyEnchantments(this, entityIn);
        }
        
		this.knockBackAttack = false;		
        
        return flag;
    }
    
    public boolean knockBackAttack = false;
    public float attackAnimationTimer = 0;
    public float attackAnimationTimerStanding = 0;
    
    public static void doAnimationTick( EntityBrownBear bear )
    {
        if (bear.warningSoundTicks > 0)
        {
            --bear.warningSoundTicks;
        }
        
        if (bear.attackAnimationTimer > 0)
        {
          bear.attackAnimationTimer -= 0.5F;
            
          if ( bear.attackAnimationTimer > 10 && bear.attackAnimationTimer < 20 )
          {
        	  bear.attackAnimationTimer -= 0.25F;
          }
            
          bear.attackAnimationTimerStanding = 0;
        }
        else if ( bear.attackAnimationTimerStanding > 0 )
        {
            bear.attackAnimationTimerStanding -= 0.6F;
            
        	if ( bear.attackAnimationTimerStanding < 16 )
        	{
        		bear.attackAnimationTimerStanding  -= 0.3F;
        		
        		if ( bear.attackAnimationTimerStanding < 8 )
            	{
        			bear.attackAnimationTimerStanding -= 0.3F;
            	}
        	}
        	
        	bear.attackAnimationTimer = 0;
        }
    }
    
    public static float getAttackAnimationTimer( EntityBrownBear bear )
    {
    	return bear.attackAnimationTimer;
    }
    
    public static float getAttackAnimationTimerStanding( EntityBrownBear bear )
    {
    	return bear.attackAnimationTimerStanding;
    }

    @SideOnly(Side.CLIENT)
   	public void handleStatusUpdate(byte id)
    {
    	if (id == 25)
   		{
   			this.attackAnimationTimerStanding = 0;
   		}
    	else if (id == 26)
   		{
   			this.attackAnimationTimer = 20;
   		}
    	else if (id == 28)
   		{
   			this.attackAnimationTimerStanding = 35;
   		}
   		else if (id == 29)
   		{
   			this.warningSoundTicks = 30;
   		}
   		else
   		{
   	   		super.handleStatusUpdate(id);
   		}
   	}
    
    @Override
    public void onLivingUpdate()
    {    	
      	if ( (this.isRiding() && !this.getRidingEntity().isEntityAlive()) )
    	{
    		this.getRidingEntity().setDead();
    		this.dismountZotz();
    	}
    	
    	if ( !this.isEntityAlive() )
    	{
    		this.dismountZotz();
    	}
    	
    	super.onLivingUpdate();
    	
    	if ( this.world.isRemote )
    	{
//            if (this.warningSoundTicks > 0)
//            {
//                --this.warningSoundTicks;
//            }
//            
//            if (this.attackAnimationTimer > 0)
//            {
//                --this.attackAnimationTimer;
//                
//                if ( this.attackAnimationTimer > 10 && this.attackAnimationTimer < 20 )
//            	{
//            		this.attackAnimationTimer--;
//            	}
//                
//                this.attackAnimationTimerStanding = 0;
//            }
//            else if (this.attackAnimationTimerStanding > 0)
//            {
//                --this.attackAnimationTimerStanding;
//                
//            	if ( this.attackAnimationTimerStanding < 15 )
//            	{
//            		this.attackAnimationTimerStanding--;
//            		if ( this.attackAnimationTimerStanding < 7 )
//                	{
//                		this.attackAnimationTimerStanding--;
//                	}
//            	}
//            	
//            	this.attackAnimationTimer = 0;
//            }
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
            
    		if ( this.isRiding() )
    		{
    			this.rotationYaw = -this.getAttackTarget().rotationYaw;
	    		this.prevRotationYaw = -this.getAttackTarget().prevRotationYaw;
//	    		this.rotationYaw = 0;
//	    		this.prevRotationYaw = 0;
    		}
    		else if ( this.fleeTimer > 0 )
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
    		
    		if ( this.isRiding() && this.getRidingEntity() == this.getAttackTarget() )
            {
            	if ( this.ticksExisted % 4 == 0 )
        		{
            		if ( this.rand.nextInt(4) != 0 )
            		{
            			this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 2.0F+this.rand.nextFloat()/10.0F, 0.5F+this.rand.nextFloat()*0.3F);
            			if ( rand.nextBoolean() ) this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F+this.rand.nextFloat()/10.0F, 1.2F+this.rand.nextFloat()*0.3F);
            		}
        		}
                
                if ( this.ticksExisted % 18 == 0 ) // ATTACK
                {
                    this.attackEntityAsMob(this.getAttackTarget());
                    this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 22, 7, true, false));
	                this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 22, 128, true, false));
                    this.spawnSweepParticles();
        			if ( rand.nextBoolean() ) this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.2F, 0.8F + rand.nextFloat()/5.0F);
                }

           	 	if ( this.motionY > 0 )
        		{
           	 		this.motionY = 0;
        		}
           	 
           	 	if ( this.getAttackTarget().motionY > 0 )
        		{
           	 		this.getAttackTarget().motionY = 0;
        		}
           	 
        		this.getAttackTarget().motionX = 0;
	       		this.getAttackTarget().motionZ = 0;
	       		this.getAttackTarget().velocityChanged = false;
	       		this.getAttackTarget().lastTickPosX = this.getAttackTarget().posX;
	       		this.getAttackTarget().lastTickPosY = this.getAttackTarget().posY;
	       		this.getAttackTarget().lastTickPosZ = this.getAttackTarget().posZ;
	       		this.getAttackTarget().setSprinting(false);
	       		this.getAttackTarget().setSneaking(true);
	       		this.getAttackTarget().resetActiveHand();
            }
    	}
    	else
    	{
			AIHelper.faceMovingDirection(this);
    		this.cannotReachTimer = 0;
    	}
    	
    	
         
         if ( this.latchTimer > 0 )
         {
         	this.latchTimer--;
         }
         
         if ( this.fleeTimer > 0 )
         {
 	    	this.setSprinting(false);
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
         
         if ( this.isRiding() && this.ticksExisted - this.latchSnapshot > 100 )
         {
             this.dismountZotz();
         }
         
    }
    
    @Override
    public boolean shouldDismountInWater(Entity rider)
    {
        return false;
    }
    
    public int latchTimer = 0;
    public int latchSnapshot = 0;
    
    public boolean grabTarget(EntityLivingBase entity)
    {
    	if ( entity.isRiding() || entity.isBeingRidden() || this.isRiding() || this.isBeingRidden() || !this.isEntityAlive() || !entity.isEntityAlive() )
    	{
    		return false;
    	}
    	
    	if ( entity.height < 1.0D )
    	{
    		return false;
    	}
    	
    	if ( !entity.isPotionApplicable(new PotionEffect(MobEffects.SLOWNESS)) ) // || !entity.isPotionActive(MobEffects.SLOWNESS) )
    	{
    		return false;
    	}
    	
    	this.fleeTimer = 0;
    	this.latchSnapshot = this.ticksExisted;

        this.startRiding(entity, true);
        
        if ( entity instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP) entity).connection.sendPacket(new SPacketSetPassengers(entity));
        }
        
		this.world.setEntityState(this, (byte) 25);
    
        return true;
    }

    public void dismountZotz()
    {
    	this.latchTimer = 90 + this.rand.nextInt(6)*10; // 90 - 140
    	Entity mount = this.getRidingEntity();
    	
    	if ( mount != null )
    	{
            this.dismountRidingEntity();
            this.dismountEntity(mount);

            if ( !this.world.isRemote ) // SERVER
            {
                if ( mount instanceof EntityPlayerMP )
                {
                    ((EntityPlayerMP) mount).connection.sendPacket(new SPacketSetPassengers(mount));
                }
            }
            
        	mount.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	}
    	
    	this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
    	this.velocityChanged = true;

    }

//    @Override
//    public void dismountRidingEntity()
//    {
//    	super.dismountRidingEntity();
//    }

    @Override
    public boolean canRiderInteract()
    {
        return true;
    }

    @Override
    public boolean shouldRiderSit()
    {
        return false;
    }
    
    @Override
    public double getYOffset()
    {
    	if ( this.getRidingEntity() != null )
    	{
    		return -this.getRidingEntity().height+0.5;
    	}
        return 0.5D;
    }

    
    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
    {
    	if ( this.getAttackTarget() == null && entitylivingbaseIn != null )
    	{
    		this.latchTimer = this.rand.nextInt(13)*10;
    		this.fleeTimer = 0;
    		this.cannotReachTimer = 0;
    	}
        super.setAttackTarget(entitylivingbaseIn);
    }

    public void spawnSweepParticles()
    {
    	double xx = this.posX + (double) (MathHelper.sin(this.rotationYaw * 0.017453292F))/2.0F;
    	double yy = this.posY + (double) this.height * 0.3D;
    	double zz = this.posZ + (double) -MathHelper.cos(this.rotationYaw * 0.017453292F)/2.0F;

    	if (this.world instanceof WorldServer)
    	{
    		for ( int i = 16; i > 0; i-- )
    		{
    			((WorldServer) this.world).spawnParticle(EnumParticleTypes.REDSTONE, xx, yy, zz, 0, 0, 0, 0, 0.08D, new int[0]);
    		}
    	}
    }
    
    // ==========================================================================================
    // ==========================================================================================
    //
    // OTHER
    //
    // ==========================================================================================
    // ==========================================================================================
    

    @Override
    protected boolean canDespawn() {
        return ModEntities.entityMap.containsKey("brownbear") ? ModEntities.entityMap.get("brownbear").despawn && !this.hasCustomName() : false;
    }

    public void playWarningSound()
    {
        if (this.warningSoundTicks <= 0)
        {
            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, 0.9F + rand.nextFloat()/5.0F);
			this.world.setEntityState(this, (byte) 29);
            this.warningSoundTicks = 30;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.8F, 0.95F + rand.nextFloat()/10.0F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        return false;
    }

    @Override
    public boolean isPreventingPlayerRest(EntityPlayer playerIn) {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.getAttackingEntity() == playerIn;
    }
    

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return ModLootTables.BEAR_BROWN;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        this.doDropHead();
    }
    
    @Override
    protected float getWaterSlowDown()
    {
        return 0.7F;
    }
    
    protected void doDropHead() {
        if (!world.isRemote && !this.isChild()) {
            if (this.rand.nextInt(12) == 0) {
                ItemStack stack = new ItemStack(HeadTypes.BEARHEAD.getItem(1));
                this.entityDropItem(stack, 0.5F);
            }
        }
    }
}
