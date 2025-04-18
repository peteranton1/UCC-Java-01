package org.tabular.accum;

import org.tabular.accum.readers.PlainTextBuilder;

import java.util.HashMap;
import java.util.Map;

public class ReaderFactory {
    private final Map<String, ReaderBuilder> register;

    public ReaderFactory() {
        register = new HashMap<>();
        register.put("plainText", new PlainTextBuilder());
    }

    public ReaderBuilder forName(String readerName) {

        for (var entry : register.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(readerName)) {
                return entry.getValue().newBuilder();
            }
        }
        String message = String.format("Unknown readerName: '%s', available: %s",
            readerName, register.keySet());
        throw new TabularException(message);
    }
}
