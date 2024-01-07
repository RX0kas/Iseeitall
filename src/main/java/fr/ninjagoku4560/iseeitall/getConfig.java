package fr.ninjagoku4560.iseeitall;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class getConfig {
    public static int execute(CommandContext<ServerCommandSource> context) {
        String name = StringArgumentType.getString(context, "Name of the config");

        Map<String, Integer> configMappings = new HashMap<>();
        configMappings.put("logIfOP", 0);
        configMappings.put("logWhenBreakBlock", 1);
        configMappings.put("logWhenHitEntity", 2);
        configMappings.put("logWhenUseItem", 3);
        configMappings.put("logWhenUseBlock", 4);
        configMappings.put("logWhenUseEntity", 5);

        // Obtention de la valeur associée au nom de configuration
        Integer numberValue = configMappings.get(name);

        if (numberValue != null) {
            context.getSource().sendFeedback(() -> Text.literal("The value of this config is " + TxTConfigLoader.getBooleanConfig(numberValue)), false);
            return 1;
        } else {
            return 2; // Config non trouvée
        }
    }
}
