package its_meow.betteranimalsplus.client.renderer.entity;

import its_meow.betteranimalsplus.client.model.ModelBrownBear;
import its_meow.betteranimalsplus.common.entity.EntityBrownBear;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBrownBear extends RenderLiving<EntityBrownBear> {

    public RenderBrownBear(RenderManager manager)
    {
        super(manager, new ModelBrownBear(), 1.25F);
        //this.addLayer(new LayerEyesCondition<EntityFeralWolf>(this, ModTextures.wolf_eyes, e -> !e.isTamed()));
    }

//    @Override
//    protected float handleRotationFloat(EntityFeralWolf livingBase, float partialTicks) {
//        return livingBase.getTailRotation();
//    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBrownBear entity)
    {
        return ModTextures.bear_brown;
    }

    @Override
    protected void preRenderCallback(EntityBrownBear bear, float partialTickTime)
    {
        float scale = 1.5F;
        GlStateManager.scale(scale, scale, scale);
        super.preRenderCallback(bear, partialTickTime);
    }
    
}
