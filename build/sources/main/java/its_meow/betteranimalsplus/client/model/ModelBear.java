package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityBear;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;

/**
 * bear - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelBear extends ModelBetterAnimals {
    public ModelRenderer hind;
    public ModelRenderer lLeg01;
    public ModelRenderer rLeg01;
    public ModelRenderer tail;
    public ModelRenderer body;
    public ModelRenderer lLeg02;
    public ModelRenderer lHindpaw;
    public ModelRenderer lLegFur;
    public ModelRenderer lHindClaw01;
    public ModelRenderer lHindClaw02;
    public ModelRenderer lHindClaw03;
    public ModelRenderer lHindClaw04;
    public ModelRenderer lHindClaw05;
    public ModelRenderer rLeg02;
    public ModelRenderer rHindpaw;
    public ModelRenderer rLegFur;
    public ModelRenderer rHindClaw01;
    public ModelRenderer rHindClaw02;
    public ModelRenderer rHindClaw03;
    public ModelRenderer rHindClaw04;
    public ModelRenderer rHindClaw05;
    public ModelRenderer chest;
    public ModelRenderer neck;
    public ModelRenderer hump;
    public ModelRenderer lArm01;
    public ModelRenderer rArm01;
    public ModelRenderer bodyFur;
    public ModelRenderer head;
    public ModelRenderer neckFur01;
    public ModelRenderer neckFur02;
    public ModelRenderer neckFur03;
    public ModelRenderer lEar01;
    public ModelRenderer lEarSmall;
    public ModelRenderer rEar01;
    public ModelRenderer rEarSmall;
    public ModelRenderer upperJaw;
    public ModelRenderer lowerJaw;
    public ModelRenderer snout;
    public ModelRenderer lEar02;
    public ModelRenderer rEar02;
    public ModelRenderer hump02;
    public ModelRenderer lArm02;
    public ModelRenderer lForepaw;
    public ModelRenderer lArmFur;
    public ModelRenderer lForeClaw01;
    public ModelRenderer lForeClaw02;
    public ModelRenderer lForeClaw03;
    public ModelRenderer lForeClaw04;
    public ModelRenderer lForeClaw05;
    public ModelRenderer rArm02;
    public ModelRenderer rForepaw;
    public ModelRenderer rArmFur;
    public ModelRenderer rForeClaw01;
    public ModelRenderer rForeClaw02;
    public ModelRenderer rForeClaw03;
    public ModelRenderer rForeClaw04;
    public ModelRenderer rForeClaw05;

    public ModelBear() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.lEar02 = new ModelRenderer(this, 45, 42);
        this.lEar02.setRotationPoint(0.0F, -0.3F, 1.2F);
        this.lEar02.addBox(-1.0F, -2.6F, -0.4F, 2, 3, 1, 0.0F);
        this.setRotateAngle(this.lEar02, 0.27314402793711257F, 0.0F, 0.0F);
        this.lHindClaw05 = new ModelRenderer(this, 116, 34);
        this.lHindClaw05.setRotationPoint(-2.7F, 1.5F, -2.1F);
        this.lHindClaw05.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindClaw05, 0.136659280431156F, 0.22759093446006054F, 0.0F);
        this.rHindClaw03 = new ModelRenderer(this, 116, 34);
        this.rHindClaw03.mirror = true;
        this.rHindClaw03.setRotationPoint(0.3F, 1.3F, -3.6F);
        this.rHindClaw03.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindClaw03, 0.18203784098300857F, 0.0F, 0.0F);
        this.lHindpaw = new ModelRenderer(this, 94, 35);
        this.lHindpaw.setRotationPoint(0.0F, 7.3F, 0.2F);
        this.lHindpaw.addBox(-3.0F, 0.3F, -4.7F, 6, 2, 7, 0.0F);
        this.setRotateAngle(this.lHindpaw, -0.091106186954104F, 0.0F, 0.0F);
        this.lForeClaw02 = new ModelRenderer(this, 86, 34);
        this.lForeClaw02.setRotationPoint(1.1F, 1.0F, -1.7F);
        this.lForeClaw02.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lForeClaw02, 0.18203784098300857F, -0.18203784098300857F, 0.0F);
        this.rForeClaw05 = new ModelRenderer(this, 86, 34);
        this.rForeClaw05.mirror = true;
        this.rForeClaw05.setRotationPoint(2.7F, 1.3F, 0.7F);
        this.rForeClaw05.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rForeClaw05, 0.136659280431156F, -0.27314402793711257F, 0.0F);
        this.rForeClaw03 = new ModelRenderer(this, 86, 34);
        this.rForeClaw03.mirror = true;
        this.rForeClaw03.setRotationPoint(0.3F, 1.0F, -2.0F);
        this.rForeClaw03.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rForeClaw03, 0.18203784098300857F, 0.0F, 0.0F);
        this.upperJaw = new ModelRenderer(this, 49, 43);
        this.upperJaw.setRotationPoint(0.0F, 0.6F, -6.8F);
        this.upperJaw.addBox(-2.5F, -1.0F, -4.1F, 5, 2, 4, 0.0F);
        this.hump02 = new ModelRenderer(this, 94, 59);
        this.hump02.setRotationPoint(0.0F, -0.7F, 2.7F);
        this.hump02.addBox(-3.5F, -3.0F, -0.3F, 7, 3, 6, 0.0F);
        this.setRotateAngle(this.hump02, -0.7740535232594852F, 0.0F, 0.0F);
        this.rLeg01 = new ModelRenderer(this, 97, 0);
        this.rLeg01.mirror = true;
        this.rLeg01.setRotationPoint(-1.0F, -0.6F, 6.5F);
        this.rLeg01.addBox(-5.0F, -1.1F, -3.0F, 5, 10, 7, 0.0F);
        this.setRotateAngle(this.rLeg01, -0.136659280431156F, 0.0F, 0.18203784098300857F);
        this.rLegFur = new ModelRenderer(this, 59, 74);
        this.rLegFur.mirror = true;
        this.rLegFur.setRotationPoint(0.0F, 3.2F, 2.0F);
        this.rLegFur.addBox(-2.0F, -1.0F, -0.3F, 4, 7, 3, 0.0F);
        this.lLeg01 = new ModelRenderer(this, 97, 0);
        this.lLeg01.setRotationPoint(1.0F, -0.6F, 6.5F);
        this.lLeg01.addBox(0.0F, -1.1F, -3.0F, 5, 10, 7, 0.0F);
        this.setRotateAngle(this.lLeg01, -0.136659280431156F, 0.0F, -0.18203784098300857F);
        this.tail = new ModelRenderer(this, 46, 63);
        this.tail.setRotationPoint(0.0F, -3.9F, 11.0F);
        this.tail.addBox(-2.0F, -1.0F, -2.3F, 4, 5, 3, 0.0F);
        this.setRotateAngle(this.tail, 0.4553564018453205F, 0.0F, 0.0F);
        this.lForepaw = new ModelRenderer(this, 65, 35);
        this.lForepaw.setRotationPoint(0.2F, 8.5F, -2.0F);
        this.lForepaw.addBox(-3.0F, 0.3F, -3.3F, 6, 2, 7, 0.0F);
        this.setRotateAngle(this.lForepaw, 0.091106186954104F, 0.0F, 0.0F);
        this.lHindClaw02 = new ModelRenderer(this, 116, 34);
        this.lHindClaw02.setRotationPoint(1.1F, 1.3F, -3.5F);
        this.lHindClaw02.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindClaw02, 0.18203784098300857F, 0.0F, 0.0F);
        this.lForeClaw01 = new ModelRenderer(this, 86, 34);
        this.lForeClaw01.setRotationPoint(2.3F, 1.1F, -1.3F);
        this.lForeClaw01.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lForeClaw01, 0.18203784098300857F, -0.27314402793711257F, 0.0F);
        this.rArmFur = new ModelRenderer(this, 43, 74);
        this.rArmFur.mirror = true;
        this.rArmFur.setRotationPoint(0.0F, 3.3F, 2.1F);
        this.rArmFur.addBox(-2.0F, -0.2F, -0.2F, 4, 7, 3, 0.0F);
        this.setRotateAngle(this.rArmFur, -0.045553093477052F, 0.0F, 0.0F);
        this.rArm02 = new ModelRenderer(this, 65, 20);
        this.rArm02.mirror = true;
        this.rArm02.setRotationPoint(-2.0F, 7.4F, 0.7F);
        this.rArm02.addBox(-2.5F, 0.3F, -2.5F, 5, 9, 5, 0.0F);
        this.setRotateAngle(this.rArm02, -0.18203784098300857F, 0.0F, -0.18203784098300857F);
        this.rHindClaw01 = new ModelRenderer(this, 116, 34);
        this.rHindClaw01.mirror = true;
        this.rHindClaw01.setRotationPoint(-2.3F, 1.4F, -3.2F);
        this.rHindClaw01.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindClaw01, 0.18203784098300857F, 0.0F, 0.0F);
        this.neckFur02 = new ModelRenderer(this, 22, 83);
        this.neckFur02.setRotationPoint(0.0F, 2.8F, -4.2F);
        this.neckFur02.addBox(-3.0F, 0.0F, -0.8F, 6, 4, 3, 0.0F);
        this.setRotateAngle(this.neckFur02, 0.22759093446006054F, 0.0F, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 49, 50);
        this.lowerJaw.setRotationPoint(0.0F, 2.1F, -6.5F);
        this.lowerJaw.addBox(-2.5F, -0.5F, -4.1F, 5, 1, 4, 0.0F);
        this.snout = new ModelRenderer(this, 38, 29);
        this.snout.setRotationPoint(0.0F, -0.9F, -6.8F);
        this.snout.addBox(-1.5F, -1.3F, -4.4F, 3, 2, 5, 0.0F);
        this.setRotateAngle(this.snout, 0.18203784098300857F, 0.0F, 0.0F);
        this.rForeClaw04 = new ModelRenderer(this, 86, 34);
        this.rForeClaw04.mirror = true;
        this.rForeClaw04.setRotationPoint(1.7F, 1.0F, -1.8F);
        this.rForeClaw04.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rForeClaw04, 0.18203784098300857F, -0.18203784098300857F, 0.0F);
        this.rHindClaw04 = new ModelRenderer(this, 116, 34);
        this.rHindClaw04.mirror = true;
        this.rHindClaw04.setRotationPoint(1.7F, 1.3F, -3.3F);
        this.rHindClaw04.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindClaw04, 0.18203784098300857F, 0.0F, 0.0F);
        this.lEarSmall = new ModelRenderer(this, 0, 0);
        this.lEarSmall.setRotationPoint(3.0F, -2.3F, -2.2F);
        this.lEarSmall.addBox(-0.8F, -2.6F, -0.7F, 2, 3, 1, 0.0F);
        this.setRotateAngle(this.lEarSmall, 0.045553093477052F, 0.0F, 0.40980330836826856F);
        this.lForeClaw03 = new ModelRenderer(this, 86, 34);
        this.lForeClaw03.setRotationPoint(-0.3F, 1.0F, -2.0F);
        this.lForeClaw03.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lForeClaw03, 0.18203784098300857F, 0.0F, 0.0F);
        this.rForepaw = new ModelRenderer(this, 65, 35);
        this.rForepaw.mirror = true;
        this.rForepaw.setRotationPoint(-0.2F, 8.5F, -2.0F);
        this.rForepaw.addBox(-3.0F, 0.3F, -3.3F, 6, 2, 7, 0.0F);
        this.setRotateAngle(this.rForepaw, 0.091106186954104F, 0.0F, 0.0F);
        this.hump = new ModelRenderer(this, 68, 59);
        this.hump.setRotationPoint(0.0F, -1.6F, -6.9F);
        this.hump.addBox(-3.0F, -3.0F, -1.3F, 6, 2, 6, 0.0F);
        this.setRotateAngle(this.hump, 0.6373942428283291F, 0.0F, 0.0F);
        this.rArm01 = new ModelRenderer(this, 65, 0);
        this.rArm01.mirror = true;
        this.rArm01.setRotationPoint(-1.3F, -0.7F, -3.0F);
        this.rArm01.addBox(-4.5F, -1.0F, -2.5F, 5, 10, 6, 0.0F);
        this.setRotateAngle(this.rArm01, 0.18203784098300857F, 0.0F, 0.18203784098300857F);
        this.lLegFur = new ModelRenderer(this, 59, 74);
        this.lLegFur.setRotationPoint(0.0F, 3.2F, 2.0F);
        this.lLegFur.addBox(-2.0F, -1.0F, -0.3F, 4, 7, 3, 0.0F);
        this.lForeClaw04 = new ModelRenderer(this, 86, 34);
        this.lForeClaw04.setRotationPoint(-1.7F, 1.0F, -1.8F);
        this.lForeClaw04.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lForeClaw04, 0.18203784098300857F, 0.18203784098300857F, 0.0F);
        this.rHindClaw02 = new ModelRenderer(this, 116, 34);
        this.rHindClaw02.mirror = true;
        this.rHindClaw02.setRotationPoint(-1.1F, 1.3F, -3.5F);
        this.rHindClaw02.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindClaw02, 0.18203784098300857F, 0.0F, 0.0F);
        this.neckFur03 = new ModelRenderer(this, 0, 93);
        this.neckFur03.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neckFur03.addBox(-3.0F, -1.3F, 0.0F, 6, 2, 6, 0.0F);
        this.setRotateAngle(this.neckFur03, 0.31869712141416456F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 19);
        this.body.setRotationPoint(0.0F, 0.0F, -0.6F);
        this.body.addBox(-5.0F, -5.0F, -4.0F, 10, 11, 8, 0.0F);
        this.setRotateAngle(this.body, 0.091106186954104F, 0.0F, 0.0F);
        this.hind = new ModelRenderer(this, 0, 39);
        this.hind.setRotationPoint(0.0F, 7.7F, 3.3F);
        this.hind.addBox(-5.5F, -5.7F, 0.0F, 11, 12, 11, 0.0F);
        this.setRotateAngle(this.hind, -0.091106186954104F, 0.0F, 0.0F);
        this.lHindClaw01 = new ModelRenderer(this, 116, 34);
        this.lHindClaw01.setRotationPoint(2.3F, 1.4F, -3.2F);
        this.lHindClaw01.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindClaw01, 0.18203784098300857F, 0.0F, 0.0F);
        this.rForeClaw02 = new ModelRenderer(this, 86, 34);
        this.rForeClaw02.mirror = true;
        this.rForeClaw02.setRotationPoint(-1.1F, 1.0F, -1.7F);
        this.rForeClaw02.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rForeClaw02, 0.18203784098300857F, 0.18203784098300857F, 0.0F);
        this.rEarSmall = new ModelRenderer(this, 0, 0);
        this.rEarSmall.mirror = true;
        this.rEarSmall.setRotationPoint(-3.0F, -2.3F, -2.2F);
        this.rEarSmall.addBox(-1.2F, -2.6F, -0.7F, 2, 3, 1, 0.0F);
        this.setRotateAngle(this.rEarSmall, 0.045553093477052F, 0.0F, -0.40980330836826856F);
        this.lHindClaw03 = new ModelRenderer(this, 116, 34);
        this.lHindClaw03.setRotationPoint(-0.3F, 1.3F, -3.6F);
        this.lHindClaw03.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindClaw03, 0.18203784098300857F, 0.0F, 0.0F);
        this.lArm01 = new ModelRenderer(this, 65, 0);
        this.lArm01.setRotationPoint(1.3F, -0.7F, -3.0F);
        this.lArm01.addBox(-0.5F, -1.0F, -2.5F, 5, 10, 6, 0.0F);
        this.setRotateAngle(this.lArm01, 0.18203784098300857F, 0.0F, -0.18203784098300857F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, -0.1F, -3.3F);
        this.chest.addBox(-4.5F, -5.0F, -7.0F, 9, 10, 7, 0.0F);
        this.setRotateAngle(this.chest, -0.091106186954104F, 0.0F, 0.0F);
        this.lHindClaw04 = new ModelRenderer(this, 116, 34);
        this.lHindClaw04.setRotationPoint(-1.7F, 1.3F, -3.3F);
        this.lHindClaw04.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindClaw04, 0.18203784098300857F, 0.0F, 0.0F);
        this.rHindpaw = new ModelRenderer(this, 94, 35);
        this.rHindpaw.mirror = true;
        this.rHindpaw.setRotationPoint(0.0F, 7.3F, 0.2F);
        this.rHindpaw.addBox(-3.0F, 0.3F, -4.7F, 6, 2, 7, 0.0F);
        this.setRotateAngle(this.rHindpaw, -0.091106186954104F, 0.0F, 0.0F);
        this.lForeClaw05 = new ModelRenderer(this, 86, 34);
        this.lForeClaw05.setRotationPoint(-2.7F, 1.3F, 0.7F);
        this.lForeClaw05.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.lForeClaw05, 0.136659280431156F, 0.27314402793711257F, 0.0F);
        this.rForeClaw01 = new ModelRenderer(this, 86, 34);
        this.rForeClaw01.mirror = true;
        this.rForeClaw01.setRotationPoint(-2.3F, 1.1F, -1.3F);
        this.rForeClaw01.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rForeClaw01, 0.18203784098300857F, 0.27314402793711257F, 0.0F);
        this.neckFur01 = new ModelRenderer(this, 0, 83);
        this.neckFur01.setRotationPoint(0.0F, 2.7F, -2.5F);
        this.neckFur01.addBox(-3.5F, 0.0F, -0.8F, 7, 5, 3, 0.0F);
        this.setRotateAngle(this.neckFur01, 0.27314402793711257F, 0.0F, 0.0F);
        this.rEar02 = new ModelRenderer(this, 45, 42);
        this.rEar02.mirror = true;
        this.rEar02.setRotationPoint(0.0F, -0.3F, 1.2F);
        this.rEar02.addBox(-1.0F, -2.6F, -0.4F, 2, 3, 1, 0.0F);
        this.setRotateAngle(this.rEar02, 0.27314402793711257F, 0.0F, 0.0F);
        this.lEar01 = new ModelRenderer(this, 36, 42);
        this.lEar01.setRotationPoint(2.6F, -2.4F, -4.0F);
        this.lEar01.addBox(-1.5F, -3.2F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(this.lEar01, 0.18203784098300857F, -0.35168384427685734F, 0.36425021489121656F);
        this.lArmFur = new ModelRenderer(this, 43, 74);
        this.lArmFur.setRotationPoint(0.0F, 3.3F, 2.1F);
        this.lArmFur.addBox(-2.0F, -0.2F, -0.2F, 4, 7, 3, 0.0F);
        this.setRotateAngle(this.lArmFur, -0.091106186954104F, 0.0F, 0.0F);
        this.rHindClaw05 = new ModelRenderer(this, 116, 34);
        this.rHindClaw05.setRotationPoint(2.7F, 1.5F, -2.1F);
        this.rHindClaw05.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindClaw05, 0.136659280431156F, -0.22759093446006054F, 0.0F);
        this.neck = new ModelRenderer(this, 33, 0);
        this.neck.setRotationPoint(0.0F, 0.2F, -6.2F);
        this.neck.addBox(-3.4F, -4.0F, -5.0F, 7, 7, 5, 0.0F);
        this.setRotateAngle(this.neck, 0.18203784098300857F, 0.0F, 0.0F);
        this.rLeg02 = new ModelRenderer(this, 97, 19);
        this.rLeg02.mirror = true;
        this.rLeg02.setRotationPoint(-2.1F, 7.5F, 0.1F);
        this.rLeg02.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(this.rLeg02, 0.31869712141416456F, 0.0F, -0.18203784098300857F);
        this.lArm02 = new ModelRenderer(this, 65, 20);
        this.lArm02.setRotationPoint(2.0F, 7.4F, 0.7F);
        this.lArm02.addBox(-2.5F, 0.3F, -2.5F, 5, 9, 5, 0.0F);
        this.setRotateAngle(this.lArm02, -0.18203784098300857F, 0.0F, 0.18203784098300857F);
        this.lLeg02 = new ModelRenderer(this, 97, 19);
        this.lLeg02.setRotationPoint(2.1F, 7.5F, 0.1F);
        this.lLeg02.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(this.lLeg02, 0.31869712141416456F, 0.0F, 0.18203784098300857F);
        this.rEar01 = new ModelRenderer(this, 36, 42);
        this.rEar01.mirror = true;
        this.rEar01.setRotationPoint(-2.6F, -2.4F, -4.0F);
        this.rEar01.addBox(-1.5F, -3.2F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(this.rEar01, 0.18203784098300857F, 0.35168384427685734F, -0.36425021489121656F);
        this.bodyFur = new ModelRenderer(this, 0, 66);
        this.bodyFur.setRotationPoint(0.0F, 3.4F, -6.1F);
        this.bodyFur.addBox(-4.0F, 1.7F, 0.0F, 8, 4, 11, 0.0F);
        this.setRotateAngle(this.bodyFur, 0.091106186954104F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 36, 14);
        this.head.setRotationPoint(0.0F, -0.8F, -2.7F);
        this.head.addBox(-4.0F, -3.5F, -7.0F, 8, 7, 6, 0.0F);
        this.setRotateAngle(this.head, 0.18203784098300857F, 0.0F, 0.0F);
        this.lEar01.addChild(this.lEar02);
        this.lHindpaw.addChild(this.lHindClaw05);
        this.rHindpaw.addChild(this.rHindClaw03);
        this.lLeg02.addChild(this.lHindpaw);
        this.lForepaw.addChild(this.lForeClaw02);
        this.rForepaw.addChild(this.rForeClaw05);
        this.rForepaw.addChild(this.rForeClaw03);
        this.head.addChild(this.upperJaw);
        this.hump.addChild(this.hump02);
        this.hind.addChild(this.rLeg01);
        this.rLeg02.addChild(this.rLegFur);
        this.hind.addChild(this.lLeg01);
        this.hind.addChild(this.tail);
        this.lArm02.addChild(this.lForepaw);
        this.lHindpaw.addChild(this.lHindClaw02);
        this.lForepaw.addChild(this.lForeClaw01);
        this.rArm02.addChild(this.rArmFur);
        this.rArm01.addChild(this.rArm02);
        this.rHindpaw.addChild(this.rHindClaw01);
        this.neck.addChild(this.neckFur02);
        this.head.addChild(this.lowerJaw);
        this.head.addChild(this.snout);
        this.rForepaw.addChild(this.rForeClaw04);
        this.rHindpaw.addChild(this.rHindClaw04);
        this.head.addChild(this.lEarSmall);
        this.lForepaw.addChild(this.lForeClaw03);
        this.rArm02.addChild(this.rForepaw);
        this.chest.addChild(this.hump);
        this.chest.addChild(this.rArm01);
        this.lLeg02.addChild(this.lLegFur);
        this.lForepaw.addChild(this.lForeClaw04);
        this.rHindpaw.addChild(this.rHindClaw02);
        this.neck.addChild(this.neckFur03);
        this.hind.addChild(this.body);
        this.lHindpaw.addChild(this.lHindClaw01);
        this.rForepaw.addChild(this.rForeClaw02);
        this.head.addChild(this.rEarSmall);
        this.lHindpaw.addChild(this.lHindClaw03);
        this.chest.addChild(this.lArm01);
        this.body.addChild(this.chest);
        this.lHindpaw.addChild(this.lHindClaw04);
        this.rLeg02.addChild(this.rHindpaw);
        this.lForepaw.addChild(this.lForeClaw05);
        this.rForepaw.addChild(this.rForeClaw01);
        this.neck.addChild(this.neckFur01);
        this.rEar01.addChild(this.rEar02);
        this.head.addChild(this.lEar01);
        this.lArm02.addChild(this.lArmFur);
        this.rHindpaw.addChild(this.rHindClaw05);
        this.chest.addChild(this.neck);
        this.rLeg01.addChild(this.rLeg02);
        this.lArm01.addChild(this.lArm02);
        this.lLeg01.addChild(this.lLeg02);
        this.head.addChild(this.rEar01);
        this.chest.addChild(this.bodyFur);
        this.neck.addChild(this.head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.hind.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float f = limbSwing;
        float f1 = limbSwingAmount/2.5F;
        
        //if (entityIn instanceof EntityLiving)
        {
            this.neck.rotateAngleX = ModelBetterAnimals.getHeadPitch((EntityLiving) entityIn) * 0.017453292F;
            this.neck.rotateAngleY = ModelBetterAnimals.getHeadYaw((EntityLiving) entityIn) * 0.017453292F;
        	this.lowerJaw.rotateAngleX = 0.0F;
        }
        
        // attackAnimationTimer = 60;
        if ( entityIn instanceof EntityBear ) // cos range: -1 to 1  // cos(0) = 1 // cos(0.5) = 0.87  // cos(1.57) = 0 // cos(1) = 0.54  // cos(2) = -0.41   // cos pi = -1
        {
        	EntityBear bear = (EntityBear)entityIn;
        	
	        if ( bear.attackAnimationTimer-- >= 0 )
	        {
	        	float r = ( 0.5F - MathHelper.abs((bear.attackAnimationTimer-25)/50.0F) ) * 2.0F;

	        	if ( bear.attackAnimationTimer > 25 )
	        	{
	        		bear.attackAnimationTimer--;
	        	}
	        	
	        	this.lowerJaw.rotateAngleX = (float) MathHelper.clamp(r, 0.0D, 0.6D);
	        	
	            this.hind.rotateAngleX = -r/8.0F;
	            this.body.rotateAngleZ = -r/4.0F;
	            
	            this.neck.rotateAngleY = r/2.2F;
	            this.neck.rotateAngleZ = -r/2.2F;
	            
	            this.lArm01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.375F * f1 + 0.18203784098300857F -r/2.2F;
	            this.lArm01.rotateAngleY = r/3.0F;
	            this.lArm01.rotateAngleZ = -r/3.0F-0.2F;

	            this.lForepaw.rotateAngleX = r/2.2F;
	            this.rArm01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.375F * f1 + 0.18203784098300857F +r/4.0F;
	        }
	        else if ( bear.attackAnimationTimerStanding-- >= 0 )
	        {
	        	float r = ( 0.5F - MathHelper.abs((bear.attackAnimationTimerStanding-25)/50.0F) ) * 2.0F; // 0 1 0

	        	if ( bear.attackAnimationTimerStanding < 25 )
	        	{
	        		bear.attackAnimationTimerStanding--;
	        	}
	        	
	        	this.lowerJaw.rotateAngleX = (float) MathHelper.clamp(r, 0.0D, 0.6D);
	        	
	            //this.lowerJaw.rotateAngleX = r;
	            this.neck.rotateAngleX = r/1.8F;
	            this.hind.rotateAngleX = -r*1.2F;
	            this.body.rotateAngleX = r/1.6F;
	            this.body.rotateAngleZ = r/4.0F;
	            
	            // L ARM
	            this.lForepaw.rotateAngleX = r;
	            this.lForepaw.rotateAngleY = -r/2.0F;
	            this.lForepaw.rotateAngleZ = r/2.0F;
	            
	            this.lArm01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.375F * f1 + 0.18203784098300857F -r/2.2F;
	            this.lArm01.rotateAngleY = r/2.0F;
	            this.lArm01.rotateAngleZ = -r/1.6F-0.2F;
	            
	            // R ARM
	            this.rForepaw.rotateAngleX = r;
	            this.rForepaw.rotateAngleY = r/2.2F;
	            this.rForepaw.rotateAngleZ = -r/2.2F;
	            
	            this.rArm01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.375F * f1 + 0.18203784098300857F -r/2.2F;
	            this.rArm01.rotateAngleY = -r/2.0F;
	            this.rArm01.rotateAngleZ = r/1.6F+0.2F;
	            
	            this.lLeg01.rotateAngleX = r/1.6F;
	            this.rLeg01.rotateAngleX = r/1.6F;

	            this.lLeg01.rotateAngleZ = -r/2.2F-0.2F;
	            this.rLeg01.rotateAngleZ = r/2.2F+0.2F;
	        }
	        else
	        {
	        	// OTHER
	        	if ( bear.warningSoundTicks >= 0 && !bear.isRiding() )
	        	{
		        	float r = ( 0.5F - MathHelper.abs((bear.attackAnimationTimer-25)/50.0F) ) * 2.0F;
		        	this.lowerJaw.rotateAngleX = (float) MathHelper.clamp(r, 0.0D, 0.6D);
		        	bear.warningSoundTicks--;
	        	}
	        	
	            this.hind.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.45F) * 0.19F * f1;
	            this.body.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.55F) * 0.18F * f1;
	            
	            // L ARM
	            this.lForepaw.rotateAngleX = -MathHelper.cos(f * 0.6662F) * 0.5F * f1;
	            this.rForepaw.rotateAngleX = -MathHelper.cos(f * 0.6662F + (float) Math.PI) * 0.5F * f1;
	            this.lForepaw.rotateAngleY = 0.0F;
	            this.lForepaw.rotateAngleZ = 0.0F;
	            
		        this.lArm01.rotateAngleY = 0.0F;
	            
		        this.lArm01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.375F * f1 + 0.18203784098300857F;
		        this.rArm01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.375F * f1 + 0.18203784098300857F;
		        this.lLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.375F * f1 - 0.136659280431156F;
		        this.rLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.375F * f1 - 0.136659280431156F;
		        
		        this.lArm01.rotateAngleZ = -0.2F;
		        this.rArm01.rotateAngleZ = 0.2F;
		        this.lLeg01.rotateAngleZ = -0.2F;
	            this.rLeg01.rotateAngleZ = 0.2F;
	            
	            this.lForepaw.rotateAngleZ = 0.0F;
	        }
	        
	        
	        if ( bear.isRiding() )
	        {            
	            // CHEST
	        	//this.body.rotateAngleX = MathHelper.cos(bear.ticksExisted * 0.3F) * 0.3F + 0.12F;
	        	this.hind.offsetZ = 1.1F + MathHelper.cos(bear.ticksExisted * 0.2F) * 0.04F;

	        	// LEGS
	            //this.lArm01.rotateAngleX = 0.4F - this.chest.rotateAngleX;
	            //this.rArm01.rotateAngleX = 0.4F - this.chest.rotateAngleX;
	            
	        	// HEAD
	        	this.lowerJaw.rotateAngleX = (float) MathHelper.clamp(MathHelper.cos(bear.ticksExisted * 0.6F) * 0.4F + 0.35F, 0.0D, 0.6D);
	        	
	            this.neck.rotateAngleX = 0.8F - MathHelper.cos(bear.ticksExisted * 0.2F) * 0.06F;
	            this.neck.rotateAngleY = 0.0F;
	            this.neck.rotateAngleZ = 0.0F;
	            //this.head.rotateAngleX = 1.0F;
	            //this.head.rotateAngleY = MathHelper.cos(bear.ticksExisted * 0.1F) * 0.02F + 0.2F;
	        }
	        else
	        {
	            this.hind.offsetZ = 0.0F;
	        	//this.body.rotateAngleX = 0.0F;
	        	
	            //this.head.rotateAngleX = 0.0F;
	            //this.head.rotateAngleY = 0.0F;
	        }
        }
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    @Override
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
