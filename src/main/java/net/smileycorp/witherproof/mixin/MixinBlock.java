package net.smileycorp.witherproof.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;
import net.smileycorp.witherproof.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {
    
    @Inject(at = @At("HEAD"), method = "canEntityDestroy", cancellable = true)
    private void witherproof$canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity, CallbackInfoReturnable<Boolean> callback) {
        if (entity instanceof EntityDragon) callback.setReturnValue(hasOreDictionary(state, Constants.DRAGON_PROOF));
        else if (entity instanceof EntityWither || entity instanceof EntityWitherSkull)
            callback.setReturnValue(hasOreDictionary(state, Constants.WITHERPROOF));
    }
    
    private boolean hasOreDictionary(IBlockState state, String oreDict) {
        int id = OreDictionary.getOreID(oreDict);
        for (int i : OreDictionary.getOreIDs(new ItemStack(state.getBlock(), state.getBlock().damageDropped(state))))
            if (i == id) return true;
        return false;
    }
    
}
