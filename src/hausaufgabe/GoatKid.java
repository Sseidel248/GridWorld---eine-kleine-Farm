package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.awt.*;

/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Aufgabe 1.6 Zicklein implementiert
 */
final class GoatKid extends Animal{
//----Variablen

//----Konstruktoren

    GoatKid(){
        super(0);
        setColor(Color.CYAN);
    }
//----Methoden

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * Wenn das Zicklein ein Alter von 3 Zeitschritte erreicht wird es zur Ziege
     */
    @Override
    public void act(){
        if (this.age == 3){
            Location locOfGoat = getLocation();
            Grid<Actor> gridOfGoat = getGrid();
            this.removeSelfFromGrid();
            Goat adultGoat = new Goat(3, true);
            adultGoat.putSelfInGrid(gridOfGoat, locOfGoat);
        }//if (this.age == 3)
        else{
            super.act();
        }//else
    }//act()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return toString vom Zicklein plus dessen Lebenzyklus
     */
    @Override
    public String toString() {
        return ( " //Zicklein: /Lebenszyklus: " + super.getAge());
    }//toString()
}//class GoatKid