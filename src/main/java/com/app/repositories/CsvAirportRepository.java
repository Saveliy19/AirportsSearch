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
    public Trie getAllAirports(int column) {
        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new FileReader(_dataPath))) {
            String line;
            String[] data;

            while ((line = br.readLine()) != null) {
                data  = line.split(",");
                trie.insert(Short.parseShort(removeQuotes(data[0])), removeQuotes(data[column-1]));
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
