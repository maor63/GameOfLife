package game_of_life.src;

public class GridConsoleGUI {

    private final Grid grid;

    public GridConsoleGUI(Grid grid) {
        this.grid = grid;
    }

    public void show() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        int[][] matrix = grid.getGridState();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] == 1 ? "X" : "0" + " ");
            }
            System.out.println();
        }
    }
}
