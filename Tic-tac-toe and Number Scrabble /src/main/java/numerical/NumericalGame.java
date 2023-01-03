package main.java.numerical;

import java.util.ArrayList;

/**
 * NumericalGame
 * Contains logic of numerical tictactoe (new game, winner conditions, loading and saving format)
 */
public class NumericalGame extends boardgame.BoardGame implements boardgame.Saveable {

    private String playerTurn;
    private int depth;
    private ArrayList numArr1 = new ArrayList<Integer>();
    private ArrayList<Integer> numArr2 = new ArrayList<Integer>();

    /** 
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     */  
    public NumericalGame(int wide, int high) {
        super(wide, high);
        setGrid(new NumericalGrid(wide, high));
        playerTurn = "O";
        depth = 0;
        numArr1.add(1);
        numArr1.add(3);
        numArr1.add(5);
        numArr1.add(7);
        numArr1.add(9);
        numArr2.add(2);
        numArr2.add(4);
        numArr2.add(6);
        numArr2.add(8);
    }

    /** 
     * Overloaded method that facilitates the placement of an input on the board
     *  with integer input. Method should be overriden 
     * to validate input prior to placing the input value. 
     *  Overridden method should throw exceptions when validating input.
     * @param across across index, zero based
     * @param down  down index, zero based
     * @param input  int input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    @Override
    public boolean takeTurn(int across, int down, int input) {

        if (across > 9 || across < 1 || down > 9 || down < 1 || !getCell(across, down).equals(" ")) {
            return false;
        }
        if (playerTurn.equals("O") && numArr1.contains(input)) {
            setValue(across, down, input);
            setTurn();
            depth++;
            numArr1.remove(numArr1.indexOf(input));
            return true;
        }
        if (playerTurn.equals("E") && numArr2.contains(input)) {
            setValue(across, down, input);
            setTurn();
            depth++;
            numArr2.remove(numArr2.indexOf(input));
            return true;
        }

        return false;
    }

    /**
     * Overloaded method that facilitates the placement of an input on the board
     * with String input. Method should be overriden
     * to validate input prior to placing the input value. Overridden method
     * should throw exceptions when validating input.
     * 
     * @param across across index, 1 based
     * @param down   down index, 1 based
     * @param input  String input from game
     * @return boolean returns true if input was placed false otherwise
     */
    @Override
    ///
    public boolean takeTurn(int across, int down, String input) {
        try {
            return takeTurn(across, down, Integer.parseInt(input));
        } catch (Exception e) {
            return false;
        }
    }

    private void setTurn() {
        if (playerTurn.equals("O")) {
            // If player X went, it is now O player turn
            playerTurn = "E";
        } else {
            // If player O went, it is now X player turn
            playerTurn = "O";
        }
    }

    /**
     * Method must be overridden. Returns the winner of the game.
     * 
     * @return 0 for tie, 1 for player 1, 2 for player 2, -1 if no winner
     */
    @Override
    public boolean isDone() {
        return getWinner() != -1;

    }

    /**
     * Method must be overridden. Returns the winner of the game.
     * 
     * @return 0 for tie, 1 for player O, 2 for player E, -1 if no winner
     */
    @Override
    public int getWinner() {
        String winner = "?";

        winner = checkVertical();

        if (winner.equals("?")) {
            winner = checkHorizontal();
        }

        if (winner.equals("?")) {
            winner = checkDiagonal();
        }

        if (winner.equals("?")) {
            if (depth == 9) {
                return 0;
            }
            return -1;
        } else if (winner.equals("O")) {
            return 1;
        } else {
            return 2;
        }

    }

    /**
     * Returns player that gets sum of 15 vertically
     * @return O,E, or ?
     */
    private String checkVertical() {
        for (int i = 1; i < 4; i++) {
            // check to see if coloumn values equal each other
            if ((!getCell(i, 1).equals(" ") && !getCell(i, 2).equals(" ") && !getCell(i, 3).equals(" "))
                    && (Integer.parseInt(getCell(i, 1)) + Integer.parseInt(getCell(i, 2))
                            + Integer.parseInt(getCell(i, 3))) == 15) {
                if (playerTurn.equals("O")) {
                    return "E";
                } else {
                    return "O";
                }
            }
        }
        return "?";

    }

    /**
     * Returns player that gets sum of 15 horizontally
     * @return O,E, or ?
     */
    private String checkHorizontal() {
        for (int i = 1; i < 4; i++) {
            if ((!getCell(1, i).equals(" ") && !getCell(2, i).equals(" ") && !getCell(3, i).equals(" "))
                    && (Integer.parseInt(getCell(1, i)) + Integer.parseInt(getCell(2, i))
                            + Integer.parseInt(getCell(3, i))) == 15) {
                if (playerTurn.equals("O")) {
                    return "E";
                } else {
                    return "O";
                }
            }
        }

        return "?";

    }

    /**
     * Returns player that gets sum of 15 diagonally
     * @return O,E, or ?
     */
    private String checkDiagonal() {
        String winner = "?";

        if ((!getCell(1, 1).equals(" ") && !getCell(2, 2).equals(" ") && !getCell(3, 3).equals(" "))
                && (Integer.parseInt(getCell(1, 1)) + Integer.parseInt(getCell(2, 2))
                        + Integer.parseInt(getCell(3, 3))) == 15) {
            if (playerTurn.equals("O")) {
                return "E";
            } else {
                return "O";
            }
        }
        if (winner.equals("?")) {
            if ((!getCell(3, 1).equals(" ") && !getCell(2, 2).equals(" ") && !getCell(1, 3).equals(" "))
                    && (Integer.parseInt(getCell(3, 1)) + Integer.parseInt(getCell(2, 2))
                            + Integer.parseInt(getCell(1, 3))) == 15) {
                if (playerTurn.equals("O")) {
                    return "E";
                } else {
                    return "O";
                }
            }
        }

        return winner;

    }

    /**
     * Must be overridden if used. Returns a message that can be output to use that
     * provides
     * information about the game state.
     * 
     * @return String message to user
     */
    public String getGameStateMessage() {

        if (getWinner() == 0) {
            return "Game is a tie. Do you want to play again?";
        } else if (getWinner() == 1) {
            return "Player 1 won. Do you want to play again?";
        } else if (getWinner() == 2) {
            return "Player 2 won. Do you want to play again?";
        } else {
            if (playerTurn.equals("O")) {
                return "It's 1's turn";
            } else {
                return "It's 2's turn";
            }
        }

    }


    /**
     * Object returns a string in the format required for
     * a text save file for that object
     * @return String 
     */
    @Override
    public String getStringToSave() {
        String ret = playerTurn + "\n";

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (!getCell(j, i).equals(" ")) {
                    ret = ret + getCell(j, i);
                }
                if (j != 3) {
                    ret = ret + ",";
                }
            }
            ret = ret + "\n";
        }

        return ret;
    }

    /**
     * Object parses the string given as a parameter and restores
     * its state based on the values in the string
     */
    @Override
    public void loadSavedString(String toLoad) {
        String[] token = toLoad.split("[,\n]");
        int k = 0;

        if (token[k++].equals("O")) {
            playerTurn = "E";
        } else {
            playerTurn = "O";
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (token[k].equals("")) {
                    setValue(j, i, " ");
                } else {
                    setValue(j, i, token[k]);
                }
                k++;
            }
        }
        updateDepth();
    }

    /**
     * Counts numbers of turn
     * after loading from file
     */
    private void updateDepth() {
        depth = 0;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (!getCell(j, i).equals(" ")) {
                    depth++;
                }
            }
        }
    }

    /**
     * Resets turn, player, and array
     */
    public void reset() {
        playerTurn = "O";
        numArr1.clear();
        numArr2.clear();
        numArr1.add(1);
        numArr1.add(3);
        numArr1.add(5);
        numArr1.add(7);
        numArr1.add(9);
        numArr2.add(2);
        numArr2.add(4);
        numArr2.add(6);
        numArr2.add(8);
        depth = 0;
        // System.out.println(depth);
    }

    /**
     * Should be overridden to create a nicer representation.
     * 
     * @return String String representation of the board
     */
    @Override
    public String toString() {
        String ret = "";
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                ret = ret + getCell(j, i);
                if (j != 3) {
                    ret = ret + "|";
                }
            }
            ret = ret + "\n";
            if (i != 3) {
                ret = ret + "-----\n";
            }
        }
        return ret;
    }

}
