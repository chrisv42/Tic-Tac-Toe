public class nk_TicTacToe {
    private char[][] gameBoard;
    private int inline;
    private int max_levels;
    private int board_size;

    /* this method will create the game board with the given specifications, and set a " " at each space on the board */

    public nk_TicTacToe(int board_size, int inline, int max_levels){
        this.gameBoard = new char[board_size][board_size];
        this.board_size = board_size;
        this.inline = inline;
        this.max_levels = max_levels;
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                gameBoard[i][j] = ' ';
            }
        }
    }

    /* this method creates and returns a dictionary of a prime numbered size */

    public Dictionary createDictionary(){
        Dictionary dict = new Dictionary(7717);
        return dict;
    }

    /* this method checks to see if a configuration is already in the dictionary and returns it's score,
       if it isn't it returns -1. */

    public int repeatedConfig(Dictionary configurations){
        String config = "";
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                config += gameBoard[i][j];
            }
        }
        return configurations.get(config);
    }

    /* this method inserts a configuration in the dictionary by first making the game board into a string, and setting a 
       new record class, and storing it in the dictionary. */

    public void insertConfig(Dictionary configurations, int score){
        String config = "";
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                config += gameBoard[i][j];
            }
        }
        Record record = new Record(config,score);
        configurations.insert(record);
    }

    /* this method inserts a symbol at the given spot */

    public void storePlay(int row, int col, char symbol) {
        this.gameBoard[row][col] = symbol;
    }

    /* this method checks to see if a specified square is empty */

    public boolean squareIsEmpty (int row, int col) {
        if (gameBoard[row][col] == ' '){
            return true;
        }
        else return false;
    }

    /* the proceeding methods checks for wins of a given symbol */

    /* this method checks to see if symbols on the same row of the input index are equal, returns true if there are the
       correct amount in a row */

    private boolean horizontalHelp(int row, int col, char symbol) {
        int counter = 0;
        while (col < board_size){
            if (gameBoard[row][col] == symbol){
                counter++;
                if (counter >= inline){
                    return true;
                }
            }
            if (gameBoard[row][col] != symbol){
                counter = 0;
            }
            col++;
        }
        return false;
    }

    /* this method checks to see if symbols on the same column of the input index are equal, returns true if there are the
       correct amount in a column */

    private boolean verticalHelp(int row, int col, char symbol) {
        int counter = 0;
        while (row < board_size){
            if (gameBoard[row][col] == symbol){
                counter++;
                if (counter >= inline){
                    return true;
                }
            }
            if (gameBoard[row][col] != symbol){
                counter = 0;
            }
            row++;
        }
        return false;
    }

    /* This is a helper method that would check to see if spaces diagonally to the right of the input space is similar or not */

    private boolean diagonalHelpLeftStart(int row, int col, char symbol){
        if (gameBoard[row][col] != symbol) {return false;}
        int counter = 0;
        while (row < board_size && col < board_size){
            if (gameBoard[row][col] == symbol) {
                counter++;
                if (counter >= inline){
                    return true;
                }
            }
            if (gameBoard[row][col] != symbol){
                counter = 0;
            }
            row++;
            col++;
        }
        return false;
    }

    /* This is a helper method that would check to see if spaces diagonally to the left of the input space is similar or not */

    private boolean diagonalHelpRightStart(int row, int col, char symbol){
        if (gameBoard[row][col] != symbol) {return false;}
        int counter = 0;
        while (row < board_size && col > -1){
            if (gameBoard[row][col] == symbol) {
                counter++;
                if (counter >= inline){
                    return true;
                }
            }
            if (gameBoard[row][col] != symbol){
                counter = 0;
            }
            row++;
            col--;
        }
        return false;
    }


    public boolean wins(char symbol) {

        boolean win = false;
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                if (gameBoard[i][j] == symbol){
                    if (diagonalHelpLeftStart(i, j, symbol) || diagonalHelpRightStart(i, j, symbol) || horizontalHelp(i, j, symbol) || verticalHelp(i, j, symbol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* this method checks to see if there are any empty spaces and if there are any wins. If neither are true
       then it returns true */

    public boolean isDraw(){
        for (int i = 0; i < board_size; i++){
            for (int j = 0; j < board_size; j++){
                if (squareIsEmpty(i, j) == true) return false;
            }
        }
        if (wins('X') || wins('O')) {
            return false;
        }
        return true;
    }

    /* this method evaluates to see if there has been a win, draw or the game is still in progress. a 2 represents a draw, 
       a 0 represents a human win, 3 is a computer win, 1 is saying that the game is still going on. */

    public int evalBoard() {
        if (isDraw() == true){
            return 2;
        }
        if (wins('X')) return 0;
        if (wins('O')) return 3;
        else return 1;
    }
}