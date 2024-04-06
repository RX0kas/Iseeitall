package fr.ninjagoku4560.iseeitall.experimental;

import fr.ninjagoku4560.iseeitall.Iseeitall;
import fr.ninjagoku4560.iseeitall.utilities.*;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// les importations sont plus haut
public class StealDetection {

    public static final String spliter = "%DATA%";
    public static final String contentspliter = "#";

    public static void init() {

        Iseeitall.LOGGER.info("Initialization of the Steal Detection");

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (TxTConfigLoader.getBooleanConfig(8)) {
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
                        Iseeitall.LOGGER.info(player.getName().getString() + " open a chest at "+hitResult.getBlockPos().toString());

                        Text ChestOwner;
                        // Detecte si le block est déja enregister
                        if (!chestFile.exists()) {
                            ChestOwner = player.getName();
                            Iseeitall.LOGGER.warn("No owner for this chest, the new owner is: "+player.getName().getString());
                        } else {
                            ChestOwner = getOwner(chestFilePath);
                            Iseeitall.LOGGER.warn(player.getName().getString() + " open a chest own by " + ChestOwner.getString());
                        }


                        List<String> ChestContent = BlockUtil.getChestContents((ServerWorld) world, pos);
                        List<String> PreviousContent = getContent(chestFilePath);
                        String content;

                        if (!(ChestContent == PreviousContent) && chestFile.exists()) {
                            Iseeitall.LOGGER.info("Should output: LASTPLAYER stole DIFFERENCE");
                            /*List<String> difference = findDifferencesItemStackRawId(ChestContent, PreviousContent);
                            if (!difference.isEmpty()) {
                                Iseeitall.LOGGER.warn(getLastPlayerWhoOpenIt(chestFilePath).getString() + " stole " + difference);
                            }*/
                            List<String> diffRemove = new ArrayList(ChestContent);
                            diffRemove.removeAll(PreviousContent);
                            List<String> diffAdd = new ArrayList(PreviousContent);
                            diffAdd.removeAll(ChestContent);


                            if (!diffRemove.isEmpty()) {
                                Iseeitall.LOGGER.warn(getLastPlayerWhoOpenIt(chestFilePath).getString() + " take " + diffRemove);
                            }
                            if (!diffAdd.isEmpty()) {
                                Iseeitall.LOGGER.warn(getLastPlayerWhoOpenIt(chestFilePath).getString() + " put " + diffAdd);
                            }
                        }

                        // Position, contenu, propriétaire
                        content = BlockPosition + contentspliter + ChestContent + contentspliter + ChestOwner.getString() + contentspliter + player.getName().getString();

                        // Écrit les données
                        FileUtil.writeToFile(chestFilePath, content);
                    }
                }
            } else {
                File directory = new File(Iseeitall.DATAFOLDER);
                if (directory.exists()) {
                    // Suppression du dossier
                    boolean deleted = directory.delete();
                } else {
                    Iseeitall.LOGGER.error("Le dossier n'existe pas.");
                }
            }
                return ActionResult.PASS; // Continue l'exécution normale
            });
        }




    /*static List<String> getData(String filepath) {
        Iseeitall.LOGGER.info("Getting data from "+filepath);
        String filecontent = FileUtil.readFromFile(filepath);
        if (filecontent == null) {return null;}
        List ListFileContent = List.of(filecontent.split(contentspliter));
        List returnvalue = new ArrayList<>();
        Collections.addAll(returnvalue, ListFileContent);
        Iseeitall.LOGGER.info("We got "+returnvalue);
        return returnvalue;
    }*/
    static List<String> getData(String filepath) {
        Iseeitall.LOGGER.info("Getting data from " + filepath);
        String filecontent = FileUtil.read(filepath);

        if (filecontent == null) {return null;}

        List<String> listFileContent = List.of(filecontent.split(contentspliter));
        Iseeitall.LOGGER.info("We got "+listFileContent);

        return listFileContent;
    }



    static List<String> getContent(String filepath) {
        List<String> data = getData(filepath);
        if (data == null) {return null;}
            //      0             1             2           3
            // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
            List<String> chestData = getData(filepath);
            return jsonUtil.readcontent(chestData.get(1));
    }


    static Text getOwner(String filepath) {
        Iseeitall.LOGGER.info("Searching for the owner");
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        if (chestData == null) {return null;}
        return Text.of(String.valueOf(chestData.get(2)));
    }

    static BlockPos getPos(String filepath) {
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        if (chestData == null) {return null;}
        return ListUtil.stringToBlockPos(String.valueOf(chestData.get(0)));
    }

    static Text getLastPlayerWhoOpenIt(String filepath) {
        //      0             1             2           3
        // ChestPosition  ChestContent  OwnerName LastTimeOpenBy
        List<String> chestData = getData(filepath);
        if (chestData == null) {return null;}
        return Text.of(String.valueOf(chestData.get(3)));
    }

    static boolean sameContent(List<ItemStack> currentContent, List<ItemStack> previousContent) {return currentContent.equals(previousContent);}

    public static List<String> findDifferencesItemStackRawId(List<ItemStack> list1, List<ItemStack> list2) {
        List<String> differences = new ArrayList<>();

        for (ItemStack stack1 : list1) {
            boolean found = false;
            for (ItemStack stack2 : list2) {
                if (ItemStack.areItemsEqual(stack1, stack2) && ItemStack.areEqual(stack2,stack1)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                differences.add(String.valueOf(Item.getRawId(stack1.getItem())));
            }
        }

        return differences;
    }
}
