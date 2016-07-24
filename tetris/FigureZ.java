package tetris;
/**
 * Obiekt <code>FigureZ</code> reprezentuje figurę w kształcie litery Z.
 * Jej wymiary to: 3x2. 
 */
public class FigureZ extends Figure{
    private static final int yElements = 3, xElements = 2;
    private boolean start = true;
    public FigureZ(int leftX, int topX){
        super(leftX, topX,yElements,xElements);
        for(int i = 0; i < yElements; i++)
            for(int k = 0; k < xElements; k++)
                if(i == 0 && k == 1 || i == 2 && k == 0)
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
    
    /**
     * Ustawia stan początkowy figury Z. 
     * @param start stan początkowy figury Z. 
     */
    public void setStart(boolean start){
        this.start = start;
    }
    
    /**
     * Zwraca czy figura Z jest w stanie początkowym. 
     * @return czy figura Z jest w stanie początkowym. 
     */
    public boolean getStart(){
        return start;
    }
}

