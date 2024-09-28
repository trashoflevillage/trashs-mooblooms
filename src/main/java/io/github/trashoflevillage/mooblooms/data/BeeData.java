package io.github.trashoflevillage.mooblooms.data;

import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;

import java.util.HashMap;
import java.util.UUID;

public class BeeData {
    private static HashMap<UUID, BeeData> beeData = new HashMap<>();
    private MoobloomEntity targetMoobloom = null;

    public static BeeData getBeeData(UUID uuid) {
        if (!beeData.containsKey(uuid)) beeData.put(uuid, new BeeData());
        return beeData.get(uuid);
    }

    public void setTargetMoobloom(MoobloomEntity targetMoobloom) {
        this.targetMoobloom = targetMoobloom;
    }

    public MoobloomEntity getTargetMoobloom() {
        return targetMoobloom;
    }
}
