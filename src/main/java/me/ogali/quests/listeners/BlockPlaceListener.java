package me.ogali.quests.listeners;

import me.ogali.quests.QuestsPlugin;
import me.ogali.quests.utilities.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final QuestsPlugin main;

    public BlockPlaceListener(QuestsPlugin main) {
        this.main = main;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getBlockPlaced().getType().name().contains("SIGN")) return;
        main.getSignRegistry().registerSignLocation(event.getBlockPlaced().getLocation());
        Chat.tell(event.getPlayer(), "&aDisplay sign successfully placed!");
    }

}