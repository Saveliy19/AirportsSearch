package com.app;

import com.app.entities.Airport;
import com.app.entities.SearchResult;
import com.app.entities.Trie;
import com.app.repositories.CsvAirportRepository;
import com.app.repositories.interfaces.IAirportRepository;
import com.utils.ArgumentParser;
import com.utils.InputFileReader;
import com.utils.JsonFileWriter;
import com.utils.interfaces.IArgumentParser;
import com.utils.interfaces.IFileReader;
import com.utils.interfaces.IFileWriter;
import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();

        IArgumentParser argParser = new ArgumentParser(args);
        IFileReader fileReader = new InputFileReader();
        IFileWriter fileWriter = new JsonFileWriter();

        String dataPath = argParser.get("--data");
        String inputPath = argParser.get("--input-file");
        String outputPath = argParser.get("--output-file");
        int column = Integer.parseInt(argParser.get("--indexed-column-id"));

        IAirportRepository _airportRepository = new CsvAirportRepository(dataPath);

        List<String> searchWords = fileReader.readFile(inputPath);

        long initTime = System.currentTimeMillis() - startTime;

        long searchStartTime;
        long searchTime;
        Trie trie;
        SearchResult sr;

        List<Airport> airports = _airportRepository.getAllAirports(column);

        Map<String, SearchResult> searchResults = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            trie = _airportRepository.loadAirportsInBatches(airports);

            for (String word : searchWords) {
                searchStartTime = System.currentTimeMillis();
                List<Short> ids = trie.searchIds(word);
                searchTime = System.currentTimeMillis() - searchStartTime;

                if (searchResults.containsKey(word)) {
                    sr = searchResults.get(word);
                    searchResults.get(word).ids.addAll(ids);
                }
                else {
                    sr = new SearchResult();
                    sr.ids = ids;
                    searchResults.put(word, sr);
                }
                sr.time += searchTime;
            }
        }

        List<Map<String, Object>> results = new ArrayList<>();
        for (String word : searchWords) {
            Map<String, Object> resultEntry = new LinkedHashMap<>();
            resultEntry.put("search", word);
            resultEntry.put("result", searchResults.get(word).ids);
            resultEntry.put("time", searchResults.get(word).time);

            results.add(resultEntry);
        }

        Map<String, Object> outputData = new LinkedHashMap<>();
        outputData.put("initTime", initTime);
        outputData.put("result", results);

        fileWriter.writeJsonFile(outputPath, outputData);
    }
}
