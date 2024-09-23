package io.github.trashoflevillage.mooblooms;

import io.github.trashoflevillage.mooblooms.entity.ModEntities;
import io.github.trashoflevillage.mooblooms.entity.ModEntitySpawn;
import io.github.trashoflevillage.mooblooms.entity.custom.MoobloomEntity;
import io.github.trashoflevillage.mooblooms.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrashsMooblooms implements ModInitializer {
	public static final String MOD_ID = "trashs_mooblooms";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		ModEntitySpawn.addEntitySpawns();

		FabricDefaultAttributeRegistry.register(ModEntities.MOOBLOOM, MoobloomEntity.createCowAttributes());
	}
}