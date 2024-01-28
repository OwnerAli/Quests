package me.ogali.quests.tasks.impl.impl;

import me.ogali.quests.QuestsPlugin;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.registries.PlayerRegistry;
import me.ogali.quests.tasks.impl.ItemStackTask;
import me.ogali.quests.utilities.Chat;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakTask extends ItemStackTask<BlockBreakEvent> {

    public BlockBreakTask(String displayName, double progress, double amount, String id, int priority, ItemStack itemStack) {
        super(displayName, progress, amount, id, priority, itemStack);
        this.objectConsumer = event -> {
            final PlayerRegistry playerRegistry = QuestsPlugin.getInstance()
                    .getPlayerRegistry();

            playerRegistry.getObjectByKey(event.getPlayer())
                    .ifPresent(questPlayer -> {
                        Quest inprogressQuest = questPlayer.getCurrentQuestProgress()
                                .getInprogressQuest();

                        if (itemStack.getType() != event.getBlock().getType()) return;
                        incrementTaskProgress(inprogressQuest, event.getPlayer());
                        Chat.sendActionBarWithSound(event.getPlayer(), "&aBlock Mined! &7(" + getProgress() + "/" + amount + ")");
                    });
        };
    }

}