package tetris;
/**
 * Obiekt <code>FigureZ</code> reprezentuje figurę w kształcie litery Z.
 * Jej wymiary to: 3x2. 
 */
public class FigureZ extends Figure{
    public FigureZ(int leftX, int topX){
        super(leftX, topX, 3, 2);
        for(int i = 0; i < 3; i++)
            for(int k = 0; k < 2; k++)
                if(i == 0 && k == 1 || i == 2 && k == 0)
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getWidth(), topX + i * Element.getHeight());
    }
}
