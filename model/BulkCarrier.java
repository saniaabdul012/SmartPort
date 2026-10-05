package model;

import enums.ShipType;
import enums.Status;

public class BulkCarrier extends Ship {

    private double cargoCapacity;

    public BulkCarrier(String shipId, String shipName, ShipType shipType, Status status, double cargoCapacity) {
        super(shipId, shipName, shipType, status);
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Cargo Capacity: " + cargoCapacity);
    }
}