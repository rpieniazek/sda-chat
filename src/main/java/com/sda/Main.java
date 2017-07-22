package com.sda;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.cars.Car;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        File jsonArrayFile = new File("list.json");
        try {
            List<Car> cars = objectMapper.readValue(jsonArrayFile, new TypeReference<List<Car>>() {});
            System.out.println(cars);
            //Pogrupuj elementy wg marki samochodu. Czyli chciałbym mieć mape z marki na liste jej samochodów.
            Map<String, List<Car>> listMap = cars
                    .stream()
                    .collect(Collectors.groupingBy(c -> c.type));
            listMap.forEach((k, v) -> System.out.println(k + " " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
