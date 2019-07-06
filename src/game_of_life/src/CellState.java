package game_of_life.src;

public enum CellState {
    ALIVE(1), DEAD(0);

    private final int value;

    CellState(int id) {
        value = id;
    }
    public int getValue(){
        return value;
    }
}
