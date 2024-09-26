package io.github.trashoflevillage.mooblooms.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(targets = "net.minecraft.entity.passive.BeeEntity.PollinateGoal")
public abstract class PollinateGoalMixin {
    @ModifyReturnValue(method = "findFlower", at = @At("HEAD"))
    private Optional<BlockPos> findFlower(Optional<BlockPos> original, Predicate<BlockState> predicate, double searchDistance) {

        return original;
    }
}
