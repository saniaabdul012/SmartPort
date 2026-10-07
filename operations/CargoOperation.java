package operations;

import model.Ship;
import model.Cargo;

public interface CargoOperation {

    void performOperation(Ship ship, Cargo cargo);
}