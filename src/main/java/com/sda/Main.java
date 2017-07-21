package com.sda;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.cars.Car;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by RENT on 2017-07-20.
 */
public class Main {
    //na repozytorium znajduje sie plik person2.json
    //trzeba dostosowac strukture naszych klas, aby mozliwe bylo jego parsowanie
    //cala reszte klas mozna usunac, bo mamy je 'w razie potrzeby' na branchu step1
    //nastepnym etapem bedzie pobranie jsona z url
    //przydatne beda klasy URlConnection i URl.

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        File jsonArrayFile = new File("list.json");
        try {
            List<Car> cars = objectMapper.readValue(jsonArrayFile, new TypeReference<List<Car>>() {
            });
            System.out.println(cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
