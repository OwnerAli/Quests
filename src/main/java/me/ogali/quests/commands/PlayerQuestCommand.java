package me.ogali.quests.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import me.ogali.quests.QuestsPlugin;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.progress.QuestProgress;
import me.ogali.quests.utilities.Chat;
import org.bukkit.entity.Player;

import java.util.Optional;

@SuppressWarnings("unused")
@CommandAlias("quest")
public class PlayerQuestCommand extends BaseCommand {

    private final QuestsPlugin main;

    public PlayerQuestCommand(QuestsPlugin main) {
        this.main = main;
    }

    @Subcommand("accept")
    @CommandCompletion("@questIdList")
    public void onAccept(Player player, String questId) {
        Optional<Quest> optionalQuestFromId = main.getQuestRegistry().getObjectByKey(questId);
        if (optionalQuestFromId.isEmpty()) {
            Chat.tell(player, "&cThere is no quest with the id: " + questId);
            return;
        }
        main.getPlayerRegistry().getObjectByKey(player)
                .ifPresent(questPlayer -> {
                    QuestProgress currentQuestProgress = questPlayer.getCurrentQuestProgress();
                    if (currentQuestProgress != null) {
                        Chat.tell(questPlayer.getPlayer(), "&cYou have to complete or cancel your quest before taking another!");
                        Chat.tell(questPlayer.getPlayer(), "&7* " + currentQuestProgress.getInprogressQuest().getName());
                        return;
                    }
                    questPlayer.setCurrentQuestProgress(new QuestProgress(optionalQuestFromId.get()));
                });
    }

}
