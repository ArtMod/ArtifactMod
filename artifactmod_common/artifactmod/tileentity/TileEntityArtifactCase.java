package artifactmod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import artifactmod.ref.RefStrings;

public class TileEntityArtifactCase extends TileEntity implements IInventory {

	private ItemStack[] inventory;
	
	public static final int INVENTORY_SIZE = 1;
	
	public TileEntityArtifactCase() {
		// Initialize inventory to the proper size
		this.inventory = new ItemStack[INVENTORY_SIZE];
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		/*
		 * Decreases the number of items in a specified slot by a specified amount, if possible.
		 * @return The itemStack with the specified amount of items
		 */
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

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		/*
		 * Sets the specified inventory slot to hold the specified itemstack, if the itemstack is not null and not greater than its capacity.
		 */
		inventory[i] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		/*
		 * Returns the name of the inventory.
		 */
		return RefStrings.ARTIFACT_CASE_NAME;
	}

	@Override
	public boolean isInvNameLocalized() {
		/*
		 * Used to check if the inventory name is in the local language or not.
		 */
		return false;
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
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		// Read from the save data to load any artifact, if stored
		super.readFromNBT(nbtTagCompound);
		NBTTagList tagList = nbtTagCompound.getTagList("Items");
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		// Save the artifact within, if any
		super.writeToNBT(nbtTagCompound);
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Items", tagList);
	}
}
