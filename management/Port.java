
package management;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import model.Ship;
import model.Cargo;
import model.Berth;
import model.Crane;

import enums.Status;

import exceptions.BerthUnavailableException;
import exceptions.CraneUnavailableException;
import exceptions.InvalidCargoException;

public class Port {

    private String portName;

    private ArrayList<Ship> ships;
    private ArrayList<Cargo> cargos;
    private ArrayList<Berth> berths;
    private ArrayList<Crane> cranes;

    private Queue<Ship> waitingShips;

    public Port(String portName) {

        this.portName = portName;

        ships = new ArrayList<>();
        cargos = new ArrayList<>();
        berths = new ArrayList<>();
        cranes = new ArrayList<>();

        waitingShips = new LinkedList<>();
    }

    // Add a ship to the port
    public void addShip(Ship ship) {

        ships.add(ship);

        System.out.println("Ship " + ship.getShipId()
                + " added to port.");
    }

    // Add cargo to the port
    public void addCargo(Cargo cargo)
            throws InvalidCargoException {

        if (cargo == null || cargo.getWeight() <= 0) {
            throw new InvalidCargoException(
                    "Invalid cargo weight."
            );
        }

        cargos.add(cargo);

        System.out.println("Cargo " + cargo.getCargoId()
                + " added to port.");
    }

    // Add berth
    public void addBerth(Berth berth) {

        berths.add(berth);

        System.out.println("Berth " + berth.getBerthId()
                + " added.");
    }

    // Add crane
    public void addCrane(Crane crane) {

        cranes.add(crane);

        System.out.println("Crane " + crane.getCraneId()
                + " added.");
    }

    // Assign a berth to a ship
    public void assignBerth(Ship ship)
            throws BerthUnavailableException {

        for (Berth berth : berths) {

            if (!berth.isOccupied()) {

                berth.setOccupied(true);
                ship.setStatus(Status.DOCKED);

                System.out.println(
                        "Berth " + berth.getBerthId()
                        + " assigned to ship "
                        + ship.getShipId()
                );

                return;
            }
        }

        waitingShips.offer(ship);

        throw new BerthUnavailableException(
                "No berth available. Ship added to waiting queue."
        );
    }

    // Find an available crane
    public Crane getAvailableCrane()
            throws CraneUnavailableException {

        for (Crane crane : cranes) {

            if (crane.isAvailable()) {
                return crane;
            }
        }

        throw new CraneUnavailableException(
                "No crane is currently available."
        );
    }

    // Add ship manually to waiting queue
    public void addWaitingShip(Ship ship) {

        waitingShips.offer(ship);

        ship.setStatus(Status.WAITING);

        System.out.println(
                "Ship " + ship.getShipId()
                + " added to waiting queue."
        );
    }

    // Display ships
    public void displayShips() {

        System.out.println("\n===== SHIPS IN PORT =====");

        if (ships.isEmpty()) {
            System.out.println("No ships in port.");
            return;
        }

        for (Ship ship : ships) {
            ship.displayDetails();
            System.out.println();
        }
    }

    // Display cargo
    public void displayCargos() {

        System.out.println("\n===== CARGO IN PORT =====");

        if (cargos.isEmpty()) {
            System.out.println("No cargo in port.");
            return;
        }

        for (Cargo cargo : cargos) {
            cargo.displayDetails();
            System.out.println();
        }
    }

    // Display waiting queue
    public void displayWaitingQueue() {

        System.out.println("\n===== WAITING QUEUE =====");

        if (waitingShips.isEmpty()) {
            System.out.println("No ships waiting.");
            return;
        }

        for (Ship ship : waitingShips) {
            System.out.println(
                    ship.getShipId()
                    + " - "
                    + ship.getShipName()
            );
        }
    }

    // Ship departure
    public void departShip(Ship ship) {

        ship.setStatus(Status.DEPARTED);

        System.out.println(
                "Ship " + ship.getShipId()
                + " has departed the port."
        );

        // If a ship is waiting, assign the freed berth
        if (!waitingShips.isEmpty()) {

            Ship nextShip = waitingShips.poll();

            try {

                assignBerth(nextShip);

                System.out.println(
                        "Waiting ship "
                        + nextShip.getShipId()
                        + " is now docked."
                );

            } catch (BerthUnavailableException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    // Display complete port dashboard
    public void displayPortDetails() {

        System.out.println("\n===== SMARTPORT DASHBOARD =====");

        System.out.println("Port Name: " + portName);
        System.out.println("Ships: " + ships.size());
        System.out.println("Cargo: " + cargos.size());
        System.out.println("Berths: " + berths.size());
        System.out.println("Cranes: " + cranes.size());
        System.out.println(
                "Waiting Ships: " + waitingShips.size()
        );

        System.out.println("\nBerth Status:");

        for (Berth berth : berths) {

            System.out.println(
                    berth.getBerthId()
                    + " - "
                    + (berth.isOccupied()
                        ? "Occupied"
                        : "Available")
            );
        }

        System.out.println("\nCrane Status:");

        for (Crane crane : cranes) {

            System.out.println(
                    crane.getCraneId()
                    + " - "
                    + (crane.isAvailable()
                        ? "Available"
                        : "Busy")
            );
        }
    }
}

