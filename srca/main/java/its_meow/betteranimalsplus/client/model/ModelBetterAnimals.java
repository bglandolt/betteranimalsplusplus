package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.util.ModMathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;

/**
 * <em><b>Copyright (c) 2018 Ocelot5836.</b></em>
 *
 * <br>
 * </br>
 *
 * Used to help when rendering entities in the world.
 *
 * @author Ocelot5836
 *
 *         Permission Granted for use in Better Animals Plus
 */
public class ModelBetterAnimals extends ModelBase {

    /**
     * Gets the number of ticks the entity has existed for, plus the time since the
     * game last ticked. This allows for precise timing and smooth movement.
     * 
     * @param base
     *             - The EntityLivingBase class for which to check the ticks
     *             existed.
     * @return TicksExisted + partialTicks of the entity.
     */
    public static float getIdleProgress(EntityLivingBase base) {
        return base.ticksExisted + Minecraft.getMinecraft().getRenderPartialTicks();
    }

    /**
     * Gets the limb swing progress of an entity. Includes partial ticks for
     * precision.
     * 
     * @param base
     *             - The EntityLivingBase class for which to get the limb swing
     *             progress from.
     * @return How far along the entity is from completing its swing.
     */
    public static float getSwingProgress(EntityLivingBase base) {
        return base.limbSwing - base.limbSwingAmount * (1.0F - Minecraft.getMinecraft().getRenderPartialTicks());
    }

    /**
     * Gets the previous limb swing progress of an entity. Includes partial ticks
     * for precision. Basically float-precise timing of ticksExisted, instead of an
     * integer value.
     * 
     * @param base
     *             - The EntityLivingBase class for which to get the previous limb
     *             swing progress from.
     * @return The time since the last limb swing of the entity was completed.
     */
    public static float getSwingProgressPrev(EntityLivingBase base) {
        return base.prevLimbSwingAmount + (base.limbSwingAmount - base.prevLimbSwingAmount) * Minecraft.getMinecraft().getRenderPartialTicks();
    }

    /**
     * Gets the yaw rotation of the entity's head. Includes partial ticks for
     * precision.
     * 
     * @param base
     *             - The entity from which to get the head yaw rotation from.
     * @return The value of the yaw rotation the head is at.
     */
    public static float getHeadYaw(EntityLivingBase base) {
        float yawOffset = ModMathHelper.interpolateRotation(base.prevRenderYawOffset, base.renderYawOffset, Minecraft.getMinecraft().getRenderPartialTicks());
        float yawHead = ModMathHelper.interpolateRotation(base.prevRotationYawHead, base.rotationYawHead, Minecraft.getMinecraft().getRenderPartialTicks());
        return yawHead - yawOffset;
    }

    /**
     * Gets the pitch rotation of the entity's head. Includes partial ticks for
     * precision.
     * 
     * @param base
     *             - The entity from which to get the head pitch rotation from.
     * @return The value of the pitch rotation the head is at.
     */
    public static float getHeadPitch(EntityLivingBase base) {
        return (base.prevRotationPitch + (base.rotationPitch - base.prevRotationPitch) * Minecraft.getMinecraft().getRenderPartialTicks());
    }

    /**
     * Gets the idle progress of a generic Object. Uses partial ticks for precision.
     * Basically float-precise timing of ticksExisted, instead of an integer value.
     * 
     * @param o
     *          - The object for which to get the idle progress from. Should be an
     *          instance of EntityLivingBase.
     * @return ticksExisted + partialTicks of the object.
     */
    public static float idleProgress(Object o) {
        if (o != null && o instanceof EntityLivingBase) {
            return getIdleProgress((EntityLivingBase) o);
        }

        return 0F;
    }

    /**
     * Gets the swing process of a generic Object. Uses partial ticks for precision.
     * 
     * @param o
     *          - The object to get the swing progress of. Should be an instance of
     *          EntityLivingBase.
     * @return How far along the object is from completing its swing.
     */
    public static float swingProgress(Object o) {
        if (o != null && o instanceof EntityLivingBase) {
            return getSwingProgress((EntityLivingBase) o);
        }

        return 0F;
    }

    /**
     * Gets the previous swing progress of a generic Object.
     * 
     * @param o
     *          - The object to get the previous swing progress of. Should be an
     *          instance of EntityLivingBase.
     * @return The time since the object's last swing was completed.
     */
    public static float swingProgressPrev(Object o) {
        if (o != null && o instanceof EntityLivingBase) {
            return getSwingProgressPrev((EntityLivingBase) o);
        }

        return 0F;
    }

    /**
     * Gets the yaw rotation of a generic Object.
     * 
     * @param o
     *          - The object from which to get the yaw of. Should be an instance of
     *          EntityLivingBase.
     * @return The yaw rotation of the object.
     */
    public static float headYaw(Object o) {
        if (o != null && o instanceof EntityLivingBase) {
            return getHeadYaw((EntityLivingBase) o);
        }

        return 0F;
    }

    /**
     * Gets the pitch rotation of a generic Object.
     * 
     * @param o
     *          - The object from which to get the pitch of. Should be an instance
     *          of EntityLivingBase.
     * @return The pitch rotation of the object.
     */
    public static float headPitch(Object o) {
        if (o != null && o instanceof EntityLivingBase) {
            return getHeadPitch((EntityLivingBase) o);
        }

        return 0F;
    }

    /**
     * Used by Tabula to set the rotation angles of model parts.
     * 
     * @param modelRenderer
     *                      The part
     * @param x
     *                      The x angle
     * @param y
     *                      The y angle
     * @param z
     *                      The z angle
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}