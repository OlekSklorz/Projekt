package tetris;

/**
 * Obiekt <code>ReversedFigureL</code> reprezentuje odwróconą figurę w kształcie litery L. 
 * Jej wymiary to: 3x2. 
 */
public class ReversedFigureL extends Figure{
    private static final int yElements = 3, xElements = 2;
    public ReversedFigureL(int leftX, int topX){
        super(leftX, topX, yElements, xElements);
        for(int i = 0; i < yElements; i++)
            for(int k = 0; k < xElements; k++)
                if(k == 0 && (i == 0 || i == 1))
                    elements[i][k] = null;
                else
                    elements[i][k] = new Element(leftX + k * Element.getHeight(), topX + i * Element.getWidth());
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
