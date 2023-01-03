Project Title
Simple overview of use/purpose.

TicTacToe/Commnad Line TicTacToe

A Tic Tac Toe game played between two players. One player will use the symbol X to indicate their turn and the other player will use the symbol O. The objective is to get the same symbol three times in a row either vertically, horizontally, or diagonally.

Numerical TicTacToe (Number Scrabble)

A Tic Tac Toe game played between two players. One player will use odd numbers to indicate their turn and the other player will use even numbers to indicate their turns. The objective is to get the sum of 15 either vertically, horizontally, or diagonally.

Description
An in-depth paragraph about your project and overview of use.

The game Tic Tac Toe is played between two players. When the program runs, the user is asked to play either TicTacToe or numerical TictacToe. The user can
click either button. If user clicks play TicTacToe they will play the normal TicTacToe game. User can click where they want to place eiher X or O dpending on turn and will be told if input is not valid. The two players at any time can load a game from file or save game to file. Once there is a winner or tie, user is asked if they want to play again. If user clicks play Numerical TicTacToe they will play TicTacToe with numbers. One player can only input odd numbers and one player can only input even numbers. Both can input between 1-9, but is limited to odd or even and have to be unique each turn. User can click where they want to place the number and will be told if input is not valid. The two players at any time can load a game from file or save game to file. Once there is a winner or tie, user is asked if they want to play again.

Through command line, user can play TicTacToe.  One player will use the symbol X to indicate their turn and the other player will use the symbol O. User has to enter row and column to place their symbol on board. User cannot save board to file. The game will go on until there is a tie or a winner, meaning a player got the same symbol three times in a row either vertically, horizontally, or diagonally.


Dependencies

Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import boardgame.ui.PositionAwareButton;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import game.GameUI;
import java.awt.Dimension;
import main.java.tictactoe.TicTacToeView;
import main.java.numerical.NumericalView;


Executing program

How to build and run the program
Step-by-step bullets

**To build and run program with GUI**

-In order to run program with gui, gradle file has to have  attributes 'Main-Class': 'game.GameUI'
- Inside the scioer shell, do: gradle build
- After doing gradle build, switch to your local terminal and navigate to A3 locally and run: java -jar build/libs/A3.jar

**To build and run TicTacToe through command line**

-In order to run program with gui, gradle file has to have  attributes 'Main-Class': 'game.TextUI'
- Inside the scioer shell, do: gradle build
- After that, to run: java -jar build/libs/A3.jar




