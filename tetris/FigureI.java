package tetris;
/**
 * Obiekt <code>FigureI</code> reprezentuje figurę w kształcie litery I zbudowanej z małych kwadracików.
 * Jej wymiary to: 4x1.
 */
public class FigureI extends Figure{
    private static final int yElements = 4, xElements = 1;
    public FigureI(int leftX, int topX){
        super(leftX, topX,yElements,xElements);
        for(int i = 0; i < yElements; i++)
            elements[i][0] = new Element(leftX, topX + Element.getHeight() * i);
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
