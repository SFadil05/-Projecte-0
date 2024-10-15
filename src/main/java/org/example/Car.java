package org.example;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String licensePlate, int timesUsed, boolean isCompetition, int numberOfDoors) {
        super(licensePlate, timesUsed, isCompetition, "Car");
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getVehicleDetails() {
        return String.format("Car [License Plate: %s, Times Used: %d, Competition: %b, Doors: %d]",
                getLicensePlate(), getTimesUsed(), isCompetition(), numberOfDoors);
    }
}
