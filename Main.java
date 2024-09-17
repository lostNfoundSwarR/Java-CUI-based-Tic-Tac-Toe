import java.util.Scanner;

public class Main {

     // Global variable declarations
     static char[][] board = new char[3][3]; // Game board for Tic-Tac-Toe (3x3 grid)
     static final char PLAYER = 'O';         // Symbol for the player
     static final char COMPUTER = 'X';       // Symbol for the computer
     static Scanner scan = new Scanner(System.in); // Scanner to take player input

     // This method resets the game board by setting all spaces to empty (' ')
     static void reset_board() {
          /* 
          Loop through each row (i) and column (j) of the board 
          and set every position to a blank space (' ') to prepare for a new game.
          */
          for(int i = 0; i < 3; i++) {
               for(int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
               }
          }
     }

     // This method prints the current state of the game board
     static void print_board() {
          /*
          The game board is printed in a formatted style using printf().
          Each line of the board is printed, separated by a divider "---|---|---".
          */
          System.out.printf(" %c | %c | %c \n", board[0][0], board[0][1], board[0][2]);
          System.out.println("---|---|---");
          System.out.printf(" %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
          System.out.println("---|---|---");
          System.out.printf(" %c | %c | %c \n", board[2][0], board[2][1], board[2][2]);
          System.out.println(); // Print an extra line for better spacing
     }

     // This method checks how many spaces on the board are still free
     static int check_FreeSpaces() {
          int freeSpaces = 9; // Start with the assumption that all 9 spaces are free
          
          /* 
          Iterate through the board and count how many spaces are not empty (' ').
          For each non-empty space, reduce the freeSpaces counter.
          */
          for(int i = 0; i < 3; i++) {
               for(int j = 0; j < 3; j++) {
                    if(board[i][j] != ' ') { // If the space is occupied
                         freeSpaces--;
                    }
               }
          }

          return freeSpaces; // Return the remaining free spaces
     }

     // This method handles the player's move
     static void playerMove() {
          int x, y; // Coordinates for the player's move (row and column)

          /* 
          Keep asking the player to enter a valid move until they provide one.
          We take input for row (x) and column (y), and adjust for 0-based indexing.
          */
          do {
               System.out.print("Enter the number of rows (1-3): ");
               x = scan.nextInt(); // Get the row from the player
               x--; // Convert to 0-based index

               System.out.print("Enter the number of columns (1-3): ");
               y = scan.nextInt(); // Get the column from the player
               y--; // Convert to 0-based index

               /* 
               Check if the chosen space is already occupied or out of bounds.
               The condition ensures the move is within the valid range (0-2) and the space is empty.
               */
               if(board[x][y] != ' ' || x < 0 || y < 0 || x > 2 || y > 2) {
                    System.out.println("Invalid move!!"); // Inform the player if it's an invalid move
               }

          } while(board[x][y] != ' ' || x < 0 || y < 0 || x > 2 || y > 2); // Repeat until valid move

          board[x][y] = PLAYER; // Place the player's symbol on the chosen spot
     }

     // This method handles the computer's move
     static void computerMove() {
          int x, y; // Coordinates for the computer's move

          /* 
          The computer generates random moves by selecting a random row and column.
          It keeps generating random moves until it finds an empty spot.
          */
          do {
               x = (int) (Math.random() * 3); // Random row between 0 and 2
               y = (int) (Math.random() * 3); // Random column between 0 and 2
          } while(board[x][y] != ' ' && check_FreeSpaces() != 0); // Retry if the spot is taken

          board[x][y] = COMPUTER; // Place the computer's symbol on the chosen spot
     }

     // This method checks if there is a winner by examining rows, columns, and diagonals
     static char checkWinner() {
          // Check for winning rows
          for(int i = 0; i < 3; i++) {
               if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != ' ') {
                    return board[i][0]; // Return the winner ('O' or 'X')
               }
          }

          // Check for winning columns
          for(int j = 0; j < 3; j++) {
               if(board[0][j] == board[1][j] && board[0][j] == board[2][j] && board[0][j] != ' ') {
                    return board[0][j]; // Return the winner ('O' or 'X')
               }
          }

          // Check for winning diagonals (top-left to bottom-right)
          if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') {
               return board[0][0]; // Return the winner
          }

          // Check for winning diagonals (top-right to bottom-left)
          if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ') {
               return board[0][2]; // Return the winner
          }

          return ' '; // Return ' ' if there is no winner (draw or ongoing game)
     }

     public static void main(String[] args) {
          char winner = ' '; // To store the result of the game ('O', 'X', or ' ')
          String response = "Y"; // To handle player's decision to play again

          do {
               reset_board(); // Reset the board before each new game
               winner = ' ';  // Reset winner for the new game

               while(winner == ' ' && check_FreeSpaces() != 0) {
                    print_board(); // Show the current state of the board
                    playerMove();  // Player makes a move
                    winner = checkWinner(); // Check if the player won

                    if(winner != ' ' || check_FreeSpaces() == 0) {
                         break; // Break if we have a winner or the board is full
                    }

                    computerMove(); // Computer makes a move
                    winner = checkWinner(); // Check if the computer won
               }

               // Announce the result of the game
               if(winner == 'O') {
                    System.out.println("Player wins!!");
               } else if(winner == 'X') {
                    System.out.println("Computer wins!!");
               } else {
                    System.out.println("Draw!!");
               }

               // Ask the player if they want to play again
               System.out.print("\nDo you want to play again? (Y/N): ");
               scan.nextLine(); // Consume leftover newline
               response = scan.nextLine().toUpperCase(); // Get the response in uppercase

               System.out.println(); // Print a new line for clarity
          } while(response.equals("Y"));  // Keep playing if the player chooses 'Y'
     }
}
