package com.mycompany.breakthrough;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the Break-through game.
 */
public class Player {

    private String name;
    private List<Doll> pieces;

    /**
     * Constructs a player with a specified name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.pieces = new ArrayList<>();
    }

    /**
     * Adds a doll to the player's list of pieces.
     *
     * @param piece the doll to add
     */
    public void addPiece(Doll piece) {
        pieces.add(piece);
    }

    /**
     * Clears all dolls from the player's list of pieces. This is used typically
     * for resetting the game.
     */
    public void clearPieces() {
        pieces.clear();
    }

    /**
     * Gets the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of dolls owned by the player.
     *
     * @return the list of dolls
     */
    public List<Doll> getPieces() {
        return pieces;
    }
}
