package model;

import enums.CargoType;

public class Cargo {

    private String cargoId;
    private String description;
    private CargoType cargoType;
    private double weight;

    public Cargo(String cargoId, String description, CargoType cargoType, double weight) {
        this.cargoId = cargoId;
        this.description = description;
        this.cargoType = cargoType;
        this.weight = weight;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void displayDetails() {
        System.out.println("Cargo ID: " + cargoId);
        System.out.println("Description: " + description);
        System.out.println("Cargo Type: " + cargoType);
        System.out.println("Weight: " + weight);
    }
}