package me.ogali.quests.listeners;

import me.ogali.quests.players.QuestPlayer;
import me.ogali.quests.registries.PlayerRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PlayerRegistry playerRegistry;

    public PlayerJoinListener(PlayerRegistry playerRegistry) {
        this.playerRegistry = playerRegistry;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playerRegistry.registerObject(event.getPlayer(),
                new QuestPlayer(event.getPlayer()));
    }

}
