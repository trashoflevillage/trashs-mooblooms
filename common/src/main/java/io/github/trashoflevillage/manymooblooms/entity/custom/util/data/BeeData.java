package io.github.trashoflevillage.manymooblooms.entity.custom.util.data;

import io.github.trashoflevillage.manymooblooms.entity.custom.MoobloomEntity;

import java.util.HashMap;
import java.util.UUID;

public class BeeData {
    private static final HashMap<UUID, BeeData> beeData = new HashMap<>();
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