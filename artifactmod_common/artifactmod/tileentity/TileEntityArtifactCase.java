package artifactmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import artifactmod.ref.BlockName;

public class TileEntityArtifactCase extends TileEntity implements IInventory {

	private ItemStack artifact;
	
	public TileEntityArtifactCase() {
		
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i > 0) return null;
		return this.artifact;
	}

	/**
	 * Decreases the number of items in a specified slot by a specified amount, if possible.
	 * @return The itemStack with the specified amount of items
	 */
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (slot > 0) return null;
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {	// If the amount is greater than or equal to what the slot has, remove the slot entirely
				setInventorySlotContents(slot, null);
			}
			else {
				itemStack = itemStack.splitStack(amount);
				if (itemStack.stackSize == 0) {	// If splitting the stack left the original with 0, destroy the stack
					setInventorySlotContents(slot, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack itemStack = getStackInSlot(i);
		if (itemStack != null) {
			setInventorySlotContents(i, null);
		}
		return itemStack;
	}

	/**
	 * Sets the specified inventory slot to hold the specified itemstack, if the itemstack is not null and not greater than its capacity.
	 */
	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		if (i > 0) return;
		artifact = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory.
	 */
	@Override
	public String getInvName() {
		return BlockName.BLOCK_ARTIFACTCASE;
	}

	/**
	 * Used to check if the inventory name is in the local language or not.
	 */
	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		// We only want to hold one item.
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	public Packet getDescriptionPacket() {
		// Allows updating of the inventory so when an artifact is placed or removed, the client is notified.
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
		readFromNBT(packet.customParam1);
	}
	
	/**
	 * Read from the save data to load any artifact, if stored
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		NBTTagCompound tagCompound = (NBTTagCompound) nbtTagCompound.getCompoundTag("artifact");
		artifact = ItemStack.loadItemStackFromNBT(tagCompound);
	}

	/**
	 * Save the artifact within, if any
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		NBTTagCompound compound = new NBTTagCompound();
		if (artifact != null)
			artifact.writeToNBT(compound);
		nbtTagCompound.setCompoundTag("artifact", compound);
	}
}
