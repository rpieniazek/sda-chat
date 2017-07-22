package com.sda;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
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


            System.out.println("--------------------------");
            //Sprawdź, czy jest osoba, której ulica zaczyna  sie na jakas litere
            personList
                    .stream()
                    .filter(p -> p.address.street.toUpperCase().startsWith("N"))
                    .findAny()
                    .ifPresent(p -> System.out.println(p.name));

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

            System.out.println("-----------------------");

            // 2. method Sprawdź, czy jest osoba, której ulica zaczyna  sie na jakas litere

            String streetOpt = personList.stream()
                    .filter(p -> p.address.street.toUpperCase().startsWith("N"))
                    .map(p -> p.name)
                    .findAny()
                    .orElse("Person not found");
            System.out.println(streetOpt);

            System.out.println("-----------------------");
            //Posortuj osoby wg username
            personList.stream()
                    .sorted((p1,p2) -> p1.username.compareTo(p2.username))
                    .forEach(p -> System.out.println(p.username));
            System.out.println("-----------------------");
            //Wyswietlic srednia dlugosc username, najwieksza dlugosc, najmniejsza.

            System.out.println("Maximum username length");
            personList.stream()
                    .mapToInt(n -> n.username.length())
                    .max()
                    .ifPresent(System.out::println);
            System.out.println("Person who has maximum username length:");
            personList.stream()
                    .max(Comparator.comparing(p -> p.username.length()))
                    .ifPresent(System.out::println);

            System.out.println("Minimum username length");
            personList.stream()
                    .mapToInt(n -> n.username.length())
                    .min()
                    .ifPresent(System.out::println);
            System.out.println("Person who has minimum username length:");
            personList.stream()
                    .min(Comparator.comparing(p -> p.username.length()))
                    .ifPresent(System.out::println);

            System.out.println("Average username length");
            personList.stream()
                    .mapToInt(n -> n.username.length())
                    .average()
                    .ifPresent(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
