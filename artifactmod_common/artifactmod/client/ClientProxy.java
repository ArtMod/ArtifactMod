package artifactmod.client;

import artifactmod.CommonProxy;
import artifactmod.client.renderer.tileentity.TileEntityArtifactCaseRenderer;
import artifactmod.tileentity.TileEntityArtifactCase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int renderPass;
	
	@Override
	public void registerRenderers() {
		
	}
	
	public static void setCustomRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArtifactCase.class, new TileEntityArtifactCaseRenderer());
	}

}
