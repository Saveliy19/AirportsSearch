package com.utils;

import com.utils.interfaces.IArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser implements IArgumentParser {
    private final Map<String, String> arguments;

    public ArgumentParser(String[] args) {
        this.arguments = new HashMap<String, String>();
        parseArguments(args);
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length - 1; i += 2) {
            if (args[i].startsWith("--")) arguments.put(args[i], args[i + 1]);
            else throw new IllegalArgumentException("Invalid argument format" + args[i]);
        }
    }

    public String get(String key) {
        if (!arguments.containsKey(key)) throw new IllegalArgumentException("Missing required argument: " + key);
        return arguments.get(key);
    }
}
