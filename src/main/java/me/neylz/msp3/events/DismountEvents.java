package me.neylz.msp3.events;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;


public class DismountEvents implements Listener {

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        Damageable vehicle = (Damageable) e.getDismounted();
        Entity rider = e.getEntity();

        if (vehicle.hasMetadata("Msp3_sit")) {
            vehicle.remove();
            rider.teleportAsync(rider.getLocation().add(0, .6, 0));
        }
    }
}