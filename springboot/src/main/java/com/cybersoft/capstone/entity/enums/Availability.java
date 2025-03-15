package com.cybersoft.capstone.entity.enums;

public enum Availability {
    AVAILABLE_NOW("Available Now"),
    COMING_SOON("Coming Soon"),
    PRE_ORDER("Pre-Order"),
    NEW_RELEASE("New Release");

    private final String displayName;

    Availability(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
