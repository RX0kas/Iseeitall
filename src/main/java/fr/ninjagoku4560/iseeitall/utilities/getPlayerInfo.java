package fr.ninjagoku4560.iseeitall.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

public class getPlayerInfo {
    /*public static int Permlvl(Entity player) {
        // if something is wrong it's return -1
        if (!player.isPlayer()) {return-1;}
        //check all the permissions level between 0 and 5
        for (int c = 0; c <= 5; c++) {
            if (player.hasPermissionLevel(c)) {return c;}
        }
        return -1;
    }*/
    public static boolean isOP(Entity entity) {
        if (entity instanceof ServerPlayerEntity player) {
            return player.hasPermissionLevel(2);  // level of OP
        }
        return false;
    }
}
