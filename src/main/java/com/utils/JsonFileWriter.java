package com.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonFileWriter {
    private JsonFileWriter() {}

    public static void writeJsonFile(String outputFilePath, Map<String, Object> outputData) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
            gson.toJson(outputData, fileWriter);
        }
    }
}
