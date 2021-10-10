package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntitySaltwaterEel;
import its_meow.betteranimalsplus.util.ModMathHelper;
import its_meow.betteranimalsplus.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * EelSaltwater - Batman
 * Created using Tabula 7.0.1
 */
public class ModelSaltwaterEel extends ModelBase {
    public ModelRenderer body00;
    public ModelRenderer body01;
    public ModelRenderer head;
    public ModelRenderer topFin01;
    public ModelRenderer lowFin01;
    public ModelRenderer lFin;
    public ModelRenderer rFin;
    public ModelRenderer body02;
    public ModelRenderer topFin02;
    public ModelRenderer lowFin02;
    public ModelRenderer body03;
    public ModelRenderer topFin03;
    public ModelRenderer lowFin03;
    public ModelRenderer body04;
    public ModelRenderer topFin04;
    public ModelRenderer lowFin04;
    public ModelRenderer body05;
    public ModelRenderer topFin05;
    public ModelRenderer lowFin05;
    public ModelRenderer body06;
    public ModelRenderer topFin06;
    public ModelRenderer lowFin06;
    public ModelRenderer topFin07;
    public ModelRenderer lowFin07;
    public ModelRenderer topJaw;
    public ModelRenderer lowerJaw;
    public ModelRenderer topTeethL;
    public ModelRenderer topTeethR;
    public ModelRenderer snout;
    public ModelRenderer ribbonNoseLeft01;
    public ModelRenderer ribbonNoseRight01;
    public ModelRenderer lCrest;
    public ModelRenderer rCrest;
    public ModelRenderer ribbonNoseLeft02;
    public ModelRenderer ribbonNoseRight2;
    public ModelRenderer lowerJawUnder;
    public ModelRenderer lowJawPieceL;
    public ModelRenderer lowJawPieceR;
    public ModelRenderer lowTeethL;
    public ModelRenderer lowTeethR;

    public ModelSaltwaterEel() {
        this.textureWidth = 50;
        this.textureHeight = 200;
        this.lowerJawUnder = new ModelRenderer(this, 16, 74);
        this.lowerJawUnder.setRotationPoint(0.0F, -1.0F, -6.9F);
        this.lowerJawUnder.addBox(-1.5F, 0.0F, 0.0F, 2, 1, 7, 0.0F);
        this.setRotateAngle(lowerJawUnder, -0.136659280431156F, 0.0F, 0.0F);
        this.lowFin03 = new ModelRenderer(this, 0, 150);
        this.lowFin03.setRotationPoint(0.0F, 2.9F, 0.0F);
        this.lowFin03.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
        this.lowFin07 = new ModelRenderer(this, 0, 168);
        this.lowFin07.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.lowFin07.addBox(0.0F, 0.0F, 0.0F, 0, 5, 13, 0.0F);
        this.lowTeethR = new ModelRenderer(this, 30, 4);
        this.lowTeethR.setRotationPoint(-0.7F, -1.5F, -6.5F);
        this.lowTeethR.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 6, 0.0F);
        this.lowFin01 = new ModelRenderer(this, 0, 140);
        this.lowFin01.setRotationPoint(0.0F, 2.9F, 0.0F);
        this.lowFin01.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
        this.lCrest = new ModelRenderer(this, 0, 0);
        this.lCrest.setRotationPoint(0.4F, 0.5F, 4.3F);
        this.lCrest.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 0, 0.0F);
        this.setRotateAngle(lCrest, 0.0F, 0.0F, 0.31869712141416456F);
        this.ribbonNoseRight2 = new ModelRenderer(this, 22, 52);
        this.ribbonNoseRight2.setRotationPoint(0.0F, 0.0F, -1.8F);
        this.ribbonNoseRight2.addBox(0.0F, -1.5F, -2.0F, 0, 3, 2, 0.0F);
        this.topFin06 = new ModelRenderer(this, 0, 125);
        this.topFin06.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin06.addBox(0.0F, -4.0F, 0.0F, 0, 5, 9, 0.0F);
        this.lowFin02 = new ModelRenderer(this, 0, 145);
        this.lowFin02.setRotationPoint(0.0F, 2.9F, 0.0F);
        this.lowFin02.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
        this.body02 = new ModelRenderer(this, 0, 33);
        this.body02.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body02.addBox(-1.5F, -3.0F, 0.0F, 3, 6, 9, 0.0F);
        this.lowFin04 = new ModelRenderer(this, 0, 155);
        this.lowFin04.setRotationPoint(0.0F, 2.9F, 0.0F);
        this.lowFin04.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
        this.lowTeethL = new ModelRenderer(this, 30, 4);
        this.lowTeethL.mirror = true;
        this.lowTeethL.setRotationPoint(-0.3F, -1.5F, -6.5F);
        this.lowTeethL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6, 0.0F);
        this.topFin01 = new ModelRenderer(this, 0, 99);
        this.topFin01.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin01.addBox(0.0F, -4.0F, 0.0F, 0, 4, 9, 0.0F);
        this.topFin03 = new ModelRenderer(this, 0, 109);
        this.topFin03.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin03.addBox(0.0F, -4.0F, 0.0F, 0, 4, 9, 0.0F);
        this.body05 = new ModelRenderer(this, 0, 80);
        this.body05.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body05.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 9, 0.0F);
        this.topTeethR = new ModelRenderer(this, 20, 0);
        this.topTeethR.setRotationPoint(-0.3F, 4.0F, -1.0F);
        this.topTeethR.addBox(-1.0F, 0.0F, -5.5F, 1, 1, 6, 0.0F);
        this.ribbonNoseLeft01 = new ModelRenderer(this, 14, 53);
        this.ribbonNoseLeft01.setRotationPoint(0.8F, 0.0F, -3.0F);
        this.ribbonNoseLeft01.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ribbonNoseLeft01, -0.40980330836826856F, -0.4553564018453205F, 0.0F);
        this.snout = new ModelRenderer(this, 26, 16);
        this.snout.setRotationPoint(0.0F, -1.0F, -4.0F);
        this.snout.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(snout, 0.4061381169390805F, 0.0F, 0.0F);
        this.body03 = new ModelRenderer(this, 0, 49);
        this.body03.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body03.addBox(-1.0F, -3.0F, 0.0F, 2, 6, 9, 0.0F);
        this.lowFin05 = new ModelRenderer(this, 0, 160);
        this.lowFin05.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.lowFin05.addBox(0.0F, 0.0F, 0.0F, 0, 5, 9, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 16, 59);
        this.lowerJaw.setRotationPoint(0.5F, 5.0F, 0.3F);
        this.lowerJaw.addBox(-2.0F, -1.0F, -7.0F, 3, 1, 7, 0.0F);
        this.ribbonNoseLeft02 = new ModelRenderer(this, 22, 52);
        this.ribbonNoseLeft02.setRotationPoint(0.0F, 0.0F, -1.8F);
        this.ribbonNoseLeft02.addBox(0.0F, -1.5F, -2.0F, 0, 3, 2, 0.0F);
        this.topFin04 = new ModelRenderer(this, 0, 114);
        this.topFin04.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin04.addBox(0.0F, -4.0F, 0.0F, 0, 4, 9, 0.0F);
        this.topFin05 = new ModelRenderer(this, 0, 119);
        this.topFin05.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin05.addBox(0.0F, -4.0F, 0.0F, 0, 5, 9, 0.0F);
        this.body04 = new ModelRenderer(this, 0, 65);
        this.body04.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body04.addBox(-1.0F, -2.5F, 0.0F, 2, 5, 9, 0.0F);
        this.topFin07 = new ModelRenderer(this, 0, 127);
        this.topFin07.setRotationPoint(0.0F, -2.5F, 0.0F);
        this.topFin07.addBox(0.0F, -4.0F, 0.0F, 0, 8, 13, 0.0F);
        this.topJaw = new ModelRenderer(this, 21, 45);
        this.topJaw.setRotationPoint(0.0F, 3.0F, -3.0F);
        this.topJaw.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4, 0.0F);
        this.lFin = new ModelRenderer(this, 18, 11);
        this.lFin.setRotationPoint(2.5F, 0.8F, 4.0F);
        this.lFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.setRotateAngle(lFin, 0.0F, 0.27314402793711257F, 0.0F);
        this.body06 = new ModelRenderer(this, 0, 94);
        this.body06.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body06.addBox(-1.0F, -1.5F, 0.0F, 2, 3, 9, 0.0F);
        this.body01 = new ModelRenderer(this, 0, 16);
        this.body01.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.body01.addBox(-2.0F, -3.0F, 0.0F, 4, 6, 9, 0.0F);
        this.topFin02 = new ModelRenderer(this, 0, 104);
        this.topFin02.setRotationPoint(0.0F, -2.9F, 0.0F);
        this.topFin02.addBox(0.0F, -4.0F, 0.0F, 0, 4, 9, 0.0F);
        this.ribbonNoseRight01 = new ModelRenderer(this, 14, 53);
        this.ribbonNoseRight01.setRotationPoint(-0.8F, 0.0F, -3.0F);
        this.ribbonNoseRight01.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(ribbonNoseRight01, -0.40980330836826856F, 0.4553564018453205F, 0.0F);
        this.lowJawPieceL = new ModelRenderer(this, 4, 3);
        this.lowJawPieceL.setRotationPoint(0.0F, -0.5F, -6.4F);
        this.lowJawPieceL.addBox(0.0F, -0.5F, -2.0F, 0, 1, 2, 0.0F);
        this.setRotateAngle(lowJawPieceL, 0.31869712141416456F, -0.18203784098300857F, 0.0F);
        this.head = new ModelRenderer(this, 17, 32);
        this.head.setRotationPoint(0.0F, -3.0F, 0.1F);
        this.head.addBox(-2.0F, 0.0F, -3.0F, 4, 4, 3, 0.0F);
        this.lowJawPieceR = new ModelRenderer(this, 4, 3);
        this.lowJawPieceR.setRotationPoint(-1.5F, -0.5F, -6.4F);
        this.lowJawPieceR.addBox(0.0F, -0.5F, -2.0F, 0, 1, 2, 0.0F);
        this.setRotateAngle(lowJawPieceR, 0.31869712141416456F, 0.18203784098300857F, 0.0F);
        this.topTeethL = new ModelRenderer(this, 20, 0);
        this.topTeethL.mirror = true;
        this.topTeethL.setRotationPoint(0.3F, 4.0F, -1.0F);
        this.topTeethL.addBox(0.0F, 0.0F, -5.5F, 1, 1, 6, 0.0F);
        this.body00 = new ModelRenderer(this, 0, 0);
        this.body00.setRotationPoint(0.5F, 17.0F, -8.0F);
        this.body00.addBox(-2.5F, -3.0F, 0.0F, 5, 6, 9, 0.0F);
        this.rCrest = new ModelRenderer(this, 0, 0);
        this.rCrest.setRotationPoint(-0.5F, 0.5F, 4.3F);
        this.rCrest.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 0, 0.0F);
        this.setRotateAngle(rCrest, 0.0F, 0.0F, -0.31869712141416456F);
        this.rFin = new ModelRenderer(this, 18, 11);
        this.rFin.setRotationPoint(-2.5F, 0.8F, 4.0F);
        this.rFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.setRotateAngle(rFin, 0.0F, -0.27314402793711257F, 0.0F);
        this.lowFin06 = new ModelRenderer(this, 0, 166);
        this.lowFin06.setRotationPoint(0.0F, 1.9F, 0.0F);
        this.lowFin06.addBox(0.0F, 0.0F, 0.0F, 0, 5, 9, 0.0F);
        this.lowerJaw.addChild(this.lowerJawUnder);
        this.body02.addChild(this.lowFin03);
        this.body06.addChild(this.lowFin07);
        this.lowerJaw.addChild(this.lowTeethR);
        this.body00.addChild(this.lowFin01);
        this.snout.addChild(this.lCrest);
        this.ribbonNoseRight01.addChild(this.ribbonNoseRight2);
        this.body05.addChild(this.topFin06);
        this.body01.addChild(this.lowFin02);
        this.body01.addChild(this.body02);
        this.body03.addChild(this.lowFin04);
        this.lowerJaw.addChild(this.lowTeethL);
        this.body00.addChild(this.topFin01);
        this.body02.addChild(this.topFin03);
        this.body04.addChild(this.body05);
        this.head.addChild(this.topTeethR);
        this.topJaw.addChild(this.ribbonNoseLeft01);
        this.topJaw.addChild(this.snout);
        this.body02.addChild(this.body03);
        this.body04.addChild(this.lowFin05);
        this.head.addChild(this.lowerJaw);
        this.ribbonNoseLeft01.addChild(this.ribbonNoseLeft02);
        this.body03.addChild(this.topFin04);
        this.body04.addChild(this.topFin05);
        this.body03.addChild(this.body04);
        this.body06.addChild(this.topFin07);
        this.head.addChild(this.topJaw);
        this.body00.addChild(this.lFin);
        this.body05.addChild(this.body06);
        this.body00.addChild(this.body01);
        this.body01.addChild(this.topFin02);
        this.topJaw.addChild(this.ribbonNoseRight01);
        this.lowerJaw.addChild(this.lowJawPieceL);
        this.body00.addChild(this.head);
        this.lowerJaw.addChild(this.lowJawPieceR);
        this.head.addChild(this.topTeethL);
        this.snout.addChild(this.rCrest);
        this.body00.addChild(this.rFin);
        this.body05.addChild(this.lowFin06);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        this.body00.rotateAngleX = 0F;
        if(!entity.isInWater()) {
            GlStateManager.translate(0F, 0.5F, 0F);
        }
        this.body00.render(f5);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        double motionLength = MathHelper.sqrt(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
        float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
        float ticks = ageInTicks / 5F + (float) motionLength * 0.05F;
        float factor = 1F;
        float offset = 0F;
        float amplitude = (float) Math.min(MathHelper.sqrt(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ) * 2.5F + 0.25F, 0.5F);
        if(!entityIn.isInWater()) {
            amplitude = 0.15F;
            ticks = ageInTicks / 2F;
        }
        this.body00.rotateAngleX = 0F;
        if(entityIn instanceof EntitySaltwaterEel) {
            EntitySaltwaterEel eel = (EntitySaltwaterEel) entityIn;
            if(motionLength > 0) {
                float rotX = (float) Math.toRadians(headPitch);
                if (rotX < 0) {
                    rotX /= 2;
                }
                this.body00.rotateAngleX = ModMathHelper.interpolateRotation(eel.lastBodyRotation, rotX, partialTicks);
                eel.lastBodyRotation = rotX;
            }
            this.head.rotateAngleY = MathHelper.sin((float)RenderUtil.partLocation(this.body00, this.head).z * factor - ticks) * 0.5F + ((float) Math.toRadians(netHeadYaw) * 0.0625F);
            float z01 = (float)RenderUtil.partLocation(this.body00, this.body01).z;
            float z02 = (float)RenderUtil.partLocation(this.body00, this.body01, this.body02).z;
            float z03 = (float)RenderUtil.partLocation(this.body00, this.body01, this.body02, this.body03).z;
            float z04 = (float)RenderUtil.partLocation(this.body00, this.body01, this.body02, this.body03, this.body04).z;
            float z05 = (float)RenderUtil.partLocation(this.body00, this.body01, this.body02, this.body03, this.body04, this.body05).z;
            float z06 = (float)RenderUtil.partLocation(this.body00, this.body01, this.body02, this.body03, this.body04, this.body05, this.body06).z;
            float newBody01 = -MathHelper.sin(z01 * factor + ticks) * amplitude + offset;
            float newBody02 = -MathHelper.sin(z02 * factor + ticks) * amplitude + offset;
            float newBody03 = MathHelper.sin(z03 * factor + ticks) * amplitude + offset;
            float newBody04 = MathHelper.sin(z04 * factor + ticks) * amplitude + offset;
            float newBody05 = MathHelper.sin(z05 * factor + ticks) * amplitude + offset;
            float newBody06 = -MathHelper.sin(z06 * factor + ticks) * amplitude + offset;
            this.body01.rotateAngleY = ModMathHelper.interpolateRotation(eel.body01, newBody01, partialTicks);
            this.body02.rotateAngleY = ModMathHelper.interpolateRotation(eel.body02, newBody02, partialTicks);
            this.body03.rotateAngleY = ModMathHelper.interpolateRotation(eel.body03, newBody03, partialTicks);
            this.body04.rotateAngleY = ModMathHelper.interpolateRotation(eel.body04, newBody04, partialTicks);
            this.body05.rotateAngleY = ModMathHelper.interpolateRotation(eel.body05, newBody05, partialTicks);
            this.body06.rotateAngleY = ModMathHelper.interpolateRotation(eel.body06, newBody06, partialTicks);
            eel.body01 = newBody01;
            eel.body02 = newBody02;
            eel.body03 = newBody03;
            eel.body04 = newBody04;
            eel.body05 = newBody05;
            eel.body06 = newBody06;
            this.body00.rotationPointX = 0.5F + MathHelper.sin((float) RenderUtil.partLocation(this.body00).z * factor + ticks) * (10F * amplitude);
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
