package tetris;

import java.awt.Component;

/**
 * Obiekt <code>SquareRunnable</code> reprezentuje poruszanie się kwadratu w 
 * wątku. 
 */
public class SquareRunnable implements Runnable{
    private Square square;
    private Component c;
    public SquareRunnable(Square square, Component c){
        this.square = square;
        this.c = c;
    }
    
    /**
     * Porusza kwadratem pionowo i poziomo. Odmalowuje na nowo komponent. 
     * Ustawia czas wykonywania tego wątku. 
     */
    public void run(){
        try{
            for(int i = 1; i < 27; i++){
                square.move(0, square.getTopX() + Element.getHeight());
                c.repaint();
                Thread.sleep(500);
            }
        }catch(InterruptedException e){}
    }
}
