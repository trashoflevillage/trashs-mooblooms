package io.github.trashoflevillage.mooblooms;

import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.ModModelLayers;
import io.github.trashoflevillage.mooblooms.entity.MoobloomRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;

public class TrashsMoobloomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.MOOBLOOM, MoobloomRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MOOBLOOM, CowEntityModel::getTexturedModelData);
	}
}