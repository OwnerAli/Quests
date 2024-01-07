package me.ogali.quests.domain;

import me.ogali.quests.rewards.AbstractReward;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Quest {

    private final String name;
    private final String description;
    private final double requiredTaskAmount;
    private final List<AbstractReward> rewardsList;
    private final String id;

    public Quest(String name, String description, double requiredTaskAmount, String id) {
        this.name = name;
        this.description = description;
        this.requiredTaskAmount = requiredTaskAmount;
        this.rewardsList = new ArrayList<>();
        this.id = id;
    }

    // Copy constructor
    public Quest(Quest originalQuest) {
        this.name = originalQuest.name;
        this.description = originalQuest.description;
        this.requiredTaskAmount = originalQuest.requiredTaskAmount;
        this.rewardsList = new ArrayList<>(originalQuest.rewardsList);
        this.id = originalQuest.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getRequiredTaskAmount() {
        return requiredTaskAmount;
    }

    public List<AbstractReward> getRewardsList() {
        return rewardsList;
    }

    public String getId() {
        return id;
    }

    public void complete(Player player) {

    }

}
