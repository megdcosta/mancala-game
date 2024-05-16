package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private static final long serialVersionUID = -7510294783110181913L;
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private boolean bonusTurn = false;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        resetBoard();
    }

    protected int getCurrentPlayer() {
        return currentPlayer;
    }

    public void bonusTurn(final boolean bonus) {
        bonusTurn = bonus;
    }

    /**
     * Get the status of the bonus turn.
     *
     * @return True if a bonus turn is granted, false otherwise.
     */
    public boolean isBonusTurn() {
        return bonusTurn;
    }

    // /**
    //  * Switch turns between players.
    //  */
    // private void switchTurn() {
    //     currentPlayer = (currentPlayer == 1) ? 2 : 1;
    // }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    protected MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(int pitNum) {
        boolean isEmpty = true;

        for (int i=0; i<6; i++) {
            if (gameBoard.getNumStones(pitNum + i) != 0) {
                isEmpty = false;
            }
        }
        // This method can be implemented in the abstract class.
        return isEmpty;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {
        // this method can be implemented in the abstract class.

        Store store1 = new Store();
        Store store2 = new Store();

        one.setStore(store1);
        two.setStore(store2);

        store1.setOwner(one);
        store2.setOwner(two);

        gameBoard.setStore(store1, 1);
        gameBoard.setStore(store2, 2);

        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        StringBuilder boardString = new StringBuilder();
        boardString.append("      12   11   10    9    8    7\n");
        
        // Display the top row of stones (pits 7 to 12)
        boardString.append("      ");
        for (int i = 12; i >= 7; i--) {
            boardString.append("[" + gameBoard.getNumStones(i)+"]  ");
        }
        boardString.append("\n"+"{"+gameBoard.getStoreCount(2)+ "}                                    {"+ gameBoard.getStoreCount(1)+"}"+ "\n");
        
        // Display the bottom row of stones (pits 1 to 6)
        boardString.append("      ");
        for (int i = 1; i <= 6; i++) {
            boardString.append("[" + gameBoard.getNumStones(i)+"]  ");
        }
        boardString.append("\n       1    2    3    4    5    6\n");

        return boardString.toString();
    }
}