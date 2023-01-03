package game;

import main.java.tictactoe.TicTacToeGame;
import java.util.Scanner;

public class TextUI {

/**
 * TextUI
 * Tictactoe played through commnadline
 */
    public static void main(String[] args) {
        TextUI obj3 = new TextUI();
        TicTacToeGame obj = new TicTacToeGame(3, 3);
        int x;
        int y;

        System.out.println(obj.toString()); // Print out the board
        System.out.println(obj.getGameStateMessage());

        while (!obj.isDone()) { //Game is not done
            do {
                x = obj3.xPosition(); //get X player input 
                y = obj3.yPosition(); //get Y player input 
            } while (!obj.takeTurn(x, y, obj.getTurn()));

            System.out.println(obj.toString()); // Print out the board
            System.out.println(obj.getGameStateMessage());
        }
        System.out.println(obj.getGameStateMessage());
    }

     /** 
     * Gets X player input
     * @return int
     */
    private int xPosition() {
        Scanner input = new Scanner(System.in);
        String userInput = "";
        int x;
        System.out.print("Enter X Position between 1 and 9:\n");
        userInput = input.nextLine();
        try {
            x = Integer.parseInt(userInput);
        } catch (Exception e) {
            System.out.println("Enter valid position");
            x = 0;
        }
        return x;
    }

     /** 
     * Gets Y player input
     * @return int
     */
    private int yPosition() {
        Scanner input = new Scanner(System.in);
        String userInput = "";
        int y;
        System.out.print("Enter Y Position between 1 and 9:\n");
        userInput = input.nextLine();
        try {
            y = Integer.parseInt(userInput);
        } catch (Exception e) {
            System.out.println("Enter valid position");
            y = 0;
        }
        return y;
    }

}
