package mancala;

import java.io.Serializable;
import java.util.ArrayList;

public class MancalaGame implements Serializable{
    private static final long serialVersionUID = -2733262777398697007L;
    private GameRules board;
    private final ArrayList<Player> players;
    

    public MancalaGame() {
        //board = new AyoRules();
        board = new KalahRules(); 
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
    }

    public void whichRule(final int rule) {
        if (rule == 1) {
            board = new KalahRules();
        }else {
            board = new AyoRules();
        }
    }

     public GameRules getBoard() {
        return board;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        players.set(0, onePlayer);
        players.set(1, twoPlayer);
        board.registerPlayers(onePlayer, twoPlayer);
        setCurrentPlayer(onePlayer);
    }

   

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(board.getCurrentPlayer()-1);
    }

    public void setCurrentPlayer(final Player player) {
        if (players.get(0).equals(player)) {
            board.setPlayer(1);
        } else{
            board.setPlayer(2);
        }
    }


    public void setBoard(final GameRules theBoard) {
        board = theBoard;
    }



    public int getNumStones(final int pitNum) {

        return board.getNumStones(pitNum);
    }

    public int move(int startPit) throws InvalidMoveException {
        int stonesInSidePits=0;
        int checkStoneInPit = 0;
        try {
            int stonesAddedToStore = board.moveStones(startPit, board.getCurrentPlayer());
            for (int i=1; i<=12; i++){
                stonesInSidePits+=board.getNumStones(i);
            }

            boolean bonus = board.isBonusTurn();
            if (!board.isBonusTurn()) {
                System.out.println("NOT BONUS");
                if (board.getCurrentPlayer() == 2){
                    board.setPlayer(1);
                }else{
                    board.setPlayer(2);
                }
            }
            else {
                if (board.getCurrentPlayer() == 1) {
                    board.setPlayer(1);
                } else if (board.getCurrentPlayer() == 2) {
                    board.setPlayer(2);
                }
            }
            System.out.println("STONES: " + stonesInSidePits);
            return stonesInSidePits;
            
        } catch (InvalidMoveException e ) {
            throw new InvalidMoveException("Invalid move");
        }
        
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException {
        try{
            return player.getStoreCount();
        }catch(Exception e){
            throw new NoSuchPlayerException("No such Player Found.");
        }
    }

    public Player getWinner() throws GameNotOverException, NoSuchPlayerException{

        if (!isGameOver()) {
            throw new GameNotOverException();
        } else {
            int storeCountPlayer1 = board.getDataStructure().getStoreCount(1);
            int storeCountPlayer2 = board.getDataStructure().getStoreCount(2);
    
            if (storeCountPlayer1 > storeCountPlayer2) {
                return players.get(0);
            } else if (storeCountPlayer2 > storeCountPlayer1) {
                return players.get(1);
            } else {
                return null; // It's a tie
            }
        }
    }

    public boolean isGameOver() {

        return (board.isSideEmpty(1) || board.isSideEmpty(7)); 

    }

    public void startNewGame() {
        //Starts a new game by resetting the board.
        board.resetBoard();
    }


    @Override
    public String toString() {
        final StringBuilder gameString = new StringBuilder();
        gameString.append("Player: ").append(players.get(1)).append("'s side\n");
        gameString.append(board);
        gameString.append("Player: ").append(players.get(0)).append("'s side\n");

        return gameString.toString();
    }

}