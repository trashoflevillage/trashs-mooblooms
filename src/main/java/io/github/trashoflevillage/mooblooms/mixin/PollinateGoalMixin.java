package io.github.trashoflevillage.mooblooms.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.mooblooms.data.BeeData;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Mixin(targets = "net.minecraft.entity.passive.BeeEntity$PollinateGoal")
public abstract class PollinateGoalMixin {
    // The bee that this goal belongs to.
    @Shadow @Final private BeeEntity field_20377;

    @Shadow @Nullable private Vec3d nextTarget;

    @ModifyReturnValue(method = "getFlower", at = @At("TAIL"))
    private Optional<BlockPos> findFlower(Optional<BlockPos> original) {
        boolean returnOriginal = false;
        if (field_20377 == null) returnOriginal = true;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        World world = field_20377.getWorld();
        List<MoobloomEntity> possibleEntities =
                world.getEntitiesByClass(MoobloomEntity.class, Box.of(field_20377.getPos(), 5, 5, 5), EntityPredicates.VALID_ENTITY);
        MoobloomEntity closestEntity =
                world.getClosestEntity(
                        possibleEntities,
                        TargetPredicate.DEFAULT,
                        field_20377,
                        field_20377.getX(), field_20377.getY(), field_20377.getZ());
        if (closestEntity == null) returnOriginal = true;

        if (!returnOriginal && field_20377.distanceTo(closestEntity) <= 8) {
            mutable.set(closestEntity.getBlockPos());
            BeeData.getBeeData(field_20377.getUuid()).setTargetMoobloom(closestEntity);
            System.out.println("moobloom targeted by " + field_20377.getUuid());
        }
        else returnOriginal = true;

        if (returnOriginal) {
            BeeData.getBeeData(field_20377.getUuid()).setTargetMoobloom(null);
            return original;
        }

        return Optional.of(mutable);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        BeeData data = BeeData.getBeeData(field_20377.getUuid());
        if (data.getTargetMoobloom() != null) {
            BlockPos pos = data.getTargetMoobloom().getBlockPos();
            field_20377.setFlowerPos(field_20377.getFlowerPos());
            //this.nextTarget = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        }
    }
}
