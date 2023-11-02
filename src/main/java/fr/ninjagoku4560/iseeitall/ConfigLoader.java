package fr.ninjagoku4560.iseeitall;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConfigLoader {

    /*public static JsonObject load(){
        String resourceName = "ISIA_config.json";
        ClassLoader classLoader = ConfigLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Fichier non trouvé! " + resourceName);
        } else {
            try {
                String dirPath = "config";
                String filePath = dirPath + "/ISIA_config.json";

                File dir = new File(dirPath);
                if (!dir.exists()) {
                    boolean created = dir.mkdir();
                    if (!created) {
                        Iseeitall.LOGGER.error("Échec de la création du répertoire.");
                    }
                }

                Path destination = Paths.get(filePath);
                Files.copy(inputStream, destination);
                System.out.println("Fichier chargé avec succès.");
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors du chargement du fichier.");
                e.printStackTrace();
            }
        }
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
        if (category == null) {
            Iseeitall.LOGGER.error("In the getBoolConfig function, the parameter \"category\" is null");
            Iseeitall.LOGGER.info("returning true");
            return true;
        }
        if (parameter == null) {
            Iseeitall.LOGGER.error("In the getBoolConfig function, the parameter \"parameter\" is null");
            Iseeitall.LOGGER.info("returning true");
            return true;
        }
        try {
            return config.get(category+"."+parameter).getAsBoolean();
        } catch (NullPointerException e) {
            Iseeitall.LOGGER.error("The "+category+"."+parameter+" value is null");
            Iseeitall.LOGGER.info("returning true");
            return true;
        }
    }
    public static boolean getBoolConfig(String parameter) {
        JsonObject config = load();
        assert config != null;

        if (parameter == null) {
            Iseeitall.LOGGER.error("In the getBoolConfig function, the parameter \"parameter\" is null");
            Iseeitall.LOGGER.info("returning true");
            return true;
        }

        if (!config.has(parameter)) {
            Iseeitall.LOGGER.error("The "+parameter+" value is null");
            Iseeitall.LOGGER.info("returning true");
            return true;
        }

        try {
            JsonElement jsonElement = config.get(parameter);
            if (jsonElement != null && jsonElement.isJsonPrimitive()) {
                return jsonElement.getAsBoolean();
            }
        } catch (NullPointerException e) {
            Iseeitall.LOGGER.error("The "+parameter+" value is null");
        }
        Iseeitall.LOGGER.info("returning true");
        return true;
    }*/


}
