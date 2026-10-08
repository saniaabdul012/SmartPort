
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

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Ship ship = null;
        Cargo cargo = null;
        Crane crane = new Crane("C01", "Main Crane", true);

        int choice;

        do {
            System.out.println("\n===== SMARTPORT =====");
            System.out.println("1. Create Container Ship");
            System.out.println("2. Create Bulk Carrier");
            System.out.println("3. Create Tanker");
            System.out.println("4. Create Cargo");
            System.out.println("5. Display Ship");
            System.out.println("6. Display Cargo");
            System.out.println("7. Load Cargo");
            System.out.println("8. Unload Cargo");
            System.out.println("9. Display Crane");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Ship ID: ");
                    String id1 = sc.nextLine();

                    System.out.print("Enter Ship Name: ");
                    String name1 = sc.nextLine();

                    System.out.print("Enter Container Capacity: ");
                    int capacity = sc.nextInt();

                    ship = new ContainerShip(
                            id1,
                            name1,
                            ShipType.CONTAINER,
                            Status.WAITING,
                            capacity
                    );

                    System.out.println("Container ship created.");
                    break;

                case 2:
                    System.out.print("Enter Ship ID: ");
                    String id2 = sc.nextLine();

                    System.out.print("Enter Ship Name: ");
                    String name2 = sc.nextLine();

                    System.out.print("Enter Cargo Capacity: ");
                    double bulkCapacity = sc.nextDouble();

                    ship = new BulkCarrier(
                            id2,
                            name2,
                            ShipType.BULK_CARRIER,
                            Status.WAITING,
                            bulkCapacity
                    );

                    System.out.println("Bulk carrier created.");
                    break;

                case 3:
                    System.out.print("Enter Ship ID: ");
                    String id3 = sc.nextLine();

                    System.out.print("Enter Ship Name: ");
                    String name3 = sc.nextLine();

                    System.out.print("Enter Liquid Capacity: ");
                    double liquidCapacity = sc.nextDouble();

                    ship = new Tanker(
                            id3,
                            name3,
                            ShipType.TANKER,
                            Status.WAITING,
                            liquidCapacity
                    );

                    System.out.println("Tanker created.");
                    break;

                case 4:
                    System.out.print("Enter Cargo ID: ");
                    String cargoId = sc.nextLine();

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
                    } else {
                        cargoType = CargoType.LIQUID;
                    }

                    System.out.print("Enter Weight: ");
                    double weight = sc.nextDouble();

                    cargo = new Cargo(
                            cargoId,
                            description,
                            cargoType,
                            weight
                    );

                    System.out.println("Cargo created.");
                    break;

                case 5:
                    if (ship != null) {
                        ship.displayDetails();
                    } else {
                        System.out.println("No ship created.");
                    }
                    break;

                case 6:
                    if (cargo != null) {
                        cargo.displayDetails();
                    } else {
                        System.out.println("No cargo created.");
                    }
                    break;

                case 7:
                    if (ship != null && cargo != null) {

                        Loading loading =
                                new Loading(ship, cargo, crane);

                        Thread loadingThread =
                                new Thread(loading);

                        loadingThread.start();

                    } else {
                        System.out.println("Create ship and cargo first.");
                    }
                    break;

                case 8:
                    if (ship != null && cargo != null) {

                        Unloading unloading =
                                new Unloading(ship, cargo, crane);

                        Thread unloadingThread =
                                new Thread(unloading);

                        unloadingThread.start();

                    } else {
                        System.out.println("Create ship and cargo first.");
                    }
                    break;

                case 9:
                    crane.displayDetails();
                    break;

                case 0:
                    System.out.println("Exiting SmartPort...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}

