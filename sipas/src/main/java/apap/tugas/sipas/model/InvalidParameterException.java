package apap.tugas.sipas.model;

public class InvalidParameterException extends Exception {
    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}