package org.example.project.ex02;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex02Streams {
    /*
    Write a method and test that does the following:

    Input argument : String
    Output result: List of counts of characters encountered.

    Restraints.
    1. Treat uppercase as the same as lowercase.
    2. Ignore characters that are not letters.
    3. Sort the result by alphabetic order.

    Example 1: "Hello" becomes
                    [e=1, h=1, l=2, o=1]

    Example 2: "Java is cool!" becomes
                    [a=2, c=1, i=1, j=1, l=1, o=2, s=1, v=1]
     */
    public List<String> charSorter(String input) {
        if(input == null) {
            return List.of();
        }
        Stream<Character> chars = input
            .toLowerCase()
            .chars()
            .mapToObj(i -> (char)i);

        var grp = chars.filter(Character::isAlphabetic)
            .collect(Collectors.groupingBy(c -> c));

        return grp.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue().size())
            .sorted()
            .toList();
    }

}
