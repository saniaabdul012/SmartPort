
package management;

import java.util.ArrayList;

import model.Ship;
import model.Cargo;
import model.Berth;
import model.Crane;

import enums.CargoType;
import enums.ShipType;
import enums.Status;

import exceptions.BerthUnavailableException;
import exceptions.CraneUnavailableException;
import exceptions.InvalidCargoException;

public class Port {

private String portName;
private ArrayList<Ship> ships = new ArrayList<>();
private ArrayList<Cargo> cargos = new ArrayList<>();
private ArrayList<Berth> berths = new ArrayList<>();
private ArrayList<Crane> cranes = new ArrayList<>();

public Port(String portName) {
    this.portName = portName;
}

public void addShip(Ship ship) {
    if (getShipById(ship.getShipId()) != null) {
        System.out.println("Ship ID already exists.");
        return;
    }

    ships.add(ship);
    System.out.println("Ship added.");
}

public Ship getShipById(String id) {
    for (Ship ship : ships) {
        if (ship.getShipId().equals(id)) {
            return ship;
        }
    }
    return null;
}

public void addCargo(Cargo cargo, Ship ship)
        throws InvalidCargoException {

    if (cargo == null || cargo.getWeight() <= 0) {
        throw new InvalidCargoException("Invalid cargo weight.");
    }

    if (getCargoById(cargo.getCargoId()) != null) {
        throw new InvalidCargoException("Cargo ID already exists.");
    }

    if (ship == null) {
        throw new InvalidCargoException("Ship not found.");
    }

    if (!isValidCargoType(cargo, ship)) {
        throw new InvalidCargoException(
                "Cargo type does not match ship type.");
    }

    double totalWeight = cargo.getWeight();

    for (Cargo c : cargos) {
        if (c.getShip() == ship) {
            totalWeight += c.getWeight();
        }
    }

    if (totalWeight > getShipCapacity(ship)) {
        throw new InvalidCargoException(
                "Cargo exceeds ship capacity.");
    }

    cargo.setShip(ship);
    cargos.add(cargo);

    System.out.println("Cargo added to ship " + ship.getShipId() + ".");
}

public Cargo getCargoById(String id) {
    for (Cargo cargo : cargos) {
        if (cargo.getCargoId().equals(id)) {
            return cargo;
        }
    }
    return null;
}

private boolean isValidCargoType(Cargo cargo, Ship ship) {
    CargoType type = cargo.getCargoType();
    ShipType shipType = ship.getShipType();

    return (shipType == ShipType.CONTAINER
            && type == CargoType.CONTAINER)
        || (shipType == ShipType.BULK_CARRIER
            && type == CargoType.BULK)
        || (shipType == ShipType.TANKER
            && type == CargoType.LIQUID);
}

private double getShipCapacity(Ship ship) {
    if (ship instanceof model.ContainerShip) {
        return ((model.ContainerShip) ship).getContainerCapacity();
    }

    if (ship instanceof model.BulkCarrier) {
        return ((model.BulkCarrier) ship).getCargoCapacity();
    }

    return ((model.Tanker) ship).getLiquidCapacity();
}

public void addBerth(Berth berth) {
    berths.add(berth);
}

public void assignBerth(Ship ship)
        throws BerthUnavailableException {

    if (ship == null) {
        throw new BerthUnavailableException("Ship not found.");
    }

    if (ship.getBerth() != null) {
        System.out.println("Ship already has a berth.");
        return;
    }

    for (Berth berth : berths) {
        if (!berth.isOccupied()) {
            berth.setOccupied(true);
            ship.setBerth(berth);
            ship.setStatus(Status.DOCKED);

            System.out.println(
                    "Berth " + berth.getBerthId()
                    + " assigned to " + ship.getShipId());
            return;
        }
    }

    throw new BerthUnavailableException(
            "No berth available.");
}

public void addCrane(Crane crane) {
    cranes.add(crane);
}

public Crane getAvailableCrane()
        throws CraneUnavailableException {

    for (Crane crane : cranes) {
        if (crane.isAvailable()) {
            return crane;
        }
    }

    throw new CraneUnavailableException(
            "No crane is available.");
}

public String getSummary() {
    return "SmartPort: " + portName
            + "\nShips: " + ships.size()
            + "\nCargo: " + cargos.size()
            + "\nBerths: " + berths.size()
            + "\nCranes: " + cranes.size();
}

public void displayPortDetails() {
    System.out.println("\n===== SMARTPORT =====");
    System.out.println(getSummary());

    System.out.println("\nShips:");
    if (ships.isEmpty()) {
        System.out.println("No ships.");
    } else {
        for (Ship ship : ships) {
            ship.displayDetails();
        }
    }

    System.out.println("\nCargo:");
    if (cargos.isEmpty()) {
        System.out.println("No cargo.");
    } else {
        for (Cargo cargo : cargos) {
            cargo.displayDetails();
        }
    }
}

}
