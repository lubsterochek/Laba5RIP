package ru.itmo.yurchik.collection;

import ru.itmo.yurchik.model.*;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DragonCollection {
    private ArrayDeque<Dragon> dragons = new ArrayDeque<>();

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
