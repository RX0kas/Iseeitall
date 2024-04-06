package fr.ninjagoku4560.iseeitall.utilities;



import fr.ninjagoku4560.iseeitall.Iseeitall;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class BlockUtil {
    public static Block getBlockAtPosition(ServerWorld world, BlockPos pos) {
        return world.getBlockState(pos).getBlock();
    }

    public static List<String> getChestContents(ServerWorld world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof ChestBlockEntity chestBlockEntity) {

            List<String> Content = new ArrayList<>();
            for (int i = 0; i < chestBlockEntity.size(); i++) {
                ItemStack itemStack = chestBlockEntity.getStack(i);
                Item item = itemStack.getItem();
                Integer nbr = itemStack.getCount();
                Content.add(nbr + " " + Item.getRawId(itemStack.getItem()));
            }
            return Content;
        }
        Iseeitall.LOGGER.error("The block at "+pos.toString()+"is not a chest");
        return new ArrayList<>();
    }

    public static List<String> getChestContentsString(ServerWorld world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof ChestBlockEntity chestBlockEntity) {

            List<String> Content = new ArrayList<>();
            for (int i = 0; i < chestBlockEntity.size(); i++) {
                String itemStack = chestBlockEntity.getStack(i).toString();
                Content.add(itemStack);
            }
            Iseeitall.LOGGER.info(Content.toString());
            return Content;
        }
        Iseeitall.LOGGER.error("The block at "+pos.toString()+"is not a chest");
        return new ArrayList<>();
    }

    public static boolean isStorageBlock(Block block) {
        List<Block> blockEntityList = new ArrayList<>();
        blockEntityList.add(Blocks.CHEST);
        blockEntityList.add(Blocks.TRAPPED_CHEST);
        blockEntityList.add(Blocks.DISPENSER);
        blockEntityList.add(Blocks.FURNACE);
        blockEntityList.add(Blocks.BLAST_FURNACE);
        blockEntityList.add(Blocks.BREWING_STAND);
        blockEntityList.add(Blocks.HOPPER);
        blockEntityList.add(Blocks.DROPPER);
        blockEntityList.add(Blocks.BARREL);
        blockEntityList.add(Blocks.SMOKER);
        blockEntityList.add(Blocks.LECTERN);
        blockEntityList.add(Blocks.JUKEBOX);
        blockEntityList.add(Blocks.ENDER_CHEST);

        return blockEntityList.contains(block);
    }

}
