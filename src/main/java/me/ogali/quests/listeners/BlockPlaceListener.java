package me.ogali.quests.listeners;

import me.ogali.quests.QuestsPlugin;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.events.PlayerAcceptQuestEvent;
import me.ogali.quests.progress.QuestProgress;
import me.ogali.quests.utilities.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
        if (event.getBlockPlaced().getType() == Material.GOLD_BLOCK) {
            main.getPlayerRegistry().getObjectByKey(event.getPlayer())
                    .ifPresent(questPlayer -> {
                        Quest superCoolQuestToTest = new Quest("Welcome Aboard!", "SUPER COOL QUEST :)",
                                10, "SUPER COOL QUEST TO TEST");
                        QuestProgress superCoolQuestToTestProgress = new QuestProgress(superCoolQuestToTest);

                        questPlayer.setCurrentQuestProgress(superCoolQuestToTestProgress);
                        Bukkit.getPluginManager().callEvent(new PlayerAcceptQuestEvent(questPlayer, superCoolQuestToTest));
                    });
            return;
        }
        if (!event.getBlockPlaced().getType().name().contains("SIGN")) return;
        main.getSignRegistry().registerSignLocation(event.getBlockPlaced().getLocation());
        Chat.tell(event.getPlayer(), "&aDisplay sign successfully placed!");
    }

}
