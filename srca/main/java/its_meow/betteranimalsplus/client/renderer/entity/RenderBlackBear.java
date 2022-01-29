package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelBrownBear;
import its_meow.betteranimalsplus.common.entity.EntityBlackBear;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBlackBear extends RenderLiving<EntityBlackBear> {

    public RenderBlackBear(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelBrownBear(), 0.8F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBlackBear entity)
    {
        int type = entity.getTypeNumber();
        switch(type)
        {
	        case 1: return ModTextures.bear_black;
	        case 2: return ModTextures.bear_kermode;
	        default: return ModTextures.bear_black;
        }
    }

}
