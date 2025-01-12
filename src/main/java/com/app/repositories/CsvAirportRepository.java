package com.app.repositories;

import com.app.entities.Trie;
import com.app.repositories.interfaces.IAirportRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvAirportRepository implements IAirportRepository {

    private String _dataPath;

    public CsvAirportRepository(String dataPath) {
        _dataPath = dataPath;
    }


    @Override
    public Trie loadAirportsInBatches(int column, int iterationNumber) {
        int batchSize = 1000;
        int counter = 0;
        int startRow = iterationNumber * batchSize;

        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new FileReader(_dataPath))) {
            String line;
            String[] data;

            for (int i = 0; i < startRow && br.readLine() != null; i++) {

            }

            while ((line = br.readLine()) != null && counter < batchSize) {
                data = line.split(",");
                trie.insert(Short.parseShort(removeQuotes(data[0])), removeQuotes(data[column - 1]));
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trie;
    }

    private String removeQuotes(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

}
