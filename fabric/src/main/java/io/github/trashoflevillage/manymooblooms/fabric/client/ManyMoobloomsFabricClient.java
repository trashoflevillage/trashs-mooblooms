package io.github.trashoflevillage.manymooblooms.fabric.client;

import io.github.trashoflevillage.manymooblooms.client.ManyMoobloomsClient;
import net.fabricmc.api.ClientModInitializer;

public final class ManyMoobloomsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ManyMoobloomsClient.init();
    }
}
