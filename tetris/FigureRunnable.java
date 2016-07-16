package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private ArrayList<Figure> figures = new ArrayList();
    private GameField c;
    public char left, right, down; 
    private int delayed;
    private int limit = 0;
    public FigureRunnable(GameField c, char left,char right,char down,int delayed){
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
        //int limit = 0;
        boolean is;
        int fullLine;
        try{
           for(Figure figure : figures){
               c.addKeyListener(new MovementAction(figure));
               fullLine = -1;
               do{
                   int y = figure.getActualTopX();
                   figure.move(0, Element.getHeight());
                   is = isObstacle(figure);
                   int i = 0;
                   if(is && y - figure.getActualTopX() != 0)
                       figure.move(0, -Element.getHeight());
                   c.repaint();
                   Thread.sleep(delayed);
               }while(figure.getActualTopX() + figure.getHeightFigure() != 560 && !is);
               limit++;
               fullLine = c.checkLine(limit);
               if(fullLine != -1){
                   c.deleteLine(limit, fullLine);
                   c.repaint();
               }
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
    
    private boolean isObstacle(Figure figure){
        boolean is = false;
        int i = 0;
        do{
            int k = 0;
            do{
                if(figure.getElements()[i][k] != null)
                    is = c.isComponent(figure.getElements()[i][k].getTopX(), figure.getElements()[i][k].getLeftX(), limit);
                k++;
            }while(k < figure.getElements()[i].length && !is);
            i++;
        }while(i < figure.getElements().length && !is);
        return is;
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
            if(figure.getActualTopX() + figure.getHeightFigure() != 560){
                char key = ke.getKeyChar();
                int leftX = elements[0][0].getLeftX();
                if(key == left){
                    if(leftX != 0){
                        figure.move(-Element.getWidth(), 0);
                        boolean is = isObstacle(figure);
                        c.repaint();
                        if(is){
                            figure.move(Element.getWidth(), 0);
                            c.repaint();
                        }
                    }
                }else{
                    if(key == right){
                        if(leftX != (10 * Element.getWidth()) - figure.getWidthFigure()){
                            figure.move(Element.getWidth(), 0);
                            boolean is = isObstacle(figure);
                            c.repaint();
                            if(is){
                                figure.move(-Element.getWidth(), 0);
                                c.repaint();
                            }
                        }
                    }else{
                        if(key == down){
                            figure.move(0, Element.getHeight());
                            boolean is = isObstacle(figure);
                            c.repaint();
                            if(is){
                                figure.move(0, -Element.getHeight());
                                c.repaint();
                            }
                        }
                    }
                }
            }
        }
    }
}
