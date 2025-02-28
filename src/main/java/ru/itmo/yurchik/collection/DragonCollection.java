package ru.itmo.yurchik.collection;

import ru.itmo.yurchik.model.*;

import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class DragonCollection {
    private final ArrayDeque<Dragon> dragons = new ArrayDeque<>();
    private final ZonedDateTime initializationTime;
    public DragonCollection() {
        this.initializationTime = ZonedDateTime.now();
    }
    public ZonedDateTime getInitializationTime() {
        return initializationTime;
    }
    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public void removeDragon(Dragon dragon) {
        dragons.remove(dragon);
    }

    public ArrayDeque<Dragon> getDragons() {
        return dragons;
    }
}
