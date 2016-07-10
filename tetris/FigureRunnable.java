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
            Element[][] elements = figure.getElements();
            do{
                figure.move(0, figure.getLeftX() + Element.getHeight());
                c.repaint();
                Thread.sleep(500);
            }while(figure.elements[0][0].getTopX() + elements.length * Element.getHeight() != 560);
        }catch(InterruptedException e){}
    }
}
