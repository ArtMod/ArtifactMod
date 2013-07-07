package artifactmod.tileentity;

import artifactmod.ArtifactMod;
import artifactmod.ref.RefStrings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOrichalcumReceptacle extends TileEntity implements IInventory {
	
	/**
	 * The amount of orichalcum energy remaining.
	 */
	private int fuel;
	
	private ItemStack[] inventory;
	
	/**
	 * Amount of energy to add per orichalcum, also the capacity.
	 */
	public static int maxFuel = 1200;
	
	public TileEntityOrichalcumReceptacle() {
		inventory = new ItemStack[1];
		inventory[0] = new ItemStack(ArtifactMod.itemArtifactPick, 1);
	}
	
	/**
	 * @return Current fuel level
	 */
	public int getFuelLevel() {
		return this.fuel;
	}
	
	/**
	 * Used to set the fuel level, with a maximum of {@code maxFuel}
	 * @param par1 Fuel level to set
	 */
	public void setFuelLevel(int par1) {
		if (par1 > this.maxFuel) par1 = 1200;
		this.fuel = par1;
	}
	
	/**
	 * Allows updating of the block so when fuel level changes, the client is notified.
	 */
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}

	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
		readFromNBT(packet.customParam1);
	}
	
	/**
	 * Read from the save data to load any fuel
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		fuel = nbtTagCompound.getInteger("fuel");
	}

	/**
	 * Save the fuel within
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("fuel", fuel);
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	/**
	 * Decreases the number of items in a specified slot by a specified amount, if possible.
	 * @return The itemStack with the specified amount of items
	 */
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
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
		inventory[i] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
	}

	/**
	 * Returns the name of the inventory.
	 */
	@Override
	public String getInvName() {
		return RefStrings.BLOCK_ORICHALCUMRECEPTACLE;
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
}
