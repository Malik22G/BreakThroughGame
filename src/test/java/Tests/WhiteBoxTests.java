package Tests;

import com.mycompany.breakthrough.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteBoxTests {

    @Test
    public void testTurnSwitching() {
        Game game = new Game(8);
        game.setSelectedDoll(game.getBoard().getPiece(0, 0));
        game.makeMove(1, 0, 2, 0);

        assertSame(game.getPlayer2(), game.getCurrentPlayer());
        game.setSelectedDoll(game.getBoard().getPiece(7, 7));
        game.makeMove(6, 0, 5, 0);

        assertSame(game.getPlayer1(), game.getCurrentPlayer());
    }

    @Test

    public void GameInitializationTest() {
        Game game = new Game(8);
        assertEquals(16, game.getPlayer1().getPieces().size());
        assertEquals(16, game.getPlayer2().getPieces().size());
        assertNotNull(game.getBoard());
    }

    @Test
    public void testMoveValidation() {
        Game game = new Game(8);
        Player player1 = game.getPlayer1();

        Doll doll = game.getBoard().getPiece(0, 0);

        assertTrue(game.isValidMove(1, 0, 2, 0));

        assertFalse(game.isValidMove(1, 0, 0, 0));

        Doll blockingDoll = new Doll(2, 2, player1);
        game.getBoard().placePiece(blockingDoll, 2, 2);
        assertFalse(game.isValidMove(1, 1, 2, 2));

        assertFalse(game.isValidMove(7, 7, 8, 7));
    }
}
