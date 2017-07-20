package com.sda;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by RENT on 2017-07-20.
 */
public class Person {

    @JsonProperty(value = "firstname")
    public String name;

    @JsonProperty(value = "lastname")
    public String lastName;

    public String memberStatus;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", memberStatus='" + memberStatus + '\'' +
                '}';
    }
}
