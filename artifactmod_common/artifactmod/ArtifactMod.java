package artifactmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import artifactmod.block.BlockArtifactCase;
import artifactmod.block.BlockDecorative;
import artifactmod.client.ClientProxy;
import artifactmod.item.ItemArtifact;
import artifactmod.item.ItemBlockDecorative;
import artifactmod.item.ItemDiviningStone;
import artifactmod.item.ItemMisc;
import artifactmod.ref.RefStrings;
import artifactmod.tileentity.TileEntityArtifactCase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
        modid = RefStrings.MOD_ID,
        name = RefStrings.MOD_NAME,
        version = RefStrings.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false)
public class ArtifactMod {
	
	@SidedProxy(clientSide = "artifactmod.client.ClientProxy", serverSide = "artifactmod.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block blockStoneDecorative,
		blockArtifactCase;
	public static int blockStoneDecorative_id,
		blockArtifactCase_id;
	
	public static Item itemArtifact,
		itemDiviningStone,
		itemMisc;
	public static int itemArtifact_id,
		itemDiviningStone_id,
		itemMisc_id;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	// Read config, create and register items/blocks
    	
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	config.addCustomCategoryComment("item", "Item IDs are as shown, do not add 256.");
    	
    	blockStoneDecorative_id = config.getBlock("blockStoneDecorative", 3550).getInt();
    	blockArtifactCase_id = config.getBlock("blockArtifactCase", 3551).getInt();
    	
    	itemArtifact_id = config.getItem("itemArtifact", 13350).getInt();
    	itemDiviningStone_id = config.getItem("itemDiviningStone", 13551).getInt();
    	itemMisc_id = config.getItem("itemMisc", 13552).getInt();
    	
    	
    	blockStoneDecorative = new BlockDecorative(blockStoneDecorative_id, Material.rock).setUnlocalizedName("blockStoneDecorative").setHardness(2.0F).setResistance(10.0F);
    	blockArtifactCase = new BlockArtifactCase(blockArtifactCase_id, Material.glass).setUnlocalizedName("blockArtifactCase");
    	
    	itemArtifact = new ItemArtifact(itemArtifact_id - 256).setUnlocalizedName("itemArtifact");
    	itemDiviningStone = new ItemDiviningStone(itemDiviningStone_id - 256).setUnlocalizedName("itemDiviningStone");
    	itemMisc = new ItemMisc(itemMisc_id - 256).setUnlocalizedName("itemMisc");
    	
    	config.save();
    	
    	GameRegistry.registerBlock(blockStoneDecorative, ItemBlockDecorative.class, RefStrings.MOD_ID + (blockStoneDecorative.getUnlocalizedName().substring(5)));
    	GameRegistry.registerBlock(blockArtifactCase, RefStrings.MOD_ID + (blockArtifactCase.getUnlocalizedName().substring(5)));
    	
    	GameRegistry.registerTileEntity(TileEntityArtifactCase.class, RefStrings.TE_ARTIFACT_CASE_ID);
    	
    	GameRegistry.addRecipe(new ItemStack(itemDiviningStone, 1),
    			"LDL",
    			"DGD",
    			"LDL",
    			'L', Block.blockLapis, 'D', Item.diamond, 'G', Block.glowStone);
    	
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 0), "Dungeon Brick");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 1), "Dungeon Brick");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 2), "Dungeon Brick");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 3), "Dungeon Brick");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 4), "Mossy Dungeon Pattern");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 5), "Mossy Dungeon Pattern");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 6), "Dungeon Pattern");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 7), "Dungeon Pattern");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 8), "Dungeon Pattern");
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 9), "Dungeon Pattern");
    	LanguageRegistry.addName(blockArtifactCase, "Artifact Case");
    	
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 0), "White Artifact");
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 1), "Black Artifact");
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 2), "Gold Artifact");
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 3), "Red Artifact");
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 4), "Green Artifact");
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 5), "Blue Artifact");
    	LanguageRegistry.addName(itemDiviningStone, "Divining Stone");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	// Create recipes, build data structures
    	ClientProxy.setCustomRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	// Handle interaction with other mods
    }
}
