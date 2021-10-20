package its_meow.betteranimalsplus.client.renderer.entity;

import javax.annotation.Nonnull;

import its_meow.betteranimalsplus.client.model.ModelBear;
import its_meow.betteranimalsplus.common.entity.EntityBear;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBear extends RenderLiving<EntityBear> {

    public RenderBear(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelBear(), 1.25F);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityBear entity)
    {
        return ModTextures.bear_brown;
    }

    /**
     * Allows the render to do state modifications necessary before the model is
     * rendered.
     */
    @Override
    protected void preRenderCallback(EntityBear bear, float partialTickTime)
    {
        float scale = 1.5F;
        GlStateManager.scale(scale, scale, scale);
        super.preRenderCallback(bear, partialTickTime);
    }

}
