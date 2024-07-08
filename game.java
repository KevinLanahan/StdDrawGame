import java.util.Random;

public class game {

  private static final int gridSize = 3;
  private static final double cellSize = 1.0;

  boolean xTurn = true;
  Random random = new Random();
  

  boolean[][] occupied = new boolean[gridSize][gridSize];
  char[][] board = new char[gridSize][gridSize];
  

//##########################################################################################################################

  public void init(){

    StdDraw.setCanvasSize(600, 600);
    StdDraw.setPenRadius(0.01);
    StdDraw.setScale(0, 3);

    

    for(int rows = 0; rows<3; rows++){
      StdDraw.line(0, rows, 3, rows);
    }
    for(int col=0; col<3; col++){
      StdDraw.line(col, 0, col, 3);
    }
  }
//##########################################################################################################################
  public void startingScreen(){
    
    int randomNumber = random.nextInt(2);

    if(randomNumber == 0){
       xTurn = true;
       StdDraw.text(1.5, 1.5, "X goes first");
       StdDraw.setPenColor(StdDraw.BLACK);
      }else{
        xTurn = false;
        StdDraw.text(1.5, 1.5, "O goes first");
        StdDraw.setPenColor(StdDraw.RED);
      }
      makeMove();
  
  }


//##########################################################################################################################

  public void firstTurn(){
    
    int randomNumber = random.nextInt(2);

    if(randomNumber == 0){
       xTurn = true;
       
       StdDraw.setPenColor(StdDraw.BLACK);
      }else{
        xTurn = false;
        
        StdDraw.setPenColor(StdDraw.RED);
      }
      makeMove();
  
  }
  
//##########################################################################################################################
  


public boolean isValidClick(int row, int col) {
    return row >= 0 && row < gridSize && col >= 0 && col < gridSize && !occupied[row][col];
  } 




//##########################################################################################################################
  public void makeMove(){

    while (true) { // Continuously handle moves until game ends
      if (StdDraw.isMousePressed()) {
          double mouseX = StdDraw.mouseX();
          double mouseY = StdDraw.mouseY();

          int row = (int) mouseY;
          int col = (int) mouseX;

          if (isValidClick(row, col)) {
              occupied[row][col] = true;
              board[row][col] = xTurn ? 'X' : 'O'; // Update the board with X or O
              drawXorO(row, col);
              StdDraw.show();
              xTurn = !xTurn;
              checkWin();
              // Check if there's a winner after each move
          }
        }
      }
    }

    
    public void drawXorO(int row, int col) {
      
      double x = col + 0.5;
      double y = row + 0.5;
      double halfSize = cellSize / 4;
      
      if (xTurn) {
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02); 
        StdDraw.line(x - halfSize, y - halfSize, x + halfSize, y + halfSize);
        StdDraw.line(x - halfSize, y + halfSize, x + halfSize, y - halfSize);
    } else {
       
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02); 
        StdDraw.circle(x, y, halfSize);
    }
    
    }
    
    
    
    public void checkWin() {
     
      char markToCheck = xTurn ? 'X' : 'O';

        // Check horizontal lines
        for (int row = 0; row < gridSize; row++) {
            if (board[row][0] == markToCheck && board[row][1] == markToCheck && board[row][2] == markToCheck) {
                displayWinner(markToCheck);
                return;
            }
        }

        // Check vertical lines
        for (int col = 0; col < gridSize; col++) {
            if (board[0][col] == markToCheck && board[1][col] == markToCheck && board[2][col] == markToCheck) {
                displayWinner(markToCheck);
                return;
            }
        }

        // Check diagonals
        if (board[0][0] == markToCheck && board[1][1] == markToCheck && board[2][2] == markToCheck) {
            displayWinner(markToCheck);
            return;
        }
        if (board[0][2] == markToCheck && board[1][1] == markToCheck && board[2][0] == markToCheck) {
            displayWinner(markToCheck);
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (!occupied[row][col]) {
                    tie = false;
                    break;
                }
            }
            if (!tie) {
                break;
            }
        }
        if (tie) {
            displayWinner('-'); // Tie
        }
    }

    public void displayWinner(char winner) {
        StdDraw.clear();
        if (winner == '-') {
            StdDraw.text(1.5, 1.5, "It's a tie!");
        } else {
            StdDraw.text(1.5, 1.5, "Winner: " + winner);
        }
        StdDraw.show();
      }
    }
  