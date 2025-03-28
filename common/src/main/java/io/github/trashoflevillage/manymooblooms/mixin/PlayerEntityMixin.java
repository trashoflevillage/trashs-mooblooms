package io.github.trashoflevillage.manymooblooms.mixin;


import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.manymooblooms.entity.custom.util.data.PlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Unique
    private static HashMap<UUID, PlayerData> playerData = new HashMap<>();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "interact", at = @At("HEAD"))
    private void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!(entity instanceof PassiveEntity) || ((PassiveEntity) entity).isBaby()) return;

        if (entity.getType() == EntityType.MOOSHROOM && getStackInHand(hand).isOf(Items.BOWL)) {
            getPlayerData().lastMooshroomStewMilk = getEntityWorld().getTime();
        }

        if (entity instanceof MoobloomEntity && getStackInHand(hand).isOf(Items.MUSHROOM_STEW)) {
            System.out.println("RUN!!!!");
            if (getEntityWorld().getTime() - getPlayerData().lastMooshroomStewMilk < 600 && getPlayerData().lastMooshroomStewMilk != -1) {
                PlayerEntity player = this.getEntityWorld().getPlayerByUuid(this.uuid);
                if (player instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayer = ((ServerPlayerEntity) player);
                    serverPlayer
                            .getAdvancementTracker()
                            .grantCriterion(
                                    serverPlayer.server.getAdvancementLoader().get(
                                            Identifier.of(ManyMooblooms.MOD_ID, "suspicious_brewery")
                                    ), "milk_mooshroom_then_moobloom_for_stew"
                            );
                }
            }
        }
    }

    @Unique
    public PlayerData getPlayerData() {
        if (!playerData.containsKey(this.uuid)) playerData.put(this.uuid, new PlayerData());
        return playerData.get(this.uuid);
    }
}
