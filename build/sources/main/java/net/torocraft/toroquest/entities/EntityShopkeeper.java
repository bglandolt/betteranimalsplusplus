package net.torocraft.toroquest.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.torocraft.toroquest.ToroQuest;
import net.torocraft.toroquest.civilization.CivilizationUtil;
import net.torocraft.toroquest.civilization.Province;
import net.torocraft.toroquest.civilization.player.PlayerCivilizationCapabilityImpl;
import net.torocraft.toroquest.config.ToroQuestConfiguration;
import net.torocraft.toroquest.entities.trades.ToroVillagerTrades;
import net.torocraft.toroquest.item.ItemScrollEarth;
import net.torocraft.toroquest.item.ItemScrollFire;
import net.torocraft.toroquest.item.ItemScrollMoon;
import net.torocraft.toroquest.item.ItemScrollSun;
import net.torocraft.toroquest.item.ItemScrollWater;
import net.torocraft.toroquest.item.ItemScrollWind;

public class EntityShopkeeper extends EntityToroVillager implements IMerchant
{

	public static String NAME = "shopkeeper";

	static
	{
		if (ToroQuestConfiguration.specificEntityNames)
		{
			NAME = ToroQuestEntities.ENTITY_PREFIX + NAME;
		}
	}
	
	@Override
	public String getName()
    {
		return "Shopkeeper";
    }
	
	@Override
	public ITextComponent getDisplayName()
    {
		return new TextComponentString("Shopkeeper");
    }

	public static void init(int entityId) {
		EntityRegistry.registerModEntity(new ResourceLocation(ToroQuest.MODID, NAME), EntityShopkeeper.class, NAME, entityId, ToroQuest.INSTANCE, 80,
				3, true, 0x000000, 0xe0d6b9);
	}

	public EntityShopkeeper(World worldIn)
	{
		super(worldIn, 0);
	}

	@Override
	public IEntityLivingData finalizeMobSpawn(DifficultyInstance p_190672_1_, @Nullable IEntityLivingData p_190672_2_, boolean p_190672_3_) {
		return p_190672_2_;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		Item item = itemstack.getItem();
		if ( item.equals(Item.getByNameOrId("toroquest:recruitment_papers")) )
		{
			return true;
		}
		return super.processInteract(player, hand);
	}
	
//	@Override
//	protected void initEntityAI()
//    {              
//		this.tasks.addTask(0, new EntityAISwimming(this));
//        this.tasks.addTask(1, new EntityAITradePlayer(this));
//        this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
//        this.tasks.addTask(9, new EntityAIVillagerInteract(this));
//        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
//    }
	
	@Override
	protected MerchantRecipeList createTradesBaseOnRep(EntityPlayer player)
	{
		MerchantRecipeList recipeList = new MerchantRecipeList();
		try
		{
			Province province = CivilizationUtil.getProvinceAt( player.world, player.chunkCoordX, player.chunkCoordZ);
			
			int rep = PlayerCivilizationCapabilityImpl.get(player).getReputation( province.civilization );
			
			if ( province == null || province.civilization == null )
			{
				this.playSound(SoundEvents.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
				this.canTalk = 2;
				return recipeList;
			}
			else if ( rep <= -50 )
			{
				if ( this.canTalk <= 0 )
				{
					this.reportToGuards(player);
					this.playSound(SoundEvents.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
					this.canTalk = 1;
				}
				return recipeList;
			}
			
			Item item = Item.getByNameOrId(ToroQuestConfiguration.scrollTradeItem);
			
			int amount = ToroQuestConfiguration.scrollTradeAmount;
			
			if ( item != null && amount > 0 )
			{
				switch ( province.civilization )
				{
					case EARTH:
					{
						ItemScrollEarth scroll = (ItemScrollEarth)Item.getByNameOrId("toroquest:scroll_earth");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll:  " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					case FIRE:
					{
						ItemScrollFire scroll = (ItemScrollFire)Item.getByNameOrId("toroquest:scroll_fire");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll:  " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					case SUN:
					{
						ItemScrollSun scroll = (ItemScrollSun)Item.getByNameOrId("toroquest:scroll_sun");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll:  " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					case WATER:
					{
						ItemScrollWater scroll = (ItemScrollWater)Item.getByNameOrId("toroquest:scroll_water");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll:  " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					case MOON:
					{
						ItemScrollMoon scroll = (ItemScrollMoon)Item.getByNameOrId("toroquest:scroll_moon");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll:  " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					case WIND:
					{
						ItemScrollWind scroll = (ItemScrollWind)Item.getByNameOrId("toroquest:scroll_wind");
						ItemStack itemstack = new ItemStack(scroll,1);
						itemstack.setTagInfo("province", new NBTTagString(province.id.toString()));
						itemstack.setTagInfo("province_name", new NBTTagString(province.name.toString()));
						itemstack.setStackDisplayName("Teleport scroll: " + province.name);
						recipeList.add(new MerchantRecipe(new ItemStack(item ,ToroVillagerTrades.getSellPrice(amount, rep) ),ItemStack.EMPTY,itemstack,0,99999));
						break;
					}
					default:
					{
						break;
					}
				}
			}
			MerchantRecipeList t = ToroVillagerTrades.trades(this, player, rep, province.civilization, "shopkeeper", "x" );
			for ( MerchantRecipe tt : t)
			{
				recipeList.add(tt);
			}
			return recipeList;
		}
		catch ( Exception e )
		{
			return recipeList;
		}
	}
}