package fr.ninjagoku4560.iseeitall
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;

public class ConfigLoader {
    try:
        String configPath = "config/config.json";
    // Lecture du fichier JSON
        JsonObject config = new Gson().fromJson(new FileReader(configPath), JsonObject.class);
    } catch (IOException e {e.printStackTrace();}

    public static bool getBoolConfig(String parameter) {
        boolean data = config.get(parameter).getAsBoolean();
        return data
    }

}

