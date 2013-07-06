package artifactmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import artifactmod.ref.RefStrings;
import artifactmod.tileentity.TileEntityArtifactCase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArtifactCase extends Block implements ITileEntityProvider {

	public BlockArtifactCase(int id, Material par2Material) {
		super(id, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.3F);
	}

	public TileEntity createNewTileEntity(World world) {
		return new TileEntityArtifactCase();
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
		if (te != null) {
			TileEntityArtifactCase caseBlock = (TileEntityArtifactCase) te;
			ItemStack artifact = caseBlock.getStackInSlot(0);
			if (artifact == null) return false;
			ItemStack[] inv = par5EntityPlayer.inventory.mainInventory;
			ItemStack is = inv[par5EntityPlayer.inventory.currentItem];
			if (is == null || is.stackSize == 0) {
				par5EntityPlayer.inventory.mainInventory[par5EntityPlayer.inventory.currentItem] = artifact;
				caseBlock.setInventorySlotContents(0, null);
				return true;
			}
		}
		return false;
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		TileEntityArtifactCase caseBlock = (TileEntityArtifactCase) par1World.getBlockTileEntity(par2, par3, par4);
		
		if (caseBlock != null) {
			ItemStack artifact = caseBlock.getStackInSlot(0);
			if (artifact == null || artifact.stackSize == 0) return;
			EntityItem entityitem = new EntityItem(par1World, par2, par3, par4, artifact);
			par1World.spawnEntityInWorld(entityitem);
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(RefStrings.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 0;
	}

}
