package game_of_life_test.test;

import org.junit.jupiter.api.Test;
import game_of_life.src.Cell;
import game_of_life.src.CellState;
import game_of_life.src.Grid;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    public void testLoadGridFromMatrix() {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 1, 0, 0}
        };
        Grid grid = new Grid(matrix);

        int[][] gridState = grid.getGridState();
        assertArrayEquals(gridState, matrix);
    }

    @Test
    public void testGetCellList() {
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 1},
                {1, 0, 1}
        };
        Grid grid = new Grid(matrix);
        List<Cell> cells = grid.getCellList();
        Cell expectedCellPosition0_0 = new Cell(CellState.DEAD.getValue(), new int[]{0, 1, 0});
        Cell expectedCellPosition0_2 = new Cell(CellState.DEAD.getValue(), new int[]{1, 1, 0});
        Cell expectedCellPosition2_0 = new Cell(CellState.ALIVE.getValue(), new int[]{0, 1, 0});
        Cell expectedCellPosition2_2 = new Cell(CellState.ALIVE.getValue(), new int[]{1, 1, 0});
        Cell expectedCellPosition1_1 = new Cell(CellState.ALIVE.getValue(), new int[]{0, 0, 0, 1, 1, 0, 1, 0});
        assertEquals(expectedCellPosition0_0, cells.get(0));
        assertEquals(expectedCellPosition0_2, cells.get(2));
        assertEquals(expectedCellPosition2_0, cells.get(6));
        assertEquals(expectedCellPosition2_2, cells.get(8));
        assertEquals(expectedCellPosition1_1, cells.get(4));
    }

    @Test
    public void testEvolveGrid(){
        int[][] matrix = {
                {0, 1, 0},
                {0, 1, 1},
                {1, 1, 1}
        };
        int[][] evoledMatrix = {
                {0, 1, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        Grid grid = new Grid(matrix);
        grid.evolve();
        int[][] gridState = grid.getGridState();
        assertArrayEquals(gridState, evoledMatrix);
    }
}
