package fr.ninjagoku4560.iseeitall.experimental;

import fr.ninjagoku4560.iseeitall.Iseeitall;
import fr.ninjagoku4560.iseeitall.utilities.TxTConfigLoader;

public class ModExperimentalFeature {
    public static void init() {
        if (TxTConfigLoader.getBooleanConfig(9)) {
            Iseeitall.LOGGER.warn("Experimental feature are enabled.");
            Iseeitall.LOGGER.warn("These are features currently under development. There's a good chance it won't work!");
            StealDetection.init();
        }
    }
}
