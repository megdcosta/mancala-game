package mancala;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("The move is invalid.");
    }
    public InvalidMoveException(String errString) {
        super(errString);
    }
}
