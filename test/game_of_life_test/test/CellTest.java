package game_of_life_test.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game_of_life.src.Cell;
import game_of_life.src.CellState;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell aliveCellWithoutNeighbors;
    private int[] deadCellNeighbors;
    private Cell deadCell;
    private Cell aliveCellWithOneNeighbor;

    @BeforeEach
    void setUp() {
        aliveCellWithoutNeighbors = new Cell(CellState.ALIVE.getValue(), new int[0]);
        aliveCellWithOneNeighbor = new Cell(CellState.ALIVE.getValue(), new int[]{0,1,0,0});
        deadCellNeighbors = new int[]{1, 0, 1, 0};
        deadCell = new Cell(CellState.DEAD.getValue(), deadCellNeighbors);
    }

    @Test
    void createAliveCellWithoutNeighbors() {
        assertEquals(CellState.ALIVE.getValue(), aliveCellWithoutNeighbors.getState());
        assertArrayEquals(new int[]{}, aliveCellWithoutNeighbors.getNeighbors());
    }

    @Test
    void createDeadCellWith3Neighbors() {
        assertEquals(CellState.DEAD.getValue(), deadCell.getState());
        assertArrayEquals(deadCellNeighbors, deadCell.getNeighbors());
    }

    @Test
    void cellNeighborAreDeepCopied() {
        deadCellNeighbors[2] = 0;
        assertArrayEquals(new int[]{1, 0, 1, 0}, deadCell.getNeighbors());
    }

    @Test
    void aliveCellWithoutNeighborsShouldDie(){
        assertEquals(CellState.DEAD.getValue(), aliveCellWithoutNeighbors.evolve());
    }

    @Test
    void aliveCellWithOneNeighborShouldDie(){
        assertEquals(CellState.DEAD.getValue(), aliveCellWithOneNeighbor.evolve());
    }

    @Test
    void aliveCellWithTwoNeighborShouldLive(){
        Cell cell = new Cell(CellState.ALIVE.getValue(), new int[]{0,1,0,1,0,0,0,0});
        assertEquals(CellState.ALIVE.getValue(), cell.evolve());
    }

    @Test
    void aliveCellWithTreeNeighborShouldLive(){
        Cell cell = new Cell(CellState.ALIVE.getValue(), new int[]{0,1,0,1,0,0,0,1});
        assertEquals(CellState.ALIVE.getValue(), cell.evolve());
    }

    @Test
    void aliveCellWithFourNeighborShouldDie(){
        Cell cell = new Cell(CellState.ALIVE.getValue(), new int[]{0,1,0,1,0,0,1,1});
        assertEquals(CellState.DEAD.getValue(), cell.evolve());
    }

    @Test
    void aliveCellWith8NeighborShouldDie(){
        Cell cell = new Cell(CellState.ALIVE.getValue(), new int[]{1,1,1,1,1,1,1,1});
        assertEquals(CellState.DEAD.getValue(), cell.evolve());
    }

    @Test
    void deadCellWith2NeighborShouldDie(){
        Cell cell = new Cell(CellState.DEAD.getValue(), new int[]{0,1,1,0,0,0,0,0});
        assertEquals(CellState.DEAD.getValue(), cell.evolve());
    }

    @Test
    void deadCellWith3NeighborShouldLive(){
        Cell cell = new Cell(CellState.DEAD.getValue(), new int[]{0,1,1,0,0,0,1,0});
        assertEquals(CellState.ALIVE.getValue(), cell.evolve());
    }

    @Test
    void deadCellWith4NeighborShouldLive(){
        Cell cell = new Cell(CellState.DEAD.getValue(), new int[]{0,1,1,0,1,0,1,0});
        assertEquals(CellState.DEAD.getValue(), cell.evolve());
    }
}