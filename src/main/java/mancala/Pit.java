package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable{
    private static final long serialVersionUID = -5900112831982566551L;
    private int stoneCount;

    public Pit() {
        stoneCount = 0;
    }

    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    @Override
    public void addStone() {
        stoneCount++;
    }
    
    //method that i added
    @Override
    public void addStones(int numToAdd) {
        stoneCount += numToAdd;
    }

    @Override
    public int removeStones() {
        int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }

    @Override
    public String toString() {

        return "[" + stoneCount + "]";
    }

}
