package tetris;

import java.awt.Component;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private Figure figure;
    private Component c;
    public FigureRunnable(Figure figure, Component c){
        this.figure = figure;
        this.c = c;
    }
    
    /**
     * Porusza figurą pionowo i poziomo. Odmalowuje na nowo komponent. 
     * Ustawia czas wykonywania tego wątku. 
     */
    public void run(){
        try{
            int overflow = figure.getElements().length - 2;
            for(int i = 1; i < 27 - overflow; i++){
                figure.move(0, figure.getTopX() + Element.getHeight());
                c.repaint();
                Thread.sleep(500);
            }
        }catch(InterruptedException e){}
    }
}
