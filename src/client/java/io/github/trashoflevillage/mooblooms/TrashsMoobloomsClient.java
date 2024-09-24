package io.github.trashoflevillage.mooblooms;

import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.ModModelLayers;
import io.github.trashoflevillage.mooblooms.entity.MoobloomRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;

public class TrashsMoobloomsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUTTERCUP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HIBISCUS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLADIOLI, RenderLayer.getCutout());

		EntityRendererRegistry.register(ModEntities.MOOBLOOM, MoobloomRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MOOBLOOM, CowEntityModel::getTexturedModelData);
	}
}