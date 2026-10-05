package model;

import enums.ShipType;
import enums.Status;

public class Tanker extends Ship {

    private double liquidCapacity;

    public Tanker(String shipId, String shipName, ShipType shipType, Status status, double liquidCapacity) {
        super(shipId, shipName, shipType, status);
        this.liquidCapacity = liquidCapacity;
    }

    public double getLiquidCapacity() {
        return liquidCapacity;
    }

    public void setLiquidCapacity(double liquidCapacity) {
        this.liquidCapacity = liquidCapacity;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Liquid Capacity: " + liquidCapacity);
    }
}