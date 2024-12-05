package edu.usdb.cs.karousti.enums;

public enum Color {
    RED,
    BLUE,
    BLACK,
    WHITE,
    SILVER,
    GRAY,
    GREEN,
    YELLOW,
    ORANGE,
    PURPLE;

    // Optional: Add a method to display color names in a user-friendly format
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
