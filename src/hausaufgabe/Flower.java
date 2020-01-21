package hausaufgabe;

import gridworld.framework.actor.Actor;

import java.awt.*;

/**
 * @author Sebastian Seidel
 * Blumen Klasse implementiert
 * Kopie aus gridworld Package
 */
class Flower extends Actor {
//----Variablen

    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final double DARKENING_FACTOR = 0.05;
//----Konstruktoren

    // lose 5% of color value in each step
    /**
     * Constructs a flower of a given color.
     * @param initialColor the initial color of this flower
     */
    Flower(Color initialColor) {
        setColor(initialColor);
    }

    /**
     * Constructs a pink flower.
     */
    Flower() {
        setColor(DEFAULT_COLOR);
    }
//----Methoden

    /**
     * Causes the color of this flower to darken.
     */
    public void act() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
    }//act()

    /**
     * @return toString von der Blume
     */
    @Override
    public String toString() {
        return ( " //Blume" );
    }//toString()
}//class Flower
