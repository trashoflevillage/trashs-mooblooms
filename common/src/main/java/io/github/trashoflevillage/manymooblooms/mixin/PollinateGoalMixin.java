package io.github.trashoflevillage.manymooblooms.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.manymooblooms.entity.custom.util.data.BeeData;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(targets = "net.minecraft.entity.passive.BeeEntity$PollinateGoal")
public abstract class PollinateGoalMixin {
    // The bee that this goal belongs to.
    @Shadow
    @Final
    BeeEntity field_20377;

    @Shadow @Nullable
    private Vec3d nextTarget;

    @ModifyReturnValue(method = "getFlower", at = @At("TAIL"))
    private Optional<BlockPos> findFlower(Optional<BlockPos> original) {
        boolean returnOriginal = field_20377 == null;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        if (field_20377 != null) {
            World world = field_20377.getWorld();
            List<MoobloomEntity> possibleEntities =
                    world.getEntitiesByClass(MoobloomEntity.class, Box.of(field_20377.getPos(), 5, 5, 5), EntityPredicates.VALID_ENTITY);
            MoobloomEntity closestEntity = null;
            if (world instanceof ServerWorld serverWorld) {
                closestEntity =
                        serverWorld.getClosestEntity(
                                possibleEntities,
                                TargetPredicate.DEFAULT,
                                field_20377,
                                field_20377.getX(), field_20377.getY(), field_20377.getZ());
            }
            if (closestEntity == null) returnOriginal = true;

            if (!returnOriginal && field_20377.distanceTo(closestEntity) <= 8) {
                mutable.set(closestEntity.getBlockPos());
                BeeData.getBeeData(field_20377.getUuid()).setTargetMoobloom(closestEntity);
            } else returnOriginal = true;

            if (returnOriginal) {
                BeeData.getBeeData(field_20377.getUuid()).setTargetMoobloom(null);
                return original;
            }
            return Optional.of(mutable);
        }
        return original;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        BeeData data = BeeData.getBeeData(field_20377.getUuid());
        if (data.getTargetMoobloom() != null) {
            field_20377.setFlowerPos(field_20377.getFlowerPos());
        }
    }
}
