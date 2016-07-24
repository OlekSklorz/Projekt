package tetris;
/**
 * Obiekt <code>FigureT</code> reprezentuje figurę w kształcie litery T.
 * Jej wymiary to: 3x2.
 */
public class FigureT extends Figure{
    private static final int yElements = 3, xElements = 2;
    public FigureT(int leftX, int topX){
        super(leftX, topX,yElements,xElements);
        for(int i = 0; i < yElements; i++)
            for(int k = 0; k < xElements; k++)
                if((i == 0 || i == 2) && k == 1)
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getWidth(), topX + i * Element.getHeight());
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
