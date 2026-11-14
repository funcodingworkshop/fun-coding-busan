class GameOfLife {
    int board_size = 10;
    Cell[][] board, old_board;
    UtilClass rand = new UtilClass();

    public GameOfLife(){
        board = new Cell[board_size][board_size];
    }

    public void seedTheBoard() {
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                Cell temp_cell = new Cell(rand.rand());
                this.board[i][j] = temp_cell;
                // System.out.print(this.board[i][j]);
            }
            // System.out.println();
        }
        this.printBoard();
    }

    public void runOnce() {
        Cell[][] oldBoard = this.board.clone();
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                int temp_neigh_cnt = countLiveNeigh(i, j, oldBoard);
                this.board[i][j].update_alive(temp_neigh_cnt); 
            }
        }
        
    }
    public int countLiveNeigh(int i, int j, Cell[][] oldBoard) {
        int sum = 0;
        for (int ii=-1; ii<=1; ii++) {
            int newI = i + ii;
            for (int jj =-1; jj<=1; jj++) {
                int newJ = j + jj;
                if ((newI != i || newJ != j) && newI >= 0 && newI < board_size && newJ >= 0 && newJ < board_size) {
                    if (oldBoard[newI][newJ].is_alive) sum++;
                }
            }
        }
        return sum;
    }

    public void printBoard(){
        for (int i=0; i<board_size; i++){
            for (int j=0; j<board_size; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------\n");
    }
}

class Cell {
    public boolean is_alive;

    public Cell(){
        this.is_alive = false;
    }

    public Cell(boolean alive_param){
        this.is_alive = alive_param;
    }

    public void update_alive(int neighbours_alive){
        // if (neighbours_alive > 3 || neighbours_alive < 2) {
        //     this.is_alive = false;
        // } else
        this.is_alive = neighbours_alive >= 2 && neighbours_alive <= 3;
    }

    public void printTest() {
        System.out.println("printTest " + (this.is_alive ? "o" : "x"));
    }

    public String toString() {
        return this.is_alive ? "O" : " ";
    }
}

class UtilClass {
    public long seed = 1415926535;
    int a = 3;
    int c = 5;
    long m = 999999999;
    public boolean rand() {
        long res = (a * seed + c) % m;
        this.seed = res;
        return (res % 7 == 0); 
    }

}

public class MainConway {
    public static void main(String args[]) {
        GameOfLife life = new GameOfLife();
        // Cell temp_cell = new Cell(false);
        // temp_cell.printTest();
        // temp_cell.update_alive(2);
        // temp_cell.printTest();
        life.seedTheBoard();
        while (true) {
            life.runOnce();
            life.printBoard();
            try {
                Thread.sleep(2000); // Pauses for 1,000 milliseconds (1 second)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restores the interrupted status
            }

        }
    }

}