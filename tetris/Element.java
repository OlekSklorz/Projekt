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
      * Dodaje wartości do współrzędnych małego kwadracika. 
      * @param x położenie w poziomie kwadracika. 
      * @param y położenie w pionie kwadracika.
      */
     public void addLeftTop(int x, int y){
         leftX += x;
         topX += y;
     }
     
     /**
      * Pobiera położenie małego kwadracika w pionie.
      * @return położenie małego kwadracika w pionie.
      */
     public int getTopX(){
         return topX;
     }
     
     /**
      * Pobiera położenie małego kwadracika w poziomie.
      * @return położenie małego kwadracika w poziomie.
      */
     public int getLeftX(){
         return leftX;
     }
     
     /**
      * Ustawia położenie poziome małego kwadracika. 
      * @param x położenie poziome.
      */
     public void setLeft(int x){
         leftX = x;
     }
     
     /**
      * Ustawia położenie pionowe małego kwadracika. 
      * @param y położenie pionowe. 
      */
     public void setTop(int y){
         topX = y;
     }
     
     /**
      * Zwraca mały kwadracik, który buduje figurę czekającą w kolejce. 
      * @param x położenie w poziomie.
      * @param y położenie w pionie. 
      * @return 
      */
     public Rectangle2D getElementInQueue(int x, int y){
         return new Rectangle2D.Double(0 + x, 0 + y, WIDTH, HEIGHT);
     }
 }
