package fr.ninjagoku4560.iseeitall;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import fr.ninjagoku4560.iseeitall.utilities.FileUtil;
import fr.ninjagoku4560.iseeitall.utilities.BlockUtil;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class StealDetection {

    private static final String spliter = "%DATA%";
    private static final String contentspliter = "|";

    static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (hand == player.getActiveHand()) {
                // Vérifie si le bloc est un coffre
                if (world.getBlockState(hitResult.getBlockPos()).isOf(Blocks.CHEST)) {
                    Iseeitall.LOGGER.info(player.getName() + " place a chest");

                    // Registering Chest
                    String chestDataFile = Iseeitall.DATAFOLDER+"\\chest.data";
                    String PlayerName = String.valueOf(player.getName());
                    String ChestPosition = String.valueOf(hitResult.getBlockPos());
                    BlockPos pos = hitResult.getBlockPos();
                    Block Chest = BlockUtil.getBlockAtPosition((ServerWorld) world,pos);
                    List<ItemStack> ChestContent = BlockUtil.getChestContents((ServerWorld) world,pos);
                    String content;
                    if (FileUtil.isFileEmpty(Path.of(chestDataFile))) {
                        content = ChestPosition+contentspliter+contentspliter+spliter;
                    } else {
                        content = spliter+ChestPosition+contentspliter+contentspliter+spliter;
                    }
                    FileUtil.writeToFile(chestDataFile,content);

                }
            }
            return ActionResult.PASS; // Continue l'exécution normale
        });
    }
}
