package fr.ninjagoku4560.iseeitall;


import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Iseeitall implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static Logger LOGGER = LogManager.getLogger("Iseeitall");
    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of the ISIA mod server side");
        TxTConfigLoader.createConfigFile();

        TxTConfigLoader.setTxTContent("logWhenBreakBlock=false\nlogWhenHitEntity=true\nlogWhenUseItem=true\nlogIfOP=false");
        Event.RegisterEvent();
    }
}
