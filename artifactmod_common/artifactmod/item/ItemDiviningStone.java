package artifactmod.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import artifactmod.ref.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDiviningStone extends Item {

	public ItemDiviningStone(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	/**
	 * Sets the icon for the Divining Stone.
	 */
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}

}
