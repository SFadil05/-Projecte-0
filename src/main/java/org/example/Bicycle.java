package org.example;

public class Bicycle extends Vehicle {
    private boolean isElectric;

    public Bicycle(String licensePlate, int timesUsed, boolean isCompetition, boolean isElectric) {
        super(licensePlate, timesUsed, isCompetition, "Bicycle");
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String getVehicleDetails() {
        return String.format("Bicycle [License Plate: %s, Times Used: %d, Competition: %b, Electric: %b]",
                getLicensePlate(), getTimesUsed(), isCompetition(), isElectric);
    }
}
