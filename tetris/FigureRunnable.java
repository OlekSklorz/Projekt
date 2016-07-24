package tetris;

import game.GameDialogWindow;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Obiekt <code>FigureRunnable</code> reprezentuje proces poruszania figury.
 */
public class FigureRunnable implements Runnable{
    private ArrayList<Figure> figures = new ArrayList();
    private GameField c;
    private int left, right, down, rotation, delayed, limit = 0, start;
    private static final int EXIT = KeyEvent.VK_ESCAPE;
    private JLabel gameOverLabel;
    private JPanel informativePanel, tetrisPanel;
    private static JFrame frame;
    private boolean stop;
    public FigureRunnable(GameField c, int[] control, int delayed, int start, JLabel gameOverLabel, JPanel informativePanel, JFrame frame, JPanel tetrisPanel, int lvl){
        this.c = c;
        c.addKeyListener(new MenuAction());
        c.setFocusable(true);
        left = control[0];
        right = control[1];
        down = control[2];
        rotation = control[3];
        this.delayed = delayed - (lvl  * 50);
        this.start = start;
        this.gameOverLabel = gameOverLabel;
        this.informativePanel = informativePanel;
        this.frame = frame;
        this.tetrisPanel = tetrisPanel;
        stop = false;
    }
    
    /**
     * Porusza figurą pionowo i poziomo. Odmalowuje na nowo komponent. 
     * Ustawia czas wykonywania tego wątku. 
     */
    public void run(){
        boolean is = false, deleted;
        int fullLine, x, points = 0, acceleration = 100;
        Component tempComponent;
        JLabel pointsLabel = null;
        for(int index = 0; index < informativePanel.getComponentCount(); index++){
            tempComponent = informativePanel.getComponent(index);
            if(tempComponent instanceof JLabel && ((JLabel)tempComponent).getText().equals("Points: 0"))
                pointsLabel = (JLabel)tempComponent;
        }
        Figure tempFigure, figure, nextFigure = null;
        NextFigureComponent nfc = new NextFigureComponent();
        nfc.setBounds(10,350,82,62);
        try{
            do{
                if(nextFigure != null)
                    figure = nextFigure;
                else
                    figure = getFigure();
                nextFigure = getFigure();
                nfc.setFigure(nextFigure);
                nfc.setBounds(10,350,82,62);
                informativePanel.add(nfc);
                nfc.repaint();
                figures.add(figure);
                c.add(figure);
                c.addKeyListener(new MovementAction(figure));
                fullLine = -1;
                if(delayed > 50 && points >= acceleration && points < acceleration * 5){
                    delayed -= 50;
                    acceleration *= 5;
                }
                do{
                    if(!stop){
                        int y = figure.getActualTopX();
                        figure.move(0, Element.getHeight());
                        is = isObstacle(figure);
                        int i = 0;
                        if(is && y - figure.getActualTopX() != 0)
                            figure.move(0, -Element.getHeight());
                        c.repaint();
                        nfc.setBounds(10,350,82,62);
                        Thread.sleep(delayed);
                    }
                }while(!c.isBorder(figure, "down") && !is || stop);
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
                        ArrayList<Figure> tempFigures = c.relocateListElements(figures);
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
                        points += 10;
                        pointsLabel.setText("Points: " + points);
                    }
                }while(deleted);
            }while(!isGameOver());
            gameOverLabel.setVisible(true);
        }catch(InterruptedException e){}
    }
    
    private Figure getFigure(){
        Random randomFigure = new Random();
        switch(randomFigure.nextInt(6)){
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
            case 5:
                return new ReversedFigureL(start, -(ReversedFigureL.getYElements() * Element.getHeight()));
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
            System.out.println("as");
        }
        
        /**
         * Wykonuje czynność w odpowiedzi za przyciśnięcie klawisza.
         * KeyPressed różni się od keyTyped tym, że reaguje też na klawisze które
         * nie są zakodowane w unicodzie. 
         * Figura może poruszać się w lewo, prawo lub przyśpieszyć w dół. 
         * @param ke wciśnięty klawisz. 
         */
        public void keyPressed(KeyEvent ke) {
            if(!figure.getStopMovement() && !c.isBorder(figure, "down")){
                int key = ke.getKeyCode();
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
    
    /**
     * Obiekt <code>MenuAction</code> reprezentuje uruchomienie menu w grze. 
     */
    public class MenuAction extends KeyAdapter{
        /**
         * Uruchamia menu w grze po wciśnięciu klawisza ESC. 
         * @param ke wciśnięty klawisz. 
         */
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if(key == EXIT){
                stop = true;
                GameDialogWindow dialog = new GameDialogWindow(tetrisPanel);
                if(dialog.showDialog(frame, c.getWidth()/2 + 200, c.getHeight()/2))
                    stop = false;
            }
        }
        
    } 
}
