package me.ogali.quests.registries;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class Registry<K, V> {

    private final Map<K, V> keyValueMap;

    public Registry() {
        keyValueMap = new HashMap<>();
    }

    public void registerObject(K key, V object) {
        keyValueMap.put(key, object);
    }

    public void unRegisterObject(K key) {
        keyValueMap.remove(key);
    }

    public Optional<V> getObjectByKey(K key) {
        return Optional.ofNullable(keyValueMap.get(key));
    }

    public Map<K, V> getObjectMap() {
        return keyValueMap;
    }

    public Set<K> getKeys() {
        return keyValueMap.keySet();
    }
}
