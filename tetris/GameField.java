package tetris;

import java.awt.*;
import java.util.*;
import javax.swing.JComponent;

/**
 * Obiekt <code>GameField</code> reprezentuje główne pole w którym rozgrywa się gra. 
 * W tym polu rysowane są kolejne elementy gry.
 */
public class GameField extends JComponent {
    ArrayList<Square> squares = new ArrayList();
    public void add(Square square){
        squares.add(square);
    }
    /**
     * Rysuje wszystkie elementy które występują w grze. Kolor jest wybierany
     * w sposób losowy. 
     * @param g obiekt Graphics służący do operacji na figurach. 
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(Square square : squares){
            g2.setPaint(square.getColor());
            Element[][] elements = square.getElements();
            for(int i = 0; i < elements.length; i++)
                for(int k = 0; k < elements[i].length; k++){
                    if(elements[i][k] != null)
                        g2.fill(elements[i][k].getElement());
                }
            g2.setPaint(Color.BLACK);
            for(int i = 0; i < elements.length; i++)
                for(int k = 0; k < elements[i].length; k++){
                    if(elements[i][k] != null)
                        g2.draw(elements[i][k].getElement());
                }
        }
    }
    
    /**
     * Pobiera preferowany rozmiar pola do gry. 
     * @return preferowany rozmiar pola do gry. 
     */
    public Dimension getPreferredSize(){
        return new Dimension(200, 561);
    }
}
