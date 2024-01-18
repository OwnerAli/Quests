package me.ogali.quests.domain;

import me.ogali.quests.rewards.AbstractReward;
import me.ogali.quests.tasks.AbstractTask;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Quest {

    private final String name;
    private final String description;
    private final PriorityQueue<AbstractTask> taskQueue;
    private final List<AbstractReward> rewardsList;
    private final String id;

    public Quest(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.taskQueue = new PriorityQueue<>();
        this.rewardsList = new ArrayList<>();
        this.id = id;
    }

    // Copy constructor
    public Quest(Quest originalQuest) {
        this.name = originalQuest.name;
        this.description = originalQuest.description;
        this.taskQueue = originalQuest.taskQueue;
        this.rewardsList = new ArrayList<>(originalQuest.rewardsList);
        this.id = originalQuest.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Queue<AbstractTask> getTaskQueue() {
        return taskQueue;
    }

    public List<AbstractReward> getRewardsList() {
        return rewardsList;
    }

    public String getId() {
        return id;
    }

    public void completeCurrentTask() {
        taskQueue.poll();
    }

    public void complete(Player player) {

    }

}
