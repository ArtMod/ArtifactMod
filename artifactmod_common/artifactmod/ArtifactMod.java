package artifactmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import artifactmod.block.BlockArtifactCase;
import artifactmod.block.BlockDecorative;
import artifactmod.block.BlockOrichalcumReceptacle;
import artifactmod.client.ClientProxy;
import artifactmod.item.ItemArtifact;
import artifactmod.item.ItemArtifactPick;
import artifactmod.item.ItemBlockDecorative;
import artifactmod.item.ItemDiviningStone;
import artifactmod.item.ItemMisc;
import artifactmod.ref.RefStrings;
import artifactmod.tileentity.TileEntityArtifactCase;
import artifactmod.tileentity.TileEntityOrichalcumReceptacle;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

// Identify this class as a Minecraft Forge Mod
@Mod(
        modid = RefStrings.MOD_ID,
        name = RefStrings.MOD_NAME,
        version = RefStrings.MOD_VERSION)

// Declare client/server relationship rules
@NetworkMod(
		clientSideRequired = true,	// If the server has the mod, the client MUST have it
		serverSideRequired = false)	// If the server doesn't have the mod, the client CAN have it
public class ArtifactMod {
	
	// Initialize proxies, used for custom rendering
	@SidedProxy(clientSide = "artifactmod.client.ClientProxy", serverSide = "artifactmod.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block blockStoneDecorative,
		blockArtifactCase,
		blockOrichalcumReceptacle;
	public static int blockStoneDecorative_id,
		blockArtifactCase_id,
		blockOrichalcumReceptacle_id;
	
	public static Item itemArtifact,
		itemDiviningStone,
		itemMisc,
		itemArtifactPick;
	public static int itemArtifact_id,
		itemDiviningStone_id,
		itemMisc_id,
		itemArtifactPick_id;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	// Read config, create and register items/blocks
    	
    	// Load configuration file
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	
    	// When initializing items, typically 256 is added to the ID; we want to tell the user this is not the case for us.
    	config.addCustomCategoryComment("item", "Item IDs are as shown, do not add 256.");
    	
    	// Get block IDs
    	blockStoneDecorative_id = config.getBlock("blockStoneDecorative", 3550).getInt();
    	blockArtifactCase_id = config.getBlock("blockArtifactCase", 3551).getInt();
    	blockOrichalcumReceptacle_id = config.getBlock("blockOrichalcumReceptacle", 3552).getInt();
    	
    	// Get item IDs
    	itemArtifact_id = config.getItem("itemArtifact", 13350).getInt();
    	itemDiviningStone_id = config.getItem("itemDiviningStone", 13551).getInt();
    	itemMisc_id = config.getItem("itemMisc", 13552).getInt();
    	itemArtifactPick_id = config.getItem("itemArtifactPick", 13553).getInt();
    	
    	// Initialize Blocks
    	blockStoneDecorative = new BlockDecorative(blockStoneDecorative_id, Material.rock).setUnlocalizedName("blockStoneDecorative").setHardness(2.0F).setResistance(10.0F);
    	blockArtifactCase = new BlockArtifactCase(blockArtifactCase_id, Material.glass).setUnlocalizedName("blockArtifactCase");
    	blockOrichalcumReceptacle = new BlockOrichalcumReceptacle(blockOrichalcumReceptacle_id, Material.rock).setUnlocalizedName("blockOrichalcumReceptacle");
    	
    	// Initialize Items
    	itemArtifact = new ItemArtifact(itemArtifact_id - 256).setUnlocalizedName("itemArtifact");
    	itemDiviningStone = new ItemDiviningStone(itemDiviningStone_id - 256).setUnlocalizedName("itemDiviningStone");
    	itemMisc = new ItemMisc(itemMisc_id - 256).setUnlocalizedName("itemMisc");
    	itemArtifactPick = new ItemArtifactPick(itemArtifactPick_id - 256).setUnlocalizedName("itemArtifactPick");
    	
    	// Save config, usually only necessary on mod's first run.
    	config.save();
    	
    	// Register blocks with the game
    	GameRegistry.registerBlock(blockStoneDecorative, ItemBlockDecorative.class, RefStrings.MOD_ID + (blockStoneDecorative.getUnlocalizedName().substring(5)));
    	GameRegistry.registerBlock(blockArtifactCase, RefStrings.MOD_ID + (blockArtifactCase.getUnlocalizedName().substring(5)));
    	GameRegistry.registerBlock(blockOrichalcumReceptacle, RefStrings.MOD_ID + (blockOrichalcumReceptacle.getUnlocalizedName().substring(5)));
    	
    	// Register tile entities
    	GameRegistry.registerTileEntity(TileEntityArtifactCase.class, RefStrings.TE_ARTIFACTCASE_ID);
    	GameRegistry.registerTileEntity(TileEntityOrichalcumReceptacle.class, RefStrings.TE_ORICHALCUMRECEPTACLE_ID);
    	
    	// Register recipes
    	ItemStack recipe_keyFragment = new ItemStack(itemMisc, 1, ItemMisc.KEYFRAGMENT);
    	GameRegistry.addShapelessRecipe(new ItemStack(itemMisc, 1, ItemMisc.KEY),
    			recipe_keyFragment, recipe_keyFragment, recipe_keyFragment, recipe_keyFragment);
    	
    	GameRegistry.addRecipe(new ItemStack(itemDiviningStone, 1),
    			"LDL",
    			"DGD",
    			"LDL",
    			'L', Block.blockLapis, 'D', Item.diamond, 'G', Block.glowStone);
    	GameRegistry.addRecipe(new ItemStack(itemMisc, 1, ItemMisc.FOCUSINGCRYSTAL),
    			" O ",
    			"ODO",
    			" O ",
    			'O', (new ItemStack(itemMisc, 1, ItemMisc.ORICHALCUM)), 'D', Item.diamond);
    	GameRegistry.addRecipe(new ItemStack(blockOrichalcumReceptacle, 1, 0),
    			"SCS",
    			"ROR",
    			"SOS",
    			'O', (new ItemStack(itemMisc, 1, ItemMisc.ORICHALCUM)),
    			'C', (new ItemStack(itemMisc, 1, ItemMisc.FOCUSINGCRYSTAL)),
    			'R', Item.redstone,
    			'S', (new ItemStack(blockStoneDecorative, 1, 8)));
    	
    	// Add in-game names for Blocks
    	// @TODO Organize all names in reference file
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 0), RefStrings.BLOCK_STONE_DECORATIVE_BRICK);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 1), RefStrings.BLOCK_STONE_DECORATIVE_BRICK);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 2), RefStrings.BLOCK_STONE_DECORATIVE_BRICK);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 3), RefStrings.BLOCK_STONE_DECORATIVE_BRICK);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 4), RefStrings.BLOCK_STONE_DECORATIVE_PATTERNMOSS);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 5), RefStrings.BLOCK_STONE_DECORATIVE_PATTERNMOSS);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 6), RefStrings.BLOCK_STONE_DECORATIVE_PATTERN);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 7), RefStrings.BLOCK_STONE_DECORATIVE_PATTERN);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 8), RefStrings.BLOCK_STONE_DECORATIVE_DARK);
    	LanguageRegistry.addName(new ItemStack(blockStoneDecorative, 1, 9), RefStrings.BLOCK_STONE_DECORATIVE_DARK);
    	LanguageRegistry.addName(blockArtifactCase, RefStrings.BLOCK_ARTIFACTCASE);
    	
    	// Add in-game names for Items
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 0), RefStrings.ITEM_ARTIFACT_WHITE);
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 1), RefStrings.ITEM_ARTIFACT_BLACK);
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 2), RefStrings.ITEM_ARTIFACT_GOLD);
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 3), RefStrings.ITEM_ARTIFACT_RED);
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 4), RefStrings.ITEM_ARTIFACT_GREEN);
    	LanguageRegistry.addName(new ItemStack(itemArtifact, 1, 5), RefStrings.ITEM_ARTIFACT_BLUE);
    	LanguageRegistry.addName(itemDiviningStone, RefStrings.ITEM_DIVININGSTONE);
    	LanguageRegistry.addName(new ItemStack(itemMisc, 1, 0), RefStrings.ITEM_ORICHALCUM);
    	LanguageRegistry.addName(new ItemStack(itemMisc, 1, 1), RefStrings.ITEM_FOCUSINGCRYSTAL);
    	LanguageRegistry.addName(new ItemStack(itemMisc, 1, 2), RefStrings.ITEM_KEYFRAGMENT);
    	LanguageRegistry.addName(new ItemStack(itemMisc, 1, 3), RefStrings.ITEM_KEY);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	// Create recipes, build data structures
    	
    	// Initialize custom renderers for special blocks
    	ClientProxy.setCustomRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	// Handle interaction with other mods
    }
}
