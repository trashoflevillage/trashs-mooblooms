package io.github.trashoflevillage.manymooblooms;

import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import io.github.trashoflevillage.manymooblooms.block.ModBlocks;
import io.github.trashoflevillage.manymooblooms.entity.ModEntities;
import io.github.trashoflevillage.manymooblooms.item.ModItemGroups;
import io.github.trashoflevillage.manymooblooms.item.ModItems;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ManyMooblooms {
    public static final String MOD_ID = "manymooblooms";
    public static final String OLD_MOD_ID = "trashs_mooblooms";

    public static void init() {
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModEntities.registerAlL();
        ModItemGroups.registerAll();
    }
}
