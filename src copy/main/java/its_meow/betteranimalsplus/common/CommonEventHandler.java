package its_meow.betteranimalsplus.common;

import its_meow.betteranimalsplus.Ref;
import its_meow.betteranimalsplus.common.entity.EntityLamprey;
import its_meow.betteranimalsplus.init.ModLootTables;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Ref.MOD_ID)
public class CommonEventHandler {

//    @SubscribeEvent
//    public static void onDeathOfEntity(LivingDeathEvent e) 
//    {
//        if(e.getSource().getImmediateSource() instanceof EntityBoar) 
//        {
//            EntityBoar boar = (EntityBoar) e.getSource().getImmediateSource();
//            boar.setInLove(null);
//            //BlockPos p = boar.getPosition();
//            //boar.world.spawnParticle(EnumParticleTypes.HEART, p.getX(), p.getY(), p.getZ(), 0.0F, 0.05F, 0.0F);
//        }
//        else if(e.getSource().getImmediateSource() instanceof EntityPlayerMP)
//        {
//            EntityPlayerMP player = (EntityPlayerMP) e.getSource().getImmediateSource();
//            if(e.getEntity() instanceof EntityBrownBear || e.getEntity() instanceof EntityPolarBear)
//            {
//                if(player.getHeldItemMainhand().isEmpty())
//                {
//                    ModTriggers.PUNCH_BEAR_DEATH.trigger(player);
//                }
//            }
//            else if(e.getEntity() instanceof EntitySquirrel && !player.getAdvancements().getProgress(player.getServer().getAdvancementManager().getAdvancement(new ResourceLocation("betteranimalsplus:squirrel_kill_100"))).isDone())
//            {
//                NBTTagCompound pTag = player.getEntityData();
//                if(!pTag.hasKey("betteranimalsplus", Constants.NBT.TAG_COMPOUND))
//                {
//                    pTag.setTag("betteranimalsplus", new NBTTagCompound());
//                }
//                NBTTagCompound bTag = pTag.getCompoundTag("betteranimalsplus");
//                
//                if(bTag.hasKey("squirrel_kills", Constants.NBT.TAG_INT))
//                {
//                    int newVal = bTag.getInteger("squirrel_kills") + 1;
//                    bTag.setInteger("squirrel_kills", newVal);
//                    if(ModTriggers.SQUIRREL_KILL_TRIGGERS.containsKey(newVal))
//                    {
//                        ModTriggers.SQUIRREL_KILL_TRIGGERS.get(newVal).trigger(player);
//                    }
//                    if(newVal >= 100)
//                    {
//                        bTag.removeTag("squirrel_kills");
//                    }
//                }
//                else
//                {
//                    bTag.setInteger("squirrel_kills", 1);
//                    if(ModTriggers.SQUIRREL_KILL_TRIGGERS.containsKey(1))
//                    {
//                        ModTriggers.SQUIRREL_KILL_TRIGGERS.get(1).trigger(player);
//                    }
//                }
//            }
//        }
//    }
    
//    @SubscribeEvent
//    public static void onBlockActivate(PlayerInteractEvent.RightClickBlock event) {
//        if(event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.JUKEBOX) {
//            TileEntity te = event.getWorld().getTileEntity(event.getPos());
//            if(te instanceof TileEntityJukebox) {
//                TileEntityJukebox box = (TileEntityJukebox) te;
//                Item held = event.getEntityPlayer().getHeldItem(event.getHand()).getItem();
//                if(box.getRecord().getItem() == ModItems.RECORD_CRAB_RAVE) {
//                    List<EntityCrab> crabs = event.getWorld().getEntitiesWithinAABB(EntityCrab.class, event.getEntityPlayer().getEntityBoundingBox().grow(50));
//                    for(EntityCrab crab : crabs) {
//                        crab.unCrabRave();
//                    }
//                } else if(held == ModItems.RECORD_CRAB_RAVE) {
//                    if(event.getEntityPlayer() instanceof EntityPlayerMP) {
//                        ModTriggers.USE_CRAB_DISK.trigger((EntityPlayerMP) event.getEntityPlayer());
//                    }
//                    List<EntityCrab> crabs = event.getWorld().getEntitiesWithinAABB(EntityCrab.class, event.getEntityPlayer().getEntityBoundingBox().grow(50));
//                    for(EntityCrab crab : crabs) {
//                        crab.crabRave();
//                    }
//                } else if(held == ModItems.RECORD_WALRUS) {
//                    if(event.getEntityPlayer() instanceof EntityPlayerMP) {
//                        ModTriggers.USE_WALRUS_DISK.trigger((EntityPlayerMP) event.getEntityPlayer());
//                    }
//                }
//            }
//        }
//    }
    
    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if(event.getName().equals(LootTableList.ENTITIES_WOLF)) {
            event.getTable().addPool(new LootPool(new LootEntry[] {new LootEntryTable(ModLootTables.WOLF_SNOWY, 1, 0, new LootCondition[0], "betteranimalsplus_wolf_snowy_inject_entry")}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0), "snowy_pelt"));
        }
    }
    
    
//    @SubscribeEvent
//    public static void onPlayerDamage(RenderLivingEvent event)
//    {
//	    GlStateManager.rotate(110, 0.0F, 1.0F, 0.0F);
//	    GlStateManager.rotate(110, 0.0F, 0.0F, 1.0F);
//	    GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
//    }
    
    
    // public static Minecraft mc = Minecraft.getMinecraft();
	

    
    
//    @SubscribeEvent
//    public static void onPlayerDamage(AttackEntityEvent event)
//    {
//    	try {
//    	System.out.println(event.getEntity().world.isRemote);
//
//    	if ( event.getEntity() instanceof EntityPlayer )
//    	{
//    	EntityPlayer p = (EntityPlayer)event.getEntity();
// 		
//			p.renderOffsetY = -31.8F;
//			p.renderOffsetZ = -31.8F;
//			p.renderOffsetX = -31.8F;
//	    	
//	    	p.bedLocation = p.getPosition();
//	    	
//	    	Field[] fields = EntityPlayer.class.getDeclaredFields();
//			
//			for ( Object a : fields )
//			{
//				System.out.println(a);
//				if ( a.toString().contains("sleeping")) a = true;
//				if ( a.toString().contains("sleepTimer")) a = 90;
//			}
//
//    	}}catch(Exception e) {}
//    	
//        if (event.getEntityPlayer() instanceof EntityPlayerMP && (event.getTarget() instanceof EntityBear || event.getTarget() instanceof EntityPolarBear))
//        {
//            if(event.getEntityPlayer().getHeldItemMainhand().isEmpty())
//            {
//                ModTriggers.PUNCH_BEAR.trigger((EntityPlayerMP) event.getEntityPlayer());
//            }
//        }
//    }
    
    
    
    
    
    
    
    
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.Clone event) {
        if(event.getOriginal().getEntityData().hasKey("betteranimalsplus", Constants.NBT.TAG_COMPOUND)) {
            event.getEntityPlayer().getEntityData().setTag("betteranimalsplus", event.getOriginal().getEntityData().getCompoundTag("betteranimalsplus"));
        }
    }
    
    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityLamprey && !(event.getEntity() instanceof EntityPlayer)) {
            event.getDrops().clear();
        }
    }

}