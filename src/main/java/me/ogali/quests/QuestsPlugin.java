package me.ogali.quests;

import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import me.ogali.quests.commands.AdminQuestCommand;
import me.ogali.quests.commands.PlayerQuestCommand;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.listeners.BlockBreakListener;
import me.ogali.quests.listeners.BlockPlaceListener;
import me.ogali.quests.listeners.PlayerAcceptQuestListener;
import me.ogali.quests.listeners.PlayerJoinListener;
import me.ogali.quests.registries.PlayerRegistry;
import me.ogali.quests.registries.QuestRegistry;
import me.ogali.quests.registries.RewardRegistry;
import me.ogali.quests.registries.SignRegistry;
import me.ogali.quests.rewards.AbstractReward;
import me.ogali.quests.rewards.CommandType;
import me.ogali.quests.rewards.impl.CommandReward;
import me.ogali.quests.rewards.impl.ItemStackReward;
import me.ogali.quests.tasks.impl.impl.BlockBreakTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuestsPlugin extends JavaPlugin {

    public static QuestsPlugin instance;

    private QuestRegistry questRegistry;
    private PlayerRegistry playerRegistry;
    private SignRegistry signRegistry;
    private RewardRegistry rewardRegistry;

    @Override
    public void onEnable() {
        instance = this;
        initializeRegistries();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockBreakListener(playerRegistry), this);
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
        pluginManager.registerEvents(new PlayerAcceptQuestListener(signRegistry), this);
        pluginManager.registerEvents(new PlayerJoinListener(playerRegistry), this);
    }

    private void initializeRegistries() {
        questRegistry = new QuestRegistry();
        playerRegistry = new PlayerRegistry();
        signRegistry = new SignRegistry();
        rewardRegistry = new RewardRegistry();
    }

    private void registerCommands() {
        PaperCommandManager cm = new PaperCommandManager(this);

        registerQuestCommandContext(cm);
        cm.setFormat(MessageType.SYNTAX, ChatColor.GREEN, ChatColor.GREEN);
        cm.registerCommand(new PlayerQuestCommand(this));
        cm.registerCommand(new AdminQuestCommand(questRegistry, rewardRegistry));
        cm.getCommandCompletions().registerCompletion("questIdList", handler -> questRegistry.getKeys());
    }

    private void registerQuestCommandContext(PaperCommandManager paperCommandManager) {
        paperCommandManager.getCommandContexts().registerContext(Quest.class, completion -> {

            String questName = completion.popFirstArg();
            String description = completion.popFirstArg();

            if (questName == null || description == null) return null;

            String id = completion.popFirstArg();
            if (id == null) return null;

            Quest quest = new Quest(questName, description, id);
            quest.getTaskQueue().add(new BlockBreakTask("test", 0, 10,
                    "test", 1, new ItemStack(Material.DIRT)));
            return quest;
        });

        paperCommandManager.getCommandContexts().registerContext(AbstractReward.class, completion -> {

            String rewardId = completion.popFirstArg();
            String command = completion.popFirstArg();

            if (rewardId == null) return null;

            if (command == null) {
                ItemStack itemInMainHand = completion.getPlayer().getInventory().getItemInMainHand();

                int amount = 1;

                try {
                    amount = Integer.parseInt(completion.popFirstArg());
                } catch (NumberFormatException ignored) {
                }
                return new ItemStackReward(rewardId, itemInMainHand, amount);
            }

            CommandType commandType = CommandType.PLAYER;
            try {
                commandType = CommandType.valueOf(completion.popFirstArg());
            } catch (NullPointerException ignored) {
            }
            return new CommandReward(rewardId, command, commandType);
        });
    }

    public static QuestsPlugin getInstance() {
        return instance;
    }

    public QuestRegistry getQuestRegistry() {
        return questRegistry;
    }

    public PlayerRegistry getPlayerRegistry() {
        return playerRegistry;
    }

    public SignRegistry getSignRegistry() {
        return signRegistry;
    }

    public RewardRegistry getRewardRegistry() {
        return rewardRegistry;
    }

}
