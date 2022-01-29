package its_meow.betteranimalsplus.common;

import its_meow.betteranimalsplus.common.entity.EntityBrownBear;
import its_meow.betteranimalsplus.common.entity.EntityFeralWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// https://github.com/CreativeMD/PlayerRevive/blob/1.12/src/main/java/com/creativemd/playerrevive/client/ReviveEventClient.java

public class ClientEventHandler
{
//	@SuppressWarnings("deprecation")
//	public static Field sleeping = ReflectionHelper.findField(EntityPlayer.class, new String[] { "sleeping", "field_71083_bS" });
//	@SuppressWarnings("deprecation")
//	public static Field sleepTimer = ReflectionHelper.findField(EntityPlayer.class, "sleepTimer");
	//	public static Field sleeping = ObfuscationReflectionHelper.findField(EntityPlayer.class, "sleeping");
	
//    @SubscribeEvent
//	public void renderPlayer(RenderPlayerEvent.Pre event) // RenderPlayer
//    {
//
//		EntityPlayer player = event.getEntityPlayer();
//		
//		if ( player.isBeingRidden() ) //&& player.getRidingEntity() instanceof EntityFeralWolf )
//		{
//			player.renderOffsetX = 0;
//			player.renderOffsetY = 0;
//			player.renderOffsetZ = 0;
////			player.setRenderYawOffset(30);
////			GlStateManager.rotate(-70, 0.0F, 1.0F, 0.0F);
////            GlStateManager.rotate(70, 0.0F, 0.0F, 1.0F);
////            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
//		}
//		
//	}
//    
//    @SubscribeEvent
//	public void renderPlayer(RenderPlayerEvent.Post event) // RenderPlayer
//    {
//		EntityPlayer player = event.getEntityPlayer();
//		//event.getRenderer().getRenderManager().renderEntity(player, player.posX, player.posY, player.posZ, 39, 0, false);
//		//event.getRenderer().getRenderManager().rota
//		//event.getRenderer().getMainModel().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
//		if ( player.isBeingRidden() ) //&& player.getRidingEntity() instanceof EntityFeralWolf )
//		{
//			player.renderOffsetX = 0;
//			player.renderOffsetY = 0;
//			player.renderOffsetZ = 0;
////			player.setRenderYawOffset(30);
////			GlStateManager.rotate(-70, 0.0F, 1.0F, 0.0F);
////            GlStateManager.rotate(70, 0.0F, 0.0F, 1.0F);
////            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
//		}
//		
//	}
	
	//@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void mountEvent(EntityMountEvent event)
	{
		if ( ( event.getEntityMounting() instanceof EntityFeralWolf || event.getEntityMounting() instanceof EntityBrownBear ) && event.getEntityBeingMounted() instanceof EntityPlayer )
		{
			//EntityPlayer player = (EntityPlayer) event.getEntityBeingMounted();
			if ( event.getWorldObj().isRemote ) this.cameraRiding = true;
		}
	}
	
//	@SideOnly(Side.CLIENT)
//	protected Entity attackTarget = null;
	
	protected boolean cameraRiding = false;
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
	public void cameraSetup(CameraSetup event)
    {
    	if ( this.cameraRiding && event.getEntity() instanceof EntityPlayer && event.getEntity().isBeingRidden() )
		{
    		// x = x------|
			GlStateManager.translate(0, 0, -3.0); // -1.5 !!!
			
			EntityPlayer player = (EntityPlayer) event.getEntity();

            // System.out.println(player);
            // System.out.println(this.attackTarget);

			player.renderOffsetX = 0;
			player.renderOffsetY = 0;
			player.renderOffsetZ = 0;
			player.cameraPitch = 0;
			player.prevCameraPitch = 0;
			player.cameraYaw = 0;
			player.prevCameraYaw = 0;
			player.rotationPitch = 0;
			player.prevRotationPitch = 0;
			player.rotationYaw = 0;
			player.prevRotationYaw = 0;
			player.rotationYawHead = 0;
			player.prevRotationYawHead = 0;
			player.motionX = 0;
			player.motionZ = 0;
			event.setYaw(20); // 20 !!!
			event.setPitch(-90);
			event.setRoll(0);
			//Minecraft.getMinecraft().gameSettings.thirdPersonView = 0;
		}
    	else
    	{
    		this.cameraRiding = false;
    	}
	}
    
    
    
//  //if ( player.isBeingRidden() && player.getRidingEntity() instanceof EntityFeralWolf )
//  		if ( player.ticksExisted % 200 < 50 )
//  		{
//  			player.renderOffsetX = -80;
//  			player.renderOffsetY = 70;
//  			player.renderOffsetZ = -60;
//  			GlStateManager.rotate(-70, 0.0F, 1.0F, 0.0F);
//              GlStateManager.rotate(70, 0.0F, 0.0F, 1.0F);
//              GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
//  			player.closeScreen();
//  			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
////  			try
////  			{
////  				event.getEntityPlayer().bedLocation = BlockPos.ORIGIN;
////  				sleeping.set(event.getEntityPlayer(), true);
////  				sleepTimer.setInt(event.getEntityPlayer(), 30);
////  			}
////  			catch (IllegalArgumentException | IllegalAccessException e)
////  			{
////  				System.out.println("xxx");
////  			}
//  		}
  		
  		
}