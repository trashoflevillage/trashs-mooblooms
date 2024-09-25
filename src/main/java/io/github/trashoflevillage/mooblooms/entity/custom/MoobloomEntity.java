package io.github.trashoflevillage.mooblooms.entity.custom;

import io.github.trashoflevillage.mooblooms.blocks.ModBlocks;
import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SuspiciousStewIngredient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MoobloomEntity extends CowEntity implements Shearable {
    private static final TrackedData<Boolean> IS_SHEARED;
    private static final TrackedData<Integer> REGROW_TIMER;
    private static final TrackedData<String> TYPE;
    private static final float SPAWN_CHANCE = 0.1f;
    private UUID lightningUUID;

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
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.YELLOW))
            return MoobloomVariant.GREEN;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.BLUE))
            return MoobloomVariant.PURPLE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.YELLOW))
            return MoobloomVariant.ORANGE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.GREEN))
            return MoobloomVariant.CYAN;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLUE, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_BLUE;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.RED, MoobloomVariant.WHITE))
            return MoobloomVariant.PINK;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.PURPLE, MoobloomVariant.PINK))
            return MoobloomVariant.MAGENTA;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLACK, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_GRAY;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.GRAY, MoobloomVariant.WHITE))
            return MoobloomVariant.LIGHT_GRAY;
        if (areMoobloomsEqualToColorPair(this, moobloom, MoobloomVariant.BLACK, MoobloomVariant.LIGHT_GRAY))
            return MoobloomVariant.GRAY;

        MoobloomVariant[] types = new MoobloomVariant[] {
                this.getVariant(),
                moobloom.getVariant()
        };
        return types[this.getWorld().random.nextInt(types.length)];
    }

    private boolean areMoobloomsEqualToColorPair(MoobloomEntity a, MoobloomEntity b, MoobloomVariant c, MoobloomVariant d) {
        return (a.getVariant() == c && b.getVariant() == d) || (b.getVariant() == c && a.getVariant() == d);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult result = ActionResult.FAIL;
        ItemStack itemStack = player.getStackInHand(hand);

        if (tryConvertToSussyStew(player, hand) ||
            tryToDyeItem(player, hand) ||
            super.interactMob(player, hand) == ActionResult.SUCCESS)
                result = ActionResult.SUCCESS;
        else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            this.sheared(SoundCategory.PLAYERS);
            this.emitGameEvent(GameEvent.SHEAR, player);
            if (!this.getWorld().isClient) {
                itemStack.damage(1, player, getSlotForHand(hand));
            }

            return ActionResult.success(this.getWorld().isClient);
        }
        return result;
    }


    private boolean tryConvertToSussyStew(PlayerEntity player, Hand hand) {
        if (this.isBaby()) return false;
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
        if (this.isBaby()) return false;
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

        dyedItems.put(ConventionalItemTags.GLASS_BLOCKS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLASS_BLOCKS);
        entry.put("white", Items.WHITE_STAINED_GLASS);
        entry.put("light_gray", Items.LIGHT_GRAY_STAINED_GLASS);
        entry.put("gray", Items.GRAY_STAINED_GLASS);
        entry.put("black", Items.BLACK_STAINED_GLASS);
        entry.put("brown", Items.BROWN_STAINED_GLASS);
        entry.put("red", Items.RED_STAINED_GLASS);
        entry.put("orange", Items.ORANGE_STAINED_GLASS);
        entry.put("yellow", Items.YELLOW_STAINED_GLASS);
        entry.put("lime", Items.LIME_STAINED_GLASS);
        entry.put("green", Items.GREEN_STAINED_GLASS);
        entry.put("cyan", Items.CYAN_STAINED_GLASS);
        entry.put("light_blue", Items.LIGHT_BLUE_STAINED_GLASS);
        entry.put("blue", Items.BLUE_STAINED_GLASS);
        entry.put("purple", Items.PURPLE_STAINED_GLASS);
        entry.put("magenta", Items.MAGENTA_STAINED_GLASS);
        entry.put("pink", Items.PINK_STAINED_GLASS);

        dyedItems.put(ConventionalItemTags.GLASS_PANES, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLASS_PANES);
        entry.put("white", Items.WHITE_STAINED_GLASS_PANE);
        entry.put("light_gray", Items.LIGHT_GRAY_STAINED_GLASS_PANE);
        entry.put("gray", Items.GRAY_STAINED_GLASS_PANE);
        entry.put("black", Items.BLACK_STAINED_GLASS_PANE);
        entry.put("brown", Items.BROWN_STAINED_GLASS_PANE);
        entry.put("red", Items.RED_STAINED_GLASS_PANE);
        entry.put("orange", Items.ORANGE_STAINED_GLASS_PANE);
        entry.put("yellow", Items.YELLOW_STAINED_GLASS_PANE);
        entry.put("lime", Items.LIME_STAINED_GLASS_PANE);
        entry.put("green", Items.GREEN_STAINED_GLASS_PANE);
        entry.put("cyan", Items.CYAN_STAINED_GLASS_PANE);
        entry.put("light_blue", Items.LIGHT_BLUE_STAINED_GLASS_PANE);
        entry.put("blue", Items.BLUE_STAINED_GLASS_PANE);
        entry.put("purple", Items.PURPLE_STAINED_GLASS_PANE);
        entry.put("magenta", Items.MAGENTA_STAINED_GLASS_PANE);
        entry.put("pink", Items.PINK_STAINED_GLASS_PANE);

        dyedItems.put(ConventionalItemTags.SHULKER_BOXES, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.SHULKER_BOXES);
        entry.put("white", Items.WHITE_SHULKER_BOX);
        entry.put("light_gray", Items.LIGHT_GRAY_SHULKER_BOX);
        entry.put("gray", Items.GRAY_SHULKER_BOX);
        entry.put("black", Items.BLACK_SHULKER_BOX);
        entry.put("brown", Items.BROWN_SHULKER_BOX);
        entry.put("red", Items.RED_SHULKER_BOX);
        entry.put("orange", Items.ORANGE_SHULKER_BOX);
        entry.put("yellow", Items.YELLOW_SHULKER_BOX);
        entry.put("lime", Items.LIME_SHULKER_BOX);
        entry.put("green", Items.GREEN_SHULKER_BOX);
        entry.put("cyan", Items.CYAN_SHULKER_BOX);
        entry.put("light_blue", Items.LIGHT_BLUE_SHULKER_BOX);
        entry.put("blue", Items.BLUE_SHULKER_BOX);
        entry.put("purple", Items.PURPLE_SHULKER_BOX);
        entry.put("magenta", Items.MAGENTA_SHULKER_BOX);
        entry.put("pink", Items.PINK_SHULKER_BOX);

        dyedItems.put(ConventionalItemTags.GLAZED_TERRACOTTAS, new HashMap<>());
        entry = dyedItems.get(ConventionalItemTags.GLAZED_TERRACOTTAS);
        entry.put("white", Items.WHITE_GLAZED_TERRACOTTA);
        entry.put("light_gray", Items.LIGHT_GRAY_GLAZED_TERRACOTTA);
        entry.put("gray", Items.GRAY_GLAZED_TERRACOTTA);
        entry.put("black", Items.BLACK_GLAZED_TERRACOTTA);
        entry.put("brown", Items.BROWN_GLAZED_TERRACOTTA);
        entry.put("red", Items.RED_GLAZED_TERRACOTTA);
        entry.put("orange", Items.ORANGE_GLAZED_TERRACOTTA);
        entry.put("yellow", Items.YELLOW_GLAZED_TERRACOTTA);
        entry.put("lime", Items.LIME_GLAZED_TERRACOTTA);
        entry.put("green", Items.GREEN_GLAZED_TERRACOTTA);
        entry.put("cyan", Items.CYAN_GLAZED_TERRACOTTA);
        entry.put("light_blue", Items.LIGHT_BLUE_GLAZED_TERRACOTTA);
        entry.put("blue", Items.BLUE_GLAZED_TERRACOTTA);
        entry.put("purple", Items.PURPLE_GLAZED_TERRACOTTA);
        entry.put("magenta", Items.MAGENTA_GLAZED_TERRACOTTA);
        entry.put("pink", Items.PINK_GLAZED_TERRACOTTA);

        dyedItems.put(ItemTags.CANDLES, new HashMap<>());
        entry = dyedItems.get(ItemTags.CANDLES);
        entry.put("white", Items.WHITE_CANDLE);
        entry.put("light_gray", Items.LIGHT_GRAY_CANDLE);
        entry.put("gray", Items.GRAY_CANDLE);
        entry.put("black", Items.BLACK_CANDLE);
        entry.put("brown", Items.BROWN_CANDLE);
        entry.put("red", Items.RED_CANDLE);
        entry.put("orange", Items.ORANGE_CANDLE);
        entry.put("yellow", Items.YELLOW_CANDLE);
        entry.put("lime", Items.LIME_CANDLE);
        entry.put("green", Items.GREEN_CANDLE);
        entry.put("cyan", Items.CYAN_CANDLE);
        entry.put("light_blue", Items.LIGHT_BLUE_CANDLE);
        entry.put("blue", Items.BLUE_CANDLE);
        entry.put("purple", Items.PURPLE_CANDLE);
        entry.put("magenta", Items.MAGENTA_CANDLE);
        entry.put("pink", Items.PINK_CANDLE);

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
            default: colors.add("yellow"); colors.add("red"); colors.add("blue"); break;
        }
        return colors.get(world.random.nextInt(colors.size()));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(TYPE, MoobloomVariant.YELLOW.name);
        builder.add(IS_SHEARED, false);
        builder.add(REGROW_TIMER, 0);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getVariant().asString());
        nbt.putBoolean("IsSheared", this.isSheared());
        nbt.putInt("RegrowTimer", this.getRegrowTimer());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(MoobloomVariant.fromName(nbt.getString("Type")));
        this.setSheared(nbt.getBoolean("IsSheared"));
        this.setRegrowTimer(nbt.getInt("RegrowTimer"));
    }

    public boolean isSheared() {
        return this.dataTracker.get(IS_SHEARED);
    }

    public void setSheared(boolean val) {
        this.dataTracker.set(IS_SHEARED, val);
    }

    public int getRegrowTimer() {
       return this.dataTracker.get(REGROW_TIMER);
    }

    public void setRegrowTimer(int val) {
        this.dataTracker.set(REGROW_TIMER, val);
    }

    static {
        TYPE = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.STRING);
        IS_SHEARED = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        REGROW_TIMER = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        if (!this.getWorld().isClient()) {
            for(int i = 0; i < 5; ++i) {
                this.getWorld().spawnEntity(
                        new ItemEntity(
                                this.getWorld(),
                                this.getX(),
                                this.getBodyY(1.0),
                                this.getZ(),
                                new ItemStack(this.getVariant().flower.getBlock())));
            }
            this.setSheared(true);
            this.setRegrowTimer(24000);
        }
    }

    @Override
    public void tick() {
        super.tick();
        flowerRegrowthTick();
    }

    private void flowerRegrowthTick() {
        int regrowTimer = this.getRegrowTimer();
        if (regrowTimer > 0) {
            regrowTimer--;
            if (regrowTimer == 0) setSheared(false);
        }
        this.setRegrowTimer(regrowTimer);
    }

    @Override
    public boolean isShearable() {
        return !this.isSheared() && this.isAlive() && !this.isBaby();
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        UUID uUID = lightning.getUuid();
        if (!uUID.equals(this.lightningUUID)) {
            this.setVariant(MoobloomVariant.WHITE);
            this.lightningUUID = uUID;
            this.playSound(SoundEvents.ENTITY_MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }
    }

    public enum MoobloomVariant implements StringIdentifiable {
        WHITE("white", Blocks.WHITE_TULIP.getDefaultState()),
        LIGHT_GRAY("light_gray", Blocks.AZURE_BLUET.getDefaultState()),
        GRAY("gray", Blocks.GRAY_BANNER.getDefaultState()),
        BLACK("black", Blocks.WITHER_ROSE.getDefaultState()),
        BROWN("brown", Blocks.BROWN_BANNER.getDefaultState()),
        RED("red", Blocks.POPPY.getDefaultState()),
        ORANGE("orange", Blocks.ORANGE_TULIP.getDefaultState()),
        YELLOW("yellow", ModBlocks.BUTTERCUP.getDefaultState()),
        LIME("lime", Blocks.LIME_BANNER.getDefaultState()),
        GREEN("green", ModBlocks.GLADIOLI.getDefaultState()),
        CYAN("cyan", Blocks.CYAN_BANNER.getDefaultState()),
        LIGHT_BLUE("light_blue", Blocks.BLUE_ORCHID.getDefaultState()),
        BLUE("blue", Blocks.CORNFLOWER.getDefaultState()),
        PURPLE("purple", Blocks.PURPLE_BANNER.getDefaultState()),
        MAGENTA("magenta", Blocks.ALLIUM.getDefaultState()),
        PINK("pink", ModBlocks.HIBISCUS.getDefaultState());

        public static final EnumCodec<MoobloomVariant> CODEC = StringIdentifiable.createCodec(MoobloomVariant::values);
        final String name;
        final BlockState flower;

        private MoobloomVariant(final String name, final BlockState flower) {
            this.name = name;
            this.flower = flower;
        }

        public BlockState getFlowerState() {
            return this.flower;
        }

        public String asString() {
            return this.name;
        }

        static MoobloomVariant fromName(String name) {
            return (MoobloomVariant)CODEC.byId(name, YELLOW);
        }
    }
}
