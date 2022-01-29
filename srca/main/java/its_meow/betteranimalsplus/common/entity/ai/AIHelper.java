package its_meow.betteranimalsplus.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.MathHelper;

public interface AIHelper
{
	public static void faceEntitySmart(EntityLivingBase in, EntityLivingBase p)
    {
    	try
    	{
	        double d0 = (p.getPositionVector().x - in.getPositionVector().x) * 2;
	        double d2 = (p.getPositionVector().z - in.getPositionVector().z) * 2;
	        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	        in.rotationYaw = f;
	        in.prevRotationYaw = f;
    	}
    	catch ( Exception e ) {}
    }
	
	public static void faceLocationSmart(EntityLivingBase in, double x, double z)
    {
    	try
    	{
	        double d0 = (x - in.getPositionVector().x) * 2;
	        double d2 = (z - in.getPositionVector().z) * 2;
	        
	        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	        
	        in.rotationYaw = f;
	        in.prevRotationYaw = f;
    	}
    	catch ( Exception e ) {}
    }
	
    public static void faceAwayEntity(EntityLivingBase in, Entity entityIn)
    {
        double d0 = in.posX - entityIn.posX;
        double d2 = in.posZ - entityIn.posZ;
        double d1;

        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            d1 = entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() - (in.posY + (double)in.getEyeHeight());
        }
        else
        {
            d1 = (entityIn.getEntityBoundingBox().minY + entityIn.getEntityBoundingBox().maxY) / 2.0D - (in.posY + (double)in.getEyeHeight());
        }

        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
        in.rotationPitch = f1;
        in.rotationYaw = f;
    }
	
	public static void faceLocationSmart(EntityLivingBase in, int x, int y, int z)
    {
    	try
    	{
	        double d0 = (x - in.getPositionVector().x) * 2;
	        double d2 = (z - in.getPositionVector().z) * 2;
	        double d1 = (y - in.getPositionVector().y) * 2;
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        
	        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	        float f1 = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
	        
	        in.rotationPitch = f1;
	        in.prevRotationPitch = f1;
	        in.rotationYaw = f;
	        in.prevRotationYaw = f;
    	}
    	catch ( Exception e ) {}
    }
	
    public static boolean faceMovingDirection(EntityLiving in)
    {
    	if ( in.getNavigator().getPath() != null )
        {
	    	try
	    	{
		    	PathPoint p = in.getNavigator().getPath().getFinalPathPoint();
		    	PathPoint p2 = in.getNavigator().getPath().getPathPointFromIndex(in.getNavigator().getPath().getCurrentPathIndex());

		        double d0 = (p.x - in.posX) + (p2.x - in.posX);
		        double d2 = (p.z - in.posZ) + (p2.z - in.posZ);
		        
		        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
		        
		        in.rotationYaw = f;
		        in.prevRotationYaw = f;

		        return true;
	    	}
	    	catch ( Exception e )
	    	{
	    		in.prevRotationYaw = in.rotationYaw;
	    	}
        }
    	else
    	{
    		in.prevRotationYaw = in.rotationYaw;
    	}
    	return false;
    }
    
    public static boolean faceCurrentMovingDirection(EntityLiving in)
    {
    	if ( in.getNavigator().getPath() != null )
        {
	    	try
	    	{
		    	PathPoint p2 = in.getNavigator().getPath().getPathPointFromIndex(in.getNavigator().getPath().getCurrentPathIndex());

		        double d0 = p2.x - in.posX;
		        double d2 = p2.z - in.posZ;
		        
		        float f = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
		        
		        in.rotationYaw = f;
		        in.prevRotationYaw = f;

		        return true;
	    	}
	    	catch ( Exception e )
	    	{
	    		in.prevRotationYaw = in.rotationYaw;
	    	}
        }
    	else
    	{
    		in.prevRotationYaw = in.rotationYaw;
    	}
    	return false;
    }
}
