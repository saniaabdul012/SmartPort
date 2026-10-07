package exceptions;
public class SmartPortException extends RuntimeException {
    public SmartPortException(String message) {
        super(message);
    }
    public SmartPortException(String message, Throwable cause) {
        super(message, cause);
    }
}
