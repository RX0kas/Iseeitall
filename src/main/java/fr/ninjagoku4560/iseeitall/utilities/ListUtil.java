package fr.ninjagoku4560.iseeitall.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registry;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static List<ItemStack> findDifferencesItemStack(List<ItemStack> list1, List<ItemStack> list2) {
        List<ItemStack> differences = new ArrayList<>();

        for (ItemStack element : list1) {
            if (!list2.contains(element)) {
                differences.add(element);
            }
        }

        for (ItemStack element : list2) {
            if (!list1.contains(element)) {
                differences.add(element);
            }
        }

        return differences;
    }

    public static List<ItemStack> stringToListItemStack(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ItemStack>>(){}.getType();
        List<ItemStack> contentString = gson.fromJson(jsonString, listType);
        return contentString;
    }

    public static List<String> stringToListString(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> contentString = gson.fromJson(jsonString, listType);
        return contentString;
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

    public static ItemStack stringToItemStack(String content) {
        return null;
    }

}
