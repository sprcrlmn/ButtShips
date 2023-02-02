package org.example;

public enum Ships {
    AIRCRAFTCARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser",3),
    DESTROYER("Destroyer", 2);
    private final String name;
    private final int len;


    Ships(String name, int len) {
        this.name = name;
        this.len = len;
    }


    public String getName() {
        return this.name;
    }

    public int getLen() {
        return this.len;
    }
}
