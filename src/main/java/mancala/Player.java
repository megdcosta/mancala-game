package mancala;

import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 4749620180294632103L;
    private UserProfile user;
    private Store store;

    public Player() {
        this("");
    }

    public Player (final String name) {
        user = new UserProfile(name);
    }

    public Player (final UserProfile userProfile) {
        user = userProfile;
    }
    
    public UserProfile getUserProfile() {
        return user;
    }

    public String getName() {
        return user.getUserName();
    }

    public void setName(final String newName) {
        user.setUserName(newName);
    }

    public Store getStore() {
        return store;
    }

    public int getStoreCount() {
        return store.getStoneCount();
    }

    public void setStore(Store newStore) {
        store = newStore;
    }

    @Override
    public String toString() {
        // return "Player: " + getName();
        return user.getUserName();
    }
}