// package ui;

// import java.io.IOException;
// import mancala.*;

// public class TextUI {
//     public static void main(String[] args) {
//         // Instantiate Saver
//         Saver saver = new Saver();

//         MancalaGame mancalaGame = new MancalaGame();
//         Player a = new Player("Megan");
//         Player b = new Player("Melanie");
//         mancalaGame.setPlayers(a,b);
//         System.out.println(mancalaGame);

//         try {
//             saver.saveObject(mancalaGame, "mancalaGame.ser");
//             System.out.println("MancalaGame saved successfully.");
//         } catch (IOException e) {
//             System.err.println("An error occurred while saving MancalaGame: " + e.getMessage());
//             e.printStackTrace();
//         }

//         // Load the MancalaGame from the file
//         try {
//             MancalaGame loadedGame = (MancalaGame) saver.loadObject("mancalaGame.ser");
//             System.out.println("Loaded MancalaGame: " + loadedGame);
//         } catch (IOException | ClassNotFoundException e) {
//             System.err.println("An error occurred while loading MancalaGame: " + e.getMessage());
//             e.printStackTrace();
//         }

//         // Create a sample UserProfile
//         UserProfile userProfile = new UserProfile();
//         userProfile.setUserName("John doe");
//         for (int i = 0; i < 5; i++) {
//             userProfile.addKalahGame(); // 5 Kalah games played
//         }
//         for (int i = 0; i < 3; i++) {
//             userProfile.addAyoGame(); // 3 Ayo games played
//         }
//         for (int i = 0; i < 2; i++) {
//             userProfile.addKalahWin(); // 2 Kalah games won
//         }
//         userProfile.addAyoWin(); // 1 Ayo game won

//         // Save the UserProfile to a file
//         try {
//             saver.saveObject(userProfile, "userProfile.ser");
//             System.out.println("UserProfile saved successfully.");
//         } catch (IOException e) {
//             System.err.println("An error occurred while saving UserProfile: " + e.getMessage());
//             e.printStackTrace();
//         }

//         // Load the UserProfile from the file
//         try {
//             UserProfile loadedProfile = (UserProfile) saver.loadObject("userProfile.ser");
//             System.out.println("Loaded UserProfile: " + loadedProfile);
//         } catch (IOException | ClassNotFoundException e) {
//             System.err.println("An error occurred while loading UserProfile: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }



package ui;

import mancala.MancalaGame;
import mancala.Player;
import java.util.Scanner;
import mancala.InvalidMoveException;
import mancala.PitNotFoundException;
import mancala.GameNotOverException;
import mancala.NoSuchPlayerException;


public class TextUI{
    private Scanner scan;
    private MancalaGame game;
    private Player playerA;
    private Player playerB;

    public TextUI(){
        scan = new Scanner(System.in);
        game = new MancalaGame();
    }

    public void getPlayerInput(){
        //System.out.println("Enter PlayerA's name: ");
        //playerA = new Player(scan.nextLine());
        //System.out.println("Enter PlayerB's name: ");
        //playerB = new Player(scan.nextLine());
        playerA = new Player("Megan");
        playerB = new Player("Melanie");
        game.setPlayers(playerA, playerB);
    }

    public void startGame() throws PitNotFoundException, NoSuchPlayerException, InvalidMoveException, GameNotOverException{
        getPlayerInput();
        System.out.println("Mancala Game Start!");
    
        do {
            run();
        } while (!game.isGameOver());
    
        endGame();
    }

    public int run() throws PitNotFoundException{
        //set current player
        boolean valid = false;
        int stonesLeft = 0;
        while(!valid){
            System.out.println();
            System.out.println(game);
            try{
                System.out.println(game.getCurrentPlayer()+"'s turn!");
                System.out.print("Enter the position you want to move: ");
                stonesLeft = game.move(scan.nextInt());
                valid = true;                
            }catch(InvalidMoveException err){
                valid = false;
                System.out.println(err.getMessage());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }   

        return stonesLeft;
    }

    public void endGame() throws PitNotFoundException,InvalidMoveException, NoSuchPlayerException, GameNotOverException{
        Player winner;
        winner = game.getWinner();
        System.out.println("\n----The game has ended----\n");
        System.out.println("..............................\n");

        if (winner == null) {
            System.out.println("The game was a tie!");
            System.out.println("..............................\n");
        } else {
            System.out.println("Congratulations!! " + winner + " is the winner!");
            System.out.println("..............................\n");
        }
        

    }

    public static void main(String[] args) throws PitNotFoundException, NoSuchPlayerException, InvalidMoveException, GameNotOverException{
        TextUI ui = new TextUI();
        ui.startGame(); 
    }
}