package io.github.trashoflevillage.mooblooms.entity;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MOOBLOOM = registerMain("moobloom");

    private static EntityModelLayer registerMain(String id) {
        return new EntityModelLayer(Identifier.of(TrashsMooblooms.MOD_ID, id), "main");
    }
}