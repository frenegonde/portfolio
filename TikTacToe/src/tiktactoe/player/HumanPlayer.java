package tiktactoe.player;

import tiktactoe.gameplay.Fishka;

public class HumanPlayer extends Player {
    public void makeStep(int ochered, String[][] arrField)
    {
        System.out.println("Enter row index");
        int row = scn.nextInt();
        System.out.println("Enter col index");
        int col = scn.nextInt();
        while (!isEmptyCell(row, col, arrField)) {
           System.out.println("wrong coordinates. please, try again: ");
           System.out.println("Enter row index");
           row = scn.nextInt();
           System.out.println("Enter col index");
           col = scn.nextInt();
        }
        if (ochered % 2 == 0) {
            arrField[row][col] = Fishka.X.toString();
        } else {
            arrField[row][col] = Fishka.O.toString();
        }
    }





}
