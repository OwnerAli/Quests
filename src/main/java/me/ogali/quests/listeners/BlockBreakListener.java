package me.ogali.quests.listeners;

import me.ogali.quests.domain.Quest;
import me.ogali.quests.registries.PlayerRegistry;
import me.ogali.quests.tasks.impl.impl.BlockBreakTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final PlayerRegistry playerRegistry;

    public BlockBreakListener(PlayerRegistry playerRegistry) {
        this.playerRegistry = playerRegistry;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        playerRegistry.getObjectByKey(event.getPlayer())
                .ifPresent(player -> {
                    Quest inprogressQuest = player.getCurrentQuestProgress().getInprogressQuest();
                    if (!(inprogressQuest.getTaskQueue().peek() instanceof BlockBreakTask blockBreakTask)) return;
                    blockBreakTask.getBlockBreakEventConsumer().accept(event);
                });
    }

}
