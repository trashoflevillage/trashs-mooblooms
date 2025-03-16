package io.github.trashoflevillage.mooblooms.entity.custom.util;

import io.github.trashoflevillage.mooblooms.ManyMooblooms;
import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MoobloomType {
    private static final HashMap<Identifier, MoobloomType> registeredTypes = new HashMap<>();

    public static final MoobloomType WHITE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "white"), new MoobloomType(ModBlocks.THUNDERBLOOM.getDefaultState(), Items.WHITE_DYE));
    public static final MoobloomType LIGHT_GRAY =
            register(Identifier.of(ManyMooblooms.MOD_ID, "light_gray"), new MoobloomType(ModBlocks.BELLFLOWER.getDefaultState(), Items.LIGHT_GRAY_DYE));
    public static final MoobloomType GRAY =
            register(Identifier.of(ManyMooblooms.MOD_ID, "gray"), new MoobloomType(ModBlocks.SILVER_IRIS.getDefaultState(), Items.GRAY_DYE));
    public static final MoobloomType BLACK =
            register(Identifier.of(ManyMooblooms.MOD_ID, "black"), new MoobloomType(ModBlocks.WITHERED_BUTTERCUP.getDefaultState(), Items.BLACK_DYE));
    public static final MoobloomType BROWN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "brown"), new MoobloomType(ModBlocks.BOAT_ORCHID.getDefaultState(), Items.BROWN_DYE));
    public static final MoobloomType RED =
            register(Identifier.of(ManyMooblooms.MOD_ID, "red"), new MoobloomType(ModBlocks.TRILLIUM.getDefaultState(), Items.RED_DYE));
    public static final MoobloomType ORANGE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "orange"), new MoobloomType(ModBlocks.BUTTERFLY_WEED.getDefaultState(), Items.ORANGE_DYE));
    public static final MoobloomType YELLOW =
            register(Identifier.of(ManyMooblooms.MOD_ID, "yellow"), new MoobloomType(ModBlocks.BUTTERCUP.getDefaultState(), Items.YELLOW_DYE));
    public static final MoobloomType LIME =
            register(Identifier.of(ManyMooblooms.MOD_ID, "lime"), new MoobloomType(ModBlocks.CONBUSH.getDefaultState(), Items.LIME_DYE));
    public static final MoobloomType GREEN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "green"), new MoobloomType(ModBlocks.GLADIOLI.getDefaultState(), Items.GREEN_DYE));
    public static final MoobloomType CYAN =
            register(Identifier.of(ManyMooblooms.MOD_ID, "cyan"), new MoobloomType(ModBlocks.CENTIAN.getDefaultState(), Items.CYAN_DYE));
    public static final MoobloomType LIGHT_BLUE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "light_blue"), new MoobloomType(ModBlocks.MYOSOTIS.getDefaultState(), Items.LIGHT_BLUE_DYE));
    public static final MoobloomType BLUE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "blue"), new MoobloomType(ModBlocks.DAYFLOWER.getDefaultState(), Items.BLUE_DYE));
    public static final MoobloomType PURPLE =
            register(Identifier.of(ManyMooblooms.MOD_ID, "purple"), new MoobloomType(ModBlocks.MORNING_GLORY.getDefaultState(), Items.PURPLE_DYE));
    public static final MoobloomType MAGENTA =
            register(Identifier.of(ManyMooblooms.MOD_ID, "magenta"), new MoobloomType(ModBlocks.BUTTERFLY_CANDY.getDefaultState(), Items.MAGENTA_DYE));
    public static final MoobloomType PINK =
            register(Identifier.of(ManyMooblooms.MOD_ID, "pink"), new MoobloomType(ModBlocks.HIBISCUS.getDefaultState(), Items.PINK_DYE));

    private final BlockState flowerState;
    private final Item dyeItem;

    public MoobloomType(BlockState flowerState, Item dyeItem) {
        this.flowerState = flowerState;
        this.dyeItem = dyeItem;
    }

    public BlockState getFlowerState() {
        return flowerState;
    }

    public Item getDyeItem() {
        return dyeItem;
    }

    public Item getItemFromShearing() {
        return flowerState.getBlock().asItem();
    }

    public static MoobloomType register(Identifier id, MoobloomType value) {
        registeredTypes.put(id, value);
        return value;
    }

    public Identifier getId() {
        if (registeredTypes.containsValue(this)) {
            for (Map.Entry<Identifier, MoobloomType> i : registeredTypes.entrySet()) {
                if (i.getValue() == this) return i.getKey();
            }
        }
        return null;
    }

    public static MoobloomType get(Identifier id) {
        return registeredTypes.get(id);
    }

    public static void registerAll() {}
}