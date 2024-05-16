package mancala;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{
    private static final long serialVersionUID = 4429653880898627616L;
    private final ArrayList<Pit> pits;
    private final ArrayList<Store> stores;

    public Board() {
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        setUpPits();
        setUpStores();
    }

    public void setUpPits() { 
        if (pits.isEmpty()) {
            for (int i = 0; i < 12; i++) {
                pits.add(new Pit());
            }
        }
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setUpStores() {
        final Store store1 = new Store();
        final Store store2 = new Store();
        stores.add(store1);
        stores.add(store2);

    }

    public void initializeBoard() {
        for (int i=0; i<4; i++) {
            for (Pit pit : pits) {
                pit.addStone(); 
            }
        }
    }
    

    public void resetBoard() {
        
        for (Pit pit : pits) {
            pit.removeStones();
        }
        for (Store store : stores) {
            store.emptyStore();
        }

        initializeBoard();
    }

    public void registerPlayers(Player one, Player two) {

        stores.get(0).setOwner(one);
        stores.get(1).setOwner(two);
        one.setStore(stores.get(0));
        two.setStore(stores.get(1));

    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException, PitNotFoundException {
        
        int stonesAddedToStore=0;
        final int initialStones1=stores.get(0).getTotalStones();
        final int initialStones2=stores.get(1).getTotalStones();

        if (startPit<=0 || startPit>12) {
            throw new InvalidMoveException("Invalid move: pit is empty");
        } else if ((startPit >=1 && startPit <= 6 && player == stores.get(1).getOwner()) 
        || (startPit >=7 && startPit <= 12 && player == stores.get(0).getOwner())) {
            throw new InvalidMoveException("Invalid move: player cannot enter opponent's pit");
        } else if (pits.get(startPit - 1).getStoneCount() == 0) {
            throw new InvalidMoveException("Invalid move: pit is empty"); 
        } else {
            int numStonesDistributed = distributeStones(startPit);
            System.out.println("Number of stones distributed: " + numStonesDistributed + "\n\n");

            if (player.equals(stores.get(0).getOwner())) {
                stonesAddedToStore=stores.get(0).getTotalStones() - initialStones1;
            } else if (player.equals(stores.get(1).getOwner())) {
                stonesAddedToStore=stores.get(1).getTotalStones() - initialStones2;
            }
        }
        return stonesAddedToStore;
    }

    public int distributeStones(int startingPoint) throws PitNotFoundException {
        int numStonesDistributed;
        if (!((startingPoint>0) && (startingPoint<=12))) {
            throw new PitNotFoundException();
        }else {
            numStonesDistributed=pits.get(startingPoint-1).getStoneCount();
            int currentPit = startingPoint-1;
            int stonesToMove = pits.get(currentPit).removeStones();

            int whichPlayer;
            if (startingPoint>0 && startingPoint<=6){
                whichPlayer = 1;
            }else {
                whichPlayer = 2;
            }

            handleDistribute(currentPit, whichPlayer, stonesToMove);
                            
        }
        return numStonesDistributed;

    }
    
    public void handleDistribute(int currentPit, int whichPlayer, int stonesToMove) throws PitNotFoundException {
        int numberOfStonesCaptured=0;
        while (stonesToMove>0){
            currentPit = (currentPit+1)%pits.size();

            if((currentPit == 6) && (whichPlayer ==1)){
                if(stonesToMove >1){
                    stores.get(0).addStones(1);
                    stonesToMove--;
                    pits.get(currentPit).addStone();
                    stonesToMove--;
                }else if (stonesToMove == 1) {
                    stores.get(0).addStones(1);
                    stonesToMove--;
                }
                    
            } else if((currentPit == 0) && (whichPlayer ==2)){
                if(stonesToMove >1){
                    stores.get(1).addStones(1);
                    stonesToMove--;
                    pits.get(currentPit).addStone();
                    stonesToMove--;
                }else if (stonesToMove ==1) {
                    stores.get(1).addStones(1);
                    stonesToMove--;
                }
                    
            } else {
                pits.get(currentPit).addStone();
                stonesToMove--;
            }
        }
        int finalStoneCount = pits.get(currentPit).getStoneCount();
        int oppStoneCount = pits.get(11-currentPit).getStoneCount();


        if ((whichPlayer == 1) && (currentPit>=0) && (currentPit<=5) && (finalStoneCount==1)) {
            numberOfStonesCaptured =captureStones(currentPit);
            System.out.println("Player 1 captured " + numberOfStonesCaptured + " stones!!");

        } else if((whichPlayer==2) &&(currentPit>=6) && (currentPit<=12) &&(finalStoneCount==1)) {
            numberOfStonesCaptured=captureStones(currentPit);
            System.out.println("Player 2 captured " + numberOfStonesCaptured + " stones!!");
        }

    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException {
        int numberOfStonesCaptured=0;
        int storeIndex;

        if (stoppingPoint>0 && stoppingPoint<=5) {
            storeIndex = 0;
        } else {
            storeIndex =1;
        }
        numberOfStonesCaptured += pits.get(stoppingPoint).removeStones();
        numberOfStonesCaptured += pits.get(11-stoppingPoint).removeStones();

        stores.get(storeIndex).addStones(numberOfStonesCaptured);

        return numberOfStonesCaptured;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        try{
            return pits.get(pitNum - 1).getStoneCount();
        } catch (IndexOutOfBoundsException e) {
            throw new PitNotFoundException("Pit not found"); 
        }
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        try {
            for (int i=0; i<6; i++) {
                
                if (getNumStones(pitNum+i) != 0) {
                    return false;
                }
            }
            return true;
        } catch(IndexOutOfBoundsException e) {
            throw new PitNotFoundException("Pit index is out of bounds");
        }
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("      12   11   10    9    8    7\n");
        
        // Display the top row of stones (pits 7 to 12)
        boardString.append("      ");
        for (int i = 12; i >= 7; i--) {
            boardString.append(pits.get(i - 1)+"  ");
        }
        boardString.append("\n"+stores.get(1)+ "                                    "+ stores.get(0)+ "\n");
        
        // Display the bottom row of stones (pits 1 to 6)
        boardString.append("      ");
        for (int i = 0; i < 6; i++) {
            boardString.append(pits.get(i)+"  ");
        }
        boardString.append("\n       1    2    3    4    5    6\n");

        return boardString.toString();
    }
}