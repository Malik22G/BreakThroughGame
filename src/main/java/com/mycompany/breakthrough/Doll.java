package com.mycompany.breakthrough;

/**
 * Represents a doll in the Break-through game.
 */
public class Doll {

    private int x;
    private int y;
    private Player owner;

    /**
     * Constructs a doll owned by a specific player at a specified position.
     *
     * @param xPos the initial x-coordinate of the doll
     * @param yPos the initial y-coordinate of the doll
     * @param o the owner of the doll
     */
    public Doll(int xPos, int yPos, Player o) {
        x = xPos;
        y = yPos;
        owner = o;
    }

    /**
     * Moves the doll to a new position.
     *
     * @param xPos the new x-coordinate of the doll
     * @param yPos the new y-coordinate of the doll
     */
    public void move(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    /**
     * Gets the x-coordinate of the doll's position.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the doll's position.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the owner of the doll.
     *
     * @return the owner (a Player object)
     */
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player p) {
        this.owner = p;
    }

}
