package io.github.xiewuzhiying.ocvs.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import li.cil.oc2r.common.network.MessageUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

@Mixin(MessageUtils.class)
public abstract class MixinMessageUtils {
    @WrapOperation(
            method = "withNearbyServerBlockEntityForInteraction",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/BlockPos;closerToCenterThan(Lnet/minecraft/core/Position;D)Z"
            ),
            require = 0,
            remap = false
    )
    private static boolean replace(BlockPos blockPos, Position position, double v, Operation<Boolean> original, @Local ServerPlayer player) {
        return VSGameUtilsKt.squaredDistanceBetweenInclShips(player.level(), blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, position.x(), position.y(), position.z()) < Mth.square(v);
    }
}
