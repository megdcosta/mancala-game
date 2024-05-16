package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("No such player found");
    }
    public NoSuchPlayerException(String errString) {
        super(errString);
    }
}
