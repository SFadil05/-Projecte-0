package org.example;

public class Motorbike extends Vehicle {
    private boolean hasTrunk;

    public Motorbike(String licensePlate, int timesUsed, boolean isCompetition, boolean hasTrunk) {
        super(licensePlate, timesUsed, isCompetition, "Motorbike");
        this.hasTrunk = hasTrunk;
    }

    public boolean hasTrunk() {
        return hasTrunk;
    }

    public void setHasTrunk(boolean hasTrunk) {
        this.hasTrunk = hasTrunk;
    }

    @Override
    public String getVehicleDetails() {
        return String.format("Motorbike [License Plate: %s, Times Used: %d, Competition: %b, Has Trunk: %b]",
                getLicensePlate(), getTimesUsed(), isCompetition(), hasTrunk);
    }
}
