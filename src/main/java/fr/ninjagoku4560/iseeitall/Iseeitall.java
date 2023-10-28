package fr.ninjagoku4560.iseeitall;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class Iseeitall implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    private static final Logger LOGGER = (Logger) LogManager.getLogger("Iseeitall");
    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of the ISIA mod server side");

        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            LOGGER.info("The player "+player.getName().toString()+" to break " + pos.toString() + "in " + world.toString());
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
