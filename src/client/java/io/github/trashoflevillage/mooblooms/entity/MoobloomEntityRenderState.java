package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.mooblooms.entity.custom.util.MoobloomType;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class MoobloomEntityRenderState extends LivingEntityRenderState {
    public MoobloomType type;
    public boolean sheared;

    public MoobloomEntityRenderState() {
        type = MoobloomType.YELLOW;
        sheared = false;
    }
}
