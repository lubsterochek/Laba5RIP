package ru.itmo.yurchik.command.base;

import java.util.HashMap;

/**
 * Класс, через который можно получить хэш мапу с командами
 */
public final class Environment {
    private final HashMap<String, Command> stringCommandHashMap;

    /**
     * Конструктор
     * @param stringCommandHashMap0
     */
    public Environment(HashMap<String, Command> stringCommandHashMap0) {
        this.stringCommandHashMap = stringCommandHashMap0;
    }

    /**
     * Получить список команд
     * @return
     */
    public HashMap<String, Command> getStringCommandHashMap() {
        return stringCommandHashMap;
    }
}
