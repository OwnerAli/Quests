package me.ogali.quests;

import me.ogali.quests.listeners.BlockPlaceListener;
import me.ogali.quests.listeners.PlayerAcceptQuestListener;
import me.ogali.quests.listeners.PlayerJoinListener;
import me.ogali.quests.registries.PlayerRegistry;
import me.ogali.quests.registries.QuestRegistry;
import me.ogali.quests.registries.SignRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuestsPlugin extends JavaPlugin {

    public static QuestsPlugin instance;

    private QuestRegistry questRegistry;
    private PlayerRegistry playerRegistry;
    private SignRegistry signRegistry;

    @Override
    public void onEnable() {
        instance = this;
        initializeRegistries();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
        pluginManager.registerEvents(new PlayerAcceptQuestListener(signRegistry), this);
        pluginManager.registerEvents(new PlayerJoinListener(playerRegistry), this);
    }

    public void initializeRegistries() {
        questRegistry = new QuestRegistry();
        playerRegistry = new PlayerRegistry();
        signRegistry = new SignRegistry();
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

}
