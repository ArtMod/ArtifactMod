package artifactmod.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import artifactmod.ArtifactMod;
import artifactmod.tileentity.TileEntityOrichalcumReceptacle;

public class TileEntityOrichalcumReceptacleRenderer extends TileEntitySpecialRenderer {

	private ModelArtifactHarness harness;

	private final RenderItem customRenderItem;

	public TileEntityOrichalcumReceptacleRenderer() {
		harness = new ModelArtifactHarness();
		customRenderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;	// Keep the item still when rendering
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	/**
	 * Special render function to show the harness above the receptacle.
	 * @param tileentity The Tile Entity to render
	 * @param x X coordinate of the Tile Entity
	 * @param y Y coordinate of the Tile Entity
	 * @param z Z coordinate of the Tile Entity
	 */
	public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f) {
		// If you don't understand this code, don't worry. I don't either.
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		TileEntityOrichalcumReceptacle tileEntityYour = (TileEntityOrichalcumReceptacle)tileEntity;
		renderBlockYour(tileEntityYour, tileEntity.worldObj, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, ArtifactMod.blockOrichalcumReceptacle);

		if (tileEntity instanceof TileEntityOrichalcumReceptacle) {
			TileEntityOrichalcumReceptacle te = (TileEntityOrichalcumReceptacle) tileEntity;
			if (((TileEntityOrichalcumReceptacle) tileEntity).getStackInSlot(0) != null) {
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);	// Disable special lighting
				
				EntityItem artifactEntity = new EntityItem(tileEntity.worldObj);
				artifactEntity.setEntityItemStack(te.getStackInSlot(0));		// Set the itemstack to render
				float scale = 1.5F;												// Make item larger to fill out the case
				GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.35F, (float) d2 + 0.5F); // Center the item in the case
				GL11.glScalef(scale, scale, scale);								// Apply scale transform
				//customRenderItem.doRenderItem(artifactEntity, 0, 0, 0, 0, 0);	// Render the artifact
				
				GL11.glPopMatrix();
				GL11.glEnable(GL11.GL_LIGHTING);	// Re-enable special lighting
			}
		}

		GL11.glPopMatrix();
	}

	public void renderBlockYour(TileEntityOrichalcumReceptacle tl, World world, int i, int j, int k, Block block) {
		Tessellator tessellator = Tessellator.instance;

		// Set block brightness
		float f = block.getBlockBrightness(world, i, j, k);
		int l = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int l1 = l % 65536;
		int l2 = l / 65536;
		tessellator.setColorOpaque_F(f, f, f);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)l1, (float)l2); 

		int dir = world.getBlockMetadata(i, j, k);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0, 0.5F);
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glTranslatef(0.0F, 0.5F, 0.0F);
		func_110628_a(new ResourceLocation("artifactmod:textures/TryTex.png"));
		harness.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}
