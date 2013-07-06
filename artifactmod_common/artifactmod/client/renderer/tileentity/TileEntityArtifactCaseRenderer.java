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
				return false;
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick) {
		/*
		 * Special render function to show the artifact within the case.
		 */
		if (tileentity instanceof TileEntityArtifactCase) {
			GL11.glPushMatrix();
	        GL11.glDisable(GL11.GL_LIGHTING);
			TileEntityArtifactCase te = (TileEntityArtifactCase) tileentity;
			if (((TileEntityArtifactCase) tileentity).getStackInSlot(0) != null) {
                EntityItem artifactEntity = new EntityItem(tileentity.worldObj);
                artifactEntity.hoverStart = 0.0F;
                artifactEntity.setEntityItemStack(te.getStackInSlot(0));
                float scale = 1.5F;
                GL11.glTranslatef((float) x + 0.5F, (float) y + 0.35F, (float) z + 0.5F);
                GL11.glScalef(scale, scale, scale);
                customRenderItem.doRenderItem(artifactEntity, 0, 0, 0, 0, 0);
			}
		}
		GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
	}
}
