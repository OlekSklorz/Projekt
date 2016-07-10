package tetris;
/**
 * Obiekt <code>Square</code> reprezentuje duży kwadrat złożony z mniejszych kwadracików.
 * Jego wymiary: 2x2. 
 */
public class Square extends Figure{
    public Square(int leftX, int topX){
        super(leftX, topX, 2, 2);
        elements[0][0] = new Element(leftX, topX);
        elements[1][0] = new Element(leftX, topX + Element.getHeight());
        elements[0][1] = new Element(leftX + Element.getWidth(), topX);
        elements[1][1] = new Element(leftX + Element.getWidth(), topX + Element.getHeight());
    }
}
