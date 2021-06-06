package tiktactoe.gameplay;

import tiktactoe.player.ComputerPlayer;
import tiktactoe.player.HumanPlayer;
import tiktactoe.player.Player;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private String[][] arrField = new String[3][3];

    public Game(){
        for (int i = 0; i < arrField.length; i++) {
            for (int j = 0; j < arrField.length; j++) {
                arrField[i][j] =  Fishka.EMPTY.toString();
            }
        }
    }

    public void printBoard(){
        int cellSize = 3;
        int cols = arrField[0].length;
        int rowLength = cols * cellSize + cols + 1;
        final char[] array = new char[rowLength];
        Arrays.fill(array, '-');
        String rowDivider = new String(array);

        for(int i = 0; i < arrField.length; i++)
        {
            System.out.println(rowDivider);
            for(int j = 0; j < arrField[i].length; j++)
            {
                System.out.printf("|%2s%1s", arrField[i][j], "");
                if(j == (arrField[i].length - 1)) System.out.println("|");
            }
        }
        System.out.println(rowDivider);
    }



    public void startGame(){
        Player player1 = null;
        Player player2 = null;
        int input = 0;
        Scanner scn = new Scanner(System.in);
        System.out.println("1. Человек vs Человек\n" +
                "2. Человек vs Компьютер\n" +
                "3. Компьютер vs Компьютер\n"+
                "4. Выход");
        input = scn.nextInt();
        if(input==1){
            player1 = new HumanPlayer();
            player2 = new HumanPlayer();
        }
        else if(input==2){
            player1 = new HumanPlayer();
            player2 = new ComputerPlayer();
        }
        else if(input==3){
            player1 = new ComputerPlayer();
            player2 = new ComputerPlayer();
        }


        int ochered = 0;
        while(true){
            printBoard();
            if(ochered % 2 == 0){
                System.out.println("Ход первого игрока");
                player1.makeStep(ochered, arrField);
                if(findWinner(Fishka.X)){
                    System.out.println("Победили крестики");
                    printBoard();
                    break;
                }
            }else {
                System.out.println("Ход второго игрока");
                player2.makeStep(ochered, arrField);
                if(findWinner(Fishka.O)){
                    System.out.println("Победили нолики");
                    printBoard();
                    break;
                }
            }
            if(isFullTable()){
                System.out.println("Ничья");
                printBoard();
                break;
            }
            ochered++;
        }
    }

    boolean isFullTable(){
        for (int i = 0; i < arrField.length; i++) {
            for (int j = 0; j < arrField.length; j++) {
                if (arrField[i][j].equalsIgnoreCase(Fishka.EMPTY.toString())){
                    return false;
                }
            }
        }
        return true;
    }

    boolean isHorizontalWinner(Fishka fishka){
        for (int i = 0; i < arrField.length; i++) {
            if (arrField[i][0].equals(fishka.toString())
                    && arrField[i][1].equals(fishka.toString())
                    && arrField[i][2].equals(fishka.toString())) {
                return true;
            }
        }
        return false;
    }
    boolean isVerticalWinner(Fishka fishka){
        for (int i = 0; i < arrField.length; i++) {
            if (arrField[0][i].equals(fishka.toString())
                    && arrField[1][i].equals(fishka.toString())
                    && arrField[2][i].equals(fishka.toString())) {
                return true;
            }
        }
        return false;
    }

    boolean isDeagonalWinner(Fishka fishka){
        return arrField[0][0].equals(fishka.toString()) && arrField[1][1].equals(fishka.toString()) && arrField[2][2].equals(fishka.toString())
                || arrField[0][2].equals(fishka.toString()) && arrField[1][1].equals(fishka.toString()) && arrField[2][0].equals(fishka.toString());
    }



    boolean findWinner(Fishka fishka){
        return isHorizontalWinner(fishka) || isVerticalWinner(fishka) || isDeagonalWinner(fishka);
    }





}
