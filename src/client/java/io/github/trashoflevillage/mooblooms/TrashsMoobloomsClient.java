package io.github.trashoflevillage.mooblooms;

import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.ModModelLayers;
import io.github.trashoflevillage.mooblooms.entity.MoobloomRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;

public class TrashsMoobloomsClient implements ClientModInitializer {
	private static final Block[] blocksWithTransparency = new Block[] {
			ModBlocks.BUTTERCUP, ModBlocks.POTTED_BUTTERCUP,
			ModBlocks.HIBISCUS, ModBlocks.POTTED_HIBISCUS,
			ModBlocks.GLADIOLI, ModBlocks.POTTED_GLADIOLI,
			ModBlocks.DAYFLOWER, ModBlocks.POTTED_DAYFLOWER,
			ModBlocks.MYOSOTIS, ModBlocks.POTTED_MYOSOTIS,
			ModBlocks.CENTIAN, ModBlocks.POTTED_CENTIAN,
			ModBlocks.TRILLIUM, ModBlocks.POTTED_TRILLIUM,
			ModBlocks.WITHERED_BUTTERCUP, ModBlocks.POTTED_WITHERED_BUTTERCUP,
			ModBlocks.THUNDERBLOOM, ModBlocks.POTTED_THUNDERBLOOM,
			ModBlocks.BELLFLOWER, ModBlocks.POTTED_BELLFLOWER,
			ModBlocks.BOAT_ORCHID, ModBlocks.POTTED_BOAT_ORCHID,
			ModBlocks.BUTTERFLY_CANDY, ModBlocks.POTTED_BUTTERFLY_CANDY,
			ModBlocks.BUTTERFLY_WEED, ModBlocks.POTTED_BUTTERFLY_WEED,
			ModBlocks.CONBUSH, ModBlocks.POTTED_CONBUSH,
			ModBlocks.MORNING_GLORY, ModBlocks.POTTED_MORNING_GLORY,
			ModBlocks.SILVER_IRIS, ModBlocks.POTTED_SILVER_IRIS
	};

	@Override
	public void onInitializeClient() {
		for (Block i : blocksWithTransparency)
			BlockRenderLayerMap.INSTANCE.putBlock(i, RenderLayer.getCutout());

		EntityRendererRegistry.register(ModEntities.MOOBLOOM, MoobloomRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MOOBLOOM, CowEntityModel::getTexturedModelData);
	}
}