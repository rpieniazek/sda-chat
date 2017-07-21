package com.sda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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

        //List<Person> personList = new ArrayList<>();
        //personList.add(person);
        List<Person> personList = singletonList(person);

        System.out.println("wersja1");
        personList.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });

        System.out.println("wersja2");
        personList.forEach(new MyConsumer());

        System.out.println("wersja3");
        personList.forEach((personConsumer) -> {
            System.out.println(personConsumer);
        });

        System.out.println("wersja4");
        personList.forEach(System.out::println);

        //wyswietlenie tylko adresu:

        personList.stream()
                .map(p -> p.address)
                .peek(System.out::println)
                .map(address -> printAddress(address))
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println("---------------");

        for(int i = 0 ; i<10; i++){
            System.out.println(i);
        }
        System.out.println("---------------");
        IntStream.range(0,10)
                .forEach(i -> System.out.println(i));
    }

    public static String printAddress(Address adddress) {
        return "Addres is " + adddress.city + " "+ adddress.street;
    }
}
