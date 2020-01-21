package hausaufgabe;

import gridworld.framework.actor.Actor;

import java.awt.*;
/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Stein implemenmtiert
 * Kopie aus gridworld Package
 */
class Rock extends Actor {
//----Variablen

    /**
     * A <code>Rock</code> is an actor that does nothing. It is commonly used to
     * block other actors from moving. <br />
     * The API of this class is testable on the AP CS A and AB exams.
     */
    private static final Color DEFAULT_COLOR = Color.GRAY;
//----Konstruktoren

    /**
     * Constructs a black rock.
     */
    Rock() {
        setColor(DEFAULT_COLOR);
    }

    /**
     * Constructs a rock of a given color.
     *
     * @param rockColor the color of this rock
     */
    Rock(Color rockColor) {
        setColor(rockColor);
    }
//----Methoden

    /**
     * Overrides the <code>act</code> method in the <code>Actor</code> class
     * to do nothing.
     */
    public void act() {
    }//act()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return toString vom Stein
     */
    @Override
    public String toString() {
        return ( " //Stein" );
    }//toString()
}//class Rock

