package mancala;

public class KalahRules extends GameRules {
    private static final long serialVersionUID = -911298300591842624L;
    public KalahRules(){
        super();
    }

    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        int stonesAddedToStore = 0;
        final int initialStones1 = getDataStructure().getStoreCount(1);
        final int initialStones2 = getDataStructure().getStoreCount(2);
        if (startPit <= 0 || startPit > 12) {
            throw new InvalidMoveException("Invalid move: pit doesn't exist");
        } else if ((startPit >= 1 && startPit <= 6 && playerNum == 2)
                || (startPit >= 7 && startPit <= 12 && playerNum == 1)) {
            throw new InvalidMoveException("Invalid move: player cannot enter opponent's pit");
        } else if (getDataStructure().getNumStones(startPit) == 0) {
            throw new InvalidMoveException("Invalid move: pit is empty");
        } else {
            int numStonesDistributed = distributeStones(startPit);
            //System.out.println("Number of stones distributed: " + numStonesDistributed + "\n\n");

            if (playerNum == 1) {
                stonesAddedToStore = getDataStructure().getStoreCount(1) - initialStones1;
            } else if (playerNum == 2) {
                stonesAddedToStore = getDataStructure().getStoreCount(2) - initialStones2;
            }
        }
        return stonesAddedToStore;
    }

    @Override
    public int distributeStones(int startingPoint) {

        bonusTurn(false);
        int stonesToMove = getDataStructure().removeStones(startingPoint);
        //System.out.println("Stones to move : " + stonesToMove);
        int whichPlayer = getCurrentPlayer();

        getDataStructure().setIterator(startingPoint, whichPlayer, false);
        Countable pit = null;
        int currentPit = -1;

        for(int i = 0; i < stonesToMove; i++){
            pit = getDataStructure().next();
            currentPit = getDataStructure().getPitNum(pit);
            pit.addStone();
        }
        
        if (pit instanceof Store) {
            bonusTurn(true);
        } else if (whichPlayer == 1 && currentPit<7 && (getDataStructure().getNumStones(currentPit) == 1)) {
            captureStones(currentPit);
        } else if (whichPlayer==2 && currentPit>7 && (getDataStructure().getNumStones(currentPit-1) == 1)) {
            captureStones(currentPit-1);
        }
        return stonesToMove;
    }

    @Override  
    public int captureStones(int stoppingPoint) {
        int numberOfStonesCaptured=0;
        int playerNum = getCurrentPlayer();


        numberOfStonesCaptured += getDataStructure().removeStones(13 - stoppingPoint);
        getDataStructure().addToStore(playerNum,numberOfStonesCaptured);
        numberOfStonesCaptured++;

        getDataStructure().addToStore(playerNum, getDataStructure().removeStones(stoppingPoint));

        System.out.println("captured " + numberOfStonesCaptured + " stones !!!");

        return numberOfStonesCaptured;
    }
}

