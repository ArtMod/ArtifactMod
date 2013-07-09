package artifactmod.client;

import artifactmod.CommonProxy;
import artifactmod.client.renderer.tileentity.TileEntityArtifactCaseRenderer;
import artifactmod.client.renderer.tileentity.TileEntityOrichalcumReceptacleRenderer;
import artifactmod.tileentity.TileEntityArtifactCase;
import artifactmod.tileentity.TileEntityOrichalcumReceptacle;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		
	}
	
	public static void setCustomRenderers() {
		// Register special renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArtifactCase.class, new TileEntityArtifactCaseRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOrichalcumReceptacle.class, new TileEntityOrichalcumReceptacleRenderer());
	}

}
