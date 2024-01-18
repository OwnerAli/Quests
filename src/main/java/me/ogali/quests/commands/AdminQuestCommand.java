package me.ogali.quests.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.registries.QuestRegistry;
import me.ogali.quests.utilities.Chat;
import org.bukkit.entity.Player;

@CommandAlias("quest")
@SuppressWarnings("unused")
public class AdminQuestCommand extends BaseCommand {

    private final QuestRegistry questRegistry;

    public AdminQuestCommand(QuestRegistry questRegistry) {
        this.questRegistry = questRegistry;
    }

    @Subcommand("create")
    public void onCreate(Player player, Quest quest) {
        if (quest == null) {
            Chat.tell(player, "&cCommande usage: /quest create <display name> &c<description> <requiredTaskAmount> <id>");
            return;
        }
        questRegistry.registerObject(quest.getId(), quest);
        Chat.tell(player, "&aYou've successfully created a new quest!");

        Chat.tell(player, "&6&lInformation!");
        Chat.tell(player, "&eName: &f" + quest.getName());
        Chat.tell(player, "&eDescription: &f" + quest.getDescription());
        Chat.tell(player, "&eRewards: &f" + quest.getRewardsList());
        Chat.tell(player, "&eId: &f" + quest.getId());
    }

}
