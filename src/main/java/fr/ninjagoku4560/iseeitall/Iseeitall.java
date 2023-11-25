package fr.ninjagoku4560.iseeitall;


import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.server.command.CommandManager.*;

public class Iseeitall implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static Logger LOGGER = LogManager.getLogger("Iseeitall");
    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of the ISIA mod server side");
        TxTConfigLoader.createConfigFile("logIfOP=false\nlogWhenBreakBlock=false\nlogWhenHitEntity=true\nlogWhenUseItem=true\nlogWhenUseBlock=true\nlogWhenUseEntity=true");
        Event.RegisterEvent();


        //register the reload command
        String CommandName = "reloadISIAConfig";

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal(CommandName)
                .requires(source -> source.hasPermissionLevel(2))
                .executes(context -> {
                    Event.RegisterEvent();
                    context.getSource().sendFeedback(() -> Text.literal("The config was reload"), false);
                    return 1;})));


    }

}
