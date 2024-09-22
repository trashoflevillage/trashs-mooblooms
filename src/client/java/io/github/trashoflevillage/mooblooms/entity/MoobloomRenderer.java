package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.MooshroomEntityRenderer;
import net.minecraft.client.render.entity.feature.MooshroomMushroomFeatureRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.Identifier;

public class MoobloomRenderer extends MobEntityRenderer<MoobloomEntity, CowEntityModel<MoobloomEntity>> {
    public MoobloomRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(ModModelLayers.MOOBLOOM)), 0.7f);
        this.addFeature(new MoobloomFlowerFeatureRenderer<>(this, context.getBlockRenderManager()));
    }

    @Override
    public Identifier getTexture(MoobloomEntity entity) {
        return Identifier.of(TrashsMooblooms.MOD_ID, "textures/entity/moobloom_" + entity.getVariant().name().toLowerCase() + ".png");
    }
}
