package me.neylz.msp3.inventories;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FactionSelection implements InventoryHolder {

    private final Inventory inv;


    public FactionSelection() {
        inv = Bukkit.createInventory(this, 45, Component.text("Faction Selection")); //54 max size
        init();
    }


    private void init() {
        for(int i=0; i<45;i++){
            List<Component> commonLore = new ArrayList<>();
            commonLore.add(Component.text("Cliquez pour rejoindre cette faction", TextColor.color(0xcccccc)));
            switch (i){
                //default -> inv.setItem(i, createItem(Component.text(""), Material.BLACK_STAINED_GLASS_PANE, null, 1));
                case 4 -> inv.setItem(i, createItem(Component.text("Rejoindre la faction Terre", TextColor.color(177, 99, 52), TextDecoration.BOLD), Material.KNOWLEDGE_BOOK, commonLore, 1));
                case 20 -> inv.setItem(i, createItem(Component.text("Rejoindre la faction Eau", TextColor.color(88, 133, 205), TextDecoration.BOLD), Material.KNOWLEDGE_BOOK, commonLore, 2));
                case 24 -> inv.setItem(i, createItem(Component.text("Rejoindre la faction Plante", TextColor.color(76, 141, 20), TextDecoration.BOLD), Material.KNOWLEDGE_BOOK, commonLore, 3));
                case 40 -> inv.setItem(i, createItem(Component.text("Rejoindre la faction Feu", TextColor.color(190, 16, 27), TextDecoration.BOLD), Material.KNOWLEDGE_BOOK, commonLore, 4));
            }
        }
    }


    private ItemStack createItem(Component name, Material mat, List<Component> lore, Integer customModelData) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        meta.setCustomModelData(customModelData);


        item.setItemMeta(meta);
        return item;
    }



    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }
}
