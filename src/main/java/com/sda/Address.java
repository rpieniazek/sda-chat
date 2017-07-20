package com.sda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-07-20.
 */
public class Address {

    //stworz klase Address(ulica, miasto),
    //dodaj w klasie Address  pole reprezentujacej lokatorow
    //zainicjuj przykladowymi zmiennymi

    public String street;
    public String city;
    public List<Person> personList;



    public Address(String street,String city){
        this.street = street;
        this.city = city;
        this.personList = new ArrayList<Person>();
    }

    public Address(String street, String city, List<Person> persons) {
        this(street, city);
        this.personList = persons;
    }

    public void addLocator(Person person){
        personList.add(person);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
