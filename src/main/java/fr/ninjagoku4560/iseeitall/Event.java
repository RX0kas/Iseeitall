package fr.ninjagoku4560.iseeitall;

import fr.ninjagoku4560.iseeitall.utilities.getName;
import fr.ninjagoku4560.iseeitall.utilities.getPlayerInfo;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;


public class Event {

    public static void RegisterEvent() {
        ActionResult PASS = ActionResult.PASS;
        ////////////////////////////////
        //0 = logIfOP                 //
        //1 = logWhenHitEntity        //
        //2 = logWhenUseItem          //
        //3 = logWhenBreakBlock       //
        //4 = logWhenUseBlock         //
        //5 = logWhenUseEntity        //
        //6 = logIfStartSleeping      //
        ////////////////////////////////
        if (TxTConfigLoader.getBooleanConfig(1)) {
            // Player break a block
            AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
                // if the player is OP then do nothing
                if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {
                    Iseeitall.LOGGER.info("The player "+player.getName().toString()+" to break " + getName.Block(world,pos));
                    return ActionResult.SUCCESS;
                }
                return PASS;
            });
        }

        if (TxTConfigLoader.getBooleanConfig(2)) {
            // Player hit other entity
            AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
                // if the player is OP then do nothing
                if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {
                    Iseeitall.LOGGER.info("The player " + player.getName().toString() + " hit " + getName.Entity(entity));
                    return ActionResult.SUCCESS;
                }
                return PASS;
            });
        }

        if (TxTConfigLoader.getBooleanConfig(3)) {
            // Player use an Item
            UseItemCallback.EVENT.register((player, world, hand) -> {
                // if the player is OP then do nothing
                if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {
                    ItemStack heldItemStack = player.getStackInHand(hand);
                    Item heldItem = heldItemStack.getItem();
                    Iseeitall.LOGGER.info("The player " + player.getName().toString() + " used " + getName.Item(heldItem));
                    return TypedActionResult.success(ItemStack.EMPTY);
                }
                return TypedActionResult.pass(ItemStack.EMPTY);
            });
        }

        if (TxTConfigLoader.getBooleanConfig(4)) {
            // Player use a block
            UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
                // if the player is OP then do nothing
                if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {
                    Iseeitall.LOGGER.info("The player "+player.getName().toString()+" use" + getName.Block(world, hitResult.getBlockPos()));
                    return ActionResult.SUCCESS;
                }
                return PASS;
            });
        }
        if (TxTConfigLoader.getBooleanConfig(5)) {
            // Player use an Entity
            UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
                // if the player is OP then do nothing
                if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {
                    assert hitResult != null;
                    Iseeitall.LOGGER.info("The player "+player.getName().toString()+" use" + getName.Entity(hitResult.getEntity()));
                    return ActionResult.SUCCESS;
                }
                return PASS;
            });
        }
        if (TxTConfigLoader.getBooleanConfig(6)) {
            // Player start sleep
            if (!(getPlayerInfo.isOP(player) && !TxTConfigLoader.getBooleanConfig(0))) {

            }
        }
    }
}
