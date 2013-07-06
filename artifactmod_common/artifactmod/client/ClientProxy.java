package artifactmod.client;

import artifactmod.CommonProxy;
import artifactmod.client.renderer.tileentity.TileEntityArtifactCaseRenderer;
import artifactmod.tileentity.TileEntityArtifactCase;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		
	}
	
	public static void setCustomRenderers() {
		// Register special renderer for the Artifact case
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArtifactCase.class, new TileEntityArtifactCaseRenderer());
	}

}
