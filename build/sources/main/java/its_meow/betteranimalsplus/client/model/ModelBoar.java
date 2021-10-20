package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityBoar;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * boar - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelBoar extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer neck;
    public ModelRenderer lArm01;
    public ModelRenderer rArm01;
    public ModelRenderer stomach;
    public ModelRenderer mane02;
    public ModelRenderer mane03;
    public ModelRenderer chestFur02;
    public ModelRenderer neck02;
    public ModelRenderer chestFur01;
    public ModelRenderer head;
    public ModelRenderer mane01;
    public ModelRenderer snoot01;
    public ModelRenderer upperJaw;
    public ModelRenderer lowerJaw;
    public ModelRenderer lEar01;
    public ModelRenderer rEar01;
    public ModelRenderer snoot02;
    public ModelRenderer snoot;
    public ModelRenderer lTusk01;
    public ModelRenderer rTusk01;
    public ModelRenderer lTusk02;
    public ModelRenderer lTusk03;
    public ModelRenderer rTusk02;
    public ModelRenderer rTusk03;
    public ModelRenderer lEar02;
    public ModelRenderer rEar02;
    public ModelRenderer lArm02;
    public ModelRenderer lArm03;
    public ModelRenderer lForeHoof;
    public ModelRenderer rArm02;
    public ModelRenderer rArm03;
    public ModelRenderer rForeHoof;
    public ModelRenderer ass;
    public ModelRenderer lLeg01;
    public ModelRenderer rLeg01;
    public ModelRenderer tail01a;
    public ModelRenderer lLeg02;
    public ModelRenderer lLeg03;
    public ModelRenderer lHindHoof;
    public ModelRenderer rLeg02;
    public ModelRenderer rLeg03;
    public ModelRenderer rHindHoof;
    public ModelRenderer tail01b;
    public ModelRenderer tail01c;
    public ModelRenderer tail01d;
    public ModelRenderer tail02;
    public ModelRenderer tail03;
    public ModelRenderer tail04;
    public ModelRenderer tailFur;

    public ModelBoar() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.rEar01 = new ModelRenderer(this, 69, 37);
        this.rEar01.mirror = true;
        this.rEar01.setRotationPoint(-1.2F, -2.7F, -1.7F);
        this.rEar01.addBox(-2.2F, -3.0F, -0.5F, 3, 3, 1, 0.0F);
        this.setRotateAngle(this.rEar01, -0.22689280275926282F, 0.7853981633974483F, -0.7853981633974483F);
        this.rLeg03 = new ModelRenderer(this, 38, 21);
        this.rLeg03.mirror = true;
        this.rLeg03.setRotationPoint(-0.2F, 2.6F, 0.0F);
        this.rLeg03.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(this.rLeg03, -0.17453292519943295F, 0.0F, 0.0F);
        this.lTusk03 = new ModelRenderer(this, 0, 0);
        this.lTusk03.setRotationPoint(-0.1F, -0.8F, 0.0F);
        this.lTusk03.addBox(-0.6F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.lTusk03, 0.0F, 0.0F, -0.4363323129985824F);
        this.rHindHoof = new ModelRenderer(this, 49, 21);
        this.rHindHoof.mirror = true;
        this.rHindHoof.setRotationPoint(-0.1F, 4.1F, -0.3F);
        this.rHindHoof.addBox(-1.0F, 0.0F, -1.8F, 2, 2, 3, 0.0F);
        this.setRotateAngle(this.rHindHoof, 0.06981317007977318F, 0.0F, 0.0F);
        this.ass = new ModelRenderer(this, 0, 18);
        this.ass.setRotationPoint(0.0F, -1.1F, 4.2F);
        this.ass.addBox(-3.5F, -3.5F, 0.0F, 7, 8, 7, 0.0F);
        this.setRotateAngle(this.ass, -0.12217304763960307F, 0.0F, 0.0F);
        this.snoot = new ModelRenderer(this, 112, 16);
        this.snoot.setRotationPoint(0.0F, -0.4F, -4.3F);
        this.snoot.addBox(-1.5F, -1.5F, -0.8F, 3, 3, 1, 0.0F);
        this.setRotateAngle(this.snoot, -0.136659280431156F, 0.0F, 0.0F);
        this.rLeg01 = new ModelRenderer(this, 38, 0);
        this.rLeg01.mirror = true;
        this.rLeg01.setRotationPoint(-1.2F, -1.2F, 4.7F);
        this.rLeg01.addBox(-3.5F, -1.0F, -2.5F, 4, 6, 5, 0.0F);
        this.setRotateAngle(this.rLeg01, -0.03490658503988659F, 0.0F, 0.091106186954104F);
        this.lArm03 = new ModelRenderer(this, 38, 21);
        this.lArm03.setRotationPoint(0.2F, 1.8F, 0.0F);
        this.lArm03.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(this.lArm03, -0.091106186954104F, 0.0F, 0.03490658503988659F);
        this.lEar02 = new ModelRenderer(this, 78, 37);
        this.lEar02.setRotationPoint(0.6F, 0.0F, 0.7F);
        this.lEar02.addBox(-1.0F, -4.0F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(this.lEar02, 0.22689280275926282F, 0.0F, 0.0F);
        this.mane01 = new ModelRenderer(this, 86, 35);
        this.mane01.setRotationPoint(0.0F, -2.7F, -1.8F);
        this.mane01.addBox(-2.0F, -1.2F, 0.0F, 4, 2, 5, 0.0F);
        this.setRotateAngle(this.mane01, 0.6981317007977318F, 0.0F, 0.0F);
        this.rTusk02 = new ModelRenderer(this, 0, 1);
        this.rTusk02.mirror = true;
        this.rTusk02.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.rTusk02.addBox(-0.45F, -0.7F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.rTusk02, 0.0F, 0.0F, 0.2617993877991494F);
        this.chestFur02 = new ModelRenderer(this, 52, 45);
        this.chestFur02.setRotationPoint(0.0F, 3.6F, -3.8F);
        this.chestFur02.addBox(-3.0F, 0.0F, -2.0F, 6, 3, 9, 0.0F);
        this.setRotateAngle(this.chestFur02, -0.08726646259971647F, 0.0F, 0.0F);
        this.tail02 = new ModelRenderer(this, 30, 53);
        this.tail02.setRotationPoint(0.0F, 1.5F, 0.3F);
        this.tail02.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.tail02, -0.6373942428283291F, 0.0F, 0.0F);
        this.tail03 = new ModelRenderer(this, 30, 53);
        this.tail03.setRotationPoint(0.0F, 1.7F, 0.0F);
        this.tail03.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.tail03, -0.20943951023931953F, 0.0F, 0.0F);
        this.lLeg01 = new ModelRenderer(this, 38, 0);
        this.lLeg01.setRotationPoint(1.2F, -1.2F, 4.7F);
        this.lLeg01.addBox(-0.5F, -1.0F, -2.5F, 4, 6, 5, 0.0F);
        this.setRotateAngle(this.lLeg01, -0.03490658503988659F, 0.0F, -0.091106186954104F);
        this.chestFur01 = new ModelRenderer(this, 108, 50);
        this.chestFur01.setRotationPoint(0.0F, 2.6F, -2.1F);
        this.chestFur01.addBox(-2.5F, 0.0F, -1.0F, 5, 4, 2, 0.0F);
        this.setRotateAngle(this.chestFur01, 0.17453292519943295F, 0.0F, 0.0F);
        this.rArm03 = new ModelRenderer(this, 38, 21);
        this.rArm03.mirror = true;
        this.rArm03.setRotationPoint(-0.2F, 1.8F, 0.0F);
        this.rArm03.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(this.rArm03, -0.091106186954104F, 0.0F, -0.03490658503988659F);
        this.lArm01 = new ModelRenderer(this, 65, 0);
        this.lArm01.setRotationPoint(1.8F, -1.0F, -2.7F);
        this.lArm01.addBox(0.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
        this.setRotateAngle(this.lArm01, 0.091106186954104F, 0.0F, -0.136659280431156F);
        this.tail01c = new ModelRenderer(this, 24, 53);
        this.tail01c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail01c.addBox(-0.2F, 0.0F, -0.8F, 1, 2, 1, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 99, 25);
        this.lowerJaw.setRotationPoint(0.0F, 2.2F, -4.1F);
        this.lowerJaw.addBox(-1.5F, -0.5F, -4.7F, 3, 1, 5, 0.0F);
        this.setRotateAngle(this.lowerJaw, -0.08726646259971647F, 0.0F, 0.0F);
        this.neck02 = new ModelRenderer(this, 0, 48);
        this.neck02.setRotationPoint(0.0F, 0.1F, -1.8F);
        this.neck02.addBox(-2.0F, -3.0F, -3.0F, 4, 6, 3, 0.0F);
        this.setRotateAngle(this.neck02, -0.136659280431156F, 0.0F, 0.0F);
        this.rTusk01 = new ModelRenderer(this, 0, 1);
        this.rTusk01.mirror = true;
        this.rTusk01.setRotationPoint(-1.3F, -0.5F, -2.8F);
        this.rTusk01.addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.rTusk01, 0.24434609527920614F, 0.0F, -0.6981317007977318F);
        this.rArm01 = new ModelRenderer(this, 65, 0);
        this.rArm01.mirror = true;
        this.rArm01.setRotationPoint(-1.8F, -1.0F, -2.7F);
        this.rArm01.addBox(-3.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
        this.setRotateAngle(this.rArm01, 0.091106186954104F, 0.0F, 0.136659280431156F);
        this.upperJaw = new ModelRenderer(this, 84, 25);
        this.upperJaw.setRotationPoint(0.0F, 0.7F, -4.4F);
        this.upperJaw.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4, 0.0F);
        this.stomach = new ModelRenderer(this, 27, 30);
        this.stomach.setRotationPoint(0.0F, 0.6F, 3.6F);
        this.stomach.addBox(-4.0F, -4.6F, -3.0F, 8, 9, 8, 0.0F);
        this.setRotateAngle(this.stomach, -0.05235987755982988F, 0.0F, 0.0F);
        this.lEar01 = new ModelRenderer(this, 69, 37);
        this.lEar01.setRotationPoint(1.2F, -2.7F, -1.7F);
        this.lEar01.addBox(-0.8F, -3.0F, -0.5F, 3, 3, 1, 0.0F);
        this.setRotateAngle(this.lEar01, -0.22689280275926282F, -0.7853981633974483F, 0.7853981633974483F);
        this.snoot02 = new ModelRenderer(this, 101, 16);
        this.snoot02.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.snoot02.addBox(0.7F, -1.5F, -4.7F, 1, 2, 5, 0.0F);
        this.tail04 = new ModelRenderer(this, 30, 53);
        this.tail04.setRotationPoint(0.0F, 0.9F, 0.0F);
        this.tail04.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.tail04, 0.19198621771937624F, 0.0F, 0.0F);
        this.tail01d = new ModelRenderer(this, 24, 53);
        this.tail01d.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail01d.addBox(-0.8F, 0.0F, -0.8F, 1, 2, 1, 0.0F);
        this.lLeg02 = new ModelRenderer(this, 41, 13);
        this.lLeg02.setRotationPoint(1.7F, 3.9F, -0.1F);
        this.lLeg02.addBox(-1.5F, 0.0F, -2.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(this.lLeg02, 0.31869712141416456F, 0.0F, 0.091106186954104F);
        this.tailFur = new ModelRenderer(this, 36, 53);
        this.tailFur.setRotationPoint(0.0F, 0.6F, -0.1F);
        this.tailFur.addBox(-1.1F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.lLeg03 = new ModelRenderer(this, 38, 21);
        this.lLeg03.setRotationPoint(0.2F, 2.6F, 0.0F);
        this.lLeg03.addBox(-1.0F, 0.0F, -1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(this.lLeg03, -0.17453292519943295F, 0.0F, 0.0F);
        this.rEar02 = new ModelRenderer(this, 78, 37);
        this.rEar02.mirror = true;
        this.rEar02.setRotationPoint(-0.6F, 0.0F, 0.7F);
        this.rEar02.addBox(-1.0F, -4.0F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(this.rEar02, 0.22689280275926282F, 0.0F, 0.0F);
        this.tail01b = new ModelRenderer(this, 24, 53);
        this.tail01b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail01b.addBox(-0.8F, 0.0F, -0.2F, 1, 2, 1, 0.0F);
        this.lForeHoof = new ModelRenderer(this, 49, 21);
        this.lForeHoof.setRotationPoint(0.1F, 4.6F, -0.3F);
        this.lForeHoof.addBox(-1.0F, 0.0F, -1.8F, 2, 2, 3, 0.0F);
        this.lTusk01 = new ModelRenderer(this, 0, 1);
        this.lTusk01.setRotationPoint(1.3F, -0.5F, -2.8F);
        this.lTusk01.addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.lTusk01, 0.24434609527920614F, 0.0F, 0.6981317007977318F);
        this.rTusk03 = new ModelRenderer(this, 0, 0);
        this.rTusk03.mirror = true;
        this.rTusk03.setRotationPoint(0.1F, -0.8F, 0.0F);
        this.rTusk03.addBox(-0.4F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.rTusk03, 0.0F, 0.0F, 0.4363323129985824F);
        this.head = new ModelRenderer(this, 84, 0);
        this.head.setRotationPoint(0.0F, -0.3F, -1.2F);
        this.head.addBox(-2.5F, -3.5F, -5.0F, 5, 6, 5, 0.0F);
        this.setRotateAngle(this.head, 0.33161255787892263F, 0.0F, 0.0F);
        this.rForeHoof = new ModelRenderer(this, 49, 21);
        this.rForeHoof.mirror = true;
        this.rForeHoof.setRotationPoint(-0.1F, 4.6F, -0.3F);
        this.rForeHoof.addBox(-1.0F, 0.0F, -1.8F, 2, 2, 3, 0.0F);
        this.rArm02 = new ModelRenderer(this, 65, 13);
        this.rArm02.mirror = true;
        this.rArm02.setRotationPoint(-1.2F, 4.6F, 0.0F);
        this.rArm02.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(this.rArm02, 0.0F, 0.0F, -0.08726646259971647F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 12.5F, -3.7F);
        this.body.addBox(-3.5F, -4.3F, -6.0F, 7, 9, 7, 0.0F);
        this.rLeg02 = new ModelRenderer(this, 41, 13);
        this.rLeg02.mirror = true;
        this.rLeg02.setRotationPoint(-1.7F, 3.9F, -0.1F);
        this.rLeg02.addBox(-1.5F, 0.0F, -2.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(this.rLeg02, 0.31869712141416456F, 0.0F, -0.091106186954104F);
        this.snoot01 = new ModelRenderer(this, 84, 16);
        this.snoot01.setRotationPoint(0.0F, -0.6F, -4.4F);
        this.snoot01.addBox(-1.7F, -1.5F, -4.7F, 3, 2, 5, 0.0F);
        this.setRotateAngle(this.snoot01, 0.27314402793711257F, 0.0F, 0.0F);
        this.tail01a = new ModelRenderer(this, 24, 53);
        this.tail01a.setRotationPoint(0.0F, -2.7F, 6.5F);
        this.tail01a.addBox(-0.2F, 0.0F, -0.2F, 1, 2, 1, 0.0F);
        this.setRotateAngle(this.tail01a, 0.8651597102135892F, 0.0F, 0.0F);
        this.mane02 = new ModelRenderer(this, 102, 38);
        this.mane02.setRotationPoint(0.0F, -3.4F, -5.2F);
        this.mane02.addBox(-2.5F, -1.1F, -0.5F, 5, 2, 6, 0.0F);
        this.setRotateAngle(this.mane02, 0.3839724354387525F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 34);
        this.neck.setRotationPoint(0.0F, -0.1F, -5.0F);
        this.neck.addBox(-3.0F, -3.5F, -2.7F, 6, 7, 4, 0.0F);
        this.setRotateAngle(this.neck, -0.091106186954104F, 0.0F, 0.0F);
        this.lTusk02 = new ModelRenderer(this, 0, 1);
        this.lTusk02.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.lTusk02.addBox(-0.55F, -0.7F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(this.lTusk02, 0.0F, 0.0F, -0.2617993877991494F);
        this.lArm02 = new ModelRenderer(this, 65, 13);
        this.lArm02.setRotationPoint(1.2F, 4.6F, 0.0F);
        this.lArm02.addBox(-1.5F, 0.0F, -2.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(this.lArm02, 0.0F, 0.0F, 0.08726646259971647F);
        this.lHindHoof = new ModelRenderer(this, 49, 21);
        this.lHindHoof.setRotationPoint(0.1F, 4.1F, -0.3F);
        this.lHindHoof.addBox(-1.0F, 0.0F, -1.8F, 2, 2, 3, 0.0F);
        this.setRotateAngle(this.lHindHoof, 0.06981317007977318F, 0.0F, 0.0F);
        this.mane03 = new ModelRenderer(this, 79, 45);
        this.mane03.setRotationPoint(0.0F, -3.4F, -3.1F);
        this.mane03.addBox(-3.0F, -0.9F, -0.5F, 6, 2, 7, 0.0F);
        this.setRotateAngle(this.mane03, 0.2792526803190927F, 0.0F, 0.0F);
        this.head.addChild(this.rEar01);
        this.rLeg02.addChild(this.rLeg03);
        this.lTusk02.addChild(this.lTusk03);
        this.rLeg03.addChild(this.rHindHoof);
        this.stomach.addChild(this.ass);
        this.snoot01.addChild(this.snoot);
        this.ass.addChild(this.rLeg01);
        this.lArm02.addChild(this.lArm03);
        this.lEar01.addChild(this.lEar02);
        this.neck02.addChild(this.mane01);
        this.rTusk01.addChild(this.rTusk02);
        this.body.addChild(this.chestFur02);
        this.tail01a.addChild(this.tail02);
        this.tail02.addChild(this.tail03);
        this.ass.addChild(this.lLeg01);
        this.neck.addChild(this.chestFur01);
        this.rArm02.addChild(this.rArm03);
        this.body.addChild(this.lArm01);
        this.tail01a.addChild(this.tail01c);
        this.head.addChild(this.lowerJaw);
        this.neck.addChild(this.neck02);
        this.lowerJaw.addChild(this.rTusk01);
        this.body.addChild(this.rArm01);
        this.head.addChild(this.upperJaw);
        this.body.addChild(this.stomach);
        this.head.addChild(this.lEar01);
        this.snoot01.addChild(this.snoot02);
        this.tail03.addChild(this.tail04);
        this.tail01a.addChild(this.tail01d);
        this.lLeg01.addChild(this.lLeg02);
        this.tail04.addChild(this.tailFur);
        this.lLeg02.addChild(this.lLeg03);
        this.rEar01.addChild(this.rEar02);
        this.tail01a.addChild(this.tail01b);
        this.lArm03.addChild(this.lForeHoof);
        this.lowerJaw.addChild(this.lTusk01);
        this.rTusk02.addChild(this.rTusk03);
        this.neck02.addChild(this.head);
        this.rArm03.addChild(this.rForeHoof);
        this.rArm01.addChild(this.rArm02);
        this.rLeg01.addChild(this.rLeg02);
        this.head.addChild(this.snoot01);
        this.ass.addChild(this.tail01a);
        this.body.addChild(this.mane02);
        this.body.addChild(this.neck);
        this.lTusk01.addChild(this.lTusk02);
        this.lArm01.addChild(this.lArm02);
        this.lLeg03.addChild(this.lHindHoof);
        this.body.addChild(this.mane03);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }
    
    private int ii = -1;

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f = limbSwing;
        float f1 = limbSwingAmount/2.2F;

        this.lArm01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.rArm01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.rLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.lLeg01.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;

        if (entityIn instanceof EntityBoar)
        {
        	EntityBoar boar = (EntityBoar)entityIn;
        	
        	if ( boar.headRam == 20 )
        	{
        		this.ii = (boar.world.rand.nextBoolean()?1:-1);
        	}
        	
        	if ( boar.headRam >= 0 ) // 20
            {
            	if ( boar.headRam > 7 )
            	{
            		this.head.rotateAngleX = (-(float)(20-boar.headRam) * 0.04F + 1.165F) * 0.6F + this.headDownAmount;
                    this.neck.rotateAngleX = (-(float)(20-boar.headRam) * 0.04F + 0.7F) * 0.6F + ModelBetterAnimals.getHeadPitch((EntityLiving) entityIn) * 0.017453292F;
	                	this.head.rotateAngleZ = ((float)(8-boar.headRam) * 0.012F + 0.144F)*1.2F*this.ii;
            	}
            	else
            	{
            		this.head.rotateAngleX = (-(float)(boar.headRam-7) * 0.0266F - 0.16F + 0.525F) * 0.6F + this.headDownAmount;
                    this.neck.rotateAngleX = (-(float)(boar.headRam-7) * 0.0166F - 0.1F) * 0.75F + ModelBetterAnimals.getHeadPitch((EntityLiving) entityIn) * 0.017453292F;
                		this.head.rotateAngleZ = ((float)(boar.headRam-7) * 0.020571F + 0.144F)*1.2F;
            	}
            	boar.headRam--;
            }
        	else
        	{
	            this.neck.rotateAngleX = ModelBetterAnimals.getHeadPitch((EntityLiving) entityIn) * 0.017453292F; // head up & down
	            this.neck.rotateAngleY = ModelBetterAnimals.getHeadYaw((EntityLiving) entityIn) * 0.017453292F;
        	}
        }
        
        this.ass.rotateAngleZ = MathHelper.cos(f * 0.5F) * 0.16F * f1;
        this.body.rotateAngleZ = MathHelper.cos(f * 0.6F) * 0.14F * f1;
        
        this.head.rotateAngleX = MathHelper.cos(f * 0.7F) * 0.12F * f1 + this.headDownAmount;

        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }
    
    private float headDownAmount = 0.5F;

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
