package fr.ninjagoku4560.iseeitall;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;

/*public class getConfig {
    public static int execute(CommandContext<ServerCommandSource> context) {
        String name = StringArgumentType.getString(context, "Name of the config");
        int NumberValue = -1;
        if (Objects.equals(name, "logIfOP")){
            NumberValue = 0;
        } else if (Objects.equals(name, "logWhenBreakBlock")) {
            NumberValue = 1;
        } else if (Objects.equals(name, "logWhenHitEntity")) {
            NumberValue = 2;
        } else if (Objects.equals(name, "logWhenUseItem")) {
            NumberValue = 3;
        } else if (Objects.equals(name, "logWhenUseBlock")) {
            NumberValue = 4;
        } else if (Objects.equals(name, "logWhenUseEntity")) {
            NumberValue = 5;
        } else {
            return 2;
        }

        context.getSource().sendFeedback(() -> Text.literal("The value of this config is "+String.valueOf(TxTConfigLoader.getBooleanConfig(NumberValue))), false);
        return 1;
    }
}*/

public class getConfig {
    public static int execute(CommandContext<ServerCommandSource> context) {
        String name = StringArgumentType.getString(context, "Name of the config");
        final int[] NumberValue = { -1 }; // Déclaré comme tableau pour être "effectively final"

        if (Objects.equals(name, "logIfOP")) {
            NumberValue[0] = 0;
        } else if (Objects.equals(name, "logWhenBreakBlock")) {
            NumberValue[0] = 1;
        } else if (Objects.equals(name, "logWhenHitEntity")) {
            NumberValue[0] = 2;
        } else if (Objects.equals(name, "logWhenUseItem")) {
            NumberValue[0] = 3;
        } else if (Objects.equals(name, "logWhenUseBlock")) {
            NumberValue[0] = 4;
        } else if (Objects.equals(name, "logWhenUseEntity")) {
            NumberValue[0] = 5;
        } else {
            return 2;
        }

        context.getSource().sendFeedback(() -> Text.literal("The value of this config is " + String.valueOf(TxTConfigLoader.getBooleanConfig(NumberValue[0]))), false);
        return 1;
    }
}
