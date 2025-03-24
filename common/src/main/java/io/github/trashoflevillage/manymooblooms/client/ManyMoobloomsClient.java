package io.github.trashoflevillage.manymooblooms.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import io.github.trashoflevillage.manymooblooms.client.entity.ModModelLayers;
import io.github.trashoflevillage.manymooblooms.client.entity.MoobloomEntityRenderer;
import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import net.minecraft.client.render.entity.model.CowEntityModel;

public class ManyMoobloomsClient {
    public static void init() {
        EntityRendererRegistry.register(ModEntities.MOOBLOOM, MoobloomEntityRenderer::new);
        EntityModelLayerRegistry.register(ModModelLayers.MOOBLOOM, CowEntityModel::getTexturedModelData);
    }
}
