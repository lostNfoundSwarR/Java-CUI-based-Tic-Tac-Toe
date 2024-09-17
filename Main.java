import java.util.Scanner;

public class Main {

     //Global variable declarations

     static char[][] board = new char[3][3]; //Game board
     static final char PLAYER = 'O';
     static final char COMPUTER = 'X';
     static Scanner scan = new Scanner(System.in);

     static void reset_board() {

          //To reset the board on start-up

          /* 
          i = number of rows
          j = number of columns

          <Same as for a matrix>
          */

          for(int i = 0; i < 3; i++) {
               for(int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
               }
          }

     }

     static void print_board() {

          //To print/initialize the game board

          //printf() to format the display of characters <Initialized as empty ' '>

          System.out.printf(" %c | %c | %c \n", board[0][0], board[0][1], board[0][2]);
          System.out.println("---|---|---");

          System.out.printf(" %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
          System.out.println("---|---|---");

          System.out.printf(" %c | %c | %c \n", board[2][0], board[2][1], board[2][2]);

          System.out.println();

     }

     static int check_FreeSpaces() {

          //To keep track of the free spaces on the board

          int freeSpaces = 9;

          for(int i = 0; i < 3; i++) {
               for(int j = 0; j < 3; j++) {
                    if(board[i][j] != ' ') {
                         freeSpaces--;
                    }
               }
          }

          return freeSpaces;

     }

     static void playerMove() {

          //To take input from the player

          /*
          x = i (rows)
          y = j (columns)
          */

          int x;
          int y;

          do {
               System.out.print("Enter the number of rows (1-3): ");
               x = scan.nextInt();
               x--;

               System.out.print("Enter the number of columns (1-3): ");
               y = scan.nextInt();
               y--;

               if(board[x][y] != ' ' || x < 0 || y < 0 || x > 2 || y > 2) {
                    System.out.println("Invalid move!!");

                    //Checking the conditions for an invalid move
               }
 
          } while(board[x][y] != ' ' || x < 0 || y < 0 || x > 2 || y > 2);

          board[x][y] = PLAYER;

     }

     static void computerMove() {

          //To generate the computer move

          /*
          x = i (rows)
          y = j (columns)
          */

          int x;
          int y;

          do {

               //Generating random moves until the condition is met

               x = (int) (Math.random() * 3);
               y = (int) (Math.random() * 3);

          } while(board[x][y] != ' ' && check_FreeSpaces() != 0);

          board[x][y] = COMPUTER;

     }

     static char checkWinner() {

          //To check the conditions return the winner

          // To check for row cases

          for(int i = 0; i < 3; i++) {   
               if(board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                    return board[i][0];

                    //Checking all rows as iterator [i]
               }
          }

          // To check for column cases

          for(int j = 0; j < 3; j++) {
               if(board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                    return board[0][j];

                    //Checking all columns as iterator [j]
               }
          }

          //To check for diagonal cases

          if(board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
               return board[0][0];

               //For principle diagonal
          }

          if(board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
               return board[0][2];

               //For secondary diagonal
          }

          return ' '; //Draw

     }

     public static void main(String[] args) {

          char winner = ' ';
          String response = "Y";

          do {

               //Resetting the board on each new game

               reset_board();

               while(winner == ' ' || check_FreeSpaces() != 0) {

                    //Printing the board as the game continues (with changes)

                    print_board();

                    playerMove();
                    winner = checkWinner();

                    if(winner != ' ' || check_FreeSpaces() == 0) {
                         break; 

                         //Checking if there is a winner yet
                    }

                    computerMove();
                    winner = checkWinner();

                    if(winner != ' ' || check_FreeSpaces() == 0) {
                         break; 
                         
                         //Again checking if there is a winner yet after the computer moves
                    }

               }

               if(winner == 'O') {
                    System.out.println("Player wins!!");
               }
               else if(winner == 'X') {
                    System.out.println("Computer wins!!");
               }
               else {
                    System.out.println("Draw!!");
               }

               System.out.print("\nDo you want to play again? (Y/N): ");
               scan.nextLine();
               response = scan.nextLine();
               response = response.toUpperCase();

               System.out.println();

          } while(response.equals("Y"));  //Iterating the player doesn't want to play

     }

}