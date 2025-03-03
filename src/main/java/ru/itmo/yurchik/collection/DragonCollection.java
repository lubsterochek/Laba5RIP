package ru.itmo.yurchik.collection;

import ru.itmo.yurchik.model.*;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Класс с коллекцией драконов
 */
public class DragonCollection {
    /** Поле коллекции */
    private final ArrayDeque<Dragon> dragons = new ArrayDeque<>();
/** Поле даты создания */
    private final ZonedDateTime initializationTime;

    /**
     * Конструктор, устанавливает текущее время
     */
    public DragonCollection() {
        this.initializationTime = ZonedDateTime.now();
    }

    /**
     * Возвращает время
     * @return
     */
    public ZonedDateTime getInitializationTime() {
        return initializationTime;
    }

    /**
     * Добавить дракона
     * @param dragon
     */
    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public void removeDragon(Dragon dragon) {
        dragons.remove(dragon);
    }

    /**
     * получить дракона
     * @return
     */
    public ArrayDeque<Dragon> getDragons() {
        return dragons;
    }


}
