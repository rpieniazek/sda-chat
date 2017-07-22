package com.sda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Person person =  objectMapper.readValue( new URL("https://jsonplaceholder.typicode.com/users/3"), Person.class);
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
