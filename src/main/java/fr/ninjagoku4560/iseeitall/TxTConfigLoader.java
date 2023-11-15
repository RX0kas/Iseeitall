package fr.ninjagoku4560.iseeitall;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TxTConfigLoader {

    private static String TxTContent = "logWhenBreakBlock=false\nlogWhenHitEntity=true\nlogWhenUseItem=true\nlogIfOP=false";

    public static void setTxTContent(String Content) {
        TxTContent = Content;
    }
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
            writer.write(TxTContent);
            writer.close();
            Iseeitall.LOGGER.info("The ISIA configuration file was successfully created");
        } catch (IOException e) {
            Iseeitall.LOGGER.error("The ISIA configuration file was not created!");
            Iseeitall.LOGGER.error(e.toString());
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
        } catch (IOException e) {
            Iseeitall.LOGGER.error(e.getMessage(), e);
        }
        return "error";
    }

    public static List<String> ToList(String str, String RequestValue) {
        String[] List1 = str.split("\n");
        List<String> ConfigNames = new ArrayList<>(); // Initialisez les listes en dehors de la boucle
        List<String> ConfigData = new ArrayList<>();

        for (String s : List1) {
            String[] List = s.split("=");

            for (int i = 0; i < List.length; i += 2) {
                ConfigNames.add(List[i]);
                ConfigData.add(List[i + 1]);
            }
        }
        if (RequestValue.equalsIgnoreCase("name") || RequestValue.equalsIgnoreCase("names")) {
            return ConfigNames;
        } else if (RequestValue.equalsIgnoreCase("data")) {
            return ConfigData;
        } else {
            System.out.println("ToList function has a wrong parameter for RequestValue");
            System.out.println("If you are not a dev, you can ignore this message");
            return new ArrayList<>();
        }
    }

    public static boolean getBooleanConfig(int config) {
        try {
            List<String> DataList = ToList(readConfigFile(), "data");
            String strValue = DataList.get(config);
            return Boolean.parseBoolean(strValue);
        } catch (IndexOutOfBoundsException e) {
            Iseeitall.LOGGER.error("The entered number for the \"getBooleanConfig\" not return a boolean value");
            Iseeitall.LOGGER.error("Returning true");
        }
        return true;
    }
}
