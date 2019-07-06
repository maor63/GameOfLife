package game_of_life.src;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0},
                {1, 1, 1, 0, 0}
        };
        Grid grid = new Grid(matrix);
        GridConsoleGUI gridConsoleGUI = new GridConsoleGUI(grid);
        while (true) {
            gridConsoleGUI.show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            grid.evolve();
        }
    }
}
