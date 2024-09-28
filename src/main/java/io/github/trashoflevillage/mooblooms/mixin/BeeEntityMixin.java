package io.github.trashoflevillage.mooblooms.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.trashoflevillage.mooblooms.data.BeeData;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {
    protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "isFlowers", at = @At("TAIL"))
    private boolean isFlowers(boolean original) {
        if (!original) {
            World world = getWorld();
            List<MoobloomEntity> possibleEntities =
                    world.getEntitiesByClass(MoobloomEntity.class, Box.of(this.getPos(), 5, 5, 5), EntityPredicates.VALID_ENTITY);
            MoobloomEntity closestEntity =
                    world.getClosestEntity(
                            possibleEntities,
                            TargetPredicate.DEFAULT,
                            (BeeEntity)world.getEntityById(getId()),
                            this.getX(), this.getY(), this.getZ());
            if (closestEntity != null) return true;
        }
        return original;
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
        return BeeData.getBeeData(this.uuid);
    }
}
