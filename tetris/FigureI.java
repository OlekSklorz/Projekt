package tetris;
/**
 * Obiekt <code>FigureI</code> reprezentuje figurę w kształcie litery I zbudowanej z małych kwadracików.
 * Jej wymiary to: 4x1.
 */
public class FigureI extends Figure{
    public FigureI(int leftX, int topX){
        super(leftX, topX,4,1);
        for(int i = 0; i < 4; i++)
            elements[i][0] = new Element(leftX, topX + Element.getHeight() * i);
    }
}
