package net.torocraft.toroquest.entities;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.torocraft.toroquest.ToroQuest;
import net.torocraft.toroquest.civilization.CivilizationHandlers;
import net.torocraft.toroquest.civilization.CivilizationType;
import net.torocraft.toroquest.civilization.CivilizationUtil;
import net.torocraft.toroquest.civilization.Province;
import net.torocraft.toroquest.civilization.player.PlayerCivilizationCapabilityImpl;
import net.torocraft.toroquest.civilization.quests.QuestCaptureEntity;
import net.torocraft.toroquest.civilization.quests.QuestCaptureFugitives;
import net.torocraft.toroquest.civilization.quests.QuestRecruit;
import net.torocraft.toroquest.config.ToroQuestConfiguration;
import net.torocraft.toroquest.entities.ai.EntityAIAvoidTooClose;
import net.torocraft.toroquest.entities.ai.EntityAINearestAttackableBanditTarget;
import net.torocraft.toroquest.entities.ai.EntityAINearestAttackableCivTarget;
import net.torocraft.toroquest.entities.ai.EntityAIPatrolVillage;
import net.torocraft.toroquest.entities.ai.EntityAIRaid;
import net.torocraft.toroquest.entities.ai.EntityAISmartTempt;
import net.torocraft.toroquest.entities.render.RenderGuard;

public class EntityGuard extends EntityToroNpc implements IRangedAttackMob
{
	public String playerGuard = "";
    protected double randPosX;
    protected double randPosY;
    protected double randPosZ;
	public Integer raidX = null;
	public Integer raidY = null;
	public Integer raidZ = null;
	protected float strafeVer = 0F;
	protected float strafeHor = 0F;
    protected int stance = rand.nextInt(8)+3;
	protected int blockingTimer = 0;
	protected int lastTargetY = 0;
	public boolean canShieldPush = true;
	protected boolean postReady = true;
	protected boolean blocking = false;
	protected boolean hitSafety = true;
	public boolean returnToPost = false;
	public boolean spawnedNearBandits = false;
	public boolean searchNextEnemy = true;
	public static boolean wildernessGuardSpeak = true;
	protected CivilizationType c = null;
	protected final AIArcher<EntityGuard> aiArrowAttack = new AIArcher<EntityGuard>(this, 0.5D, 40, 40.0F);
	public final EntityAISmartTempt followNoCiv = new EntityAISmartTempt(this, 0.625D, Items.AIR)
	{
		@Override
		public boolean shouldExecute()
	    {
	        if ( underAttack instanceof EntityPlayer || murderWitness instanceof EntityPlayer || inCombat )
	        {
		        return false;
	        }
	        return super.shouldExecute();
	    }
		
		@Override
		protected boolean isTempting(ItemStack stack)
	    {
			if ( underAttack instanceof EntityPlayer || murderWitness instanceof EntityPlayer || inCombat )
	        {
		        return false;
	        }
	        return true;
	    }
	};

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
	    return EnumCreatureAttribute.ILLAGER;
	}
	
	public static String NAME = "guard";
	static
	{
		if (ToroQuestConfiguration.specificEntityNames)
		{
			NAME = ToroQuestEntities.ENTITY_PREFIX + NAME;
		}
	}
	public static void init(int entityId)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(ToroQuest.MODID, NAME), EntityGuard.class, NAME, entityId, ToroQuest.INSTANCE, 80, 1, true, 0x503526, 0xe0d6b9);
	}

	public static void registerRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, new IRenderFactory<EntityGuard>()
		{
			@Override
			public Render<EntityGuard> createRenderFor(RenderManager manager)
			{
				return new RenderGuard(manager);
			}
		});
	}
	
	public EntityGuard(World worldIn)
	{
		this(worldIn, null);
	}

	public EntityGuard(World worldIn, CivilizationType civ)
	{
		super(worldIn, civ);
		this.setSize(0.6F, 1.9F);
		this.experienceValue = 30;
        this.setCombatTask();
		Arrays.fill(inventoryHandsDropChances, ToroQuestConfiguration.guardHandsDropChance);
		Arrays.fill(inventoryArmorDropChances, ToroQuestConfiguration.guardArmorDropChance);
		((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
		this.setCanPickUpLoot(false);
		this.pledgeAllegianceIfUnaffiliated();
	}
	
	public void setPlayerGuard(String b)
	{
		if ( b == null )
		{
			this.playerGuard = "";
		}
		else
		{
			this.playerGuard = b;
		}
		this.writeEntityToNBT(new NBTTagCompound());
	}
	
	@Override
	public float getEyeHeight()
	{
	    return 1.94F;
	}

	@Override
	public boolean inCombat()
	{
		return this.inCombat;
	}
	
	@Override
	public boolean actionReady()
	{
		return this.actionTimer < 1;
	}
	
	@Override
	public boolean isAnnoyed()
	{
		return this.isAnnoyedTimer > 0;
	}
	
	@Override
	public int actionTimer()
	{
		return this.actionTimer;
	}
	
	@Override
	public void setActionTimer(int n)
	{
		this.actionTimer = n;
	}
	
	@Override
	public void setAnnoyed( EntityPlayer player )
	{
		if ( this.isAnnoyed() )
		{
			this.isAnnoyedTimer = 8;
		}
		else
		{
			this.isAnnoyedTimer = 4;
		}
		this.annoyedAt = player;
	}
	
	@Override
	public boolean isAnnoyedAt( EntityPlayer player )
	{
		return ( this.isAnnoyed() && this.annoyedAt != null && this.annoyedAt == player );
	}
	
	@Override
	public void setUnderAttack( EntityPlayer player )
	{
		this.setAnnoyed( player );
		this.underAttack = player;
		this.underAttackTimer = 16;
	}
	
	@Override
	public void setMurder( EntityPlayer player )
	{
		this.setUnderAttack( player );
		this.murderWitness = player;
		this.murderTimer = 64;
	}
	
	@Override
	public EntityPlayer murderWitness()
	{
		return this.murderWitness;
	}
	
	@Override
	public EntityPlayer underAttack()
	{
		return this.underAttack;
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}
	
	@Override
	public boolean hasHome()
	{
		return ( this.raidX != null && this.raidY != null && this.raidZ != null );
	}
	
	public BlockPos getHomePosition()
    {
		if ( this.raidX != null && this.raidY != null && this.raidZ != null )
		{
			return new BlockPos(this.raidX,this.raidY,this.raidZ);
		}
		else
		{
			return this.getPosition();
		}
    }
	
	//==================================================== NBT ===========================================================

	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		if ( this.raidX != null && this.raidY != null && this.raidZ != null )
		{
			compound.setInteger("raidX", this.raidX);
			compound.setInteger("raidY", this.raidY);
			compound.setInteger("raidZ", this.raidZ);
		}
		if ( this.playerGuard != "" )
		{
			compound.setString("playerGuard", this.playerGuard);
		}
		super.writeEntityToNBT(compound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
	    if ( compound.hasKey("raidX") && compound.hasKey("raidY") && compound.hasKey("raidZ") )
	    {
	    	this.raidX = compound.getInteger("raidX");
	    	this.raidY = compound.getInteger("raidY");
	    	this.raidZ = compound.getInteger("raidZ");
	    }
	    if ( compound.hasKey("playerGuard") )
	    {
	    	this.playerGuard = compound.getString("playerGuard");
	    }
	    super.readEntityFromNBT(compound);
	}
	
	//==================================================== Attributes ===========================================================
	
	@Override
    protected void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.setLeftHanded(false);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ToroQuestConfiguration.guardHealth);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(ToroQuestConfiguration.guardArmor);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(ToroQuestConfiguration.guardArmorToughness);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.395D);
    	this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
    }
	
	//==================================================== AI Tasks ===========================================================

	@SuppressWarnings({ "rawtypes" })
	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(10, new EntityAIOpenDoor(this, false));
        this.tasks.addTask(8, new EntityAISmartTempt(this, 0.65D, Item.getByNameOrId("toroquest:recruitment_papers"))
        {
        	@Override
            public boolean shouldExecute()
            {
        		if ( inCombat || isAnnoyed() || underAttackTimer > 0 || murderTimer > 0 || isBurning() ) 
        		{
        			return false;
        		}
            	return super.shouldExecute();
            }
        });
		this.tasks.addTask(9, new EntityAIAvoidTooClose(this, 0.5D, 0.65D));
        this.tasks.addTask(11, new EntityAIPatrolVillage(this, 0.65D));
        this.tasks.addTask(12, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
        this.tasks.addTask(13, new EntityAILookIdle(this)
        {
        public boolean shouldExecute()
        {
            return world.rand.nextFloat() < 0.02F && getNavigator().noPath();
        }});
        
		this.targetTasks.addTask(0, new EntityAINearestAttackableCivTarget(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableBanditTarget(this));
	}
	
	//==================================================== Living Update ===========================================================
	
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
        
		if ( this.world.isRemote )
		{
			return;
		}
		
       	if ( this.ticksExisted % 100 == 0 )
    	{
       		this.pledgeAllegianceIfUnaffiliated();
       		
       		if ( this.getHealth() >= this.getMaxHealth() )
			{
       			if ( this.getAttackTarget() != null && !this.canEntityBeSeen(this.getAttackTarget())  &&  this.getNavigator().getPathToEntityLiving(this.getAttackTarget()) == null   &&  Math.abs(this.posY-this.getAttackTarget().posY)*2 >= this.getDistance(this.getAttackTarget()) )
				{
					if (this.raidX != null && this.raidZ != null)
			    	{
			    		if ( returnToPost(0) )
			    		{
			    			this.returnToPost = true;
			    		}
			    		else
			    		{
    			    		if ( returnToPost(20) )
    			    		{
    			    			this.returnToPost = true;
    			    		}
			    		}
			    	}
					if ( this.getAttackTarget() instanceof EntityLiving )
					{
						((EntityLiving)this.getAttackTarget()).setAttackTarget(null);
					}
					this.setAttackTarget(null);
					this.searchNextEnemy = true;
				}
       			else if ( this.underAttackTimer < 1 ) 
				{
					this.hitSafety = true;
					BlockPos pos = this.getPosition();
					IBlockState block = world.getBlockState(pos);
					if ( block instanceof BlockLiquid )
					{
				        Vec3d vec3d = RandomPositionGenerator.getLandPos(this, 16, 8);
				        if ( vec3d != null )
				        {
				        	this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.5);
				        }
					}
				}
			}
       		else this.heal(1.0f);
    		
	        this.setSprinting(false);
			this.postReady = true;

    		if ( this.isAnnoyed() )
    		{
    			this.isAnnoyedTimer--;
    		}
    		else
    		{
    			this.annoyedAt = null;
    		}
    		
    		if ( this.underAttackTimer > 0 )
    		{		
    			if ( --this.underAttackTimer < 1 )
    			{
    				this.underAttack = null;
    				
    				// guards will drop aggro on players if not attacked for some time
    				if ( this.getAttackTarget() instanceof EntityPlayer && this.murderWitness() != this.getAttackTarget() )
    				{
    					this.blocking = false;
    					this.blockingTimer = -200;
    			    	this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
    		        	this.canShieldPush = true;
    					this.resetActiveHand();
    					this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 0.8F + rand.nextFloat()/5.0F );
    					
    					if ( this.getCivilization() != null && this.getDistance(this.getAttackTarget()) <= 12 && PlayerCivilizationCapabilityImpl.get((EntityPlayer)this.getAttackTarget()).getReputation(this.getCivilization()) > -50 ) this.chat((EntityPlayer) this.getAttackTarget(), "dropplayeraggro", "House " + this.getCivilization().getLocalizedName());
    					
    					this.strafeVer = 0.0F;
    					this.strafeHor = 0.0F;
    			    	this.getMoveHelper().strafe( 0.0F, 0.0F );
    			    	this.getNavigator().clearPath();
    			    	if ( this.raidX != null && this.raidY != null && this.raidZ != null )
    			    	{
    			    		if ( this.returnToPost(0) )
    			    		{
    			    			this.returnToPost = true;
    			    		}
    			    		else if ( this.returnToPost(20) )
    			    		{
    			    			this.returnToPost = true;
    			    		}
    			    	}
    			    	this.isAnnoyedTimer = 0;
    					this.setAttackTarget(null);
    				}
    			}
    		}
    		
    		if ( this.murderTimer > 0 && rand.nextBoolean() )
    		{
    			if ( --this.murderTimer < 1 )
    			{
    				this.murderWitness = null;
    			}
    		}
    		
    		if ( !this.inCombat )
    		{
    			ItemStack iStack = this.getHeldItemMainhand();
    			
    			if ( !this.actionReady() )
    			{
    				this.actionTimer--;
    			}
    			
    			if ( this.getAttackTarget() == null )
    			{
	    			if ( this.lastTargetY < 4 && iStack != null && (iStack.getItem() instanceof ItemBow) )
					{
						this.resetActiveHand();
			        	this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.8F + rand.nextFloat()/5.0F );
			        	if ( !this.world.isRemote )
						{
			        		this.setMeleeWeapon();
			        		if ( this.world.canSeeSky(this.getPosition()) && this.world.getWorldTime() >= 12500 && this.world.getWorldTime() <= 23500 )
			        		{
			        			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Blocks.TORCH, 1));
			        		}
						}
						this.blockingTimer = 0;
					}
	    			else if ( this.world.canSeeSky(this.getPosition()) && !(iStack.getItem() instanceof ItemBow) && this.world.getWorldTime() >= 12500 && this.world.getWorldTime() <= 23500 )
	        		{
	    				if ( !(iStack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) )
	    				{
							this.resetActiveHand();
				        	this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.8F + rand.nextFloat()/5.0F );
				        	if ( !this.world.isRemote )
				        	{
				        		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Blocks.TORCH, 1));
				        	}
							this.blockingTimer = 0;
	    				}
	        		}
	    			else if ( iStack.getItem() == Item.getItemFromBlock(Blocks.TORCH) )
	    			{
						this.resetActiveHand();
			        	this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.8F + rand.nextFloat()/5.0F );
			        	if ( !this.world.isRemote )
			        	{
			        		this.setMeleeWeapon();
			        	}
	    			}
    				this.canShieldPush = true;
    			}
	    		
	    		if ( this.underAttackTimer < 1 && rand.nextInt(5) == 0 && this.raidX != null && this.raidY != null && this.raidZ != null && this.murderTimer <= 0 && !this.isAnnoyed() ) 
	    		{
	    			BlockPos pos = new BlockPos( this.raidX, this.raidY, this.raidZ );
	        		if ( this.getNavigator().tryMoveToXYZ(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, 0.45D) )
	        		{
	        			this.returnToPost = true;
	        		}
	        		else if ( this.returnToPost(0) )
	        		{
	        			this.returnToPost = true;
	        		}
	        		else if ( this.returnToPost(25) )
        			{
	        			this.returnToPost = true;
        			}
	    		}
    		}
    		else if ( this.getAttackTarget() != null )
    		{
	    		this.callForHelp( this.getAttackTarget() );
    		}
    	}
       	
		if ( this.getAttackTarget() != null && this.getAttackTarget().isEntityAlive() && !(this.getAttackTarget() instanceof EntityToroNpc) && !(this.getAttackTarget() instanceof EntityVillager) && ( !(this.getAttackTarget() instanceof EntityGolem) || this.getAttackTarget() instanceof EntityConstruct ) )
		{
			List<EntityArrow> arrows = this.world.getEntitiesWithinAABB(EntityArrow.class, new AxisAlignedBB(this.getPosition()).grow(8, 8, 8), new Predicate<EntityArrow>()
			{
				public boolean apply(@Nullable EntityArrow entity)
				{
					if ( entity.lastTickPosX == 0 && entity.shootingEntity == getAttackTarget() )
					{
						return true;
					}
					return false;
				}
			});
			
	        double dist = this.getDistanceSq(this.getAttackTarget());
			
			if ( !arrows.isEmpty() )
			{
				this.stance = rand.nextInt(6)+5;
				if ( dist <= 12 )
				{
					this.blockingTimer = 25;
				}
				else
				{
					this.blockingTimer = 50;
				}
				this.blocking = true;
				this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	        	this.canShieldPush = true;
				this.resetActiveHand();
				this.setActiveHand(EnumHand.OFF_HAND);
				this.updateActiveHand();
				this.strafeHor = getStrafe(stance);
				this.strafeVer = 0.4F;
			}
			
	        this.lastTargetY = (int)(Math.abs(this.posY - this.getAttackTarget().posY)+0.5D);
	        ItemStack iStack = this.getHeldItemMainhand();
	        if ( !this.inCombat )
	        {
				this.getMoveHelper().strafe( 0.0F, 0.0F );
	        	this.getNavigator().clearPath();
	        	this.canShieldPush = true;
				this.resetActiveHand();
				this.inCombat = true;
				this.stance = rand.nextInt(6)+5;
				this.strafeHor = getStrafe(stance);
	        	if ( this.onGround && this.getNavigator().getPathToEntityLiving(this.getAttackTarget()) == null )
				{
	        		this.blockingTimer = -200;
	        	}
	        	else
				{
					this.getMoveHelper().strafe( 0.0F, 0.0F );
					this.getNavigator().clearPath();
				}
	        }
	        // if within range and has not been in melee range for a short amount of time, or very close and has not been in melee range for a long amount of time
			if (  ( ( dist < 200+this.blockingTimer ) || ( this.lastTargetY < 4 && dist <= 20 && this.canEntityBeSeen(this.getAttackTarget())) ) )
	        {
				// if this does not have a sword, swap to sword and board
				if ( iStack != null && ( iStack.getItem() instanceof ItemBow || iStack.getItem() == Item.getItemFromBlock(Blocks.TORCH) ) ) // SSS
				{
		        	this.canShieldPush = true;
					this.resetActiveHand();
					this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 0.9F + rand.nextFloat()/10.0F );
					this.getMoveHelper().strafe( 0.0F, 0.0F );
	            	this.getNavigator().clearPath();
	            	if ( !this.world.isRemote )
					{
	            		setMeleeWeapon();
					}
					this.blockingTimer = 0;
					this.strafeHor = getStrafe(stance);
				}
				
				// if this is not blocking, is within range, and block is ready, start blocking
				if ( !this.blocking && dist <= 12 && this.blockingTimer <= -((int)(this.stance*5+dist+20)) && this.getRevengeTarget() != null && this.getRevengeTarget().isEntityAlive() )
				{
					this.stance = rand.nextInt(8)+3;
					this.strafeHor = getStrafe(stance);
					this.blockingTimer = (int)MathHelper.clamp((rand.nextInt(70)+20-dist), 20, 80);
					this.blocking = true;
					this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		        	this.canShieldPush = true;
					this.resetActiveHand();
					this.setActiveHand(EnumHand.OFF_HAND);
					this.updateActiveHand();
					if ( dist <= 6 )
					{
						this.strafeVer = 0.2F;
					}
					else
					{
						this.strafeVer = 0.4F;
					}
				}
				else if ( this.blocking && this.blockingTimer % 16 == 0 )
				{
		        	this.canShieldPush = true;

					if ( dist <= 3 )
					{
						this.strafeVer = 0.2F;
					}
					else
					{
						this.strafeVer = 0.4F;
					}
				}
				
				// if this is blocking and should no longer block, stop blocking
				if ( this.blocking && this.blockingTimer <= 0 )
				{
					this.blocking = false;
					this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
					this.stance = rand.nextInt(8)+3;
					this.strafeHor = getStrafe(stance);
		        	this.canShieldPush = true;
					this.resetActiveHand();
				}
				// otherwise, if this is in melee range, strafe
				else if ( !this.blocking && dist <= 64 )
				{
					if ( this.blockingTimer == -12 || this.blockingTimer == -32 || ( this.blockingTimer < -32 && this.blockingTimer % 14 == 0 ) )
					{
						if ( rand.nextInt(3) == 0 )
						{
							this.stance = rand.nextInt(8)+3;
							this.strafeHor = getStrafe(stance);
						}
					}
				}
				
				if ( !this.blocking )
				{
					float strafeMod = 1.0F;
					
					if ( this.stance < 5 )
					{
						this.strafeVer = 0.4F;

						if ( dist <= 25 )
						{
							if ( !this.world.isRemote && this.onGround )
							{
								Vec3d velocityVector = new Vec3d(this.posX - this.getAttackTarget().posX, 0, this.posZ - this.getAttackTarget().posZ);
								double push = (12.0D+dist*dist);
								this.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
			                	this.velocityChanged = true;
							}
						}
						else
						{
							this.stance = rand.nextInt(6)+5;
							this.strafeHor = getStrafe(stance);
						}
					}
					else if ( dist <= 2.5 )
					{
						this.strafeVer = 0.4F;
					}
					else if ( dist <= 7 )
					{
						this.strafeVer = 0.7F;
						strafeMod = 0.9F;
					}
					else if ( dist <= 12 )
					{
						this.strafeVer = 0.8F;
						strafeMod = 0.8F;
					}
					else
					{
						this.strafeVer = 0.9F;
						strafeMod = 0.7F;
					}
								
					if ( this.getNavigator().tryMoveToEntityLiving(this.getAttackTarget(), this.strafeVer) )
					{						
						if ( dist >= 12 ) // if this is too far away and blocking, stop blocking faster
						{
							this.blockingTimer--;
						}
						else if ( dist <= 3 )
						{
							if ( !this.world.isRemote && this.onGround && !this.isSprinting() )
							{
								Vec3d velocityVector = new Vec3d(this.posX - this.getAttackTarget().posX, 0, this.posZ - this.getAttackTarget().posZ);
								double push = (1.0D+dist*dist);
								this.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
			                	this.velocityChanged = true;
							}
						}
						
						if ( this.posY + 1.5D < this.getAttackTarget().posY )
						{
							this.getMoveHelper().strafe( this.strafeVer, 0.0F );
						}
						else
						{
							this.getMoveHelper().strafe( this.strafeVer, this.strafeHor*strafeMod );
						}
					}
					else
					{
						if ( this.posY + 1.5D < this.getAttackTarget().posY )
						{
							this.getMoveHelper().strafe( this.strafeVer*0.5F, 0.0F );
						}
						else
						{
							this.getMoveHelper().strafe( this.strafeVer*0.5F, this.strafeHor*0.5F*strafeMod );
						}
					}
				}
				else // is blocking
				{
					if ( this.strafeVer < 0.4F )
					{
						if ( !this.world.isRemote && this.onGround )
						{
							Vec3d velocityVector = new Vec3d(this.posX - this.getAttackTarget().posX, 0, this.posZ - this.getAttackTarget().posZ);
							double push = (1.0D+dist*dist);
							this.addVelocity((velocityVector.x)/push, 0.0D, (velocityVector.z)/push);
		                	this.velocityChanged = true;
						}
					}
					else if ( this.strafeVer > 0.4F )
					{
						this.strafeVer = 0.4F;
					}
					
					if ( this.getNavigator().tryMoveToEntityLiving(this.getAttackTarget(), this.strafeVer) )
					{
						this.getMoveHelper().strafe( this.strafeVer, this.strafeHor*1.25F);
					}
					else
					{
						this.getMoveHelper().strafe( this.strafeVer*0.5F, this.strafeHor*0.5F );
					}
				}
				
	        }
			else if ( iStack != null && !(iStack.getItem() instanceof ItemBow) )
			{
				if ( ToroQuestConfiguration.mobsAttackGuards && this.getAttackTarget() instanceof EntityLiving )
				{
					EntityLiving v = (EntityLiving)this.getAttackTarget();
					if ( v.getAttackTarget() == null )
					{
						v.setAttackTarget(this);
					}
				}
				else if ( this.getAttackTarget() instanceof EntityPlayer )
				{
					this.setAnnoyed((EntityPlayer)this.getAttackTarget());
				}
				this.blocking = false;
				this.blockingTimer = -200;
		    	this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
	        	this.canShieldPush = true;
				this.resetActiveHand();
				this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 0.9F + rand.nextFloat()/5.0F );
				
				if ( !this.world.isRemote )
				{
					this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW, 1));
					this.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
				}
				
				this.strafeVer = 0.0F;
				this.strafeHor = 0.0F;
		    	this.getMoveHelper().strafe( 0.0F, 0.0F );
		    	this.getNavigator().clearPath();
			}
			this.blockingTimer--;
		}
		else if ( this.blocking || this.inCombat ) // end of combat
		{
			this.inCombat = false;
			this.blocking = false;
			if (this.raidX != null && this.raidZ != null && this.getDistance(this.raidX, this.posY, this.raidZ) > 20)
	    	{
	    		if ( returnToPost(0) )
	    		{
	    			this.returnToPost = true;
	    		}
	    		else
	    		{
		    		if ( returnToPost(20) )
		    		{
		    			this.returnToPost = true;
		    		}
	    		}
	    	}
			this.setAttackTarget(null);
			this.searchNextEnemy = true;
        	this.canShieldPush = true;
			this.resetActiveHand();
			this.activeItemStackUseCount = 0;
	    	this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
	    	this.strafeHor = 0F;
	    	this.strafeHor = 0F;
	    	this.getMoveHelper().strafe( 0.0F, 0.0F );
	    	this.getNavigator().clearPath();
		}
	}
	
	//==================================================== Strafe ===========================================================

	public float getStrafe(int stance)
	{
		switch ( stance )
		{
			case 3:
			{
				return -0.8F;
			}
			case 4:
			{
				return 0.8F;
			}
			case 5:
			{
				return -0.3F;
			}
			case 6:
			{
				return 0.3F;
			}
			case 7:
			{
				return -0.295F;
			}
			case 8:
			{
				return 0.295F;
			}
			case 9:
			{
				return -0.29F;
			}
			case 10:
			{
				return 0.29F;
			}
		}
		return 0.0F;
	}
	
	//==================================================== Return To Post ===========================================================

	public boolean returnToPost( int range )
	{
		if ( this.raidX == null || this.raidZ == null )
		{
			return false;
		}
		
		BlockPos start = this.getPosition();
		int x = 0;
		int z = 0;
		
		if ( range > 0 )
		{			
			x = start.getX() - this.raidX + ( rand.nextInt(5) * (rand.nextInt(2)*2 - 1) );
			z = start.getZ() - this.raidZ + ( rand.nextInt(5) * (rand.nextInt(2)*2 - 1) );
			
			int signX = -1;
			int signZ = -1;
			
			if ( x < 0 )
			{
				signX = 1;
			}
			if ( z < 0 )
			{
				signZ = 1;
			}
			
			x = Math.abs(x);
			z = Math.abs(z);
			
			double ratioX = (double)(x)/(double)(1+x+z);
			double ratioZ = (double)(z)/(double)(1+x+z);
			
			x = (int)(ratioX * range);
			z = (int)(ratioZ * range);
					
			x = MathHelper.clamp(x, 0, range ) * signX;
			z = MathHelper.clamp(z, 0, range ) * signZ;
		}
		
		BlockPos pos = EntityAIRaid.findValidSurface(this.world, new BlockPos(start.getX()+x, start.getY(), start.getZ()+z), 8);
		if ( pos == null )
		{
			return false;
		}
		return this.getNavigator().tryMoveToXYZ(((int)pos.getX())+0.5, pos.getY(), ((int)pos.getZ())+0.5, 0.6D);
	}

	//==================================================== Take Damage ===========================================================
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if ( this.world.isRemote )
        {
            return false;
        }
		
		Entity e = source.getTrueSource();
		
		if (source.getTrueSource() == this)
        {
            amount = 0.0F;
        }
		
		if ( source == DamageSource.IN_WALL || source == DamageSource.CRAMMING ) 
		{
			return false;
		}
		
		if ( source == DamageSource.FALL )
		{
			amount = amount/2.0F;
			if ( amount <= 1 )
			{
				return false;
			}
			return super.attackEntityFrom(source, amount);
		}
		
		if ( e == null )
		{
			if ( this.rand.nextBoolean() )
			{
				BlockPos pos = this.getPosition();
				IBlockState block = world.getBlockState(pos);
				if ( block == Blocks.LAVA.getDefaultState() || block.getBlock() == Blocks.LAVA )
				{
					if ( this.dimension == 0 )
					{
						this.swingArm(EnumHand.MAIN_HAND);
						if ( this.world.isRemote )
						{
							this.addVelocity(0, 0.25, 0);
							this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.WATER_BUCKET, 1));
						}
						this.world.setBlockState(pos, Blocks.WATER.getDefaultState());
					}
			        Vec3d vec3d = RandomPositionGenerator.getLandPos(this, 8, 4);
		            if ( vec3d != null )
		            {
				        this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.5D);
		            }
				}
				else if ( block.getBlock() instanceof BlockFire )
				{
					this.swingArm(EnumHand.MAIN_HAND);
					this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
					world.setBlockToAir(pos);
				}
				else if ( this.rand.nextBoolean() )
				{
					this.extinguish();
				}
				else
				{
					Vec3d vec3d = RandomPositionGenerator.getLandPos(this, 8, 4);
		            if ( vec3d != null )
		            {
				        this.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 0.5D);
		            }
				}
			}
			if ( source.isFireDamage() || source.isExplosion() || source.isMagicDamage() || source.isProjectile() )
			{
				return super.attackEntityFrom(source, amount);
			}
			return false;
		}

		if ( e instanceof EntityToroNpc || e instanceof EntityVillager )
		{
			return false;
		}
		
		if ( e instanceof EntityPlayer )
		{
			if ( this.hitSafety || e.getName().equals(this.playerGuard) ) // !this.playerGuard.equals("")
			{
				this.hitSafety = false;
				this.playSound(SoundEvents.BLOCK_CLOTH_BREAK, 1.0F, 1.0F);
				return false;
			}
			
			int entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.getPosition()).grow(3, 3, 3), new Predicate<EntityLivingBase>()
			{
				public boolean apply(@Nullable EntityLivingBase entity)
				{
					if ( entity instanceof IMob || entity instanceof EntityMob )
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}).size();
			
			if ( entities > 0 && this.getAttackTarget() != e )
			{
				return false;
			}
		}
		
		if ( this.blocking && canBlockDamageSource(source) )
		{
			
			if ( this.blockingTimer > 10 && rand.nextBoolean() )
			{
				this.blockingTimer = 10;
			}
			
			double dist = e.getDistanceSq(this);
			
			if ( !source.isProjectile() && !source.isMagicDamage() && !source.isFireDamage() )
			{
				if ( e instanceof EntityLivingBase )
				{
					if ( amount >= 5.0F && ( ((EntityLivingBase)e).getHeldItemMainhand().getItem() instanceof ItemAxe || ((EntityLivingBase)e).getHeldItemMainhand().getItem().getRegistryName().toString().contains("halberd") || ((EntityLivingBase)e).getHeldItemMainhand().getItem().getRegistryName().toString().contains("battleaxe") ) )
					{
						this.resetActiveHand();
						if ( this.world.isRemote ) 
						{
							this.handleStatusUpdate((byte)29);
							this.handleStatusUpdate((byte)30);
						}
						if ( dist < 16 )
						{
							this.canShieldPush = true;
							Vec3d velocityVector = new Vec3d(this.posX - e.posX, 0, this.posZ - e.posZ);
							if ( !this.world.isRemote )
							{	
								this.addVelocity((velocityVector.x)/( dist+1 )*MathHelper.clamp(amount, 0.0D, 1.2D), (0.22D-MathHelper.clamp(dist/100.0, 0.0D, 0.16D))*MathHelper.clamp(amount, 0.0D, 1.0D), (velocityVector.z)/( dist+1 )*MathHelper.clamp(amount, 0.0D, 1.2D));
			                	this.velocityChanged = true;
							}
						}
						this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + rand.nextFloat()/5.0F);
						this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0F, 0.8F + rand.nextFloat()/5.0F);
						this.blockingTimer = 50;
						return (super.attackEntityFrom(source, amount/2.0F));
					}
					else
					{
						this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + rand.nextFloat()/5.0F);
						if ( dist < 16 )
						{
							if ( this.canShieldPush )
							{
								this.canShieldPush = false;
								Vec3d velocityVector = new Vec3d(e.posX - this.posX, 0, e.posZ - this.posZ);
								if ( !this.world.isRemote )
								{	
									e.addVelocity((velocityVector.x)/( dist+1 )*MathHelper.clamp(amount, 0.0D, 1.2D), (0.22D-MathHelper.clamp(dist/100.0, 0.0D, 0.16D))*MathHelper.clamp(amount, 0.0D, 1.0D), (velocityVector.z)/( dist+1 )*MathHelper.clamp(amount, 0.0D, 1.2D));
				                	e.velocityChanged = true;
								}
							}
							else
							{								
								Vec3d velocityVector = new Vec3d(e.posX - this.posX, 0, e.posZ - this.posZ);
								if ( !this.world.isRemote )
								{	
									e.addVelocity((velocityVector.x)/( dist+8 )*MathHelper.clamp(amount, 0.0D, 1.0D), 0, (velocityVector.z)/( dist+8 )*MathHelper.clamp(amount, 0.0D, 1.0D));
				                	e.velocityChanged = true;
								}
							}
						}
						this.handleStatusUpdate((byte)29);
					}
					return false;
				}
			}
			else if ( source.isProjectile() )
			{
				this.blockingTimer = 8;
				if ( this.world.isRemote ) 
				{
					this.handleStatusUpdate((byte)29);
				}
				this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + rand.nextFloat()/5.0F);
				return false;
			}
			if ( this.world.isRemote ) 
			{
				this.handleStatusUpdate((byte)29);
			}
			this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + rand.nextFloat()/5.0F);
			return false;
		}
		
		if ( e instanceof EntityLivingBase ) 
		{
			if ( rand.nextInt(3) == 0 || this.getAttackTarget() == null )
			{
				this.setAttackTarget((EntityLivingBase)e);
			}
			this.setRevengeTarget((EntityLivingBase)e);
			this.callForHelp((EntityLivingBase)e);
		}
		
		if ( e instanceof EntityPlayer && super.attackEntityFrom(source, amount) )
		{
			adjustRep(e,-(int)MathHelper.clamp(amount*4,5,this.getHealth()*4));
			this.setUnderAttack((EntityPlayer)e);
			return true;
		}
		
		return super.attackEntityFrom(source, amount);
	}
	
	private boolean canBlockDamageSource(DamageSource damageSourceIn)
	{
        if (!damageSourceIn.isUnblockable() && this.isActiveItemStackBlocking())
        {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null)
            {
                Vec3d vec3d1 = this.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(this.posX, this.posY, this.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D)
                {
                    return true;
                }
            }
        }

        return false;
    }
	
	//==================================================== Attack Target ===========================================================
	
	@Override
	public void setAttackTarget( EntityLivingBase e )
	{
		if ( e instanceof EntityPlayer )
		{
			if ( this.isAnnoyedTimer < 4 )
			{
				return;
			}
			
			if ( !this.isAnnoyedAt((EntityPlayer)e) )
			{
				return;
			}
			
			if ( e.getName().equals(this.playerGuard) )
			{
				return;
			}
			
			this.underAttack = (EntityPlayer)e;
			if ( this.underAttackTimer < 2 ) this.underAttackTimer = 2 + rand.nextInt(3);
		}
		super.setAttackTarget(e);
	}
	
	//==================================================== Adjust Reputation ===========================================================
	
	private void adjustRep(Entity entity, int amount)
	{
		if (!(entity instanceof EntityPlayer))
		{
			return;
		}
		
		EntityPlayer player = (EntityPlayer) entity;
		
		CivilizationType civ = getCivilization();
		
		if (civ == null)
		{
			return;
		}
		CivilizationHandlers.adjustPlayerRep(player, civ, amount);
	}

	//==================================================== Civilization ===========================================================

	@Override
	public void setCivilization(CivilizationType civ)
	{
		if (civ == null)
		{
			dataManager.set(CIV, "");
		}
		else
		{
			dataManager.set(CIV, civ.toString());
			c = civ;
			this.tasks.removeTask(followNoCiv);
		}
		dataManager.setDirty(CIV);
	}
	
	public void setCivilizationCaravan(CivilizationType civ)
	{
		if (civ == null)
		{
			dataManager.set(CIV, "");
		}
		else
		{
			dataManager.set(CIV, civ.toString());
			c = civ;
		}
		dataManager.setDirty(CIV);
	}
	
	@Override
	protected void pledgeAllegianceIfUnaffiliated()
	{
		if ( this.getCivilization() != null )
		{
			return;
		}
		
		if ( this.world == null )
		{
			return;
		}

		Province civ = CivilizationUtil.getProvinceAt(this.world, this.chunkCoordX, this.chunkCoordZ);

		if ( civ == null || civ.civilization == null )
		{
			return;
		}
		
		if ( !this.playerGuard.equals("") )
		{
			for ( EntityPlayer player : this.world.playerEntities )
			{
				try
				{
					if ( player.getName().equals(this.playerGuard) )
					{
						this.recruitGuardReputation(player, civ);
					}
				}
				catch ( Exception e )
				{
					
				}
			}
			this.setPlayerGuard(null);
		}
		else
		{
			List<EntityPlayer> players = this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.getPosition()).grow(20, 12, 20), new Predicate<EntityPlayer>()
			{
				public boolean apply(@Nullable EntityPlayer entity)
				{
					return true;
				}
			});
			
			for ( EntityPlayer player : players )
			{
				try
				{
					this.recruitGuardReputation(player, civ);
				}
				catch ( Exception e )
				{
					
				}
			}
			this.setPlayerGuard(null);
		}
		
		this.setCivilization(civ.getCiv());
		
		if ( !this.world.isRemote )
		{
    		setMeleeWeapon();
		}
	}
	
	private void recruitGuardReputation( EntityPlayer player, Province civ ) // TODO
	{
		CivilizationHandlers.adjustPlayerRep(player, civ.getCiv(), ToroQuestConfiguration.recruitGuardRepGain);
		QuestRecruit.INSTANCE.onRecruit(player, civ);
		this.chat(player, "civvillagerrecruit", civ.getCiv().getDisplayName());
		player.sendStatusMessage( new TextComponentString("Guard Recruited!"), true);
	}
	
	@Override
	public CivilizationType getCivilization()
	{
		if (c != null)
		{
			return c;
		}
		return enumCiv(dataManager.get(CIV));
	}
		
	//==================================================== Initial Spawn ===========================================================

	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
		this.setCanPickUpLoot(false);
		this.setLeftHanded(false);
		this.pledgeAllegianceIfUnaffiliated();
		if ( !this.world.isRemote )
		{
			setMeleeWeapon();
			if ( ToroQuestConfiguration.guardsHaveArmorForSpartanWeaponry )
			{
				setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET, 1));
				setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE, 1));
				setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS, 1));
				setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS, 1));
			}
		}
		this.inCombat = false;
		this.blocking = false;
		this.blockingTimer = 0;
		this.setAttackTarget(null);
    	this.canShieldPush = true;
		this.resetActiveHand();
		this.setActiveHand(EnumHand.MAIN_HAND);
		this.activeItemStackUseCount = 0;
    	this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
    	this.strafeHor = 0F;
    	this.strafeHor = 0F;
    	this.getMoveHelper().strafe( 0.0F, 0.0F );
    	this.getNavigator().clearPath();
        this.raidX = (int)this.posX;
		this.raidY = (int)this.posY;
		this.raidZ = (int)this.posZ;
		CivilizationType civ = this.getCivilization();
        if ( civ == null || ( civ != null && civ.toString() == "" ) )
        {
        	this.tasks.addTask(9, followNoCiv);
        }
		this.writeEntityToNBT(new NBTTagCompound());
		return livingdata;
	}
	
	//==================================================== Set Weapon ===========================================================

	public void setMeleeWeapon()
	{
		CivilizationType civ = this.getCivilization();
		if ( civ == null )
		{
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD, 1));
			ItemStack istack = new ItemStack(Item.getByNameOrId("spartanshields:shield_tower_wood"));
			if ( istack != null && !istack.isEmpty() )
			{
	    		this.setHeldItem(EnumHand.OFF_HAND, istack);
			}
			else
			{
				this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.SHIELD, 1));
			}
    		return;
		}
		switch ( civ )
		{
			case FIRE:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_RED_BRIAR), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_RED_BRIAR), 1));
	    		return;
			}
			case EARTH:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_GREEN_WILD), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_GREEN_WILD), 1));
	    		return;
			}
			case SUN:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_YELLOW_DAWN), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_YELLOW_DAWN), 1));
	    		return;
			}
			case WIND:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_BROWN_MITHRIL), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_BROWN_MITHRIL), 1));
	    		return;
			}
			case MOON:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_BLACK_MOOR), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_BLACK_MOOR), 1));
	    		return;
			}
			case WATER:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardWeapon_BLUE_GLACIER), 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Item.getByNameOrId(ToroQuestConfiguration.guardShield_BLUE_GLACIER), 1));
	    		return;
			}
			default:
			{
				this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.IRON_SWORD, 1));
	    		this.setHeldItem(EnumHand.OFF_HAND, new ItemStack(Items.SHIELD, 1));
	    		return;
			}
		}
	}
	
	//==================================================== Find Surface ===========================================================

	public static BlockPos findSpawnSurface( World world, BlockPos pos, int yOffset )
	{
		IBlockState blockState;
		boolean airspace = false;
		boolean floor =  false;
		while ( yOffset > 0 )
		{
			blockState = world.getBlockState(pos);
			if ( blockState instanceof BlockLiquid )
			{
				return null;
			}
			if ( blockState.getBlock() instanceof BlockAir )
			{
				if ( floor )
				{
					if ( airspace )
					{
						return pos.up();
					}
					else
					{
						airspace = true;
					}
				}
			}
			else
			{
				floor = true;
				airspace = false;
			}
			pos = pos.up();
			yOffset--;
		}
		return null;
	}
	
	//==================================================== Tame Effect ===========================================================

	public void playTameEffect(boolean play)
    {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        if (!play)
        {
            enumparticletypes = EnumParticleTypes.CLOUD;
        }

        for (int i = 0; i < 7; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
	
	//==================================================== Interact ===========================================================

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		if ( !this.isEntityAlive() || player == null )
		{
			return false;
		}
		
		CivilizationType civ = this.getCivilization();
		
		int rep = 0;
		
		if ( civ != null )
		{
			rep = PlayerCivilizationCapabilityImpl.get(player).getReputation(this.getCivilization());
		
			if ( rep <= -100 || this.murderWitness() == player )
			{
				if ( this.actionReady() )
				{
					this.insult(player);
					this.actionTimer = 5;
				}
				return false;
			}
			
			if ( player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemLead )
			{
				List<EntityFugitive> fugitives = player.world.getEntitiesWithinAABB(EntityFugitive.class, new AxisAlignedBB(player.getPosition()).grow(8, 4, 8), new Predicate<EntityFugitive>()
				{
					public boolean apply(@Nullable EntityFugitive entity)
					{
						return true;
					}
				});
				
				// FUGITIVE
				for (EntityFugitive v : fugitives)
				{
					if ( !this.inCombat && v != null && v.isEntityAlive() && v.getLeashHolder() != null && ( this.murderWitness == null || this.murderWitness != player ) && ( this.underAttack == null || this.underAttack != player ) )
					{
						
						if ( !this.world.isRemote ) 
						{
				            try
				            {
				            	QuestCaptureFugitives.INSTANCE.onReturn(player);
				            	this.chat(player, "fugitive", null);
				    	        this.playSound( SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F );
				    	        this.playSound( SoundEvents.BLOCK_ANVIL_LAND, 0.8F, 0.8F );
				    	        this.playSound( SoundEvents.ENTITY_VILLAGER_NO, 0.8F, 0.8F );
				    	        v.setDead();
				    			v.setHealth(0);
					            CivilizationHandlers.adjustPlayerRep(player, (int)(player.posX / 16), (int)(player.posZ / 16), ToroQuestConfiguration.returnFugitiveRepGain);
					            this.actionTimer = 5;
					            ItemStack itemstack = new ItemStack(Items.LEAD, 1);
								itemstack.setStackDisplayName( I18n.format("item.fugitive_bindings.name") );
								player.setHeldItem(EnumHand.MAIN_HAND, itemstack);
					            return true;
				            }
				            catch(Exception e)
				            {
				            	
				            }
						}
					}
				}
				
				List<EntitySheep> toros = player.world.getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(player.getPosition()).grow(8, 4, 8), new Predicate<EntitySheep>()
				{
					public boolean apply(@Nullable EntitySheep entity)
					{
						return true;
					}
				});
				
				// SHEEP
				for (EntitySheep v : toros)
				{
					if ( !this.inCombat && v != null && v.isEntityAlive() && v.getLeashHolder() != null && ( this.murderWitness == null || this.murderWitness != player ) && ( this.underAttack == null || this.underAttack != player ) )
					{
						boolean flag = false;
						for ( String t : v.getTags() )
						{
							if ( t.equals("capture_quest") )
							{
								flag = true;
							}
						}
						if ( flag && !this.world.isRemote ) 
						{
				            try
				            {
				            	if ( QuestCaptureEntity.INSTANCE.onReturn(player) )
				            	{
				            		this.chat(player, "returnsheep", null);
									v.setDead();
									v.setHealth(0);
									this.playSound( SoundEvents.BLOCK_ANVIL_LAND, 0.8F, 0.8F );
							        this.playSound( SoundEvents.ENTITY_SHEEP_AMBIENT, 0.8F, 0.8F );
						            CivilizationHandlers.adjustPlayerRep(player, (int)(player.posX / 16), (int)(player.posZ / 16), ToroQuestConfiguration.returnFugitiveRepGain);
						            this.actionTimer = 5;
						            ItemStack itemstack = new ItemStack(Items.LEAD, 1);
									itemstack.setStackDisplayName( I18n.format("item.sheep_bindings.name") );
									player.setHeldItem(EnumHand.MAIN_HAND, itemstack);
							        return true;
				            	}
				            }
				            catch(Exception e)
				            {
				            	
				            }
						}
					}
				}
			}
		}
		
		// RECRUITMENT_PAPERS
		if ( !this.world.isRemote ) 
		{
			try
			{
				ItemStack itemstack = player.getHeldItem(hand);
				Item item = itemstack.getItem();
							
				if ( item.equals(Item.getByNameOrId("toroquest:recruitment_papers") ) && this.postReady )
		        {
	    			this.postReady = false;
		        	if ( !this.inCombat && this.isFriendly(player, rep) )
		    		{
		        		if ( player.isSneaking() )
		        		{
							BlockPos pos = findSpawnSurface(world, this.getPosition().up(), 32);
							if ( pos != null )
							{
								this.setPositionAndUpdate(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5);
								this.raidX = pos.getX();
								this.raidY = pos.getY();
								this.raidZ = pos.getZ();
								this.writeEntityToNBT(new NBTTagCompound());
					        	this.playSound(SoundEvents.BLOCK_DISPENSER_LAUNCH, 1.0F, 1.0F);
			        			this.playTameEffect(false);
			                    this.world.setEntityState(this, (byte)6);
								player.sendStatusMessage(new TextComponentString( "§oGuard posted at [" + this.raidX + ", " + this.raidY + ", " + this.raidZ + "]§r" ), true);
					        	this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
							}
							else
							{
								player.sendStatusMessage(new TextComponentString( "§oInvalid post location!§r" ), true);
								player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_BASS, SoundCategory.AMBIENT, 3.0F, 0.6F);
							}
						}
		        		else
		        		{
		        			this.raidX = (int)this.posX;
		        			this.raidY = (int)this.posY;
		        			this.raidZ = (int)this.posZ;
							this.writeEntityToNBT(new NBTTagCompound());
							player.sendStatusMessage(new TextComponentString( "§oGuard posted at [" + this.raidX + ", " + this.raidY + ", " + this.raidZ + "]§r" ), true);
				        	this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
		        		}
		    		}
		        	else
		        	{
						player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_NOTE_BASS, SoundCategory.AMBIENT, 3.0F, 0.6F);
		        	}
		        	return true;
		        }
				else if ( item == Items.EMERALD && rep < 0 )
				{
					if ( this.murderWitness == player )
					{
						if ( this.actionReady() )
						{
		            		this.chat(player, "murderer", null);
						}
						this.setAnnoyed( player );
						this.setAttackTarget(player);
					}
					else
					{
						int maxRepGain = -rep;
						int emeraldRep = itemstack.getCount()*ToroQuestConfiguration.donateEmeraldRepGain;
						if ( emeraldRep > maxRepGain )
						{
		            		this.chat(player, "bountyclear", null);
							int remainder = emeraldRep-maxRepGain;
							this.adjustRep( player, maxRepGain );
				        	player.setHeldItem( hand, new ItemStack( item, (int)(remainder/ToroQuestConfiguration.donateEmeraldRepGain) ) );
				        	this.underAttack = null;
				        	this.isAnnoyedTimer = 0;
				        	this.setAttackTarget(null);
						}
						else
						{
		            		this.chat(player, "bounty", null);
		            		this.underAttack = null;
				        	this.isAnnoyedTimer = 0;
				        	this.setAttackTarget(null);
							this.adjustRep( player, emeraldRep );
				        	player.setHeldItem( hand, new ItemStack( Items.AIR, 0 ) );
						}
						player.world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundCategory.AMBIENT, 1.0F, 1.2F);
						this.setAttackTarget(null);
					}
					return true;
				}
			}
			catch ( Exception e )
			{
				
			}
			
			if ( !this.inCombat && this.actionReady() ) // && this.postReady )
			{
				EntityGuard.guardSpeak(this, player);
				return true;
			}
		}
		return false;
	}
	
	//==================================================== Friendly ===========================================================
	
	public boolean isFriendly( EntityPlayer player, int rep )
	{
		return ( rep >= 0 && ( this.murderWitness == null || this.murderWitness != player ) && ( this.underAttack == null || this.underAttack != player ) && !this.isAnnoyed() );
	}
	
	//==================================================== Chat ===========================================================
	
	public void chat( EntityPlayer player, String message, @Nullable String extra )
	{
		if ( ToroQuestConfiguration.guardsHaveDialogue )
		{
			if ( this.getDistance(player) > 12 )
			{
				return;
			}
			
			this.getLookHelper().setLookPositionWithEntity(player, 20.0F, 20.0F);
			this.faceEntity(player, 20.0F, 20.0F);
			
			if ( player.world.isRemote )
			{
				return;
			}
			
			try
			{
				String s = "§l" + this.getName() + "§r: " + (I18n.format("entity.toroquest.guard."+message+rand.nextInt(Integer.parseInt(I18n.format("entity.toroquest.guard."+message)))).replace("@p", player.getDisplayNameString()));
				
				if ( extra != null )
				{
					s = s.replace("@e", extra);
				}
				
				player.sendMessage(new TextComponentString(s));
			}
			catch ( Exception e )
			{
				String s = "§l" + this.getName() + "§r: " + (I18n.format("entity.toroquest.guard."+message).replace("@p", player.getDisplayNameString()));
				
				if ( extra != null )
				{
					s = s.replace("@e", extra);
				}
				
				player.sendMessage(new TextComponentString(s));
			}
			
			this.playSound( SoundEvents.VINDICATION_ILLAGER_AMBIENT, 1.0F, 0.9F + rand.nextFloat()/5.0F );
			this.actionTimer = 5;
		}
	}
	
	@Override
	public void chat( EntityToroNpc guard, EntityPlayer player, String message, @Nullable String extra )
	{
		if ( ToroQuestConfiguration.guardsHaveDialogue )
		{
			if ( guard.getDistance(player) > 12 )
			{
				return;
			}
			
			guard.getLookHelper().setLookPositionWithEntity(player, 20.0F, 20.0F);
			guard.faceEntity(player, 20.0F, 20.0F);
			
			if ( player.world.isRemote )
			{
				return;
			}
			
			try
			{
				String s = "§l" + guard.getName() + "§r: " + (I18n.format("entity.toroquest.guard."+message+guard.world.rand.nextInt(Integer.parseInt(I18n.format("entity.toroquest.guard."+message)))).replace("@p", player.getDisplayNameString()));
				
				if ( extra != null )
				{
					s = s.replace("@e", extra);
				}
				
				player.sendMessage(new TextComponentString(s));
			}
			catch ( Exception e )
			{
				String s = "§l" + this.getName() + "§r: " + (I18n.format("entity.toroquest.guard."+message).replace("@p", player.getDisplayNameString()));
				
				if ( extra != null )
				{
					s = s.replace("@e", extra);
				}
				
				player.sendMessage(new TextComponentString(s));
			}
			
			guard.playSound( SoundEvents.VINDICATION_ILLAGER_AMBIENT, 1.0F, 0.9F + guard.world.rand.nextFloat()/5.0F );
			guard.actionTimer = 5;
		}
	}

	//==================================================== Combat Task ===========================================================

	public void setCombatTask()
	{
	    this.aiArrowAttack.setAttackCooldown(40);
		this.tasks.addTask(6, new AIAttackWithSword(this, 0.65D));
		this.tasks.addTask(7, this.aiArrowAttack);
	    this.inCombat = false;
		this.blocking = false;
		this.blockingTimer = 0;
		this.setAttackTarget(null);
		this.canShieldPush = true;
		this.resetActiveHand();
		this.setActiveHand(EnumHand.MAIN_HAND);
		this.activeItemStackUseCount = 0;
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
		this.strafeHor = 0F;
		this.strafeHor = 0F;
		this.getMoveHelper().strafe( 0.0F, 0.0F );
		this.getNavigator().clearPath();
	}
	
	//==================================================== Call For Help ===========================================================

	public void callForHelp( EntityLivingBase attacker )
	{
		List<EntityGuard> guards = this.world.getEntitiesWithinAABB(EntityGuard.class, new AxisAlignedBB(this.getPosition()).grow(16, 12, 16), new Predicate<EntityGuard>()
		{
			public boolean apply(@Nullable EntityGuard entity)
			{
				return true;
			}
		});
		
		for (EntityGuard guard: guards)
		{
			if ( guard.getAttackTarget() == null && guard.canEntityBeSeen( attacker ) )
			{
				if ( attacker instanceof EntityPlayer )
				{
					guard.setAnnoyed( (EntityPlayer)attacker );
				}
				guard.setAttackTarget( attacker );
			}
		}
		
		if ( this.getAttackTarget() == null || !this.getAttackTarget().isEntityAlive() )
		{
			if ( this.getRevengeTarget() instanceof EntityPlayer )
			{
				this.setAnnoyed( (EntityPlayer)attacker );
			}
			this.setAttackTarget( this.getRevengeTarget() );
		}
		
		if ( this.getAttackTarget() != null && !this.getAttackTarget().isEntityAlive() )
		{
			this.setAttackTarget(null);
		}
		
		List<EntityToroVillager> villagers = world.getEntitiesWithinAABB(EntityToroVillager.class, new AxisAlignedBB(getPosition()).grow(12, 8, 12), new Predicate<EntityToroVillager>()
		{
			public boolean apply(@Nullable EntityToroVillager entity)
			{
				return true;
			}
		});
	
		for ( EntityToroVillager villager : villagers )
		{
			villager.setUnderAttack(attacker);
		}
	}
	
	//==================================================== Ranged Attack ===========================================================

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
	{
		if ( target == null ) return;
	    EntityArrow entityarrow = this.getArrow(distanceFactor);
	    entityarrow.setIsCritical(true);
	    entityarrow.setDamage(entityarrow.getDamage()*(rand.nextFloat()/2+1));
	    double d0 = target.posX - this.posX;
	    double d1 = target.getEntityBoundingBox().minY + target.height/4.0 - entityarrow.posY - 1 - rand.nextDouble();
	    double d2 = target.posZ - this.posZ;
	    double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	    entityarrow.shoot( d0, d1 + d3 * 0.2D, d2, 2.35F, 1.0F );
	    this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.5F + 0.8F));
	    this.world.spawnEntity(entityarrow);
	}
	
	protected EntityArrow getArrow(float p_190726_1_)
	{
	    return new EntitySmartArrow(this.world, this);
	}
	
	//==================================================== Update Hand ===========================================================

	@Override
	protected void updateActiveHand()
	{
	    if (this.isHandActive())
	    {
	        ItemStack itemstack = this.getHeldItem(this.getActiveHand());
	        if ( itemstack.getItem() instanceof ItemShield ) // this.blocking
	        {
	        	activeItemStackUseCount = 30;
	        	if (activeItemStackUseCount > 0)
	        	{
	        		activeItemStack.getItem().onUsingTick(activeItemStack, this, activeItemStackUseCount);
	        	}
		        
		        if (this.getItemInUseCount() <= 25 && this.getItemInUseCount() % 4 == 0)
		        {
		        	this.canShieldPush = true;
		            this.updateItemUse(this.activeItemStack, 5);
		        }
		
		        if (--this.activeItemStackUseCount <= 0 && !this.world.isRemote)
		        {
		            this.onItemUseFinish();
		        }
	        }
	        else // NOT blocking
	        {
	        	if (itemstack == this.activeItemStack)
		        {
		            if (!this.activeItemStack.isEmpty())
		            {
		            	activeItemStackUseCount = net.minecraftforge.event.ForgeEventFactory.onItemUseTick(this, activeItemStack, activeItemStackUseCount);
		                if (activeItemStackUseCount > 0)
		                {
		                    activeItemStack.getItem().onUsingTick(activeItemStack, this, activeItemStackUseCount);
		                }
		            }
		
		            if (this.getItemInUseCount() <= 25 && this.getItemInUseCount() % 4 == 0)
		            {
			        	this.canShieldPush = true;
		                this.updateItemUse(this.activeItemStack, 5);
		            }
		
		            if (--this.activeItemStackUseCount <= 0 && !this.world.isRemote)
		            {
		                this.onItemUseFinish();
		            }
		        }
		        else
		        {
		        	this.canShieldPush = true;
		            this.resetActiveHand();
		        }
	        }
	    }
	}
	
	//==================================================== Status ===========================================================

	@Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        boolean flag = id == 33;
        boolean flag1 = id == 36;
        boolean flag2 = id == 37;
        
        if (id == 7)
        {
            this.playTameEffect(true);
        }
        else if (id == 6)
        {
            this.playTameEffect(false);
        }

        if (id != 2 && !flag && !flag1 && !flag2)
        {
            if (id == 3)
            {
                SoundEvent soundevent1 = this.getDeathSound();

                if (soundevent1 != null)
                {
                    this.playSound(soundevent1, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }

                this.setHealth(0.0F);
                this.onDeath(DamageSource.GENERIC);
            }
            else if (id == 30)
            {
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
            else if (id == 29)
            {
                this.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
            else
            {
                super.handleStatusUpdate(id);
            }
        }
        else
        {
            this.limbSwingAmount = 1.5F;
            this.hurtResistantTime = this.maxHurtResistantTime;
            this.maxHurtTime = 10;
            this.hurtTime = this.maxHurtTime;
            this.attackedAtYaw = 0.0F;

            if (flag)
            {
                this.playSound(SoundEvents.ENCHANT_THORNS_HIT, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            DamageSource damagesource;

            if (flag2)
            {
                damagesource = DamageSource.ON_FIRE;
            }
            else if (flag1)
            {
                damagesource = DamageSource.DROWN;
            }
            else
            {
                damagesource = DamageSource.GENERIC;
            }

            SoundEvent soundevent = this.getHurtSound(damagesource);

            if (soundevent != null)
            {
                this.playSound(soundevent, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.attackEntityFrom(DamageSource.GENERIC, -1.0F);
        }
        super.handleStatusUpdate(id);
    }
	
    public void setSwingingArms(boolean swingingArms)
    {
        return;
    }

	//==================================================== Insult ===========================================================

	public void insult(EntityPlayer player)
	{
		if ( this.murderWitness() == player )
		{
			this.chat(player, "murderer", null);
		}
		else
		{
			if ( this.getCivilization() != null )
			{
				this.chat(player, "insult", "House "+this.getCivilization().getDisplayName());
			}
			else if ( !player.getName().equals("") && !player.getName().equals(this.playerGuard) )
			{
				this.chat(player, "insult", this.playerGuard);
			}
			else
			{
				this.chat(player, "insult", I18n.format("civilization.null.name"));
			}
		}
		this.setAnnoyed( player );
		this.setAttackTarget(player);
	}
	
	//==================================================== Melee Attack ===========================================================

	@Override
	public boolean attackEntityAsMob(Entity victim)
	{
		if ( victim == null || !victim.isEntityAlive() )
		{
			this.setAttackTarget(null);
			return false;
		}
		
		if ( victim instanceof EntityToroNpc || victim instanceof EntityVillager || (victim instanceof EntityGolem && !(victim instanceof EntityConstruct)) )
		{
			if ( victim instanceof EntityLiving )
			{
				EntityLiving v = ((EntityLiving)victim);
				v.setAttackTarget(null);
			}
			this.setAttackTarget(null);
			return false;
		}
		
		if ( victim instanceof EntityLiving )
		{
			EntityLiving v = ((EntityLiving)victim);
			if ( v.getHealth() <= 0 )
			{
				this.setAttackTarget(null);
				if ( rand.nextInt(8) == 0 )
				{
					v.setDead();
				}
			}
		}
		
		this.attackTargetEntityWithCurrentItem(victim);
		
		if ( victim instanceof EntityPlayer )
		{
			EntityPlayer player = (EntityPlayer)victim;
			if ( !player.world.isRemote )
			{
				if ( this.rand.nextInt(25) == 0 )
				{
					this.insult(player);
				}
			}
		}
		return true;
	}
	
	public void attackTargetEntityWithCurrentItem(Entity targetEntity)
	{
		if ( rand.nextInt(5) == 0 )
        {
        	this.playSound( SoundEvents.VINDICATION_ILLAGER_AMBIENT, 1.0F, 0.9F + rand.nextFloat()/5.0F );
        }
		
		if (targetEntity.canBeAttackedWithItem())
		{
			if (!targetEntity.hitByEntity(this))
			{
				float attackDamage = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
				float modifierForCreature;

				if (targetEntity instanceof EntityLivingBase) {
					modifierForCreature = EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
							((EntityLivingBase) targetEntity).getCreatureAttribute());
				} else {
					modifierForCreature = EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
				}

				if (attackDamage > 0.0F || modifierForCreature > 0.0F) {

					int i = 0;
					i = i + EnchantmentHelper.getKnockbackModifier(this);

					boolean criticalHit = this.fallDistance > 0.0F && !this.onGround && !this.isOnLadder() && !this.isInWater()
							&& !this.isPotionActive(MobEffects.BLINDNESS) && !this.isRiding() && targetEntity instanceof EntityLivingBase;
					criticalHit = criticalHit && !this.isSprinting();

					if (criticalHit) {
						attackDamage *= 1.5F;
					}

					attackDamage = attackDamage + modifierForCreature;
					boolean swordSweep = false;
					double d0 = (double) (this.distanceWalkedModified - this.prevDistanceWalkedModified);

					if (!criticalHit && this.onGround && d0 < (double) this.getAIMoveSpeed()) {
						ItemStack itemstack = this.getHeldItem(EnumHand.MAIN_HAND);

						if (itemstack != null && itemstack.getItem() instanceof ItemSword) {
							swordSweep = true;
						}
					}

					float targetHealth = 0.0F;
					boolean setFireToTarget = false;
					int fireAspectModiferOfGuard = EnchantmentHelper.getFireAspectModifier(this);

					if (targetEntity instanceof EntityLivingBase) {
						targetHealth = ((EntityLivingBase) targetEntity).getHealth();

						if (fireAspectModiferOfGuard > 0 && !targetEntity.isBurning()) {
							setFireToTarget = true;
							targetEntity.setFire(1);
						}
					}

					double targetMotionX = targetEntity.motionX;
					double targetMotionY = targetEntity.motionY;
					double targetMotionZ = targetEntity.motionZ;

					boolean successfulAttack = targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);

					if (successfulAttack) {
						if (i > 0) {
							if (targetEntity instanceof EntityLivingBase) {
								((EntityLivingBase) targetEntity).knockBack(this, (float) i * 0.5F,
										(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
										(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
							} else {
								if ( !world.isRemote ) targetEntity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * 0.017453292F) * (float) i * 0.5F), 0.1D,
										(double) (MathHelper.cos(this.rotationYaw * 0.017453292F) * (float) i * 0.5F));
							}

							this.motionX *= 0.6D;
							this.motionZ *= 0.6D;
							this.setSprinting(false);
						}

						if (swordSweep) {
							for (EntityLivingBase entitylivingbase : this.world.getEntitiesWithinAABB(EntityLivingBase.class,
									targetEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
								if (entitylivingbase != this && entitylivingbase != targetEntity && !this.isOnSameTeam(entitylivingbase)
										&& this.getDistanceSq(entitylivingbase) < 9.0D) {
									entitylivingbase.knockBack(this, 0.4F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F),
											(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
									entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
								}
							}

							world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
									this.getSoundCategory(), 1.0F, 1.0F);
							this.spawnSweepParticles();
						}

						if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
							((EntityPlayerMP) targetEntity).connection.sendPacket(new SPacketEntityVelocity(targetEntity));
							targetEntity.velocityChanged = false;
							targetEntity.motionX = targetMotionX;
							targetEntity.motionY = targetMotionY;
							targetEntity.motionZ = targetMotionZ;
						}

						if (criticalHit) {
							this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
									this.getSoundCategory(), 1.0F, 1.0F);
							this.onCriticalHit(targetEntity);
						}

						if (!criticalHit && !swordSweep) {
							this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
									this.getSoundCategory(), 1.0F, 1.0F);
						}

						if (modifierForCreature > 0.0F) {
							this.onEnchantmentCritical(targetEntity);
						}

						if (!world.isRemote && targetEntity instanceof EntityPlayer) {
							EntityPlayer entityplayer = (EntityPlayer) targetEntity;
							ItemStack itemstack2 = this.getHeldItemMainhand();
							ItemStack itemstack3 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack() : null;

							if (itemstack2 != null && itemstack3 != null && itemstack3.getItem() instanceof ItemShield && ( itemstack2.getItem() instanceof ItemAxe || itemstack2.getItem().getRegistryName().toString().contains("halberd") || itemstack2.getItem().getRegistryName().toString().contains("battleaxe") ) )
							{
								float f3 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
								if (this.rand.nextFloat() < f3) {
									entityplayer.getCooldownTracker().setCooldown(Items.SHIELD, 100);
									this.world.setEntityState(entityplayer, (byte) 30);
								}
							}
						}

						this.setLastAttackedEntity(targetEntity);

						if (targetEntity instanceof EntityLivingBase) {
							EnchantmentHelper.applyThornEnchantments((EntityLivingBase) targetEntity, this);
						}

						EnchantmentHelper.applyArthropodEnchantments(this, targetEntity);
						ItemStack mainhandItem = this.getHeldItemMainhand();
						Entity entity = targetEntity;

						if (mainhandItem != null && entity instanceof EntityLivingBase) {
							mainhandItem.getItem().hitEntity(mainhandItem, (EntityLivingBase) entity, this);

							if (mainhandItem.getCount() <= 0) {
								this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
							}
						}

						if (targetEntity instanceof EntityLivingBase) {
							float damageDealt = targetHealth - ((EntityLivingBase) targetEntity).getHealth();

							if (fireAspectModiferOfGuard > 0) {
								targetEntity.setFire(fireAspectModiferOfGuard * 4);
							}

							if (world instanceof WorldServer && damageDealt > 2.0F) {
								int k = (int) ((double) damageDealt * 0.5D);
								((WorldServer) this.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.posX,
										targetEntity.posY + (double) (targetEntity.height * 0.5F), targetEntity.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D,
										new int[0]);
							}
						}

					} else {
						this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE,
								this.getSoundCategory(), 1.0F, 1.0F);

						if (setFireToTarget) {
							targetEntity.extinguish();
						}
					}
				}
			}
		}
	}
	
	public void onCriticalHit(Entity entityHit)
	{
		
	}

	public void onEnchantmentCritical(Entity entityHit)
	{
		
	}

	public void spawnSweepParticles()
	{
		double d0 = (double) (-MathHelper.sin(this.rotationYaw * 0.017453292F));
		double d1 = (double) MathHelper.cos(this.rotationYaw * 0.017453292F);

		if (this.world instanceof WorldServer) {
			((WorldServer) this.world).spawnParticle(EnumParticleTypes.SWEEP_ATTACK, this.posX + d0, this.posY + (double) this.height * 0.5D,
					this.posZ + d1, 0, d0, 0.0D, d1, 0.0D, new int[0]);
		}
	}
	
	//==================================================== Sounds ===========================================================

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
		if ( rand.nextInt(4) == 0 )
		{
			this.playSound(SoundEvents.VINDICATION_ILLAGER_DEATH, 1.0F, 0.9F + rand.nextFloat()/5.0F );
		}
		return super.getHurtSound(damageSourceIn);
    }
	
	@Override
    protected SoundEvent getDeathSound()
    {
		if ( rand.nextBoolean() )
		{
			this.playSound(SoundEvents.EVOCATION_ILLAGER_DEATH, 1.0F, 0.9F + rand.nextFloat()/5.0F );
		}
		else
		{
			this.playSound(SoundEvents.ENTITY_ILLAGER_DEATH, 1.0F, 0.9F + rand.nextFloat()/5.0F );
		}
        return null;
    }

	@Override
    protected SoundEvent getAmbientSound()
    {
        return null;
    }
	
	//==================================================== Guard Speak ===========================================================
	
	public static void guardSpeak(EntityGuard entity, EntityPlayer player)
	{
		if ( entity.world.isRemote )
		{
			return;
		}
		
		if ( entity.getAttackTarget() == player )
		{
			entity.insult(player);
			return;
		}
		
		if ( entity.getAttackTarget() != null )
		{
			return;
		}
		
		if ( entity.actionReady() )
		{
			entity.getNavigator().clearPath();
			entity.faceEntity(player, 20.0F, 20.0F);
			entity.getLookHelper().setLookPositionWithEntity(player, 20.0F, 20.0F);
		}
		
		CivilizationType civ = entity.getCivilization();
		
		boolean bandit = false;
		
		for ( ItemStack itemStack : player.getArmorInventoryList() )
		{
			if ( itemStack.getItem().equals(Item.getByNameOrId("toroquest:bandit_helmet") ) || itemStack.getItem().equals(Item.getByNameOrId("toroquest:legendary_bandit_helmet") ) )
			{
				bandit = true;
			}
		}
		
		if ( civ == null )
		{
			// HAS PLAYER GUARD
			if ( !entity.playerGuard.equals("") )
			{
				if ( player.getName().equals(entity.playerGuard) )
				{
					// PLAYERS GUARD					
					if ( entity.getHealth() <= entity.getMaxHealth()*0.75 )
					{
						if ( entity.actionReady() ) entity.chat(player, "wounded", null); return;
					}
					else
					{
						if ( entity.actionReady() ) entity.chat(player, "playersguard", null); return;
					}
				}
				// NOT PLAYERS GUARD
				else
				{
					if ( entity.actionReady() ) entity.chat(player, "otherplayersguard", entity.playerGuard); return;
				}
			}
			else if ( bandit || entity.murderWitness == player || entity.underAttack == player )
			{
				if ( entity.actionReady() ) entity.insult(player); return;
			}
			else
			{
				if ( wildernessGuardSpeak )
				{
					wildernessGuardSpeak = false;
					if ( !entity.world.getEntitiesWithinAABB(EntityCaravan.class, new AxisAlignedBB(entity.getPosition()).grow(25, 15, 25), new Predicate<EntityCaravan>(){public boolean apply(@Nullable EntityCaravan entity){return true;}}).isEmpty() )
					{
						entity.getNavigator().clearPath();
						entity.faceEntity(player, 20.0F, 20.0F);
						entity.getLookHelper().setLookPositionWithEntity(player, 20.0F, 20.0F);
						entity.chat(player, "caravan", null);
						entity.actionTimer = 20;
					}
					else
					{
						entity.getNavigator().clearPath();
						entity.faceEntity(player, 20.0F, 20.0F);
						entity.getLookHelper().setLookPositionWithEntity(player, 20.0F, 20.0F);
						entity.chat(player, "nociv", null);
						entity.actionTimer = 20;
					}
				}
				return;
			}
		}
		else if ( bandit )
		{
			if ( entity.actionReady() ) entity.chat(player, "playerbandit", null); return;
		}
		
		if ( !entity.actionReady() ) return;
		
		Province prov = CivilizationUtil.getProvinceAt(entity.world, entity.chunkCoordX, entity.chunkCoordZ);
		
		String name;
		
		if ( prov == null )
		{
			name = I18n.format("civilization.null.name");
		}
		else
		{
			name = prov.name;
		}
			
		int rep = PlayerCivilizationCapabilityImpl.get(player).getReputation(civ);
		rep = (int)(rep * (1+(entity.rand.nextGaussian()/3.0D)));
		ItemStack itemStack = player.getHeldItemMainhand();
		Item item = itemStack.getItem();
		
		if ( rep <= -50 || entity.murderWitness == player || entity.underAttack == player )
		{
			entity.insult(player); return;
		}
		else if ( entity.isAnnoyedAt(player) || rep < 0 )
		{
			entity.chat(player, "annoyed", name); return;
		}
		else if ( entity.getHealth() <= entity.getMaxHealth()*0.75 )
		{
			entity.chat(player, "wounded", name); return;
		}
		else if ( entity.rand.nextInt(4) == 0 && item instanceof ItemSword )
		{
			entity.chat(player, "sword", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( entity.rand.nextBoolean() && item instanceof ItemAxe )
		{
			entity.chat(player, "axe", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( item instanceof ItemHoe )
		{
			entity.chat(player, "hoe", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( entity.rand.nextBoolean() && item instanceof ItemPickaxe )
		{
			entity.chat(player, "pickaxe", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( item instanceof ItemSpade )
		{
			entity.chat(player, "spade", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( entity.rand.nextInt(4) == 0 && item instanceof ItemBow )
		{
			entity.chat(player, "bow", item.getItemStackDisplayName(itemStack)); return;
		}
		else if ( entity.rand.nextInt(8) == 0 && entity.world.isRainingAt(entity.getPosition()) )
		{
			entity.chat(player, "rain", name); return;
		}
		else if ( entity.rand.nextInt(8) == 0 && entity.world.isRaining() && entity.world.canSnowAtBody(entity.getPosition(), false) )
		{
			entity.chat(player, "snow", name); return;
		}
		else if ( entity.rand.nextInt(32) == 0 )
		{
			int time = (int)entity.world.getWorldTime();
			if ( time > 9000 && time < 13000 ) entity.chat(player, "evening", name);
			else if ( time >= 4000 && time < 9000 ) entity.chat(player, "morning", name);
			else if ( time <= 9000 ) entity.chat(player, "afternoon", name);
			else entity.chat(player, "night", name);
			return;
		}
		else
		{
			if ( !prov.hasLord && entity.ticksExisted > 500 )
			{
				entity.chat(player, "nolord", name); return;
			}
			else if ( entity.rand.nextInt(10) == 0 )
			{
				entity.chat(player, "anyrep", name); return;
			}
			else if ( rep < 300 )
			{
				entity.chat(player, "lowrep", name); return;
			}
			else if ( rep < 1500 )
			{
				entity.chat(player, "mediumrep", name); return;
			}
			else
			{
				entity.chat(player, "highrep", name); return;
			}
		}
	}
}