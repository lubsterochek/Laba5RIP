package ru.itmo.yurchik.model;

public class Coordinates {
    private float x;
    private double y; //Максимальное значение поля: 273
    public Coordinates(float x, double y) {
        this.x = x;
        if(y<=273){
            this.y = y;
        }else {
            throw new IllegalArgumentException("Значение y не должно быть больше 273");
        }
    }
}
