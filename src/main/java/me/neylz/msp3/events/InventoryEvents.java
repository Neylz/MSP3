package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import me.neylz.msp3.inventories.FactionSelection;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) { return; }
        Player player = (Player) e.getWhoClicked();


        //if (e.getClickedInventory().getHolder() instanceof FactionSelection) {      // faction selection screen
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof FactionSelection) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null) { return; }

            if (e.getCurrentItem().getType() == Material.KNOWLEDGE_BOOK) {
                switch (e.getCurrentItem().getItemMeta().getCustomModelData()) {
                    default :
                        player.sendMessage(ChatColor.RED + "error : CustomModelData not defined");
                        break;
                    case 1: //terre
                        if (!Msp3.getFaction("Terre").hasPlayer(player)) {
                            Msp3.getFaction("Terre").addPlayer(player);
                            player.sendMessage("Bienvenue dans la faction Terre !");
                        } else player.sendMessage(ChatColor.RED + "Vous êtes déjà dans cette faction");
                        break;
                    case 2: //eau
                        if (!Msp3.getFaction("Eau").hasPlayer(player)) {
                            Msp3.getFaction("Eau").addPlayer(player);
                            player.sendMessage("Bienvenue dans la faction Eau !");
                        } else player.sendMessage(ChatColor.RED + "Vous êtes déjà dans cette faction");
                        break;
                    case 3: //plante
                        if (!Msp3.getFaction("Plante").hasPlayer(player)) {
                            Msp3.getFaction("Plante").addPlayer(player);
                            player.sendMessage("Bienvenue dans la faction Plante !");
                        } else player.sendMessage(ChatColor.RED + "Vous êtes déjà dans cette faction");
                        break;
                    case 4: //feu
                        if (!Msp3.getFaction("Feu").hasPlayer(player)) {
                            Msp3.getFaction("Feu").addPlayer(player);
                            player.sendMessage("Bienvenue dans la faction Feu !");
                        } else player.sendMessage(ChatColor.RED + "Vous êtes déjà dans cette faction");
                        break;
                }
            }
        }

    }
}
