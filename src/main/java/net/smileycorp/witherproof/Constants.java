package net.smileycorp.witherproof;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Constants {
    
    public static final String NAME = "Witherproof";
    public static final String MODID = "witherproof";
    public static final String VERSION = "1.0.0";
    
    public static final String WITHERPROOF = "witherproof";
    
    public static final String DRAGON_PROOF = "dragonProof";
    
    public static boolean hasOreDictionary(IBlockState state, String oreDict) {
        int id = OreDictionary.getOreID(oreDict);
        try {
            for (int i : OreDictionary.getOreIDs(new ItemStack(state.getBlock(), 1, state.getBlock().damageDropped(state))))
                if (i == id) return true;
        } catch (Exception e) {}
        return false;
    }
    
}
