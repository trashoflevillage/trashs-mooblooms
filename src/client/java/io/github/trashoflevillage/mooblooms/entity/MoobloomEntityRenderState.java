package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class MoobloomEntityRenderState extends LivingEntityRenderState {
    public MoobloomEntity.Type type;
    public boolean sheared;

    public MoobloomEntityRenderState() {
        type = MoobloomEntity.Type.YELLOW;
        sheared = false;
    }
}
