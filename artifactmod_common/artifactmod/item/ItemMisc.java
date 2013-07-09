package artifactmod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import artifactmod.ref.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMisc extends Item {
	
	// List of all Misc items
	public static short ORICHALCUM = 0;
	public static short FOCUSINGCRYSTAL = 1;
	public static short KEYFRAGMENT = 2;
	public static short KEY = 3;
	
	/**
	 * Array of textures used to render items
	 */
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemMisc(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHasSubtypes(true);
	}
	
	/**
	 * Load all textures needed for the items.
	 * @param par1IconRegister IconRegister used to load textures.
	 */
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[4];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(ModInfo.MOD_ID + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}
	
	/**
	 * Triggers when item is right-clicked on a block.
	 * @return True if something significant happens, false if not.
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		return false;
	}
	
	/**
	 * Returns unlocalized name, different for each type of item.
	 * @param par1ItemStack The ItemStack to identify.
	 * @return Unlocalized name of the item, based on metadata.
	 */
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + i;
	}
	
	/**
	 * Used to get the texture for the item.
	 * @param par1 The metadata of the item.
	 * @return Icon of the artifact color.
	 */
	public Icon getIconFromDamage(int par1) {
		return icons[par1];
	}

	/**
	 * Populate a list including all different types of items.
	 */
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

}
