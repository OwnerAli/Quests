package me.ogali.quests.tasks.impl.impl;

import me.ogali.quests.QuestsPlugin;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.registries.PlayerRegistry;
import me.ogali.quests.tasks.impl.ItemStackTask;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class BlockBreakTask extends ItemStackTask {

    private final Consumer<BlockBreakEvent> blockBreakEventConsumer;

    protected BlockBreakTask(String displayName, double progress, double amount, String id, int priority, ItemStack itemStack) {
        super(displayName, progress, amount, id, priority, itemStack);
        this.blockBreakEventConsumer = event -> {
            final PlayerRegistry playerRegistry = QuestsPlugin.getInstance()
                    .getPlayerRegistry();

            playerRegistry.getObjectByKey(event.getPlayer())
                    .ifPresent(questPlayer -> {
                        Quest inprogressQuest = questPlayer.getCurrentQuestProgress()
                                .getInprogressQuest();

                        incrementProgress(inprogressQuest);
                    });
        };
    }

    public Consumer<BlockBreakEvent> getBlockBreakEventConsumer() {
        return blockBreakEventConsumer;
    }

}