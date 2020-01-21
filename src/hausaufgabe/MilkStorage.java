package hausaufgabe;

import gridworld.framework.actor.Actor;

import java.awt.*;

/**
 * @author Sebastian Seidel
 * @author Philipp Moll
 * Aufgabe 1.5 Milchspeicher implemeniert
 */
final class MilkStorage extends Actor {
//----Variablen

    private static long countMilk = 0;
    private static int countMilkStorage = 0;
//----Konstruktoren

    MilkStorage(){
        countMilkStorage++;
        setColor(Color.lightGray);
    }
//----Methoden
//------Getter und Setter

    /**
     * @author Sebastian Seidel
     * setzt die Anzahl der Milch im Speicher + 1
     */
    void setCountMilk(){
        countMilk++;
    }//setCountMilk()

    /**
     * @author Sebastian Seidel
     * @return Anzahl der Milch im Speicher
     */
    long getCountMilk(){
        return countMilk;
    }//getCountMilk()

    /**
     * @author Sebastian Seidel
     * @return Anzahl der MilchSpeicher
     */
    int getCountMilkStorage(){
        return countMilkStorage;
    }//getCountMilkStorage()

//------Andere
    /**
     * @author Sebastian Seidel
     * setzt den Milchspeicher countMilk = 0
     */
    public void resetMilkStorage(){
        countMilk = 0;
    }//resetMilkStorage()

    /**
     * @author Sebastian Seidel
     * @return Anzahl der Milch für ToString
     */
    @Override
    public String toString(){
        //Typecast von int in String
        return (String.valueOf(getCountMilk()));
    }//toString()
}//class MilkStorage
