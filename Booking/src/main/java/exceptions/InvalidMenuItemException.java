package exceptions;

public class InvalidMenuItemException extends Exception {
    public InvalidMenuItemException(String errorMessage) {
        super(errorMessage);
    }
}
