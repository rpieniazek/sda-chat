package com.sda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-20.
 */
public class Address {

    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }
}
