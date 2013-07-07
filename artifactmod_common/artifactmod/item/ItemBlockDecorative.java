package artifactmod.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDecorative extends ItemBlock {

	public ItemBlockDecorative(int id) {
		super(id);
		setHasSubtypes(true); // This block has multiple types
	}
	
	/**
	 * Returns a unique name for each type of block, using the unlocalized name and metadata.
	 * @param itemStack The ItemStack to identify
	 * @return "unlocalizedName.metadata" ex. "blockStoneDecorative.3"
	 */
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int metadata = itemStack.getItemDamage();
		return getUnlocalizedName() + "." + metadata;
	}
	
	/**
	 * Used to set the metadata of the block when placed in the world.
	 * @return The metadata to place on the in-world block
	 */
	public int getMetadata(int par1) {
		return par1;
	}

}
