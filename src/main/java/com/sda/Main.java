package com.sda;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
