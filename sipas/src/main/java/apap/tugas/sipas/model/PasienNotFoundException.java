package apap.tugas.sipas.model;

public class PasienNotFoundException extends Exception {
    public PasienNotFoundException() {
        super();
    }

    public PasienNotFoundException(String message) {
        super(message);
    }
}
