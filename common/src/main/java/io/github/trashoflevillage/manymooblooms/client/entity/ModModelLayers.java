package io.github.trashoflevillage.manymooblooms.client.entity;

import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MOOBLOOM = registerMain("moobloom");

    private static EntityModelLayer registerMain(String id) {
        return new EntityModelLayer(Identifier.of(ManyMooblooms.MOD_ID, id), "main");
    }
}