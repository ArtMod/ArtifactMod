package artifactmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import artifactmod.ArtifactMod;
import artifactmod.item.ItemMisc;
import artifactmod.ref.RefStrings;
import artifactmod.tileentity.TileEntityOrichalcumReceptacle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOrichalcumReceptacle extends Block implements ITileEntityProvider {
	
	private Icon[] icons;

	public BlockOrichalcumReceptacle(int id, Material par2Material) {
		super(id, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
	 * Attaches the tile entity to the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityOrichalcumReceptacle();
	}
	
	/**
	 * Set the textures for the block
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[8];
		
		// Register the 5 charge states
		for (int i = 0; i < 5; i++) {
			String tmp = RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + i);
			icons[i] = par1IconRegister.registerIcon(tmp);
		}
		icons[5] = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + "top"));
		icons[6] = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + "bottom"));
		icons[7] = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + (this.getUnlocalizedName().substring(5) + "topOff"));
	}
	
	/**
	 * Called when the block is right clicked. If the player has an empty hand, the Artifact Case will give them the item.
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		ItemStack is = par5EntityPlayer.inventory.mainInventory[par5EntityPlayer.inventory.currentItem];
		if (is != null) {
			if ((is.itemID == ArtifactMod.itemMisc.itemID) && (is.getItemDamage() == ItemMisc.ORICHALCUM)) {
				TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
				if (te != null) {
					TileEntityOrichalcumReceptacle tile = (TileEntityOrichalcumReceptacle) te;
					if (tile.getFuelLevel() == 0) {
						is.splitStack(1);					// Remove 1 orichalcum from the player
						tile.setFuelLevel(tile.maxFuel);	// Fuel the receptacle
						par1World.markBlockForRenderUpdate(par2, par3, par4);	// Force block to update its textures.
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		if (par5EntityLivingBase instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par5EntityLivingBase;
			int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			world.setBlockMetadataWithNotify(x, y, z, dir, 0);
		}
	}
	
	/**
	 * Returns the icon to render on a specific side of the block
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int side) {
		TileEntity te = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		int max = 0;
		int fuel = 0;
		if (te != null) {
			TileEntityOrichalcumReceptacle tile = (TileEntityOrichalcumReceptacle) te;
			max = tile.maxFuel;
			fuel = tile.getFuelLevel();
		}
		switch(side) {
			case 0:		// Bottom side
				return icons[6];
			case 1:		// Top side
				if (fuel > 0) return icons[5];	// 1-100%
				return icons[7];				// 0%
			default:	// Sides
				if (fuel > (max * 0.75)) return icons[0];	// 76-100%
				if (fuel > (max * 0.5)) return icons[1];	// 51-75%
				if (fuel > (max * 0.25)) return icons[2];	// 26-50%
				if (fuel > 0) return icons[3];				// 1-25%
				return icons[4];							// 0%
		}
	}
	
	/**
	 * Returns the icon to render on a specific side of the block
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		switch(side) {
			case 0:		// Bottom
				return icons[6];
			case 1:		// Top
				return icons[7];
			default:	// Sides
				return icons[4];
		}
	}
}
