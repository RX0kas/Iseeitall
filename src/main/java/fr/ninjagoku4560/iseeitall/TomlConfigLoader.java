package fr.ninjagoku4560.iseeitall;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TomlConfigLoader {
    /*public static Toml load() {
        String resourceName = "ISIA.toml";
        ClassLoader classLoader = TomlConfigLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new IllegalArgumentException("Fichier non trouvé! " + resourceName);
        } else {
            try {
                String configDir = "config";
                String destinationFilePath = configDir + "/ISIA.toml";

                Path configPath = Paths.get(configDir);
                if (!Files.exists(configPath)) {
                    Files.createDirectories(configPath);
                }

                Path destination = Paths.get(destinationFilePath);
                Files.copy(inputStream, destination);
                System.out.println("Fichier chargé avec succès.");
            } catch (IOException e) {
                System.err.println("Une erreur s'est produite lors du chargement du fichier.");
                e.printStackTrace();
            }
        }

        File file = new File(resourceName);
        return new Toml().read(file);
        Path path = Paths.get("ISIA.toml");
        return new Toml().read(path.toFile());
    }*/
}
