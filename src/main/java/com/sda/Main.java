package com.sda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.*;


/**
 * Created by RENT on 2017-07-20.
 */
public class Main {
    //na repozytorium znajduje sie plik person2.json
    //trzeba dostosowac strukture naszych klas, aby mozliwe bylo jego parsowanie
    //cala reszte klas mozna usunac, bo mamy je 'w razie potrzeby' na branchu step1
    //nastepnym etapem bedzie pobranie jsona z url
    //przydatne beda klasy URlConnection i URl.


    //znalezc Osoby ktorych email zaczyna sie na 'N'
    //wypisac tylko adresy dla kazdej osoby


    public static void main(String[] args) {
        File file = new File("person2.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = null;
        try {
            person = objectMapper.readValue(file, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List<Person> persons = new ArrayList<>();
        //persons.add(person);
        List<Person> persons = singletonList(person);

        System.out.println("wersja1");
        persons.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });

        System.out.println("wersja2");
        persons.forEach(new MyConsumer());

        System.out.println("wersja3");
        persons.forEach(personConsumer -> {
            System.out.println(personConsumer);
        });

        System.out.println("wersja4");
        persons.forEach(System.out::println);
    }
}
