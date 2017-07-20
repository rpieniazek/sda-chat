package com.sda;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by RENT on 2017-07-20.
 */
public class Person {

    @JsonProperty(value = "firstName")
    public String name;
    @JsonProperty(value = "surname")
    public String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
