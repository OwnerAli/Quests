package me.ogali.quests.registries;

import me.ogali.quests.domain.Quest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestRegistry {

    private final Map<String, Quest> questMap = new HashMap<>();

    public void registerQuest(Quest quest) {
        questMap.put(quest.getId(), quest);
    }

    public Optional<Quest> getQuestById(String id) {
        return Optional.ofNullable(questMap.get(id));
    }

    public void unRegisterQuest(String name) {
        questMap.remove(name);
    }

}
