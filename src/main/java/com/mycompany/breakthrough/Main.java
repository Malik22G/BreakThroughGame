package com.mycompany.breakthrough;

import javax.swing.*;

/**
 * Main class to launch the Break-through game. This class initializes the game
 * by setting up the MVC components: the model (Game), the view
 * (BreakThroughView), and the controller (BreakThroughController).
 */
public class Main {

    /**
     * The main method to start the application. It sets up the game components
     * and shows the GUI. It runs the setup on the Event Dispatch Thread to
     * ensure thread safety for Swing components.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game(6);

            BreakThroughController controller = new BreakThroughController(game, null);

            BreakThroughView view = new BreakThroughView(6, controller);

            controller.setView(view);
        });
    }
}
