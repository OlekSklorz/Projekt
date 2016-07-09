package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>Square</code> reprezentuje duży kwadrat złożony z mniejszych kwadracików.
 * Jego wymiary: 2x2. Kolor kwadratu przydzielany losowo.
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
    
    public void move(int x, int y){
        elements[0][0].setLeftTop(x, y); 
        elements[1][0].setLeftTop(x, y); 
        elements[0][1].setLeftTop(x, y);
        elements[1][1].setLeftTop(x, y);
    }
    
    public int getLeftX(){
        return leftX;
    }
    
    public int getTopX(){
        return topX;
    }
    
    public Element[][] getElements(){
        return elements;
    }
}

