package me.ogali.quests.tasks.impl;

import me.ogali.quests.tasks.AbstractTask;
import org.bukkit.inventory.ItemStack;

public abstract class ItemStackTask<T> extends AbstractTask<T> {

    private final ItemStack itemStack;

    protected ItemStackTask(String displayName, double progress, double amount, String id, int priority, ItemStack itemStack) {
        super(displayName, progress, amount, id, priority);
        this.itemStack = itemStack;
    }

}
