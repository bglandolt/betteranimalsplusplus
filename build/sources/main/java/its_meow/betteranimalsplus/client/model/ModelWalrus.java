package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityWalrus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * walrus - batman, cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelWalrus extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer body2;
    public ModelRenderer neck;
    public ModelRenderer lFlipper1;
    public ModelRenderer rFlipper1;
    public ModelRenderer body3;
    public ModelRenderer body4;
    public ModelRenderer lLeg1;
    public ModelRenderer rLeg1;
    public ModelRenderer tail;
    public ModelRenderer lLeg2;
    public ModelRenderer rLeg2;
    public ModelRenderer neck2;
    public ModelRenderer head;
    public ModelRenderer lowJaw;
    public ModelRenderer upperJawL;
    public ModelRenderer upperJawR;
    public ModelRenderer snout;
    public ModelRenderer lTusk01;
    public ModelRenderer lWhiskersF;
    public ModelRenderer lWhiskersB;
    public ModelRenderer lTusk02;
    public ModelRenderer rTusk01;
    public ModelRenderer rWhiskersF;
    public ModelRenderer rWhiskersB;
    public ModelRenderer rTusk02;
    public ModelRenderer lFlipper2;
    public ModelRenderer lFlipper3;
    public ModelRenderer rFlipper2;
    public ModelRenderer rFlipper3;

    public ModelWalrus() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.body2 = new ModelRenderer(this, 0, 26);
        this.body2.setRotationPoint(0.0F, -0.4F, -0.7F);
        this.body2.addBox(-5.5F, -2.5F, 0.0F, 11, 12, 10, 0.0F);
        this.setRotateAngle(body2, -0.03490658503988659F, 0.0F, 0.0F);
        this.lFlipper1 = new ModelRenderer(this, 89, 0);
        this.lFlipper1.setRotationPoint(4.0F, 5.0F, -9.0F);
        this.lFlipper1.addBox(-1.5F, 0.0F, -2.0F, 3, 5, 5, 0.0F);
        this.setRotateAngle(lFlipper1, -0.13962634015954636F, 0.0F, -0.9424777960769379F);
        this.rTusk01 = new ModelRenderer(this, 0, 0);
        this.rTusk01.mirror = true;
        this.rTusk01.setRotationPoint(-1.0F, 1.6F, -3.5F);
        this.rTusk01.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rTusk01, 0.03490658503988659F, 0.0F, 0.2792526803190927F);
        this.lLeg2 = new ModelRenderer(this, 110, 12);
        this.lLeg2.setRotationPoint(0.0F, 5.7F, -0.2F);
        this.lLeg2.addBox(-3.0F, 0.0F, -0.5F, 6, 8, 1, 0.0F);
        this.setRotateAngle(lLeg2, 0.7853981633974483F, 0.7853981633974483F, -0.08726646259971647F);
        this.snout = new ModelRenderer(this, 50, 15);
        this.snout.setRotationPoint(0.0F, 0.0F, -5.5F);
        this.snout.addBox(-3.0F, -1.5F, -3.9F, 6, 3, 4, 0.0F);
        this.setRotateAngle(snout, 0.4553564018453205F, 0.0F, 0.0F);
        this.lTusk02 = new ModelRenderer(this, 0, 0);
        this.lTusk02.setRotationPoint(0.0F, 3.9F, 0.0F);
        this.lTusk02.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lTusk02, 0.12217304763960307F, 0.0F, 0.0F);
        this.rLeg2 = new ModelRenderer(this, 110, 12);
        this.rLeg2.mirror = true;
        this.rLeg2.setRotationPoint(0.0F, 5.7F, -0.2F);
        this.rLeg2.addBox(-3.0F, 0.0F, -0.5F, 6, 8, 1, 0.0F);
        this.setRotateAngle(rLeg2, 0.7853981633974483F, -0.7853981633974483F, 0.08726646259971647F);
        this.rTusk02 = new ModelRenderer(this, 0, 0);
        this.rTusk02.mirror = true;
        this.rTusk02.setRotationPoint(0.0F, 3.9F, 0.0F);
        this.rTusk02.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(rTusk02, 0.12217304763960307F, 0.0F, 0.0F);
        this.lTusk01 = new ModelRenderer(this, 0, 0);
        this.lTusk01.setRotationPoint(1.0F, 1.6F, -3.3F);
        this.lTusk01.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(lTusk01, 0.03490658503988659F, 0.0F, -0.2792526803190927F);
        this.rWhiskersF = new ModelRenderer(this, 76, 50);
        this.rWhiskersF.mirror = true;
        this.rWhiskersF.setRotationPoint(0.0F, 0.3F, -4.6F);
        this.rWhiskersF.addBox(-2.1F, 0.0F, -0.5F, 4, 4, 0, 0.0F);
        this.setRotateAngle(rWhiskersF, -0.17453292519943295F, 0.08726646259971647F, 0.17453292519943295F);
        this.lFlipper3 = new ModelRenderer(this, 90, 21);
        this.lFlipper3.setRotationPoint(-0.4F, 3.8F, 0.0F);
        this.lFlipper3.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 5, 0.0F);
        this.setRotateAngle(lFlipper3, 0.6283185307179586F, 0.0F, -0.08726646259971647F);
        this.upperJawL = new ModelRenderer(this, 71, 15);
        this.upperJawL.setRotationPoint(1.5F, 2.8F, -4.8F);
        this.upperJawL.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(upperJawL, 0.0F, 0.03490658503988659F, 0.2792526803190927F);
        this.neck = new ModelRenderer(this, 86, 41);
        this.neck.setRotationPoint(0.0F, 2.3F, -5.1F);
        this.neck.addBox(-5.0F, -1.5F, -11.0F, 10, 10, 11, 0.0F);
        this.setRotateAngle(neck, -0.7155849933176751F, 0.0F, 0.0F);
        this.upperJawR = new ModelRenderer(this, 71, 15);
        this.upperJawR.mirror = true;
        this.upperJawR.setRotationPoint(-1.5F, 2.8F, -4.8F);
        this.upperJawR.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(upperJawR, 0.0F, -0.03490658503988659F, -0.2792526803190927F);
        this.rFlipper2 = new ModelRenderer(this, 90, 11);
        this.rFlipper2.mirror = true;
        this.rFlipper2.setRotationPoint(0.5F, 4.5F, -2.0F);
        this.rFlipper2.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(rFlipper2, 0.5759586531581287F, 0.0F, 0.45378560551852565F);
        this.lWhiskersF = new ModelRenderer(this, 76, 50);
        this.lWhiskersF.setRotationPoint(0.0F, 0.3F, -4.6F);
        this.lWhiskersF.addBox(-2.1F, 0.0F, -0.5F, 4, 4, 0, 0.0F);
        this.setRotateAngle(lWhiskersF, -0.17453292519943295F, -0.08726646259971647F, -0.17453292519943295F);
        this.lFlipper2 = new ModelRenderer(this, 90, 11);
        this.lFlipper2.setRotationPoint(-0.5F, 4.5F, -2.0F);
        this.lFlipper2.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5, 0.0F);
        this.setRotateAngle(lFlipper2, 0.5759586531581287F, 0.0F, -0.45378560551852565F);
        this.head = new ModelRenderer(this, 58, 0);
        this.head.setRotationPoint(0.0F, -0.4F, -5.8F);
        this.head.addBox(-3.5F, -1.5F, -6.0F, 7, 7, 6, 0.0F);
        this.setRotateAngle(head, 0.6981317007977318F, 0.0F, 0.0F);
        this.rWhiskersB = new ModelRenderer(this, 76, 51);
        this.rWhiskersB.mirror = true;
        this.rWhiskersB.setRotationPoint(-2.0F, 0.4F, -3.0F);
        this.rWhiskersB.addBox(0.0F, 0.0F, -1.7F, 0, 5, 4, 0.0F);
        this.setRotateAngle(rWhiskersB, 0.0F, 0.0F, 0.2617993877991494F);
        this.body4 = new ModelRenderer(this, 0, 48);
        this.body4.setRotationPoint(0.0F, 0.1F, 6.3F);
        this.body4.addBox(-4.0F, -2.0F, 0.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(body4, -0.3141592653589793F, 0.0F, 0.0F);
        this.lLeg1 = new ModelRenderer(this, 110, 0);
        this.lLeg1.setRotationPoint(2.5F, 2.4F, 6.2F);
        this.lLeg1.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4, 0.0F);
        this.setRotateAngle(lLeg1, 0.5759586531581287F, 1.7453292519943295F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 14.6F, -2.0F);
        this.body.addBox(-6.0F, -3.5F, -12.0F, 12, 13, 12, 0.0F);
        this.neck2 = new ModelRenderer(this, 42, 48);
        this.neck2.setRotationPoint(0.0F, 0.0F, -10.6F);
        this.neck2.addBox(-4.0F, -1.5F, -7.0F, 8, 9, 7, 0.0F);
        this.setRotateAngle(neck2, 0.17453292519943295F, 0.0F, 0.0F);
        this.rFlipper3 = new ModelRenderer(this, 90, 21);
        this.rFlipper3.mirror = true;
        this.rFlipper3.setRotationPoint(0.4F, 3.8F, 0.0F);
        this.rFlipper3.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 5, 0.0F);
        this.setRotateAngle(rFlipper3, 0.6283185307179586F, 0.0F, 0.08726646259971647F);
        this.rFlipper1 = new ModelRenderer(this, 89, 0);
        this.rFlipper1.mirror = true;
        this.rFlipper1.setRotationPoint(-4.0F, 5.0F, -9.0F);
        this.rFlipper1.addBox(-1.5F, 0.0F, -2.0F, 3, 5, 5, 0.0F);
        this.setRotateAngle(rFlipper1, -0.13962634015954636F, 0.0F, 0.9424777960769379F);
        this.lowJaw = new ModelRenderer(this, 41, 0);
        this.lowJaw.setRotationPoint(0.0F, 4.9F, -5.4F);
        this.lowJaw.addBox(-2.0F, -0.5F, -3.5F, 4, 1, 4, 0.0F);
        this.rLeg1 = new ModelRenderer(this, 110, 0);
        this.rLeg1.mirror = true;
        this.rLeg1.setRotationPoint(-2.5F, 2.4F, 6.2F);
        this.rLeg1.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4, 0.0F);
        this.setRotateAngle(rLeg1, 0.5759586531581287F, -1.7453292519943295F, 0.0F);
        this.tail = new ModelRenderer(this, 79, 37);
        this.tail.setRotationPoint(0.0F, -0.9F, 7.5F);
        this.tail.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail, -0.5235987755982988F, 0.0F, 0.0F);
        this.lWhiskersB = new ModelRenderer(this, 76, 51);
        this.lWhiskersB.setRotationPoint(3.0F, 0.4F, -4.5F);
        this.lWhiskersB.addBox(-1.0F, 0.0F, -0.3F, 0, 5, 4, 0.0F);
        this.setRotateAngle(lWhiskersB, 0.0F, 0.0F, -0.2617993877991494F);
        this.body3 = new ModelRenderer(this, 45, 29);
        this.body3.setRotationPoint(0.0F, -0.4F, 9.4F);
        this.body3.addBox(-4.5F, -2.0F, 0.0F, 9, 11, 7, 0.0F);
        this.setRotateAngle(body3, -0.13962634015954636F, 0.0F, 0.0F);
        this.body.addChild(this.body2);
        this.body.addChild(this.lFlipper1);
        this.upperJawR.addChild(this.rTusk01);
        this.lLeg1.addChild(this.lLeg2);
        this.head.addChild(this.snout);
        this.lTusk01.addChild(this.lTusk02);
        this.rLeg1.addChild(this.rLeg2);
        this.rTusk01.addChild(this.rTusk02);
        this.upperJawL.addChild(this.lTusk01);
        this.upperJawR.addChild(this.rWhiskersF);
        this.lFlipper2.addChild(this.lFlipper3);
        this.head.addChild(this.upperJawL);
        this.body.addChild(this.neck);
        this.head.addChild(this.upperJawR);
        this.rFlipper1.addChild(this.rFlipper2);
        this.upperJawL.addChild(this.lWhiskersF);
        this.lFlipper1.addChild(this.lFlipper2);
        this.neck2.addChild(this.head);
        this.upperJawR.addChild(this.rWhiskersB);
        this.body3.addChild(this.body4);
        this.body4.addChild(this.lLeg1);
        this.neck.addChild(this.neck2);
        this.rFlipper2.addChild(this.rFlipper3);
        this.body.addChild(this.rFlipper1);
        this.head.addChild(this.lowJaw);
        this.body4.addChild(this.rLeg1);
        this.body4.addChild(this.tail);
        this.upperJawL.addChild(this.lWhiskersB);
        this.body2.addChild(this.body3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        this.setRotateAngle(lLeg2, 0.7853981633974483F, 0.7853981633974483F, -0.08726646259971647F);
        this.setRotateAngle(rLeg2, 0.7853981633974483F, -0.7853981633974483F, 0.08726646259971647F);
        this.setRotateAngle(lLeg1, 0.5759586531581287F, 1.7453292519943295F, 0.0F);
        this.setRotateAngle(rLeg1, 0.5759586531581287F, -1.7453292519943295F, 0.0F);
        this.setRotateAngle(rFlipper2, 0.5759586531581287F, 0.0F, 0.45378560551852565F);
        this.setRotateAngle(lFlipper2, 0.5759586531581287F, 0.0F, -0.45378560551852565F);
        this.neck.rotateAngleX = -0.7155849933176751F;
        this.neck.rotationPointY = 2.3F;
        
        EntityWalrus walrus = (EntityWalrus)entity;
        //if(entity instanceof EntityLivingBase) {
            this.head.rotateAngleX = (float) Math.toRadians(ModelBetterAnimals.getHeadPitch(walrus)) + 0.6981317007977318F;
            this.neck2.rotateAngleY = (float) MathHelper.clamp(Math.toRadians(ModelBetterAnimals.getHeadYaw(walrus)), Math.toRadians(-35F), Math.toRadians(35F));
        //}
        if (!entity.isOverWater())
        {

//        	this.body.rotateAngleX = -MathHelper.cos(limbSwing * 0.45F) * 0.20F * limbSwingAmount;
//        	this.body2.rotateAngleX = -MathHelper.cos(limbSwing * 0.4F) * 0.19F * limbSwingAmount;
//        	this.body3.rotateAngleX = -MathHelper.cos(limbSwing * 0.35F) * 0.18F * limbSwingAmount;
//        	this.body4.rotateAngleX = -MathHelper.cos(limbSwing * 0.3F) * 0.17F * limbSwingAmount;
        	//float motion = (float) MathHelper.clamp(limbSwingAmount,0.0D,0.26D);
        	
        	float motion = 0.016F*(MathHelper.abs(walrus.leaping)-9);//-(float)MathHelper.clamp(MathHelper.abs((float)entity.motionY),0.0D,0.26D);

        	this.neck.rotateAngleX = -motion - 0.7155849933176751F + ( ( MathHelper.sin(limbSwing * 1.8F) * limbSwingAmount ) / 2.8F );			// (bigger)
        	//this.neck2.rotateAngleX = motion + 0.17F; 			// (smaller)
        	this.body.rotateAngleX = motion * 1.5F; 			// 12 13 /12
        	this.body2.rotateAngleX = motion - 0.1F; 	// 11 12 10
        	this.body3.rotateAngleX = -motion * 0.9F - 0.2F; 	// 9 11 7
        	this.body4.rotateAngleX = -motion * 1.2F + 0.1F; 	// 8 8 8
        	
        	if ( walrus.leaping > -9 )
        	{
        		walrus.leaping--;
        	}

        	this.lLeg1.rotateAngleX = 0.5759586531581287F * 1.75F;
            this.rLeg1.rotateAngleX = 0.5759586531581287F * 1.75F;
            this.lLeg1.rotateAngleY = MathHelper.sin(limbSwing * 1.5F) * limbSwingAmount * 1.75F + 1.7453292519943295F - 0.6F;
            this.rLeg1.rotateAngleY = MathHelper.cos(limbSwing * 1.5F) * limbSwingAmount * 1.75F - 1.7453292519943295F + 0.6F;
            this.lFlipper2.rotateAngleX = MathHelper.cos(limbSwing * 1.5F) * limbSwingAmount * 1.75F - 0.5759586531581287F / 3F;
            this.rFlipper2.rotateAngleX = MathHelper.sin(limbSwing * 1.5F) * limbSwingAmount * 1.75F - 0.5759586531581287F / 3F;
        }
        else
        {

        	this.body.rotateAngleX = MathHelper.cos(limbSwing * 0.45F) * 0.20F * limbSwingAmount;
        	this.body2.rotateAngleX = MathHelper.cos(limbSwing * 0.4F) * 0.19F * limbSwingAmount;
        	this.body3.rotateAngleX = MathHelper.cos(limbSwing * 0.35F) * 0.18F * limbSwingAmount;
        	this.body4.rotateAngleX = MathHelper.cos(limbSwing * 0.3F) * 0.17F * limbSwingAmount;
        	
            this.head.rotateAngleX -= 0.6981317007977318F;
        	//this.neck2.rotateAngleX = 0.17F;
            this.neck.rotateAngleX = 0.0F;
            this.neck.rotationPointY = -1.15F;
//            this.lFlipper2.rotateAngleZ = 0F;
//            this.rFlipper2.rotateAngleZ = 0F;
            this.lFlipper2.rotateAngleX = (float) Math.cos(limbSwing) * limbSwingAmount * 1.5F - 0.5759586531581287F / 3F;
            this.rFlipper2.rotateAngleX = (float) Math.sin(limbSwing) * limbSwingAmount * 1.5F - 0.5759586531581287F / 3F;
            this.lFlipper2.rotateAngleY = (float) Math.sin(-limbSwing * 0.25F) * limbSwingAmount * 0.5F;
            this.rFlipper2.rotateAngleY = (float) Math.sin(limbSwing * 0.25F) * limbSwingAmount * 0.5F;
            this.lLeg1.rotateAngleX = 0.5759586531581287F * 3F;
            this.rLeg1.rotateAngleX = 0.5759586531581287F * 3F;
            this.lLeg1.rotateAngleY = 0F;
            this.rLeg1.rotateAngleY = 0F;
            this.lLeg2.rotateAngleZ = 0F;
            this.rLeg2.rotateAngleZ = 0F;
            this.rLeg2.rotateAngleY = -(float) Math.PI / 2F;
            this.lLeg2.rotateAngleY = (float) Math.PI / 2F;
            this.rLeg2.rotateAngleX = -(float) Math.sin(-limbSwing * 0.25F) * limbSwingAmount * 1F;
            this.lLeg2.rotateAngleX = -(float) Math.sin(limbSwing * 0.25F) * limbSwingAmount * 1F;
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
