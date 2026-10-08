
package model;

import enums.CargoType;

public class Cargo {

private String cargoId;
private String description;
private CargoType cargoType;
private double weight;
private Ship ship;
private boolean loaded;

public Cargo(String cargoId, String description,
             CargoType cargoType, double weight) {

    this.cargoId = cargoId;
    this.description = description;
    this.cargoType = cargoType;
    this.weight = weight;
    this.loaded = false;
}

public String getCargoId() {
    return cargoId;
}

public String getDescription() {
    return description;
}

public CargoType getCargoType() {
    return cargoType;
}

public double getWeight() {
    return weight;
}

public Ship getShip() {
    return ship;
}

public void setShip(Ship ship) {
    this.ship = ship;
}

public boolean isLoaded() {
    return loaded;
}

public void setLoaded(boolean loaded) {
    this.loaded = loaded;
}

public void displayDetails() {
    System.out.println(
            cargoId + " | " + description
            + " | " + cargoType
            + " | Weight: " + weight
            + " | Ship: "
            + (ship == null ? "None" : ship.getShipId())
            + " | Loaded: " + loaded
    );
}

}
