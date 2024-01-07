package me.ogali.quests.domain;

import me.ogali.quests.rewards.AbstractReward;

import java.util.ArrayList;
import java.util.List;

public class Quest {

    private final String name;
    private final String description;
    private final List<AbstractReward> rewardsList;
    private final String id;

    public Quest(String name, String description, List<AbstractReward> rewardsList, String id) {
        this.name = name;
        this.description = description;
        this.rewardsList = new ArrayList<>();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<AbstractReward> getRewardsList() {
        return rewardsList;
    }

    public String getId() {
        return id;
    }

}
