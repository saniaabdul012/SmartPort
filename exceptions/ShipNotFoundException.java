package exceptions;
public class ShipNotFoundException extends SmartPortException {
    public ShipNotFoundException(String shipId) {
        super("Ship with ID '" + shipId + "' could not be found in the system.");
    }
}
