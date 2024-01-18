package me.ogali.quests.listeners;

import me.ogali.quests.domain.Quest;
import me.ogali.quests.events.PlayerAcceptQuestEvent;
import me.ogali.quests.registries.SignRegistry;
import me.ogali.quests.utilities.Chat;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerAcceptQuestListener implements Listener {

    private final SignRegistry signRegistry;

    public PlayerAcceptQuestListener(SignRegistry signRegistry) {
        this.signRegistry = signRegistry;
    }

    @EventHandler
    public void onPlayerAcceptQuest(PlayerAcceptQuestEvent event) {
        Player player = event.getQuestPlayer().getPlayer();
        Quest quest = event.getQuest();

        Chat.tell(player, "&aYou've Accepted A Quest! &7(" + quest.getName() + ")");

        String[] signLines = {quest.getName(), quest.getDescription(), " ", " "};
        signRegistry.getSignLocationSet()
                .forEach(location -> player.sendSignChange(location, signLines,
                        DyeColor.BLACK, true));
    }

}
//        synchronized (this) {
//            Player player = event.getQuestPlayer().getPlayer();
//            Quest quest = event.getQuest();
//
//            Chat.tell(player, "&aYou've Accepted A Quest! &7(" + quest.getName() + ")");
//
//            String[] signLines = {quest.getName(), quest.getDescription(), " ", " "};
//            signRegistry.getSignLocationSet()
//                    .forEach(location -> player.sendSignChange(location, signLines,
//                            DyeColor.LIME));
//        }