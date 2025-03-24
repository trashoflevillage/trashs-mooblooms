package io.github.trashoflevillage.manymooblooms;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import io.github.trashoflevillage.manymooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import net.minecraft.entity.passive.CowEntity;

public final class ManyMooblooms {
    public static final String MOD_ID = "manymooblooms";
    public static final String OLD_MOD_ID = "trashs_mooblooms";

    public static void init() {
        ModBlocks.registerAll();
        ModEntities.registerAlL();
        EntityAttributeRegistry.register(ModEntities.MOOBLOOM, CowEntity::createCowAttributes);
    }
}
