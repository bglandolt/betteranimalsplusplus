package its_meow.betteranimalsplus.common.tileentity;

import java.util.Random;

import its_meow.betteranimalsplus.common.entity.miniboss.hirschgeist.EntityHirschgeist;
import its_meow.betteranimalsplus.init.ModBlocks;
import its_meow.betteranimalsplus.init.ModTriggers;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityHandOfFate extends TileEntity {

    private boolean onFire;
    private final String keyOnFire = "OnFire";

    private boolean hasNetherWart;
    private final String keyNetherWart = "HasNetherWart";

    private boolean hasAntler;
    private final String keyAntler = "HasAntler";

    private boolean hasVenison;
    private final String keyVenison = "HasVenison";

    public TileEntityHandOfFate() {
    }

    public TileEntityHandOfFate(World worldIn) {
        this.world = worldIn;
    }

    public void setOnFire(boolean b) {
        this.onFire = b;
        if (this.world.isRemote) {
            this.world.scheduleUpdate(this.pos, this.getBlockType(), 0);
            this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 0);
            this.world.markBlockRangeForRenderUpdate(this.getPos().down(5).west(5).north(5), this.getPos().up(5).east(5).south(5));
        }
        this.markDirty();
    }

    public boolean isOnFire() {
        return this.onFire;
    }

    public boolean hasNetherWart() {
        return this.hasNetherWart;
    }

    public void setHasNetherWart(EntityPlayer player, boolean hasNetherWart) {
        this.hasNetherWart = hasNetherWart;
        this.markDirty();
        this.checkHasAllThree(player);
    }

    public boolean hasAntler() {
        return this.hasAntler;
    }

    public void setHasAntler(EntityPlayer player, boolean hasAntler) {
        this.hasAntler = hasAntler;
        this.markDirty();
        this.checkHasAllThree(player);
    }

    public boolean hasVenison() {
        return this.hasVenison;
    }

    public void setHasVenison(EntityPlayer player, boolean hasVenison) {
        this.hasVenison = hasVenison;
        this.markDirty();
        this.checkHasAllThree(player);
    }

    private void checkHasAllThree(EntityPlayer player) {
        if (this.hasVenison && this.hasAntler && this.hasNetherWart && this.isOnFire()) {
            this.setHasVenison(player, false);
            this.setHasAntler(player, false);
            this.setHasNetherWart(player, false);
            this.fireBurst();
            this.spawnHirschgeist();
            if(!world.isRemote && player instanceof EntityPlayerMP) {
                ModTriggers.HAND_OF_FATE_SUMMON.trigger((EntityPlayerMP) player);
            }
        }
    }

    private void spawnHirschgeist() {
        if (!this.world.isRemote) {
            EntityHirschgeist hg = new EntityHirschgeist(this.world);
            hg.setLocationAndAngles(this.pos.getX(), this.pos.getY() + 1F, this.pos.getZ(), 0, 0);
            hg.setNoAI(false);
            this.world.spawnEntity(hg);
        }
    }

    private void fireBurst() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.getPos().getX() + ((rand.nextFloat() + 0.5F) / 2), this.getPos().getY() + 1.5F, this.getPos().getZ() + ((rand.nextFloat() + 0.5F) / 2), 0, 0.5F, 0);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey(this.keyOnFire)) {
            this.onFire = compound.getBoolean(this.keyOnFire);
        }
        if (compound.hasKey(this.keyNetherWart)) {
            this.hasNetherWart = compound.getBoolean(this.keyNetherWart);
        }
        if (compound.hasKey(this.keyAntler)) {
            this.hasAntler = compound.getBoolean(this.keyAntler);
        }
        if (compound.hasKey(this.keyVenison)) {
            this.hasVenison = compound.getBoolean(this.keyVenison);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean(this.keyOnFire, this.onFire);
        compound.setBoolean(this.keyAntler, this.hasAntler);
        compound.setBoolean(this.keyNetherWart, this.hasNetherWart);
        compound.setBoolean(this.keyVenison, this.hasVenison);
        return compound;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new SPacketUpdateTileEntity(this.pos, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
        this.world.scheduleUpdate(this.pos, this.blockType, 100);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @SideOnly(Side.CLIENT)
    public float getRotation() {
        IBlockState state = this.world.getBlockState(this.pos);
        if (state.getBlock() == ModBlocks.HAND_OF_FATE) {
            EnumFacing facing = state.getValue(BlockHorizontal.FACING).getOpposite();
            if (facing == EnumFacing.NORTH) {
                return 0F;
            }
            if (facing == EnumFacing.EAST) {
                return 90F;
            }
            if (facing == EnumFacing.SOUTH) {
                return 180F;
            }
            if (facing == EnumFacing.WEST) {
                return 270F;
            }
        }
        return 0F;
    }

}
