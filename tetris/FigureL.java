package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>FigureL</code> reprezentuje figurę w kształcie litery L. 
 * Jej wymiary to: 3x2. Kolor figury przydzielony losowo. 
 */
public class FigureL extends Figure{
    public FigureL(int leftX, int topX){
        super(leftX, topX, 3, 2);
        this.leftX = leftX;
        this.topX = topX;
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if(k == 1 && (i == 0 || i == 1))
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getHeight(), topX + i * Element.getWidth());
    }
}
