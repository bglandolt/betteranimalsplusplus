package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityMoose;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;

/**
 * moose - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelMoose extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer ass;
    public ModelRenderer chest;
    public ModelRenderer lHindLeg01;
    public ModelRenderer rHindLeg01;
    public ModelRenderer tail;
    public ModelRenderer lHindLeg02;
    public ModelRenderer lHindLeg03;
    public ModelRenderer lHindHoof;
    public ModelRenderer rHindLeg02;
    public ModelRenderer rHindLeg03;
    public ModelRenderer rHindHoof;
    public ModelRenderer hump;
    public ModelRenderer lForeleg01;
    public ModelRenderer rForeleg01;
    public ModelRenderer neck;
    public ModelRenderer hump02;
    public ModelRenderer lForeleg02;
    public ModelRenderer lForeleg03;
    public ModelRenderer lForeHoof;
    public ModelRenderer rForeleg02;
    public ModelRenderer rForeleg03;
    public ModelRenderer rForeHoof;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer throat;
    public ModelRenderer snoutSlope;
    public ModelRenderer lEar;
    public ModelRenderer rEar;
    public ModelRenderer lAntler00;
    public ModelRenderer rAntler00;
    public ModelRenderer beard;
    public ModelRenderer lowerJaw;
    public ModelRenderer lAntler01a;
    public ModelRenderer lAntler01b;
    public ModelRenderer lAntler01c;
    public ModelRenderer lAntler01e;
    public ModelRenderer lAntler02a;
    public ModelRenderer lAntler01d;
    public ModelRenderer lAntler03a;
    public ModelRenderer lAntler04a;
    public ModelRenderer lAntler05a;
    public ModelRenderer lAntler06a;
    public ModelRenderer lAntler07a;
    public ModelRenderer lAntler08a;
    public ModelRenderer lAntler09a;
    public ModelRenderer lAntler07b;
    public ModelRenderer lAntler08b;
    public ModelRenderer lAntler09b;
    public ModelRenderer lAntler04b;
    public ModelRenderer lAntler05b;
    public ModelRenderer lAntler06b;
    public ModelRenderer rAntler01a;
    public ModelRenderer rAntler01b;
    public ModelRenderer rAntler01c;
    public ModelRenderer rAntler01e;
    public ModelRenderer rAntler02a;
    public ModelRenderer rAntler01d;
    public ModelRenderer rAntler03a;
    public ModelRenderer rAntler04a;
    public ModelRenderer rAntler05a;
    public ModelRenderer rAntler06a;
    public ModelRenderer rAntler07a;
    public ModelRenderer rAntler08a;
    public ModelRenderer rAntler09a;
    public ModelRenderer rAntler07b;
    public ModelRenderer rAntler08b;
    public ModelRenderer rAntler09b;
    public ModelRenderer rAntler04b;
    public ModelRenderer rAntler05b;
    public ModelRenderer rAntler06b;

    public ModelMoose() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.lAntler00 = new ModelRenderer(this, 84, 57);
        this.lAntler00.setRotationPoint(2.0F, -2.4F, -2.1F);
        this.lAntler00.addBox(0.0F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(lAntler00, 0.0F, 0.17453292519943295F, -0.3490658503988659F);
        this.lAntler09b = new ModelRenderer(this, 84, 50);
        this.lAntler09b.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.lAntler09b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler09b, 0.0F, 0.0F, -0.3665191429188092F);
        this.lHindLeg02 = new ModelRenderer(this, 65, 15);
        this.lHindLeg02.setRotationPoint(1.4F, 6.0F, -1.1F);
        this.lHindLeg02.addBox(-2.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F);
        this.setRotateAngle(lHindLeg02, 0.9105382707654417F, 0.0F, 0.0F);
        this.lEar = new ModelRenderer(this, 0, 0);
        this.lEar.setRotationPoint(2.0F, -2.3F, 0.0F);
        this.lEar.addBox(0.0F, -1.5F, -0.5F, 4, 3, 1, 0.0F);
        this.setRotateAngle(lEar, 0.22689280275926282F, 0.0F, -0.9599310885968813F);
        this.rAntler01e = new ModelRenderer(this, 84, 57);
        this.rAntler01e.mirror = true;
        this.rAntler01e.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.rAntler01e.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rAntler01e, 0.5235987755982988F, 0.0F, -0.47123889803846897F);
        this.lAntler06a = new ModelRenderer(this, 84, 57);
        this.lAntler06a.setRotationPoint(1.9F, 0.0F, 5.0F);
        this.lAntler06a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lAntler06a, -0.7853981633974483F, 0.17453292519943295F, 1.0471975511965976F);
        this.rAntler09a = new ModelRenderer(this, 84, 50);
        this.rAntler09a.mirror = true;
        this.rAntler09a.setRotationPoint(-0.3F, -0.1F, 5.7F);
        this.rAntler09a.addBox(-0.5F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler09a, -1.48352986419518F, 0.17453292519943295F, -1.53588974175501F);
        this.lAntler01b = new ModelRenderer(this, 84, 57);
        this.lAntler01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lAntler01b.addBox(0.0F, -0.2F, -0.5F, 5, 1, 1, 0.0F);
        this.rAntler08a = new ModelRenderer(this, 84, 50);
        this.rAntler08a.mirror = true;
        this.rAntler08a.setRotationPoint(-1.9F, -0.1F, 5.3F);
        this.rAntler08a.addBox(-0.5F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler08a, -1.0471975511965976F, 0.0F, -1.48352986419518F);
        this.neck = new ModelRenderer(this, 88, 0);
        this.neck.setRotationPoint(0.0F, -0.9F, -3.2F);
        this.neck.addBox(-2.5F, -2.5F, -7.5F, 5, 7, 7, 0.0F);
        this.setRotateAngle(neck, -0.3490658503988659F, 0.0F, 0.0F);
        this.lForeleg02 = new ModelRenderer(this, 48, 14);
        this.lForeleg02.setRotationPoint(0.8F, 4.4F, 0.6F);
        this.lForeleg02.addBox(-1.9F, -0.3F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(lForeleg02, 0.0F, 0.0F, 0.091106186954104F);
        this.lAntler06b = new ModelRenderer(this, 84, 57);
        this.lAntler06b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.lAntler06b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler06b, 0.0F, 0.0F, -0.3665191429188092F);
        this.snout = new ModelRenderer(this, 104, 36);
        this.snout.setRotationPoint(0.0F, 0.5F, -5.1F);
        this.snout.addBox(-2.5F, -1.2F, -5.9F, 5, 3, 6, 0.0F);
        this.lAntler01e = new ModelRenderer(this, 84, 57);
        this.lAntler01e.setRotationPoint(3.0F, 0.0F, 0.0F);
        this.lAntler01e.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lAntler01e, 0.5235987755982988F, 0.0F, 0.47123889803846897F);
        this.rHindLeg03 = new ModelRenderer(this, 68, 30);
        this.rHindLeg03.mirror = true;
        this.rHindLeg03.setRotationPoint(0.5F, 6.4F, 0.5F);
        this.rHindLeg03.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(rHindLeg03, -0.5009094953223726F, 0.0F, 0.0F);
        this.rAntler06a = new ModelRenderer(this, 84, 57);
        this.rAntler06a.mirror = true;
        this.rAntler06a.setRotationPoint(-1.9F, 0.0F, 5.0F);
        this.rAntler06a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rAntler06a, -0.7853981633974483F, -0.17453292519943295F, -1.0471975511965976F);
        this.rForeleg01 = new ModelRenderer(this, 45, 0);
        this.rForeleg01.mirror = true;
        this.rForeleg01.setRotationPoint(-3.9F, 1.3F, -1.0F);
        this.rForeleg01.addBox(-2.0F, -2.4F, -2.5F, 3, 7, 6, 0.0F);
        this.setRotateAngle(rForeleg01, 0.19198621771937624F, 0.0F, 0.08726646259971647F);
        this.lAntler01a = new ModelRenderer(this, 84, 57);
        this.lAntler01a.setRotationPoint(2.9F, 0.0F, 0.1F);
        this.lAntler01a.addBox(0.0F, -0.8F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(lAntler01a, 0.0F, 0.4363323129985824F, 0.13962634015954636F);
        this.rAntler04a = new ModelRenderer(this, 84, 57);
        this.rAntler04a.mirror = true;
        this.rAntler04a.setRotationPoint(-1.9F, 0.0F, 1.1F);
        this.rAntler04a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rAntler04a, -0.3141592653589793F, -0.17453292519943295F, -1.1344640137963142F);
        this.lForeleg03 = new ModelRenderer(this, 50, 25);
        this.lForeleg03.setRotationPoint(-0.4F, 4.2F, 0.0F);
        this.lForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(lForeleg03, -0.10471975511965977F, 0.0F, 0.0F);
        this.ass = new ModelRenderer(this, 0, 43);
        this.ass.setRotationPoint(0.0F, -0.3F, 9.2F);
        this.ass.addBox(-3.5F, -4.4F, 0.0F, 7, 10, 7, 0.0F);
        this.setRotateAngle(ass, -0.18203784098300857F, 0.0F, 0.0F);
        this.rAntler06b = new ModelRenderer(this, 84, 57);
        this.rAntler06b.mirror = true;
        this.rAntler06b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.rAntler06b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler06b, 0.0F, 0.0F, 0.3665191429188092F);
        this.rAntler07b = new ModelRenderer(this, 84, 50);
        this.rAntler07b.mirror = true;
        this.rAntler07b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.rAntler07b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler07b, 0.0F, 0.0F, 0.3665191429188092F);
        this.lAntler02a = new ModelRenderer(this, 83, 57);
        this.lAntler02a.setRotationPoint(2.8F, 0.0F, -0.3F);
        this.lAntler02a.addBox(-1.5F, -0.6F, 0.4F, 4, 1, 5, 0.0F);
        this.setRotateAngle(lAntler02a, 0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.rAntler00 = new ModelRenderer(this, 84, 57);
        this.rAntler00.mirror = true;
        this.rAntler00.setRotationPoint(-2.0F, -2.4F, -2.1F);
        this.rAntler00.addBox(-3.0F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(rAntler00, 0.0F, -0.17453292519943295F, 0.3490658503988659F);
        this.rHindLeg01 = new ModelRenderer(this, 64, 0);
        this.rHindLeg01.mirror = true;
        this.rHindLeg01.setRotationPoint(-2.5F, -0.8F, 3.3F);
        this.rHindLeg01.addBox(-3.0F, -1.9F, -2.2F, 4, 9, 6, 0.0F);
        this.setRotateAngle(rHindLeg01, -0.22759093446006054F, 0.0F, 0.0F);
        this.hump = new ModelRenderer(this, 33, 39);
        this.hump.setRotationPoint(0.0F, -2.7F, -4.7F);
        this.hump.addBox(-3.0F, -3.0F, -1.7F, 6, 3, 7, 0.0F);
        this.setRotateAngle(hump, 0.22689280275926282F, 0.0F, 0.0F);
        this.rAntler04b = new ModelRenderer(this, 84, 57);
        this.rAntler04b.mirror = true;
        this.rAntler04b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.rAntler04b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler04b, 0.0F, 0.0F, 0.3665191429188092F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, -0.6F, -3.6F);
        this.chest.addBox(-4.5F, -4.6F, -5.7F, 9, 11, 10, 0.0F);
        this.setRotateAngle(chest, -0.08726646259971647F, 0.0F, 0.0F);
        this.beard = new ModelRenderer(this, 56, 46);
        this.beard.setRotationPoint(0.0F, 1.9F, 1.7F);
        this.beard.addBox(-1.5F, -0.2F, -3.8F, 3, 5, 7, 0.0F);
        this.setRotateAngle(beard, -0.13962634015954636F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 29, 53);
        this.tail.setRotationPoint(0.0F, -3.4F, 6.1F);
        this.tail.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(tail, 0.5759586531581287F, 0.0F, 0.0F);
        this.rHindLeg02 = new ModelRenderer(this, 65, 15);
        this.rHindLeg02.mirror = true;
        this.rHindLeg02.setRotationPoint(-1.4F, 6.0F, -1.1F);
        this.rHindLeg02.addBox(-1.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F);
        this.setRotateAngle(rHindLeg02, 0.9105382707654417F, 0.0F, 0.0F);
        this.lAntler09a = new ModelRenderer(this, 84, 50);
        this.lAntler09a.setRotationPoint(0.3F, -0.1F, 5.7F);
        this.lAntler09a.addBox(-0.5F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler09a, -1.48352986419518F, -0.17453292519943295F, 1.53588974175501F);
        this.rAntler01b = new ModelRenderer(this, 84, 57);
        this.rAntler01b.mirror = true;
        this.rAntler01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rAntler01b.addBox(-5.0F, -0.2F, -0.5F, 5, 1, 1, 0.0F);
        this.rAntler01c = new ModelRenderer(this, 84, 57);
        this.rAntler01c.mirror = true;
        this.rAntler01c.setRotationPoint(-4.9F, 0.0F, 0.0F);
        this.rAntler01c.addBox(-0.5F, -3.2F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rAntler01c, 0.3490658503988659F, -0.17453292519943295F, -1.1344640137963142F);
        this.lHindLeg03 = new ModelRenderer(this, 68, 30);
        this.lHindLeg03.setRotationPoint(-0.5F, 6.4F, 0.5F);
        this.lHindLeg03.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(lHindLeg03, -0.5009094953223726F, 0.0F, 0.0F);
        this.lAntler07a = new ModelRenderer(this, 84, 50);
        this.lAntler07a.setRotationPoint(1.9F, 0.0F, 2.8F);
        this.lAntler07a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lAntler07a, -0.7853981633974483F, 0.17453292519943295F, 1.1344640137963142F);
        this.lAntler01c = new ModelRenderer(this, 84, 57);
        this.lAntler01c.setRotationPoint(4.9F, 0.0F, 0.0F);
        this.lAntler01c.addBox(-0.5F, -3.2F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lAntler01c, 0.3490658503988659F, 0.17453292519943295F, 1.1344640137963142F);
        this.rForeleg03 = new ModelRenderer(this, 50, 25);
        this.rForeleg03.mirror = true;
        this.rForeleg03.setRotationPoint(0.4F, 4.2F, 0.0F);
        this.rForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(rForeleg03, -0.10471975511965977F, 0.0F, 0.0F);
        this.lAntler04b = new ModelRenderer(this, 84, 57);
        this.lAntler04b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.lAntler04b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler04b, 0.0F, 0.0F, -0.3665191429188092F);
        this.lAntler05b = new ModelRenderer(this, 84, 57);
        this.lAntler05b.setRotationPoint(0.0F, -3.9F, 0.0F);
        this.lAntler05b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler05b, 0.0F, 0.0F, -0.3665191429188092F);
        this.lAntler01d = new ModelRenderer(this, 84, 57);
        this.lAntler01d.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.lAntler01d.addBox(-0.5F, -0.9F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(lAntler01d, 0.0F, 0.0F, -0.3665191429188092F);
        this.rForeHoof = new ModelRenderer(this, 32, 0);
        this.rForeHoof.mirror = true;
        this.rForeHoof.setRotationPoint(-0.0F, 8.8F, 0.3F);
        this.rForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.head = new ModelRenderer(this, 88, 16);
        this.head.setRotationPoint(0.0F, 0.0F, -6.2F);
        this.head.addBox(-3.0F, -3.0F, -5.1F, 6, 7, 6, 0.0F);
        this.setRotateAngle(head, 0.5235987755982988F, 0.0F, 0.0F);
        this.throat = new ModelRenderer(this, 102, 47);
        this.throat.setRotationPoint(0.0F, 1.6F, -5.2F);
        this.throat.addBox(-2.5F, 0.6F, -2.7F, 5, 1, 3, 0.0F);
        this.rAntler08b = new ModelRenderer(this, 84, 50);
        this.rAntler08b.mirror = true;
        this.rAntler08b.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.rAntler08b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler08b, 0.0F, 0.0F, 0.3665191429188092F);
        this.lAntler04a = new ModelRenderer(this, 84, 57);
        this.lAntler04a.setRotationPoint(1.9F, 0.0F, 1.1F);
        this.lAntler04a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(lAntler04a, -0.3141592653589793F, 0.17453292519943295F, 1.1344640137963142F);
        this.rAntler07a = new ModelRenderer(this, 84, 50);
        this.rAntler07a.mirror = true;
        this.rAntler07a.setRotationPoint(-1.9F, 0.0F, 2.8F);
        this.rAntler07a.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        this.setRotateAngle(rAntler07a, -0.7853981633974483F, -0.17453292519943295F, -1.1344640137963142F);
        this.lAntler03a = new ModelRenderer(this, 78, 47);
        this.lAntler03a.setRotationPoint(0.4F, 0.0F, 5.3F);
        this.lAntler03a.addBox(-2.2F, -0.61F, -0.7F, 5, 1, 7, 0.0F);
        this.setRotateAngle(lAntler03a, 0.20943951023931953F, -0.4363323129985824F, 0.0F);
        this.rHindHoof = new ModelRenderer(this, 32, 0);
        this.rHindHoof.mirror = true;
        this.rHindHoof.setRotationPoint(-0.0F, 7.8F, 0.3F);
        this.rHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.lForeHoof = new ModelRenderer(this, 32, 0);
        this.lForeHoof.setRotationPoint(-0.0F, 8.8F, 0.3F);
        this.lForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.rAntler02a = new ModelRenderer(this, 83, 57);
        this.rAntler02a.mirror = true;
        this.rAntler02a.setRotationPoint(-2.8F, 0.0F, -0.3F);
        this.rAntler02a.addBox(-2.5F, -0.6F, 0.4F, 4, 1, 5, 0.0F);
        this.setRotateAngle(rAntler02a, 0.4363323129985824F, 0.08726646259971647F, 0.0F);
        this.rAntler01d = new ModelRenderer(this, 84, 57);
        this.rAntler01d.mirror = true;
        this.rAntler01d.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.rAntler01d.addBox(-0.5F, -0.9F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(rAntler01d, 0.0F, 0.0F, 0.3665191429188092F);
        this.rAntler05a = new ModelRenderer(this, 84, 57);
        this.rAntler05a.mirror = true;
        this.rAntler05a.setRotationPoint(-1.9F, 0.0F, 3.1F);
        this.rAntler05a.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rAntler05a, -0.5235987755982988F, -0.17453292519943295F, -1.1344640137963142F);
        this.hump02 = new ModelRenderer(this, 32, 52);
        this.hump02.setRotationPoint(0.0F, -0.2F, 4.0F);
        this.hump02.addBox(-3.5F, -3.0F, -0.4F, 7, 3, 8, 0.0F);
        this.setRotateAngle(hump02, -0.5235987755982988F, 0.0F, 0.0F);
        this.rAntler05b = new ModelRenderer(this, 84, 57);
        this.rAntler05b.mirror = true;
        this.rAntler05b.setRotationPoint(0.0F, -3.9F, 0.0F);
        this.rAntler05b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler05b, 0.0F, 0.0F, 0.3665191429188092F);
        this.lAntler08a = new ModelRenderer(this, 84, 50);
        this.lAntler08a.setRotationPoint(1.9F, -0.1F, 5.3F);
        this.lAntler08a.addBox(-0.5F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler08a, -1.0471975511965976F, 0.0F, 1.48352986419518F);
        this.lHindHoof = new ModelRenderer(this, 32, 0);
        this.lHindHoof.setRotationPoint(-0.0F, 7.8F, 0.3F);
        this.lHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.rEar = new ModelRenderer(this, 0, 0);
        this.rEar.mirror = true;
        this.rEar.setRotationPoint(-2.0F, -2.3F, 0.0F);
        this.rEar.addBox(-4.0F, -1.5F, -0.5F, 4, 3, 1, 0.0F);
        this.setRotateAngle(rEar, 0.22689280275926282F, 0.0F, 0.9599310885968813F);
        this.lAntler07b = new ModelRenderer(this, 84, 50);
        this.lAntler07b.setRotationPoint(0.0F, -2.8F, 0.0F);
        this.lAntler07b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler07b, 0.0F, 0.0F, -0.3665191429188092F);
        this.snoutSlope = new ModelRenderer(this, 78, 36);
        this.snoutSlope.setRotationPoint(0.0F, -1.6F, -5.0F);
        this.snoutSlope.addBox(-2.0F, -1.3F, -6.6F, 4, 3, 7, 0.0F);
        this.setRotateAngle(snoutSlope, 0.17453292519943295F, 0.0F, 0.0F);
        this.rAntler03a = new ModelRenderer(this, 78, 47);
        this.rAntler03a.mirror = true;
        this.rAntler03a.setRotationPoint(-0.4F, 0.0F, 5.3F);
        this.rAntler03a.addBox(-2.8F, -0.61F, -0.7F, 5, 1, 7, 0.0F);
        this.setRotateAngle(rAntler03a, 0.20943951023931953F, 0.4363323129985824F, 0.0F);
        this.rForeleg02 = new ModelRenderer(this, 48, 14);
        this.rForeleg02.mirror = true;
        this.rForeleg02.setRotationPoint(-0.8F, 4.4F, 0.6F);
        this.rForeleg02.addBox(-1.0F, 0.0F, -2.0F, 3, 5, 4, 0.0F);
        this.setRotateAngle(rForeleg02, 0.0F, 0.0F, -0.091106186954104F);
        this.lowerJaw = new ModelRenderer(this, 102, 54);
        this.lowerJaw.setRotationPoint(0.0F, 1.1F, -1.9F);
        this.lowerJaw.addBox(-2.0F, -0.6F, -3.4F, 4, 1, 3, 0.0F);
        this.lAntler08b = new ModelRenderer(this, 84, 50);
        this.lAntler08b.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.lAntler08b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(lAntler08b, 0.0F, 0.0F, -0.3665191429188092F);
        this.lForeleg01 = new ModelRenderer(this, 45, 0);
        this.lForeleg01.setRotationPoint(3.9F, 1.3F, -1.0F);
        this.lForeleg01.addBox(-1.0F, -2.4F, -2.5F, 3, 7, 6, 0.0F);
        this.setRotateAngle(lForeleg01, 0.19198621771937624F, 0.0F, -0.08726646259971647F);
        this.lAntler05a = new ModelRenderer(this, 84, 57);
        this.lAntler05a.setRotationPoint(1.9F, 0.0F, 3.1F);
        this.lAntler05a.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lAntler05a, -0.5235987755982988F, 0.17453292519943295F, 1.1344640137963142F);
        this.rAntler01a = new ModelRenderer(this, 84, 57);
        this.rAntler01a.mirror = true;
        this.rAntler01a.setRotationPoint(-2.9F, 0.0F, 0.1F);
        this.rAntler01a.addBox(-5.0F, -0.8F, -0.5F, 5, 1, 1, 0.0F);
        this.setRotateAngle(rAntler01a, 0.0F, -0.4363323129985824F, -0.13962634015954636F);
        this.body = new ModelRenderer(this, 0, 22);
        this.body.setRotationPoint(0.0F, 4.2F, -3.9F);
        this.body.addBox(-4.0F, -4.2F, 0.0F, 8, 10, 10, 0.0F);
        this.lHindLeg01 = new ModelRenderer(this, 64, 0);
        this.lHindLeg01.setRotationPoint(2.5F, -0.8F, 3.3F);
        this.lHindLeg01.addBox(-1.0F, -1.9F, -2.2F, 4, 9, 6, 0.0F);
        this.setRotateAngle(lHindLeg01, -0.22759093446006054F, 0.0F, 0.0F);
        this.rAntler09b = new ModelRenderer(this, 84, 50);
        this.rAntler09b.mirror = true;
        this.rAntler09b.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.rAntler09b.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(rAntler09b, 0.0F, 0.0F, 0.3665191429188092F);
        this.head.addChild(this.lAntler00);
        this.lAntler09a.addChild(this.lAntler09b);
        this.lHindLeg01.addChild(this.lHindLeg02);
        this.head.addChild(this.lEar);
        this.rAntler01a.addChild(this.rAntler01e);
        this.lAntler02a.addChild(this.lAntler06a);
        this.rAntler03a.addChild(this.rAntler09a);
        this.lAntler01a.addChild(this.lAntler01b);
        this.rAntler03a.addChild(this.rAntler08a);
        this.chest.addChild(this.neck);
        this.lForeleg01.addChild(this.lForeleg02);
        this.lAntler06a.addChild(this.lAntler06b);
        this.head.addChild(this.snout);
        this.lAntler01a.addChild(this.lAntler01e);
        this.rHindLeg02.addChild(this.rHindLeg03);
        this.rAntler02a.addChild(this.rAntler06a);
        this.chest.addChild(this.rForeleg01);
        this.lAntler00.addChild(this.lAntler01a);
        this.rAntler02a.addChild(this.rAntler04a);
        this.lForeleg02.addChild(this.lForeleg03);
        this.body.addChild(this.ass);
        this.rAntler06a.addChild(this.rAntler06b);
        this.rAntler07a.addChild(this.rAntler07b);
        this.lAntler01a.addChild(this.lAntler02a);
        this.head.addChild(this.rAntler00);
        this.ass.addChild(this.rHindLeg01);
        this.chest.addChild(this.hump);
        this.rAntler04a.addChild(this.rAntler04b);
        this.body.addChild(this.chest);
        this.throat.addChild(this.beard);
        this.ass.addChild(this.tail);
        this.rHindLeg01.addChild(this.rHindLeg02);
        this.lAntler03a.addChild(this.lAntler09a);
        this.rAntler01a.addChild(this.rAntler01b);
        this.rAntler01a.addChild(this.rAntler01c);
        this.lHindLeg02.addChild(this.lHindLeg03);
        this.lAntler03a.addChild(this.lAntler07a);
        this.lAntler01a.addChild(this.lAntler01c);
        this.rForeleg02.addChild(this.rForeleg03);
        this.lAntler04a.addChild(this.lAntler04b);
        this.lAntler05a.addChild(this.lAntler05b);
        this.lAntler01c.addChild(this.lAntler01d);
        this.rForeleg03.addChild(this.rForeHoof);
        this.neck.addChild(this.head);
        this.head.addChild(this.throat);
        this.rAntler08a.addChild(this.rAntler08b);
        this.lAntler02a.addChild(this.lAntler04a);
        this.rAntler03a.addChild(this.rAntler07a);
        this.lAntler02a.addChild(this.lAntler03a);
        this.rHindLeg03.addChild(this.rHindHoof);
        this.lForeleg03.addChild(this.lForeHoof);
        this.rAntler01a.addChild(this.rAntler02a);
        this.rAntler01c.addChild(this.rAntler01d);
        this.rAntler02a.addChild(this.rAntler05a);
        this.hump.addChild(this.hump02);
        this.rAntler05a.addChild(this.rAntler05b);
        this.lAntler03a.addChild(this.lAntler08a);
        this.lHindLeg03.addChild(this.lHindHoof);
        this.head.addChild(this.rEar);
        this.lAntler07a.addChild(this.lAntler07b);
        this.head.addChild(this.snoutSlope);
        this.rAntler02a.addChild(this.rAntler03a);
        this.rForeleg01.addChild(this.rForeleg02);
        this.throat.addChild(this.lowerJaw);
        this.lAntler08a.addChild(this.lAntler08b);
        this.chest.addChild(this.lForeleg01);
        this.lAntler02a.addChild(this.lAntler05a);
        this.rAntler00.addChild(this.rAntler01a);
        this.ass.addChild(this.lHindLeg01);
        this.rAntler09a.addChild(this.rAntler09b);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
    
    private int ii = -1;

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        float f = limbSwing;
        float f1 = limbSwingAmount/2.0F;

        
        //this.chest.rotateAngleX = MathHelper.cos(limbSwing * 0.8665F) * 0.6F * limbSwingAmount  - 0.22759093446006054F;
        //this.torso.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F + (float) Math.PI) * 0.1F * limbSwingAmount - 0.22759093446006054F;
        this.lForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 + 0.19198621771937624F;
        this.rForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 + 0.19198621771937624F;
        this.rHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 - 0.22759093446006054F;
        this.lHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 - 0.22759093446006054F;

        if(entity instanceof EntityMoose)
        {
            EntityMoose moose = (EntityMoose) entity;
            float eatTime = moose.getEatTime();
            
            if ( moose.headRam >= 0 ) // 20
            {
//            	this.head.rotateAngleX = -(float)(20-moose.headRam) * 0.032F + 0.64F;
//              this.neck.rotateAngleX = -(float)(20-moose.headRam) * 0.035F + 0.7F;
            	if ( moose.headRam == 20 )
            	{
            		this.ii = (moose.world.rand.nextBoolean()?1:-1);
            	}
            	
            	if ( moose.headRam > 7 )
            	{
            		this.head.rotateAngleX = -(float)(20-moose.headRam) * 0.04F + 1.165F;
                    this.neck.rotateAngleX = -(float)(20-moose.headRam) * 0.04F + 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
	                	this.head.rotateAngleZ = ((float)(8-moose.headRam) * 0.012F + 0.144F)*2.0F*this.ii;
            	}
            	else
            	{
            		this.head.rotateAngleX = -(float)(moose.headRam-7) * 0.0266F - 0.16F + 0.525F;
                    this.neck.rotateAngleX = -(float)(moose.headRam-7) * 0.0166F - 0.1F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
                		this.head.rotateAngleZ = ((float)(moose.headRam-7) * 0.020571F + 0.144F)*2.0F;
            	}
            	moose.headRam--;
            	moose.headDownDuration = -1;
            }
            else if ( moose.headDownDuration >= 0 ) // 120
            {
            	if ( moose.headDownDuration > 100 )
            	{
//            		this.head.rotateAngleX = -(float)(moose.headDownDuration-90) * 0.0213F + 0.64F;
//                    this.neck.rotateAngleX = -(float)(moose.headDownDuration-90) * 0.0233F + 0.7F;
            		this.head.rotateAngleX = -(float)(moose.headDownDuration-100) * 0.032F + 1.165F;
            		this.neck.rotateAngleX = -(float)(moose.headDownDuration-100) * 0.035F + 0.7F +  ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
            	}
            	else if ( moose.headDownDuration < 30 )
            	{
            		this.head.rotateAngleX = -(float)(30-moose.headDownDuration) * 0.0213F + 1.165F;
                    this.neck.rotateAngleX = -(float)(30-moose.headDownDuration) * 0.0233F + 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
            	}
            	else
            	{
                    this.head.rotateAngleX = 1.165F;
                    this.neck.rotateAngleX = 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
            	}
            	moose.headDownDuration--;
            }
            else if ( eatTime > 0 ) // 60
            {
            	if ( eatTime > 40 )
            	{
            		this.head.rotateAngleX = -(float)(eatTime-40) * 0.032F + 1.165F;
            		this.neck.rotateAngleX = -(float)(eatTime-40) * 0.035F + 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
            	}
            	else if ( eatTime < 30 )
            	{
            		this.head.rotateAngleX = -(float)(30-eatTime) * 0.0213F + 1.165F;
                    this.neck.rotateAngleX = -(float)(30-eatTime) * 0.0233F + 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
            	}
            	else
            	{
            		this.head.rotateAngleX = 1.165F; // 0.64F + 0.525F
                    this.neck.rotateAngleX = 0.7F + ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
                    this.lowerJaw.rotateAngleX = (float) Math.toRadians((eatTime % 20F)) + 0.1F;
            	}
            }
            else
            {
                this.head.rotateAngleZ = 0.0F;
                this.head.rotateAngleX = 0.0F;
                this.neck.rotateAngleX = 0.0F;
                this.head.rotateAngleX = 0.525F;
                this.neck.rotateAngleX = ModelBetterAnimals.getHeadPitch((EntityLiving) entity) * 0.017453292F - 0.08726646259971647F;
                //System.out.println(this.neck.rotateAngleX);

                this.chest.rotateAngleY = ModelBetterAnimals.getHeadYaw((EntityLiving) entity) * 0.017453292F * 0.5F;
                this.lowerJaw.rotateAngleX = 0F;
            }
            
            this.body.rotateAngleZ = MathHelper.cos(f * 0.35F) * 0.18F * f1;
            this.chest.rotateAngleZ = MathHelper.cos(f * 0.4F) * 0.18F * f1;
            
//            {
//            	this.neck.rotateAngleX = -0.3490658503988659F + (float) Math.toRadians(60F);
//                this.head.rotateAngleX = -0.31869712141416456F + (float) Math.toRadians(55F);
//            }
        }
    }
    
//    EntityMoose moose = (EntityMoose) entity;
//    float eatTime = moose.getEatTime();
//    if(eatTime > 0) {
//        this.neck.rotateAngleX = -0.3490658503988659F + (float) Math.toRadians(60F); .7
//        this.head.rotateAngleX = -0.31869712141416456F + (float) Math.toRadians(55F);
//        this.lowerJaw.rotateAngleX = (float) Math.toRadians((eatTime % 20F)) + 0.1F;
//    } else {
//        this.head.rotateAngleX = 0.5235987755982988F;
//        this.lowerJaw.rotateAngleX = 0F;
//    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
