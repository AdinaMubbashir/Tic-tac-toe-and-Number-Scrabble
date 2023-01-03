package main.java.tictactoe;

/**
 * TicTacToeGame
 * Contains logic of tictactoe (new game, winner conditions, loading and saving format)
 */
public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable {

    private String playerTurn;
    private int depth;

    /** 
     * @param wide The desired width of the board
     * @param high The desired height of teh board
     */  
    public TicTacToeGame(int wide, int high) {
        super(wide, high);
        setGrid(new TicTacToeGrid(wide, high));
        playerTurn = "X";
        depth = 0;
    }

     /** 
     * 
     * @return String  returns player (X or O)
     */
    public String getTurn() {
        return playerTurn;
    }

     /** 
     * 
     * Set players turns
     */
    private void setTurn() {
        if (playerTurn.equals("X")) {
            // If player X went, it is now O player turn
            playerTurn = "O";
        } else {
            // If player O went, it is now X player turn
            playerTurn = "X";
        }
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
        return false;
    }

    /** 
     * Overloaded method that facilitates the placement of an input on the board 
     * with String input. Method should be overriden 
     * to validate input prior to placing the input value.  Overridden method 
     * should throw exceptions when validating input.
     * @param across across index, 1 based
     * @param down  down index, 1 based
     * @param input  String input from game
     * @return boolean  returns true if input was placed false otherwise
     */
    @Override
    public boolean takeTurn(int across, int down, String input) {

        if (across > 9 || across < 1 || down > 9 || down < 1 || !getCell(across, down).equals(" ")
                || !input.equals(playerTurn)) {
            return false;
        }
        setValue(across, down, playerTurn);
        setTurn();
        depth++;
        return true;
    }

    /**
     * Returns true when game is over, false otherwise. Method must be overridden.
     * 
     * @return boolean
     */
    @Override
    public boolean isDone() {
        return getWinner() != -1;

    }

     
    /**
     * Method must be overridden.  Returns the winner of the game.
     * @return 0 for tie, 1 for player 1, 2 for player 2, -1 if no winner
     */
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
        } else if (winner.equals("X")) {
            return 1;
        } else {
            return 2;
        }

    }

    /**
     * Returns symbol found 3 in a row vertically
     * @return 1, 2, or ?
     */
    private String checkVertical() {
        for (int i = 1; i < 4; i++) {
            // check to see if coloumn values equal each other
            if (!getCell(i, 1).equals(" ") && getCell(i, 1).equals(getCell(i, 2))
                    && getCell(i, 2).equals(getCell(i, 3))) {
                return getCell(i, 1);
            }
        }

        return "?";

    }

    /**
     * Returns symbol found 3 in a row horizontally
     * @return 1, 2, or ?
     */
    private String checkHorizontal() {
        for (int i = 1; i < 4; i++) {
            if (!getCell(1, i).equals(" ") && getCell(1, i).equals(getCell(2, i))
                    && getCell(2, i).equals(getCell(3, i))) {
                return getCell(1, i);
            }
        }

        return "?";

    }

    /**
     * Returns symbol found 3 in a row diagonally
     * @return 1, 2, or ?
     */
    private String checkDiagonal() {
        String winner = "?";

        if (!getCell(1, 1).equals(" ") && getCell(1, 1).equals(getCell(2, 2)) && getCell(2, 2).equals(getCell(3, 3))) {
            return getCell(1, 1);
        }
        if (winner.equals("?")) {
            if (!getCell(3, 1).equals(" ") && getCell(3, 1).equals(getCell(2, 2))
                    && getCell(2, 2).equals(getCell(1, 3))) {
                return getCell(3, 1);
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
    @Override
    public String getGameStateMessage() {

        if (getWinner() == 0) {
            return "Game is a tie. Do you want to play again?";
        } else if (getWinner() == 1) {
            return "Congrats! Player X won. Do you want to play again?";
        } else if (getWinner() == 2) {
            return "Congrats! Player O won. Do you want to play again?";
        } else {
            if (playerTurn.equals("X")) {
                return "It's X's turn";
            } else {
                return "It's O's turn";
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

        if (token[k++].equals("X")) {
            playerTurn = "O";
        } else {
            playerTurn = "X";
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
     * Resets turn and player
     */
    public void reset() {
        playerTurn = "X";
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