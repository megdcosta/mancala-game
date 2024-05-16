package mancala;

import java.io.Serializable;

public class PitNotFoundException extends Exception implements Serializable {
    public PitNotFoundException() {
        super("Pit not found");
    }

    public PitNotFoundException(String errString) {
        super(errString);
    }

}

