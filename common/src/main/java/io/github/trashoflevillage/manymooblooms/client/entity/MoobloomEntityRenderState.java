package io.github.trashoflevillage.manymooblooms.client.entity;

import io.github.trashoflevillage.manymooblooms.entity.custom.util.MoobloomType;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class MoobloomEntityRenderState extends LivingEntityRenderState {
    public MoobloomType type;
    public boolean sheared;

    public MoobloomEntityRenderState() {
        type = MoobloomType.YELLOW;
        sheared = false;
    }
}