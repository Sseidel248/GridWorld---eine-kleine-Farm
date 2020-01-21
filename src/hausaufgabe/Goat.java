package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Sebastian Seidel
 * Aufgabe 1.2 Ziege implementiert
 * Aufgabe 1.1 Ziege kann klettern
 */
final class Goat extends Animal {
//----Variablen

    private double zufall;
    private Boolean milkFromGoat;
    private int timeStepX;
    private LinkedList<Location> farAwayLocs;
//----Konstruktor

    Goat(int newAge, Boolean milkFromGoat){
        super(newAge);
        zufall = Math.random();
        this.milkFromGoat = milkFromGoat;
        setColor(Color.lightGray);
    }
    Goat(){
        this(0, false);
    }
//----Methoden
//------Getter und Setter

    /**
     * timeStepX auf 0
     */
    void setTimeStepX(){
        this.timeStepX = 0;
    }//setTimeStepX(...)

    /**
     * @return timeStepX
     */
    int getTimeStepX(){
        return this.timeStepX;
    }//getTimeStepX()

    /**
     * Setzen von false, da sie gerade vom GoatMilker gemolken wurde
     */
    void setMilkFromGoat(){
        this.milkFromGoat = false;
    }//setMilkFromGoat(...)

    /**
     * je nachdem wie viele Zeitschritte gemacht wurden, wurde der Boolean milkFromGoat angepasst
     * @return milkFromGoat true wenn Ziege Milch beinhaltet or false wenn die Ziege gemolken wurde
     */
    Boolean getMilkFromGoat(){
        return this.milkFromGoat;
    }//getMilkFromGoat()
//------Andere

    /**
     * Ziege bewegt sich wie ein Animal
     * mit 16,666%iger Wahrscheinlichkeit wird eine weiteres GoatKid auf eine freie Fläche gesetzt
     * mit 20%iger Wahrscheinlich und wenn die Ziege älter als 15 Zeitschritte ist, stirbt Goat und hinterlässt eine Blume
     * Ziege klettert über Steine, wenn sich diese in ihrer Blickrichtung befindet Im direkten Feld vor der Ziege)
     * JEDOCH geht die Ziege lieber zu anderen ihrer Art statt zu springen
     * nach 2 Zeitschritten (egal ob frisch gewordenes Goat oder gerade gemolken) ist das Goat bereit zu erneuten zum melken
     */
    @Override
    public void act(){
        Location localActor = getLocation();
        Grid<Actor> gridOfActor = getGrid();
        zufall = Math.random();
        if (zufall <= (0.16667)) {
            putNewAnimalOnFreePlace(localActor, gridOfActor);
        }
        //Wahrscheinlichkeit 1/5 und Lebenszyklus > 15, stirbt das Schaf und wird zur Blume
        else if ((age > 15) && (zufall <= 0.2)){
            goatIsDied(localActor, gridOfActor);
            //Wenn das Schaf gestorben ist, gehe aus der Act() Methode
            return;
        }
        //Wenn keine Ziegen oder Zicklein in der Nähe sind dann spring über Steine bzw. bewege dich normal
        else if (getAnimalListSize() == 0) {
            if (canJump()) {
                jump();
            } else {
                super.act();
            }
        }
        //wenn Ziegen und Zicklein in der Nähe sind dann folgen sie dem Herdentrieb
        else {
            super.act();
        }
        //Wenn die Ziege 2 Zeitschritte nach dem melken noch auf dem grid ist, kann sie wieder gemolken werden
        if (this.getTimeStepX() >= 2){
            milkFromGoat = true;
        }
        //die Milch hat 2 Zeitschritte Zeit nach produziert zu werden
        else{
            milkFromGoat = false;
            timeStepX++;
        }
    }//act()

    /**
     * Wahrscheinlichkeit 1/5 und Lebenszyklus > 15, stirbt die Ziege und wird zur Blume
     * @param locFromActor Location von der Ziege die Stirbt
     * @param gridFromActor Grid in der sich die Ziege befindet
     */
    private void goatIsDied(Location locFromActor, Grid<Actor> gridFromActor){
        this.removeSelfFromGrid();
        Flower flower = new Flower(Color.green);
        flower.putSelfInGrid(gridFromActor, locFromActor);
    }//GoatIsDied(...)

    /**
     * Ziege kann klettern und springt bei Rock als Hindernis auf frei liegendes Feld dahinter
     */
    private void jump(){
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        //Nehme das letzt hinzugefügte Element aus der Location Liste und bewege dich bis dahin
        Location farAway = farAwayLocs.getLast();
        if (gr.isValid(farAway)) {
            moveTo(farAway);
        }
    }//jump()

    /**
     * prüft ob die Ziege klettern und auf das frei liegendes Feld dahinter springen kann
     */
    private boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor nextField = gr.get(next);
        //prüft ob der Actor auf den nächsten Feld ein Rock ist
        if (nextField instanceof Rock) {
            Location farAway = next.getAdjacentLocation(getDirection());
            //nehme die größte Zahl von Beiden um alle Felder erreichen zu können
            int countForLoop = Math.max(gr.getNumCols(), gr.getNumRows());
            //erzeugen einer Location LinkedList
            farAwayLocs = new LinkedList<>();
            //lenkt die Position des Actor nextField wenn es ein Rock ist in die Liste
            farAwayLocs.add(farAway);
            for ( int i = 0; i<= countForLoop; i++) {
                //liegt die jeweilige Location im Grid
                if (!gr.isValid(farAwayLocs.get(i))) {
                    return false;
                }//if (!gr.isValid...)
                //wenn ja, hole den Actor auf der jeweiligen Location
                nextField = gr.get(farAwayLocs.get(i));
                //ist der Actor auf der jeweiligen Location leer
                //wenn ja, gebe ein true zurück das die Ziege bis hierhin springen kann
                if (nextField == null) {
                    return true;
                }//if (nextField == null)
                //wenn nein, dann nehme die nächste Location von der jetzigen Location der LinkedList und füge sie hinzu
                else {
                    farAwayLocs.add(farAwayLocs.get(i).getAdjacentLocation(getDirection()));
                }//else
            }//for ( int i = 0; i<= countForLoop; i++)
        }//if (nextField instanceof Rock)
        return false;
    }//canJump()

    /**
     * bekommt eine Liste übergeben in dem die leeren Felder stehen.
     * für jedes Feld werden die If Abfragen geprüft.
     * Wenn das Feld ok ist und der dortige Aktor gleich null ist, plaziere ein Zicklein
     * @param locOfActor Location für das neue Tier
     * @param gridOfActor Grid in dem sich das neue Tier befinden soll
     */
    void putNewAnimalOnFreePlace(Location locOfActor, Grid<Actor> gridOfActor){
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
                    GoatKid newGoat = new GoatKid();
                    newGoat.putSelfInGrid(gridOfActor, loc1);
                    break;
                }//if (neighbor == null)
            }//if (gridOfActor.isValid(loc1))
        }//for(Location loc1 : putLocs)
    }//putNewAnimalOnFreePlace

    /**
     * @return toString von der Ziege inkl. Milch(ja Nein), Zeitschritte der Ziege und Lebenzyklus
     */
    @Override
    public String toString() {
        return ( " //Ziege: /Milch: " + this.getMilkFromGoat() + " /Zeitschritte: " + this.getTimeStepX() + " /Lebenszyklus: " + super.getAge());
    }//toString()
}//Class Goat