package com.app.repositories;

import com.app.entities.Airport;
import com.app.entities.Trie;
import com.app.repositories.interfaces.IAirportRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CsvAirportRepository implements IAirportRepository {

    private String _dataPath;

    public CsvAirportRepository(String dataPath) {
        _dataPath = dataPath;
    }


    @Override
    public List<Airport> getAllAirports(int column) {
        List<Airport> airports = new ArrayList<Airport>();
        Airport airport;
        try (BufferedReader br = new BufferedReader(new FileReader(_dataPath))){
            String line;
            String[] tokens;
            while ((line = br.readLine()) != null) {
                tokens = line.split(",");
                airport = new Airport();
                airport.id = Short.parseShort(tokens[0]);
                airport.value = removeQuotes(tokens[column-1]);
                airports.add(airport);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(airports);
        return airports;
    }

    @Override
    public Trie loadAirportsInBatches(List<Airport> airports) {
        int batchSize = 500;
        Trie trie = new Trie();
        int counter = 0;

        Iterator<Airport> iterator = airports.iterator();

        while (iterator.hasNext() && counter < batchSize) {
            Airport airport = iterator.next();
            trie.insert(airport.id, airport.value);
            iterator.remove();
            counter++;
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
