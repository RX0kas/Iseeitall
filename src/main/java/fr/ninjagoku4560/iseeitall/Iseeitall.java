package fr.ninjagoku4560.iseeitall;


import fr.ninjagoku4560.iseeitall.experimental.ModExperimentalFeature;
import fr.ninjagoku4560.iseeitall.experimental.StealDetection;
import fr.ninjagoku4560.iseeitall.utilities.TxTConfigLoader;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ninjagoku4560.iseeitall.utilities.FileUtil;

import fr.ninjagoku4560.iseeitall.CustomEvent.*;


import static net.minecraft.server.command.CommandManager.*;


public class Iseeitall implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static Logger LOGGER = LogManager.getLogger("Iseeitall");

    public static String DATAFOLDER = FileUtil.createFolder("ISIAdata");

    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of the ISIA mod server side");
        TxTConfigLoader.createConfigFile("logIfOP=false\nlogWhenBreakBlock=false\nlogWhenHitEntity=true\nlogWhenUseItem=true\nlogWhenUseBlock=true\nlogWhenUseEntity=true\nlogIfStartSleeping=false\nlogIfStopSleeping=false\nExperimentalFeature=false\n");
        Event.RegisterEvent();

        EntitySleepEvents.START_SLEEPING.register(SleepingEvent::onStartSleeping);
        EntitySleepEvents.STOP_SLEEPING.register(SleepingEvent::onStopSleeping);

        //register the reload command
        String ReloadCommandName = "reloadISIAConfig";

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal(ReloadCommandName)
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    Event.RegisterEvent();
                    ModExperimentalFeature.init();
                    context.getSource().sendFeedback(() -> Text.literal("The config was reload"), false);
                    return 1;})));
        ///////////////////////

        // Experimental feature
        StealDetection.init();
    }



}