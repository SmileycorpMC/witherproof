package net.smileycorp.witherproof.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.smileycorp.witherproof.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {
    
    @Inject(at=@At("HEAD"), method = "canExplosionDestroyBlock", cancellable = true)
    public void witherproof$canExplosionDestroyBlock(Explosion explosion, World world, BlockPos pos, IBlockState state, float power, CallbackInfoReturnable<Boolean> callback) {
        if (!((Object)this instanceof EntityWither || (Object)this instanceof EntityWitherSkull)) return;
        callback.setReturnValue(!Constants.hasOreDictionary(state, Constants.WITHERPROOF));
    }
    
}
