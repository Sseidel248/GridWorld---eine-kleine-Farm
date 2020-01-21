package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;
import gridworld.framework.world.World;

import java.util.ArrayList;

/**
 * @author Sebastian Seidel
 * Kopie aus gridworld Package
 */
class ActorWorld extends World<Actor> {
//----Variablen

    private static final String DEFAULT_MESSAGE = "Click on a grid location to construct or manipulate an actor.";
//----Konstruktoren

    /**
     * Constructs an actor world with a given grid.
     * @param grid the grid for this world.
     */
    ActorWorld(Grid<Actor> grid) {
        super(grid);
    }
    /**
     * Constructs an actor world with a default grid.
     */
    ActorWorld() {
    }
//----Methoden

    /**
     * zeigt DEFAULT_MESSAGE in der gelben MessageBox an
     */
    public void show() {
        if (getMessage() == null)
            setMessage( DEFAULT_MESSAGE );
        super.show();
    }//show()

    /**
     * führt pro Aufruf einmal den alles aus, als wäre der Step Button gedrückt worden
     */
    @Override
    public void step() {
        Grid<Actor> gr = getGrid();
        ArrayList<Actor> actors = new ArrayList<>();
        for (Location loc : gr.getOccupiedLocations()){
            actors.add(gr.get(loc));
        }//for (Location loc : ...)
        for (Actor a : actors) {
            // only act if another actor hasn't removed a
            if (a.getGrid() == gr) {
                a.act();
            }
        }//for (Actor a : actors)
    }//step()

    /**
     * Adds an actor to this world at a given location.
     * @param loc the location at which to add the actor
     * @param occupant the actor to add
     */
    public void add(Location loc, Actor occupant) {
        occupant.putSelfInGrid(getGrid(), loc);
    }//add(...)

    /**
     * Adds an occupant at a random empty location.
     * @param occupant the occupant to add
     */
    void add(Actor occupant) {
        Location loc = getRandomEmptyLocation();
        if (loc != null)
            add(loc, occupant);
    }//add(...)

    /**
     * Removes an actor from this world.
     * @param loc the location from which to remove an actor
     * @return the removed actor, or null if there was no actor at the given
     * location.
     */
    public Actor remove(Location loc) {
        Actor occupant = getGrid().get(loc);
        if (occupant == null)
            return null;
        occupant.removeSelfFromGrid();
        return occupant;
    }//remove(...)
}//class ActorWorld
