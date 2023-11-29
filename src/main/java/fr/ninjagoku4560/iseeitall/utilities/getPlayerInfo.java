package fr.ninjagoku4560.iseeitall.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public class getPlayerInfo {
    public static boolean isOP(Entity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            return player.hasPermissionLevel(2);  // level of OP
        }
        return false;
    }
}
