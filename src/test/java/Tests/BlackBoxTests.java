/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Tests;

import com.mycompany.breakthrough.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author malik
 */
public class BlackBoxTests {

    @Test
    public void testPlayerWinning() {
        Game game = new Game(8);
        Player player1 = game.getPlayer1();

        game.getBoard().getPiece(7, 7).setOwner(player1);

        assertTrue(game.hasWon(player1));
    }

    @Test
    public void testValidCapture() {
        Game game = new Game(8);
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        Doll dollPlayer1 = new Doll(2, 2, player1);
        Doll dollPlayer2 = new Doll(3, 3, player2);

        game.getBoard().placePiece(dollPlayer1, 2, 2);
        game.getBoard().placePiece(dollPlayer2, 3, 3);

        game.setSelectedDoll(dollPlayer1);

        game.makeMove(2, 2, 3, 3);

        assertNull(game.getBoard().getPiece(2, 2), "Player 1's doll should have moved from (2, 2)");
        assertNotNull(game.getBoard().getPiece(3, 3), "Player 1's doll should now be at (3, 3)");
        assertEquals(player1, game.getBoard().getPiece(3, 3).getOwner(), "The doll at (3, 3) should belong to Player 1");
    }

    @Test
    public void testInvalidMovement() {
        Game game = new Game(8);
        Player player = game.getPlayer1();
        Doll doll = game.getBoard().getPiece(1, 1);
        game.setSelectedDoll(doll);

        assertFalse(game.isValidMove(1, 1, 0, 1));
    }

    @Test
    public void testClickOutsideBoard() {
        Game game = new Game(8);
        BreakThroughController controller = new BreakThroughController(game, null);

        assertThrows(NullPointerException.class, () -> {
            controller.handleCellClick(12, 12);
        });
    }

}
