package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

import java.util.ArrayList;

/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Aufgabe 1.1 Animal hat die Bewegung wie ein Bug
 * Aufgabe 1.7 Herdentrieb
 */
class Animal extends Actor {
//----Variablen

    int age;
    int animalListSize;
//----Konstruktor

    Animal(int newAge){
        super();
        this.age = newAge;
    }
    Animal(){
        super();
        age = 0;
    }
//----Methoden
//------Getter und Setter

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return Alter von der Ziege
     */
    int getAge(){
        return this.age;
    }//getAge()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return gitb den Wert der größe der AnimalList als Integer zurück
     */
    int getAnimalListSize(){
        return animalListSize;
    }//getAnimalListSize()

//------Andere
    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * gibt an wie sich das Animal zu verhalten hat
     * Wenn die AnimalList leer ist, ist der Ausdruck (!(animalList.isEmpty())) = false und das Bewegungsverhalten
     * ist wie ein normaler Bug
     * Wenn die AnimalList nicht leer ist, ist der Ausdruck (!(animalList.isEmpty())) = true und
     * der Herdentrieb ist ausschlaggebend für das Bewegungsverhalten
     */
    public void act() {
        ArrayList<Actor> animalList = new ArrayList<>(); // new ArrayList ist Redundant, lässt sich aber besser lesen
        animalList = getActors();
        processActors(animalList);
        animalListSize = animalList.size();
        if(!(animalList.isEmpty())){
            this.setDirection(animalList.get(0).getDirection());
        }
        if (canMove()) {
            move();
        }
        else {
            turn();
        }
        age++;
    }//act()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * sammelt die ganzen Actors um die Location von diesem Animal ein
     * @return Array Liste mit die vorhandenen Actors
     */
    ArrayList<Actor> getActors() {
        return getGrid().getNeighbors(getLocation());
    }//getActors()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @param actors ist die Liste, welche mit getActors() erstellt wurde.
     * Diese Methode entfernt Actors aus der Liste, welche keine Ziegen oder Zicklein sind.
     * removeIf() Methode von ArrayList wird benutzt um alle Elemente a der Liste actors zu löschen wenn der Prädicat-Filter = true ist.
     */
    void processActors(ArrayList<Actor> actors) {
        actors.removeIf(a -> !(a instanceof Animal));
    }//processActors(...)

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * turn() Methode wie beim Bug
     */
    void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }//turn()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * move() Methode wie beim Bug
     */
    void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        }
        else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower();
        flower.putSelfInGrid(gr, loc);
    }//move()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * CanMove() Methode wie beim Bug
     */
    boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null);
    }//canMove()

    /**
     * @author Sebastian Seidel
     * @author Philipp Moll
     * @return toString von der Elternklasse und vom Animal mit seinem Alter
     */
    @Override
    public String toString() {
        return ( super.toString() + " //Tier: /Lebenszyklus: " + getAge() );
    }//toString()
}//class Animal()

