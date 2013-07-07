package artifactmod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import artifactmod.ref.RefStrings;
import artifactmod.tileentity.TileEntityArtifactCase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArtifact extends Item {
	
	/**
	 * Array of textures used to render artifacts
	 */
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	/**
	 * Used to identify the artifacts in unlocalized names
	 */
	public static final String[] names = new String[] {
		"white",
		"black",
		"gold",
		"red",
		"green",
		"blue"
	};

	public ItemArtifact(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Load all textures needed for the artifacts.
	 * @param par1IconRegister IconRegister used to load textures.
	 */
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[6];
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}
	
	@Override
	/**
	 * Triggers when artifact is right-clicked on a block. If the block is an empty artifact case, the artifact will be placed inside.
	 * @return True if artifact was placed, false if not.
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		TileEntity te = par3World.getBlockTileEntity(par4, par5, par6);
		if (te != null) {
			if (te instanceof TileEntityArtifactCase) {
				TileEntityArtifactCase caseBlock = (TileEntityArtifactCase) te;
				ItemStack is = caseBlock.getStackInSlot(0);
				if (is == null || is.stackSize == 0) {
					ItemStack toPlace = par1ItemStack.splitStack(1);
					caseBlock.setInventorySlotContents(0, toPlace);
				}
			}
		}
		return false;
	}
	
	@Override
	/**
	 * Returns unlocalized name, different for each type of artifact.
	 * @param par1ItemStack The ItemStack to identify.
	 * @return Unlocalized name of the item, based on metadata.
	 */
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + this.names[i];
	}
	

	/**
	 * Used to get the texture for the artifact.
	 * @param par1 The metadata of the artifact.
	 * @return Icon of the artifact color.
	 */
	public Icon getIconFromDamage(int par1) {
		return icons[par1];
	}
	

	/**
	 * Populate a list including all different types of artifacts.
	 */
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < icons.length; i++) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

}
