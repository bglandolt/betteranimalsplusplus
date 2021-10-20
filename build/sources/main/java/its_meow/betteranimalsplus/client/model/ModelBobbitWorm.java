package its_meow.betteranimalsplus.client.model;

import its_meow.betteranimalsplus.common.entity.EntityBobbitWorm;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * bobbit_worm - cybercat5555
 * Created using Tabula 7.0.1
 */
public class ModelBobbitWorm extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer lFin00a;
    public ModelRenderer rFin00a;
    public ModelRenderer lFin00b;
    public ModelRenderer rFin00b;
    public ModelRenderer head;
    public ModelRenderer tail00;
    public ModelRenderer mAntenna;
    public ModelRenderer lAntenna00;
    public ModelRenderer rAntenna00;
    public ModelRenderer lAntenna01;
    public ModelRenderer rAntenna01;
    public ModelRenderer lMandible00;
    public ModelRenderer rMandible00;
    public ModelRenderer lMandible01;
    public ModelRenderer lMandible02;
    public ModelRenderer rMandible01;
    public ModelRenderer rMandible02;
    public ModelRenderer lFin01a;
    public ModelRenderer rFin01a;
    public ModelRenderer lFin01b;
    public ModelRenderer rFin01b;
    public ModelRenderer lFin01c;
    public ModelRenderer rFin01c;
    public ModelRenderer tail01;
    public ModelRenderer lFin02a;
    public ModelRenderer rFin02a;
    public ModelRenderer lFin02b;
    public ModelRenderer rFin02b;
    public ModelRenderer lFin02c;
    public ModelRenderer rFin02c;
    public ModelRenderer tail02;
    public ModelRenderer lFin03a;
    public ModelRenderer rFin03a;
    public ModelRenderer lFin03b;
    public ModelRenderer rFin03b;
    public ModelRenderer lFin03c;
    public ModelRenderer rFin03c;
    public ModelRenderer tail03;
    public ModelRenderer lFin04a;
    public ModelRenderer rFin04a;
    public ModelRenderer lFin04b;
    public ModelRenderer rFin04b;
    public ModelRenderer lFin04c;
    public ModelRenderer rFin04c;
    public ModelRenderer tail04;
    public ModelRenderer lFin05a;
    public ModelRenderer rFin05a;
    public ModelRenderer lFin05b;
    public ModelRenderer rFin05b;
    public ModelRenderer lFin05c;
    public ModelRenderer rFin05c;
    public ModelRenderer tail05;
    public ModelRenderer lFin06a;
    public ModelRenderer rFin06a;
    public ModelRenderer lFin06b;
    public ModelRenderer rFin06b;
    public ModelRenderer lFin06c;
    public ModelRenderer rFin06c;

    public ModelBobbitWorm() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rFin00a = new ModelRenderer(this, 28, 0);
        this.rFin00a.mirror = true;
        this.rFin00a.setRotationPoint(-2.8F, 1.2F, -0.1F);
        this.rFin00a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rMandible00 = new ModelRenderer(this, 31, 7);
        this.rMandible00.mirror = true;
        this.rMandible00.setRotationPoint(-0.9F, 0.5F, -5.3F);
        this.rMandible00.addBox(-7.0F, -1.4F, -2.5F, 7, 2, 3, 0.0F);
        this.setRotateAngle(rMandible00, 0.0F, -0.3839724354387525F, 0.0F);
        this.body = new ModelRenderer(this, 0, 13);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-3.0F, -2.0F, -4.0F, 6, 4, 8, 0.0F);
        this.rFin03a = new ModelRenderer(this, 28, 0);
        this.rFin03a.mirror = true;
        this.rFin03a.setRotationPoint(-2.8F, 0.0F, 1.2F);
        this.rFin03a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin06a = new ModelRenderer(this, 28, 3);
        this.rFin06a.mirror = true;
        this.rFin06a.setRotationPoint(-1.8F, 0.0F, 1.2F);
        this.rFin06a.addBox(-4.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(rFin06a, 0.0F, 0.13962634015954636F, 0.0F);
        this.rFin04c = new ModelRenderer(this, 28, 0);
        this.rFin04c.mirror = true;
        this.rFin04c.setRotationPoint(-2.8F, 0.0F, 6.6F);
        this.rFin04c.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin05c = new ModelRenderer(this, 28, 0);
        this.lFin05c.setRotationPoint(1.1F, 0.0F, 6.2F);
        this.lFin05c.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.setRotateAngle(lFin05c, 0.0F, -0.06981317007977318F, 0.0F);
        this.rFin01c = new ModelRenderer(this, 28, 0);
        this.rFin01c.mirror = true;
        this.rFin01c.setRotationPoint(-2.8F, 0.0F, 6.6F);
        this.rFin01c.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin04b = new ModelRenderer(this, 28, 0);
        this.rFin04b.mirror = true;
        this.rFin04b.setRotationPoint(-2.8F, 0.0F, 4.0F);
        this.rFin04b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin04b = new ModelRenderer(this, 28, 0);
        this.lFin04b.setRotationPoint(2.8F, 0.0F, 4.0F);
        this.lFin04b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lAntenna00 = new ModelRenderer(this, 37, 0);
        this.lAntenna00.setRotationPoint(1.0F, -1.3F, -6.2F);
        this.lAntenna00.addBox(-0.5F, 0.0F, -6.0F, 1, 0, 6, 0.0F);
        this.setRotateAngle(lAntenna00, -0.2792526803190927F, -0.3839724354387525F, 0.0F);
        this.lFin06c = new ModelRenderer(this, 28, 3);
        this.lFin06c.setRotationPoint(0.6F, 0.0F, 6.2F);
        this.lFin06c.addBox(0.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(lFin06c, 0.0F, -0.3490658503988659F, 0.0F);
        this.rFin01a = new ModelRenderer(this, 28, 0);
        this.rFin01a.mirror = true;
        this.rFin01a.setRotationPoint(-2.8F, 0.0F, 1.2F);
        this.rFin01a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rMandible01 = new ModelRenderer(this, 31, 13);
        this.rMandible01.mirror = true;
        this.rMandible01.setRotationPoint(-6.4F, -0.8F, -0.2F);
        this.rMandible01.addBox(-5.0F, -0.5F, -1.3F, 5, 1, 2, 0.0F);
        this.setRotateAngle(rMandible01, 0.0F, -0.5235987755982988F, 0.17453292519943295F);
        this.lFin00b = new ModelRenderer(this, 28, 0);
        this.lFin00b.setRotationPoint(2.8F, 0.6F, 2.5F);
        this.lFin00b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin04a = new ModelRenderer(this, 28, 0);
        this.rFin04a.mirror = true;
        this.rFin04a.setRotationPoint(-2.8F, 0.0F, 1.2F);
        this.rFin04a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin03b = new ModelRenderer(this, 28, 0);
        this.lFin03b.setRotationPoint(2.8F, 0.0F, 4.0F);
        this.lFin03b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin02a = new ModelRenderer(this, 28, 0);
        this.lFin02a.setRotationPoint(2.8F, 0.0F, 1.2F);
        this.lFin02a.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin05a = new ModelRenderer(this, 28, 0);
        this.lFin05a.setRotationPoint(2.3F, 0.0F, 1.2F);
        this.lFin05a.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin01a = new ModelRenderer(this, 28, 0);
        this.lFin01a.setRotationPoint(2.8F, 0.0F, 1.2F);
        this.lFin01a.addBox(0.0F, 0.0F, -1.1F, 5, 0, 2, 0.0F);
        this.rAntenna01 = new ModelRenderer(this, 37, 0);
        this.rAntenna01.mirror = true;
        this.rAntenna01.setRotationPoint(-0.3F, -0.9F, -6.2F);
        this.rAntenna01.addBox(-0.5F, 0.0F, -6.0F, 1, 0, 6, 0.0F);
        this.setRotateAngle(rAntenna01, -0.17453292519943295F, 0.17453292519943295F, 0.0F);
        this.lFin05b = new ModelRenderer(this, 28, 0);
        this.lFin05b.setRotationPoint(1.8F, 0.0F, 3.8F);
        this.lFin05b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rAntenna00 = new ModelRenderer(this, 37, 0);
        this.rAntenna00.mirror = true;
        this.rAntenna00.setRotationPoint(-1.0F, -1.3F, -6.2F);
        this.rAntenna00.addBox(-0.5F, 0.0F, -6.0F, 1, 0, 6, 0.0F);
        this.setRotateAngle(rAntenna00, -0.2792526803190927F, 0.3839724354387525F, 0.0F);
        this.rFin01b = new ModelRenderer(this, 28, 0);
        this.rFin01b.mirror = true;
        this.rFin01b.setRotationPoint(-2.8F, 0.0F, 4.0F);
        this.rFin01b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.tail05 = new ModelRenderer(this, 39, 13);
        this.tail05.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail05.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
        this.lFin02b = new ModelRenderer(this, 28, 0);
        this.lFin02b.setRotationPoint(2.8F, 0.0F, 4.0F);
        this.lFin02b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lMandible02 = new ModelRenderer(this, 31, 13);
        this.lMandible02.setRotationPoint(6.4F, -0.1F, -0.2F);
        this.lMandible02.addBox(0.0F, -0.5F, -1.3F, 5, 1, 2, 0.0F);
        this.setRotateAngle(lMandible02, 0.0F, 0.5235987755982988F, 0.0F);
        this.rFin00b = new ModelRenderer(this, 28, 0);
        this.rFin00b.mirror = true;
        this.rFin00b.setRotationPoint(-2.8F, 0.6F, 2.5F);
        this.rFin00b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin06b = new ModelRenderer(this, 28, 3);
        this.lFin06b.setRotationPoint(1.3F, 0.0F, 3.8F);
        this.lFin06b.addBox(0.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(lFin06b, 0.0F, -0.2792526803190927F, 0.0F);
        this.tail03 = new ModelRenderer(this, 0, 13);
        this.tail03.mirror = true;
        this.tail03.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail03.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 8, 0.0F);
        this.lFin00a = new ModelRenderer(this, 28, 0);
        this.lFin00a.setRotationPoint(2.8F, 1.2F, -0.1F);
        this.lFin00a.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin04c = new ModelRenderer(this, 28, 0);
        this.lFin04c.setRotationPoint(2.8F, 0.0F, 6.6F);
        this.lFin04c.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin05a = new ModelRenderer(this, 28, 0);
        this.rFin05a.mirror = true;
        this.rFin05a.setRotationPoint(-2.3F, 0.0F, 1.2F);
        this.rFin05a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin02c = new ModelRenderer(this, 28, 0);
        this.rFin02c.mirror = true;
        this.rFin02c.setRotationPoint(-2.8F, 0.0F, 6.6F);
        this.rFin02c.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lMandible00 = new ModelRenderer(this, 31, 7);
        this.lMandible00.setRotationPoint(0.9F, 0.5F, -5.3F);
        this.lMandible00.addBox(0.0F, -1.4F, -2.5F, 7, 2, 3, 0.0F);
        this.setRotateAngle(lMandible00, 0.0F, 0.3839724354387525F, 0.0F);
        this.rFin03b = new ModelRenderer(this, 28, 0);
        this.rFin03b.mirror = true;
        this.rFin03b.setRotationPoint(-2.8F, 0.0F, 4.0F);
        this.rFin03b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin02c = new ModelRenderer(this, 28, 0);
        this.lFin02c.setRotationPoint(2.8F, 0.0F, 6.6F);
        this.lFin02c.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin03c = new ModelRenderer(this, 28, 0);
        this.lFin03c.setRotationPoint(2.8F, 0.0F, 6.6F);
        this.lFin03c.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin03a = new ModelRenderer(this, 28, 0);
        this.lFin03a.setRotationPoint(2.8F, 0.0F, 1.2F);
        this.lFin03a.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.mAntenna = new ModelRenderer(this, 37, 0);
        this.mAntenna.setRotationPoint(0.0F, -1.7F, -6.2F);
        this.mAntenna.addBox(-0.5F, 0.0F, -6.0F, 1, 0, 6, 0.0F);
        this.setRotateAngle(mAntenna, -0.3141592653589793F, 0.0F, 0.0F);
        this.rFin06c = new ModelRenderer(this, 28, 3);
        this.rFin06c.mirror = true;
        this.rFin06c.setRotationPoint(-0.6F, 0.0F, 6.2F);
        this.rFin06c.addBox(-4.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(rFin06c, 0.0F, 0.3490658503988659F, 0.0F);
        this.rMandible02 = new ModelRenderer(this, 31, 13);
        this.rMandible02.mirror = true;
        this.rMandible02.setRotationPoint(-6.4F, -0.1F, -0.2F);
        this.rMandible02.addBox(-5.0F, -0.5F, -1.3F, 5, 1, 2, 0.0F);
        this.setRotateAngle(rMandible02, 0.0F, -0.5235987755982988F, 0.0F);
        this.rFin05b = new ModelRenderer(this, 28, 0);
        this.rFin05b.mirror = true;
        this.rFin05b.setRotationPoint(-1.8F, 0.0F, 3.8F);
        this.rFin05b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin01c = new ModelRenderer(this, 28, 0);
        this.lFin01c.setRotationPoint(2.8F, 0.0F, 6.6F);
        this.lFin01c.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.tail02 = new ModelRenderer(this, 0, 13);
        this.tail02.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail02.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 8, 0.0F);
        this.rFin02a = new ModelRenderer(this, 28, 0);
        this.rFin02a.mirror = true;
        this.rFin02a.setRotationPoint(-2.8F, 0.0F, 1.2F);
        this.rFin02a.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lMandible01 = new ModelRenderer(this, 31, 13);
        this.lMandible01.setRotationPoint(6.4F, -0.8F, -0.2F);
        this.lMandible01.addBox(0.0F, -0.5F, -1.3F, 5, 1, 2, 0.0F);
        this.setRotateAngle(lMandible01, 0.0F, 0.5235987755982988F, -0.17453292519943295F);
        this.lFin06a = new ModelRenderer(this, 28, 3);
        this.lFin06a.setRotationPoint(1.8F, 0.0F, 1.2F);
        this.lFin06a.addBox(0.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(lFin06a, 0.0F, -0.13962634015954636F, 0.0F);
        this.rFin05c = new ModelRenderer(this, 28, 0);
        this.rFin05c.mirror = true;
        this.rFin05c.setRotationPoint(-1.1F, 0.0F, 6.2F);
        this.rFin05c.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.setRotateAngle(rFin05c, 0.0F, 0.06981317007977318F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.head.addBox(-3.5F, -2.0F, -7.0F, 7, 4, 7, 0.0F);
        this.lFin04a = new ModelRenderer(this, 28, 0);
        this.lFin04a.setRotationPoint(2.8F, 0.0F, 1.2F);
        this.lFin04a.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.tail04 = new ModelRenderer(this, 20, 19);
        this.tail04.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail04.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 8, 0.0F);
        this.tail01 = new ModelRenderer(this, 0, 13);
        this.tail01.mirror = true;
        this.tail01.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail01.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 8, 0.0F);
        this.rFin03c = new ModelRenderer(this, 28, 0);
        this.rFin03c.mirror = true;
        this.rFin03c.setRotationPoint(-2.8F, 0.0F, 6.6F);
        this.rFin03c.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.lFin01b = new ModelRenderer(this, 28, 0);
        this.lFin01b.setRotationPoint(2.8F, 0.0F, 4.0F);
        this.lFin01b.addBox(0.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.rFin06b = new ModelRenderer(this, 28, 3);
        this.rFin06b.mirror = true;
        this.rFin06b.setRotationPoint(-1.3F, 0.0F, 3.8F);
        this.rFin06b.addBox(-4.0F, 0.0F, -1.0F, 4, 0, 2, 0.0F);
        this.setRotateAngle(rFin06b, 0.0F, 0.2792526803190927F, 0.0F);
        this.lAntenna01 = new ModelRenderer(this, 37, 0);
        this.lAntenna01.setRotationPoint(0.3F, -0.9F, -6.2F);
        this.lAntenna01.addBox(-0.5F, 0.0F, -6.0F, 1, 0, 6, 0.0F);
        this.setRotateAngle(lAntenna01, -0.17453292519943295F, -0.17453292519943295F, 0.0F);
        this.tail00 = new ModelRenderer(this, 0, 13);
        this.tail00.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.tail00.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 8, 0.0F);
        this.rFin02b = new ModelRenderer(this, 28, 0);
        this.rFin02b.mirror = true;
        this.rFin02b.setRotationPoint(-2.8F, 0.0F, 4.0F);
        this.rFin02b.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 2, 0.0F);
        this.body.addChild(this.rFin00a);
        this.head.addChild(this.rMandible00);
        this.tail02.addChild(this.rFin03a);
        this.tail05.addChild(this.rFin06a);
        this.tail03.addChild(this.rFin04c);
        this.tail04.addChild(this.lFin05c);
        this.tail00.addChild(this.rFin01c);
        this.tail03.addChild(this.rFin04b);
        this.tail03.addChild(this.lFin04b);
        this.head.addChild(this.lAntenna00);
        this.tail05.addChild(this.lFin06c);
        this.tail00.addChild(this.rFin01a);
        this.rMandible00.addChild(this.rMandible01);
        this.body.addChild(this.lFin00b);
        this.tail03.addChild(this.rFin04a);
        this.tail02.addChild(this.lFin03b);
        this.tail01.addChild(this.lFin02a);
        this.tail04.addChild(this.lFin05a);
        this.tail00.addChild(this.lFin01a);
        this.head.addChild(this.rAntenna01);
        this.tail04.addChild(this.lFin05b);
        this.head.addChild(this.rAntenna00);
        this.tail00.addChild(this.rFin01b);
        this.tail04.addChild(this.tail05);
        this.tail01.addChild(this.lFin02b);
        this.lMandible00.addChild(this.lMandible02);
        this.body.addChild(this.rFin00b);
        this.tail05.addChild(this.lFin06b);
        this.tail02.addChild(this.tail03);
        this.body.addChild(this.lFin00a);
        this.tail03.addChild(this.lFin04c);
        this.tail04.addChild(this.rFin05a);
        this.tail01.addChild(this.rFin02c);
        this.head.addChild(this.lMandible00);
        this.tail02.addChild(this.rFin03b);
        this.tail01.addChild(this.lFin02c);
        this.tail02.addChild(this.lFin03c);
        this.tail02.addChild(this.lFin03a);
        this.head.addChild(this.mAntenna);
        this.tail05.addChild(this.rFin06c);
        this.rMandible00.addChild(this.rMandible02);
        this.tail04.addChild(this.rFin05b);
        this.tail00.addChild(this.lFin01c);
        this.tail01.addChild(this.tail02);
        this.tail01.addChild(this.rFin02a);
        this.lMandible00.addChild(this.lMandible01);
        this.tail05.addChild(this.lFin06a);
        this.tail04.addChild(this.rFin05c);
        this.body.addChild(this.head);
        this.tail03.addChild(this.lFin04a);
        this.tail03.addChild(this.tail04);
        this.tail00.addChild(this.tail01);
        this.tail02.addChild(this.rFin03c);
        this.tail00.addChild(this.lFin01b);
        this.tail05.addChild(this.rFin06b);
        this.head.addChild(this.lAntenna01);
        this.body.addChild(this.tail00);
        this.tail01.addChild(this.rFin02b);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        if(entity instanceof EntityBobbitWorm) {
            EntityBobbitWorm worm = (EntityBobbitWorm) entity;
            int attack = worm.getAttackState();
            
            float add = entity.getUniqueID().hashCode() * 0.001F;
            if(attack > 0) {
                float angle = ((30 - attack) % 60) * 0.04F + 0.3839724354387525F;
                this.lMandible00.rotateAngleY = angle;
                this.rMandible00.rotateAngleY = -angle;
            } else {
                float mul = 0.1F;
                float div = 40F;
                this.lMandible00.rotateAngleY = MathHelper.cos(ageInTicks * (mul + 0.03F) + add) / div + 0.3839724354387525F;
                this.rMandible00.rotateAngleY = MathHelper.cos(ageInTicks * (mul) + add) / div - 0.3839724354387525F;
            }
            this.body.rotateAngleX = 0;
            this.body.offsetY = 0;
            
            if (worm.world.isBlockLoaded(entity.getPosition()) && worm.world.isBlockLoaded(entity.getPosition().down()))
            {
                if (worm.motionX < 0.03 && worm.motionZ < 0.03 && worm.motionY < 0.03 && !worm.getMoveHelper().isUpdating() && worm.isGoodBurrowingPosition(worm.getPosition()))
                {
                    this.body.rotateAngleX = (float) - Math.toRadians(70.F);
                    
//                    if ( worm.grabTargetAnimation > 0 )
//                    {
//                    	if ( worm.grabTargetAnimation > 15 )
//                    	{
//                    		int n = 30-worm.grabTargetAnimation;
//                        	this.body.offsetY = 0.3F - (n)/(8.0F + n/2.0F);
//                            this.body.offsetX = this.body.offsetY;
//                    	}
//                    	else
//                    	{
//                    		int n = worm.grabTargetAnimation;
//                        	this.body.offsetY = 0.3F - (n)/(8.0F + n/2.0F);
//                            this.body.offsetX = this.body.offsetY;
//                    	}
//                    }
//                    else
//                    {
//                        this.body.offsetX = 0.0F;
//                    	this.body.offsetY = 0.3F;
//                    }
                }
            }
            float mul = 0.3F;
            float div = 20.0F;
            this.lAntenna00.rotateAngleX = MathHelper.cos(ageInTicks * (mul + 0.05F) + add) / div;
            this.lAntenna01.rotateAngleX = MathHelper.cos(ageInTicks * mul + add) / div;
            this.mAntenna.rotateAngleX = MathHelper.cos(ageInTicks * (mul + 0.1F) + add) / div;
            this.rAntenna00.rotateAngleX = MathHelper.cos(ageInTicks * mul + add) / div;
            this.rAntenna01.rotateAngleX = MathHelper.cos(ageInTicks * (mul + 0.03F) + add) / div;
            
            
            if ( worm.grabTargetAnimation > 0 )
            {
            	if ( worm.grabTargetAnimation > 15 )
            	{
            		int n = 30-worm.grabTargetAnimation;
                	this.body.offsetY = 0.3F - (n)/(3.0F + n/3.0F);
                    this.body.offsetZ = this.body.offsetY - 0.3F;
            	}
            	else
            	{
            		int n = worm.grabTargetAnimation;
                	this.body.offsetY = 0.3F - (n)/(3.0F + n/3.0F);
                    this.body.offsetZ = this.body.offsetY - 0.3F;
            	}
            }
            else
            {
            	this.body.offsetY = 0.3F;
                this.body.offsetZ = 0.0F;
            }
            
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
