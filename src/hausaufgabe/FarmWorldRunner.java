package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * @author Sebastian Seidel
 */

public class FarmWorldRunner implements FarmWorldRunnerInterface {
//----Variablen

    private ActorWorld farmWorld;
    private Grid<Actor> gr;
    private MilkStorage milkStorage1;
//----Konstruktoren

//----Methoden

//--------------------------------------------------------------------------------------------------------------------//
    public static void main(String[] args){
        //initialisierung der Grid World
        FarmWorldRunner farmWorldRunner = new FarmWorldRunner();
        farmWorldRunner.createNewWorldWithGridSize(20,20);

        //Platzierung der Actors
        //Rock
        Rock rock1 = farmWorldRunner.addRockIfFieldEmpty(3,4);
        Rock rock2 = farmWorldRunner.addRockIfFieldEmpty(2,4);
        Rock rock3 = farmWorldRunner.addRockIfFieldEmpty(1,4);
        Rock rock9 = farmWorldRunner.addRockIfFieldEmpty(5,4);
        Rock rock4 = farmWorldRunner.addRockIfFieldEmpty(6,4);
        Rock rock5 = farmWorldRunner.addRockIfFieldEmpty(7,4);
        Rock rock6 = farmWorldRunner.addRockIfFieldEmpty(8,4);
        Rock rock7 = farmWorldRunner.addRockIfFieldEmpty(9,4);
        Rock rock8 = farmWorldRunner.addRockIfFieldEmpty(10,4);
        Rock rock10 = farmWorldRunner.addRockIfFieldEmpty(0,6);
        Rock rock11 = farmWorldRunner.addRockIfFieldEmpty(1,6);

        //Tiere
        Goat goat1 = farmWorldRunner.addGoatIfFieldEmpty(14,4);
        Goat goat2 = farmWorldRunner.addGoatIfFieldEmpty(3,5);
        Goat goat3 = farmWorldRunner.addGoatIfFieldEmpty(4,5);
        Goat goat4 = farmWorldRunner.addGoatIfFieldEmpty(4,4);

        //Milchbehälter
        MilkStorage milkStorage1 = farmWorldRunner.addMilkStorageIfFieldEmpty(0,0);

        //Farmer
        Farmer farmer1 = farmWorldRunner.addFarmerIfFieldEmpty(8,8);
        Farmer farmer2 = farmWorldRunner.addFarmerIfFieldEmpty(9,8);
        CreatorFarmer cFarmer1 = farmWorldRunner.addCreatorFarmerIfFieldEmpty(9,0);
        GoatMilker gFarmer1 = farmWorldRunner.addGoatMilkerIfFieldEmpty(1,0);

        //ToString anzeigen von Stelle (x Zeile /y Spalte)

        //simulation um n weiterlaufen lassen
        farmWorldRunner.runNSteps(10_000);
    }
//--------------------------------------------------------------------------------------------------------------------//
    /**
     * @param x for zeile
     * @param y for Spalte
     * @return a farmworld for actors
     */
    @Override
    public ActorWorld createNewWorldWithGridSize(int x, int y) {
        if ((x > 0) && (y > 0)) {
            farmWorld = new ActorWorld(new BoundedGrid<>(x, y));
            gr = farmWorld.getGrid();
            return new ActorWorld(new BoundedGrid<>(x, y));
        }
        else{
            return null;
        }
    }//createNewWorldWithGridSize(...)

    /**
     * prüft ob die Location ok ist
     * @param x ist Zeile
     * @param y ist Spalte
     * @return true wenn Location leer ist und im Grid liegt
     */
    Boolean IsLocationOk(int x, int y){
        Location locTest = new Location(x,y);
        if (!gr.isValid(locTest)){
            return false;
        }//if (!gr.isValid(locTest))
        Actor actor = gr.get(locTest);
        return actor == null;
    }//IsLocationOk(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert ein Animal ins Grid
     */
    @Override
    public Animal addAnimalIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            Animal animal1 = new Animal();
            animal1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
            return animal1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addAnimalIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert eine Goat ins Grid
     */
    @Override
    public Goat addGoatIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            Goat goat1 = new Goat();
            goat1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
            return goat1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addGoatIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert ein GoatKid ins Grid
     */
    @Override
    public GoatKid addGoatKidIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            GoatKid goatKid1 = new GoatKid();
            goatKid1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
            return goatKid1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addGoatKidIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert eine Farmer ins Grid
     */
    @Override
    public Farmer addFarmerIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            Farmer farmer1 = new Farmer();
            farmer1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
            return farmer1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addFarmerIfFieldEmpty

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert ein CreatorFarmer ins Grid
     */
    @Override
    public CreatorFarmer addCreatorFarmerIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            CreatorFarmer creatorFarmer1 = new CreatorFarmer();
            creatorFarmer1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
            return creatorFarmer1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addCreatorFarmerIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert eine MilkStorage ins Grid wenn es noch kein anderes gibt
     */
    @Override
    public MilkStorage addMilkStorageIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            milkStorage1 = new MilkStorage();
            if (milkStorage1.getCountMilkStorage() == 1){
                milkStorage1.putSelfInGrid(farmWorld.getGrid(),new Location(x,y));
                return milkStorage1;
            }//if (countMilkStorage == 0)
            else {
                return null;
            }//else
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addMilkStorageIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert GoatMilker ins Grid
     */
    @Override
    public GoatMilker addGoatMilkerIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            GoatMilker goatMilker1 = new GoatMilker(milkStorage1);
            goatMilker1.putSelfInGrid(farmWorld.getGrid(), new Location (x,y));
            return goatMilker1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addGoatMilkerIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null und platziert eine Flower ins Grid
     */
    @Override
    public Flower addFlowerIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            Flower flower1 = new Flower();
            flower1.putSelfInGrid(farmWorld.getGrid(), new Location(x,y));
            return flower1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addFlowerIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return null platziert ein Rock ins grid
     */
    @Override
    public Rock addRockIfFieldEmpty(int x, int y) {
        if (IsLocationOk(x,y)){
            Rock rock1 = new Rock();
            rock1.putSelfInGrid(farmWorld.getGrid(), new Location(x,y));
            return rock1;
        }//if (IsLocationOk(x,y))
        else {
            return null;
        }//else
    }//addRockIfFieldEmpty(...)

    /**
     * @param x for Row
     * @param y for Column
     * @return toString-Methode vom Actor auf dem Feld (x/y)
     */
    @Override
    public String getToStringOfActorInField(int x, int y) {
        if (!(IsLocationOk(x,y))){
            return farmWorld.getGrid().get(new Location(x,y)).toString();
        }//if (!(IsLocationOk(x,y)))
        else {
            return ( "no Actor at: " +x+ " & " +y );
        }//else
    }//getToStringOfActorInField

    /**
     * lässt das Framework sprunghaft bis n Steps laufen (ohne Wartezeit)
     * Danach kann entweder manuell der Step oder Run Button gedrückt werden und die Simulation läuft weiter
     */
    @Override
    public void runNSteps(int n) {
        int counter = 0;
        while(counter <= n) {
            farmWorld.step();
            counter++;
        }
        farmWorld.show();
    }//runNSteps(...)
}//class FarmWorldRunner
