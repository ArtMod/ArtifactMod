package artifactmod.item;

import artifactmod.ref.RefStrings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemDiviningStone extends Item {

	public ItemDiviningStone(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}

}
