package com.sda;

import java.util.function.Consumer;

/**
 * Created by RENT on 2017-07-21.
 */
public class MyConsumer implements Consumer<Person> {
    @Override
    public void accept(Person p) {
        System.out.println(p);
    }
}
