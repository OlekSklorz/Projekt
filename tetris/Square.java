package tetris;
/**
 * Obiekt <code>Square</code> reprezentuje duży kwadrat złożony z mniejszych kwadracików.
 * Jego wymiary: 2x2. 
 */
public class Square extends Figure{
    private static final int yElements = 2, xElements = 2;
    public Square(int leftX, int topX){
        super(leftX, topX,yElements,xElements);
        elements[0][0] = new Element(leftX, topX);
        elements[1][0] = new Element(leftX, topX + Element.getHeight());
        elements[0][1] = new Element(leftX + Element.getWidth(), topX);
        elements[1][1] = new Element(leftX + Element.getWidth(), topX + Element.getHeight());
    }
    
    /**
     * Pobiera liczbę elementów budujących figurę w pionie.
     * @return liczba elementów w pionie. 
     */
    public static int getYElements(){
        return yElements;
    }
    
    /**
     * Pobiera liczbę elementów budujących figurę w poziomie.
     * @return liczba elementów w poziomie. 
     */
    public static int getXElements(){
        return xElements;
    }
}

