package tetris;

import java.awt.Component;

public class SquareRunnable implements Runnable{
    Square square;
    Component c;
    public SquareRunnable(Square square, Component c){
        this.square = square;
        this.c = c;
    }
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
