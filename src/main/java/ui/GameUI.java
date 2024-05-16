package ui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import mancala.MancalaGame;


public class GameUI extends JFrame {
    private JPanel gameContainer;
    private JButton submitButton;
    //private JTextField usernameField;
    private JLabel statusLabel;
    //private PositionAwareButton[][] buttons;

    public GameUI (String title){
        super();
        basicSetUp(title);
        setupGameContainer();
        add(gameContainer, BorderLayout.CENTER);
        gameContainer.add(makeMancalaGrid(6,2));
        add(makeButtonPanel(), BorderLayout.EAST);
        pack();
    }


    private void basicSetUp(String title){
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeMancalaButton());
        buttonPanel.add(makeNewGameButton());
        return buttonPanel;
    }

    private JButton makeNewGameButton() {
        JButton button = new JButton("New Game");
        return button;
    }
    private JButton makeMancalaButton() {
        JButton button = new JButton("Resume Game");
        return button;
    }

    private JPanel makeMancalaGrid(int wide, int tall) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(tall, wide));
        for(int i=0;i<12;i++){
            JButton pit = new JButton();
            panel.add(pit);
        }
        // for (int y = 0; y < wide; y++) {
        //     for (int x = 0; x < tall; x++) {
        //         buttons[y][x] = new PositionAwareButton();
        //         buttons[y][x].setAcross(x + 1);
        //         buttons[y][x].setDown(y + 1);
        //         panel.add(buttons[y][x]);
        //     }
        // }
        return panel;
    }

    private JPanel startupMessage() {
        JPanel temp = new JPanel();
        // Customize the message as desired
        temp.add(new JLabel("Welcome to Mancala!"));
        return temp;
    }
    public void setupGameContainer(){
        gameContainer.add(startupMessage());
    }

    public static void main(String[] args) {
        GameUI ui = new GameUI("MancalaGame");
        ui.setVisible(true);
    }
}

