package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class MoobloomFlowerFeatureRenderer<T extends MoobloomEntity> extends FeatureRenderer<T, CowEntityModel<T>> {
    private final BlockRenderManager blockRenderManager;

    public MoobloomFlowerFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> context, BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T moobloomEntity, float f, float g, float h, float j, float k, float l) {
        final float FLOWER_SCALE = getFlowerScale(moobloomEntity.getVariant());
        final float FLOWER_SHIFT = getFlowerShift(FLOWER_SCALE);
        if (!moobloomEntity.isBaby() && !moobloomEntity.isSheared()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(moobloomEntity) && moobloomEntity.isInvisible();
            if (!moobloomEntity.isInvisible() || bl) {
                BlockState blockState = moobloomEntity.getVariant().getFlowerState();
                int m = LivingEntityRenderer.getOverlay(moobloomEntity, 0.0F);
                BakedModel bakedModel = this.blockRenderManager.getModel(blockState);
                matrixStack.push();
                matrixStack.translate(0.2F, -0.35F * FLOWER_SCALE, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F * FLOWER_SCALE, -1.0F * FLOWER_SCALE, 1.0F * FLOWER_SCALE);
                matrixStack.translate(-0.5F, -0.5F + FLOWER_SHIFT, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                matrixStack.translate(0.2F, -0.35F * FLOWER_SCALE, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0F));
                matrixStack.translate(0.1F, 0.0F, -0.6F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F * FLOWER_SCALE, -1.0F * FLOWER_SCALE, 1.0F * FLOWER_SCALE);
                matrixStack.translate(-0.5F, -0.5F + FLOWER_SHIFT, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
                matrixStack.pop();
                matrixStack.push();
                ((CowEntityModel)this.getContextModel()).getHead().rotate(matrixStack);
                matrixStack.translate(0.0F, -0.7F * FLOWER_SCALE, -0.2F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0F));
                matrixStack.scale(-1.0F * FLOWER_SCALE, -1.0F * FLOWER_SCALE, 1.0F * FLOWER_SCALE);
                matrixStack.translate(-0.5F, -0.5F + FLOWER_SHIFT, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
                matrixStack.pop();
            }
        }
    }

    private float getFlowerScale(MoobloomEntity.MoobloomVariant variant) {
        switch (variant) {
            case MoobloomEntity.MoobloomVariant.YELLOW, MoobloomEntity.MoobloomVariant.GREEN,
                 MoobloomEntity.MoobloomVariant.PINK, MoobloomEntity.MoobloomVariant.BLACK: return 1f;
            default: return 0.6f;
        }
    }

    private float getFlowerShift(float scale) {
        if (scale == 0.75f) return -0.1f;
        if (scale == 0.6f) return -0.1f;
        return 0f;
    }

    private void renderFlower(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            this.blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0F, 0.0F, 0.0F, light, overlay);
        } else {
            this.blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }

    }
}
