package me.ogali.quests.rewards.impl;

import me.ogali.quests.rewards.AbstractReward;
import me.ogali.quests.rewards.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandReward extends AbstractReward {

    private final String command;
    private final CommandType commandType;

    protected CommandReward(String name, String command, CommandType commandType) {
        super(name);
        this.command = command;
        this.commandType = commandType;
    }

    @Override
    public void reward(Player player) {
        // Can be used to award quest coins, exp, currency, etc.
        if (CommandType.PLAYER.equals(commandType)) {
            player.performCommand(command);
            return;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
    }

}
