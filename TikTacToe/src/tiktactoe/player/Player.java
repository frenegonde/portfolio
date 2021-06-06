package tiktactoe.player;

import tiktactoe.gameplay.Fishka;

import java.util.Scanner;

public abstract class Player {
    protected final Scanner scn = new Scanner(System.in);
    public abstract void makeStep(int ochered, String[][] arrField);

    public boolean isEmptyCell(int x, int y, String[][] arrField){
        if (x<0 || y<0 || x>=3 || y>=3){
            return false;
        }
        return arrField[x][y].equalsIgnoreCase(Fishka.EMPTY.toString());
    }
}
