package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelLamprey;
import its_meow.betteranimalsplus.common.entity.EntityLamprey;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLamprey extends RenderLiving<EntityLamprey> {

	public RenderLamprey(RenderManager rendermanager) {
		super(rendermanager, new ModelLamprey(), 0.4F);
	}

	@Override
	protected void preRenderCallback(EntityLamprey entity, float partialTickTime) {
		GlStateManager.scale(0.5D, 0.5D, 0.5D);
		
		if(entity.isRiding()) {
            GlStateManager.rotate(180, 0, 1, 0);
            GlStateManager.translate(0, 0, 0.5F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLamprey entity) {
		int type = entity.getTypeNumber();
		switch(type) {
		case 1: return ModTextures.lamprey_1;
		case 2: return ModTextures.lamprey_2;
		case 3: return ModTextures.lamprey_3;
		default: return ModTextures.lamprey_1;
		}
	}

}
