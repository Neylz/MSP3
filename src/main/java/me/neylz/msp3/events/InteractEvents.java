package me.neylz.msp3.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class InteractEvents implements Listener {

    @EventHandler
    public void onRightClick (PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getMaterial().equals(Material.AIR)) {
            if (e.getHand() == EquipmentSlot.OFF_HAND) return;  //otherwise executes for both hands

            Block block = e.getClickedBlock();
            assert block != null;
            if (block.getBlockData() instanceof Stairs && ((Bisected) block.getBlockData()).getHalf() == Bisected.Half.BOTTOM) {
                player.sendMessage("wow" + Bukkit.getServer().getCurrentTick());
            }
        }
    }
}
