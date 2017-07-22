package com.sda;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.*;


/**
 * Created by RENT on 2017-07-20.
 */
public class Main {

    public static void main(String[] args) {


        ObjectMapper objectMapper = new ObjectMapper();
        try {

            URL urlPersonsList = new URL("https://jsonplaceholder.typicode.com/users");
            List<Person> personList = objectMapper.readValue(urlPersonsList, new TypeReference<List<Person>>() {
            });
            System.out.println(personList);

            //Zrób liste mailingową
            personList.stream()
                    .map(person -> person.email)
                    .forEach(System.out::println);

            //Sprawdź, czy jest osoba, której ulica zaczyna  sie na jakas litere

            System.out.println("--------------------------");
            personList
                    .stream()
                    .filter(p -> p.address.street.toUpperCase().startsWith("N"))
                    .findAny().ifPresent(p -> System.out.println(p.name));

            Optional<Person> person2 = personList
                    .stream()
                    .filter(p -> p.address.street.toUpperCase().startsWith("N"))
                    .findAny();


            System.out.println("-----------------------");
            if (person2.isPresent()) {
                System.out.println(person2.get().name);
            }
            else {
                System.out.println("Nobody live in street starts with N");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
