package fr.ninjagoku4560.iseeitall;

import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;


public class ReloadConfigCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("reloadISIAConfig")
                .requires(source -> source.hasPermissionLevel(2))  // Niveau d'opérateur (OP) requis
                .executes(ReloadConfigCommand::reloadConfig));
    }

    private static int reloadConfig(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Event.RegisterEvent();
        context.getSource().sendFeedback(() -> Text.literal("The config was reload"), false);
        return 1;  // Indique que la commande a été exécutée avec succès
    }
}
