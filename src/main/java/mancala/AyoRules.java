package mancala;


public class AyoRules extends GameRules{
    private static final long serialVersionUID = -911298300591842624L;
    public AyoRules(){
        super();
    }


    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        int stonesToStore = 0;
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
            //getDataStructure().setIterator(startPit, playerNum, true);
            distributeStones(startPit);
            //System.out.println("Number of stones distributed: " + numStonesDistributed + "\n\n");

            if (playerNum == 1) {
                stonesToStore = getDataStructure().getStoreCount(1) - initialStones1;
            } else if (playerNum == 2) {
                stonesToStore = getDataStructure().getStoreCount(2) - initialStones2;
            }
        }
        return stonesToStore;
    }

    @Override
    public int distributeStones(int startingPoint) {
        int numStonesDistributed = 0;
        int whichPlayer = getCurrentPlayer();

        getDataStructure().setIterator(startingPoint, whichPlayer, true);
        int stonesToMove = getDataStructure().removeStones(startingPoint);
        numStonesDistributed+=stonesToMove;
        Countable pit = null;
        int currentPit = -1;

        do{
            for (int i=0; i<stonesToMove; i++) {
                pit = getDataStructure().next();
                currentPit = getDataStructure().getPitNum(pit);
                pit.addStone();
            }

            // if (pit instanceof Store) {
            //     bonusTurn(false);
            // } 
            if (pit instanceof Pit && pit.getStoneCount() > 1) {
                stonesToMove = pit.removeStones();
                numStonesDistributed += stonesToMove;
                // redistribute the stones
            } else {
                stonesToMove = 0;
            }
        }while (stonesToMove!=0);

        if (currentPit<7 && whichPlayer == 1) {
            captureStones(currentPit);
        } else if (currentPit<14 && currentPit>7 && whichPlayer == 2) {
            captureStones(currentPit-1); 
        }
        
        return numStonesDistributed;
    }

    @Override  
    public int captureStones(int stoppingPoint) {

        int whichPlayer = getCurrentPlayer();
        int capturedStones = getDataStructure().removeStones(13-stoppingPoint);
        getDataStructure().addToStore(whichPlayer, capturedStones);

        
        return capturedStones;

    }
}