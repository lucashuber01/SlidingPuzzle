package progra3;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class SlidingPuzzleGUI extends JFrame implements KeyListener {

	private JPanel gridPanel;
    private JButton[][] buttons;
    private PuzzleLogic puzzleLogic;
    private ImagePuzzle imagen;

    public SlidingPuzzleGUI() {
        setTitle("Sliding Puzzle");
        setSize(600, 470);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 4));
        buttons = new JButton[4][4];

        // Inicializa la lógica del puzzle
        puzzleLogic = new PuzzleLogic();

        //Carga la imagen
        imagen = new ImagePuzzle("/progra3/fruta.jpg", 4, 4);


        // Carga el estado inicial del puzzle en la interfaz
        initializeBoard();

        addKeyListener(this);
        add(gridPanel);
        setVisible(true);

        // Asegura que el JFrame tenga el foco justo después de hacerse visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                requestFocusInWindow();
            }
        });
    }

    private void initializeBoard() {

        int[][] board = puzzleLogic.getBoard();
        BufferedImage[] imageParts = imagen.getImageParts();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	buttons[i][j] = new JButton();
            	
                if (board[i][j] != 0) {
                    buttons[i][j].setIcon(new ImageIcon(imageParts[board[i][j] - 1]));// Casillero vacío
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                } 
                buttons[i][j].setHorizontalTextPosition(JButton.CENTER);
                buttons[i][j].setVerticalTextPosition(JButton.CENTER);
                gridPanel.add(buttons[i][j]);
            }
        }
    }

    private void updateBoard() {
        int[][] board = puzzleLogic.getBoard();
        BufferedImage[] imageParts = imagen.getImageParts();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    buttons[i][j].setIcon(null); // Casillero vacío
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setIcon(new ImageIcon(imageParts[board[i][j] - 1]));
                    buttons[i][j].setText(String.valueOf(board[i][j]));
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        boolean moved = false;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                moved = puzzleLogic.moveTile(puzzleLogic.getEmptyRow() + 1, puzzleLogic.getEmptyCol());
                break;
            case KeyEvent.VK_DOWN:
                moved = puzzleLogic.moveTile(puzzleLogic.getEmptyRow() - 1, puzzleLogic.getEmptyCol());
                break;
            case KeyEvent.VK_LEFT:
                moved = puzzleLogic.moveTile(puzzleLogic.getEmptyRow(), puzzleLogic.getEmptyCol() + 1);
                break;
            case KeyEvent.VK_RIGHT:
                moved = puzzleLogic.moveTile(puzzleLogic.getEmptyRow(), puzzleLogic.getEmptyCol() - 1);
                break;
        }

        if (moved) {
            updateBoard(); // Actualiza el tablero en la interfaz gráfica
        }
     // Verificar si el puzzle está resuelto
        if (puzzleLogic.isSolved()) {
            JOptionPane.showMessageDialog(this, "¡Felicidades! Has resuelto el puzzle.");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        // Crea el rompecabezas con la interfaz gráfica
        new SlidingPuzzleGUI();
    }

}
