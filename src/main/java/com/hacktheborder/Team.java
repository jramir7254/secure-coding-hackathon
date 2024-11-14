package com.hacktheborder;

public class Team {
    private String teamName;
    private int numMembers;
    private int epccIdNumber;
    private int teamScore;

    public Team() {
        this(null, 0, -1, 0);
    }

    public Team(String teamName, int numMembers, int epccIdNumber, int teamScore) {
        this.teamName = teamName;
        this.numMembers = numMembers;
        this.epccIdNumber = epccIdNumber;
        this.teamScore = teamScore;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getNumMembers() {
        return this.numMembers;
    }

    public int getEPCCIDNumber() {
        return this.epccIdNumber;
    }

    public int getTeamScore() {
        return this.teamScore;
    }

   
}
