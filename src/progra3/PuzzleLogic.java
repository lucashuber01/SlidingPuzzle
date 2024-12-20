package progra3;

import java.util.Random;



public class PuzzleLogic {

    private int[][] board; // Tablero del juego
    private int emptyRow;  // Fila del casillero vacío
    private int emptyCol;  // Columna del casillero vacío

    public PuzzleLogic() {
        // Carga un patrón aleatorio
        loadRandomPattern();
    }

    public void loadRandomPattern() {
        Random rand = new Random();
        int pattern = rand.nextInt(6) + 1; // Genera un número aleatorio entre 1 y 6
        System.out.println("Pattern generado: " + pattern);
        loadPattern(pattern);
    }

    public void loadPattern(int pattern) {
        switch (pattern) {
            case 1:
                board = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 0},
                    {13, 14, 15, 12}
                };
                emptyRow = 2;
                emptyCol = 3;
                break;
            case 2:
                board = new int[][]{
                    {2, 1, 4, 3},
                    {6, 5, 8, 7},
                    {0, 10, 9, 11},
                    {13, 12, 14, 15}
                };
                emptyRow = 2;
                emptyCol = 0;
                break;
            case 3:
                board = new int[][]{
                    {4, 2, 3, 1},
                    {7, 8, 6, 5},
                    {9, 0, 10, 11},
                    {13, 14, 15, 12}
                };
                emptyRow = 2;
                emptyCol = 1;
                break;
            case 4:
                board = new int[][]{
                    {7, 2, 10, 8},
                    {9, 15, 0, 4},
                    {5, 3, 1, 11},
                    {13, 14, 6, 12}
                };
                emptyRow = 1;
                emptyCol = 2;
                break;
            case 5:
                board = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 15, 0},
                    {13, 14, 12, 11}
                };
                emptyRow = 2;
                emptyCol = 3;
                break;
            case 6:
            	board = new int[][] {
            		{1, 3, 2, 4},
            		{8, 6, 7, 5},
            		{0, 9, 10, 15},
            		{13, 14, 12, 11}
            	};
            	emptyRow = 2;
            	emptyCol = 0;
            	break;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean moveTile(int newRow, int newCol) {
        if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
            // Intercambiar el casillero vacío con el botón adyacente
            board[emptyRow][emptyCol] = board[newRow][newCol];
            board[newRow][newCol] = 0;
            emptyRow = newRow;
            emptyCol = newCol;
            return true; // Mueve exitosamente
        }
        return false; // Movimiento inválido
    }

    public boolean isSolved() {
        int[][] solvedBoard = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != solvedBoard[i][j]) {
                    return false; // Si alguna celda no coincide, el puzzle no está resuelto
                }
            }
        }
        return true; // Todas las celdas coinciden, el puzzle está resuelto
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public int getEmptyCol() {
        return emptyCol;
    }
}
