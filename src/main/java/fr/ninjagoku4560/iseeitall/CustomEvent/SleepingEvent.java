package fr.ninjagoku4560.iseeitall.CustomEvent;

import fr.ninjagoku4560.iseeitall.TxTConfigLoader;
import fr.ninjagoku4560.iseeitall.utilities.getPlayerInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class SleepingEvent {
    public static ActionResult onStartSleeping(LivingEntity entity, BlockPos sleepingPos) {
        if (TxTConfigLoader.getBooleanConfig(6)) {
            // Player start sleep
            if (!(getPlayerInfo.isOP(entity) && !TxTConfigLoader.getBooleanConfig(0))) {
                return ActionResult.PASS;
            }
            // Assurez-vous que l'entité est un joueur avant de continuer
            if (entity instanceof PlayerEntity player) {

                // Votre code va ici

                // Par exemple, affichez un message dans la console lorsque le joueur commence à dormir
                System.out.println("The player " + player.getName().getString() + " start sleeping at " + sleepingPos);

                // N'oubliez pas de renvoyer l'ActionResult.SUCCESS si votre code réussit.
                return ActionResult.SUCCESS;
            }
            return ActionResult.FAIL;
        }
        // Si ce n'est pas un joueur, vous pouvez renvoyer une autre action si nécessaire.
        return ActionResult.PASS;
    }
        public static ActionResult onStopSleeping(LivingEntity entity, BlockPos sleepingPos) {
            if (TxTConfigLoader.getBooleanConfig(7)) {
                // Player start sleep
                if (!(getPlayerInfo.isOP(entity) && !TxTConfigLoader.getBooleanConfig(0))) {
                    return ActionResult.PASS;
                }
                // Assurez-vous que l'entité est un joueur avant de continuer
                if (entity instanceof PlayerEntity player) {

                    // Votre code va ici

                    // Par exemple, affichez un message dans la console lorsque le joueur commence à dormir
                    System.out.println("The player " + player.getName().getString() + " stop sleeping at " + sleepingPos);

                    // N'oubliez pas de renvoyer l'ActionResult.SUCCESS si votre code réussit.
                    return ActionResult.SUCCESS;
                }

                // Si ce n'est pas un joueur, vous pouvez renvoyer une autre action si nécessaire.
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        }
}
