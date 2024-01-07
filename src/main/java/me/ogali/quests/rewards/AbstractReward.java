package me.ogali.quests.rewards;

import org.bukkit.entity.Player;

public abstract class AbstractReward {

    private final String name;

    protected AbstractReward(String name) {
        this.name = name;
    }

    public abstract void reward(Player player);

}
