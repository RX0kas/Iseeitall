package fr.ninjagoku4560.iseeitall.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Type;
import java.util.List;

public class convertUtil {
    public static List<ItemStack> stringToListItemStack(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ItemStack>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static BlockPos stringToBlockPos(String jsonString) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        // Récupération des coordonnées x, y et z du JsonObject
        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();
        int z = jsonObject.get("z").getAsInt();

        // Création d'un objet BlockPos avec les coordonnées récupérées
        return new BlockPos(x, y, z);
    }
}
