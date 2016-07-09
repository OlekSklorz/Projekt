package tetris;

import java.awt.*;
import java.util.*;
import javax.swing.JComponent;

public class GameField extends JComponent {
    ArrayList<Square> squares = new ArrayList();
    public void add(Square square){
        squares.add(square);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(Square square : squares){
            g2.setPaint(MyColors.getColor(new Random().nextInt(12)));
            Element[][] elements = square.getElements();
            for(int i = 0; i < 2; i++)
                for(int k = 0; k < 2; k++)
                    g2.fill(elements[i][k].getElement());
            g2.setPaint(Color.BLACK);
            for(int i = 0; i < 2; i++)
                for(int k = 0; k < 2; k++)
                    g2.draw(elements[i][k].getElement());
        }
    }
    public Dimension getPreferredSize(){
        return new Dimension(200, 561);
    }
}
