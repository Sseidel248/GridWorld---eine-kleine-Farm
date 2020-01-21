package hausaufgabe;

import gridworld.framework.actor.Actor;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Aufgabe 1.5 Milchbauer implementiert
 */
final class GoatMilker extends Farmer{
//----Variablen

    private static MilkStorage milkStorage;
//----Konstruktoren

    GoatMilker(MilkStorage milkStorage1){
        super();
        milkStorage = milkStorage1;
        setColor(Color.gray);
    }
//----Methoden

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * Bekommt alle actors in einer Liste, die um ihn herum sind, übergeben.
     * Entfernt die Blumen vom grid wie es der Farmer macht.
     * Wenn der Actor in der Liste eine Ziege ist und und er Milch produziert hat, dann wird die Ziege gemolken
     * @param actors bekommt alle Aktoren geliefert und nur bei den Ziegen wird Milch geholt
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Flower){
                a.removeSelfFromGrid();
            }//if (a instanceof Flower)
            if (a instanceof Goat){
                if (((Goat) a).getMilkFromGoat()) {
                    milkToMilkStorage(milkStorage);
                    //Milch von Goat auf false
                    ((Goat) a).setMilkFromGoat();
                    //Zeitschritte auf 0
                    ((Goat) a).setTimeStepX();
                }//if (((Goat) a).getMilkFromGoat())
            }//if (a instanceof Goat)
        }//for (Actor a : actors)
    }//processActors(...)

    /**
     * @author Sebastian Seidel
     * @author Philipp Mol
     * sammelt die Milch ein und übergibt sie dem mitgelieferten Milkstorage
     * @param milkStorage wird mit gesammelter Milch befüllt
     */
    public void milkToMilkStorage(MilkStorage milkStorage){
        milkStorage.setCountMilk();
    }//MilkToMilkStorage(...)

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return toString vom Milchbauer
     */
    @Override
    public String toString() {
        return ( " //Milchbauer" );
    }//toString()
}//class GoatMilker
