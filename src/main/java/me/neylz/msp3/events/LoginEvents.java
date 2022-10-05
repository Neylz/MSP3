package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import me.neylz.msp3.inventories.FactionSelection;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvents implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();

        if (!(player.getName().equals("Neylz") || player.getName().equals("encorelogiste"))) {
            e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, Component.text("\uE025\uE024\uE021\uE023\uE022").font(Key.key("msmp", "custom"))
                    .append(Component.text("\n\n\nLe serveur est actuellement en maintenance.\nPlus d'informations sur discord dans le salon #info\n\n").color(TextColor.color(255, 83, 83)).font(Key.key("minecraft", "default"))));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (Msp3.playerInTeam(player) == null) {
            player.openInventory(new FactionSelection().getInventory());
        }
    }

}
