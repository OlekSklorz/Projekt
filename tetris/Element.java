package tetris;

import java.awt.geom.Rectangle2D;
/**
 * Obiekt <code>Element</code> reprezentuje jeden mały kwadracik. 
 * Jeden taki kwadracik symbolizuje część całej figury. Każda figura jest złożona z kilku kwadracików.
 */
public class Element{
    private int leftX, topX;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    public Element(int leftX, int topX){
        this.leftX = leftX;
        this.topX = topX;
    }
    /**
     * Tworzy kwadracik z podanych współrzędnych x i y oraz z podanej szerokości i wysokości.
     * Służy on do budowy innych figur.
     * @return mały kwadracik
     */
    public Rectangle2D getElement(){
        return new Rectangle2D.Double(leftX, topX, WIDTH, HEIGHT);
    }
    /**
     * Pobiera szerokość kwadracika.
     * @return szerokość kwadracika
     */
    public static int getWidth(){
        return WIDTH;
    }
    /**
     * Pobiera wysokość kwadracika.
     * @return wysokość kwadracika.
     */
    public static int getHeight(){
        return HEIGHT;
    }
    
    /**
     * Ustawia współrzędne małego kwadracika. 
     * @param x położenie w poziomie kwadracika. 
     * @param y położenie w pionie kwadracika.
     */
    public void setLeftTop(int x, int y){
        leftX += x;
        topX += y;
    }
    
    public int getTopX(){
        return topX;
    }
    
    public int getLeftX(){
        return leftX;
    }
}
