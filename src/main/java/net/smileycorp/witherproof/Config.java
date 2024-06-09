package net.smileycorp.witherproof;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class Config {
    
    private static String[] witherproofBlocks = {};
    private static String[] dragonProofBlocks = {};
    
    public static final void init(Configuration config) {
        witherproofBlocks = config.get("general", "witherproofBlocks", new String[] {"minecraft:bedrock", "minecraft:end_portal",
                "minecraft:end_portal_frame", "minecraft:command_block", "minecraft:repeating_command_block", "minecraft:chain_command_block", "minecraft:barrier",
                "minecraft:structure_block", "minecraft:structure_void", "minecraft:piston_extension", "minecraft:end_gateway"
        }, "Blocks that can't be broken by withers.").getStringList();
        dragonProofBlocks = config.get("general", "dragonProofBlocks", new String[] {"minecraft:bedrock", "minecraft:end_portal",
                "minecraft:end_portal_frame", "minecraft:command_block", "minecraft:repeating_command_block", "minecraft:chain_command_block", "minecraft:barrier",
                "minecraft:obsidian", "minecraft:end_stone", "minecraft:iron_bars", "minecraft:end_gateway"
        }, "Blocks that can't be broken by ender dragons.").getStringList();
    }
    
    public static final void load() {
        loadEntries(witherproofBlocks, Constants.WITHERPROOF);
        loadEntries(dragonProofBlocks, Constants.DRAGON_PROOF);
    }
    
    private static final void loadEntries(String[] entries, String ore) {
        for (String entry : entries) {
            try {
                String[] split = entry.split(":");
                ResourceLocation loc = split.length < 2 ? new ResourceLocation(split[0]) : new ResourceLocation(split[0], split[1]);
                Block block = ForgeRegistries.BLOCKS.getValue(loc);
                if (split.length > 2) {
                    try {
                        int meta = Integer.valueOf(split[2]);
                        OreDictionary.registerOre(ore, new ItemStack(block, 1, meta));
                    } catch (Exception e) {}
                }
                OreDictionary.registerOre(ore, new ItemStack(block, OreDictionary.WILDCARD_VALUE));
            } catch (Exception e) {
                Witherproof.logError("failed loading entry " + entry + " for oredict " + ore, e);
            }
        }
    }
    
}
