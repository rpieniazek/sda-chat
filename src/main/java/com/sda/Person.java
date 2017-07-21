package com.sda;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by RENT on 2017-07-20.
 */
public class Person {

    public int id;
    public String name;
    public String username;
    public String email;
    public String phone;
    public String website;
    public Address address;
    public Company company;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", address=" + address +
                ", company=" + company +
                '}';
    }
}
