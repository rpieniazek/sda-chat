package com.sda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.collections.MappingChange;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-07-20.
 */
public class Main {
    //stworz klase Address(ulica, miasto),
    //dodaj w klasie Address  pole reprezentujacej lokatorow
    //zainicjuj przykladowymi zmiennymi
//        objectMapper.writerWithDefaultPrettyPrinter();

    public static void main(String[] args) {


        Person person = new Person("John","Clark");
        Person person2 = new Person("Barack","Obama");
        ObjectMapper objectMapper = new ObjectMapper();
        Person [] tab = {person, person2 };

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
    }
}
