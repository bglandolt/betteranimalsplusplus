package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelGiantSquid;
import its_meow.betteranimalsplus.common.entity.EntityBadger;
import its_meow.betteranimalsplus.common.entity.EntityGiantSquid;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGiantSquid extends RenderLiving<EntityGiantSquid> {

    public RenderGiantSquid(RenderManager mgr)
    {
        super(mgr, new ModelGiantSquid(), 0.4F);
    }
    
    @Override
    protected void preRenderCallback(EntityGiantSquid entitylivingbaseIn, float partialTickTime) 
    {
        if (this.getMainModel().isChild)
        {
            GlStateManager.scale(0.4D, 0.4D, 0.4D);
        }
        else
        {
            GlStateManager.scale(0.8D, 0.8D, 0.8D);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityGiantSquid entity)
    {
    	return ModTextures.squid_1;
//    	switch(entity.getTypeNumber())
//        {
//	        case 1: return ModTextures.squid_1
//	        case 2: return ModTextures.squid_2
//	        default: return ModTextures.squid_1;
//        }
    }

}
