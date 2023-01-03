package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import main.java.tictactoe.TicTacToeView;
import main.java.numerical.NumericalView;

/**
* GameUI
* Creates user interface to play games
*/
public class GameUI extends JFrame {
    private JPanel field;

     /** 
     * @param name title 
     */
    public GameUI(String name) {
        // superclass constructor
        super();
        // set the siza and title
        this.setSize(500, 400);
        // title that is passed
        this.setTitle(name);
        //make colour
        Color cl = new Color(51, 204, 255);
        field = new JPanel();
        field.setSize(500, 200);
        field.setBackground(cl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // new label to store messages
        add(field, BorderLayout.CENTER);
        add(buttonBoard(), BorderLayout.SOUTH);
        game();
    }
     /** 
     * Adds button to panel
     * @return JPanel
     */
    private JPanel buttonBoard() {
        JPanel makePanel = new JPanel();
        makePanel.setLayout(new BoxLayout(makePanel, BoxLayout.X_AXIS));
        makePanel.setPreferredSize(new Dimension(50, 65));
        makePanel.add(ticTacToeButton());
        makePanel.add(numericalButton());
        return makePanel;
    }

     /** 
     * Adds message to panel
     * @return JPanel
     */
    private JPanel gameupMessage() {
        JPanel userMessage = new JPanel();
        userMessage.setPreferredSize(new Dimension(300, 35));
        userMessage.setBackground(Color.GREEN);
        userMessage.add(new JLabel("Welcome!"));
        return userMessage;

    }

     /** 
     * Adds message to panel
     * @return JPanel
     */
    private JPanel gameupMessageTwo() {
        JPanel userMessage = new JPanel();
        userMessage.setPreferredSize(new Dimension(300, 35));
        userMessage.setBackground(Color.GREEN);
        userMessage.add(new JLabel("Chose a game to play"));
        return userMessage;

    }

     /** 
     * Makes a play game button for tictactoe
     * @return JButton
     */
    private JButton ticTacToeButton() {
        JButton button = new JButton("Play TicTacToe");
        button.setForeground(Color.MAGENTA);
        button.addActionListener(e -> ticTacToe());
        return button;
    }

     /** 
     * Makes a play game button for numerical tictactoe
     * @return JButton
     */
    private JButton numericalButton() {
        JButton button = new JButton("Play Numerical TicTacToe");
        button.setForeground(Color.MAGENTA);
        button.addActionListener(e -> secondGame());
        return button;
    }

     /** 
     * Erases and recalculates elements
     */
    public void game() {
        field.removeAll();
        field.add(gameupMessage());
        field.add(gameupMessageTwo());
        getContentPane().repaint();
        getContentPane().revalidate();

    }

     /** 
     * get width and height of grid and does recalculates layout
     */
    protected void ticTacToe() {
        field.removeAll();
        field.add(new TicTacToeView(3, 3, this));
        getContentPane().repaint();
        getContentPane().revalidate();
    }

     /** 
     * get width and height of grid and does recalculates layout
     */
    protected void secondGame() {
        field.removeAll();
        field.add(new NumericalView(3, 3, this));
        getContentPane().repaint();
        getContentPane().revalidate();
    }

    public static void main(String[] args) {
        GameUI event = new GameUI("Games");
        event.setVisible(true);

    }
}
