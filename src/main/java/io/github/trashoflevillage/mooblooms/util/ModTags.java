package io.github.trashoflevillage.mooblooms.util;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MOOBLOOM_SPAWNABLE_ON = createTag("moobloom_spawnable_on");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TrashsMooblooms.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TrashsMooblooms.MOD_ID, name));
        }
    }
}
