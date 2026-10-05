package model;

import enums.ShipType;
import enums.Status;

public class ContainerShip extends Ship {

    private int containerCapacity;

    public ContainerShip(String shipId, String shipName, ShipType shipType, Status status, int containerCapacity) {
        super(shipId, shipName, shipType, status);
        this.containerCapacity = containerCapacity;
    }

    public int getContainerCapacity() {
        return containerCapacity;
    }

    public void setContainerCapacity(int containerCapacity) {
        this.containerCapacity = containerCapacity;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Container Capacity: " + containerCapacity);
    }
}