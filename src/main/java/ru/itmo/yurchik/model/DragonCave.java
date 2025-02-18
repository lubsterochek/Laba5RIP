package ru.itmo.yurchik.model;

public class DragonCave {
    private double depth;
    private Float numberOfTreasures; //Поле может быть null, Значение поля должно быть больше 0
    public DragonCave(double depth, Float numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public Float getNumberOfTreasures() {
        return numberOfTreasures;
    }

    public void setNumberOfTreasures(Float numberOfTreasures) {
        if (numberOfTreasures != null) {
            this.numberOfTreasures = numberOfTreasures;
        } else {
            throw new IllegalArgumentException("NumberOfTreasures не может быть пустым или отрицательным");
        }
    }
}
