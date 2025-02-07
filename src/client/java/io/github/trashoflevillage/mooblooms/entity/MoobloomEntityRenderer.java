package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;

public class MoobloomEntityRenderer extends MobEntityRenderer<MoobloomEntity, MoobloomEntityRenderState, CowEntityModel> {
    public MoobloomEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel(context.getPart(ModModelLayers.MOOBLOOM)), 0.7f);
        this.addFeature(new MoobloomEntityFlowerFeatureRenderer<>(this, context.getBlockRenderManager()));
    }

    @Override
    public MoobloomEntityRenderState createRenderState() {
        return new MoobloomEntityRenderState();
    }

    @Override
    public Identifier getTexture(MoobloomEntityRenderState state) {
        return Identifier.of(TrashsMooblooms.MOD_ID, "textures/entity/moobloom_" + state.type.name().toLowerCase() + ".png");
    }

    @Override
    public void updateRenderState(MoobloomEntity entity, MoobloomEntityRenderState state, float f) {
        super.updateRenderState(entity, state, f);
        state.baby = entity.isBaby();
        state.sheared = entity.isSheared();
    }
}
