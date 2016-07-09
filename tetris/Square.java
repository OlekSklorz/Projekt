package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>Square</code> reprezentuje duży kwadrat złożony z mniejszych kwadracików.
 * Jego wymiary: 2x2. 
 */
public class Square{
    private int leftX, topX;
    private Element[][] elements;
    public Square(int leftX, int topX){
        elements = new Element[2][2];
        this.leftX = leftX;
        this.topX = topX;
        elements[0][0] = new Element(leftX, topX);
        elements[1][0] = new Element(leftX, topX + Element.getHeight());
        elements[0][1] = new Element(leftX + Element.getWidth(), topX);
        elements[1][1] = new Element(leftX + Element.getWidth(), topX + Element.getHeight());
    }
    
    /**
     * Zmiania pozycje kwadratu poprzez zmienianie pozycji każdego kwadracika. 
     * @param x zmiana pozycji w poziomie. 
     * @param y zmiana pozycji w pionie. 
     */
    public void move(int x, int y){
        elements[0][0].setLeftTop(x, y); 
        elements[1][0].setLeftTop(x, y); 
        elements[0][1].setLeftTop(x, y);
        elements[1][1].setLeftTop(x, y);
    }
    
    /**
     * Pobiera położenie kwadratu w poziomie. 
     * @return położenie w poziomie. 
     */
    public int getLeftX(){
        return leftX;
    }
    
    /**
     * Pobiera położenie kwadratu w pionie. 
     * @return położenie w pionie. 
     */
    public int getTopX(){
        return topX;
    }
    
    /**
     * Pobiera wszystkie elementy (kwadraciki) z których złożony jest kwadrat. 
     * @return tablica składowych kwadratu. 
     */
    public Element[][] getElements(){
        return elements;
    }
}

