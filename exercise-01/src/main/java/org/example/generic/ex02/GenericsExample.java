package org.example.generic.ex02;

import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {

        List<Cat> cats = List.of(
                new Cat(),
                new Cat("George"),
                new Cat());

        List<Dog> dogs = List.of(
                new Dog(),
                new Dog("George"),
                new Dog());

        printListCats(cats);
        printListDogs(dogs);
        printList(cats);
        printList(dogs);
    }

    private static void printListCats(List<Cat> cats) {
        System.out.println(cats);
    }
    private static void printListDogs(List<Dog> dogs) {
        System.out.println(dogs);
    }
    private static <T> void printList(List<T> theList) {
        System.out.println(theList);
    }
}
