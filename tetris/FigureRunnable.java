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
    public char left, right, down, rotation; 
    private int delayed;
    private int limit = 0;
    public FigureRunnable(GameField c, char left, char right, char down, char rotation, int delayed){
        this.c = c;
        this.left = left;
        this.right = right;
        this.down = down;
        this.rotation = rotation;
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
        boolean is, deleted;
        int fullLine, x;
        Figure tempFigure;
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
               }while(!c.isBorder(figure, "down") && !is);
               //}while(((figure.getActualTopX() + figure.getHeightFigure() != 580 && ( !(figure instanceof FigureL && figure.getPosition() != 3))) || (figure.getActualTopX() != 540 && figure.getPosition() == 2)) && !is);
               figure.setStopMovement(true);
               limit++;
               do{
                    fullLine = c.checkLine(limit);
                    deleted = false;
                    if(fullLine != -1){
                        x = fullLine * Element.getHeight();
                        c.deleteLine(limit, x);
                        c.repaint();
                        deleted = true;
                        for(int i = 0; i < limit; i++){
                            tempFigure = figures.get(i);
                            if(tempFigure.getActualTopX() < x-1 && !c.isEmptyLine(tempFigure)){
                                
                                tempFigure.move(0, Element.getHeight());
                            }
                            c.repaint();
                        }
                    }
               }while(deleted);
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
            if(!figure.getStopMovement() && !c.isBorder(figure, "down")){
            //if(!figure.getStopMovement() && ((figure.getActualTopX() + figure.getHeightFigure() != 560 && figure.getPosition() != 2) || (figure.getActualTopX() != 540 && figure.getPosition() == 2))){
                char key = ke.getKeyChar();
                int leftX = elements[0][0].getLeftX();
                if(key == left){
                    if(!c.isBorder(figure, "left")){
                    //if(leftX != 0 && (figure.getPosition() != 1 || leftX + (-1) * figure.getWidthFigure() >= 0) && (figure instanceof FigureL && figure.getPosition() == 2 && leftX + (-1) * figure.getWidthFigure() >= 0)){
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
                        if(!c.isBorder(figure, "right")){
                        //if((leftX != (10 * Element.getWidth()) - figure.getWidthFigure() && figure.getPosition() != 1 && !(figure instanceof FigureL && figure.getPosition() == 2)) || (figure.getPosition() == 1 && leftX < 180) || (figure instanceof FigureL && figure.getPosition() == 2 && leftX <= 10 * Element.getWidth() - 2 * Element.getWidth())){
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
                        }else{
                            if(key == rotation){
                                if(!(figure instanceof Square)){
                                    figure.rotate(Math.PI/2);
                                    Element[][] el = figure.getElements();
                                    boolean repeating = false;
                                    int w = 0, k;
                                    do{
                                        k = 0;
                                        do{
                                            if(elements[w][k] != null && (elements[w][k].getLeftX() < 0 || elements[w][k].getLeftX() >= 10 * Element.getWidth() || c.isComponent(elements[w][k].getTopX(), elements[w][k].getLeftX(), limit)))
                                                repeating = true;
                                            k++;
                                        }while(k < el[w].length && !repeating);
                                        w++;
                                    }while(w < el.length && !repeating);
                                    if(repeating) figure.rotate(-Math.PI/2);
                                    c.repaint();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
