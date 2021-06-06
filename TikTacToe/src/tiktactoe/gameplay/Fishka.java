package tiktactoe.gameplay;

public enum Fishka {
    X("X"), O("O"), EMPTY("");

    private String val;

    Fishka(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
