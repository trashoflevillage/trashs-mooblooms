package io.github.trashoflevillage.manymooblooms.neoforge;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import io.github.trashoflevillage.manymooblooms.ManyMooblooms;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@Mod(ManyMooblooms.MOD_ID)
public final class ManyMoobloomsNeoForge {
    public ManyMoobloomsNeoForge(IEventBus modBus) {
        // Run our common setup.
        ManyMooblooms.init();
    }
}
