package com.app.entities;

public class Airport implements Comparable<Airport> {
    public short id;
    public String value;

    @Override
    public int compareTo(Airport other) {
        return this.value.compareTo(other.value);
    }
}
