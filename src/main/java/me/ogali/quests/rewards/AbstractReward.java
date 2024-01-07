package me.ogali.quests.rewards;

import org.bukkit.entity.Player;

public abstract class AbstractReward {

    private final String id;

    protected AbstractReward(String id) {
        this.id = id;
    }

    public abstract void reward(Player player);

}
