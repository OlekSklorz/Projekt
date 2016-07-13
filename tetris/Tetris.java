package tetris;

import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

/**
 * Obiekt <code>Tetris</code> reprezentuje planszę do gry Tetris. 
 * Zawiera ona panel do gry oraz panel do wyświetlania wyniku i następnego elementu. 
 */
public class Tetris {
    private JPanel informativePanel, tetrisPanel, gamePanel, emptyPanel;
    private JLabel pointsLabel;
    private GameField c;
    public Tetris(int lvl){
        informativePanel = new JPanel();
        pointsLabel = new JLabel("Points: 9999999999999");
        informativePanel.setLayout(new BorderLayout());
        informativePanel.add(pointsLabel, BorderLayout.NORTH);
        informativePanel.add(new JLabel("NEXT FIGURE: "), BorderLayout.CENTER);
        informativePanel.add(new Edge(0,0,0,Toolkit.getDefaultToolkit().getScreenSize().height, 0), BorderLayout.WEST);
        gamePanel = new JPanel();
        c = new GameField();
        //Figure s = new Square(0, 0);
        //c.add(s);
        //Runnable r = new FigureRunnable(s, c);
        //Thread t = new Thread(r);
        //t.start();
        play();
        gamePanel.add(c);
        emptyPanel = new JPanel();
        emptyPanel.setLayout(new BorderLayout());
        int space = 451;
        emptyPanel.add(new Edge(space,0,space,Toolkit.getDefaultToolkit().getScreenSize().height, space), BorderLayout.EAST);
        tetrisPanel = new JPanel();
        tetrisPanel.setLayout(new BorderLayout());
        tetrisPanel.add(informativePanel, BorderLayout.EAST);
        tetrisPanel.add(gamePanel);
        tetrisPanel.add(emptyPanel, BorderLayout.WEST);
        tetrisPanel.setVisible(true);
    }
    
    /**
     * Uruchamia grę. 
     */
    public void play(){
        FigureRunnable r = new FigureRunnable(c, 'a', 'd', 's', 200);
        int i = 0;
        int center = (int)c.getPreferredSize().width/2;
        center -= 20;
        do{
            center -= 1;
        }while(center % 20 != 0);
        center = center;
        do{
            Figure s = new Square(center,-Figure.getHeightFigure());
            c.add(s);
            r.add(s);
            i++;
        }while(i < 10);
        Thread t = new Thread(r);
        t.start();
    }
    
    /**
     * Pobiera panel gry Tetris zawierajacej panel do gry oraz panel do
     * wyświetlania wyniku i kolejnego elementu. 
     * @return panel gry Tetris.
     */
    public JPanel getTetrisPanel(){
        return tetrisPanel;
    }
    
    /**
     * Obiekt <code>Edge</code> reprezentuje linię przedstawiajacą krawędź. 
     */
    public class Edge extends JComponent{
        Line2D.Double line;
        int space;
        public Edge(double xStart,double yStart,double xEnd,double yEnd,int space){
            line = new Line2D.Double(xStart, yStart, xEnd, yEnd);
            this.space = space;
        }
        
        /**
         * Rysuje linię o wymiarach podanych w konstruktorze. 
         * @param g obiekt Graphics służący do wykonywania operacji na figurach. 
         */
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            g2.draw(line);
        }
        
        /**
         * Pobiera preferowany rozmiar komponentu zawierającego linię. 
         * @return preferowany rozmiar komponentu zawierającego linię. 
         */
        public Dimension getPreferredSize(){
            return new Dimension(1 + space, 601);
        }
    }
}

