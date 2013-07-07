package artifactmod.client.renderer.tileentity;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import artifactmod.tileentity.TileEntityArtifactCase;

public class TileEntityArtifactCaseRenderer extends TileEntitySpecialRenderer {
	
	private final RenderItem customRenderItem;
	
	public TileEntityArtifactCaseRenderer() {
		customRenderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;	// Keep the item still when rendering
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	/**
	 * Special render function to show the artifact within the case.
	 * @param tileentity The Tile Entity to render
	 * @param x X coordinate of the Tile Entity
	 * @param y Y coordinate of the Tile Entity
	 * @param z Z coordinate of the Tile Entity
	 */
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick) {
		if (tileentity instanceof TileEntityArtifactCase) {
			GL11.glPushMatrix();				// Save the current matrix to not interfere with rendering
	        GL11.glDisable(GL11.GL_LIGHTING);	// Disable special lighting
	        
			TileEntityArtifactCase te = (TileEntityArtifactCase) tileentity;
			if (((TileEntityArtifactCase) tileentity).getStackInSlot(0) != null) {
                EntityItem artifactEntity = new EntityItem(tileentity.worldObj);
                artifactEntity.setEntityItemStack(te.getStackInSlot(0));		// Set the itemstack to render
                float scale = 1.5F;												// Make item larger to fill out the case
                GL11.glTranslatef((float) x + 0.5F, (float) y + 0.35F, (float) z + 0.5F); // Center the item in the case
                GL11.glScalef(scale, scale, scale);								// Apply scale transform
                customRenderItem.doRenderItem(artifactEntity, 0, 0, 0, 0, 0);	// Render the artifact
			}
		}
		GL11.glPopMatrix();					// Return to the saved matrix
        GL11.glEnable(GL11.GL_LIGHTING);	// Re-enable special lighting
	}
}
