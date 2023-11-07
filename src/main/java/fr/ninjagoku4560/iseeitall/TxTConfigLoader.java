package fr.ninjagoku4560.iseeitall;

import org.jetbrains.annotations.NotNull;

import java.io.*;


public class TxTConfigLoader {
    public static void createConfigFile() {
        try {
            String configDir = "config";
            String filePath = configDir + "/ISIA.txt";

            File dir = new File(configDir);
            if (!dir.exists()) {
                boolean created = dir.mkdir();
                if (!created) {
                    Iseeitall.LOGGER.error("Could not create the config folder!");
                    return;
                }
            }

            FileWriter writer = new FileWriter(filePath);
            String DefaultTxTContent = "";
            writer.write(DefaultTxTContent);
            writer.close();
            Iseeitall.LOGGER.info("The ISIA configuration file was successfully created");
        } catch (IOException e) {
            Iseeitall.LOGGER.error("The ISIA configuration file was not created!");
            e.printStackTrace();
        }
    }

    public static String readConfigFile() {
        try {
            String configDir = "config";
            String filePath = configDir + "/ISIA.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            Iseeitall.LOGGER.error("Config File not found");
            Iseeitall.LOGGER.error(e);
        } catch (IOException e) {
            Iseeitall.LOGGER.error("IOExection");
            Iseeitall.LOGGER.error(e);
        }
        return "error";

    }

    public static void ToList(@NotNull String str) {}

    public static boolean getBooleanConfig(@NotNull String config) {

        return true;
    }

}
