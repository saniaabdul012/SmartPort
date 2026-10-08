
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

        // Create port resources
        port.addBerth(new Berth("B01", "Berth 1", false));
        port.addBerth(new Berth("B02", "Berth 2", false));

        port.addCrane(new Crane("C01", "Main Crane", true));

        Ship ship = null;
        Cargo cargo = null;

        int choice;

        do {

            System.out.println("\n========== SMARTPORT ==========");
            System.out.println("1. Add Container Ship");
            System.out.println("2. Add Bulk Carrier");
            System.out.println("3. Add Tanker");
            System.out.println("4. Add Cargo");
            System.out.println("5. Assign Berth");
            System.out.println("6. Load Cargo");
            System.out.println("7. Unload Cargo");
            System.out.println("8. View Ships");
            System.out.println("9. View Cargo");
            System.out.println("10. View Waiting Queue");
            System.out.println("11. Port Dashboard");
            System.out.println("12. Depart Ship");
            System.out.println("13. Save Port Data");
            System.out.println("14. Read Saved Data");
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

                    port.addShip(ship);

                    System.out.println("Container ship added.");
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

                    port.addShip(ship);

                    System.out.println("Bulk carrier added.");
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

                    port.addShip(ship);

                    System.out.println("Tanker added.");
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
                    } else if (type == 3) {
                        cargoType = CargoType.LIQUID;
                    } else {
                        System.out.println("Invalid cargo type.");
                        break;
                    }

                    System.out.print("Enter Weight: ");
                    double weight = sc.nextDouble();

                    cargo = new Cargo(
                            cargoId,
                            description,
                            cargoType,
                            weight
                    );

                    try {

                        port.addCargo(cargo);

                    } catch (InvalidCargoException e) {

                        System.out.println(
                                "Cargo Error: " + e.getMessage()
                        );
                    }

                    break;


                case 5:

                    if (ship == null) {

                        System.out.println("Add a ship first.");

                    } else {

                        try {

                            port.assignBerth(ship);

                        } catch (BerthUnavailableException e) {

                            System.out.println(
                                    "Berth Error: " + e.getMessage()
                            );
                        }
                    }

                    break;


                case 6:

                    if (ship == null || cargo == null) {

                        System.out.println(
                                "Add a ship and cargo first."
                        );

                    } else {

                        try {

                            Crane crane = port.getAvailableCrane();

                            Loading loading =
                                    new Loading(ship, cargo, crane);

                            Thread thread =
                                    new Thread(loading);

                            thread.start();

                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                System.out.println(
                                        "Loading interrupted."
                                );
                            }

                        } catch (CraneUnavailableException e) {

                            System.out.println(
                                    "Crane Error: " + e.getMessage()
                            );
                        }
                    }

                    break;


                case 7:

                    if (ship == null || cargo == null) {

                        System.out.println(
                                "Add a ship and cargo first."
                        );

                    } else {

                        try {

                            Crane crane = port.getAvailableCrane();

                            Unloading unloading =
                                    new Unloading(ship, cargo, crane);

                            Thread thread =
                                    new Thread(unloading);

                            thread.start();

                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                System.out.println(
                                        "Unloading interrupted."
                                );
                            }

                        } catch (CraneUnavailableException e) {

                            System.out.println(
                                    "Crane Error: " + e.getMessage()
                            );
                        }
                    }

                    break;


                case 8:

                    port.displayShips();
                    break;


                case 9:

                    port.displayCargos();
                    break;


                case 10:

                    port.displayWaitingQueue();
                    break;


                case 11:

                    port.displayPortDetails();
                    break;


                case 12:

                    if (ship == null) {

                        System.out.println("No ship selected.");

                    } else {

                        port.departShip(ship);
                    }

                    break;


                case 13:

                    String data =
                            "SmartPort Data\n" +
                            "Ships and cargo information saved.\n";

                    FileManager.writeData(data);

                    break;


                case 14:

                    FileManager.readData();

                    break;


                case 0:

                    System.out.println(
                            "Thank you for using SmartPort."
                    );

                    break;


                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);

        sc.close();
    }
}



