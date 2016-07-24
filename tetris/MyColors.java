package tetris;

import static java.awt.Color.*;

/**
 * Obiekt <code>MyColors</code> reprezentuje podstawowy kolor.
 * Klasa ta zawiera 12 podstawowych kolorów z klasy Color.
 */
public class MyColors {
    public static final java.awt.Color[] colors = {BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW};
    
    /**
     * Pobiera jeden z 12 kolorów. 
     * @param i indeks koloru do pobrania
     * @return kolor
     */
    public static java.awt.Color getColor(int i){
        return colors[i];
    }
}

