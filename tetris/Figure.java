package tetris;

import java.awt.Color;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Obiekt <code>Figure</code> reprezentuje dowolną figurę o podanych wymiarach.
 * Jej kolor jest dobierany losowo. 
 */
public abstract class Figure extends JComponent{
    protected int leftX, topX;
    protected Element[][] elements;
    protected Color color;
    private int x, y;
    boolean stop = false;
    public Figure(int leftX, int topX, int x, int y){
        this.leftX = leftX;
        this.topX = topX;
        this.x = x;
        this.y = y;
        this.elements = new Element[x][y];
        color = MyColors.getColor(new Random().nextInt(12));
    }
    
    /**
     * Przemieszcza figurę poziomo i pionowo. 
     * @param x przemieszczanie poziomo. 
     * @param y przemieszczenie pionowo. 
     */
    public void move(int x, int y){
        for(int i = 0; i < elements.length; i++)
            for(int k = 0; k < elements[i].length; k++){
                if(elements[i][k] != null)
                    elements[i][k].setLeftTop(x, y);
            }
    }
    
    /**
     * Pobiera położenie figury w poziomie. 
     * @return położenie w poziome. 
     */
    public int getLeftX(){
        return leftX;
    }
    
    /**
     * Pobiera położenie figury w pionie. 
     * @return położenie w pionie. 
     */
    public int getTopX(){
        return topX;
    }
    
    /**
     * Pobiera wszystkie elementy (kwadraciki) z których złożona jest figura. 
     * @return tablica składowych figury. 
     */
    public Element[][] getElements(){
        return elements;
    }
    
    /**
     * Pobiera kolor figury.
     * @return kolor figury.
     */
    public Color getColor(){
        return color;
    }
    
    /**
     * Pobiera aktualne położenie figury w pionie. 
     * @return aktualne położenie figury w pionie. 
     */
    public int getActualTopX(){
        return elements[0][0].getTopX();
    }
    
    /**
     * Pobiera wysokość figury. 
     * @return wysokość figury. 
     */
    public int getHeightFigure(){
        return x * Element.getHeight();
    }
    
    /**
     * Pobiera szerokość figury. 
     * @return szerokość figury. 
     */
    public int getWidthFigure(){
        return y * Element.getWidth();
    }
    
    /**
     * Usuwa wiersz elementów z danej figury. 
     * @param oldX numer wiersza do usunięcia. 
     */
    public void deleteElement(int oldX){
        for(int i = 0; i < elements[oldX].length; i++){
            if(elements[oldX][i] != null)
                elements[oldX][i] = null;
        }
    }
}
