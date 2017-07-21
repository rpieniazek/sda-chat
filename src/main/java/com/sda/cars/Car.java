package com.sda.cars;

/**
 * Created by RENT on 2017-07-21.
 */
public class Car {

    public String color;
    public String type;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
