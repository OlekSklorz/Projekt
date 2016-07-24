package tetris;

import java.awt.*;
import javax.swing.JComponent;

/**
 * Obiekt <code>NextFigureComponent</code> reprezentuje pole w którym 
 * pojawia się następna figura. 
 */
public class NextFigureComponent extends JComponent{
    private Figure figure;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(figure != null && this.getBounds().y == 350){
            g2.setPaint(figure.getColor());
            Element[][] elements = figure.getElements();
            int x = 0, y;
            for(int w = 0; w < elements.length; w++){
                y = 0;
                for(int k = 0; k < elements[w].length; k++){
                    if(elements[w][k] != null)
                        g2.fill(elements[w][k].getElementInQueue(x, y));
                    y += Element.getHeight();
                }
                x += Element.getWidth();
            }
            x = 0;
            g2.setPaint(Color.BLACK);
            for(int w = 0; w < elements.length; w++){
                y = 0;
                for(int k = 0; k < elements[w].length; k++){
                    if(elements[w][k] != null)
                        g2.draw(elements[w][k].getElementInQueue(x, y));
                    y += Element.getHeight();
                }
                x += Element.getWidth();
            }
        }
    }
    public void setFigure(Figure figure){
        this.figure = figure;
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(61, 61);
    }
}

