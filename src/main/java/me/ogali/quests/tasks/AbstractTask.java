package me.ogali.quests.tasks;

import me.ogali.quests.domain.Quest;


public abstract class AbstractTask implements Comparable<AbstractTask> {

    private final String displayName;
    private final String id;
    private final int priority;

    protected double progress;
    protected final double amount;

    protected AbstractTask(String displayName, double progress, double amount, String id, int priority) {
        this.displayName = displayName;
        this.progress = progress;
        this.amount = amount;
        this.id = id;
        this.priority = priority;
    }

    public void incrementProgress(Quest inprogressQuest) {
        if (progress++ >= amount) inprogressQuest.completeCurrentTask();
    }

    @Override
    public int compareTo(AbstractTask otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
    }
}
