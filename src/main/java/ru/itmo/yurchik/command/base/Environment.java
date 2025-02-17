package ru.itmo.yurchik.command.base;

import java.util.HashMap;

public final class Environment {
    private final HashMap<String, Command> stringCommandHashMap;

    public Environment(HashMap<String, Command> stringCommandHashMap0) {
        this.stringCommandHashMap = stringCommandHashMap0;
    }

    public HashMap<String, Command> getStringCommandHashMap() {
        return stringCommandHashMap;
    }
}
