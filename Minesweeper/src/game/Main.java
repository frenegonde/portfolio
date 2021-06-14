package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GameService gameService = new GameService(10);
        gameService.startGame();

    }

}
