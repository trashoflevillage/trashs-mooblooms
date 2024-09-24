package io.github.trashoflevillage.mooblooms.mixin;

import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.items.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.AdvancementCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity {
	private long lastMooshroomStewMilk = -1;

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}


	@Inject(at = @At("HEAD"), method = "interact")
	private void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		if (entity.getType() == EntityType.MOOSHROOM && getStackInHand(hand).itemMatches(Items.BOWL.getRegistryEntry())) {
			lastMooshroomStewMilk = getEntityWorld().getTime();
		}

		if (entity.getType() == ModEntities.MOOBLOOM && getStackInHand(hand).itemMatches(Items.MUSHROOM_STEW.getRegistryEntry())) {
			if (lastMooshroomStewMilk - getEntityWorld().getTime() < 600) {
				PlayerEntity player = this.getEntityWorld().getPlayerByUuid(this.uuid);
				if (player instanceof ServerPlayerEntity) {
					(ServerPlayerEntity)((ServerPlayerEntity) player)
							.getAdvancementTracker()
							.grantCriterion(

							)
				}
			}
		}
	}

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return null;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return null;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {}

	@Override
	public Arm getMainArm() {
		return null;
	}
}