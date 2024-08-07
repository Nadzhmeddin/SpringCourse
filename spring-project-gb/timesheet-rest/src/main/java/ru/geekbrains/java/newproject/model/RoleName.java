package ru.geekbrains.java.newproject.model;

public enum RoleName {

    ADMIN("admin"),
    USER("user"),
    REST("rest"),
    DEVELOPER("developer");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
