package exceptions;
public class BerthNotAvailableException extends SmartPortException {
    public BerthNotAvailableException(String berthId) {
        super("Berth with ID '" + berthId + "' is currently occupied or unavailable.");
    }
}
