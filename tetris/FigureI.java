package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>FigureI</code> reprezentuje figurę w kształcie litery I zbudowanej z małych kwadracików.
 * Jej wymiary to: 4x1. Kolor figury przydzielany losowo. 
 */
public class FigureI extends JComponent{
    private int leftX, topX;
    private Element[][] elements;
    public FigureI(int leftX, int topX){
        elements = new Element[4][1];
        this.leftX = leftX;
        this.topX = topX;
        for(int i = 0; i < 4; i++)
            elements[i][0] = new Element(leftX, topX + Element.getHeight() * i);
    }
    
    /**
     * Rysuje figurę w kształcie litery I o wymiarach 4x1 oraz przydziela jej losowy kolor. 
     * @param g obiekt Graphics służący do operacji na figurach
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(MyColors.getColor(new Random().nextInt(12)));
        for(int i = 0; i < 4; i++)
            g2.fill(elements[i][0].getElement());
        g2.setPaint(Color.BLACK);
        for(int i = 0; i < 4; i++)
            g2.draw(elements[i][0].getElement());
    }
    
    /**
     * Pobiera preferowany rozmiar komponentu zawierającego figurę.
     * @return preferowany rozmiar komponentu.
     */
    public Dimension getPreferredSize(){
        return new Dimension(leftX + Element.getWidth() + 1,leftX + 4*Element.getHeight() + 1);
    }
}
