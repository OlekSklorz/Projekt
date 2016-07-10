package tetris;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private Figure figure;
    private Component c;
    public char left, right, down; 
    public FigureRunnable(Figure figure, Component c){
        this.figure = figure;
        this.c = c;
        left = 'a';
        right = 'd';
        down = 's';
    }
    
    /**
     * Porusza figurą pionowo i poziomo. Odmalowuje na nowo komponent. 
     * Ustawia czas wykonywania tego wątku. 
     */
    public void run(){
        c.setFocusable(true);
        c.addKeyListener(new DownAction());
        try{
           Element[][] elements = figure.getElements();
            do{
                figure.move(0, figure.getTopX() + Element.getHeight());
                c.repaint();
                Thread.sleep(500);
            }while(figure.elements[0][0].getTopX() + elements.length * Element.getHeight() != 560);
        }catch(InterruptedException e){}
    }
    public class DownAction extends KeyAdapter{
        Element[][] elements = figure.getElements();
        public void keyPressed(KeyEvent ke) {
            if(figure.elements[0][0].getTopX() + elements.length * Element.getHeight() != 560){
                char key = ke.getKeyChar();
                int leftX = elements[0][0].getLeftX();
                if(key == left){
                    if(leftX != 0){
                        figure.move(figure.getLeftX() - Element.getWidth(), 0);
                        c.repaint();
                    }
                }else{
                    if(key == right){
                        if(leftX != 8 * Element.getWidth()){
                            figure.move(figure.getLeftX() + Element.getWidth(), 0);
                            c.repaint();
                        }
                    }else{
                        if(key == down){
                            figure.move(0, figure.getTopX() + Element.getHeight());
                            c.repaint();
                        }
                    }
                }
            }
        }
    }
}

