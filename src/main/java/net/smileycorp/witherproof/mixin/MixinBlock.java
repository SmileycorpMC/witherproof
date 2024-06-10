package net.smileycorp.witherproof.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.smileycorp.witherproof.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {
    
    @Inject(at = @At("HEAD"), method = "canEntityDestroy", cancellable = true, remap = false)
    private void witherproof$canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity, CallbackInfoReturnable<Boolean> callback) {
        if (entity instanceof EntityDragon) callback.setReturnValue(!Constants.hasOreDictionary(state, Constants.DRAGON_PROOF));
        else if (entity instanceof EntityWither || entity instanceof EntityWitherSkull)
            callback.setReturnValue(!Constants.hasOreDictionary(state, Constants.WITHERPROOF));
    }
    
}
