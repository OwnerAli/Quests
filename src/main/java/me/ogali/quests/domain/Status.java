package me.ogali.quests.domain;

public enum Status {

    NOT_STARTED("Not Started"), DOING("In Progress"), DONE("Complete");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
