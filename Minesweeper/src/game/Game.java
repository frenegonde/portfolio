package game;

public class Game {

    private String[][] visibleBoard;
    private String[][] hideBoard;
    private final String EMPTY_CELL = "|_|";
    private final String BOMB_CELL = "|b|";
    private final String CLOSED_CELL = "|*|";

    public Game(int size){
        visibleBoard = new String[size][size];
        hideBoard = new String[size][size];

        for (int i = 0; i < visibleBoard.length; i++) {
            for (int j = 0; j < visibleBoard[i].length; j++) {
                visibleBoard[i][j] = CLOSED_CELL;
            }
        }

        for (int i = 0; i < hideBoard.length; i++) {
            for (int j = 0; j < hideBoard[i].length; j++) {
                int flag = (int)(Math.random()*3);
                if(flag == 0 || flag == 1) {
                    hideBoard[i][j] = EMPTY_CELL;
                }else{
                    hideBoard[i][j] = BOMB_CELL;
                }
            }
        }


    }


    public int getBoardSize(){
        return visibleBoard.length;
    }

    /**
     * Рекурсивный метод, который срабатывает при открытии ячейки,
     * ищет свободные открытые ячейки, открывает их, либо проставляет цифры в эти ячейки, обозначающие количество мин вокруг
     * @param rowIndex индекс строки
     * @param colIndex индекс столбца
     * @param arrTrackedCell хранит уже посещенные ячейки для исключения повторного посещения одной и той же ячейки
     * @return возвращает true, если ячейка была свободна или false, если в ячейке была мина
     */
    public boolean openCell(int rowIndex, int colIndex, String[][] arrTrackedCell){
        if(arrTrackedCell[rowIndex][colIndex]!=null){
            return true;
        }
        if(!isBombExist(rowIndex, colIndex)){
            arrTrackedCell[rowIndex][colIndex] = "visited";
            int currentRow = rowIndex;
            int currentCol = colIndex-1;
            int mineCount = 0;
            mineCount+=viewCell(currentRow, currentCol, arrTrackedCell);

            currentRow = rowIndex-1;
            currentCol = colIndex;
            mineCount+=viewCell(currentRow, currentCol, arrTrackedCell);


            currentRow = rowIndex;
            currentCol = colIndex + 1;
            mineCount+=viewCell(currentRow, currentCol, arrTrackedCell);

            currentRow = rowIndex+1;
            currentCol = colIndex;
            mineCount+=viewCell(currentRow, currentCol, arrTrackedCell);

            if(mineCount==0){
                visibleBoard[rowIndex][colIndex] = EMPTY_CELL;
            }else{
                visibleBoard[rowIndex][colIndex] = "|"+mineCount+"|"; //конвертируем число в строку за счет сложения с пустой строкой
            }

            return true;
        }
        return false;
    }


    /**
     * Метод является частью рекурсивного метода openCell и позволяет посчитать, сколько мин находится вокруг ячейки
     * @param currentRow координата строки ячейки
     * @param currentCol координата столбца ячейки
     * @param arrTrackedCell массив-игровое поле
     * @return возвращает 0, если в ячейке нет мины или 1, если мина есть
     */
    public int viewCell(int currentRow, int currentCol, String[][] arrTrackedCell){
        int res = checkMines(currentRow, currentCol);
        if(res==0){
            openCell(currentRow, currentCol, arrTrackedCell);
            return 0;
        }else if(res == 1){
            return 1;
        }
        else{
            return 0;
        }

    }

    /**
     * Метод проверяет, есть ли мина в ячейке
     * @param currentRow координата строки в двумерном массиве
     * @param currentCol координата столбца в двумерном массиве
     * @return возвращает 1, если есть мина,
     * 0 - если мины нет
     * -1 - если ячейка не существует
     */
    public int checkMines(int currentRow, int currentCol){
        if(isCellExist(currentRow, currentCol)){

            if(isBombExist(currentRow, currentCol)){
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }

    public void printVisibleBoard(){
        System.out.print("   ");
        for (int i = 0; i < visibleBoard.length; i++) {
            System.out.print(i+"  ");
        }
        System.out.println();
        for (int i = 0; i < visibleBoard.length; i++) {
            System.out.print(i+" ");
            for (int j = 0; j < visibleBoard[i].length; j++) {
                System.out.print(visibleBoard[i][j]);
            }
            System.out.println();
        }
    }

    public void printHideBoard(){
        System.out.print("   ");
        for (int i = 0; i < hideBoard.length; i++) {
            System.out.print(i+"  ");
        }
        System.out.println();
        for (int i = 0; i < hideBoard.length; i++) {
            System.out.print(i+" ");
            for (int j = 0; j < hideBoard[i].length; j++) {
                System.out.print(hideBoard[i][j]);
            }
            System.out.println();
        }
    }


    public boolean isCellExist(int rowIndex, int colIndex){
        return rowIndex>=0 && rowIndex<getBoardSize() && colIndex>=0 && colIndex<getBoardSize();
    }

    private boolean isBombExist(int rowIndex, int colIndex){
        return hideBoard[rowIndex][colIndex].equals(BOMB_CELL);
    }

}
