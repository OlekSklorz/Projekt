package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private ArrayList<Figure> figures = new ArrayList();
    private GameField c;
    public char left, right, down, rotation; 
    private int delayed;
    private int limit = 0;
    private int start;
    private JLabel gameOverLabel;
    public FigureRunnable(GameField c, char left, char right, char down, char rotation, int delayed, int start, JLabel gameOverLabel){
        this.c = c;
        this.left = left;
        this.right = right;
        this.down = down;
        this.rotation = rotation;
        this.delayed = delayed;
        this.start = start;
        this.gameOverLabel = gameOverLabel;
        
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
        int fullLine, x, size;
        Figure tempFigure;
        Figure figure;
        try{
            do{
                Random randomFigure = new Random();
                figure = getFigure();
                figures.add(figure);
                c.add(figure);
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
                figure.setStopMovement(true);
                figure.move(0, Element.getHeight());
                boolean freeFall = false;
                while(!c.isBorder(figure, "down") && !isObstacle(figure)){
                    figure.move(0, Element.getHeight());
                    freeFall = true;
                }
                if(!freeFall || isObstacle(figure))
                    figure.move(0, -Element.getHeight());
                c.repaint();
                limit++;
                do{
                    fullLine = c.checkLine(limit);
                    deleted = false;
                    if(fullLine != -1){
                        x = fullLine * Element.getHeight();
                        for(int i = 0; i < limit; i++)
                            c.deleteLine(figures.get(i), x, i, figures);
                        ArrayList<Figure> tempFigures = c.przesun(figures);
                        if(tempFigures != null){
                            figures = tempFigures;
                            limit = figures.size();
                        }
                        c.repaint();
                        deleted = true;
                        for(int i = 0; i < limit; i++){
                            tempFigure = figures.get(i);
                            Element[][] elements = tempFigure.getElements();
                            for(int w = 0; w < elements.length; w++){
                                for(int k = 0; k < elements[w].length; k++){
                                    if(elements[w][k] != null && elements[w][k].getTopX() < x - 1){
                                        elements[w][k].setTop(elements[w][k].getTopX() + 20);
                                    }
                                }
                            }
                            c.repaint();
                        }
                    }
                }while(deleted);
            }while(!isGameOver());
            gameOverLabel.setVisible(true);
        }catch(InterruptedException e){}
    }
    
    private Figure getFigure(){
        Random randomFigure = new Random();
        switch(randomFigure.nextInt(5)){
            case 0: 
                return new Square(start,-(Square.getYElements() * Element.getHeight()));
            case 1:
                return new FigureI(start,-(FigureI.getYElements() * Element.getHeight()));
            case 2:
                return new FigureL(start,-(FigureL.getYElements() * Element.getHeight()));
            case 3:
                return new FigureT(start,-(FigureT.getYElements() * Element.getHeight()));
            case 4:
                return new FigureZ(start,-(FigureZ.getYElements() * Element.getHeight()));
        }
        return null;
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
    
    private boolean isGameOver(){
        boolean gameOver = false;
        Element[][] elements;
        for(int i = 0; i < figures.size(); i++){
            elements = figures.get(i).getElements();
            for(int w = 0; w < elements.length; w++){
                for(int k = 0; k < elements[w].length; k++){
                    if(elements[w][k] != null && elements[w][k].getTopX() < 0)
                        return true;
                    }
                }
            }
        return false;
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
                char key = ke.getKeyChar();
                int leftX = elements[0][0].getLeftX();
                if(key == left){
                    if(!c.isBorder(figure, "left")){
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
                                            if(elements[w][k] != null && (elements[w][k].getLeftX() < 0 || elements[w][k].getLeftX() >= 10 * Element.getWidth() || c.isComponent(elements[w][k].getTopX(), elements[w][k].getLeftX(), limit) || elements[w][k].getTopX() > 27 * Element.getHeight()))
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
