package com.journal.journal.object;

import java.util.*;
import java.util.stream.*;

public class ObjectMap {

    public static void main(String[] args) {
        // Example: array of key-value pairs
        String[][] entries = {
                {"a", "1"},
                {"b", "2"},
                {"c", "3"}
        };

        // Convert array to map
        Map<String, String> map = Arrays.stream(entries)
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

        // Print the map
        System.out.println("Map: " + map);

        // Simple assertions (manual)
        assert "1".equals(map.get("a")) : "Value for 'a' should be '1'";
        assert "2".equals(map.get("b")) : "Value for 'b' should be '2'";
        assert "3".equals(map.get("c")) : "Value for 'c' should be '3'";
        assert map.size() == 3 : "Map size should be 3";

        System.out.println("All tests passed!");
    }
}
