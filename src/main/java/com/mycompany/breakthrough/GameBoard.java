package com.mycompany.breakthrough;

/**
 * Represents the game board.
 */
public class GameBoard {

    private int size;
    private Doll[][] board;

    /**
     * Constructs a GameBoard of a given size.
     *
     * @param size the size of the board (number of cells in a row and column)
     */
    public GameBoard(int size) {
        this.size = size;
        board = new Doll[size][size];
    }

    /**
     * Places a doll at a specified position on the board.
     *
     * @param piece the doll to place
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     * @throws IllegalStateException if the position is already occupied
     */
    public void placePiece(Doll piece, int x, int y) {
        if (board[x][y] != null) {
            throw new IllegalStateException("Position already occupied");
        }
        board[x][y] = piece;
        piece.move(x, y);
    }

    /**
     * Moves a doll from one position to another on the board.
     *
     * @param oldX the current x-coordinate of the doll
     * @param oldY the current y-coordinate of the doll
     * @param newX the new x-coordinate of the doll
     * @param newY the new y-coordinate of the doll
     */
    public void movePiece(int oldX, int oldY, int newX, int newY) {
        Doll piece = board[oldX][oldY];
        board[newX][newY] = piece;
        board[oldX][oldY] = null;
        piece.move(newX, newY);
    }

    /**
     * Removes a doll from a specified position on the board.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public void removePiece(int x, int y) {
        if (board[x][y] != null) {
            board[x][y] = null;
        }
    }

    /**
     * Checks if a position is within the boundaries of the board.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     * @return true if the position is inside the board, false otherwise
     */
    public boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    /**
     * Gets the size of the board.
     *
     * @return the size of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Retrieves a doll from a specified position on the board.
     *
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     * @return the doll at the specified position
     */
    public Doll getPiece(int x, int y) {
        return board[x][y];
    }
}
