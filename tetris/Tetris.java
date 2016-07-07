package tetris;

import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;
public class Tetris {
    JPanel informativePanel, tetrisPanel, gamePanel, emptyPanel;
    JLabel pointsLabel;
    public Tetris(){
        informativePanel = new JPanel();
        pointsLabel = new JLabel("Points: 9999999999999");
        informativePanel.setLayout(new BorderLayout());
        informativePanel.add(pointsLabel, BorderLayout.NORTH);
        informativePanel.add(new JLabel("NEXT FIGURE: "), BorderLayout.CENTER);
        informativePanel.add(new Edge(0,0,0,Toolkit.getDefaultToolkit().getScreenSize().height, 0), BorderLayout.WEST);
        gamePanel = new JPanel();
        gamePanel.add(new JLabel("TU BĘDZIE GRA"));
        emptyPanel = new JPanel();
        emptyPanel.setLayout(new BorderLayout());
        int space = 440;
        emptyPanel.add(new Edge(space,0,space,Toolkit.getDefaultToolkit().getScreenSize().height, space), BorderLayout.EAST);
        tetrisPanel = new JPanel();
        tetrisPanel.setLayout(new BorderLayout());
        tetrisPanel.add(informativePanel, BorderLayout.EAST);
        tetrisPanel.add(gamePanel, BorderLayout.CENTER);
        tetrisPanel.add(emptyPanel, BorderLayout.WEST);
    }
    public JPanel getTetrisPanel(){
        return tetrisPanel;
    }
    public class Edge extends JComponent{
        Line2D.Double line;
        int space;
        public Edge(double xStart,double yStart,double xEnd,double yEnd,int space){
            line = new Line2D.Double(xStart, yStart, xEnd, yEnd);
            this.space = space;
        }
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            g2.draw(line);
        }
        public Dimension getPreferredSize(){
            return new Dimension(1 + space, 601);
        }
    }
}
