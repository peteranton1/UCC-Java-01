package org.example.generic.ex02;

import java.util.Random;

public abstract class Animal {

    private final String name;

    protected Animal() {
        this(null);
    }

    protected Animal(String name) {
        if (name == null || name.isEmpty()) {
            name = randomString();
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }

    public void eat() {
        String myName = getClass().getSimpleName() + " " + getName();
        System.out.println(myName + " is eating.");
    }

    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int len = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
