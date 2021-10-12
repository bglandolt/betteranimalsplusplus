package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityFeralWolf;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * wolf_v2 - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelFeralWolf extends ModelBetterAnimals
{
    public ModelRenderer chest;
    public ModelRenderer torso;
    public ModelRenderer neck;
    public ModelRenderer lForeleg01;
    public ModelRenderer rForeleg01;
    public ModelRenderer hackles01;
    public ModelRenderer hackles02;
    public ModelRenderer tail01;
    public ModelRenderer lHindLeg01;
    public ModelRenderer rHindLeg01;
    public ModelRenderer tail02;
    public ModelRenderer tail03;
    public ModelRenderer tail04;
    public ModelRenderer lHindLeg02;
    public ModelRenderer lHindLeg03;
    public ModelRenderer lHindPaw;
    public ModelRenderer rHindLeg02;
    public ModelRenderer rHindLeg03;
    public ModelRenderer rHindPaw;
    public ModelRenderer head;
    public ModelRenderer mane01;
    public ModelRenderer mane02;
    public ModelRenderer mane03;
    public ModelRenderer mane04;
    public ModelRenderer jawUpper01;
    public ModelRenderer jawLower;
    public ModelRenderer lEar01;
    public ModelRenderer rEar01;
    public ModelRenderer lCheekFur;
    public ModelRenderer rCheekFur;
    public ModelRenderer snout;
    public ModelRenderer jawUpper02;
    public ModelRenderer upperTeeth01;
    public ModelRenderer upperTeeth03;
    public ModelRenderer upperTeeth02;
    public ModelRenderer lowerTeeth01;
    public ModelRenderer lowerTeeth02;
    public ModelRenderer lEar02;
    public ModelRenderer rEar02;
    public ModelRenderer lForeleg02;
    public ModelRenderer lForePaw;
    public ModelRenderer rForeleg02;
    public ModelRenderer rForePaw;

    public ModelFeralWolf() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.mane03 = new ModelRenderer(this, 61, 50);
        this.mane03.setRotationPoint(0.0F, 3.1F, -2.0F);
        this.mane03.addBox(-2.5F, 0.0F, -0.5F, 5, 6, 1, 0.0F);
        this.setRotateAngle(mane03, 0.6632251157578453F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 38, 27);
        this.neck.setRotationPoint(0.0F, -1.7F, -3.3F);
        this.neck.addBox(-3.0F, -2.5F, -5.0F, 6, 6, 5, 0.0F);
        this.setRotateAngle(neck, -0.5235987755982988F, 0.0F, 0.0F);
        this.lowerTeeth01 = new ModelRenderer(this, 57, 22);
        this.lowerTeeth01.setRotationPoint(0.0F, -3.7F, 0.1F);
        this.lowerTeeth01.addBox(0.6F, -0.7F, 0.4F, 1, 3, 1, 0.0F);
        this.lHindLeg03 = new ModelRenderer(this, 94, 30);
        this.lHindLeg03.setRotationPoint(0.1F, 0.0F, 5.8F);
        this.lHindLeg03.addBox(-1.0F, -0.3F, -1.5F, 2, 7, 3, 0.0F);
        this.setRotateAngle(lHindLeg03, 0.5235987755982988F, 0.0F, 0.05235987755982988F);
        this.jawUpper02 = new ModelRenderer(this, 51, 12);
        this.jawUpper02.mirror = true;
        this.jawUpper02.setRotationPoint(0.0F, 0.35F, 0.0F);
        this.jawUpper02.addBox(-3.5F, -4.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(jawUpper02, 0.0F, 0.0F, 0.2792526803190927F);
        this.jawLower = new ModelRenderer(this, 39, 20);
        this.jawLower.setRotationPoint(0.0F, -4.6F, -2.51F);
        this.jawLower.addBox(-1.5F, -4.5F, -0.5F, 3, 5, 1, 0.0F);
        this.lHindPaw = new ModelRenderer(this, 66, 27);
        this.lHindPaw.setRotationPoint(0.0F, 6.4F, -0.3F);
        this.lHindPaw.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 4, 0.0F);
        this.setRotateAngle(lHindPaw, 0.12217304763960307F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 3, 0);
        this.chest.setRotationPoint(0.0F, 7.8F, -5.0F);
        this.chest.addBox(-4.0F, -4.0F, -5.0F, 8, 9, 10, 0.0F);
        this.setRotateAngle(chest, -0.06981317007977318F, 0.0F, 0.0F);
        this.tail04 = new ModelRenderer(this, 18, 49);
        this.tail04.setRotationPoint(0.0F, 0.0F, 0.4F);
        this.tail04.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.setRotateAngle(tail04, 0.06981317007977318F, 0.0F, 0.0F);
        this.lHindLeg02 = new ModelRenderer(this, 90, 18);
        this.lHindLeg02.setRotationPoint(1.9F, 5.6F, -1.1F);
        this.lHindLeg02.addBox(-1.5F, -1.5F, 0.0F, 3, 4, 7, 0.0F);
        this.setRotateAngle(lHindLeg02, -0.2617993877991494F, 0.0F, 0.0F);
        this.jawUpper01 = new ModelRenderer(this, 51, 12);
        this.jawUpper01.setRotationPoint(1.2F, -5.2F, -1.1F);
        this.jawUpper01.addBox(-0.9F, -4.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(jawUpper01, 0.0F, 0.0F, -0.13962634015954636F);
        this.lForePaw = new ModelRenderer(this, 66, 27);
        this.lForePaw.setRotationPoint(0.0F, 7.8F, -0.5F);
        this.lForePaw.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 4, 0.0F);
        this.setRotateAngle(lForePaw, 0.03490658503988659F, 0.0F, 0.0F);
        this.mane02 = new ModelRenderer(this, 49, 50);
        this.mane02.setRotationPoint(0.0F, 3.1F, -3.2F);
        this.mane02.addBox(-2.0F, 0.0F, -0.5F, 4, 5, 1, 0.0F);
        this.setRotateAngle(mane02, 0.6283185307179586F, 0.0F, 0.0F);
        this.upperTeeth02 = new ModelRenderer(this, 50, 20);
        this.upperTeeth02.mirror = true;
        this.upperTeeth02.setRotationPoint(-2.4F, -2.8F, -1.0F);
        this.upperTeeth02.addBox(-0.5F, -1.0F, -0.89F, 1, 4, 2, 0.0F);
        this.rHindLeg01 = new ModelRenderer(this, 90, 0);
        this.rHindLeg01.mirror = true;
        this.rHindLeg01.setRotationPoint(-1.3F, -0.9F, 7.8F);
        this.rHindLeg01.addBox(-4.0F, -1.0F, -3.0F, 4, 9, 6, 0.0F);
        this.setRotateAngle(rHindLeg01, -0.3839724354387525F, 0.0F, 0.05235987755982988F);
        this.mane01 = new ModelRenderer(this, 36, 50);
        this.mane01.setRotationPoint(0.0F, 3.1F, -3.9F);
        this.mane01.addBox(-2.0F, 0.0F, -0.5F, 4, 4, 1, 0.0F);
        this.setRotateAngle(mane01, 0.5235987755982988F, 0.0F, 0.0F);
        this.rForeleg01 = new ModelRenderer(this, 69, 0);
        this.rForeleg01.mirror = true;
        this.rForeleg01.setRotationPoint(-3.0F, -0.2F, -1.2F);
        this.rForeleg01.addBox(-3.0F, -1.1F, -3.4F, 4, 8, 5, 0.0F);
        this.setRotateAngle(rForeleg01, 0.13962634015954636F, 0.0F, 0.0F);
        this.upperTeeth01 = new ModelRenderer(this, 50, 20);
        this.upperTeeth01.setRotationPoint(0.0F, -2.8F, -1.0F);
        this.upperTeeth01.addBox(-0.5F, -1.0F, -0.89F, 1, 4, 2, 0.0F);
        this.lEar02 = new ModelRenderer(this, 0, 4);
        this.lEar02.setRotationPoint(0.0F, 1.2F, 0.2F);
        this.lEar02.addBox(-1.0F, -0.7F, 0.2F, 2, 1, 4, 0.0F);
        this.setRotateAngle(lEar02, 0.3141592653589793F, 0.0F, 0.0F);
        this.hackles01 = new ModelRenderer(this, 59, 37);
        this.hackles01.setRotationPoint(0.0F, -3.9F, -4.5F);
        this.hackles01.addBox(-2.5F, -1.0F, 0.0F, 5, 2, 7, 0.0F);
        this.setRotateAngle(hackles01, 0.13962634015954636F, 0.0F, 0.0F);
        this.tail02 = new ModelRenderer(this, 0, 47);
        this.tail02.setRotationPoint(0.0F, 3.5F, 0.0F);
        this.tail02.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.setRotateAngle(tail02, -0.3141592653589793F, 0.0F, 0.0F);
        this.torso = new ModelRenderer(this, 0, 19);
        this.torso.setRotationPoint(0.0F, 0.4F, 4.0F);
        this.torso.addBox(-3.5F, -4.0F, 0.0F, 7, 8, 11, 0.0F);
        this.setRotateAngle(torso, 0.06981317007977318F, 0.0F, 0.0F);
        this.lCheekFur = new ModelRenderer(this, 30, -6);
        this.lCheekFur.mirror = true;
        this.lCheekFur.setRotationPoint(3.5F, -2.5F, -0.6F);
        this.lCheekFur.addBox(0.0F, -0.9F, -3.8F, 0, 5, 6, 0.0F);
        this.setRotateAngle(lCheekFur, -0.3490658503988659F, -0.08726646259971647F, -0.6981317007977318F);
        this.lowerTeeth02 = new ModelRenderer(this, 57, 22);
        this.lowerTeeth02.mirror = true;
        this.lowerTeeth02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowerTeeth02.addBox(-1.6F, -0.7F, 0.3F, 1, 3, 1, 0.0F);
        this.lForeleg01 = new ModelRenderer(this, 69, 0);
        this.lForeleg01.setRotationPoint(3.0F, -0.2F, -1.2F);
        this.lForeleg01.addBox(-1.0F, -1.1F, -3.4F, 4, 8, 5, 0.0F);
        this.setRotateAngle(lForeleg01, 0.13962634015954636F, 0.0F, 0.0F);
        this.hackles02 = new ModelRenderer(this, 94, 42);
        this.hackles02.setRotationPoint(0.0F, -3.2F, -3.0F);
        this.hackles02.addBox(-3.5F, -1.0F, 0.0F, 7, 2, 9, 0.0F);
        this.setRotateAngle(hackles02, 0.10471975511965977F, 0.0F, 0.0F);
        this.lHindLeg01 = new ModelRenderer(this, 90, 0);
        this.lHindLeg01.setRotationPoint(1.3F, -0.9F, 7.8F);
        this.lHindLeg01.addBox(0.0F, -1.0F, -3.0F, 4, 9, 6, 0.0F);
        this.setRotateAngle(lHindLeg01, -0.3839724354387525F, 0.0F, -0.05235987755982988F);
        this.tail03 = new ModelRenderer(this, 13, 39);
        this.tail03.setRotationPoint(0.0F, 6.5F, 0.1F);
        this.tail03.addBox(-1.5F, 0.2F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(tail03, 0.13962634015954636F, 0.0F, 0.0F);
        this.rHindLeg02 = new ModelRenderer(this, 90, 18);
        this.rHindLeg02.mirror = true;
        this.rHindLeg02.setRotationPoint(-1.9F, 5.6F, -1.1F);
        this.rHindLeg02.addBox(-1.5F, -1.5F, 0.0F, 3, 4, 7, 0.0F);
        this.setRotateAngle(rHindLeg02, -0.2617993877991494F, 0.0F, 0.0F);
        this.tail01 = new ModelRenderer(this, 0, 39);
        this.tail01.setRotationPoint(0.0F, -2.9F, 10.0F);
        this.tail01.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3, 0.0F);
        this.setRotateAngle(tail01, 0.6981317007977318F, 0.0F, 0.0F);
        this.rEar02 = new ModelRenderer(this, 0, 4);
        this.rEar02.mirror = true;
        this.rEar02.setRotationPoint(0.0F, 1.2F, 0.2F);
        this.rEar02.addBox(-1.0F, -0.7F, 0.2F, 2, 1, 4, 0.0F);
        this.setRotateAngle(rEar02, 0.3141592653589793F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 40, 0);
        this.head.setRotationPoint(0.0F, -0.5F, -3.6F);
        this.head.addBox(-3.5F, -5.0F, -3.0F, 7, 5, 6, 0.0F);
        this.setRotateAngle(head, 2.1816615649929116F, 0.0F, 0.0F);
        this.mane04 = new ModelRenderer(this, 75, 50);
        this.mane04.setRotationPoint(0.0F, 3.1F, -0.8F);
        this.mane04.addBox(-3.0F, 0.0F, -0.5F, 6, 7, 2, 0.0F);
        this.setRotateAngle(mane04, 0.6981317007977318F, 0.0F, 0.0F);
        this.rCheekFur = new ModelRenderer(this, 30, -6);
        this.rCheekFur.mirror = true;
        this.rCheekFur.setRotationPoint(-3.5F, -2.5F, -0.6F);
        this.rCheekFur.addBox(0.0F, -0.8F, -3.6F, 0, 5, 6, 0.0F);
        this.setRotateAngle(rCheekFur, -0.3490658503988659F, 0.08726646259971647F, 0.6981317007977318F);
        this.snout = new ModelRenderer(this, 40, 12);
        this.snout.setRotationPoint(0.0F, -4.9F, 0.1F);
        this.snout.addBox(-1.5F, -4.45F, -1.0F, 3, 5, 2, 0.0F);
        this.setRotateAngle(snout, 0.18203784098300857F, 0.0F, 0.0F);
        this.rForeleg02 = new ModelRenderer(this, 69, 14);
        this.rForeleg02.mirror = true;
        this.rForeleg02.setRotationPoint(-1.5F, 6.7F, -0.5F);
        this.rForeleg02.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
        this.setRotateAngle(rForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
        this.rHindLeg03 = new ModelRenderer(this, 94, 30);
        this.rHindLeg03.mirror = true;
        this.rHindLeg03.setRotationPoint(-0.1F, 0.0F, 5.8F);
        this.rHindLeg03.addBox(-1.0F, -0.3F, -1.5F, 2, 7, 3, 0.0F);
        this.setRotateAngle(rHindLeg03, 0.5235987755982988F, 0.0F, -0.05235987755982988F);
        this.rForePaw = new ModelRenderer(this, 66, 27);
        this.rForePaw.mirror = true;
        this.rForePaw.setRotationPoint(0.0F, 7.8F, -0.5F);
        this.rForePaw.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 4, 0.0F);
        this.setRotateAngle(rForePaw, 0.03490658503988659F, 0.0F, 0.0F);
        this.rEar01 = new ModelRenderer(this, 0, 0);
        this.rEar01.mirror = true;
        this.rEar01.setRotationPoint(-2.1F, -2.2F, 1.9F);
        this.rEar01.addBox(-1.5F, -0.5F, 0.3F, 3, 1, 3, 0.0F);
        this.setRotateAngle(rEar01, 0.08726646259971647F, -0.22689280275926282F, -0.3665191429188092F);
        this.upperTeeth03 = new ModelRenderer(this, 55, 28);
        this.upperTeeth03.setRotationPoint(0.0F, -3.2F, -1.0F);
        this.upperTeeth03.addBox(-2.27F, -0.7F, -0.4F, 3, 0, 1, 0.0F);
        this.setRotateAngle(upperTeeth03, 0.0F, 0.0F, 0.136659280431156F);
        this.lForeleg02 = new ModelRenderer(this, 69, 14);
        this.lForeleg02.setRotationPoint(1.5F, 6.7F, -0.5F);
        this.lForeleg02.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
        this.setRotateAngle(lForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
        this.rHindPaw = new ModelRenderer(this, 66, 27);
        this.rHindPaw.mirror = true;
        this.rHindPaw.setRotationPoint(0.0F, 6.4F, -0.3F);
        this.rHindPaw.addBox(-1.5F, 0.0F, -2.4F, 3, 2, 4, 0.0F);
        this.setRotateAngle(rHindPaw, 0.12217304763960307F, 0.0F, 0.0F);
        this.lEar01 = new ModelRenderer(this, 0, 0);
        this.lEar01.setRotationPoint(2.1F, -2.2F, 1.9F);
        this.lEar01.addBox(-1.5F, -0.5F, 0.3F, 3, 1, 3, 0.0F);
        this.setRotateAngle(lEar01, 0.08726646259971647F, 0.22689280275926282F, 0.3665191429188092F);
        this.neck.addChild(this.mane03);
        this.chest.addChild(this.neck);
        this.jawLower.addChild(this.lowerTeeth01);
        this.lHindLeg02.addChild(this.lHindLeg03);
        this.jawUpper01.addChild(this.jawUpper02);
        this.head.addChild(this.jawLower);
        this.lHindLeg03.addChild(this.lHindPaw);
        this.tail03.addChild(this.tail04);
        this.lHindLeg01.addChild(this.lHindLeg02);
        this.head.addChild(this.jawUpper01);
        this.lForeleg02.addChild(this.lForePaw);
        this.neck.addChild(this.mane02);
        this.jawUpper02.addChild(this.upperTeeth02);
        this.torso.addChild(this.rHindLeg01);
        this.neck.addChild(this.mane01);
        this.chest.addChild(this.rForeleg01);
        this.jawUpper01.addChild(this.upperTeeth01);
        this.lEar01.addChild(this.lEar02);
        this.chest.addChild(this.hackles01);
        this.tail01.addChild(this.tail02);
        this.chest.addChild(this.torso);
        this.head.addChild(this.lCheekFur);
        this.lowerTeeth01.addChild(this.lowerTeeth02);
        this.chest.addChild(this.lForeleg01);
        this.chest.addChild(this.hackles02);
        this.torso.addChild(this.lHindLeg01);
        this.tail02.addChild(this.tail03);
        this.rHindLeg01.addChild(this.rHindLeg02);
        this.torso.addChild(this.tail01);
        this.rEar01.addChild(this.rEar02);
        this.neck.addChild(this.head);
        this.neck.addChild(this.mane04);
        this.head.addChild(this.rCheekFur);
        this.head.addChild(this.snout);
        this.rForeleg01.addChild(this.rForeleg02);
        this.rHindLeg02.addChild(this.rHindLeg03);
        this.rForeleg02.addChild(this.rForePaw);
        this.head.addChild(this.rEar01);
        this.jawUpper01.addChild(this.upperTeeth03);
        this.lForeleg01.addChild(this.lForeleg02);
        this.rHindLeg03.addChild(this.rHindPaw);
        this.head.addChild(this.lEar01);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chest.render(f5);
    }  

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
    {
        this.head.rotateAngleX = (float) Math.toRadians(ModelBetterAnimals.getHeadPitch((EntityLivingBase) entity)) + 2.1816615649929116F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        EntityFeralWolf wolf = (EntityFeralWolf) entity;
        
        this.tail01.rotateAngleX = ageInTicks + (wolf.isSitting() ? 1.7453292519943295F : 0.6981317007977318F);
        {
            this.tail01.rotateAngleY = MathHelper.cos(ageInTicks * 0.6662F) * 0.16F * limbSwingAmount;
        }
        
        if ( wolf.isSitting() )
        {
            this.setRotateAngle(rHindPaw, 1.3089969389957472F, 0.20943951023931953F, 0.0F);
            this.setRotateAngle(lForePaw, 0.03490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(rForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
            this.setRotateAngle(lForeleg01, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(tail02, 0.5235987755982988F, 0.0F, 0.0F);
            this.setRotateAngle(tail04, 0.06981317007977318F, 0.0F, 0.0F);
            this.setRotateAngle(rHindLeg01, -0.20943951023931953F, 0.0F, 0.5235987755982988F);
            this.setRotateAngle(lHindPaw, 1.3089969389957472F, -0.20943951023931953F, 0.0F);
            this.setRotateAngle(lForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
            this.setRotateAngle(tail03, -0.03490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(torso, -0.17453292519943295F, 0.0F, 0.0F);
            this.setRotateAngle(rForeleg01, 0.8726646259971648F, 0.0F, 0.0F);
            this.setRotateAngle(rForePaw, 0.03490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(neck, 0.22689280275926282F, 0.0F, 0.0F);
            this.setRotateAngle(lHindLeg03, -0.6981317007977318F, 0.0F, 0.05235987755982988F);
            this.setRotateAngle(rHindLeg02, 0.6981317007977318F, 0.0F, 0.0F);
            this.setRotateAngle(lHindLeg02, 0.6981317007977318F, 0.0F, 0.0F);
            this.setRotateAngle(rHindLeg03, -0.6981317007977318F, 0.0F, -0.05235987755982988F);
            this.setRotateAngle(lHindLeg01, -0.20943951023931953F, 0.0F, -0.5235987755982988F);
            this.setRotateAngle(chest, -0.8028514559173915F, 0.0F, 0.0F);
        }
        else
        {
            this.setRotateAngle(neck, -0.5535987755982988F, 0.0F, 0.0F);
            this.setRotateAngle(lHindLeg03, 0.5235987755982988F, 0.0F, 0.05235987755982988F);
            this.setRotateAngle(lHindPaw, 0.12217304763960307F, 0.0F, 0.0F);
            this.setRotateAngle(chest, -0.06981317007977318F, 0.0F, 0.0F); // 06981317007977318F
            this.setRotateAngle(tail04, 0.06981317007977318F, 0.0F, 0.0F);
            this.setRotateAngle(lHindLeg02, -0.2617993877991494F, 0.0F, 0.0F);
            this.setRotateAngle(lForePaw, 0.03490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(rHindLeg01, -0.3839724354387525F, 0.0F, 0.05235987755982988F);
            this.setRotateAngle(rForeleg01, 0.13962634015954636F, 0.0F, 0.0F);
            this.setRotateAngle(tail02, -0.3141592653589793F, 0.0F, 0.0F);
            this.setRotateAngle(lForeleg01, 0.13962634015954636F, 0.0F, 0.0F);
            this.setRotateAngle(lHindLeg01, -0.3839724354387525F, 0.0F, -0.05235987755982988F);
            this.setRotateAngle(tail03, 0.13962634015954636F, 0.0F, 0.0F);
            this.setRotateAngle(rHindLeg02, -0.2617993877991494F, 0.0F, 0.0F);
            this.setRotateAngle(rForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
            this.setRotateAngle(rHindLeg03, 0.5235987755982988F, 0.0F, -0.05235987755982988F);
            this.setRotateAngle(rForePaw, 0.03490658503988659F, 0.0F, 0.0F);
            this.setRotateAngle(lForeleg02, -0.10471975511965977F, 0.0F, 0.0F);
            this.setRotateAngle(rHindPaw, 0.12217304763960307F, 0.0F, 0.0F);
            
            if ( wolf.leaping >= 0 )
            {
                this.jawLower.rotateAngleX = MathHelper.clamp((float)(wolf.motionY*2.0D), 0.0F, 0.4F) + 0.5F; //  - 0.9
                
            	this.chest.rotateAngleX = -MathHelper.clamp((float)(wolf.motionY), -0.3F, 0.3F);
            	this.torso.rotateAngleX = MathHelper.clamp((float)(wolf.motionY), -0.4F, 0.4F) + 0.1F;
                
            	float legs = MathHelper.clamp((float)(wolf.motionY), -0.4F, 0.4F) + 0.3F;
            	
            	this.lHindLeg01.rotateAngleX = legs;
                this.rHindLeg01.rotateAngleX = legs;
                this.lForeleg01.rotateAngleX = legs;
                this.rForeleg01.rotateAngleX = legs;
            	
                wolf.leaping--;
            }
            else
            {
                float swingModifier = 0.85f;

            	this.chest.rotateAngleX = 0.1F;
                this.jawLower.rotateAngleX = 0.0F;
                this.torso.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F + (float) Math.PI) * -0.1F * limbSwingAmount - 0.22759093446006054F;
                
                this.lHindLeg01.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F + (float) Math.PI) * swingModifier * limbSwingAmount - 0.22759093446006054F;
                this.rHindLeg01.rotateAngleX = MathHelper.cos(limbSwing * 0.8665F) * swingModifier * limbSwingAmount  - 0.22759093446006054F;
                this.lForeleg01.rotateAngleX = MathHelper.sin(limbSwing * 0.8665F) * swingModifier * limbSwingAmount + 0.22759093446006054F;
                this.rForeleg01.rotateAngleX = MathHelper.cos(limbSwing * 0.8665F + (float) Math.PI) * swingModifier * limbSwingAmount + 0.22759093446006054F;
            }
            
            this.torso.rotateAngleZ = MathHelper.cos(limbSwing * 0.45F) * 0.16F * limbSwingAmount;
            this.chest.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.15F * limbSwingAmount;
        }

        if ( wolf.isRiding() )
        {            
            // CHEST
        	this.chest.rotateAngleX = MathHelper.cos(wolf.ticksExisted * 0.3F) * 0.3F + 0.12F;
        	this.chest.rotateAngleZ = -this.chest.rotateAngleX * 0.2F;
        	this.torso.rotateAngleX = -this.chest.rotateAngleX;
        	this.chest.offsetZ = 0.98F + MathHelper.cos(wolf.ticksExisted * 0.2F) * 0.04F;

        	// LEGS
            this.lForeleg01.rotateAngleX = 0.4F - this.chest.rotateAngleX;
            this.rForeleg01.rotateAngleX = 0.4F - this.chest.rotateAngleX;
            
        	// HEAD
            this.jawLower.rotateAngleX = MathHelper.cos(wolf.ticksExisted * 0.6F) * 0.4F + 0.35F;
            
            this.neck.rotateAngleX = 1.4F - MathHelper.cos(wolf.ticksExisted * 0.2F) * 0.06F;
            this.head.rotateAngleX = 1.2F;
            this.head.rotateAngleY = MathHelper.cos(wolf.ticksExisted * 0.1F) * 0.02F + 0.2F;
        }
        else
        {
            this.chest.offsetZ = 0.0F;
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
