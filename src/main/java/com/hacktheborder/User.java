package com.hacktheborder;

public class User {
    private String lastName;
    private String firstName;
    private int epccIdNumber;
    private int numLives;

    public User() {
        this(null, null, -1);
    }

    public User(String lastName, String firstName, int epccIdNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.epccIdNumber = epccIdNumber;
        this.numLives = 3;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public int getEPCCIDNumber() {
        return this.epccIdNumber;
    }

    public int getNumLives() {
        return this.numLives;
    }

    public void decreaseLife() {
        this.numLives--;
    }
}
