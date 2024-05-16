package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private static final long serialVersionUID = -911298300591842624L;
    private String userName;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahGamesWon;
    private int ayoGamesWon;

    public UserProfile() {
        // Initialize with default values
        this.userName = "";
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahGamesWon = 0;
        this.ayoGamesWon = 0;
    }

    public UserProfile (String user) {
        this.userName = user;
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahGamesWon = 0;
        this.ayoGamesWon = 0;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void addAyoGame() {
        ayoGamesPlayed++;
    }

    public void addKalahGame() {
        kalahGamesPlayed++;
    }

    public void addKalahWin() {
        kalahGamesWon++;
    }

    public void addAyoWin() {
        ayoGamesWon++;
    }

    public int getKalahGamesPlayed() {
        return kalahGamesPlayed;
    }

    public int getKalahGamesWon() {
        return kalahGamesWon;
    }

    public void setKalahGamesWon(int kalahGamesWon) {
        this.kalahGamesWon = kalahGamesWon;
    }

    public int getAyoGamesPlayed() {
        return ayoGamesPlayed;
    }

    public void setAyoGamesWon(int ayoGamesWon) {
        this.ayoGamesWon = ayoGamesWon;
    }

    public int getAyoGamesWon() {
        return ayoGamesWon;
    }

    @Override
    public String toString() {
        return "UserProfile {" +
                "Username ='" + this.userName +
                ", # of KalahGamesPlayed = " + this.kalahGamesPlayed +
                ", # of AyoGamesPlayed = " + this.ayoGamesPlayed +
                ", # of KalahGamesWon = " + this.kalahGamesWon +
                ", # of AyoGamesWon = " + this.ayoGamesWon +
                "}";
    }
    
}