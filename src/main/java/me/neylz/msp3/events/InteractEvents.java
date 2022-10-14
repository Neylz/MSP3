package me.neylz.msp3.events;

import me.neylz.msp3.Msp3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;

public class InteractEvents implements Listener {

    @EventHandler
    public void onRightClick (PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getMaterial().equals(Material.AIR)) {
            if (e.getHand() == EquipmentSlot.OFF_HAND) return;  //otherwise executes for both hands

            Block block = e.getClickedBlock();
            assert block != null;
            if (
                block.getBlockData() instanceof Stairs
                && ((Bisected) block.getBlockData()).getHalf() == Bisected.Half.BOTTOM
                && ((Stairs) block.getBlockData()).getShape() == Stairs.Shape.STRAIGHT
                && !player.isSneaking()
                && player.getWorld().getBlockAt(block.getLocation().add(0, 1, 0)).getBlockData().getMaterial() == Material.AIR
            ) { // if block is stairs, straight, positioned bottom and the player isn't sneaking
                player.sendMessage("wow" + Bukkit.getServer().getCurrentTick());

                World world = player.getWorld();
                Location blockLocation = e.getClickedBlock().getLocation();
                blockLocation.add(.5, 500, .5);

                ArmorStand entity = world.spawn(blockLocation, ArmorStand.class);
                entity.setVisible(false);
                entity.setGravity(false);
                entity.setInvulnerable(true);
                entity.setSmall(true);
                entity.setDisabledSlots(EquipmentSlot.HAND);
                entity.setDisabledSlots(EquipmentSlot.OFF_HAND);
                entity.setDisabledSlots(EquipmentSlot.CHEST);
                entity.setDisabledSlots(EquipmentSlot.HEAD);
                entity.setDisabledSlots(EquipmentSlot.LEGS);
                entity.setDisabledSlots(EquipmentSlot.FEET);
                entity.setMarker(true);
                entity.setMetadata("Msp3_sit", new FixedMetadataValue(Msp3.getInstance(), Boolean.TRUE));

                entity.teleport(entity.getLocation().add(0, -500, 0));  //tp back
                entity.teleport(entity.getLocation().add(0, .3, 0));


                float yaw = 0;
                switch (((Stairs) block.getBlockData()).getFacing()) {
                    case NORTH -> yaw = 0;
                    case EAST -> {
                        entity.setRotation(90, 0);
                        yaw = 90;
                    }
                    case SOUTH -> {
                        entity.setRotation(180, 0);
                        yaw = 180;
                    }
                    case WEST -> {
                        entity.setRotation(-90, 0);
                        yaw = -90;
                    }
                }

                Location playerLocation = player.getLocation();
                player.teleport(new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), yaw, 0));

                entity.addPassenger(player);

            }
        }
    }
}
