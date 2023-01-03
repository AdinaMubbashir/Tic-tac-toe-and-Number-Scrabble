package main.java.numerical;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import boardgame.ui.PositionAwareButton;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;

import game.GameUI;
/**
* NumericalView
* Creating user interface to play tictactoe 
*/
public class NumericalView extends JPanel {

    private JLabel displayUser;
    private NumericalGame game;
    private PositionAwareButton[][] buttons;
    private GameUI source;

     /** 
     *  Sets Game
     * @param controller  sets the depth
     */
    public void setGame(NumericalGame controller) {
        this.game = controller;
    }

    /** 
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     * @param window sets the game frame
     */  
    public NumericalView(int wide, int tall, GameUI window) {
        // uperclass constructor
        super();
        setLayout(new BorderLayout());
        source = window;
        setGame(new NumericalGame(wide, tall));
        // make a new label to store messages
        displayUser = new JLabel("Welcome to Numerical TicTacToe");
        add(displayUser, BorderLayout.NORTH); // Messages go on top
        add(makeNewGameButton(), BorderLayout.EAST);
        add(makeSavePanel(), BorderLayout.SOUTH);
        add(makeButtonGrid(tall, wide), BorderLayout.CENTER);
    }

     /** 
     * Makes a new game button
     * @return JButton
     */
    private JButton makeNewGameButton() {
        Color c1 = new Color(0, 204, 0);
        JButton button = new JButton("New Game");
        button.setForeground(c1);
        button.addActionListener(e -> newGame());
        return button;
    }

     /** 
     * Adds button to panel
     * @return JPanel
     */
    private JPanel makeSavePanel() {
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new BoxLayout(buttonPanel2, BoxLayout.X_AXIS));
        buttonPanel2.add(makeSaveButton());
        buttonPanel2.add(makeLoadButton());
        return buttonPanel2;
    }

     /** 
     * Makes a save game button
     * @return JButton
     */
    private JButton makeSaveButton() {
        Color c1 = new Color(102, 0, 153);
        JButton button = new JButton("Save Game");
        button.setForeground(c1);
        button.addActionListener(e -> saveGame());
        return button;
    }

     /** 
     * Makes a load game button
     * @return JButton
     */
    private JButton makeLoadButton() {
        Color c1 = new Color(102, 0, 153);
        JButton button = new JButton("Load Game");
        button.setForeground(c1);
        button.addActionListener(e -> loadGame());
        return button;
    }

     /** 
     * Saves game to file entered by user
     */
    private void saveGame() {
        String fileName = JOptionPane.showInputDialog("Please input file name");
        try {
            FileWriter myWriter = new FileWriter("./assets/" + fileName);
            myWriter.write(game.getStringToSave());
            myWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully saved!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to save to file");
        }
    }

     /** 
     * Saves game to file entered by user
     */
    private void loadGame() {
        String line = null;
        Path path;
        String fileName = JOptionPane.showInputDialog("Please input file name");
        try {
            path = FileSystems.getDefault().getPath("./assets", fileName);
            line = Files.readString(path);
            game.loadSavedString(line);
            updateView();
            JOptionPane.showMessageDialog(null, "Successfully loaded!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to load");
        }
    }

     /** 
     * @param wide The desired width of the board
     * @param tall The desired height of the board
     * @return JPanel with game grid in form of buttons
     */ 
    private JPanel makeButtonGrid(int tall, int wide) {
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(wide, tall));
        for (int y = 0; y < wide; y++) {
            for (int x = 0; x < tall; x++) {
                // Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x + 1); // made the choice to be 1-based
                buttons[y][x].setDown(y + 1);
                buttons[y][x].addActionListener(e -> {
                    enterNumber(e);
                    checkGameState();
                });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

     /** 
     * Checks if game has ended or not, starts a new game if user wishes
     */
    private void checkGameState() {
        int choice = 0;
        if (game.isDone()) {
            choice = JOptionPane.showConfirmDialog(null,
                    game.getGameStateMessage(), "PlayAgain?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                source.game();
            } else {
                newGame();
            }
        }

    }

     /** 
     * Updates the labels on the buttons according to the model
     */
    protected void updateView() {
        // update the labels on the buttons according to the model
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(), buttons[y][x].getDown()));
            }
        }

    }

    /** 
     * Sets stats to 0 and calls updateview to start new game
     */
    protected void newGame() {
        game.newGame();
        game.reset();
        updateView();
    }

    /** 
     *  Gets input and checka for valid input
     */  
    private void enterNumber(ActionEvent e) {
        // get input from user
        int i;
        String num = JOptionPane.showInputDialog("Please input a value");
        try {
            i = Integer.parseInt(num);
            if (i > 0 && i < 10) {
                PositionAwareButton clicked = ((PositionAwareButton) (e.getSource()));
                if (game.takeTurn(clicked.getAcross(), clicked.getDown(), num)) {
                    clicked.setText(game.getCell(clicked.getAcross(), clicked.getDown()));
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
        // send input to game and update view

    }

}
