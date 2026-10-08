import java.util.Scanner;

import model.Ship;
import model.ContainerShip;
import model.BulkCarrier;
import model.Tanker;
import model.Cargo;
import model.Berth;
import model.Crane;

import enums.ShipType;
import enums.CargoType;
import enums.Status;

import operations.Loading;
import operations.Unloading;

import management.Port;

import exceptions.BerthUnavailableException;
import exceptions.InvalidCargoException;
import exceptions.CraneUnavailableException;

import utils.FileManager;

public class Main {

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    Port port = new Port("SmartPort");

    port.addBerth(new Berth("B01", "Berth 1", false));
    port.addBerth(new Berth("B02", "Berth 2", false));
    port.addCrane(new Crane("C01", "Main Crane", true));

    int choice;

    do {
        System.out.println("\n===== SMARTPORT =====");
        System.out.println("1. Add Ship");
        System.out.println("2. Add Cargo");
        System.out.println("3. Assign Berth");
        System.out.println("4. Load Cargo");
        System.out.println("5. Unload Cargo");
        System.out.println("6. Display Port");
        System.out.println("7. Save Data");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                addShip(sc, port);
                break;

            case 2:
                addCargo(sc, port);
                break;

            case 3:
                System.out.print("Enter Ship ID: ");
                Ship ship = port.getShipById(sc.nextLine());

                if (ship == null) {
                    System.out.println("Ship not found.");
                } else {
                    try {
                        port.assignBerth(ship);
                    } catch (BerthUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

            case 4:
                operateCargo(sc, port, true);
                break;

            case 5:
                operateCargo(sc, port, false);
                break;

            case 6:
                port.displayPortDetails();
                break;

            case 7:
                FileManager.writeData(port.getSummary());
                break;

            case 0:
                System.out.println("Thank you for using SmartPort.");
                break;

            default:
                System.out.println("Invalid choice.");
        }

    } while (choice != 0);

    sc.close();
}

private static void addShip(Scanner sc, Port port) {

    System.out.print("Enter Ship ID: ");
    String id = sc.nextLine();

    System.out.print("Enter Ship Name: ");
    String name = sc.nextLine();

    System.out.println("1. Container Ship");
    System.out.println("2. Bulk Carrier");
    System.out.println("3. Tanker");
    System.out.print("Choose Ship Type: ");
    int type = sc.nextInt();

    System.out.println("1. WAITING");
    System.out.println("2. DOCKED");
    System.out.println("3. LOADING");
    System.out.println("4. UNLOADING");
    System.out.println("5. DEPARTED");
    System.out.print("Choose Status: ");
    int statusChoice = sc.nextInt();

    Status status;

    switch (statusChoice) {
        case 1:
            status = Status.WAITING;
            break;
        case 2:
            status = Status.DOCKED;
            break;
        case 3:
            status = Status.LOADING;
            break;
        case 4:
            status = Status.UNLOADING;
            break;
        case 5:
            status = Status.DEPARTED;
            break;
        default:
            System.out.println("Invalid status.");
            return;
    }

    Ship ship;

    if (type == 1) {
        System.out.print("Enter Container Capacity: ");
        int capacity = sc.nextInt();

        ship = new ContainerShip(
                id, name, ShipType.CONTAINER, status, capacity);

    } else if (type == 2) {
        System.out.print("Enter Cargo Capacity: ");
        double capacity = sc.nextDouble();

        ship = new BulkCarrier(
                id, name, ShipType.BULK_CARRIER, status, capacity);

    } else if (type == 3) {
        System.out.print("Enter Liquid Capacity: ");
        double capacity = sc.nextDouble();

        ship = new Tanker(
                id, name, ShipType.TANKER, status, capacity);

    } else {
        System.out.println("Invalid ship type.");
        return;
    }

    port.addShip(ship);
}

private static void addCargo(Scanner sc, Port port) {

    System.out.print("Enter Cargo ID: ");
    String id = sc.nextLine();

    System.out.print("Enter Description: ");
    String description = sc.nextLine();

    System.out.println("1. Container");
    System.out.println("2. Bulk");
    System.out.println("3. Liquid");
    System.out.print("Choose Cargo Type: ");
    int type = sc.nextInt();

    CargoType cargoType;

    if (type == 1) {
        cargoType = CargoType.CONTAINER;
    } else if (type == 2) {
        cargoType = CargoType.BULK;
    } else if (type == 3) {
        cargoType = CargoType.LIQUID;
    } else {
        System.out.println("Invalid cargo type.");
        return;
    }

    System.out.print("Enter Weight: ");
    double weight = sc.nextDouble();
    sc.nextLine();

    System.out.print("Enter Ship ID: ");
    Ship ship = port.getShipById(sc.nextLine());

    Cargo cargo = new Cargo(
            id, description, cargoType, weight);

    try {
        port.addCargo(cargo, ship);
    } catch (InvalidCargoException e) {
        System.out.println("Cargo Error: " + e.getMessage());
    }
}

private static void operateCargo(
        Scanner sc, Port port, boolean loading) {

    System.out.print("Enter Ship ID: ");
    Ship ship = port.getShipById(sc.nextLine());

    System.out.print("Enter Cargo ID: ");
    Cargo cargo = port.getCargoById(sc.nextLine());

    if (ship == null || cargo == null) {
        System.out.println("Ship or cargo not found.");
        return;
    }

    try {
        Crane crane = port.getAvailableCrane();

        Runnable operation;

        if (loading) {
            operation = new Loading(ship, cargo, crane);
        } else {
            operation = new Unloading(ship, cargo, crane);
        }

        Thread thread = new Thread(operation);
        thread.start();
        thread.join();

    } catch (CraneUnavailableException e) {
        System.out.println(e.getMessage());
    } catch (InterruptedException e) {
        System.out.println("Operation interrupted.");
        Thread.currentThread().interrupt();
    }
}
}
