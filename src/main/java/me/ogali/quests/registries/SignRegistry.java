package me.ogali.quests.registries;

import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class SignRegistry {

    private final Set<Location> signLocationSet = new HashSet<>();

    public void registerSignLocation(Location location) {
        signLocationSet.add(location);
    }

    public void unRegisterSignLocation(Location location) {
        signLocationSet.remove(location);
    }

    public Set<Location> getSignLocationSet() {
        return signLocationSet;
    }

}
