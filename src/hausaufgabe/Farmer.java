package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Critter;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Aufgabe 1.3 Blumenwirt der die Blumen einsammelt implementiert
 */
class Farmer extends Critter {
//----Variablen

//----Konstruktoren

    Farmer(){
        setColor(Color.yellow);
    }
//----Methoden

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * Der Farmer räumt alle Blumen in seinem Umfeld weg
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Flower){
                a.removeSelfFromGrid();
            }//if (a instanceof Flower)
        }//for (Actor a : actors)
    }//processActors

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return toString vom Farmer
     */
    @Override
    public String toString() {
        return ( " //Blumenwirt" );
    }//toString()
}//class Farmer
