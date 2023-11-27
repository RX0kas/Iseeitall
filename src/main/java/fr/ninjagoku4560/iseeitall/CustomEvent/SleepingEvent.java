package fr.ninjagoku4560.iseeitall.CustomEvent;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class SleepingEvent {
    public static ActionResult onStartSleeping(LivingEntity entity, BlockPos sleepingPos) {
        // Assurez-vous que l'entité est un joueur avant de continuer
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            // Votre code va ici

            // Par exemple, affichez un message dans la console lorsque le joueur commence à dormir
            System.out.println("Le joueur " + player.getName().getString() + " commence à dormir à " + sleepingPos);

            // N'oubliez pas de renvoyer l'ActionResult.SUCCESS si votre code réussit.
            return ActionResult.SUCCESS;
        }

        // Si ce n'est pas un joueur, vous pouvez renvoyer une autre action si nécessaire.
        return ActionResult.PASS;
    }
}
