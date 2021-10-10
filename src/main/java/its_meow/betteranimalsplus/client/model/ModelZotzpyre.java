package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityZotzpyre;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;

/**
 * zotzpyre - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelZotzpyre extends ModelBase {
    public ModelRenderer chest;
    public ModelRenderer stomach;
    public ModelRenderer neck;
    public ModelRenderer lWing01;
    public ModelRenderer rWing01;
    public ModelRenderer mane01;
    public ModelRenderer mane02;
    public ModelRenderer mane03;
    public ModelRenderer mane04;
    public ModelRenderer lLeg01;
    public ModelRenderer rLeg01;
    public ModelRenderer tail01;
    public ModelRenderer lLeg02;
    public ModelRenderer lClaw01;
    public ModelRenderer lClaw02;
    public ModelRenderer lClaw03;
    public ModelRenderer lClaw04;
    public ModelRenderer lClaw05;
    public ModelRenderer rLeg02;
    public ModelRenderer rClaw01;
    public ModelRenderer rClaw02;
    public ModelRenderer rClaw03;
    public ModelRenderer rClaw04;
    public ModelRenderer rClaw05;
    public ModelRenderer tail02;
    public ModelRenderer tail01Membrane;
    public ModelRenderer tail02Membrane;
    public ModelRenderer head;
    public ModelRenderer lEar01;
    public ModelRenderer rEar01;
    public ModelRenderer snout;
    public ModelRenderer lowerJaw;
    public ModelRenderer lEar02;
    public ModelRenderer rEar02;
    public ModelRenderer upperJaw01;
    public ModelRenderer upperJaw02;
    public ModelRenderer nose;
    public ModelRenderer lTeeth;
    public ModelRenderer rTeeth;
    public ModelRenderer lWing02;
    public ModelRenderer lWing03;
    public ModelRenderer lWingMembrane02;
    public ModelRenderer lWingMembrane03;
    public ModelRenderer lFinger;
    public ModelRenderer lWing04;
    public ModelRenderer lWingMembrane01;
    public ModelRenderer rWing02;
    public ModelRenderer rWing03;
    public ModelRenderer rWingMembrane02;
    public ModelRenderer rWingMembrane03;
    public ModelRenderer rFinger;
    public ModelRenderer rWing04;
    public ModelRenderer rWingMembrane01;

    public ModelZotzpyre() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.snout = new ModelRenderer(this, 47, 0);
        this.snout.setRotationPoint(0.0F, -0.8F, -4.0F);
        this.snout.addBox(-1.5F, -0.5F, -3.0F, 3, 1, 3, 0.0F);
        this.setRotateAngle(snout, 0.2617993877991494F, 0.0F, 0.0F);
        this.rFinger = new ModelRenderer(this, 63, 18);
        this.rFinger.mirror = true;
        this.rFinger.setRotationPoint(-11.5F, 0.1F, -0.6F);
        this.rFinger.addBox(-1.0F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(rFinger, 0.0F, 0.40142572795869574F, -0.6283185307179586F);
        this.head = new ModelRenderer(this, 0, 52);
        this.head.setRotationPoint(0.0F, -0.2F, -1.5F);
        this.head.addBox(-4.0F, -3.5F, -4.5F, 8, 6, 5, 0.0F);
        this.setRotateAngle(head, -0.08726646259971647F, 0.0F, 0.0F);
        this.tail02Membrane = new ModelRenderer(this, 56, 56);
        this.tail02Membrane.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail02Membrane.addBox(-3.5F, -0.0F, 0.0F, 7, 0, 6, 0.0F);
        this.lClaw01 = new ModelRenderer(this, 97, 0);
        this.lClaw01.setRotationPoint(0.8F, 7.8F, -0.8F);
        this.lClaw01.addBox(-0.3F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(lClaw01, 0.13962634015954636F, 0.6108652381980153F, 0.3490658503988659F);
        this.rClaw04 = new ModelRenderer(this, 97, 0);
        this.rClaw04.mirror = true;
        this.rClaw04.setRotationPoint(-1.2F, 7.8F, 0.6F);
        this.rClaw04.addBox(-3.7F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(rClaw04, 0.0F, 0.2617993877991494F, -0.3490658503988659F);
        this.lWing02 = new ModelRenderer(this, 43, 27);
        this.lWing02.setRotationPoint(5.7F, 0.0F, 0.0F);
        this.lWing02.addBox(0.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(lWing02, 0.3490658503988659F, 0.6981317007977318F, 0.7853981633974483F);
        this.rWingMembrane01 = new ModelRenderer(this, 7, 38);
        this.rWingMembrane01.mirror = true;
        this.rWingMembrane01.setRotationPoint(0.0F, 0.0F, -0.1F);
        this.rWingMembrane01.addBox(-27.7F, -0.01F, -2.8F, 29, 0, 13, 0.0F);
        this.setRotateAngle(rWingMembrane01, 0.0F, 0.2617993877991494F, 0.0F);
        this.rWing03 = new ModelRenderer(this, 43, 32);
        this.rWing03.mirror = true;
        this.rWing03.setRotationPoint(-12.0F, 0.0F, -0.1F);
        this.rWing03.addBox(-13.0F, -0.5F, -1.0F, 13, 1, 2, 0.0F);
        this.setRotateAngle(rWing03, 0.0F, 1.9547687622336491F, 0.0F);
        this.mane03 = new ModelRenderer(this, 30, 51);
        this.mane03.setRotationPoint(0.0F, 3.4F, -5.2F);
        this.mane03.addBox(-3.5F, 0.0F, -4.0F, 7, 3, 8, 0.0F);
        this.setRotateAngle(mane03, -0.05235987755982988F, 0.0F, 0.0F);
        this.rWingMembrane03 = new ModelRenderer(this, 64, 46);
        this.rWingMembrane03.mirror = true;
        this.rWingMembrane03.setRotationPoint(0.2F, 0.0F, 1.0F);
        this.rWingMembrane03.addBox(-6.0F, -0.01F, 0.0F, 16, 0, 18, 0.0F);
        this.rWingMembrane03.offsetY = -0.03F;
        this.setRotateAngle(rWingMembrane03, 0.0F, 0.40142572795869574F, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 62, 8);
        this.lowerJaw.setRotationPoint(0.0F, 1.6F, -3.8F);
        this.lowerJaw.addBox(-1.5F, 0.0F, -2.7F, 3, 1, 3, 0.0F);
        this.rLeg02 = new ModelRenderer(this, 85, 8);
        this.rLeg02.mirror = true;
        this.rLeg02.setRotationPoint(-6.7F, 1.9F, 0.0F);
        this.rLeg02.addBox(-1.5F, 0.0F, -1.0F, 3, 9, 2, 0.0F);
        this.lWing04 = new ModelRenderer(this, 43, 36);
        this.lWing04.setRotationPoint(12.7F, 0.0F, -0.2F);
        this.lWing04.addBox(0.0F, -0.5F, -0.5F, 11, 1, 1, 0.0F);
        this.setRotateAngle(lWing04, 0.0F, -0.3490658503988659F, 0.0F);
        this.upperJaw02 = new ModelRenderer(this, 47, 6);
        this.upperJaw02.mirror = true;
        this.upperJaw02.setRotationPoint(-1.2F, 1.8F, 0.6F);
        this.upperJaw02.addBox(-1.0F, -1.0F, -3.8F, 2, 2, 4, 0.0F);
        this.setRotateAngle(upperJaw02, -0.2617993877991494F, -0.13962634015954636F, 0.0F);
        this.tail01Membrane = new ModelRenderer(this, 58, 52);
        this.tail01Membrane.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail01Membrane.addBox(-3.5F, 0.0F, 0.0F, 7, 0, 4, 0.0F);
        this.rEar01 = new ModelRenderer(this, 0, 0);
        this.rEar01.mirror = true;
        this.rEar01.setRotationPoint(-2.5F, -2.6F, -1.0F);
        this.rEar01.addBox(-2.0F, -4.0F, -0.5F, 4, 4, 1, 0.0F);
        this.setRotateAngle(rEar01, 0.0F, 0.13962634015954636F, -0.3490658503988659F);
        this.nose = new ModelRenderer(this, 47, 14);
        this.nose.setRotationPoint(0.0F, 0.6F, -3.0F);
        this.nose.addBox(-1.5F, -3.0F, 0.0F, 3, 3, 0, 0.0F);
        this.setRotateAngle(nose, 0.10471975511965977F, 0.0F, 0.0F);
        this.rClaw01 = new ModelRenderer(this, 97, 0);
        this.rClaw01.mirror = true;
        this.rClaw01.setRotationPoint(-0.2F, 7.8F, -0.8F);
        this.rClaw01.addBox(-4.0F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(rClaw01, 0.13962634015954636F, -0.6108652381980153F, -0.3490658503988659F);
        this.rWing01 = new ModelRenderer(this, 43, 20);
        this.rWing01.mirror = true;
        this.rWing01.setRotationPoint(-4.5F, -2.2F, -7.0F);
        this.rWing01.addBox(-6.0F, -1.5F, -1.5F, 6, 3, 3, 0.0F);
        this.setRotateAngle(rWing01, -0.13962634015954636F, 0.5759586531581287F, -0.40142572795869574F);
        this.tail01 = new ModelRenderer(this, 30, 19);
        this.tail01.setRotationPoint(0.0F, -3.3F, 7.9F);
        this.tail01.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4, 0.0F);
        this.setRotateAngle(tail01, -0.6283185307179586F, 0.0F, 0.0F);
        this.upperJaw01 = new ModelRenderer(this, 47, 6);
        this.upperJaw01.setRotationPoint(1.2F, 1.8F, 0.6F);
        this.upperJaw01.addBox(-1.0F, -1.0F, -3.8F, 2, 2, 4, 0.0F);
        this.setRotateAngle(upperJaw01, -0.2617993877991494F, 0.13962634015954636F, 0.0F);
        this.mane04 = new ModelRenderer(this, 102, 25);
        this.mane04.setRotationPoint(0.0F, 2.1F, -10.6F);
        this.mane04.addBox(-2.5F, -0.8F, -0.5F, 5, 6, 2, 0.0F);
        this.setRotateAngle(mane04, 0.08726646259971647F, 0.0F, 0.0F);
        this.mane01 = new ModelRenderer(this, 102, 13);
        this.mane01.setRotationPoint(0.0F, -3.6F, -10.3F);
        this.mane01.addBox(-2.5F, -0.8F, 0.0F, 5, 2, 8, 0.0F);
        this.setRotateAngle(mane01, 0.2617993877991494F, 0.0F, 0.0F);
        this.rWingMembrane02 = new ModelRenderer(this, 52, 19);
        this.rWingMembrane02.mirror = true;
        this.rWingMembrane02.setRotationPoint(-5.1F, 0.0F, 0.0F);
        this.rWingMembrane02.addBox(-6.0F, -0.0F, 0.0F, 12, 0, 26, 0.0F);
        this.setRotateAngle(rWingMembrane02, 0.0F, 0.5759586531581287F, 0.0F);
        this.rClaw02 = new ModelRenderer(this, 97, 0);
        this.rClaw02.mirror = true;
        this.rClaw02.setRotationPoint(-1.2F, 7.8F, -0.6F);
        this.rClaw02.addBox(-3.7F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(rClaw02, 0.0F, -0.2617993877991494F, -0.3490658503988659F);
        this.rClaw05 = new ModelRenderer(this, 97, 0);
        this.rClaw05.mirror = true;
        this.rClaw05.setRotationPoint(-0.8F, 7.8F, 0.8F);
        this.rClaw05.addBox(-3.7F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(rClaw05, -0.13962634015954636F, 0.6108652381980153F, -0.3490658503988659F);
        this.lClaw04 = new ModelRenderer(this, 97, 0);
        this.lClaw04.setRotationPoint(1.2F, 7.8F, 0.6F);
        this.lClaw04.addBox(-0.3F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(lClaw04, 0.0F, -0.2617993877991494F, 0.3490658503988659F);
        this.lEar01 = new ModelRenderer(this, 0, 0);
        this.lEar01.setRotationPoint(2.5F, -2.6F, -1.0F);
        this.lEar01.addBox(-2.0F, -4.0F, -0.5F, 4, 4, 1, 0.0F);
        this.setRotateAngle(lEar01, 0.0F, -0.13962634015954636F, 0.3490658503988659F);
        this.lClaw05 = new ModelRenderer(this, 97, 0);
        this.lClaw05.setRotationPoint(0.8F, 7.8F, 0.8F);
        this.lClaw05.addBox(-0.3F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(lClaw05, -0.13962634015954636F, -0.6108652381980153F, 0.3490658503988659F);
        this.lLeg02 = new ModelRenderer(this, 85, 8);
        this.lLeg02.setRotationPoint(6.7F, 1.9F, 0.0F);
        this.lLeg02.addBox(-1.5F, 0.0F, -1.0F, 3, 9, 2, 0.0F);
        this.lLeg01 = new ModelRenderer(this, 73, 0);
        this.lLeg01.setRotationPoint(3.2F, -1.6F, 5.6F);
        this.lLeg01.addBox(-0.5F, -2.0F, -1.5F, 9, 4, 3, 0.0F);
        this.setRotateAngle(lLeg01, 0.0F, 0.45378560551852565F, -0.03490658503988659F);
        this.mane02 = new ModelRenderer(this, 98, 3);
        this.mane02.setRotationPoint(0.0F, -4.0F, -9.0F);
        this.mane02.addBox(-3.5F, -0.7F, 0.0F, 7, 2, 8, 0.0F);
        this.setRotateAngle(mane02, 0.12217304763960307F, 0.0F, 0.0F);
        this.lWingMembrane02 = new ModelRenderer(this, 52, 19);
        this.lWingMembrane02.setRotationPoint(5.1F, 0.0F, 0.0F);
        this.lWingMembrane02.addBox(-6.0F, -0.0F, 0.0F, 12, 0, 26, 0.0F);
        this.setRotateAngle(lWingMembrane02, 0.0F, -0.5759586531581287F, 0.0F);
        this.tail02 = new ModelRenderer(this, 31, 24);
        this.tail02.setRotationPoint(0.0F, 0.0F, 3.8F);
        this.tail02.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(tail02, -0.3141592653589793F, 0.0F, 0.0F);
        this.rWing02 = new ModelRenderer(this, 43, 27);
        this.rWing02.mirror = true;
        this.rWing02.setRotationPoint(-5.7F, 0.0F, 0.0F);
        this.rWing02.addBox(-12.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(rWing02, 0.3490658503988659F, -0.6981317007977318F, -0.7853981633974483F);
        this.rEar02 = new ModelRenderer(this, 33, 0);
        this.rEar02.mirror = true;
        this.rEar02.setRotationPoint(-0.9F, -0.5F, 0.8F);
        this.rEar02.addBox(-1.5F, -5.5F, -0.5F, 3, 6, 1, 0.0F);
        this.setRotateAngle(rEar02, 0.20943951023931953F, 0.0F, 0.17453292519943295F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, 14.8F, 2.5F);
        this.chest.addBox(-5.0F, -4.5F, -10.0F, 10, 8, 10, 0.0F);
        this.setRotateAngle(chest, 0.05235987755982988F, 0.0F, 0.0F);
        this.lWing01 = new ModelRenderer(this, 43, 20);
        this.lWing01.setRotationPoint(4.5F, -2.2F, -7.0F);
        this.lWing01.addBox(0.0F, -1.5F, -1.5F, 6, 3, 3, 0.0F);
        this.setRotateAngle(lWing01, -0.13962634015954636F, -0.5759586531581287F, 0.40142572795869574F);
        this.lWing03 = new ModelRenderer(this, 43, 32);
        this.lWing03.setRotationPoint(12.0F, 0.0F, -0.1F);
        this.lWing03.addBox(0.0F, -0.5F, -1.0F, 13, 1, 2, 0.0F);
        this.setRotateAngle(lWing03, 0.0F, -1.9547687622336491F, 0.0F);
        this.lWingMembrane01 = new ModelRenderer(this, 7, 38);
        this.lWingMembrane01.setRotationPoint(0.0F, 0.0F, -0.1F);
        this.lWingMembrane01.addBox(-1.3F, -0.01F, -2.8F, 29, 0, 13, 0.0F);
        this.setRotateAngle(lWingMembrane01, 0.0F, -0.2617993877991494F, 0.0F);
        this.stomach = new ModelRenderer(this, 0, 23);
        this.stomach.setRotationPoint(0.0F, -0.2F, -0.8F);
        this.stomach.addBox(-4.5F, -4.0F, 0.0F, 9, 7, 8, 0.0F);
        this.setRotateAngle(stomach, -0.13962634015954636F, 0.0F, 0.0F);
        this.rClaw03 = new ModelRenderer(this, 97, 0);
        this.rClaw03.mirror = true;
        this.rClaw03.setRotationPoint(-1.3F, 7.7F, 0.0F);
        this.rClaw03.addBox(-3.7F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(rClaw03, 0.0F, 0.0F, -0.3490658503988659F);
        this.rLeg01 = new ModelRenderer(this, 73, 0);
        this.rLeg01.mirror = true;
        this.rLeg01.setRotationPoint(-3.2F, -1.6F, 5.6F);
        this.rLeg01.addBox(-8.5F, -2.0F, -1.5F, 9, 4, 3, 0.0F);
        this.setRotateAngle(rLeg01, 0.0F, -0.45378560551852565F, 0.03490658503988659F);
        this.rTeeth = new ModelRenderer(this, 63, 0);
        this.rTeeth.mirror = true;
        this.rTeeth.setRotationPoint(-0.4F, 1.3F, -0.8F);
        this.rTeeth.addBox(-0.5F, -1.1F, -2.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rTeeth, 0.13962634015954636F, 0.0F, 0.0F);
        this.lClaw02 = new ModelRenderer(this, 97, 0);
        this.lClaw02.setRotationPoint(1.2F, 7.8F, -0.6F);
        this.lClaw02.addBox(-0.3F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(lClaw02, 0.0F, 0.2617993877991494F, 0.3490658503988659F);
        this.lTeeth = new ModelRenderer(this, 63, 0);
        this.lTeeth.setRotationPoint(0.4F, 1.3F, -0.8F);
        this.lTeeth.addBox(-0.5F, -1.1F, -2.8F, 1, 2, 2, 0.0F);
        this.setRotateAngle(lTeeth, 0.13962634015954636F, 0.0F, 0.0F);
        this.rWing04 = new ModelRenderer(this, 43, 36);
        this.rWing04.mirror = true;
        this.rWing04.setRotationPoint(-12.7F, 0.0F, -0.2F);
        this.rWing04.addBox(-11.0F, -0.5F, -0.5F, 11, 1, 1, 0.0F);
        this.setRotateAngle(rWing04, 0.0F, 0.3490658503988659F, 0.0F);
        this.lClaw03 = new ModelRenderer(this, 97, 0);
        this.lClaw03.setRotationPoint(1.3F, 7.7F, 0.0F);
        this.lClaw03.addBox(-0.3F, -1.0F, -0.5F, 4, 2, 1, 0.0F);
        this.setRotateAngle(lClaw03, 0.0F, 0.0F, 0.3490658503988659F);
        this.lFinger = new ModelRenderer(this, 63, 18);
        this.lFinger.setRotationPoint(11.5F, 0.1F, -0.6F);
        this.lFinger.addBox(-1.0F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(lFinger, 0.0F, -0.40142572795869574F, 0.6283185307179586F);
        this.lEar02 = new ModelRenderer(this, 33, 0);
        this.lEar02.setRotationPoint(0.9F, -0.5F, 0.8F);
        this.lEar02.addBox(-1.5F, -5.5F, -0.5F, 3, 6, 1, 0.0F);
        this.setRotateAngle(lEar02, 0.20943951023931953F, 0.0F, -0.17453292519943295F);
        this.neck = new ModelRenderer(this, 0, 41);
        this.neck.setRotationPoint(0.0F, -0.8F, -9.6F);
        this.neck.addBox(-3.5F, -3.5F, -2.0F, 7, 6, 2, 0.0F);
        this.setRotateAngle(neck, 0.08726646259971647F, 0.0F, 0.0F);
        this.lWingMembrane03 = new ModelRenderer(this, 64, 46);
        this.lWingMembrane03.setRotationPoint(0.2F, 0.0F, 1.0F);
        this.lWingMembrane03.addBox(-10.0F, -0.01F, 0.0F, 16, 0, 18, 0.0F);
        this.lWingMembrane03.offsetY = -0.03F;
        this.setRotateAngle(lWingMembrane03, 0.0F, -0.40142572795869574F, 0.0F);
        this.head.addChild(this.snout);
        this.rWing02.addChild(this.rFinger);
        this.neck.addChild(this.head);
        this.tail02.addChild(this.tail02Membrane);
        this.lLeg02.addChild(this.lClaw01);
        this.rLeg02.addChild(this.rClaw04);
        this.lWing01.addChild(this.lWing02);
        this.rWing03.addChild(this.rWingMembrane01);
        this.rWing02.addChild(this.rWing03);
        this.chest.addChild(this.mane03);
        this.rWing02.addChild(this.rWingMembrane03);
        this.head.addChild(this.lowerJaw);
        this.rLeg01.addChild(this.rLeg02);
        this.lWing03.addChild(this.lWing04);
        this.snout.addChild(this.upperJaw02);
        this.tail01.addChild(this.tail01Membrane);
        this.head.addChild(this.rEar01);
        this.snout.addChild(this.nose);
        this.rLeg02.addChild(this.rClaw01);
        this.chest.addChild(this.rWing01);
        this.stomach.addChild(this.tail01);
        this.snout.addChild(this.upperJaw01);
        this.chest.addChild(this.mane04);
        this.chest.addChild(this.mane01);
        this.rWing02.addChild(this.rWingMembrane02);
        this.rLeg02.addChild(this.rClaw02);
        this.rLeg02.addChild(this.rClaw05);
        this.lLeg02.addChild(this.lClaw04);
        this.head.addChild(this.lEar01);
        this.lLeg02.addChild(this.lClaw05);
        this.lLeg01.addChild(this.lLeg02);
        this.stomach.addChild(this.lLeg01);
        this.chest.addChild(this.mane02);
        this.lWing02.addChild(this.lWingMembrane02);
        this.tail01.addChild(this.tail02);
        this.rWing01.addChild(this.rWing02);
        this.rEar01.addChild(this.rEar02);
        this.chest.addChild(this.lWing01);
        this.lWing02.addChild(this.lWing03);
        this.lWing03.addChild(this.lWingMembrane01);
        this.chest.addChild(this.stomach);
        this.rLeg02.addChild(this.rClaw03);
        this.stomach.addChild(this.rLeg01);
        this.upperJaw02.addChild(this.rTeeth);
        this.lLeg02.addChild(this.lClaw02);
        this.upperJaw01.addChild(this.lTeeth);
        this.rWing03.addChild(this.rWing04);
        this.lLeg02.addChild(this.lClaw03);
        this.lWing02.addChild(this.lFinger);
        this.lEar01.addChild(this.lEar02);
        this.chest.addChild(this.neck);
        this.lWing02.addChild(this.lWingMembrane03);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chest.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        if(entity instanceof EntityZotzpyre) {
            EntityZotzpyre zotz = (EntityZotzpyre) entity;
            // if((zotz.motionX > 0.05 || zotz.motionZ > 0.05) && !zotz.isRiding() || (!zotz.isRiding() && !zotz.onGround)) {
            
            // if ( !zotz.onGround && !zotz.isRiding() || ( zotz.isRiding() && zotz.getRidingEntity().motionY >= 0.001D ) )
            if ( !zotz.onGround || zotz.isRiding() )
            {
            	// FLY
            	this.setRotateAngle(lLeg01, 1.3962634015954636F, 0.08726646259971647F, 0.40142572795869574F);
                this.setRotateAngle(lWing03, 0.0F, -0.9599310885968813F, 0.0F);
                this.setRotateAngle(lWing02, 0.0F, 0.5235987755982988F, 0.0F);
                this.setRotateAngle(rLeg01, 1.3962634015954636F, -0.08726646259971647F, -0.40142572795869574F);
                this.setRotateAngle(rWing03, 0.0F, 0.9599310885968813F, 0.0F);
                this.setRotateAngle(rWing02, 0.0F, -0.5235987755982988F, 0.0F);

                //
                this.setRotateAngle(tail01, 0.0F, 0.0F, 0.0F);
                this.setRotateAngle(tail02, 0.0F, 0.0F, 0.0F);
                this.setRotateAngle(rWingMembrane02, 0.0F, 0.0F, 0.0F);
                this.setRotateAngle(lWingMembrane02, 0.0F, 0.0F, 0.0F);
                this.setRotateAngle(lWing01, 0.0F, 0.0F, 0.0F);
                this.setRotateAngle(rWing01, 0.0F, 0.0F, 0.0F);
                
                if ( zotz.isRiding() )
                {
                	float moveAmount = MathHelper.cos(ageInTicks * 0.6F) * 0.12F;
                	this.setRotateAngle(lWing01, -moveAmount, 0.0F, moveAmount*2.0F + 0.2F);
                  	this.setRotateAngle(rWing01, moveAmount, 0.0F, -moveAmount*2.0F - 0.2F);
                  	
//                  	this.lWing03.rotationPointZ = this.lWing01.rotationPointZ; //moveAmount*2.0F + 0.2F;
//                  	this.rWing03.rotationPointZ = this.rWing01.rotationPointZ; //moveAmount*2.0F - 0.2F;
                  	
                    this.setRotateAngle(rLeg01, 0.3F, -0.2F + moveAmount, -0.45F - MathHelper.cos(ageInTicks * 0.6F) * 0.1F); // -0.1
                    this.setRotateAngle(lLeg01, 0.3F, 0.2F - moveAmount, -this.rLeg01.rotateAngleZ);
                    
                    this.chest.rotateAngleX = -0.8F + moveAmount * 0.6F;
                    this.chest.rotateAngleZ = moveAmount * 1.2F;
    	            this.stomach.rotateAngleZ = moveAmount;
    	            
                    this.setRotateAngle(head, 0.4F + moveAmount * 0.4F, 0.0F, 0.0F);
                }
                else
                {
                	// whole wing, z = flap V
                	this.lWing01.rotateAngleZ = MathHelper.cos(limbSwing) * limbSwingAmount * 0.4F - 0.13962634015954636F; // was 0.8F
                    this.rWing01.rotateAngleZ = -this.lWing01.rotateAngleZ;
                    
                    // whole wing, x = twist --
                    this.lWing01.rotateAngleX = MathHelper.cos(ageInTicks * 0.1F) * 0.4F - 0.13962634015954636F;
                    this.rWing01.rotateAngleX = this.lWing01.rotateAngleX;
                    
                    this.chest.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.3F) * 0.1F * limbSwingAmount;
    	            this.stomach.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.3F) * 0.1F * limbSwingAmount;
    	            
    	            this.lLeg01.rotateAngleZ = MathHelper.cos(ageInTicks * 0.6F) * 0.1F * limbSwingAmount;
    	            this.rLeg01.rotateAngleZ = this.rLeg01.rotateAngleZ;
    	            
    	            
    	            
    	            
    	            
    	            
    	            // whole wing, z = flap V
                	this.lWing03.rotateAngleZ = MathHelper.cos(limbSwing) * limbSwingAmount - 0.13962634015954636F;
                    this.rWing03.rotateAngleZ = -this.lWing03.rotateAngleZ;
                    
                    this.chest.rotateAngleX = -(MathHelper.cos(ageInTicks * 0.45F) * 0.1F * limbSwingAmount)/6.0F;
                    
                    this.setRotateAngle(head, -0.08726646259971647F, 0.0F, 0.0F);

                }
                
                this.tail01.rotateAngleZ = this.lWing01.rotateAngleZ;
                
                // first wing flaps
//              this.lWing02.rotateAngleZ = this.lWing01.rotateAngleZ*2.0F;
//              this.rWing02.rotateAngleZ = -this.lWing01.rotateAngleZ*2.0F;
                this.lowerJaw.rotateAngleX = 0.785F;
                
            }
            else
            {
                this.setRotateAngle(lWing02, 0.3490658503988659F, 0.6981317007977318F, 0.7853981633974483F);
                this.setRotateAngle(rWing03, 0.0F, 1.9547687622336491F, 0.0F);
                this.setRotateAngle(rWing01, -0.13962634015954636F, 0.5759586531581287F, -0.40142572795869574F);
                this.setRotateAngle(tail01, -0.6283185307179586F, 0.0F, 0.0F);
                this.setRotateAngle(rWingMembrane02, 0.0F, 0.5759586531581287F, 0.0F);
                this.setRotateAngle(lLeg01, 0.0F, 0.45378560551852565F, -0.03490658503988659F);
                this.setRotateAngle(lWingMembrane02, 0.0F, -0.5759586531581287F, 0.0F);
                this.setRotateAngle(tail02, -0.3141592653589793F, 0.0F, 0.0F);
                this.setRotateAngle(rWing02, 0.3490658503988659F, -0.6981317007977318F, -0.7853981633974483F);
                this.setRotateAngle(lWing01, -0.13962634015954636F, -0.5759586531581287F, 0.40142572795869574F);
                this.setRotateAngle(lWing03, 0.0F, -1.9547687622336491F, 0.0F);
                this.setRotateAngle(rLeg01, 0.0F, -0.45378560551852565F, 0.03490658503988659F);
                this.lLeg01.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F + (float) Math.PI) * limbSwingAmount;
                this.rLeg01.rotateAngleX = MathHelper.cos(limbSwing * 0.8665F) * limbSwingAmount;
                this.lWing01.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F + (float) Math.PI) * limbSwingAmount - 0.13962634015954636F;
                this.rWing01.rotateAngleX = MathHelper.cos(limbSwing * 0.8665F) * limbSwingAmount - 0.13962634015954636F;
                
                this.chest.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.45F) * 0.1F * limbSwingAmount;
                this.chest.rotateAngleX = this.chest.rotateAngleZ/6.0F;
	            this.stomach.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.55F) * 0.1F * limbSwingAmount;
	            
                this.lowerJaw.rotateAngleX = 0F;

            }
            
            this.neck.rotateAngleX = ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F;
            this.neck.rotateAngleY = ModelBetterAnimals.getHeadYaw((EntityLiving) entity) * 0.017453292F;
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
