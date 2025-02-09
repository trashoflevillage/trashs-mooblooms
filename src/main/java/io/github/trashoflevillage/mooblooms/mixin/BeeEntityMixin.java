package io.github.trashoflevillage.mooblooms.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.mooblooms.data.BeeData;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin {
    @Mixin(targets = "net.minecraft.entity.passive.BeeEntity$ValidateFlowerGoal")
    public abstract static class ValidateFlowerGoalMixin {
        @Shadow @Final BeeEntity field_52460;
        @ModifyReturnValue(method = "isFlower", at = @At("TAIL"))
        private boolean isFlower(boolean original) {
            if (!original) {
                World world = field_52460.getWorld();
                List<MoobloomEntity> possibleEntities =
                        world.getEntitiesByClass(MoobloomEntity.class, Box.of(field_52460.getPos(), 5, 5, 5), EntityPredicates.VALID_ENTITY);
                MoobloomEntity closestEntity = null;
                if (world instanceof ServerWorld serverWorld) {
                    closestEntity =
                            serverWorld.getClosestEntity(
                                    possibleEntities,
                                    TargetPredicate.DEFAULT,
                                    (BeeEntity) world.getEntityById(field_52460.getId()),
                                    field_52460.getX(), field_52460.getY(), field_52460.getZ());
                }
                if (closestEntity != null) return true;
            }
            return original;
        }
    }

    @ModifyReturnValue(method = "getFlowerPos", at = @At("TAIL"))
    private BlockPos getFlowerPos(BlockPos original) {
        BeeData beeData = getBeeData();
        if (beeData.getTargetMoobloom() != null) {
            return beeData.getTargetMoobloom().getBlockPos();
        }
        return original;
    }

    public BeeData getBeeData() {
        return BeeData.getBeeData(((BeeEntity)(Object)this).getUuid());
    }
}
