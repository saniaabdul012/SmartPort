package operations;

import model.Ship;
import model.Cargo;
import model.Crane;

public class Unloading implements CargoOperation, Runnable {

    private Ship ship;
    private Cargo cargo;
    private Crane crane;

    public Unloading(Ship ship, Cargo cargo, Crane crane) {
        this.ship = ship;
        this.cargo = cargo;
        this.crane = crane;
    }

    @Override
    public void performOperation(Ship ship, Cargo cargo) {
        System.out.println("Unloading cargo " + cargo.getCargoId()
                + " from ship " + ship.getShipId());
    }

    @Override
    public void run() {
        synchronized (crane) {

            if (!crane.isAvailable()) {
                System.out.println("Crane " + crane.getCraneId()
                        + " is unavailable.");
                return;
            }

            crane.setAvailable(false);

            System.out.println("Unloading started...");
            performOperation(ship, cargo);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Unloading interrupted.");
            }

            crane.setAvailable(true);

            System.out.println("Unloading completed.");
        }
    }
}