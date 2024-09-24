package io.github.trashoflevillage.mooblooms.blocks;

import io.github.trashoflevillage.mooblooms.TrashsMooblooms;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BUTTERCUP = registerBlock("buttercup", new FlowerBlock(StatusEffects.POISON, 10, AbstractBlock.Settings.copy(Blocks.DANDELION)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,
                Identifier.of(TrashsMooblooms.MOD_ID, name),
                block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(TrashsMooblooms.MOD_ID, name),
                new BlockItem(block,
                        new Item.Settings()));
    }

    public static void registerModBlocks() {
        TrashsMooblooms.LOGGER.info("Registering blocks for " + TrashsMooblooms.MOD_ID);
    }
}
