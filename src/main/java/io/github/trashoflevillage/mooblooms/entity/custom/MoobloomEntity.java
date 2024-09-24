package io.github.trashoflevillage.mooblooms.entity.custom;

import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MoobloomEntity extends CowEntity {
    private static final TrackedData<String> TYPE;
    private static final float SPAWN_CHANCE = 0.1f;

    public MoobloomEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    public MoobloomEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        MoobloomEntity moobloomEntity = ModEntities.MOOBLOOM.create(serverWorld);
        if (moobloomEntity != null) {
            moobloomEntity.setVariant(this.chooseBabyType((MoobloomEntity)passiveEntity));
        }

        return moobloomEntity;
    }

    public static boolean canSpawn(EntityType<MoobloomEntity> entityType, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    private MoobloomVariant chooseBabyType(MoobloomEntity moobloom) {
        MoobloomVariant[] types = new MoobloomVariant[] {
                this.getVariant(),
                moobloom.getVariant()
        };
        return types[this.getWorld().random.nextInt(types.length)];
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult result = ActionResult.FAIL;
        if (tryConvertToSussyStew(player, hand) ||
            tryToDyeItem(player, hand) ||
            super.interactMob(player, hand) == ActionResult.SUCCESS)
                result = ActionResult.SUCCESS;
        return result;
    }


    private boolean tryConvertToSussyStew(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.itemMatches(Items.MUSHROOM_STEW.getRegistryEntry())) {
            SuspiciousStewIngredient s = SuspiciousStewIngredient.of(Item.fromBlock(getVariant().flower.getBlock()));
            ItemStack itemStack2 = new ItemStack(Items.SUSPICIOUS_STEW);
            itemStack2.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, s.getStewEffects());
            player.setStackInHand(hand, itemStack2);
            player.playSound(SoundEvents.ENTITY_MOOSHROOM_SUSPICIOUS_MILK, 1, 1);
            return true;
        }
        return false;
    }

    private boolean tryToDyeItem(PlayerEntity player, Hand hand) {
        HashMap<TagKey<Item>, HashMap<String, Item>> dyedItems = getDyeableItemHashmap();
        ItemStack itemStack = player.getStackInHand(hand);

        for (TagKey<Item> i : dyedItems.keySet()) {
            if (itemStack.isIn(i)) {
                if (dyedItems.get(i).containsKey(getVariant().name)) {
                    ItemStack newItem = itemStack.copyComponentsToNewStack(dyedItems.get(i).get(getVariant().name), itemStack.getCount());
                    if (newItem.itemMatches(itemStack.getRegistryEntry())) return false;
                    player.setStackInHand(player.getActiveHand(), newItem);
                    player.playSound(SoundEvents.ITEM_DYE_USE, 1.0F, 1.0F);

                    return true;
                }
            }
        }
        return false;
    }

    private HashMap<TagKey<Item>, HashMap<String, Item>> getDyeableItemHashmap() {
        HashMap<TagKey<Item>, HashMap<String, Item>> dyedItems = new HashMap<>();

        HashMap<String, Item> entry;
        dyedItems.put(ItemTags.WOOL, new HashMap<>());
        
        entry = dyedItems.get(ItemTags.WOOL);
        entry.put("white", Items.WHITE_WOOL);
        entry.put("light_gray", Items.LIGHT_GRAY_WOOL);
        entry.put("gray", Items.GRAY_WOOL);
        entry.put("black", Items.BLACK_WOOL);
        entry.put("brown", Items.BROWN_WOOL);
        entry.put("red", Items.RED_WOOL);
        entry.put("orange", Items.ORANGE_WOOL);
        entry.put("yellow", Items.YELLOW_WOOL);
        entry.put("lime", Items.LIME_WOOL);
        entry.put("green", Items.GREEN_WOOL);
        entry.put("cyan", Items.CYAN_WOOL);
        entry.put("light_blue", Items.LIGHT_BLUE_WOOL);
        entry.put("blue", Items.BLUE_WOOL);
        entry.put("purple", Items.PURPLE_WOOL);
        entry.put("magenta", Items.MAGENTA_WOOL);
        entry.put("pink", Items.PINK_WOOL);

        dyedItems.put(ItemTags.WOOL_CARPETS, new HashMap<>());
        entry = dyedItems.get(ItemTags.WOOL_CARPETS);
        entry.put("white", Items.WHITE_CARPET);
        entry.put("light_gray", Items.LIGHT_GRAY_CARPET);
        entry.put("gray", Items.GRAY_CARPET);
        entry.put("black", Items.BLACK_CARPET);
        entry.put("brown", Items.BROWN_CARPET);
        entry.put("red", Items.RED_CARPET);
        entry.put("orange", Items.ORANGE_CARPET);
        entry.put("yellow", Items.YELLOW_CARPET);
        entry.put("lime", Items.LIME_CARPET);
        entry.put("green", Items.GREEN_CARPET);
        entry.put("cyan", Items.CYAN_CARPET);
        entry.put("light_blue", Items.LIGHT_BLUE_CARPET);
        entry.put("blue", Items.BLUE_CARPET);
        entry.put("purple", Items.PURPLE_CARPET);
        entry.put("magenta", Items.MAGENTA_CARPET);
        entry.put("pink", Items.PINK_CARPET);

        dyedItems.put(ItemTags.BANNERS, new HashMap<>());
        entry = dyedItems.get(ItemTags.BANNERS);
        entry.put("white", Items.WHITE_BANNER);
        entry.put("light_gray", Items.LIGHT_GRAY_BANNER);
        entry.put("gray", Items.GRAY_BANNER);
        entry.put("black", Items.BLACK_BANNER);
        entry.put("brown", Items.BROWN_BANNER);
        entry.put("red", Items.RED_BANNER);
        entry.put("orange", Items.ORANGE_BANNER);
        entry.put("yellow", Items.YELLOW_BANNER);
        entry.put("lime", Items.LIME_BANNER);
        entry.put("green", Items.GREEN_BANNER);
        entry.put("cyan", Items.CYAN_BANNER);
        entry.put("light_blue", Items.LIGHT_BLUE_BANNER);
        entry.put("blue", Items.BLUE_BANNER);
        entry.put("purple", Items.PURPLE_BANNER);
        entry.put("magenta", Items.MAGENTA_BANNER);
        entry.put("pink", Items.PINK_BANNER);

        dyedItems.put(ItemTags.TERRACOTTA, new HashMap<>());
        entry = dyedItems.get(ItemTags.TERRACOTTA);
        entry.put("white", Items.WHITE_TERRACOTTA);
        entry.put("light_gray", Items.LIGHT_GRAY_TERRACOTTA);
        entry.put("gray", Items.GRAY_TERRACOTTA);
        entry.put("black", Items.BLACK_TERRACOTTA);
        entry.put("brown", Items.BROWN_TERRACOTTA);
        entry.put("red", Items.RED_TERRACOTTA);
        entry.put("orange", Items.ORANGE_TERRACOTTA);
        entry.put("yellow", Items.YELLOW_TERRACOTTA);
        entry.put("lime", Items.LIME_TERRACOTTA);
        entry.put("green", Items.GREEN_TERRACOTTA);
        entry.put("cyan", Items.CYAN_TERRACOTTA);
        entry.put("light_blue", Items.LIGHT_BLUE_TERRACOTTA);
        entry.put("blue", Items.BLUE_TERRACOTTA);
        entry.put("purple", Items.PURPLE_TERRACOTTA);
        entry.put("magenta", Items.MAGENTA_TERRACOTTA);
        entry.put("pink", Items.PINK_TERRACOTTA);

        dyedItems.put(ItemTags.BEDS, new HashMap<>());
        entry = dyedItems.get(ItemTags.BEDS);
        entry.put("white", Items.WHITE_BED);
        entry.put("light_gray", Items.LIGHT_GRAY_BED);
        entry.put("gray", Items.GRAY_BED);
        entry.put("black", Items.BLACK_BED);
        entry.put("brown", Items.BROWN_BED);
        entry.put("red", Items.RED_BED);
        entry.put("orange", Items.ORANGE_BED);
        entry.put("yellow", Items.YELLOW_BED);
        entry.put("lime", Items.LIME_BED);
        entry.put("green", Items.GREEN_BED);
        entry.put("cyan", Items.CYAN_BED);
        entry.put("light_blue", Items.LIGHT_BLUE_BED);
        entry.put("blue", Items.BLUE_BED);
        entry.put("purple", Items.PURPLE_BED);
        entry.put("magenta", Items.MAGENTA_BED);
        entry.put("pink", Items.PINK_BED);

        dyedItems.put(ConventionalItemTags.CONCRETE_POWDERS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.CONCRETE_POWDERS);
        entry.put("white", Items.WHITE_CONCRETE_POWDER);
        entry.put("light_gray", Items.LIGHT_GRAY_CONCRETE_POWDER);
        entry.put("gray", Items.GRAY_CONCRETE_POWDER);
        entry.put("black", Items.BLACK_CONCRETE_POWDER);
        entry.put("brown", Items.BROWN_CONCRETE_POWDER);
        entry.put("red", Items.RED_CONCRETE_POWDER);
        entry.put("orange", Items.ORANGE_CONCRETE_POWDER);
        entry.put("yellow", Items.YELLOW_CONCRETE_POWDER);
        entry.put("lime", Items.LIME_CONCRETE_POWDER);
        entry.put("green", Items.GREEN_CONCRETE_POWDER);
        entry.put("cyan", Items.CYAN_CONCRETE_POWDER);
        entry.put("light_blue", Items.LIGHT_BLUE_CONCRETE_POWDER);
        entry.put("blue", Items.BLUE_CONCRETE_POWDER);
        entry.put("purple", Items.PURPLE_CONCRETE_POWDER);
        entry.put("magenta", Items.MAGENTA_CONCRETE_POWDER);
        entry.put("pink", Items.PINK_CONCRETE_POWDER);

        dyedItems.put(ConventionalItemTags.CONCRETE, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.CONCRETE);
        entry.put("white", Items.WHITE_CONCRETE);
        entry.put("light_gray", Items.LIGHT_GRAY_CONCRETE);
        entry.put("gray", Items.GRAY_CONCRETE);
        entry.put("black", Items.BLACK_CONCRETE);
        entry.put("brown", Items.BROWN_CONCRETE);
        entry.put("red", Items.RED_CONCRETE);
        entry.put("orange", Items.ORANGE_CONCRETE);
        entry.put("yellow", Items.YELLOW_CONCRETE);
        entry.put("lime", Items.LIME_CONCRETE);
        entry.put("green", Items.GREEN_CONCRETE);
        entry.put("cyan", Items.CYAN_CONCRETE);
        entry.put("light_blue", Items.LIGHT_BLUE_CONCRETE);
        entry.put("blue", Items.BLUE_CONCRETE);
        entry.put("purple", Items.PURPLE_CONCRETE);
        entry.put("magenta", Items.MAGENTA_CONCRETE);
        entry.put("pink", Items.PINK_CONCRETE);

        return dyedItems;
    }

    public MoobloomVariant getVariant() {
        return MoobloomVariant.fromName((String)this.dataTracker.get(TYPE));
    }

    public void setVariant(MoobloomVariant variant) {
        this.dataTracker.set(TYPE, variant.name);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        setVariant(MoobloomVariant.fromName(findVariant()));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    private String findVariant() {
        World world = getWorld();
        RegistryEntry<Biome> biome = world.getBiome(getBlockPos());

        ArrayList<String> colors = new ArrayList<>();
        switch(biome) {
            default: colors.add("yellow"); colors.add("red"); break;
        }
        return colors.get(world.random.nextInt(colors.size()));
    }

    private boolean biomeEquals(RegistryEntry<Biome> biome, RegistryKey<Biome> biome2) {
        return biome.value().equals(getWorld().getRegistryManager().get(RegistryKeys.BIOME).get(biome2));

    }

    private boolean biomeHasTag(RegistryEntry<Biome> biome, TagKey<Biome> tag) {
        return biome.isIn(tag);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, MoobloomVariant.YELLOW.name);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getVariant().asString());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(MoobloomEntity.MoobloomVariant.fromName(nbt.getString("Type")));
    }

    static {
        TYPE = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.STRING);
    }

    public enum MoobloomVariant implements StringIdentifiable {
        YELLOW("yellow", Blocks.DANDELION.getDefaultState()),
        RED("red", Blocks.POPPY.getDefaultState()),
        PINK("pink", Blocks.PINK_TULIP.getDefaultState()),
        BLUE("blue", Blocks.CORNFLOWER.getDefaultState());

        public static final StringIdentifiable.EnumCodec<MoobloomEntity.MoobloomVariant> CODEC = StringIdentifiable.createCodec(MoobloomEntity.MoobloomVariant::values);
        final String name;
        final BlockState flower;

        private MoobloomVariant(final String name, final BlockState mushroom) {
            this.name = name;
            this.flower = mushroom;
        }

        public BlockState getFlowerState() {
            return this.flower;
        }

        public String asString() {
            return this.name;
        }

        static MoobloomEntity.MoobloomVariant fromName(String name) {
            return (MoobloomEntity.MoobloomVariant)CODEC.byId(name, YELLOW);
        }
    }
}
