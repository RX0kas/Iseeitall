package fr.ninjagoku4560.iseeitall.utilities;

import fr.ninjagoku4560.iseeitall.Iseeitall;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {


    public static String createFolder(String FolderName) {

        // Créer un objet File représentant le dossier
        File directory = new File(FolderName);

        // Vérifier si le dossier existe déjà
        if (!directory.exists()) {
            // Créer le dossier
            boolean created = directory.mkdir();

            if (created) {
                Iseeitall.LOGGER.info("The folder "+FolderName+" was created");
                return FolderName;
            } else {
                Iseeitall.LOGGER.error("Creation of the folder "+FolderName+" have failed");
            }
        } else {
            Iseeitall.LOGGER.warn("The folder "+FolderName+" already exist");
            return FolderName;
        }
        return "";
    }

    public static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            Iseeitall.LOGGER.info("The file "+filePath+" was created");
        } catch (IOException e) {
            Iseeitall.LOGGER.error("Error while writing the file "+filePath+ " : " + e.getMessage());
        }
    }


    public static String read(String filePath){
        File file = new File(filePath);
        if (!file.exists()) {return null;}
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            Iseeitall.LOGGER.error("Error while reading the file : "+filePath+" : " + e.getMessage());
        }

        return content;
    }

    public static boolean isFileEmpty(Path path) {
        try {
            // Lire le contenu du fichier
            String content = Files.readString(path);
            // Vérifier si le contenu est vide
            return content.isEmpty();
        } catch (IOException e) {
            Iseeitall.LOGGER.error(e);
            return false; // En cas d'erreur, considérer que le fichier n'est pas vide
        }
    }
}
