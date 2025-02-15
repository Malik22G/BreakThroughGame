package com.mycompany.breakthrough;

import javax.swing.*;

/**
 * Controls the interaction between the model and view in the Break-through
 * game.
 */
public class BreakThroughController {

    private Game game;
    private BreakThroughView view;

    /**
     * Constructs a controller for managing the game.
     *
     * @param game the game model
     * @param view the game view
     */
    public BreakThroughController(Game game, BreakThroughView view) {
        this.game = game;
        this.view = view;
        if (this.view != null) {
            this.view.setController(this);
            initController();
        }
    }

    /**
     * Initializes the controller, setting up listeners and initial view state.
     */
    private void initController() {
        view.updateBoard();
    }

    /**
     * Handles cell clicks by updating the model and the view accordingly.
     *
     * @param x the x-coordinate of the clicked cell
     * @param y the y-coordinate of the clicked cell
     */
    public void handleCellClick(int x, int y) {

        try {

            Doll clickedDoll = game.getBoard().getPiece(x, y);

            if (game.getSelectedDoll() != null) {
                if (clickedDoll != null && clickedDoll.getOwner() == game.getCurrentPlayer()) {
                    if (clickedDoll == game.getSelectedDoll()) {
                        game.setSelectedDoll(null);
                    } else {
                        System.out.println("Changing selection to doll at " + x + ", " + y);
                        game.setSelectedDoll(clickedDoll);
                    }
                } else {
                    game.makeMove(game.getSelectedDoll().getX(), game.getSelectedDoll().getY(), x, y);
                    System.out.println("Moved selected doll to " + x + ", " + y);

                    game.setSelectedDoll(null);
                }
            } else if (clickedDoll != null && clickedDoll.getOwner() == game.getCurrentPlayer()) {
                System.out.println("Setting selected doll at " + x + ", " + y);
                game.setSelectedDoll(clickedDoll);
            }
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            game.setSelectedDoll(null);
        } finally {
            view.updateBoard();
            if (game.hasWon(game.getCurrentPlayer())) {
                JOptionPane.showMessageDialog(view, game.getCurrentPlayer().getName() + " Wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

                resetGame();
            }
        }
    }

    /**
     * Changes the size of the board, resetting the game.
     *
     * @param newSize the new size of the board
     */
    public void changeBoardSize(int newSize) {
        game = new Game(newSize);
        view.changeBoardSize(newSize);
    }

    /**
     * Sets the view for this controller and initializes it. This method is
     * useful when the view needs to be instantiated separately from the
     * controller and set after the controller's creation.
     *
     * @param view the BreakThroughView to be managed by this controller
     */
    public void setView(BreakThroughView view) {
        this.view = view;
        this.view.setController(this);
        initController();
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        game.reset();
        view.updateBoard();
    }

    // Additional methods to expose necessary data to the view
    /**
     * Gets the piece at the specified board position.
     *
     * @param x the x-coordinate on the board
     * @param y the y-coordinate on the board
     * @return the Doll at the specified position
     */
    public Doll getPieceAt(int x, int y) {
        return game.getBoard().getPiece(x, y);
    }

    /**
     * Returns the first player.
     *
     * @return the first player
     */
    public Player getPlayer1() {
        return game.getPlayer1();
    }

    /**
     * Returns the doll currently selected in the game.
     *
     * @return the selected Doll, if any
     */
    public Doll getSelectedDoll() {
        return game.getSelectedDoll();
    }

    public Game getGame() {
        return game;
    }
}
