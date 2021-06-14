package game;

import java.util.Scanner;

public class GameService {
    private Game game;
    private final int GAME_OVER = 0;
    private int finishGameCause = -1; //код причины завершения игры : либо GAME_OVER, либо WIN
    public GameService(int size){
        game = new Game(size);
    }


    /**
     * Запускает игру
     */
    public void startGame(){
        Scanner scn = new Scanner(System.in);
        boolean isGameOver = false;
        while(!isGameOver){
            game.printVisibleBoard();
            System.out.println();
            game.printHideBoard();
            System.out.println("Enter row index");
            int rowIndex = scn.nextInt();
            System.out.println("Enter column index");
            int colIndex = scn.nextInt();
            if(!game.isCellExist(rowIndex, colIndex))
            {
                System.out.println("Введены некорректные координаты. Повторите ввод");
                continue;
            }

            if(!game.openCell(rowIndex, colIndex, new String[game.getBoardSize()][game.getBoardSize()])){
                isGameOver = true;
                finishGameCause = GAME_OVER;
            }
        }
        if(finishGameCause == GAME_OVER){
            System.out.println("GAME OVER!!!!");
        }else{
            System.out.println("YOU WIN!!!");
        }
    }


}
