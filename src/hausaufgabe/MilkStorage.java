package hausaufgabe;

import gridworld.framework.actor.Actor;

import java.awt.*;

/**
 * @author Sebastian Seidel
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
     * setzt die Anzahl der Milch im Speicher + 1
     */
    void setCountMilk(){
        countMilk++;
    }//setCountMilk()

    /**
     * @return Anzahl der Milch im Speicher
     */
    long getCountMilk(){
        return countMilk;
    }//getCountMilk()

    /**
     * @return Anzahl der MilchSpeicher
     */
    int getCountMilkStorage(){
        return countMilkStorage;
    }//getCountMilkStorage()

//------Andere
    /**
     * setzt den Milchspeicher countMilk = 0
     */
    public void resetMilkStorage(){
        countMilk = 0;
    }//resetMilkStorage()

    /**
     * @return Anzahl der Milch für ToString
     */
    @Override
    public String toString(){
        //Typecast von int in String
        return (String.valueOf(getCountMilk()));
    }//toString()
}//class MilkStorage
