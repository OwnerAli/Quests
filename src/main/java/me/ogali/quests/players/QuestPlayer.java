package me.ogali.quests.players;

import me.ogali.quests.events.PlayerAcceptQuestEvent;
import me.ogali.quests.progress.QuestProgress;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class QuestPlayer {

    private final Player player;
    private QuestProgress currentQuestProgress;

    public QuestPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public QuestProgress getCurrentQuestProgress() {
        return currentQuestProgress;
    }

    public void setCurrentQuestProgress(QuestProgress currentQuestProgress) {
        this.currentQuestProgress = currentQuestProgress;
    }

    public void beginNewQuest(QuestProgress currentQuestProgress) {
        setCurrentQuestProgress(currentQuestProgress);
        Bukkit.getPluginManager().callEvent(new PlayerAcceptQuestEvent(this,
                currentQuestProgress.getInprogressQuest()));
    }

    public void completeCurrentQuest() {
        this.currentQuestProgress = null;
    }

}
