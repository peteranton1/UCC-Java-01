package org.example.project.ex02;

import java.util.List;
import java.util.stream.Collectors;

public class Ex02aStreams {
    /*
    Write a method and test that does the following:

    Input argument : String
    Output result: List of characters encountered with counts.

    Restraints.
    1. Treat uppercase as the same as lowercase.
    2. Ignore characters that are not letters.
    3. Sort the result by alphabetic order.

    Example 1: "Hello" becomes
                    [e=1, h=1, l=2, o=1]
                    e=1, h=1, l=2, o=1

    Example 2: "Java is cool!" becomes
                    [a=2, c=1, i=1, j=1, l=1, o=2, s=1, v=1]
                    a=2, c=1, i=1, j=1, l=1, o=2, s=1, v=1
     */
    public List<String> charStream(String input) {
        if (input == null || input.isEmpty()) {
            return List.of();
        }
        // Turn input into stream of characters.
        var chars = input
            .toLowerCase()
            .chars()
            .mapToObj(i -> (char) i);

        // Remove non-alpha and group into a map
        var gpc = chars
            .filter(Character::isAlphabetic)
            .collect(Collectors.groupingBy(c -> c));

        // Sort list and reformat to show value and size
        return gpc.entrySet().stream()
            .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
            .map(e -> e.getKey() + "=" + e.getValue().size())
            .toList();
    }
}
