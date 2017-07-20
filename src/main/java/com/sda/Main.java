package com.sda;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by RENT on 2017-07-20.
 */
public class Main {
    //stworz klase Address(ulica, miasto),
    //dodaj w klasie Address  pole reprezentujacej lokatorow
    //zainicjuj przykladowymi zmiennymi
//        objectMapper.writerWithDefaultPrettyPrinter();

    public static void main(String[] args) {


        Person person = new Person("John", "Clark");
        Person person2 = new Person("Barack", "Obama");
        ObjectMapper objectMapper = new ObjectMapper();
        Person[] tab = {person, person2};

        try {
            System.out.println(objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(DistrictFactory.create()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Address adres1 = new Address("Dubois", "Wroclaw");

        adres1.addLocator(person);
        adres1.addLocator(person2);

        try {
            System.out.println(objectMapper.writeValueAsString(person));
            System.out.println(objectMapper.writeValueAsString(tab));
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(adres1));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String response = "{\n" +
                "  \"firstname\": \"John\",\n" +
                "  \"lastname\": \"Doe\",\n" +
                "  \"memberStatus\": \"Full\"\n" +
                "}";

        try {
            Person person1 = objectMapper.readValue(response, Person.class);
            System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
