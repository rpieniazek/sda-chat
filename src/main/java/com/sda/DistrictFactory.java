package com.sda;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-07-20.
 */
public class DistrictFactory {

    public static Map<String, List<Address>> create() {
        Map<String, List<Address>> districts = new HashMap<String, List<Address>>();
        districts.put("Krzyki", createAddresses());
        return districts;
    }

    private static List<Address> createAddresses() {
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(new Address(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(8),
                createPersons()));
        addresses.add(new Address(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(8),
                createPersons()));
        return addresses;
    }

    private static List<Person> createPersons() {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(8)));
        persons.add(new Person(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(8)));
        return persons;
    }
}
