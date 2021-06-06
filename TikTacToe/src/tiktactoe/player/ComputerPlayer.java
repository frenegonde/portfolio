package tiktactoe.player;

import tiktactoe.gameplay.Fishka;

public class ComputerPlayer extends Player{
    public void makeStep(int ochered, String[][] arrField)
    {

        int row = (int)(Math.random()*arrField.length);
        int col = (int)(Math.random()*arrField.length);
        while(!isEmptyCell(row, col,arrField)){
            row = (int)(Math.random()*arrField.length);
            col = (int)(Math.random()*arrField.length);
        }
        if(ochered % 2 == 0) {
            arrField[row][col] = Fishka.X.toString();
        }else{
            arrField[row][col] = Fishka.O.toString();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
