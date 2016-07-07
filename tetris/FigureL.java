package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>FigureL</code> reprezentuje figurę w kształcie litery L. 
 * Jej wymiary to: 3x2. Kolor figury przydzielony losowo. 
 */
public class FigureL extends JComponent{
    private int leftX, topX;
    private Element[][] elements;
    public FigureL(int leftX, int topX){
        elements = new Element[3][2];
        this.leftX = leftX;
        this.topX = topX;
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if(k == 1 && (i == 0 || i == 1))
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getHeight(), topX + i * Element.getWidth());
    }
    
    /**
     * Rysuje figurę w kształcie litery L o wymiarach 3x2 oraz przydziela jej losowy kolor. 
     * @param g obiekt Graphics służący do operacji na figurach
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(MyColors.getColor(new Random().nextInt(12)));
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if(!(k == 1 && (i == 0 || i == 1)))
                    g2.fill(elements[i][k].getElement());
        g2.setPaint(Color.BLACK);
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if(!(k == 1 && (i == 0 || i == 1)))
                    g2.draw(elements[i][k].getElement());
        
    }
    
    /**
     * Pobiera preferowany rozmiar komponentu zawierającego figurę.
     * @return preferowany rozmiar komponentu.
     */
    public Dimension getPreferredSize(){
        return new Dimension(leftX + 2 * Element.getWidth() + 1,topX + 3*Element.getHeight() + 1);
    }
}
