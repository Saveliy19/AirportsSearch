package com.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.interfaces.IFileWriter;


public class JsonFileWriter implements IFileWriter {
    public JsonFileWriter() {}

    public void writeJsonFile(String outputFilePath, Map<String, Object> outputData) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
            gson.toJson(outputData, fileWriter);
        }
    }
}
