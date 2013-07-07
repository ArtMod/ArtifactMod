package artifactmod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import artifactmod.ref.RefStrings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDecorative extends Block {

	/**
	 * Array of icons used to render the block
	 */
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public BlockDecorative(int id, Material par2Material) {
		super(id, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
	 * Load all the icons needed to render the blocks
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[10];
		
		for (int i = 0; i < icons.length; i++) {
			String tmp = RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + i);
			icons[i] = par1IconRegister.registerIcon(tmp);
		}
	}
	
	/**
	 * Decides the metadata of the block to drop
	 */
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	/**
	 * Decides what Icon to use to render a block, dependent on its metadata.
	 * @return The Icon to render the block
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return icons[metadata];
	}
	
	/**
	 * Populates a list of each type of sub-block this block implements
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

}
