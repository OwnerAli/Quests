package me.ogali.quests.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.ogali.quests.domain.Quest;
import me.ogali.quests.registries.QuestRegistry;
import me.ogali.quests.registries.RewardRegistry;
import me.ogali.quests.rewards.AbstractReward;
import me.ogali.quests.utilities.Chat;
import org.bukkit.entity.Player;

import java.util.Optional;

@CommandAlias("quest")
@SuppressWarnings("unused")
public class AdminQuestCommand extends BaseCommand {

    private final QuestRegistry questRegistry;
    private final RewardRegistry rewardRegistry;

    public AdminQuestCommand(QuestRegistry questRegistry, RewardRegistry rewardRegistry) {
        this.questRegistry = questRegistry;
        this.rewardRegistry = rewardRegistry;
    }

    @Subcommand("create")
    public void onCreate(Player player, Quest quest) {
        if (quest == null) {
            Chat.tell(player, "&cCommand usage: /quest create <display name> &c<description> <requiredTaskAmount> <id>");
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

    @Subcommand("reward add")
    public void onRewardAdd(Player player, String questId, String rewardId) {
        Optional<Quest> questOptional = questRegistry.getObjectByKey(questId);
        Optional<AbstractReward> rewardOptional = rewardRegistry.getObjectByKey(rewardId);

        if (questOptional.isEmpty()) {
            Chat.tell(player, "&cInvalid quest id.");
            return;
        }
        if (rewardOptional.isEmpty()) {
            Chat.tell(player, "&cInvalid reward id.");
            return;
        }

        questOptional.get().getRewardsList()
                .add(rewardOptional.get());
        Chat.tell(player, "&aReward successfully added to quest!");
    }

    @Subcommand("reward create")
    public void onRewardCreate(Player player, AbstractReward abstractReward) {
        if (abstractReward == null) {
            Chat.tell(player, "&cThere are two ways to use this command!");
            Chat.tell(player, "&cCommand usage: /quest reward create <id> <command (no '/')> <command type>\n&7(default command type: player command, other types: console command)");
            Chat.tell(player, "&7OR");
            Chat.tell(player, "&cCommand usage: /quest reward create <id> <amount>" +
                    "\n&7(this will use the ItemStack currently in your hand as the reward, with the specified amount, default amount: 1)");
            return;
        }
        rewardRegistry.registerObject(abstractReward.getId(), abstractReward);
        Chat.tell(player, "&aReward successfully created!");
    }

}
