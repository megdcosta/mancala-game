package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable {
    private static final long serialVersionUID = -1132148524732040459L;
    private int totalStones;
    private Player owner;

    public Store() {
        totalStones = 0;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public int getStoneCount() {
        return totalStones;
    }

    //method that i added
    @Override
    public void addStone() {
        totalStones +=1 ;
    }

    @Override
    public void addStones(int amount) {
        totalStones += amount;
    }

    @Override
    public int removeStones() {
        // Store doesn't support removing stones, so return 0
        int stones = totalStones;
        totalStones = 0;
        return stones;
    }

    //@Override
    public int getTotalStones() {
        return totalStones;
    }

    public int emptyStore() {
        int stonesInStore = totalStones;
        totalStones = 0;
        return stonesInStore;
    }

    @Override
    public String toString() {
        return "[" + totalStones + "]";
    }
}
