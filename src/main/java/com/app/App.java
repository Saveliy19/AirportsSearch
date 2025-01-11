package com.app;

import com.app.entities.Trie;
import com.app.repositories.CsvAirportRepository;
import com.app.repositories.interfaces.IAirportRepository;
import com.utils.ArgumentParser;
import com.utils.InputFileReader;
import com.utils.JsonFileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        ArgumentParser argParser = new ArgumentParser(args);

        String dataPath = argParser.get("--data");
        String inputPath = argParser.get("--input-file");
        String outputPath = argParser.get("--output-file");
        int column = Integer.parseInt(argParser.get("--indexed-column-id"));

        IAirportRepository _airportRepository = new CsvAirportRepository(dataPath);

        Trie trie = _airportRepository.getAllAirports(column);

        ArrayList<String> searchWords = InputFileReader.readFile(inputPath);

        long initTime = System.currentTimeMillis() - startTime;

        ArrayList<Map<String, Object>> results = new ArrayList<>();
        for (String searchWord : searchWords) {
            long searchStartTime = System.currentTimeMillis();

            ArrayList<Short> ids = trie.searchIds(searchWord);

            long searchTime = System.currentTimeMillis() - searchStartTime;

            Map<String, Object> resultEntry = new HashMap<>();
            resultEntry.put("search", searchWord);
            resultEntry.put("result", ids);
            resultEntry.put("time", searchTime);

            results.add(resultEntry);
        }

        Map<String, Object> outputData = new HashMap<>();
        outputData.put("initTime", initTime);
        outputData.put("result", results);

        JsonFileWriter.writeJsonFile(outputPath, outputData);
    }
}
