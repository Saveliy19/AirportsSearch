package com.app.entities;

public class Airport implements Comparable<Airport> {
    public short id;
    public String value;

    @Override
    public int compareTo(Airport other) {
        try {
            double thisValueAsNumber = Double.parseDouble(this.value);
            double otherValueAsNumber = Double.parseDouble(other.value);
            return Double.compare(thisValueAsNumber, otherValueAsNumber);
        } catch (NumberFormatException e) {
            return this.value.compareTo(other.value);
        }
    }
}
