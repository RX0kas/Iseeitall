package fr.ninjagoku4560.iseeitall;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

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

        // Player break a block
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            LOGGER.info("The player "+player.getName().toString()+" to break " + pos.toString() + "in " + world.toString());
            return ActionResult.PASS;
        });
        // Player hit other entity
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            LOGGER.info(player.getName().toString() + " hit " + getEntityName(entity));
            return ActionResult.PASS;
        });
    }


    private String getEntityName(Entity entity) {
        if (entity instanceof PlayerEntity) {
            return entity.getName().toString();
        } else {
            return entity.getType().toString();
        }
    }
}
