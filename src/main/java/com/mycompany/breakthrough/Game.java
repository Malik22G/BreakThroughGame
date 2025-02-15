package com.mycompany.breakthrough;

/**
 * Represents the game logic and state for the Break-through game.
 */
public class Game {

    private GameBoard board; // Game board holding all game pieces.
    private int boardSize; // Size of the game board.
    private Player player1; // First player in the game.
    private Player player2; // Second player in the game.
    private Player currentPlayer; // Player currently taking their turn.
    private Doll selectedDoll; // Doll currently selected for movement.

    /**
     * Constructs a new game with the specified board size and initializes
     * players.
     *
     * @param boardSize the size of the game board
     */
    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new GameBoard(boardSize);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        initializePieces(player1, 0);
        initializePieces(player2, boardSize - 2);
        currentPlayer = player1;
    }

    /**
     * Initializes pieces on the board for a specified player at a given
     * starting row.
     *
     * @param player the player whose pieces are being placed
     * @param startRow the starting row for placing the pieces
     */
    private void initializePieces(Player player, int startRow) {
        for (int i = startRow; i < startRow + 2; i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Doll piece = new Doll(i, j, player);
                board.placePiece(piece, i, j);
                player.addPiece(piece);
            }
        }
    }

    /**
     * Attempts to make a move from a start position to an end position on the
     * board. Checks for win conditions after making a move.
     *
     * @param startX the x-coordinate of the start position
     * @param startY the y-coordinate of the start position
     * @param endX the x-coordinate of the end position
     * @param endY the y-coordinate of the end position
     * @throws IllegalStateException if the move is invalid
     */
    public void makeMove(int startX, int startY, int endX, int endY) throws IllegalStateException {
        if (!isValidMove(startX, startY, endX, endY)) {
            throw new IllegalStateException("Invalid move");
        }
        Doll piece = board.getPiece(startX, startY);
        board.movePiece(startX, startY, endX, endY);
        if (Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1) {
            if (board.getPiece(endX, endY) != null && board.getPiece(endX, endY).getOwner() != piece.getOwner()) {
                board.removePiece(endX, endY);
            }
        }

        if (hasWon(currentPlayer)) {
            return;
        }

        switchTurn();
    }

    /**
     * Checks if a move is valid based on the game's rules.
     *
     * @param startX the x-coordinate of the start position
     * @param startY the y-coordinate of the start position
     * @param endX the x-coordinate of the end position
     * @param endY the y-coordinate of the end position
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        if (!board.isInsideBoard(endX, endY)) {
            return false;
        }
        int direction = currentPlayer == player1 ? 1 : -1;
        if (endX != startX + direction) {
            return false;
        }
        if (Math.abs(endY - startY) > 1) {
            return false;
        }
        Doll targetDoll = board.getPiece(endX, endY);
        if (targetDoll == null) {
            return true;
        } else {
            return Math.abs(endY - startY) == 1 && targetDoll.getOwner() != currentPlayer;
        }
    }

    /**
     * Determines if a player has won the game by checking if any of their dolls
     * have reached the opposite end of the board.
     *
     * @param player the player to check for a win
     * @return true if the player has won, false otherwise
     */
    public boolean hasWon(Player player) {
        int lastRow = player == player1 ? board.getSize() - 1 : 0;
        for (int j = 0; j < board.getSize(); j++) {
            Doll doll = board.getPiece(lastRow, j);
            if (doll != null && doll.getOwner() == player) {
                return true;
            }
        }
        return false;
    }

    /**
     * Switches the current player's turn.
     */
    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Getters for accessing game state information
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Doll getSelectedDoll() {
        return selectedDoll;
    }

    public void setSelectedDoll(Doll doll) {
        selectedDoll = doll;
    }

    public GameBoard getBoard() {
        return board;
    }

    /**
     * Resets the game to its initial state, reinitializing the board and
     * redistributing pieces.
     */
    public void reset() {
        board = new GameBoard(boardSize);
        player1.clearPieces();
        player2.clearPieces();
        initializePieces(player1, 0);
        initializePieces(player2, boardSize - 2);
        currentPlayer = player1;
        selectedDoll = null;
    }
}
