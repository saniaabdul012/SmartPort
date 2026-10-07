package exceptions;
public class PortCapacityExceededException extends SmartPortException {
    public PortCapacityExceededException(String message) {
        super(message);
    }
    public PortCapacityExceededException(String itemName, double current, double max) {
        super("Capacity exceeded for " + itemName + "! Current value: " + current + ", Maximum allowed: " + max);
    }
}
