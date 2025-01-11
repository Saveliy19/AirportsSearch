package com.utils;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {
    private final Map<String, String> arguments;

    public ArgumentParser(String[] args) {
        this.arguments = new HashMap<String, String>();
        parseArguments(args);
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length - 1; i += 2) {
            String key = args[i];
            String value = args[i + 1];
            if (key.startsWith("--")) arguments.put(key, value);
            else throw new IllegalArgumentException("Invalid argument format" + key);
        }
    }

    public String get(String key) {
        if (!arguments.containsKey(key)) throw new IllegalArgumentException("Missing required argument: " + key);

        return arguments.get(key);
    }
}
