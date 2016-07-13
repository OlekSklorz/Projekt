package tetris;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private ArrayList<Figure> figures = new ArrayList();
    private Component c;
    public char left, right, down; 
    private int delayed;
    public FigureRunnable(Component c, char left,char right,char down,int delayed){
        this.c = c;
        this.left = left;
        this.right = right;
        this.down = down;
        this.delayed = delayed;
    }
    
    /**
     * Porusza figurą pionowo i poziomo. Odmalowuje na nowo komponent. 
     * Ustawia czas wykonywania tego wątku. 
     */
    public void run(){
        c.setFocusable(true);
        //c.addKeyListener(new DownAction());
        try{
           for(Figure figure : figures){
               c.addKeyListener(new MovementAction(figure));
               do{
                   figure.move(0, Math.abs(figure.getTopX() + Element.getHeight()));
                   c.repaint();
                   Thread.sleep(delayed);
               }while(figure.getActualTopX() + Figure.getHeightFigure() != 560);
           }
        }catch(InterruptedException e){}
    }
    
    /**
     * Dodaje figurę do listy figur. 
     * @param f dodawana figura.
     */
    public void add(Figure f){
        figures.add(f);
    }
    
    /**
     * Obiekt <code>MovementAction</code> reprezentuje ruch figury. 
     * Za pomocą odpowiednich klawiszy figura może poruszać się w lewo, prawo lub
     * przyśpieszyć poruszanie się w dół. 
     */
    public class MovementAction extends KeyAdapter{
        //Element[][] elements = figures.get(i).getElements();
        Element[][] elements;
        Figure figure;
        public MovementAction(Figure figure){
            this.figure = figure;
            elements = figure.elements;
        }
        
        /**
         * Wykonuje czynność w odpowiedzi za przyciśnięcie klawisza.
         * KeyPressed różni się od keyTyped tym, że reaguje też na klawisze które
         * nie są zakodowane w unicodzie. 
         * Figura może poruszać się w lewo, prawo lub przyśpieszyć w dół. 
         * @param ke 
         */
        public void keyPressed(KeyEvent ke) {
            if(figure.getActualTopX() + Figure.getHeightFigure() != 560){
                char key = ke.getKeyChar();
                int leftX = elements[0][0].getLeftX();
                if(key == left){
                    if(leftX != 0){
                        figure.move(-Element.getWidth(), 0);
                        c.repaint();
                    }
                }else{
                    if(key == right){
                        if(leftX != 4 * Figure.getWidthFigure()){
                            figure.move(Element.getWidth(), 0);
                            c.repaint();
                        }
                    }else{
                        if(key == down){
                            figure.move(0, Math.abs(figure.getTopX() + Element.getHeight()));
                            c.repaint();
                        }
                    }
                }
            }
        }
    }
}
