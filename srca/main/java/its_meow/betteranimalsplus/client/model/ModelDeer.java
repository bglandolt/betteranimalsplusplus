package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityDeer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;

/**
 * deer_2 - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelDeer extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer ass;
    public ModelRenderer chest;
    public ModelRenderer lForeleg01;
    public ModelRenderer rForeleg01;
    public ModelRenderer lHindLeg01;
    public ModelRenderer rHindLeg01;
    public ModelRenderer tail;
    public ModelRenderer lHindLeg02;
    public ModelRenderer lHindLeg03;
    public ModelRenderer lHindHoof;
    public ModelRenderer rHindLeg02;
    public ModelRenderer rHindLeg03;
    public ModelRenderer rHindHoof;
    public ModelRenderer neck;
    public ModelRenderer mane03;
    public ModelRenderer mane04;
    public ModelRenderer head;
    public ModelRenderer mane01;
    public ModelRenderer mane02;
    public ModelRenderer snout;
    public ModelRenderer upperJaw;
    public ModelRenderer lEar;
    public ModelRenderer rEar;
    public ModelRenderer throat;
    public ModelRenderer lAntler01;
    public ModelRenderer rAntler01;
    public ModelRenderer lowerJaw;
    public ModelRenderer lAntler02;
    public ModelRenderer lAntler01b;
    public ModelRenderer lAntler03;
    public ModelRenderer lAntler04;
    public ModelRenderer lAntler03b;
    public ModelRenderer lAntler04b;
    public ModelRenderer lAntler05;
    public ModelRenderer lAntler06;
    public ModelRenderer lAntler07;
    public ModelRenderer lAntler06b;
    public ModelRenderer lAntler08;
    public ModelRenderer lAntler07b;
    public ModelRenderer lAntler08b;
    public ModelRenderer lAntler08c;
    public ModelRenderer rAntler02;
    public ModelRenderer rAntler01b;
    public ModelRenderer rAntler03;
    public ModelRenderer rAntler04;
    public ModelRenderer rAntler03b;
    public ModelRenderer rAntler04b;
    public ModelRenderer rAntler05;
    public ModelRenderer rAntler06;
    public ModelRenderer rAntler07;
    public ModelRenderer rAntler06b;
    public ModelRenderer rAntler08;
    public ModelRenderer rAntler07b;
    public ModelRenderer rAntler08b;
    public ModelRenderer rAntler08c;
    public ModelRenderer lForeleg02;
    public ModelRenderer lForeleg03;
    public ModelRenderer lForeHoof;
    public ModelRenderer rForeleg02;
    public ModelRenderer rForeleg03;
    public ModelRenderer rForeHoof;

    public ModelDeer() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.throat = new ModelRenderer(this, 41, 48);
        this.throat.setRotationPoint(0.0F, 1.2F, -0.49F);
        this.throat.addBox(-2.0F, 0.0F, -0.5F, 4, 2, 1, 0.0F);
        this.snout = new ModelRenderer(this, 54, 31);
        this.snout.setRotationPoint(0.0F, 1.7F, -3.1F);
        this.snout.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(snout, 0.3490658503988659F, 0.0F, 0.0F);
        this.rAntler04b = new ModelRenderer(this, 0, 20);
        this.rAntler04b.mirror = true;
        this.rAntler04b.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.rAntler04b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rAntler04b, 0.40142572795869574F, -0.3490658503988659F, 0.0F);
        this.lAntler02 = new ModelRenderer(this, 0, 13);
        this.lAntler02.setRotationPoint(-0.1F, 0.1F, 0.0F);
        this.lAntler02.addBox(-0.5F, -0.4F, -2.3F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler02, -0.5235987755982988F, 0.0F, 0.0F);
        this.lAntler04b = new ModelRenderer(this, 0, 20);
        this.lAntler04b.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.lAntler04b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lAntler04b, 0.40142572795869574F, 0.3490658503988659F, 0.0F);
        this.lAntler08b = new ModelRenderer(this, 0, 19);
        this.lAntler08b.setRotationPoint(0.0F, 0.0F, -0.7F);
        this.lAntler08b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lAntler08b, 0.7853981633974483F, 0.40142572795869574F, 0.0F);
        this.lHindLeg03 = new ModelRenderer(this, 45, 26);
        this.lHindLeg03.setRotationPoint(0.1F, 5.8F, 0.3F);
        this.lHindLeg03.addBox(-1.0F, -0.3F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(lHindLeg03, -0.4886921905584123F, 0.0F, 0.0F);
        this.lAntler07 = new ModelRenderer(this, 0, 19);
        this.lAntler07.setRotationPoint(0.0F, 0.0F, -3.8F);
        this.lAntler07.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler07, 0.0F, 0.4886921905584123F, 0.0F);
        this.rAntler08 = new ModelRenderer(this, 0, 19);
        this.rAntler08.mirror = true;
        this.rAntler08.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.rAntler08.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler08, -0.3141592653589793F, -0.6108652381980153F, 0.0F);
        this.tail = new ModelRenderer(this, 54, 25);
        this.tail.setRotationPoint(0.0F, -2.7F, 5.4F);
        this.tail.addBox(-1.5F, 0.0F, -1.0F, 3, 4, 2, 0.0F);
        this.setRotateAngle(tail, 0.5759586531581287F, 0.0F, 0.0F);
        this.lAntler06 = new ModelRenderer(this, 0, 19);
        this.lAntler06.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.lAntler06.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler06, 0.8726646259971648F, 0.22689280275926282F, 0.0F);
        this.rForeleg01 = new ModelRenderer(this, 29, 0);
        this.rForeleg01.mirror = true;
        this.rForeleg01.setRotationPoint(-3.1F, 0.9F, 2.3F);
        this.rForeleg01.addBox(-2.0F, -2.4F, -2.5F, 3, 7, 5, 0.0F);
        this.setRotateAngle(rForeleg01, 0.13962634015954636F, 0.0F, 0.08726646259971647F);
        this.lAntler08c = new ModelRenderer(this, 0, 19);
        this.lAntler08c.setRotationPoint(0.0F, -0.2F, 0.2F);
        this.lAntler08c.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lAntler08c, -0.3490658503988659F, -0.6108652381980153F, -1.2217304763960306F);
        this.rForeleg03 = new ModelRenderer(this, 45, 26);
        this.rForeleg03.mirror = true;
        this.rForeleg03.setRotationPoint(0.4F, 2.7F, 0.0F);
        this.rForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(rForeleg03, -0.13962634015954636F, 0.0F, 0.0F);
        this.mane01 = new ModelRenderer(this, 0, 48);
        this.mane01.setRotationPoint(0.0F, 1.6F, -4.0F);
        this.mane01.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 6, 0.0F);
        this.setRotateAngle(mane01, -0.5410520681182421F, 0.0F, 0.0F);
        this.rAntler06 = new ModelRenderer(this, 0, 19);
        this.rAntler06.mirror = true;
        this.rAntler06.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.rAntler06.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler06, 0.8726646259971648F, -0.22689280275926282F, 0.0F);
        this.chest = new ModelRenderer(this, 0, 0);
        this.chest.setRotationPoint(0.0F, 0.8F, 2.4F);
        this.chest.addBox(-3.5F, -3.0F, -5.0F, 7, 6, 5, 0.0F);
        this.setRotateAngle(chest, -0.8377580409572781F, 0.0F, 0.0F);
        this.upperJaw = new ModelRenderer(this, 19, 57);
        this.upperJaw.setRotationPoint(0.0F, 1.3F, -1.0F);
        this.upperJaw.addBox(-2.0F, 0.0F, -1.0F, 4, 4, 1, 0.0F);
        this.rHindHoof = new ModelRenderer(this, 32, 20);
        this.rHindHoof.mirror = true;
        this.rHindHoof.setRotationPoint(-0.0F, 6.2F, 0.39F);
        this.rHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 52, 48);
        this.lowerJaw.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.lowerJaw.addBox(-2.0F, 0.0F, -0.5F, 4, 2, 1, 0.0F);
        this.lForeleg01 = new ModelRenderer(this, 29, 0);
        this.lForeleg01.setRotationPoint(3.1F, 0.9F, 2.3F);
        this.lForeleg01.addBox(-1.0F, -2.4F, -2.5F, 3, 7, 5, 0.0F);
        this.setRotateAngle(lForeleg01, 0.13962634015954636F, 0.0F, -0.08726646259971647F);
        this.rAntler04 = new ModelRenderer(this, 0, 13);
        this.rAntler04.mirror = true;
        this.rAntler04.setRotationPoint(0.0F, -0.1F, -2.6F);
        this.rAntler04.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler04, 0.45378560551852565F, -0.22689280275926282F, 0.0F);
        this.lHindLeg01 = new ModelRenderer(this, 46, 0);
        this.lHindLeg01.setRotationPoint(2.3F, -0.4F, 2.9F);
        this.lHindLeg01.addBox(0.0F, -1.9F, -2.0F, 3, 8, 5, 0.0F);
        this.setRotateAngle(lHindLeg01, -0.22689280275926282F, 0.0F, 0.0F);
        this.rAntler03b = new ModelRenderer(this, 0, 12);
        this.rAntler03b.mirror = true;
        this.rAntler03b.setRotationPoint(0.0F, 0.0F, -1.3F);
        this.rAntler03b.addBox(-0.5F, -0.5F, -2.7F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler03b, 1.3962634015954636F, 0.9599310885968813F, 0.0F);
        this.rAntler06b = new ModelRenderer(this, 2, 21);
        this.rAntler06b.mirror = true;
        this.rAntler06b.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.rAntler06b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rAntler06b, 0.3141592653589793F, -0.45378560551852565F, 0.0F);
        this.mane03 = new ModelRenderer(this, 17, 48);
        this.mane03.setRotationPoint(0.0F, 1.8F, -4.7F);
        this.mane03.addBox(-3.0F, -0.1F, -0.1F, 6, 1, 8, 0.0F);
        this.setRotateAngle(mane03, -0.6981317007977318F, 0.0F, 0.0F);
        this.mane04 = new ModelRenderer(this, 36, 54);
        this.mane04.setRotationPoint(0.0F, 2.0F, -3.1F);
        this.mane04.addBox(-3.0F, -1.0F, 0.0F, 6, 2, 8, 0.0F);
        this.setRotateAngle(mane04, -0.6108652381980153F, 0.0F, 0.0F);
        this.lAntler01 = new ModelRenderer(this, 0, 13);
        this.lAntler01.setRotationPoint(1.5F, -0.5F, -3.9F);
        this.lAntler01.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lAntler01, 0.0F, -0.2617993877991494F, 0.2617993877991494F);
        this.lAntler03 = new ModelRenderer(this, 0, 13);
        this.lAntler03.setRotationPoint(0.0F, 0.5F, -1.4F);
        this.lAntler03.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler03, -0.4886921905584123F, -0.22689280275926282F, 0.0F);
        this.lEar = new ModelRenderer(this, 21, 0);
        this.lEar.setRotationPoint(2.1F, -1.0F, -3.0F);
        this.lEar.addBox(-1.0F, -0.7F, -3.1F, 2, 1, 3, 0.0F);
        this.setRotateAngle(lEar, 0.2792526803190927F, -1.0821041362364843F, 0.0F);
        this.head = new ModelRenderer(this, 46, 39);
        this.head.setRotationPoint(0.0F, 1.1F, -5.0F);
        this.head.addBox(-2.5F, -3.0F, -4.0F, 5, 5, 4, 0.0F);
        this.setRotateAngle(head, -0.3141592653589793F, 0.0F, 0.0F);
        this.lForeleg03 = new ModelRenderer(this, 45, 26);
        this.lForeleg03.setRotationPoint(-0.4F, 2.7F, 0.0F);
        this.lForeleg03.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(lForeleg03, -0.13962634015954636F, 0.0F, 0.0F);
        this.rHindLeg03 = new ModelRenderer(this, 45, 26);
        this.rHindLeg03.mirror = true;
        this.rHindLeg03.setRotationPoint(-0.1F, 5.8F, 0.3F);
        this.rHindLeg03.addBox(-1.0F, -0.3F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(rHindLeg03, -0.4886921905584123F, 0.0F, 0.0F);
        this.lForeHoof = new ModelRenderer(this, 32, 20);
        this.lForeHoof.setRotationPoint(-0.0F, 7.3F, 0.4F);
        this.lForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.lHindHoof = new ModelRenderer(this, 32, 20);
        this.lHindHoof.setRotationPoint(-0.0F, 6.2F, 0.39F);
        this.lHindHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.mane02 = new ModelRenderer(this, 0, 56);
        this.mane02.setRotationPoint(0.0F, 1.7F, -2.2F);
        this.mane02.addBox(-2.5F, 0.0F, 0.0F, 5, 1, 7, 0.0F);
        this.setRotateAngle(mane02, -0.5410520681182421F, 0.0F, 0.0F);
        this.rForeHoof = new ModelRenderer(this, 32, 20);
        this.rForeHoof.mirror = true;
        this.rForeHoof.setRotationPoint(-0.0F, 7.3F, 0.4F);
        this.rForeHoof.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 3, 0.0F);
        this.body = new ModelRenderer(this, 0, 13);
        this.body.setRotationPoint(0.0F, 7.0F, -9.0F);
        this.body.addBox(-4.0F, -3.5F, 0.0F, 8, 8, 13, 0.0F);
        this.rAntler01b = new ModelRenderer(this, 0, 12);
        this.rAntler01b.mirror = true;
        this.rAntler01b.setRotationPoint(0.0F, 0.0F, -1.2F);
        this.rAntler01b.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler01b, 0.9599310885968813F, 0.6981317007977318F, -0.3141592653589793F);
        this.ass = new ModelRenderer(this, 0, 34);
        this.ass.setRotationPoint(0.0F, -0.4F, 12.3F);
        this.ass.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 6, 0.0F);
        this.setRotateAngle(ass, -0.17453292519943295F, 0.0F, 0.0F);
        this.rAntler08c = new ModelRenderer(this, 0, 19);
        this.rAntler08c.mirror = true;
        this.rAntler08c.setRotationPoint(0.0F, -0.3F, 0.2F);
        this.rAntler08c.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rAntler08c, -0.3490658503988659F, 0.6108652381980153F, 1.2217304763960306F);
        this.rHindLeg01 = new ModelRenderer(this, 46, 0);
        this.rHindLeg01.mirror = true;
        this.rHindLeg01.setRotationPoint(-2.3F, -0.5F, 2.9F);
        this.rHindLeg01.addBox(-3.0F, -1.9F, -2.0F, 3, 8, 5, 0.0F);
        this.setRotateAngle(rHindLeg01, -0.22689280275926282F, 0.0F, 0.0F);
        this.lAntler03b = new ModelRenderer(this, 0, 12);
        this.lAntler03b.setRotationPoint(0.0F, 0.0F, -1.3F);
        this.lAntler03b.addBox(-0.5F, -0.5F, -2.7F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler03b, 1.3962634015954636F, -0.9599310885968813F, 0.0F);
        this.rAntler01 = new ModelRenderer(this, 0, 13);
        this.rAntler01.mirror = true;
        this.rAntler01.setRotationPoint(-1.5F, -0.5F, -3.9F);
        this.rAntler01.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rAntler01, 0.0F, 0.2617993877991494F, -0.2617993877991494F);
        this.rAntler05 = new ModelRenderer(this, 0, 19);
        this.rAntler05.mirror = true;
        this.rAntler05.setRotationPoint(0.0F, -0.1F, -0.3F);
        this.rAntler05.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(rAntler05, -0.8028514559173915F, 0.22689280275926282F, 0.0F);
        this.rEar = new ModelRenderer(this, 21, 0);
        this.rEar.mirror = true;
        this.rEar.setRotationPoint(-2.1F, -1.0F, -3.0F);
        this.rEar.addBox(-1.0F, -0.7F, -3.1F, 2, 1, 3, 0.0F);
        this.setRotateAngle(rEar, 0.2792526803190927F, 1.0821041362364843F, 0.0F);
        this.lAntler07b = new ModelRenderer(this, 0, 19);
        this.lAntler07b.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.lAntler07b.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler07b, 0.8726646259971648F, 0.0F, 0.7853981633974483F);
        this.rAntler02 = new ModelRenderer(this, 0, 13);
        this.rAntler02.mirror = true;
        this.rAntler02.setRotationPoint(0.1F, 0.1F, 0.0F);
        this.rAntler02.addBox(-0.5F, -0.4F, -2.3F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler02, -0.5235987755982988F, 0.0F, 0.0F);
        this.rAntler07 = new ModelRenderer(this, 0, 19);
        this.rAntler07.mirror = true;
        this.rAntler07.setRotationPoint(0.0F, 0.0F, -3.8F);
        this.rAntler07.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler07, 0.0F, -0.4886921905584123F, 0.0F);
        this.lForeleg02 = new ModelRenderer(this, 31, 13);
        this.lForeleg02.setRotationPoint(0.8F, 4.4F, 0.1F);
        this.lForeleg02.addBox(-2.0F, 0.0F, -2.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(lForeleg02, 0.0F, 0.0F, 0.08726646259971647F);
        this.rAntler08b = new ModelRenderer(this, 0, 19);
        this.rAntler08b.mirror = true;
        this.rAntler08b.setRotationPoint(0.0F, 0.0F, -0.7F);
        this.rAntler08b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(rAntler08b, 0.7853981633974483F, -0.40142572795869574F, 0.0F);
        this.rAntler03 = new ModelRenderer(this, 0, 13);
        this.rAntler03.mirror = true;
        this.rAntler03.setRotationPoint(0.0F, 0.5F, -1.4F);
        this.rAntler03.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler03, -0.4886921905584123F, 0.22689280275926282F, 0.0F);
        this.rHindLeg02 = new ModelRenderer(this, 48, 15);
        this.rHindLeg02.mirror = true;
        this.rHindLeg02.setRotationPoint(-1.4F, 5.0F, -1.1F);
        this.rHindLeg02.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(rHindLeg02, 0.9075712110370513F, 0.0F, 0.0F);
        this.rAntler07b = new ModelRenderer(this, 0, 19);
        this.rAntler07b.mirror = true;
        this.rAntler07b.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.rAntler07b.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(rAntler07b, 0.8726646259971648F, 0.0F, -0.7853981633974483F);
        this.lAntler01b = new ModelRenderer(this, 0, 12);
        this.lAntler01b.setRotationPoint(0.0F, 0.0F, -1.2F);
        this.lAntler01b.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler01b, 0.9599310885968813F, -0.6981317007977318F, 0.3141592653589793F);
        this.rForeleg02 = new ModelRenderer(this, 31, 13);
        this.rForeleg02.mirror = true;
        this.rForeleg02.setRotationPoint(-0.8F, 4.4F, 0.1F);
        this.rForeleg02.addBox(-1.0F, 0.0F, -2.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(rForeleg02, 0.0F, 0.0F, -0.08726646259971647F);
        this.lAntler06b = new ModelRenderer(this, 2, 21);
        this.lAntler06b.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.lAntler06b.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
        this.setRotateAngle(lAntler06b, 0.3141592653589793F, 0.45378560551852565F, 0.0F);
        this.lAntler04 = new ModelRenderer(this, 0, 13);
        this.lAntler04.setRotationPoint(0.0F, -0.1F, -2.6F);
        this.lAntler04.addBox(-0.5F, -0.5F, -3.1F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler04, 0.45378560551852565F, 0.22689280275926282F, 0.0F);
        this.lAntler05 = new ModelRenderer(this, 0, 19);
        this.lAntler05.setRotationPoint(0.0F, -0.1F, -0.3F);
        this.lAntler05.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
        this.setRotateAngle(lAntler05, -0.8028514559173915F, -0.22689280275926282F, 0.0F);
        this.lAntler08 = new ModelRenderer(this, 0, 19);
        this.lAntler08.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.lAntler08.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lAntler08, -0.3141592653589793F, 0.6108652381980153F, 0.0F);
        this.lHindLeg02 = new ModelRenderer(this, 48, 15);
        this.lHindLeg02.setRotationPoint(1.4F, 5.0F, -1.1F);
        this.lHindLeg02.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(lHindLeg02, 0.9075712110370513F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 26, 37);
        this.neck.setRotationPoint(0.0F, 0.4F, -3.0F);
        this.neck.addBox(-2.51F, -2.5F, -6.0F, 5, 5, 5, 0.0F);
        this.setRotateAngle(neck, -0.3141592653589793F, 0.0F, 0.0F);
        this.head.addChild(this.throat);
        this.head.addChild(this.snout);
        this.rAntler04.addChild(this.rAntler04b);
        this.lAntler01.addChild(this.lAntler02);
        this.lAntler04.addChild(this.lAntler04b);
        this.lAntler08.addChild(this.lAntler08b);
        this.lHindLeg02.addChild(this.lHindLeg03);
        this.lAntler05.addChild(this.lAntler07);
        this.rAntler07.addChild(this.rAntler08);
        this.ass.addChild(this.tail);
        this.lAntler05.addChild(this.lAntler06);
        this.body.addChild(this.rForeleg01);
        this.lAntler08.addChild(this.lAntler08c);
        this.rForeleg02.addChild(this.rForeleg03);
        this.neck.addChild(this.mane01);
        this.rAntler05.addChild(this.rAntler06);
        this.body.addChild(this.chest);
        this.head.addChild(this.upperJaw);
        this.rHindLeg03.addChild(this.rHindHoof);
        this.throat.addChild(this.lowerJaw);
        this.body.addChild(this.lForeleg01);
        this.rAntler03.addChild(this.rAntler04);
        this.ass.addChild(this.lHindLeg01);
        this.rAntler03.addChild(this.rAntler03b);
        this.rAntler06.addChild(this.rAntler06b);
        this.chest.addChild(this.mane03);
        this.chest.addChild(this.mane04);
        this.head.addChild(this.lAntler01);
        this.lAntler02.addChild(this.lAntler03);
        this.head.addChild(this.lEar);
        this.neck.addChild(this.head);
        this.lForeleg02.addChild(this.lForeleg03);
        this.rHindLeg02.addChild(this.rHindLeg03);
        this.lForeleg03.addChild(this.lForeHoof);
        this.lHindLeg03.addChild(this.lHindHoof);
        this.neck.addChild(this.mane02);
        this.rForeleg03.addChild(this.rForeHoof);
        this.rAntler01.addChild(this.rAntler01b);
        this.body.addChild(this.ass);
        this.rAntler08.addChild(this.rAntler08c);
        this.ass.addChild(this.rHindLeg01);
        this.lAntler03.addChild(this.lAntler03b);
        this.head.addChild(this.rAntler01);
        this.rAntler04.addChild(this.rAntler05);
        this.head.addChild(this.rEar);
        this.lAntler07.addChild(this.lAntler07b);
        this.rAntler01.addChild(this.rAntler02);
        this.rAntler05.addChild(this.rAntler07);
        this.lForeleg01.addChild(this.lForeleg02);
        this.rAntler08.addChild(this.rAntler08b);
        this.rAntler02.addChild(this.rAntler03);
        this.rHindLeg01.addChild(this.rHindLeg02);
        this.rAntler07.addChild(this.rAntler07b);
        this.lAntler01.addChild(this.lAntler01b);
        this.rForeleg01.addChild(this.rForeleg02);
        this.lAntler06.addChild(this.lAntler06b);
        this.lAntler03.addChild(this.lAntler04);
        this.lAntler04.addChild(this.lAntler05);
        this.lAntler07.addChild(this.lAntler08);
        this.lHindLeg01.addChild(this.lHindLeg02);
        this.chest.addChild(this.neck);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
        
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
        float f = limbSwing;
        float f1 = limbSwingAmount/2.2F;

        if ( entity instanceof EntityDeer)
        {
            EntityDeer deer = (EntityDeer) entity;
            float eatTime = deer.getEatTime();
            
            this.chest.rotateAngleY = ModelBetterAnimals.getHeadYaw((EntityLiving) deer) * 0.017453292F * 0.5F;

            if ( eatTime > 0 )
            {
                this.chest.rotateAngleX = 0.278F;
                this.neck.rotateAngleX = 0.729F;
                this.head.rotateAngleX = -1.1F;
                this.lowerJaw.rotateAngleX = (float) Math.toRadians((eatTime % 20F)) + 0.1F;
            }
            else
            {
//            	this.body.rotateAngleX = MathHelper.sin(limbSwingAmount)/8.0F - MathHelper.sin(deer.upTimer/4.0F)/4.0F;
//            	this.chest.rotateAngleX = MathHelper.sin(limbSwingAmount)/4.0F - this.body.rotateAngleX/2.0F - 0.58F;
//            	
            	if ( deer.onGround || deer.isInWater() )
            	{
//            		if ( this.upTimer > 0 )
//            		{
//                		this.upTimer--;
//            		}
            		this.body.rotateAngleX = MathHelper.sin(limbSwingAmount)/8.0F - MathHelper.sin(deer.upTimer/4.0F)/4.0F;
                	this.chest.rotateAngleX = MathHelper.sin(limbSwingAmount)/4.0F - this.body.rotateAngleX/2.0F - 0.58F;
//            		
                    this.lForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 + 0.136659280431156F;
                    this.rForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 + 0.136659280431156F;
                    this.rHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 - 0.22759093446006054F;
                    this.lHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 - 0.22759093446006054F;
            	}
            	else
                {
//            		this.upTimer++;
//            		
//            		if ( this.upTimer >= 30 )
//            		{
//            			this.upTimer = 30;
//            		}
            		
                	this.body.rotateAngleX = MathHelper.sin(limbSwingAmount)/8.0F - MathHelper.sin(deer.upTimer/4.0F)/4.0F;
                	this.chest.rotateAngleX = MathHelper.sin(limbSwingAmount)/4.0F - this.body.rotateAngleX/2.0F - 0.58F;
//                  this.neck.rotateAngleX = this.body.rotateAngleX/6.0F + 0.729F;
                	
                    this.lForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 + 0.136659280431156F + deer.upTimer/100.0F;
                    this.rForeleg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 + 0.136659280431156F + deer.upTimer/100.0F;
                    this.rHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 - 0.22759093446006054F - deer.upTimer/100.0F;
                    this.lHindLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1 - 0.22759093446006054F - deer.upTimer/100.0F;
                }
            	
                this.neck.rotateAngleX = headPitch * 0.017453292F - 0.31869712141416456F;
                this.head.rotateAngleX = -0.31869712141416456F;
                this.lowerJaw.rotateAngleX = 0.0F;
            }
            
            if ( deer.underAttack-- > 0)
            {
                this.tail.rotateAngleX = 3.0F;
            }
            else
            {
                this.tail.rotateAngleX = 0.3F;
            }
            
            this.body.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 0.15F * f1;
        }
        
        this.body.rotateAngleZ = MathHelper.cos(limbSwing * 0.3F) * 0.05F * limbSwingAmount;
        this.chest.rotateAngleZ = MathHelper.cos(limbSwing * 0.6F) * 0.05F * limbSwingAmount;

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
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
