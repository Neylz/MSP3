package me.neylz.msp3;


import me.neylz.msp3.commands.AdminCamCommand;
import me.neylz.msp3.commands.FactionCommand;
import me.neylz.msp3.commands.MaintenanceCommand;
import me.neylz.msp3.commands.OpenEndCommand;
import me.neylz.msp3.commands.tabcompletion.AdminCamCompletion;
import me.neylz.msp3.commands.tabcompletion.FactionTabCompletion;
import me.neylz.msp3.data.ConfigInterface;
import me.neylz.msp3.events.*;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

import static org.bukkit.scoreboard.Team.Option.*;


public final class Msp3 extends JavaPlugin {

    public static Msp3 instance;
    public static Msp3 getInstance() {
        return instance;
    }


    //factions
    public static Team terreFaction;
    public static Team eauFaction;
    public static Team planteFaction;
    public static Team feuFaction;
    public static Team lumiereFaction;

    public ScoreboardManager scoreboardManager;
    public Scoreboard scoreboard;




    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        //commands
        Objects.requireNonNull(getCommand("admincam")).setExecutor(new AdminCamCommand());
        Objects.requireNonNull(this.getCommand("admincam")).setTabCompleter(new AdminCamCompletion());
        Objects.requireNonNull(getCommand("faction")).setExecutor(new FactionCommand());
        Objects.requireNonNull(this.getCommand("faction")).setTabCompleter(new FactionTabCompletion());
        Objects.requireNonNull(getCommand("maintenance")).setExecutor(new MaintenanceCommand());
        Objects.requireNonNull(getCommand("endentrance")).setExecutor(new OpenEndCommand());


        //events
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
        getServer().getPluginManager().registerEvents(new LoginEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinEvents(), this);
        getServer().getPluginManager().registerEvents(new InteractEvents(), this);
        getServer().getPluginManager().registerEvents(new DismountEvents(), this);
        getServer().getPluginManager().registerEvents(new ServerListPing(), this);


        ConfigInterface.setupData();

        //teams
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getMainScoreboard();

        //detect if the team exists-> create it or get it
        if (scoreboard.getTeam("Terre") == null) terreFaction = scoreboard.registerNewTeam("Terre"); else terreFaction = scoreboard.getTeam("Terre");
        if (scoreboard.getTeam("Feu") == null) feuFaction = scoreboard.registerNewTeam("Feu"); else feuFaction = scoreboard.getTeam("Feu");
        if (scoreboard.getTeam("Eau") == null) eauFaction = scoreboard.registerNewTeam("Eau"); else eauFaction = scoreboard.getTeam("Eau");
        if (scoreboard.getTeam("Plante") == null) planteFaction = scoreboard.registerNewTeam("Plante"); else planteFaction = scoreboard.getTeam("Plante");
        if (scoreboard.getTeam("Lumiere") == null) lumiereFaction = scoreboard.registerNewTeam("Lumiere"); else lumiereFaction = scoreboard.getTeam("Lumiere");

        //apply parameters/update them
        applyFactionParameters(terreFaction, NamedTextColor.DARK_RED, "\uE022");
        applyFactionParameters(feuFaction, NamedTextColor.RED, "\uE025");
        applyFactionParameters(eauFaction, NamedTextColor.DARK_AQUA, "\uE023");
        applyFactionParameters(planteFaction, NamedTextColor.DARK_GREEN, "\uE024");
        applyFactionParameters(lumiereFaction, NamedTextColor.GOLD, "\uE021");





        //log
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Mazened Survival Plugin 3 Enabled");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Mazened Survival Plugin 3 Disabled");


    }


    private void applyFactionParameters (Team faction, NamedTextColor color, String prefix) {
        faction.color(color);

        Component newPrefix = Component.text(prefix, TextColor.color(255,255,255)).font(Key.key("msmp", "custom"));
        newPrefix = newPrefix.append(Component.text(" ").font(Key.key(Key.MINECRAFT_NAMESPACE, "default")));
        faction.prefix(newPrefix);

        faction.setOption(NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        faction.setOption(COLLISION_RULE, Team.OptionStatus.ALWAYS);
        faction.setOption(DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.ALWAYS);

        faction.setAllowFriendlyFire(true);
        faction.setCanSeeFriendlyInvisibles(false);

    }

    public static Team getFaction(String faction) {
        return switch (faction) {
            default -> null;
            case "Terre" -> terreFaction;
            case "Eau" -> eauFaction;
            case "Plante" -> planteFaction;
            case "Feu" -> feuFaction;
            case "Lumiere" -> lumiereFaction;
        };
    }
    public static String playerInTeam(Player player) {
        String team = null;
        if (terreFaction.hasPlayer(player)) team = "Terre";
        else if (eauFaction.hasPlayer(player)) team = "Eau";
        else if (planteFaction.hasPlayer(player)) team = "Plante";
        else if (feuFaction.hasPlayer(player)) team = "Feu";
        else if (lumiereFaction.hasPlayer(player)) team = "Lumiere";

        return team;
    }
}
