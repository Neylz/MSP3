package me.neylz.msp3.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatEvents implements Listener {

    @EventHandler
    public void onMessage(AsyncChatEvent e) {
        Player sender = e.getPlayer();

        e.setCancelled(true);


        if (sender.getName().equals("Neylzounet")) {
            Bukkit.broadcast(sender.teamDisplayName().append(Component.text(": ").color(TextColor.color(0xBFBFBF))).append(e.message().color(TextColor.color(0xFFAA00))));
        } else {
            Bukkit.broadcast(sender.teamDisplayName().append(Component.text(": ").color(TextColor.color(0xBFBFBF))).append(e.message().color(TextColor.color(0xBFBFBF))));
        }


    }
}
