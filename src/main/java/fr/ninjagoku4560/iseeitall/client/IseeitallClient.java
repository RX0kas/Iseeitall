package fr.ninjagoku4560.iseeitall.client;

import net.fabricmc.api.ClientModInitializer;
import fr.ninjagoku4560.iseeitall.Iseeitall;

public class IseeitallClient implements ClientModInitializer {

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        Iseeitall.LOGGER.info("Client Initialize started...");
    }
}
