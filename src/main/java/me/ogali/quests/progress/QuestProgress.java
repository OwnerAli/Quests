package me.ogali.quests.progress;

import me.ogali.quests.domain.Quest;
import org.bukkit.entity.Player;

public class QuestProgress {

    private final Quest inprogressQuest;
    private int progress;

    public QuestProgress(Quest inprogressQuest) {
        this.inprogressQuest = inprogressQuest;
    }

    public QuestProgress(Quest inprogressQuest, int progress) {
        this.inprogressQuest = inprogressQuest;
        this.progress = progress;
    }

    public Quest getInprogressQuest() {
        return inprogressQuest;
    }

    public int getProgress() {
        return progress;
    }

    public void updateProgress(Player player) {
        if (progress++ >= inprogressQuest.getRequiredTaskAmount()) {
            inprogressQuest.complete(player);
        }
        progress++;
    }

}
