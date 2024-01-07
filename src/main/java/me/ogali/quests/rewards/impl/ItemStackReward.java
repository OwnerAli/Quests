package me.ogali.quests.rewards.impl;

import me.ogali.quests.rewards.AbstractReward;
import me.ogali.quests.utilities.Chat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemStackReward extends AbstractReward {

    private final ItemStack itemStack;

    public ItemStackReward(String name, ItemStack itemStack, int amount) {
        super(name);
        this.itemStack = itemStack;
        itemStack.setAmount(amount);
    }

    @Override
    public void reward(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItem(player.getLocation(), itemStack);
            Chat.tell(player, "&cYour inventory was full so your reward was dropped at your feet!");
            return;
        }
        player.getInventory().addItem(itemStack);
    }

}
