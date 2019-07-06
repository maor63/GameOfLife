package game_of_life.src;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Cell {

    private int cellState;
    private int[] neighbors;

    public Cell(int state, int[] neighbors) {
        cellState = state;
        this.neighbors = neighbors.clone();
    }

    public int getState() {
        return cellState;
    }

    public int[] getNeighbors() {
        return neighbors;
    }

    public int evolve() {
        int aliveNeighbors = IntStream.of(neighbors).sum();
        if (cellState == CellState.ALIVE.getValue())
            return aliveNeighbors > 1 && aliveNeighbors < 4 ? 1 : 0;
        else
            return aliveNeighbors == 3 ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cell)
            return cellState == ((Cell) obj).cellState && Arrays.equals(neighbors, ((Cell) obj).neighbors);
        else
            return super.equals(obj);
    }
}
