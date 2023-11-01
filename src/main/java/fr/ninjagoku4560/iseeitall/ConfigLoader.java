package fr.ninjagoku4560.iseeitall;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;


public class ConfigLoader {
    public static JsonObject load(){
        try {
            // Chemin du fichier de configuration
            String configPath = "config/ISIA_config.json";

            // Lecture du fichier JSON
            return new Gson().fromJson(new FileReader(configPath), JsonObject.class);
        } catch (IOException e) {
            Iseeitall.LOGGER.warn(e.getMessage(), e);
        }
        return null;
    }


    public static boolean getBoolConfig(String category,String parameter) {
        JsonObject config = load();
        assert config != null;
        return config.get(category+"."+parameter).getAsBoolean();
    }
    public static boolean getBoolConfig(String parameter) {
        JsonObject config = load();
        assert config != null;
        return config.get(parameter).getAsBoolean();
    }
}
