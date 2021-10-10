package net.torocraft.toroquest.entities.ai;

import java.util.Random;

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.torocraft.toroquest.entities.EntityGuard;
import net.torocraft.toroquest.entities.EntitySentry;

public class EntityAIRaid extends EntityAIBase
{
	private final EntityCreature entity;
	private int radiusSq;
	private int centerX;
	private int centerZ;
	private double movePosX;
	private double movePosY;
	private double movePosZ;
	private final double movementSpeed;
	
	private Random rand = new Random();

	public EntityAIRaid(EntityCreature entity, double speedIn, int radius)
	{
		this.entity = entity;
		this.movementSpeed = speedIn;
		this.radiusSq = radius * radius;
		this.setMutexBits(1);
	}

	public void setCenter(int centerX, int centerZ)
	{
		this.centerX = centerX;
		this.centerZ = centerZ;
	}
	
	public BlockPos getSurfacePosition(World world, BlockPos start, int range)
	{						
		int x = start.getX() - this.centerX + ( rand.nextInt(range) * (rand.nextInt(2)*2 - 1) );
		int z = start.getZ() - this.centerZ + ( rand.nextInt(range) * (rand.nextInt(2)*2 - 1) );
		
		int signX = -1;
		int signZ = -1;
		
		if ( x < 0 )
		{
			signX = 1;
		}
		if ( z < 0 )
		{
			signZ = 1;
		}
		
		x = Math.abs(x);
		z = Math.abs(z);
		
		double ratioX = (double)(x)/(double)(1+x+z);
		double ratioZ = (double)(z)/(double)(1+x+z);
		
		x = (int)(ratioX * range);
		z = (int)(ratioZ * range);
				
		x = MathHelper.clamp(x, 0, range ) * signX;
		z = MathHelper.clamp(z, 0, range ) * signZ;		
		
		return findValidSurface(world, new BlockPos(start.getX()+x, start.getY(), start.getZ()+z), 8);
	}
	
	public static BlockPos findValidSurface( World world, BlockPos startPos, int yOffset )
	{
		IBlockState blockState;
		
		// =-=-=-=-=-= SEARCH UP =-=-=-=-=-=
		int y = yOffset;
		boolean airspace = false;
		boolean floor =  false;
		BlockPos pos = startPos.down();
		while ( y > 0 )
		{
			blockState = world.getBlockState(pos);
			if ( blockState.getBlock() == Blocks.LAVA )
			{
				return null;
			}
			if ( blockState.getBlock() instanceof BlockAir )
			{
				if ( floor )
				{
					if ( airspace )
					{
						return pos.down();
					}
					else
					{
						airspace = true;
					}
				}
			}
			else
			{
				floor = true;
				airspace = false;
			}
			pos = pos.up();
			y--;
		}
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		// =-=-=-=-= SEARCH DOWN =-=-=-=-=
		y = yOffset;
		airspace = false;
		boolean airspace2 = false;
		pos = startPos.up();
		while ( y > 0 )
		{
			blockState = world.getBlockState(pos);
			if ( blockState.getBlock() == Blocks.LAVA )
			{
				return null;
			}
			if ( blockState.getBlock() instanceof BlockAir )
			{
				if ( airspace )
				{
					airspace2 = true;
				}
				else
				{
					airspace = true;
				}
			}
			else
			{
				if ( airspace && airspace2 )
				{
					return pos.up();
				}
				airspace = false;
				airspace2 = false;
			}
			pos = pos.down();
			y--;
		}
		return null;
	}

	public boolean shouldExecute()
	{
		if ( this.inCorrectPosition() )
		{
			return false;
		}
		
//		if ( this.entity instanceof EntitySentry )
//		{
//			if ( this.entity.isRiding() )
//			{
//				return false;
//			}
//	    	
//			if ( this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() <= 0.0D )
//			{
//				this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D+rand.nextDouble()/50.0D);
//			}
//		}

//		this.pos = this.getSurfacePosition(this.entity.world, new BlockPos(this.centerX,0,this.centerZ));
//		this.path = this.entity.getNavigator().getPathToPos(pos);
//		
//		if ( path != null && this.pos != null )
//		{
//			this.entity.getNavigator().setPath(path, this.movementSpeed);
//			return true;
//		}
//		else
		
		if ( this.entity.getNavigator().noPath() || rand.nextInt(32) == 0 )
		{
			BlockPos vec3d = null;
			vec3d = this.getSurfacePosition(this.entity.world, this.entity.getPosition(), 24);
			if (vec3d == null)
			{
				vec3d = this.getSurfacePosition(this.entity.world, this.entity.getPosition(), 16);
				if (vec3d == null)
				{
					return false;
				}
			}
			else
			{
				this.movePosX = vec3d.getX();
				this.movePosY = vec3d.getY();
				this.movePosZ = vec3d.getZ();
			}
			return true;
		}
		return false;
		
		/*
		Vec3d vec3d = null;
		
		for ( int i = 0; i < 8; i++)
		{
			vec3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.entity, 32, 16, new Vec3d((double) centerX, (double) this.entity.posY, (double) centerZ));
			
			if (vec3d == null)
			{

			}
			else
			{
				double xdif = Math.abs(this.entity.posX-vec3d.x);
				double zdif = Math.abs(this.entity.posZ-vec3d.z);
				
				System.out.println(xdif);
				System.out.println(zdif);
				
				if ( xdif + zdif < 8 || Math.abs(xdif-zdif) > 8 )
				{

				}
				else
				{
					this.movePosX = vec3d.x;
					this.movePosY = vec3d.y;
					this.movePosZ = vec3d.z;
					return true;
				}
			}
		}
		return false;
		*/
		
	}

	public boolean inCorrectPosition()
	{
		double x = (double) centerX - entity.posX;
		double z = (double) centerZ - entity.posZ;
		double dist = x * x + z * z;
		return dist < radiusSq;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return !this.entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.entity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
	}
}