package com.mycompany.breakthrough;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the graphical user interface for the Break-through game, managing
 * all user interactions and visual updates.
 */
public class BreakThroughView extends JFrame {

    private static final int CELL_SIZE = 60;
    private JButton[][] cellViews;
    private int gameSize;
    private BreakThroughController controller;

    /**
     * Constructs the view for the game and initializes the user interface
     * components.
     *
     * @param gameSize The size of the game board, determining the grid layout
     * dimensions
     * @param controller The game controller that handles all interactions and
     * game logic
     */
    public BreakThroughView(int gameSize, BreakThroughController controller) {
        this.gameSize = gameSize;
        this.controller = controller;
        initializeGUI();
    }

    /**
     * Initializes the graphical user interface, setting up the frame, board,
     * and menu bar.
     */
    private void initializeGUI() {
        setTitle("Break-through Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupMenuBar();
        setupBoard(gameSize);
        packAndShow();
    }

    /**
     * Sets the game controller for this view.
     *
     * @param controller The controller managing game interactions
     */
    public void setController(BreakThroughController controller) {
        this.controller = controller;
    }

    /**
     * Sets up the menu bar with options for changing board size, resetting the
     * game, and exiting.
     */
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu boardSizeMenu = new JMenu("Board Size");
        menuBar.add(boardSizeMenu);

        String[] sizes = {"6x6", "8x8", "10x10"};
        for (String size : sizes) {
            JMenuItem sizeItem = new JMenuItem(size);
            sizeItem.addActionListener(e -> {
                gameSize = Integer.parseInt(size.split("x")[0]);
                controller.changeBoardSize(gameSize);
            });
            boardSizeMenu.add(sizeItem);
        }

        menuBar.add(Box.createHorizontalGlue());

        JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.addActionListener(e -> controller.resetGame());
        menuBar.add(resetItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menuBar.add(exitItem);

        setJMenuBar(menuBar);
    }

    /**
     * Changes the game board size and re initializes the UI components
     * accordingly.
     *
     * @param newSize The new size of the game board
     */
    public void changeBoardSize(int newSize) {
        this.gameSize = newSize;
        dispose();
        setupBoard(newSize);
        packAndShow();
    }

    /**
     * Initializes the game board with buttons for each cell, configuring the
     * layout based on the board size.
     *
     * @param boardSize The size of the board to determine the grid layout
     */
    private void setupBoard(int boardSize) {
        getContentPane().removeAll();
        JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        boardPanel.setPreferredSize(new Dimension(boardSize * CELL_SIZE, boardSize * CELL_SIZE));
        cellViews = new JButton[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton cell = new JButton();
                cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cell.setBackground((i % 2 == j % 2) ? Color.LIGHT_GRAY : Color.GRAY);
                int finalI = i;
                int finalJ = j;
                cell.addActionListener(e -> controller.handleCellClick(finalI, finalJ));
                boardPanel.add(cell);
                cellViews[i][j] = cell;
            }
        }
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        updateBoard();
    }

    /**
     * Packs the frame and ensures it is displayed in the center of the screen.
     */
    private void packAndShow() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Updates the visual representation of the game board to reflect the
     * current state.
     */
    public void updateBoard() {
        ImageIcon player1Icon = createImageIcon("images/player1_doll.png");
        ImageIcon player2Icon = createImageIcon("images/player2_doll.png");
        Doll selectedDoll = controller.getSelectedDoll();

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                JButton button = cellViews[i][j];
                Doll piece = controller.getPieceAt(i, j);
                button.setBorder(null);
                button.setBackground((i % 2 == j % 2) ? Color.LIGHT_GRAY : Color.GRAY);

                if (piece != null) {
                    button.setIcon(piece.getOwner() == controller.getPlayer1() ? player1Icon : player2Icon);
                } else {
                    button.setIcon(null);
                }

                if (selectedDoll != null && controller.getGame().isValidMove(selectedDoll.getX(), selectedDoll.getY(), i, j)) {
                    button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }

                if (piece != null && piece == selectedDoll) {
                    button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                    button.setBackground(new Color(245, 222, 179));
                }
            }
        }
    }

    /**
     * Creates an ImageIcon from a given file path, scaling it to fit the cell
     * size.
     *
     * @param path The path to the image file
     * @return An ImageIcon object
     */
    private ImageIcon createImageIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
}
