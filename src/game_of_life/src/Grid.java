package game_of_life.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    private int[][] matrix;

    public Grid(int[][] aMatrix) {
        matrix = deepCopyMatrix(aMatrix);
    }

    private int[][] deepCopyMatrix(int[][] aMatrix) {
        return Arrays.stream(aMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    public int[][] getGridState() {
        return matrix;
    }

    public List<Cell> getCellList() {
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                cellList.add(new Cell(matrix[i][j], getCellNeighborStates(i, j)));
            }
        }
        return cellList;
    }

    private int[] getCellNeighborStates(int i, int j) {
        int[][] possibleNeighbors = getPossibleNeighborsPositions(i, j);
        List<Integer> neighbors = new ArrayList<>();
        for (int[] possibleNeighbor : possibleNeighbors) {
            int row = possibleNeighbor[0];
            int column = possibleNeighbor[1];
            if (row >= 0 && row < matrix.length && column >= 0 && column < matrix[0].length)
                neighbors.add(matrix[row][column]);
        }
        return neighbors.stream().mapToInt(num -> num).toArray();
    }

    private int[][] getPossibleNeighborsPositions(int i, int j) {
        return new int[][]{
                {i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},
                {i, j + 1},
                {i + 1, j + 1}, {i + 1, j}, {i + 1, j - 1},
                {i, j - 1},
        };
    }

    public void evolve() {
        List<Cell> cellList = getCellList();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    matrix[i][j] = cellList.get(i * matrix[0].length + j).evolve();
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Error");
                }
            }
        }
    }
}
