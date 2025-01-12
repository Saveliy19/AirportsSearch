package com.app.repositories.interfaces;

import com.app.entities.Trie;

public interface IAirportRepository {

    public Trie loadAirportsInBatches(int column, int iterationNumber);
}