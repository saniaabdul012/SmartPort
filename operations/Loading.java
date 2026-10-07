package operations;

import model.Ship;
import model.Cargo;
import model.Crane;

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
        System.out.println("Loading cargo " + cargo.getCargoId()
                + " onto ship " + ship.getShipId());
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

            System.out.println("Loading started...");
            performOperation(ship, cargo);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Loading interrupted.");
            }

            crane.setAvailable(true);

            System.out.println("Loading completed.");
        }
    }
}