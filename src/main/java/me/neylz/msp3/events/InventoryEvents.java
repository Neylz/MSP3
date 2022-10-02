package me.neylz.msp3.events;

import me.neylz.msp3.inventories.FactionSelection;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Team;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }

        if (e.getClickedInventory().getHolder() instanceof FactionSelection) {      // faction selection screen
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null) { return; }

            if (e.getCurrentItem().getType() == Material.KNOWLEDGE_BOOK) {
                switch (e.getCurrentItem().getItemMeta().getCustomModelData()) {
                    default :
                        player.sendMessage(ChatColor.RED + "error : CustomModelData not defined");
                        break;
                    case 1: //terre
                        if (player.getScoreboard().getTeams() == null) {

                        }
                        break;
                    case 2: //eau
                    case 3: //plante
                    case 4: //feu
                }
            }
        }

    }
}
