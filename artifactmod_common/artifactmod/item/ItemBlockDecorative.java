package artifactmod.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import artifactmod.block.BlockDecorative;

public class ItemBlockDecorative extends ItemBlock {

	public ItemBlockDecorative(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int metadata = itemStack.getItemDamage();
		return getUnlocalizedName() + "." + metadata;
	}
	
	public int getMetadata(int par1) {
		return par1;
	}

}
