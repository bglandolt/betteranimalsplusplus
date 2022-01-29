package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelOctopus;
import its_meow.betteranimalsplus.common.entity.EntityOctopus;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOctopus extends RenderLiving<EntityOctopus> {

    public RenderOctopus(RenderManager mgr)
    {
        super(mgr, new ModelOctopus(), 0.4F);
    }
    
    @Override
    protected void preRenderCallback(EntityOctopus entitylivingbaseIn, float partialTickTime) 
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
    protected ResourceLocation getEntityTexture(EntityOctopus entity)
    {
    	return ModTextures.octopus_1;
//    	switch(entity.getTypeNumber())
//        {
//	        case 1: return ModTextures.octopus_1
//	        case 2: return ModTextures.octopus_2
//	        case 3: return ModTextures.octopus_3
//	        case 4: return ModTextures.octopus_4
//	        default: return ModTextures.octopus_1;
//        }
    }

}
