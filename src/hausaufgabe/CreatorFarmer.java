package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Sebastian Seidel
 * Aufgabe 1.4 erstellen einer Schöpfer-Klasse welche eine Ziege in das Grid setzt, wenn alle Ziegen gestorben sind.
 */
final class CreatorFarmer extends Farmer{
//----Variablen

    Grid<Actor> gridOfCreatorFarmer;
    int goatCounter;
//----Konstruktoren

    CreatorFarmer(){
        setColor(Color.GREEN);
    }
//----Methoden

    /**
     * Bekommt die Liste mit allen Actors enthalten und prüft, ob innerhalb dieser Liste Goats enthalten sind.
     * Wenn ja dann steigt der goatCounter + 1 und der CreatorFarmer muss keine Goats erschaffen.
     * Wenn goatCounter = 0 dann erschafft der Schöpfer ein Goat neben sich
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        Location locOfCreatorFarmer = getLocation();
        gridOfCreatorFarmer = getGrid();
        goatCounter = 0;
        for (Actor a : actors) {
            if (a instanceof Goat || a instanceof GoatKid) {
                goatCounter++;
            }//if (a instanceof Goat || ...)
        }//for (Actor a : actors)
        if (goatCounter == 0) {
            putNewGoatOnFreePlace(locOfCreatorFarmer, gridOfCreatorFarmer);
        }//if (goatCounter == 0)
    }//processActors

    /**
     * bekommt eine Liste übergeben in dem die leeren Felder stehen.
     * für jedes Feld werden die If Abfragen geprüfen.
     * Wenn das Feld ok ist und der dortige Aktor gleich null ist, plaziere eine Ziege dorthin.
     * @param locOfActor Location für das neue Tier
     * @param gridOfActor Grid in dem sich das neue Tier befinden soll
     */
    void putNewGoatOnFreePlace(Location locOfActor, Grid<Actor> gridOfActor){
        //bekommt eine Liste übergeben in dem die leeren Felder stehen
        ArrayList<Location> putLocs = new ArrayList<>(); // new ArrayList ist Redundant, lässt sich aber besser lesen
        putLocs = gridOfActor.getEmptyAdjacentLocations(locOfActor);
        //für jedes Feld If Abfragen prüfen
        for(Location loc1 : putLocs){
            //Wenn das Feld ok ist
            if (gridOfActor.isValid(loc1)) {
                Actor neighbor = gridOfActor.get(loc1);
                //und der dortige Aktor gleich null ist, plaziere ein Schaf
                if (neighbor == null) {
                    Goat goat1 = new Goat();
                    goat1.putSelfInGrid(gridOfActor, loc1);
                    break;
                }//if (neighbor == null)
            }//if (gridOfActor.isValid(loc1))
        }//for(Location loc1 : putLocs)
    }//putNewGoatOnFreePlace

    /**
     * prüft das gesamte Grid, vom dem CreaterFarmer +- getGrid().getNumRows() bzw. getGrid().getNumCols(), d.h.
     * unser Feld hier ist 20x20. Das heißt egal welche Position der CreatorFarmer hat er würde mit der abzutastenden Location
     * immer über den Grid rand kommen, das fängt aber isValid(tempLoc) ab. Damit ist gewährleistet das immer nur unser Gesamtes Grid
     * als Location in Frage kommt.
     * @return leifert einer Liste mit allen Actors die auf dem gesamten Grid sind
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<Location> locOfActors = new ArrayList<>();// new ArrayList ist Redundant, lässt sich aber besser lesen
        //befüllt die locOfActors Liste mit allen Position der vorhandenen Aktoren
        locOfActors = getGrid().getOccupiedLocations();
        for (Location locActor : locOfActors ){
            if (getGrid().isValid(locActor)) {
                //hole den jeweiligen Actor von der entsprechenden Position locActor
                Actor a = getGrid().get(locActor);
                //die Ziegen werden einer Liste hinzugefügt, wodurch die Anzahl der Ziegen und Ziecklein auf dem Feld ermittelbar ist
                if (a != null && a != this) {
                    actors.add(a);
                }//if(a != null &&...)
            }//if (getGrid().isValid(locActor))
        }//for (Location locActor : locOfActors )
        return actors;
    }//getActors()

    /**
     * @return toString vom Schöpfer
     */
    @Override
    public String toString() {
        return ( " //Schöpfer" );
    }//toString()
}//class CreatorFarmer
