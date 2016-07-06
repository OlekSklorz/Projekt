package tetris;

import java.awt.*;
import javax.swing.JComponent;

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
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.draw(elements[0][0].getElement());
        g2.draw(elements[1][0].getElement());
        g2.draw(elements[0][1].getElement());
        g2.draw(elements[1][1].getElement());
    }
    public Dimension getPreferredSize(){
        return new Dimension(leftX + 2*Element.getWidth() + 1,topX + 2*Element.getHeight() + 1);
    }
}
