package me.ogali.quests.events;

import me.ogali.quests.domain.Quest;
import me.ogali.quests.players.QuestPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAcceptQuestEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final QuestPlayer questPlayer;
    private final Quest quest;

    public PlayerAcceptQuestEvent(QuestPlayer questPlayer, Quest quest) {
        this.questPlayer = questPlayer;
        this.quest = quest;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public QuestPlayer getQuestPlayer() {
        return questPlayer;
    }

    public Quest getQuest() {
        return quest;
    }

}
