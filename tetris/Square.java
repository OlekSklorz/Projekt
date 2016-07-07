package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>Square</code> reprezentuje duży kwadrat złożony z mniejszych kwadracików.
 * Jego wymiary: 2x2. Kolor kwadratu przydzielany losowo.
 */
public class Square extends JComponent{
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
     * Rysuje duży kwadrat o wymiarach 2x2 i wypełnia go losowym kolorem. 
     * @param g obiekt Graphics służący do operacji na figurach
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(MyColors.getColor(new Random().nextInt(12)));
        for(int i = 0; i < 2; i++)
            for(int k = 0; k < 2; k++)
                g2.fill(elements[i][k].getElement());
        g2.setPaint(Color.BLACK);
        for(int i = 0; i < 2; i++)
            for(int k = 0; k < 2; k++)
                g2.draw(elements[i][k].getElement());
    }
    
    /**
     * Pobiera preferowany rozmiar komponentu zawierającego kwadrat.
     * @return preferowany rozmiar komponentu.
     */
    public Dimension getPreferredSize(){
        return new Dimension(leftX + 2*Element.getWidth() + 1,topX + 2*Element.getHeight() + 1);
    }
}

