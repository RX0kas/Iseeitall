package fr.ninjagoku4560.iseeitall.utilities;


import fr.ninjagoku4560.iseeitall.experimental.StealDetection;

import java.util.List;

public class jsonUtil {
    public static List<String> getslot(int slot,String filecontent) {
        List<String> content = readcontent(filecontent);
        // remove [ and ]
        String contentS = content.get(slot).toString().replace("[", "").replace("]", "");
        List<String> returnvalue = List.of(contentS.split(" "));
        return returnvalue;
    }
    public static List<String> readcontent(String filecontent){
        List<String> content = List.of(filecontent.split(StealDetection.contentspliter)[1].split(", "));
        for (int i=0;i<content.size();i++) {
            if (content.get(i).contains("[")) {
                content.set(i,content.get(i).replace("[",""));
            }
            if (content.get(i).contains("[")) {
                content.set(i,content.get(i).replace("]",""));
            }

        }
        return content;
    }
}
