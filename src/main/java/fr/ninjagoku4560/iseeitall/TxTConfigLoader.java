package fr.ninjagoku4560.iseeitall;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TxTConfigLoader {

    static String DefaultTxTContent = "";


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
            writer.write(DefaultTxTContent);
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
        } catch (FileNotFoundException e) {
            Iseeitall.LOGGER.error("Config File not found");
            Iseeitall.LOGGER.error(e);
        } catch (IOException e) {
            Iseeitall.LOGGER.error("IOExection");
            Iseeitall.LOGGER.error(e);
        }
        return "error";

    }

    public static List<String> ToList(@NotNull String str,String RequestValue) {
        String[] List = str.split("\n");
        List<String> ConfigNames = new ArrayList<>();
        List<String> ConfigData = new ArrayList<>();

        for (int i = 0; i < List.length; i+=2) {
            ConfigNames.add(List[i]);
            ConfigData.add(List[i+1]);
        }
        if (RequestValue.equalsIgnoreCase("name") || RequestValue.equalsIgnoreCase("names")) {
            return ConfigNames;
        } else if (RequestValue.equalsIgnoreCase("data")) {
            return ConfigData;
        } else {
            Iseeitall.LOGGER.error("ToList function has a wrong parameter for RequestValue");
            Iseeitall.LOGGER.error("If you are not a dev ignore this message");
            return new ArrayList<>();
        }
    } // data or names

    public static int FindPosition(List<String> list, String value) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(value)) {
                    return i;
                }
            }
        }
        return -1; // return -1 if the value is not found or if the list is empty
    }

    public static boolean getBooleanConfig(@NotNull String config) {
        List<String> NameList = ToList(readConfigFile(),"name");
        List<String> ValueList = ToList(readConfigFile(),"data");
        int position = FindPosition(NameList,config);
        if (position == -1) {
            Iseeitall.LOGGER.error("The \"config\" parameter is wrong or the given list is empty");
            return true;
        } else {
            String strValue = ValueList.get(position);
            return Boolean.parseBoolean(strValue);
        }
    }

}
