package com.app.repositories.interfaces;

import com.app.entities.Airport;
import com.app.entities.Trie;

import java.util.List;

public interface IAirportRepository {

    public List<Airport> getAllAirports(int column);
    public Trie loadAirportsInBatches(List<Airport> airports);
}