package org.example;

public abstract class Vehicle {
    private String licensePlate;
    private int timesUsed;
    private boolean isCompetition;
    private String type;

    public Vehicle(String licensePlate, int timesUsed, boolean isCompetition, String type) {
        this.licensePlate = licensePlate;
        this.timesUsed = timesUsed;
        this.isCompetition = isCompetition;
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }

    public boolean isCompetition() {
        return isCompetition;
    }

    public void setCompetition(boolean competition) {
        isCompetition = competition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String getVehicleDetails();
}
