package net.torocraft.toroquest.config;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.torocraft.toroquest.ToroQuest;

public class ToroQuestConfiguration
{

	private static final String CATEGORY = "ToroQuest Settings";
	private static final String CATEGORY_SKINS = "Custom Mob Skins";
	private static final String CATEGORY_TRADES = "Trades";
	private static final String CATEGORY_MOBS = "Mobs";
	private static final String CATEGORY_WEAPONS = "Bandit, Orc, and Guard Weapons & Shields";
	private static final String CATEGORY_GEN = "Generation";
	private static final String CATEGORY_SPAWNING = "Spawning";
	private static final String CATEGORY_BOSSES = "Bosses";
	private static final String CATEGORY_REPUTATION = "Reputation";

	private static Configuration config;
	
	public static boolean useCrownToCreateNewProvinces = true;
	
	public static float bossHealthMultiplier = 1;
	public static float bossAttackDamageMultiplier = 1;
	
	public static int guardHealth = 30;
	public static int guardArmor = 0;
	public static int guardArmorToughness = 2;
	public static float trophyTitanAdditionalGuardDamageMulitiplier = 1.5F;
	public static float guardDamageMultiplierToMobs = 1.5F;
	public static float guardDamageMultiplierToPlayers = 1.25F;
	
	public static float guardNerfOutsideProvince = 0.75F;
	
	public static int banditHealth = 20;
	public static int banditArmor = 0;
	public static float banditDamageMultiplier = 1.25F;
	
	// public static float banditDamageBasedOnHealthModifier = 0.01; per 1HP
	// public static float orcDamageBasedOnHealthModifier = 0.01; per 1HP
	// public static float guardDamageBasedOnHealthModifier = 0.01; // increases the damage modifier by X for every 1HP over their base health. So with the default value (0.01), 
	// a bandit with 50HP over their default base health (20) would have a damage multiplier of...   1 * ( banditDamageMultiplier + banditDamageBasedOnHealthModifier )  ->  1 * (1.25 * 0.5)  -> 1.75
	
	public static int spawnHeight = 100;
	
	public static int toroVillagerMateChance = 1200;
	public static float villageDoorsModifier = 0.4F;
	public static int maxVillagersPerVillage = 20;
	
	public static int orcHealth = 30;
	public static int orcArmor = 0;
	public static float orcDamageMultiplier = 1.5F;
	
	public static float banditArmorDropChance = 0.1F;
	public static float banditHandsDropChance = 0.3F;
	
	public static float guardArmorDropChance = 0.1F;
	public static float guardHandsDropChance = 0.3F;
	
	public static int banditAndOrcFleeHealthThreshold = 4;
	
	public static boolean banditsDropEmeralds = true;
	public static int banditsDropPotions = 6;
	public static boolean banditsDropMasks = true;
	public static boolean orcsDropEmeralds = true;
	public static boolean orcsDropMasks = false;
	public static boolean steamGolemsDropLoot = true;
	public static boolean anyAnimalForBreedQuest = false;
	public static boolean cartographerMapTrade = true;

	//public static boolean renderGuardCape = true;
	public static boolean renderBanditMask = true;
	public static boolean renderOrcMask = false;
	public static boolean banditsHaveArmorForSpartanWeaponry = true;
	public static boolean guardsHaveArmorForSpartanWeaponry = true;
	
	public static boolean showProvinceEnterLeaveMessage = true;
	public static boolean sendRepLevelMessage = true;
	
	public static boolean mobsAttackGuards = true;
	public static boolean iMobAttackVillagers = true;
	public static boolean removeMuleOnCaravanEscort = false;
	
	public static boolean enderIdolTeleport = true;

	public static boolean titanBoss = true;
	public static boolean pigBoss = true;
	public static boolean banditBoss = true;
	public static boolean golemBoss = true;
	public static boolean skeletonBoss = true;
	public static boolean spiderBoss = true;
	public static boolean mageBoss = true;
	public static boolean enderBoss = true;
		
	public static int unexpensiveRepLoss = 1;
	public static int leashVillagerRepLoss = 5;
	public static int fireGriefRepLoss = 5;
	public static int lavaGriefRepLoss = 10;
	public static int murderLivestockRepLoss = 10;
	public static int abandonQuestRepLoss = 10;
	public static int expensiveRepLoss = 10;
	
	public static int killMobRepGain = 1;
	public static int donateEmeraldRepGain = 2;
	public static int donateBanditMaskRepGain = 5;
	public static int recruitGuardRepGain = 5;
	public static int returnFugitiveRepGain = 10;
	public static int escortCaravanRepGain = 20;
	public static int donateArtifactRepGain = 50;
	public static int donateTrophyRepGain = 50;
	public static boolean disableTreeChoppingQuest = false;
	
	public static String scrollTradeItem = "minecraft:emerald";
	public static int scrollTradeAmount = 3;
	
	public static int destroyedVillagesNearSpawnDistance = 320;
	public static boolean useBiomeSpecificProvinces = true;
	
	public static boolean specificEntityNames = true;
	public static boolean useDefaultVillagersOutsideOfProvince = true;
	public static boolean useDefaultVillagers = false;
	public static boolean villagesSpawnGolems = false;

	public static int banditMountChance = 3;
	public static int orcMountChance = 0;
	public static boolean orcsAreNeutral = false;

	public static boolean loseReputationForCropGrief = true;
	public static boolean loseReputationForAnimalGrief = true;
	public static boolean loseReputationForBlockGrief = true;
	
	public static boolean showQuestCompletionAboveActionBar = true;
	
	//public static boolean banditsWearArmor = true;
	public static boolean guardsHaveDialogue = true;
	public static boolean recruitBandits = true;
	public static boolean recruitVillagers = true;
	
//	public static boolean vampirismCompatability = false;

	public static int zombieAttackVillageChance = 25;
	public static int zombieRaiderVillagerChance = 25;
	public static int provinceSiegeRate = 8;
	public static int caravanSpawnRate = 12;
	public static int banditSpawnRate = 8;
	public static int fugitiveSpawnRate = 8;
	public static int artifactDropRate = 10;
	public static int disableMobSpawningNearVillage = 88;
	
	public static String[] tradeList = new String[]{};
	public static ArrayList<Trade> trades = new ArrayList<Trade>();
	
	public static String[] mobsList = new String[]{};
	public static ArrayList<KillMob> mobs = new ArrayList<KillMob>();
	
//	public static String[] pitQuestList = new String[]{};
//	public static ArrayList<pitMob> pitQuestMobs = new ArrayList<pitMob>();
		
	public static String[] banditShields = new String[]{};
	public static String[] banditOneHandedMeleeWeapons = new String[]{};
	public static String[] banditTwoHandedMeleeWeapons = new String[]{};
	public static String[] banditRangedWeapons = new String[]{};
	
	public static String[] orcShields = new String[]{};
	public static String[] orcOneHandedMeleeWeapons = new String[]{};
	public static String[] orcTwoHandedMeleeWeapons = new String[]{};
	public static String[] orcRangedWeapons = new String[]{};
	
	public static String[] enchantFirstBanditAndOrcMeleeWeapon = new String[]{};
	public static String[] enchantSecondBanditAndOrcMeleeWeapon = new String[]{};

	public static String[] enchantFirstBanditAndOrcShield = new String[]{};
//	public static String[] enchantSecondBanditAndOrcShield = new String[]{};

	public static String[] enchantFirstBanditAndOrcRanged = new String[]{};
	public static String[] enchantSecondBanditAndOrcRanged = new String[]{};

	public static int enchantFirstBanditAndOrcChance = 20;
	public static int enchantSecondBanditAndOrcChance = 60;
	
	public static String guardWeapon_RED_BRIAR = "minecraft:iron_sword";
	public static String guardShield_RED_BRIAR = "minecraft:shield";
	
	public static String guardWeapon_GREEN_WILD = "minecraft:iron_sword";
	public static String guardShield_GREEN_WILD = "minecraft:shield";

	public static String guardWeapon_BROWN_MITHRIL = "minecraft:iron_sword";
	public static String guardShield_BROWN_MITHRIL = "minecraft:shield";
	
	public static String guardWeapon_YELLOW_DAWN = "minecraft:iron_sword";
	public static String guardShield_YELLOW_DAWN = "minecraft:shield";
	
	public static String guardWeapon_BLUE_GLACIER = "minecraft:iron_sword";
	public static String guardShield_BLUE_GLACIER= "minecraft:shield";
	
	public static String guardWeapon_BLACK_MOOR = "minecraft:iron_sword";
	public static String guardShield_BLACK_MOOR = "minecraft:shield";

	
	//public static File configDirectory;
	//public static String configFolderName = "toroquest_skins";


	// GeneralConfig.init(new File(configDirectory, "general.cfg"));
	
	public static void init(File configFile)
	{
		if (config == null)
		{
			config = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	public static int banditSkins = 1;
	public static int orcSkins = 1;

	private static void loadConfiguration()
	{
		try
		{
			
			// SKINS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			
			banditSkins = config.getInt("banditSkins", CATEGORY_SKINS, 1, 1, 2560,
					"The number of bandit skins in your Resource Folder (check mod description on Curse) that you wish to use. For example, in your folder you have bandit_0,"
					+ " bandit_1, bandit_2, bandit_3, bandit_4, then you would set this number to 5.");
			
			orcSkins = config.getInt("orcSkins", CATEGORY_SKINS, 1, 1, 2560,
					"The number of bandit skins in your Resource Folder (check mod description on Curse) that you wish to use. For example, in your folder you have orc_0,"
					+ " orc_1, orc_2, then you would set this number to 3.");
			
			// GEN =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			
			useBiomeSpecificProvinces = config.getBoolean("useBiomeSpecificProvinces", CATEGORY_GEN, true,
					"If set to true, villages will generate their province based off the biome they are in"
					+ "(I highly recommened you include the mod Mo' Villages so that villages spawn more frequently and in any biome).");

			destroyedVillagesNearSpawnDistance = config.getInt("destroyedVillagesNearSpawnDistance", CATEGORY_GEN, 320, 0, Integer.MAX_VALUE,
					"Villages within X blocks of [0,0] will generate as ruined/raided/destoyed villages.");

			// MOBS =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

			toroVillagerMateChance = config.getInt("toroVillagerMateChance", CATEGORY_MOBS, 1200, 1, Integer.MAX_VALUE,
					"Chance for villagers to mate (vanilla is 500). Toro Villagers no longer need to be 'willing' (enough food and/or enough trades), they only need a bed to mate.");

			villageDoorsModifier = config.getFloat("villageDoorsModifier", CATEGORY_MOBS, 0.4F, 0.0F, 8.0F,
					"If the number of (village doors) * villageDoorsModifier is less than the number of (villagers), then allow villagers to breed (vanilla is 0.35F, lower value means more villagers breeding, 0.5F means 2 villagers are allowed per valid village door. Set to 0.0F to disable breeding).");
			
			maxVillagersPerVillage = config.getInt("maxVillagersPerVillage", CATEGORY_MOBS, 20, 0, 256,
					"The number of villagers allowed in a village.");
			
			orcsAreNeutral = config.getBoolean("orcsAreNeutral", CATEGORY_MOBS, false,
					"Enable to make orcs neutral like pigmen.");
			
			mobsAttackGuards = config.getBoolean("mobsAttackGuards", CATEGORY_MOBS, true,
					"Enable to have mobs attack guards on sight.");
			
			iMobAttackVillagers = config.getBoolean("iMobAttackVillagers", CATEGORY_MOBS, true,
					"Enable to add have all IMobs attack villagers on sight.");
			
			useDefaultVillagersOutsideOfProvince = config.getBoolean("useDefaultVillagersOutsideOfProvince", CATEGORY_MOBS, true, "Enable to make default villagers"
					+ "spawn (instead of toro villagers) outside the bounds of a province. Enable this if you want to be able to trade with villagers that spawn from random structures such as Recurrent Complex.");
			
			useDefaultVillagers = config.getBoolean("useDefaultVillagers", CATEGORY_MOBS, false, "Enable to make default villagers spawn instead of toro villagers :(");
			
			banditsDropEmeralds = config.getBoolean("banditsDropEmeralds", CATEGORY_MOBS, true,
					"Enable to allow bandits a 1/3 chance to drop 1-3 Emeralds on death.");
			
			banditsDropPotions = config.getInt("banditsDropPotions", CATEGORY_MOBS, 6, 0, 128,
					"Chance ( 1 out of X ) for bandits to drop 1 Potion on death. Set to 0 to disable.");
			
			banditsDropMasks = config.getBoolean("banditsDropMasks", CATEGORY_MOBS, true,
					"Enable to allow orcs to drop their masks on death, which can be worn or turned in for reputation.");
			
			steamGolemsDropLoot = config.getBoolean("steamGolemsDropLoot", CATEGORY_MOBS, true,
					"Enable to allow Steam Golems to drop loot.");
			
			orcsDropEmeralds = config.getBoolean("orcsDropEmeralds", CATEGORY_MOBS, true,
					"Enable to allow orcs a 1/3 chance to drop 1-4 Emeralds on death.");
			
			orcsDropMasks = config.getBoolean("orcsDropMasks", CATEGORY_MOBS, false,
					"Enable to allow ors to drop their masks on death, which can be worn or turned in for reputation. If this is true, they will become neutral to players wearing masks as well.");
			
			banditHandsDropChance = config.getFloat("banditHandsDropChance", CATEGORY_MOBS, 0.3f, 0.0f, 1.0f,
					"Chance for bandits to drop their held items on death.");

			banditArmorDropChance = config.getFloat("banditArmorDropChance", CATEGORY_MOBS, 0.1f, 0.0f, 1.0f,
					"Chance for bandits to drop their armor on death. Does nothing if banditsHaveArmorForSpartanWeaponry is set to false.");
			
			guardHandsDropChance = config.getFloat("guardHandsDropChance", CATEGORY_MOBS, 0.3f, 0.0f, 1.0f,
					"Chance for guards to drop their held items on death.");

			guardArmorDropChance = config.getFloat("guardArmorDropChance", CATEGORY_MOBS, 0.1f, 0.0f, 1.0f,
					"Chance for guards to drop their armor on death. Does nothing if guardsHaveArmorForSpartanWeaponry is set to false.");
			
//			renderGuardCape = config.getBoolean("renderGuardCape", CATEGORY_MOBS, true,
//					"Enable to allow cape to be rendered/ visible on guards. Disabling this will slightly improve performance!");
			
			renderBanditMask = config.getBoolean("renderBanditMask", CATEGORY_MOBS, true,
					"Enable to allow mask to be rendered/ visible on bandits.");
			
			renderOrcMask = config.getBoolean("renderOrcMask", CATEGORY_MOBS, false,
					"Enable to allow mask to be rendered/ visible on orcs.");

			banditsHaveArmorForSpartanWeaponry = config.getBoolean("banditsHaveArmorForSpartanWeaponry", CATEGORY_MOBS, true,
					"Enable to have sabers, rapiers, and warhammers deal the correct damage against bandits and orcs. This setting, when true, adds leather armor to bandits (which is not rendered! Meaning it will be invisible). However, if banditArmorDropChance is higher than 0, they will be able to drop leather armor.");

			guardsHaveArmorForSpartanWeaponry = config.getBoolean("guardsHaveArmorForSpartanWeaponry", CATEGORY_MOBS, true,
					"Enable to have sabers, rapiers, warhammers, and hammers deal the correct damage against guards. This setting, when true, adds chainmail armor to guards (which is not rendered! Meaning it will be invisible). However, if guardArmorDropChance is higher than 0, they will be able to drop chainmail armor.");

			villagesSpawnGolems = config.getBoolean("villagesSpawnGolems", CATEGORY_MOBS, false,
					"Enable to allow Iron Golems spawning in villages.");
			
			removeMuleOnCaravanEscort = config.getBoolean("removeMuleOnCaravanEscort", CATEGORY_MOBS, false,
					"Setting this to true despawns the caravan's mule after the caravan has been escorted.");
			
			guardsHaveDialogue = config.getBoolean("guardsHaveDialogue", CATEGORY_MOBS, true,
					"Enable to allow guards to speak in the chat.");
			
			guardDamageMultiplierToMobs = config.getFloat("guardDamageMultiplierToMobs", CATEGORY_MOBS, 1.5f, 0.25f, 5f,
					"guard damage is muliplied by this amount to mobs (and not the player).");
			
			guardDamageMultiplierToPlayers = config.getFloat("guardDamageMultiplierToPlayers", CATEGORY_MOBS, 1.0f, 0.25f, 5f,
					"guard damage is muliplied by this amount to players (and not mobs).");
			
			trophyTitanAdditionalGuardDamageMulitiplier = config.getFloat("trophyTitanAdditionalGuardDamageMulitiplier", CATEGORY_MOBS, 1.0f, 1.0f, 5f,
					"guard damage is muliplied by this amount to mobs with provinces with the titan trophy active.");
			
			guardNerfOutsideProvince = config.getFloat("guardNerfOutsideProvince", CATEGORY_MOBS, 0.75f, 0.0f, 1.0f,
					"Setting this to true will significantly reduce guard damage while outside a province. This is useful if you want to prevent the effectiveness of players from luring guards to go kill bosses for them.");
			
			guardHealth = config.getInt("guardHealth", CATEGORY_MOBS, 30, 1, 100,
					"Max HP of guards.");
			
			guardArmor = config.getInt("guardArmor", CATEGORY_MOBS, 4, 0, 20,
					"Guard armor value.");
			
			guardArmorToughness = config.getInt("guardArmorToughness", CATEGORY_MOBS, 2, 0, 20,
					"Guard armor toughness value.");
			
			banditDamageMultiplier = config.getFloat("banditDamageMultiplier", CATEGORY_MOBS, 1.5f, 0.25f, 5f,
					"Bandit damage is muliplied by this amount.");
			
			banditHealth = config.getInt("banditHealth", CATEGORY_MOBS, 20, 1, 100,
					"Max HP of bandits.");
			
			banditArmor = config.getInt("banditArmor", CATEGORY_MOBS, 2, 0, 20,
					"Armor value of bandits.");
			
			orcDamageMultiplier = config.getFloat("orcDamageMultiplier", CATEGORY_MOBS, 1.5f, 0.25f, 5f,
					"Orc damage is muliplied by this amount.");
			
			orcHealth = config.getInt("orcHealth", CATEGORY_MOBS, 30, 1, 100,
					"Max HP of orcs.");
			
			orcArmor = config.getInt("orcArmor", CATEGORY_MOBS, 4, 0, 20,
					"Armor value of orcs.");
			
//			banditMountChance = config.getInt("banditMountChance", CATEGORY_MOBS, 0, 0, 10,
//					"REMOVED!!! The chance (out of 10) for bandits to spawn with mounts. Set to 0 to disable.");
//			
//			orcMountChance = config.getInt("orcMountChance", CATEGORY_MOBS, 0, 0, 10,
//					"REMOVED!!! The chance (out of 10) for orcs to spawn with mounts. Set to 0 to disable.");
			
			banditAndOrcFleeHealthThreshold = config.getInt("banditAndOrcFleeHealthThreshold", CATEGORY_MOBS, 4, 0, 100,
					"When orcs or bandits have their health reduced to or below this number, they will flee.");

			zombieAttackVillageChance = config.getInt("zombieAttackVillageChance", CATEGORY_MOBS, 25, 0, 100,
					"The chance (out of 100) for zombies that spawn near a province to seige it (these raiding zombies will only replace VANILLA zombies that spawn, so you may need to increase this number if you have mods that add more zombie types). Set to 0 to disable.");
			
			zombieRaiderVillagerChance = config.getInt("zombieRaiderVillagerChance", CATEGORY_MOBS, 25, 0, 100,
					"The chance (out of 100) for siege zombies that spawn near a province to become zombie villagers. Set to 0 to disable.");
			
			// SPAWNING =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			
			spawnHeight = config.getInt("spawnHeight", CATEGORY_MOBS, 100, -2560, 2560,
					"Max spawn height of toroquest entities. Have this number be roughly equal to 40 blocks higher than what the y-axis that terrain generates at. Vanilla is 64.");
			
			disableMobSpawningNearVillage = config.getInt("disableMobSpawningNearVillage", CATEGORY_SPAWNING, 88, 0, 208,
					"Disable mob spawns within X blocks from the village center. The higher the number, the further from the center of a province mobs will spawn. 208 blocks is the max distance of a province. Setting this to 208 means mobs can NOT spawn anywhere in a province. Setting this to 0 disables this feature and mobs can spawn anywhere.");
						
			provinceSiegeRate = config.getInt("provinceSiegeRate", CATEGORY_SPAWNING, 8, 0, 100, "every 1200 ticks/ every minute, the chance out of 100 for a random village (with a player visiting or nearby) to spawn a siege."
					+ " This chance increases by 2 for each other player online, up to a max of provinceSiegeRate*2. Setting to 0 will disable spawning. NOTE: there are 24000 ticks per day/night cycle. These mobs eventually despawn.");

			fugitiveSpawnRate = config.getInt("fugitiveSpawnRate", CATEGORY_SPAWNING, 8, 0, 100, "every 1200 ticks/ every minute, the chance out of 100 for a random village (with a player visiting or nearby) to spawn a fugitive."
					+ " This chance increases by 2 for each other player online, up to a max of fugitiveSpawnRate*2. Setting to 0 will disable spawning. NOTE: there are 24000 ticks per day/night cycle. These fugitives will not despawn, although there is a max of 4 that can be in a village!");
			
			caravanSpawnRate = config.getInt("caravanSpawnRate", CATEGORY_SPAWNING, 12, 0, 100, "every 1200 ticks/ every minute, the chance out of 100 for a random player to have a caravan spawned near them."
					+ " This chance increases by 2 for each other player online, up to a max of caravanSpawnRate*2. Setting to 0 will disable spawning. NOTE: there are 24000 ticks per day/night cycle. These caravans will despawn, but will most likely die before they do. Caravans only spawn in the daytime.");

			banditSpawnRate = config.getInt("banditSpawnRate", CATEGORY_SPAWNING, 8, 0, 100, "every 1200 ticks/ every minute, the chance out of 100 for a random player to have bandits spawned near them."
					+ " This chance increases by 2 for each other player online, up to a max of banditSpawnRate*2. Setting to 0 will disable spawning. NOTE: there are 24000 ticks per day/night cycle. These bandits eventually despawn.");
						
			// REPUTATION =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

			unexpensiveRepLoss = config.getInt("unexpensiveRepLoss", CATEGORY_REPUTATION, 1, 0, 3000,
					"The amount of rep you will lose for destroying un-expensive village blocks such as crops or wood/stone blocks (when your reputation is not high enough).");
			
			leashVillagerRepLoss = config.getInt("leashVillagerRepLoss", CATEGORY_REPUTATION, 5, 0, 3000,
					"The amount of rep you will lose for placing fire in a province (when your reputation is not high enough).");
			
			fireGriefRepLoss = config.getInt("fireGriefRepLoss", CATEGORY_REPUTATION, 5, 0, 3000,
					"The amount of rep you will lose for placing fire in a province (when your reputation is not high enough).");
			
			lavaGriefRepLoss = config.getInt("lavaGriefRepLoss", CATEGORY_REPUTATION, 10, 0, 3000,
					"The amount of rep you will lose for placing lava in a province (when your reputation is not high enough).");

			murderLivestockRepLoss = config.getInt("murderLivestockRepLoss", CATEGORY_REPUTATION, 10, 0, 3000,
					"The amount of rep you will lose for butchering livestock (when your reputation is not high enough).");
			
			expensiveRepLoss = config.getInt("expensiveRepLoss", CATEGORY_REPUTATION, 10, 0, 3000,
					"The amount of rep you will lose for destroying expensive village blocks such as bookshelves, doors, gold blocks, or stealing from villager chests (when your reputation is not high enough).");

			abandonQuestRepLoss = config.getInt("abandonQuestRepLoss", CATEGORY_REPUTATION, 10, 0, 3000,
					"The amount of rep you will lose for abandoning a quest.");
			
			killMobRepGain = config.getInt("killMobRepGain", CATEGORY_REPUTATION, 1, 0, 3000,
					"The amount of rep you will gain for killing mobs in a province.");
			
			donateEmeraldRepGain = config.getInt("donateEmeraldRepGain", CATEGORY_REPUTATION, 2, 1, 3000,
					"The amount of rep you will gain for donating emeralds to a village lord.");
			
			donateBanditMaskRepGain = config.getInt("donateBanditMaskRepGain", CATEGORY_REPUTATION, 5, 0, 3000,
					"The amount of rep you will gain for donating bandit masks to a village lord.");
			
			recruitGuardRepGain = config.getInt("recruitGuardRepGain", CATEGORY_REPUTATION, 5, 0, 3000,
					"The amount of rep you will gain for recruiting guards.");
			
			returnFugitiveRepGain = config.getInt("returnFugitiveRepGain", CATEGORY_REPUTATION, 10, 0, 3000,
					"The amount of rep you will gain for capturing fugitives with a lead and returning them to guards.");
			
			escortCaravanRepGain = config.getInt("escortCaravanRepGain", CATEGORY_REPUTATION, 20, 0, 3000,
					"The amount of rep you will gain for escorting caravans to a province. This number is increased by 25% per mule escorted.");
			
			donateArtifactRepGain = config.getInt("donateArtifactRepGain", CATEGORY_REPUTATION, 50, 0, 3000,
					"The amount of rep you will gain for donating lost artifacts to a village lord.");
			
			donateTrophyRepGain = config.getInt("donateTrophyRepGain", CATEGORY_REPUTATION, 50, 0, 3000,
					"The amount of rep you will gain for donating legendary trophies to a village lord.");
			
			loseReputationForCropGrief = config.getBoolean("loseReputationForCropGrief", CATEGORY_REPUTATION, true,
					"If set to true and a player's reputation is below 50, they will lose reputation for griefing crops.");
			
			loseReputationForAnimalGrief = config.getBoolean("loseReputationForAnimalGrief", CATEGORY_REPUTATION, true,
					"If set to true and a player's reputation is below 100, they will lose reputation for butchering animals.");
			
			loseReputationForBlockGrief = config.getBoolean("loseReputationForBlockGrief", CATEGORY_REPUTATION, true,
					"If set to true and a player's reputation is below 250, they will lose reputation for griefing villages.");
			
			// BOSSES =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

			bossHealthMultiplier = config.getFloat("bossHealthMultiplier", CATEGORY_BOSSES, 1, 0.01f, 100f, "ToroQuest boss health multipler");
			
			bossAttackDamageMultiplier = config.getFloat("bossAttackDamageMultiplier", CATEGORY_BOSSES, 1, 0.01f, 100f, "ToroQuest boss damage multipler");

			titanBoss = config.getBoolean("titanBoss", CATEGORY_BOSSES, true,
					"Enable to allow Grave Titan boss quest to generate.");
			
			banditBoss = config.getBoolean("banditBoss", CATEGORY_BOSSES, true,
					"Enable to allow  Bandit boss quest to generate.");
			
			pigBoss = config.getBoolean("pigBoss", CATEGORY_BOSSES, true,
					"Enable to allow Zombie Pigman boss quest to generate.");
			
			golemBoss = config.getBoolean("golemBoss", CATEGORY_BOSSES, true,
					"Enable to allow Steam Golem boss quest to generate.");
			
			skeletonBoss = config.getBoolean("skeletonBoss", CATEGORY_BOSSES, true,
					"Enable to allow Wither Skeleton boss quest to generate.");
			
//			blazeBoss = config.getBoolean("blazeBoss", CATEGORY_QUESTS, true,
//					"Enable to allow Blaze boss quest to generate.");
			
			spiderBoss = config.getBoolean("spiderBoss", CATEGORY_BOSSES, true,
					"Enable to allow Spider Boss boss quest to generate.");
			
			mageBoss = config.getBoolean("mageBoss", CATEGORY_BOSSES, true,
					"Enable to allow Mage boss quest to generate.");
			
			enderBoss = config.getBoolean("enderBoss", CATEGORY_BOSSES, true,
					"Enable to allow Ender boss quest to generate.");
			
			// OTHER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			
			recruitBandits = config.getBoolean("recruitBandits", CATEGORY, true,
					"If set to true, players can use Recruitment Papers on bribed (with emeralds) Bandits to recruit them as Guards.");
			
			recruitVillagers = config.getBoolean("recruitVillagers", CATEGORY, true,
					"If set to true, players can use Recruitment Papers on Villagers to recruit them as Guards.");
			
			useCrownToCreateNewProvinces = config.getBoolean("useCrownToCreateNewProvinces", CATEGORY, true,
					"If set to true, players can create new provinces using the crown on a bandit.");
			
			artifactDropRate = config.getInt("artifactDropRate", CATEGORY, 10, 0 ,2000,
					"Drop rate of lost artifacts. X out of 2000 mobs will drop an artifact on death, and X out of 8000 dirt/stone/sand blocks will drop an artifact on harvest. Set to 0 to disable.");
	
			showQuestCompletionAboveActionBar = config.getBoolean("showQuestCompletionAboveActionBar", CATEGORY, true,
					"If set to true, the Quest Complete! notification will appear above the action bar instead of in chat.");
			
			showProvinceEnterLeaveMessage = config.getBoolean("showProvinceEnterLeaveMessage", CATEGORY, true,
					"If set to true, show province enter and leave message.");
			
			sendRepLevelMessage = config.getBoolean("sendRepLevelMessage", CATEGORY, true,
					"If set to true, show the reputation level message that appears at certain reputation level thresholds (at 50, 100, 250, 500, 1000, 2000, 3000)");
			
			enderIdolTeleport = config.getBoolean("enderIdolTeleport", CATEGORY, true,
					"If set to true, the ender idol item will save you (similar to totem of undying) and teleport you on death, instead of having you respawn with all of your items and experience after a death. Set to true if you have other inventory death mods such as corpse complex, as there may be inventory dupe interactions.");
			
			anyAnimalForBreedQuest = config.getBoolean("anyAnimalForBreedQuest", CATEGORY, false,
					"Set to true if you want to have all animals count for the breed quest. Set to true of you have mods installed that include many extra animals, such as Animania.");
			
			disableTreeChoppingQuest = config.getBoolean("disableTreeChoppingQuest", CATEGORY, false,
					"Set to true if you have DynamicTrees installed and have the option for felling an entire tree (removes tree chopping quest).");
			
			cartographerMapTrade = config.getBoolean("cartographerMapTrade", CATEGORY, true,
					"Set true to torovillager enable map trade.");
			
			tradeList = config.getStringList("tradeList", CATEGORY_TRADES,
					
		    new String[]
		    {
		    	// FARMER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	// 0 roots

		    	"minecraft:emerald,1,x,minecraft:beetroot_seeds,24,x,x,farmer,0",
		    	"minecraft:beetroot_seeds,24,x,minecraft:emerald,1,x,x,farmer,0",
		    	
		    	"minecraft:emerald,1,x,minecraft:beetroot,20,x,x,farmer,0",
		    	"minecraft:beetroot,20,x,minecraft:emerald,1,x,x,farmer,0",
		    	
		    	"minecraft:emerald,1,x,minecraft:carrot,20,x,50,farmer,0",
		    	"minecraft:carrot,20,x,minecraft:emerald,1,x,50,farmer,0",

		    	"minecraft:emerald,1,x,minecraft:potato,20,x,100,farmer,0",
		    	"minecraft:potato,20,x,minecraft:emerald,1,x,100,farmer,0",

		    	// 1 wheat
	    		
		    	"minecraft:emerald,1,x,minecraft:wheat_seeds,24,x,x,farmer,1",
		    	"minecraft:wheat_seeds,24,x,minecraft:emerald,1,x,x,farmer,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:wheat,16,x,x,farmer,1",
		    	"minecraft:wheat,16,x,minecraft:emerald,1,x,x,farmer,1",
		    	
		    	// 2 sugarcane
		    	"minecraft:emerald,1,x,minecraft:reeds,24,x,x,farmer,2",
		    	"minecraft:reeds,24,x,minecraft:emerald,1,x,x,farmer,2",
		    			    			    			    	
		    	// 3 melon/pumpkins
		    	"minecraft:emerald,1,x,minecraft:pumpkin,6,x,x,farmer,3",
		    	"minecraft:pumpkin,6,x,minecraft:emerald,1,x,x,farmer,3",
		    	
		    	"minecraft:emerald,1,x,minecraft:melon,24,x,100,farmer,3",
		    	"minecraft:melon,24,x,minecraft:emerald,1,x,100,farmer,3",
		    	
		    	// 4 apple
		    	"minecraft:emerald,1,x,minecraft:apple,6,x,x,farmer,4",
		    	"minecraft:apple,6,x,minecraft:emerald,1,x,x,farmer,4",
		    	
		    	"minecraft:sapling,12,x,minecraft:emerald,1,x,100,farmer,4",
		    	"minecraft:emerald,1,x,minecraft:emerald,12,x,100,farmer,4",
		    	
		    	// "minecraft:charcoal,14,x,minecraft:emerald,1,x,50,farmer,x",
		    	// "minecraft:dye:15,36,x,minecraft:emerald,1,x,100,farmer,x",

		    	// FISHERMAN =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:fish,3,x,minecraft:emerald,1,x,x,fisherman,x",
		    	"minecraft:fish:1,2,x,minecraft:emerald,1,x,50,fisherman,x",
		    	"minecraft:fish:2,1,x,minecraft:emerald,1,x,100,fisherman,x",
		    	"minecraft:fish:3,1,x,minecraft:emerald,8,x,250,fisherman,x",
		    	
		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,x,50,fisherman,0,minecraft:lure~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,x,250,fisherman,0,minecraft:lure~2",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,1,x,500,fisherman,0,minecraft:lure~3",
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,1,x,1000,fisherman,0,minecraft:lure~4",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,1,x,3000,fisherman,0,minecraft:lure~5",

		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,x,50,fisherman,1,minecraft:luck_of_the_sea~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,x,250,fisherman,1,minecraft:luck_of_the_sea~2",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,1,x,500,fisherman,1,minecraft:luck_of_the_sea~3",
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,1,x,1000,fisherman,1,minecraft:luck_of_the_sea~4",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,1,x,3000,fisherman,1,minecraft:luck_of_the_sea~5",
		    	
		    	// FLETCHER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,1,x,minecraft:bow,1,x,x,fletcher,x",
		    	"minecraft:emerald,1,x,minecraft:arrow,20,x,x,fletcher,x",
		    	
		    	"minecraft:string,9,x,minecraft:emerald,1,x,50,fletcher,x",
		    	"minecraft:feather,32,x,minecraft:emerald,1,x,50,fletcher,x",
		    	"minecraft:flint,8,x,minecraft:emerald,1,x,50,fletcher,x",
		    	
		    	"minecraft:log,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	"minecraft:log:1,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	"minecraft:log:2,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	"minecraft:log:3,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	"minecraft:log2,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	"minecraft:log2:1,16,x,minecraft:emerald,1,x,100,fletcher,x",
		    	
		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,x,50,fletcher,1,minecraft:power~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,x,250,fletcher,1,minecraft:power~2",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,1,x,500,fletcher,1,minecraft:power~3",
		    	"minecraft:emerald,40,x,minecraft:enchanted_book,1,x,1000,fletcher,1,minecraft:power~4",
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,1,x,2000,fletcher,1,minecraft:power~5",
		    	"minecraft:emerald,256,x,minecraft:enchanted_book,1,x,3000,fletcher,1,minecraft:power~6",

		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,x,50,fletcher,2,minecraft:punch~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,x,250,fletcher,2,minecraft:punch~2",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,1,x,500,fletcher,2,minecraft:punch~3",
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,1,x,1000,fletcher,2,minecraft:punch~4",
		    	"minecraft:emerald,96,x,minecraft:enchanted_book,1,x,2000,fletcher,2,minecraft:punch~5",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,1,x,3000,fletcher,2,minecraft:punch~2",
		    	
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,1,x,1000,fletcher,3,minecraft:infinity~1",
		    	
		    	"minecraft:emerald,40,x,minecraft:enchanted_book,0,YELLOW,500,fletcher,4,minecraft:flame~1",

		    	//"minecraft:emerald,1,x,minecraft:tipped_arrow,2,x,x,farmer,x,minecraft:healing~minecraft:tipped_arrow",
		    	//"minecraft:emerald,1,x,minecraft:tipped_arrow,2,x,x,farmer,x,minecraft:tipped_arrow~minecraft:healing",
		    	// "minecraft:emerald,16,x,minecraft:enchanted_book,1,x,50,librarian,1,minecraft:smite~5~minecraft:fire_aspect~2",
		    	// "minecraft:emerald,16,x,minecraft:golden_sword,1,x,100,weapon,1,Sword of Righteousness~minecraft:smite~5~minecraft:fire_aspect~5",

		    	// LIBRARIAN =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:paper,24,x,minecraft:emerald,1,x,x,librarian,x",
		    	
		    	"minecraft:enchanted_book,1,x,minecraft:emerald,2,x,x,librarian,x",
		    	
		    	"minecraft:emerald,1,x,minecraft:bookshelf,3,x,x,librarian,x",

		    	// CARTOGRAPHER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:filled_map,1,x,minecraft:emerald,2,x,x,cartographer,x",
		    	
		    	// CLERIC =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	// 0 undead
		    	"minecraft:rotten_flesh,20,x,minecraft:emerald,1,x,x,cleric,0",
		    	"minecraft:gold_ingot,3,x,minecraft:emerald,1,x,100,cleric,0",
		    	"minecraft:bone,12,x,minecraft:emerald,1,x,50,cleric,0",
		    	"minecraft:emerald,1,x,minecraft:bone,12,x,50,cleric,0",

		    	"minecraft:emerald,1,x,minecraft:potion,1,x,x,cleric,0,minecraft:healing~minecraft:potion",
		    	"minecraft:emerald,1,x,minecraft:potion,1,x,x,cleric,0,minecraft:healing~minecraft:splash_potion",
		    	"minecraft:emerald,2,x,minecraft:potion,1,x,1000,cleric,0,minecraft:healing~minecraft:lingering_potion",
		    	
		    	// 1 end
		    	
		    	"minecraft:emerald,1,x,minecraft:blaze_powder,9,x,50,cleric,1",
		    	"minecraft:emerald,1,x,minecraft:ender_pearl,1,x,250,cleric,1",
		    	"minecraft:emerald,4,x,minecraft:ghast_tear,1,x,500,cleric,1",
		    	"minecraft:emerald,1,x,minecraft:dragon_breath,1,x,1000,cleric,1",
		    	
		    	"minecraft:emerald,3,x,minecraft:potion,1,x,x,cleric,1,minecraft:regeneration~minecraft:potion",
		    	"minecraft:emerald,3,x,minecraft:potion,1,x,x,cleric,1,minecraft:regeneration~minecraft:splash_potion",
		    	"minecraft:emerald,4,x,minecraft:potion,1,x,1000,cleric,1,minecraft:regeneration~minecraft:lingering_potion",

		    	// 2 overworld
		    	
		    	"minecraft:emerald,1,x,minecraft:spider_eye,10,x,x,cleric,2",
		    	"minecraft:emerald,1,x,minecraft:gunpowder,8,x,50,cleric,2",
		    	"minecraft:emerald,1,x,minecraft:rabbit_foot,1,x,250,cleric,2",
		    	"minecraft:emerald,1,x,minecraft:slime_ball,2,x,500,cleric,2",
		    	
		    	"minecraft:emerald,3,x,minecraft:potion,1,x,x,cleric,2,minecraft:swiftness~minecraft:potion",
		    	"minecraft:emerald,3,x,minecraft:potion,1,x,x,cleric,2,minecraft:swiftness~minecraft:splash_potion",
		    	"minecraft:emerald,4,x,minecraft:potion,1,x,1000,cleric,2,minecraft:swiftness~minecraft:lingering_potion",
		    	
		    	// 3 flowers
		    	
		    	"minecraft:emerald,1,x,minecraft:yellow_flower,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:1,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:2,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:3,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:4,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:5,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:6,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:7,8,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:red_flower:8,8,x,x,cleric,3",
		    	
		    	"minecraft:emerald,1,x,minecraft:double_plant:1,3,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:double_plant:4,3,x,x,cleric,3",
		    	"minecraft:emerald,1,x,minecraft:double_plant:5,3,x,x,cleric,3",
		    	
		    	"minecraft:emerald,1,x,minecraft:experience_bottle,1,x,1000,cleric,3",

		    	// 4 shrooms
		    	
		    	"minecraft:sugar,36,x,minecraft:emerald,1,x,x,cleric,4",
		    	
		    	"minecraft:brown_mushroom,10,x,minecraft:emerald,1,x,50,cleric,4",
		    	"minecraft:emerald,1,x,minecraft:brown_mushroom,10,x,50,cleric,4",
		    	
		    	"minecraft:red_mushroom,10,x,minecraft:emerald,1,x,100,cleric,4",
		    	"minecraft:emerald,1,x,minecraft:red_mushroom,10,x,100,cleric,4",
		    	
		    	"minecraft:nether_wart,20,x,minecraft:emerald,1,x,250,cleric,4",
		    	"minecraft:emerald,1,x,minecraft:nether_wart,20,x,250,cleric,4",
		    			    	
		    	"minecraft:emerald,1,x,minecraft:potion,1,x,x,cleric,4,minecraft:poison~minecraft:splash_potion",
		    	"minecraft:emerald,2,x,minecraft:potion,1,x,1000,cleric,4,minecraft:poison~minecraft:lingering_potion",
		    	
		    	"minecraft:emerald,2,x,minecraft:potion,1,x,x,cleric,4,minecraft:harming~minecraft:splash_potion",
		    	"minecraft:emerald,3,x,minecraft:potion,1,x,1000,cleric,4,minecraft:harming~minecraft:lingering_potion",
		    	
		    	// x
		    	
		    	"minecraft:glass_bottle,48,x,minecraft:emerald,1,x,50,cleric,x",
		    	"minecraft:redstone,5,x,minecraft:emerald,1,x,250,cleric,x",
		    	"minecraft:glowstone_dust,16,x,minecraft:emerald,1,x,500,cleric,x",
		    	
		    	// ARMOR =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,2,x,minecraft:iron_boots,1,x,x,armor,x",
		    	"minecraft:emerald,3,x,minecraft:iron_leggings,1,x,x,armor,x",
		    	"minecraft:emerald,2,x,minecraft:iron_helmet,1,x,x,armor,x",
		    	"minecraft:emerald,4,x,minecraft:iron_chestplate,1,x,x,armor,x",
		    			    	
		    	"minecraft:emerald,1,x,minecraft:chainmail_boots,1,x,100,armor,x",
		    	"minecraft:emerald,2,x,minecraft:chainmail_leggings,1,x,100,armor,x",
		    	"minecraft:emerald,1,x,minecraft:chainmail_helmet,1,x,100,armor,x",
		    	"minecraft:emerald,3,x,minecraft:chainmail_chestplate,1,x,100,armor,x",
		    	
		    	"minecraft:iron_ingot,2,x,minecraft:emerald,1,x,x,armor,x",
		    	
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,0,BLUE,x,armor,x,minecraft:aqua_affinity~1",
		    	
		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,BLUE,50,armor,x,minecraft:respiration~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,BLUE,250,armor,x,minecraft:respiration~2",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,1,BLUE,500,armor,x,minecraft:respiration~3",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,1,BLUE,3000,armor,x,minecraft:respiration~4",
		    	
		    	"minecraft:emerald,12,x,minecraft:enchanted_book,2,BLUE,50,armor,x,minecraft:frost_walker~1",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,2,BLUE,250,armor,x,minecraft:frost_walker~2",
		    	"minecraft:emerald,64,x,minecraft:enchanted_book,2,BLUE,3000,armor,x,minecraft:frost_walker~3",

		    	"minecraft:emerald,8,x,minecraft:enchanted_book,3,BLUE,50,armor,x,minecraft:depth_strider~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,3,BLUE,250,armor,x,minecraft:depth_strider~2",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,3,BLUE,500,armor,x,minecraft:depth_strider~3",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,3,BLUE,3000,armor,x,minecraft:depth_strider~4",

		    	"minecraft:emerald,24,x,minecraft:enchanted_book,1,RED,500,armor,x,minecraft:thorns~1",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,1,RED,1000,armor,x,minecraft:thorns~2",
		    	"minecraft:emerald,96,x,minecraft:enchanted_book,1,RED,2000,armor,x,minecraft:thorns~3",
		    	"minecraft:emerald,192,x,minecraft:enchanted_book,1,RED,3000,armor,x,minecraft:thorns~4",
		    	
		    	"minecraft:emerald,14,x,minecraft:enchanted_book,1,YELLOW,250,armor,1,minecraft:fire_protection~1",
		    	"minecraft:emerald,28,x,minecraft:enchanted_book,1,YELLOW,500,armor,1,minecraft:fire_protection~2",
		    	"minecraft:emerald,42,x,minecraft:enchanted_book,1,YELLOW,1000,armor,1,minecraft:fire_protection~3",
		    	"minecraft:emerald,56,x,minecraft:enchanted_book,1,YELLOW,2000,armor,1,minecraft:fire_protection~4",
		    	"minecraft:emerald,112,x,minecraft:enchanted_book,1,YELLOW,3000,armor,1,minecraft:fire_protection~5",
		    	
		    	"minecraft:emerald,8,x,minecraft:enchanted_book,2,YELLOW,250,armor,1,minecraft:blast_protection~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,2,YELLOW,500,armor,1,minecraft:blast_protection~2",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,2,YELLOW,1000,armor,1,minecraft:blast_protection~3",
		    	"minecraft:emerald,32,x,minecraft:enchanted_book,2,YELLOW,2000,armor,1,minecraft:blast_protection~4",
		    	
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,2,YELLOW,3000,armor,1,minecraft:blast_protection~5",
//		    	water - resp - frost walk
//		    	sun - blast / fire
//		    	mount - prot - unbrea
//		    	thorn - thorn
//		    	leaf
//		    	swamp/skull
		    	
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,1,BROWN,250,armor,1,minecraft:protection~1",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,1,BROWN,500,armor,1,minecraft:protection~2",
		    	"minecraft:emerald,96,x,minecraft:enchanted_book,1,BROWN,1000,armor,1,minecraft:protection~3",
		    	"minecraft:emerald,192,x,minecraft:enchanted_book,1,BROWN,2000,armor,1,minecraft:protection~4",
		    	"minecraft:emerald,256,x,minecraft:enchanted_book,1,BROWN,3000,armor,1,minecraft:protection~5",
		    	
		    	// WEAPON =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,1,x,minecraft:iron_sword,1,x,x,weapon,x",
		    	
		    	"minecraft:iron_ingot,2,x,minecraft:emerald,1,x,x,armor,x",
		    	
		    	"minecraft:emerald,1,x,minecraft:shield,1,x,x,armor,x",
		    	
		    	"minecraft:emerald,8,x,minecraft:enchanted_book,1,YELLOW,250,weapon,1,minecraft:smite~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,1,YELLOW,500,weapon,1,minecraft:smite~2",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,1,YELLOW,1000,weapon,1,minecraft:smite~3",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,1,YELLOW,2000,weapon,1,minecraft:smite~4",
		    	"minecraft:emerald,96,x,minecraft:enchanted_book,1,YELLOW,2000,weapon,1,minecraft:smite~5",
		    	
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,2,YELLOW,250,weapon,1,minecraft:fire_aspect~1",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,2,YELLOW,1000,weapon,1,minecraft:fire_aspect~2",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,2,YELLOW,3000,weapon,1,minecraft:fire_aspect~3",

		    	"minecraft:emerald,8,x,minecraft:enchanted_book,3,YELLOW,50,weapon,1,minecraft:smite~1",
		    	"minecraft:emerald,16,x,minecraft:enchanted_book,3,YELLOW,250,weapon,1,minecraft:smite~2",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,3,YELLOW,500,weapon,1,minecraft:smite~3",
		    	"minecraft:emerald,36,x,minecraft:enchanted_book,3,YELLOW,1000,weapon,1,minecraft:smite~4",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,3,YELLOW,2000,weapon,1,minecraft:smite~5",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,3,YELLOW,3000,weapon,1,minecraft:smite~6",

		    	"minecraft:emerald,12,x,minecraft:enchanted_book,1,BROWN,250,weapon,1,minecraft:unbreaking~1",
		    	"minecraft:emerald,24,x,minecraft:enchanted_book,1,BROWN,500,weapon,1,minecraft:unbreaking~2",
		    	"minecraft:emerald,48,x,minecraft:enchanted_book,1,BROWN,1000,weapon,1,minecraft:unbreaking~3",
		    	"minecraft:emerald,128,x,minecraft:enchanted_book,1,BROWN,3000,weapon,1,minecraft:unbreaking~4",
		    	
		    	// TOOL =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	// 0 ores
		    	"minecraft:iron_pickaxe,1,x,minecraft:emerald,1,x,x,tool,0",
		    	
		    	"minecraft:emerald,1,x,minecraft:coal,14,x,x,tool,0",
		    	"minecraft:emerald,1,x,minecraft:iron_ingot,2,x,50,tool,0",
		    	"minecraft:emerald,1,x,minecraft:gold_ingot,3,x,100,tool,0",
		    	"minecraft:emerald,1,x,minecraft:redstone,5,x,250,tool,0",
		    	"minecraft:emerald,1,x,minecraft:dye:4,3,x,500,tool,0",
		    	"minecraft:emerald,10,x,minecraft:diamond,1,x,1000,tool,0",
		    	
		    	// MASON =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

		    	"minecraft:iron_pickaxe,1,x,minecraft:emerald,1,x,x,tool,1",		    	
		    	
		    	"minecraft:emerald,1,x,minecraft:cobblestone,64,x,x,tool,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:stone:1,64,x,100,tool,1",
		    	"minecraft:emerald,1,x,minecraft:stone:3,64,x,100,tool,1",
		    	"minecraft:emerald,1,x,minecraft:stone:5,64,x,100,tool,1",

		    	"minecraft:emerald,1,x,minecraft:obsidian,12,x,500,tool,1",
		    	// "minecraft:obsidian,12,x,minecraft:emerald,1,x,500,tool,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:quartz,16,x,500,tool,1",
		    	// "minecraft:quartz,12,x,minecraft:emerald,1,x,x,tool,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:netherbrick,32,x,250,tool,1",
		    	// "minecraft:nether_bricks,12,x,minecraft:emerald,1,x,x,tool,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:end_stone,16,x,250,tool,1",
		    	// "minecraft:end_stone,12,x,minecraft:emerald,1,x,x,tool,1",
		    	
	    		"minecraft:emerald,1,x,minecraft:glowstone,2,x,500,tool,1",

		    	"minecraft:emerald,1,x,minecraft:iron_pickaxe,1,x,x,tool,2",
		    	
		    	"minecraft:emerald,1,x,minecraft:stone_bricks,32,x,x,tool,2",
		    	"minecraft:stone_bricks,32,x,minecraft:emerald,1,x,x,tool,2",
		    	
		    	// 3 wood
		    	"minecraft:iron_axe,1,x,minecraft:emerald,1,x,x,tool,3",
		    	
		    	"minecraft:emerald,1,x,minecraft:log,16,x,x,tool,3",
		    	"minecraft:emerald,1,x,minecraft:log:1,16,x,x,tool,3",
		    	"minecraft:emerald,1,x,minecraft:log:2,16,x,x,tool,3",
		    	"minecraft:emerald,1,x,minecraft:log:3,16,x,x,tool,3",
		    	"minecraft:emerald,1,x,minecraft:log2,16,x,x,tool,3",
		    	"minecraft:emerald,1,x,minecraft:log2:1,16,x,x,tool,3",

		    	// 4 sands
		    	
		    	"minecraft:emerald,1,x,minecraft:iron_shovel,1,x,x,tool,4",

		    	"minecraft:emerald,1,x,minecraft:dirt,64,x,x,tool,4",
		    	"minecraft:emerald,1,x,minecraft:sand,64,x,50,tool,4",
		    	"minecraft:emerald,1,x,minecraft:gravel,32,x,100,tool,4",
		    	"minecraft:emerald,1,x,minecraft:clay,16,x,100,tool,4",
		    	"minecraft:emerald,1,x,minecraft:red_sand,64,x,500,tool,4",
		    	
		    	"minecraft:dirt,64,x,minecraft:emerald,1,x,x,tool,4",
		    	"minecraft:sand,64,x,minecraft:emerald,1,x,50,tool,4",
		    	"minecraft:gravel,32,x,minecraft:emerald,1,x,100,tool,4",
		    	"minecraft:clay,16,x,minecraft:emerald,1,x,100,tool,4",
		    	"minecraft:red_sand,64,x,minecraft:emerald,1,x,500,tool,4",


		    	// BUTCHER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,1,x,minecraft:cooked_beef,10,x,x,butcher,x",
		    	"minecraft:emerald,1,x,minecraft:cooked_porkchop,10,x,x,butcher,x",
		    	"minecraft:emerald,1,x,minecraft:cooked_mutton,12,x,x,butcher,x",
		    	"minecraft:emerald,1,x,minecraft:cooked_chicken,14,x,x,butcher,x",
		    	"minecraft:emerald,1,x,minecraft:cooked_rabbit,16,x,x,butcher,x",
		    	
		    	"minecraft:beef,10,x,minecraft:emerald,1,x,x,butcher,x",
		    	"minecraft:porkchop,10,x,minecraft:emerald,1,x,x,butcher,x",
		    	"minecraft:mutton,12,x,minecraft:emerald,1,x,x,butcher,x",
		    	"minecraft:chicken,14,x,minecraft:emerald,1,x,x,butcher,x",
		    	"minecraft:rabbit,16,x,minecraft:emerald,1,x,x,butcher,x",
		    	
		    	// LEATHER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,1,x,minecraft:leather,10,x,x,leather,x",
		    	"minecraft:leather,10,x,minecraft:emerald,1,x,x,leather,x",
		    	
		    	"minecraft:rabbit_hide,4,x,minecraft:emerald,1,x,x,leather,0",
		    	
		    	"minecraft:string,12,x,minecraft:emerald,1,x,x,leather,1",
		    	
		    	"minecraft:emerald,1,x,minecraft:leather_boots,1,x,x,leather,x",
		    	"minecraft:emerald,2,x,minecraft:leather_leggings,1,x,x,leather,x",
		    	"minecraft:emerald,1,x,minecraft:leather_helmet,1,x,x,leather,x",
		    	"minecraft:emerald,3,x,minecraft:leather_chestplate,1,x,x,leather,x",
		    	
		    	"minecraft:emerald,1,x,toroquest:toro_leather,5,x,100,leather,2",
		    	"toroquest:toro_leather,5,x,minecraft:emerald,1,x,100,leather,2",
		    	
		    	"minecraft:emerald,2,x,toroquest:toro_leather_boots,1,x,100,leather,2",
		    	"minecraft:emerald,3,x,toroquest:toro_leather_leggings,1,x,100,leather,2",
		    	"minecraft:emerald,2,x,toroquest:toro_leather_helmet,1,x,100,leather,2",
		    	"minecraft:emerald,4,x,toroquest:toro_leather_chestplate,1,x,100,leather,2",
		    			    	
		    	"minecraft:emerald,16,x,minecraft:saddle,1,x,250,leather,3",
		    	
		    	"minecraft:emerald,32,x,minecraft:gold_horse_armor,1,x,500,leather,3",
		    	"minecraft:emerald,64,x,minecraft:iron_horse_armor,1,x,1000,leather,3",
		    	"minecraft:emerald,96,x,minecraft:diamond_horse_armor,1,x,2000,leather,3",

		    	// SHEPHERD =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	// 0
		    	"minecraft:wool,16,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool,16,x,x,shepherd,x",
		    	
		    	"minecraft:wool:14,16,x,minecraft:emerald,1,RED,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:14,16,RED,x,shepherd,x",
		    	
		    	"minecraft:wool:13,16,x,minecraft:emerald,1,GREEN,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:13,16,GREEN,x,shepherd,x",
		    	
		    	"minecraft:wool:9,16,x,minecraft:emerald,1,BLUE,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:9,16,BLUE,x,shepherd,x",
		    	
		    	"minecraft:wool:15,16,x,minecraft:emerald,1,BLACK,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:15,16,BLACK,x,shepherd,x",
		    	
		    	"minecraft:wool:4,16,x,minecraft:emerald,1,YELLOW,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:4,16,YELLOW,x,shepherd,x",
		    	
		    	"minecraft:wool:12,16,x,minecraft:emerald,1,BROWN,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:wool:12,16,BROWN,x,shepherd,x",		    	
		    	
		    	"minecraft:emerald,1,x,minecraft:string,12,x,x,shepherd,x",
		    	
		    	"minecraft:wheat,16,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:shears,2,x,minecraft:emerald,1,x,x,shepherd,x",

		    	"minecraft:egg,16,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:emerald,1,x,minecraft:egg,16,x,x,shepherd,x",
		    	
		    	"minecraft:emerald,1,x,minecraft:feather,28,x,x,shepherd,x",
		    	
		    	"minecraft:wheat_seeds,24,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:melon_seeds,24,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:beetroot_seeds,24,x,minecraft:emerald,1,x,x,shepherd,x",
		    	"minecraft:pumpkin_seeds,24,x,minecraft:emerald,1,x,x,shepherd,x",
		    			    	
		    	// NITWIT =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:poisonous_potato,1,x,minecraft:emerald,1,x,x,nitwit,0",
		    	
		    	"minecraft:emerald,3,x,minecraft:emerald,1,x,x,nitwit,1",
		    	
		    	"minecraft:anvil:2,1,x,minecraft:emerald,12,x,x,nitwit,2",
		    	
		    	"minecraft:bedrock,1,x,minecraft:emerald,6,x,x,nitwit,3",
		    	
		    	"minecraft:emerald,5,x,minecraft:jukebox,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_13,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_cat,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_blocks,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_chirp,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_far,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_mall,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_mellohi,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_stal,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_strad,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_ward,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_11,1,x,x,nitwit,4",
		    	"minecraft:emerald,64,x,minecraft:record_wait,1,x,x,nitwit,4",
		    	
		    	// SHOPKEEPER =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		    	
		    	"minecraft:emerald,5,x,toroquest:recruitment_papers,1,x,0,shopkeeper,x",
		    	"minecraft:emerald,3,x,minecraft:experience_bottle,1,x,x,shopkeeper,x",
		    	"minecraft:emerald,5,x,minecraft:lead,1,x,x,shopkeeper,x",
		    	"minecraft:emerald,64,x,toroquest:city_key,1,x,500,shopkeeper,x",
		    	"toroquest:trophy_titan,1,x,toroquest:ender_idol,2,x,1000,shopkeeper,x",
		    	"toroquest:legendary_bandit_helmet,1,x,toroquest:ender_idol,2,x,1200,shopkeeper,x",
		    	"toroquest:trophy_pig,1,x,toroquest:ender_idol,2,x,1400,shopkeeper,x",
		    	"toroquest:trophy_skeleton,1,x,toroquest:ender_idol,2,x,1600,shopkeeper,x",
		    	"toroquest:trophy_spider,1,x,toroquest:ender_idol,2,x,1800,shopkeeper,x",
		    	"toroquest:trophy_archmage,1,x,toroquest:ender_idol,2,x,2000,shopkeeper,x",
		    	"toroquest:trophy_beholder,1,x,toroquest:ender_idol,2,x,2200,shopkeeper,x"
		    },
					
			"A list of trades for villagers. Follow the format to add trades (do not include spaces, it is shown like this to make it easier to understand):"
			+ "\n  modId:itemToSell  , amount , secondItemToSell, modId:itemToBuy:damageValue , amount , province , minRepRequired ,   job    , jobVarient\n"
	    	+ "\n  minecraft:emerald ,   1    ,        x        ,     minecraft:wool:14       ,   16   ,     x    ,       100      , shepherd ,      4"
			+ "\nFor ENCHANTED ITEMS, use the following format:\n\n" 
			+ "\nitemToSell,amount,secondItemToSell,itemToBuy,amount,minRepRequired,province,job,jobVarient,enchantedItemName~enchantment~power~enchantment~power\n" 
			+ "\n  modId:itemToSell , amount , secondItemToSell, modId:enchantedItem:damageValue , amount , province , minRepRequired ,     job    , jobVarient ~ enchantedItemDisplayName ~ modId:enchantment ~ power ~   modId:enchantment ~   power\n"
	    	+ "\n minecraft:emerald ,   1    ,        x        ,     minecraft:diamond_sword     ,   1    ,  YELLOW  ,      2000      ,    weapon  ,      0     ~        Sun Blade         ~  minecraft:smite  ~   3   ~  minecraft:sharpness  ~   5"
			+ "\nYou can add as many additional enchantments as you'd like. For ENCHANTED BOOKS, put minecraft:enchanted_book in place of modId:enchantedItem:damageValue, and DO NOT include enchantedItemDisplayName"
	    	+ "\nHere is a trade example for potions --> minecraft:emerald,1,x,minecraft:potion,1,x,x,cleric,0,minecraft:healing~minecraft:splash_potion"
			+ "\n\nItem to Sell:  minecraft:wheat, minecraft:stone, toroquest:recruitment_papers, ... visit https://www.minecraftinfo.com/idnamelist.htm OR https://minecraftitemids.com/ for help\n"
			+ "\nItem Amount:   1, 2, 3... 64.\n\n"
			+ "\n2nd Item:      x, minecraft:wheat, minecraft:stone, toroquest:recruitment_papers, ...\n"
			+ "\nItem to Buy:   minecraft:wheat, minecraft:stone, toroquest:recruitment_papers, ...\n"
			+ "\nItem Amount:   1, 2, 3... 128.\n\n"
			+ "\nProvinces:     x, GREEN, BROWN, RED, BLUE, YELLOW, BLACK.\n\n"
			+ "\nRep Required:  x, -50, 0, 100... 3000\n\n"
			+ "\nJobs:          x, farmer, fisherman, shepherd, fletcher, librarian, cartographer, cleric, armor, weapon, tool, butcher, leather, nitwit, shopkeeper, ..."
			+ "\nJob varients:  x, 0, 1, 2, 3, 4."
			+ "\n\nVillagers will not trade with players at or below -50 rep. Prices are not improved for the player past 3000.");

			scrollTradeItem = config.getString("scrollTradeItem", CATEGORY_TRADES, "minecraft:emerald",
					"The item you will use to trade for a shopkeeper's teleport scroll. Delete this text to disable trade.");
			
			scrollTradeAmount = config.getInt("scrollTradeAmount", CATEGORY_TRADES, 3, 0, 128,
					"The amount of scrollTradeItem a shopkeeper's teleport scroll will cost you. Set to 0 to disable this trade.");
			// BANDIT =-=-=-=-=-=-=-=-=-=-=

			banditOneHandedMeleeWeapons = config.getStringList("banditOneHandedMeleeWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:iron_sword",
				    	"minecraft:iron_axe",
				    	"spartanweaponry:caestus",
				    	"spartanweaponry:caestus_studded",
				    	"spartanweaponry:hammer_iron",
				    	"spartanweaponry:mace_iron",
				    	"spartanweaponry:saber_iron",
				    	"spartanweaponry:katana_iron",
				    	"spartanweaponry:rapier_iron",
				    	"spartanweaponry:warhammer_iron",
				    	"spartanweaponry:battleaxe_iron",
				    	"spartanweaponry:longsword_iron"
				    },
				    
					"One-handed melee weapons that bandits will spawn with.");
			
			banditTwoHandedMeleeWeapons = config.getStringList("banditTwoHandedMeleeWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:iron_sword",
				    	"minecraft:iron_axe",
				    	"spartanweaponry:spear_iron",
				    	"spartanweaponry:longsword_iron",
				    	"spartanweaponry:battleaxe_iron",
				    	"spartanweaponry:greatsword_iron",
				    	"spartanweaponry:glaive_iron",
				    	"spartanweaponry:halberd_iron"
				    },
				    
					"Two-handed melee weapons that bandits will spawn with.");
		
			banditRangedWeapons = config.getStringList("banditRangedWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:bow"
				    },
				    
					"Ranged weapons that bandits will spawn with (crossbows currently do not work).");
			
			banditShields = config.getStringList("banditShields", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:shield",
				    	"spartanshields:shield_basic_wood",
				    	"spartanshields:shield_basic_stone",
				    	"spartanshields:shield_basic_iron",
				    	"spartanshields:shield_basic_gold",
				    	"spartanshields:shield_basic_obsidian"
				    },
				    
					"Shields that bandits will spawn with.");
			
			// ORC =-=-=-=-=-=-=-=-=-=-=
			
			orcOneHandedMeleeWeapons = config.getStringList("orcOneHandedMeleeWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:stone_sword",
				    	"minecraft:stone_axe",
				    	"spartanweaponry:caestus",
				    	"spartanweaponry:caestus_studded",
				    	"spartanweaponry:club_wood",
				    	"spartanweaponry:club_studded",
				    	"spartanweaponry:hammer_stone",
				    	"spartanweaponry:mace_stone",
				    	"spartanweaponry:saber_stone",
				    	"spartanweaponry:katana_stone",
				    	"spartanweaponry:warhammer_stone",
				    	"spartanweaponry:battleaxe_stone",
				    	"spartanweaponry:longsword_stone"
				    },
				    
					"One-handed melee weapons that orcs will spawn with.");
			
		orcTwoHandedMeleeWeapons = config.getStringList("orcTwoHandedMeleeWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
			    		"minecraft:stone_sword",
				    	"minecraft:stone_axe",
				    	"spartanweaponry:spear_stone",
				    	"spartanweaponry:longsword_stone",
				    	"spartanweaponry:battleaxe_stone",
				    	"spartanweaponry:greatsword_stone",
				    	"spartanweaponry:glaive_stone",
				    	"spartanweaponry:halberd_stone"
				    },
				    
					"Two-handed melee weapons that orcs will spawn with.");
		
			orcRangedWeapons = config.getStringList("orcRangedWeapons", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"spartanweaponry:longbow_wood",
				    	"spartanweaponry:longbow_leather",
				    	"spartanweaponry:longbow_iron"
				    },
				    
					"Ranged weapons that orcs will spawn with (crossbows currently do not work).");
			
			orcShields = config.getStringList("orcShields", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"spartanshields:shield_tower_wood",
						"spartanshields:shield_tower_stone",
						"spartanshields:shield_tower_iron",
						"spartanshields:shield_tower_gold",
						"spartanshields:shield_tower_obsidian"
				    },
				    
					"Shields that orcs will spawn with.");
			
			enchantFirstBanditAndOrcMeleeWeapon = config.getStringList("enchantFirstBanditAndOrcMeleeWeapon", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:sharpness,2",
				    	"minecraft:sharpness,4",
				    	"minecraft:smite,4",
				    	"minecraft:bane_of_arthropods,4",
				    	"minecraft:knockback,4",
				    	"minecraft:looting,2",
				    	"minecraft:sweeping,3",
				    },
				    
					"First enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			enchantSecondBanditAndOrcMeleeWeapon = config.getStringList("enchantSecondBanditAndOrcMeleeWeapon", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:unbreaking,4",
				    	"minecraft:unbreaking,4",
				    	"minecraft:unbreaking,4",
				    	"minecraft:fire_aspect,2",
				    },
				    
					"Second enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			enchantFirstBanditAndOrcRanged = config.getStringList("enchantFirstBanditAndOrcRanged", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:power,2",
				    	"minecraft:power,5",
				    	"minecraft:punch,5",
				    	"minecraft:flame,1",
				    	"minecraft:flame,1",
				    },
				    
					"First enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			enchantSecondBanditAndOrcRanged = config.getStringList("enchantSecondBanditAndOrcRanged", CATEGORY_WEAPONS,
				    
					new String[]
				    {
				    	"minecraft:unbreaking,4",
				    	"minecraft:unbreaking,4",
				    	"minecraft:unbreaking,4",
				    	"minecraft:infinity,1",
				    },
				    
					"Second enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			enchantFirstBanditAndOrcShield = config.getStringList("enchantFirstBanditAndOrcShield", CATEGORY_WEAPONS,
				    
					new String[]
				    {
					    "minecraft:unbreaking,4",
				    },
				    
					"First enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			mobsList = config.getStringList("questKillMobsList", CATEGORY_MOBS,
				    
					new String[]
				    {
				    	"EntityZombie~Zombie~10~20~0.6~X~0~Countless undead have risen from fallen villagers after the recent bandit attacks. @p, cut down any walking corpses you see and lay their souls to rest.",
				    	"EntitySentry~Bandit~6~12~1.0~X~250~The bandit attacks have getting worse, @p. Cut them down.",
				    	"EntityDragon~Dragon~1~1~32.0~X~1000~My scouts have reported a seeing a dragon not too far from here! Go, slay it at once! Before it catches scent of the village!",
				    },
				    
					"Custom configuration for the (Kill Mobs) quest:\n mobToKill~mobDisplayName~minimumNumberOfKills~maximimumNumberOfKills~EmeraldsPerKill~ProvinceAllowed~minReputationRequired~lordDialogue \n ProvinceAllowed: X, RED, YELLOW, BLUE, BROWN, BLACK");

//			pitQuestList = config.getStringList("pitQuestMobList", CATEGORY_MOBS,
//				    
//					new String[]
//				    {
//				    	"EntityZombie~Zombie~12~16~0.6~X~Countless undead have risen from fallen villagers after the recent bandit attacks. @p, cut down any walking corpses you see and lay their souls to rest.",
//				    	"EntitySentry~Bandit~8~12~1.0~X~The bandit attacks have getting worse, @p. Cut them down.",
//				    },
//				    
//					"Custom configuration for the (Underground Mob Pit / Heirloom) quest:\n mobToKill|mobDisplayName|minimumNumberOfKills|maximimumNumberOfKills|EmeraldsPerKill|ProvinceAllowed|lordDialogue \n ProvinceAllowed: X, RED, YELLOW, BLUE, BROWN, BLACK");
//			
//			public static String[] pitQuestList = new String[]{};
//			public static ArrayList<pitMob> pitQuestMobs = new ArrayList<pitMob>();
			
			
//			enchantSecondBanditAndOrcShield = config.getStringList("enchantSecondBanditAndOrcShield", CATEGORY_WEAPONS,
//				    
//					new String[]
//				    {
//				    		
//				    },
//				    
//					"Second enchant that bandits will spawn with. Format is - modid:enchant,level (add duplicate of the same enchant to increase its chance)");
			
			enchantFirstBanditAndOrcChance = config.getInt("enchantFirstBanditAndOrcChance", CATEGORY_SPAWNING, 20, 0, 100, "X chance out of 100 for the first enchant to be applied to weapon/ shield/ bow.");
			
			enchantSecondBanditAndOrcChance = config.getInt("enchantSecondBanditAndOrcChance", CATEGORY_SPAWNING, 60, 0, 100, "X chance out of 100 for an additional enchant to be applied to weapon/ shield/ bow (NOTE: this second enchant only has a chance to be applied if the first enchant is applied).");
			
			guardWeapon_RED_BRIAR = config.getString("guardWeapon_RED_BRIAR", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Briar guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_RED_BRIAR = config.getString("guardShield_RED_BRIAR", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Briar guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
			
			guardWeapon_GREEN_WILD = config.getString("guardWeapon_GREEN_WILD", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Wild guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_GREEN_WILD  = config.getString("guardShield_GREEN_WILD", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Wild guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
			
			guardWeapon_BROWN_MITHRIL = config.getString("guardWeapon_BROWN_MITHRIL", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Mithril guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_BROWN_MITHRIL = config.getString("guardShield_BROWN_MITHRIL", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Mithril guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
			
			guardWeapon_BLUE_GLACIER = config.getString("guardWeapon_BLUE_GLACIER", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Glacier guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_BLUE_GLACIER = config.getString("guardShield_BLUE_GLACIER", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Glacier guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
			
			guardWeapon_YELLOW_DAWN = config.getString("guardWeapon_YELLOW_DAWN", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Dawn guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_YELLOW_DAWN = config.getString("guardShield_YELLOW_DAWN", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Dawn guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
			
			guardWeapon_BLACK_MOOR = config.getString("guardWeapon_BLACK_MOOR", CATEGORY_WEAPONS, "minecraft:iron_sword",
					"Weapons that House Moor guards use. Cannot be blank or invalid, as weapons are built into the AI. Accepts weapons from other mods. Example - - - spartanweaponry:saber_iron");
			
			guardShield_BLACK_MOOR = config.getString("guardShield_BLACK_MOOR", CATEGORY_WEAPONS, "minecraft:shield",
					"Shields that House Moor guards use. Cannot be blank or invalid, as shields are built into the AI. Accepts sheilds from other mods. Example - - - spartanshields:shield_tower_wood");
			
		
			for ( String s : tradeList )
			{
				try
				{
					String[] list = s.split(",");
					Trade trade = new Trade();
					trade.sellName = list[0];
					trade.sellAmount = list[1];
					trade.sellOptional = list[2];
					trade.buyName = list[3];
					trade.buyAmount = list[4];
					trade.province = list[5];
					trade.minimunRepRequired = list[6];
					trade.job = list[7];
					trade.varient = list[8];
					try
					{
						trade.enchantment = list[9];
					}
					catch(Exception e)
					{
						trade.enchantment = null;
					}
					trades.add(trade);
				}
				catch ( Exception e )
				{
					System.out.print("Trades config incorrect format! It follows a strict format. Error:" + e );
				}
			}
			
			for ( String s : mobsList )
			{
				try
				{
					String[] list = s.split("~");
					KillMob mob = new KillMob();
					mob.mobName = list[0];
					mob.mobDisplayName = list[1];
					mob.minKills = Integer.parseInt(list[2]);
					mob.maxKills = Integer.parseInt(list[3]);
					mob.emeraldsPerKill = Double.parseDouble(list[4]);
					mob.provinceAllowed = list[5];
					mob.minRepRequired = Integer.parseInt(list[6]);
					mob.acceptChat = list[7];
					mobs.add(mob);
				}
				catch ( Exception e )
				{
					System.out.print("Mobs config incorrect format! It follows a strict format. Error:" + e + s );
				}
			}
			
//			if ( !guardTargetBlacklist.isEmpty() )
//			{
//				guardTargetBlacklistLIST = guardTargetBlacklist.split(",");
//			}
//			
			config.save();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equalsIgnoreCase(ToroQuest.MODID))
		{
			loadConfiguration();
		}
	}
	
	@Nullable
	public static class Trade
	{
		public String sellName = "";
		public String sellAmount = "";
		public String sellOptional = "";
		public String buyName = "";
		public String buyAmount = "";
		public String minimunRepRequired = "";
		public String province = "";
		public String job = "";
		public String varient = "";
		public String enchantment = null;
	}
	
	@Nullable
	public static class KillMob
	{
		public String mobName = "";
		public String mobDisplayName = "";
		public int minKills = 0;
		public int maxKills = 0;
		public double emeraldsPerKill = 0.0;
		public String provinceAllowed = "";
		public int minRepRequired = 0;
		public String acceptChat = "";
		
	}
}