package fr.ninjagoku4560.iseeitall;

import fr.ninjagoku4560.iseeitall.utilities.convertUtil;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import fr.ninjagoku4560.iseeitall.utilities.FileUtil;
import fr.ninjagoku4560.iseeitall.utilities.BlockUtil;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StealDetection {

    private static final String spliter = "%DATA%";
    private static final String contentspliter = "#";

    static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            // Registering data
            String PlayerName = String.valueOf(player.getName());
            String BlockPosition = String.valueOf(hitResult.getBlockPos());

            // Nom: {position}.chestdata
            String chestFilePath = Iseeitall.DATAFOLDER+"\\"+BlockPosition.toLowerCase()+".chestdata";
            File chestFile = new File(chestFilePath);


            BlockPos pos = hitResult.getBlockPos();




            // enregistre le block
            if (hand == player.getActiveHand()) {
                // Vérifie si le bloc est un coffre
                if (BlockUtil.isStorageBlock(world.getBlockState(hitResult.getBlockPos()).getBlock())) {
                    Iseeitall.LOGGER.info(player.getName().getString() + " open a chest");

                    Text ChestOwner;
                    // Detecte si le block est déja enregister
                    if (!chestFile.exists()) {
                        ChestOwner = player.getName();
                        Iseeitall.LOGGER.warn("No owner for this chest, the new owner is: "+player.getName().getString());
                    } else {
                        Iseeitall.LOGGER.warn(player.getName().getString() + " open a chest own by " + getOwner(chestFilePath).getString() + ".");
                        ChestOwner = getOwner(chestFilePath);
                    }


                    List<ItemStack> ChestContent = BlockUtil.getChestContents((ServerWorld) world,pos);
                    String content;


                    // position content Owner
                    content = BlockPosition+contentspliter+ChestContent+contentspliter+ChestOwner.getString()+contentspliter+player.getName().getString();

                    // écrit les données
                    FileUtil.writeToFile(chestFilePath,content);

                }
            }
            return ActionResult.PASS; // Continue l'exécution normale
        });


    }


    static List<String> getData(String filepath) {
        String filecontent = FileUtil.readFromFile(filepath);
        String[] ListFileContent = filecontent.split(contentspliter);
        List<String> returnvalue = new ArrayList<>();
        Collections.addAll(returnvalue, ListFileContent);
        return returnvalue;
    }


    static List<ItemStack> getContent(BlockPos ChestPosition,String filepath) {
        List<String> data = getData(filepath);
        for (int i = 0;i<data.size();i++) {
            //      0             1             2           3
            // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
            List<String> chestData = getData(filepath);
            BlockPos ExtractedChestPos = convertUtil.stringToBlockPos(chestData.get(0));
            if (ExtractedChestPos.equals(ChestPosition)){
                return convertUtil.stringToListItemStack(chestData.get(1));
            }
        }
        return null;
    }


    static Text getOwner(String filepath) {
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        return Text.of(chestData.get(2));
    }

    static BlockPos getPos(String filepath) {
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        return convertUtil.stringToBlockPos(chestData.get(0));
    }

    static Text getLastPlayerWhoOpenIt(String filepath) {
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        return Text.of(chestData.get(3));
    }
}
