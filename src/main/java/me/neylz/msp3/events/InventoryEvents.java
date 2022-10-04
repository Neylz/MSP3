package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import me.neylz.msp3.inventories.FactionSelection;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerKickEvent;

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
                e.getClickedInventory().close();
            }
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = Bukkit.getPlayer(e.getPlayer().getName());

        if (Msp3.playerInTeam(player) == null) {
            assert player != null;
            player.kick(Component.text("\uE025\uE024\uE021\uE023\uE022").font(Key.key("msmp", "custom"))
                    .append(Component.text("\n\n\nMerci de sélectionner une faction pour commencer à jouer.\n\n").font(Key.key("minecraft", "default")))
                    .append(Component.text("Reconnectez-vous pour en choisir une\n").color(TextColor.color(255, 85, 85)).font(Key.key("minecraft", "default"))), PlayerKickEvent.Cause.PLUGIN);
        }
    }
}
