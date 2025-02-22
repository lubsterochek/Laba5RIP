package ru.itmo.yurchik.collection;

import ru.itmo.yurchik.model.*;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DragonCollection {
    private ArrayDeque<Dragon> dragons = new ArrayDeque<>();
    private ArrayList<Integer> dragonsIds = new ArrayList<>();

    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
        dragonsIds.add(dragon.getId());
    }

    public void removeDragon(Dragon dragon) {
        dragons.remove(dragon);
        dragonsIds.remove(dragon.getId());
    }

    public ArrayDeque<Dragon> getDragons() {
        return dragons;
    }
    public ArrayList<Integer> getDragonIds() {
        return dragonsIds;
        }
}
