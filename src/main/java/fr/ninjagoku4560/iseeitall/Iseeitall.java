package fr.ninjagoku4560.iseeitall;

import com.moandjiezana.toml.Toml;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Iseeitall implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static Logger LOGGER = LogManager.getLogger("Iseeitall");
    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of the ISIA mod server side");

        Toml ConfigFile = TomlConfigLoader.load();

        Boolean logWhenBreakBlock = ConfigFile.getBoolean("LogWhenBreakBlock");
        Boolean logWhenHitEntity = ConfigFile.getBoolean("LogWhenHitEntity");
        Boolean logWhenUseItem = ConfigFile.getBoolean("LogWhenUseItem");


        ActionResult PASS = ActionResult.PASS;

        if (logWhenBreakBlock) {
            // Player break a block
            AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
                LOGGER.info("The player "+player.getName().toString()+" to break " + getBlockName(world,pos));
                return PASS;
            });
        }

        if (logWhenHitEntity) {
            // Player hit other entity
            AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
                LOGGER.info("The player " + player.getName().toString() + " hit " + getEntityName(entity));
                return PASS;
            });
        }

        if (logWhenUseItem) {
            // Player use an Item
            UseItemCallback.EVENT.register((player, world, hand) -> {
                ItemStack heldItemStack = player.getStackInHand(hand);
                Item heldItem = heldItemStack.getItem();
                LOGGER.info("The player " + player.getName().toString() + " used " + getItemName(heldItem));
                return TypedActionResult.pass(ItemStack.EMPTY);
            });
        }
    }


    private String getEntityName(Entity entity) {
        if (entity instanceof PlayerEntity) {
            String EntityName = entity.getName().toString();
            String[] name = EntityName.split("\\.");
            return name[name.length-1];
        } else {
            String EntityName = entity.getType().toString();
            String[] name = EntityName.split("\\.");
            return name[name.length-1];
        }
    }
    private String getItemName(Item item) {
        String ItemName = item.getTranslationKey();
        String[] name = ItemName.split("\\.");
        return name[name.length-1];
    }
    private String getBlockName(World world, BlockPos blockPos) {
        Block block = world.getBlockState(blockPos).getBlock();
        return block.getTranslationKey();
    }
}
