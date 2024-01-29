package me.ogali.quests.tasks;

import me.ogali.quests.domain.Quest;
import me.ogali.quests.utilities.Chat;
import org.bukkit.entity.Player;

import java.util.function.Consumer;


public abstract class AbstractTask<T> implements Comparable<AbstractTask<T>> {

    private final String displayName;
    private final String id;
    private final int priority;
    private double progress;

    protected final double amount;
    protected Consumer<T> objectConsumer;

    protected AbstractTask(String displayName, double progress, double amount, String id, int priority) {
        this.displayName = displayName;
        this.progress = progress;
        this.amount = amount;
        this.id = id;
        this.priority = priority;
    }

    public void progressTask(T object) {
        objectConsumer.accept(object);
    }

    protected void incrementTaskProgress(Quest inprogressQuest, Player player) {
        progress++;

        if (progress == amount) {
            Chat.tell(player, "&aYou've successfully completed this task!");
            inprogressQuest.completeCurrentTask(player);
        }
    }

    @Override
    public int compareTo(AbstractTask otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
    }

    public double getProgress() {
        return progress;
    }

}
