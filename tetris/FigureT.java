package tetris;

import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>FigureT</code> reprezentuje figurę w kształcie litery T.
 * Jej wymiary to: 3x2. 
 */
public class FigureT extends Figure{
    public FigureT(int leftX, int topX){
        super(leftX, topX, 3, 2);
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if((i == 0 || i == 2) && k == 1)
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getWidth(), topX + i * Element.getHeight());
    }
}

