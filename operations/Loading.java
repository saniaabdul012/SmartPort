
package operations;

import model.Ship;
import model.Cargo;
import model.Crane;
import enums.Status;

public class Loading implements CargoOperation, Runnable {

private Ship ship;
private Cargo cargo;
private Crane crane;

public Loading(Ship ship, Cargo cargo, Crane crane) {
    this.ship = ship;
    this.cargo = cargo;
    this.crane = crane;
}

@Override
public void performOperation(Ship ship, Cargo cargo) {
    System.out.println(
            "Loading cargo " + cargo.getCargoId()
            + " onto ship " + ship.getShipId());
}

@Override
public void run() {
    synchronized (crane) {

        if (!crane.isAvailable()) {
            System.out.println("Crane is unavailable.");
            return;
        }

        if (ship.getBerth() == null) {
            System.out.println("Ship has no berth.");
            return;
        }

        if (cargo.getShip() != ship) {
            System.out.println("Cargo does not belong to this ship.");
            return;
        }

        if (cargo.isLoaded()) {
            System.out.println("Cargo is already loaded.");
            return;
        }

        if (ship.getStatus() == Status.DEPARTED) {
            System.out.println("Ship has already departed.");
            return;
        }

        crane.setAvailable(false);
        ship.setStatus(Status.LOADING);

        System.out.println("Loading started...");
        performOperation(ship, cargo);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted.");
            Thread.currentThread().interrupt();
        }

        cargo.setLoaded(true);
        ship.setStatus(Status.DOCKED);
        crane.setAvailable(true);

        System.out.println("Loading completed.");
    }
}

}
