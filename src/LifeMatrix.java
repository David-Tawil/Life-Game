import java.security.SecureRandom;

public class LifeMatrix {
    public enum State {DEAD,ALIVE}
    private State[][] matrix;//matrix that each cell can be DEAD or ALIVE

    /**
     * constructs a logic life matrix that
     * @param size to set the dimension of the square matrix
     */
    public LifeMatrix(int size){
        matrix = new State[size][size];
        for (int row = 0 ; row < size;row++)
            for (int column = 0 ; column< size; column++)
                matrix[row][column] = randomState();//set each cell with random value
    }

    /**
     * @return the size of the matrix
     */
    public int size(){
        return matrix.length;
    }

    /**
     * @param row index for row in matrix
     * @param column index for column in matrix
     * @return value of the specified cell in matrix
     */
    public State getCellValue(int row, int column){
        return matrix[row][column];
    }

    /**
     * update the matrix to the next generation
     */
    public void goToNextGen(){
        State[][] nextMatrix = new State[this.size()][this.size()]; // set new matrix
        for (int row = 0; row < this.size(); row++)
            for (int column = 0; column < this.size(); column++)
                if (this.getCellValue(row, column) == State.DEAD)// if cell is dead
                    nextMatrix[row][column] = (this.countLiveNeighbours(row, column) == 3 ? State.ALIVE : State.DEAD);// if the cell has 3 neighbors alive then turn it to alive otherwise it will remain dead
                else // if cell is alive
                    nextMatrix[row][column] = (countLiveNeighbours(row, column) == 2 || countLiveNeighbours(row, column) == 3 ? State.ALIVE : State.DEAD); // if the cell has 3 or 2 neighbors alive then it will remain alive otherwise it will turn dead
        this.matrix = nextMatrix; // set the matrix to the updated one
    }

    /**
     * count how many live neighbors has the specified cell
     * @param row index for row in matrix
     * @param column index for column in matrix
     * @return how many live neighbors the cell has
     */
    private int countLiveNeighbours(int row,int column){
        int neighbourRow ,neighbourColumn, count = 0;
        for(neighbourRow = row -1;neighbourRow <= row+1;neighbourRow ++)//starting iterating from the upper row
            for (neighbourColumn = column - 1;neighbourColumn <= column+1;neighbourColumn++)  { // iterating from left to right
                if (neighbourRow == row && neighbourColumn == column) // skip the current cell
                    continue;
                if (cellInBounds(neighbourRow, neighbourColumn))//check if indexes are inside the matrix bounds
                    count += (getCellValue(neighbourRow, neighbourColumn)== State.ALIVE ? 1:0); // if neighbor alive increment the counter
            }
        return count;
    }

    /**
     *
     * @param row index for row in matrix
     * @param column index for column in matrix
     * @return true if the indexes are inside the matrix bounds so they represent a cell in the matrix
     */
    private boolean cellInBounds(int row,int column){
        return (row >= 0 && row < matrix.length) && (column >= 0 && column < matrix.length);
    }

    /**
     * @return random state ALIVE or DEAD
     */
    private static State randomState(){
        final SecureRandom random = new SecureRandom();
        int x = random.nextInt(State.values().length);
        return State.values()[x];
    }
}