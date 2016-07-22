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
    private JLabel pointsLabel, gameOverLabel;
    private GameField c;
    public Tetris(int lvl){
        /*informativePanel = new JPanel(new BorderLayout());
        JPanel pointsPanel = new JPanel(null);
        pointsLabel = new JLabel("Points: 0");
        //pointsPanel.setBounds(750, 500, 751, 501);
        pointsPanel.add(pointsLabel);
        pointsPanel.setBounds(8,8,9,9);
        //informativePanel.setLayout(new BorderLayout());
        informativePanel.setBounds(10,10,20,20);
        //informativePanel.add(pointsLabel, BorderLayout.NORTH);
        //informativePanel.add(new JLabel("NEXT FIGURE: "), BorderLayout.CENTER);
        //JPanel edgePanelRight = new JPanel(new BorderLayout());
        //edgePanelRight.add(new Edge(0,0,0,Toolkit.getDefaultToolkit().getScreenSize().height, 0), BorderLayout.WEST);
        //informativePanel.add(edgePanelRight);
        gamePanel = new JPanel();
        c = new GameField();
        gamePanel.add(c);
        emptyPanel = new JPanel(new BorderLayout());
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 25));
        gameOverLabel.setVisible(true);
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.setBounds(150,200,151,200);
        JPanel edgePanel = new JPanel(new BorderLayout());
        //int space = 451;
        //int space = 342;
        int space = 451;
        edgePanel.add(new Edge(space,0,space,Toolkit.getDefaultToolkit().getScreenSize().height, space), BorderLayout.EAST);
        emptyPanel.add(gameOverPanel);
        emptyPanel.add(edgePanel);
        tetrisPanel = new JPanel();
        tetrisPanel.setLayout(new BorderLayout());
        tetrisPanel.add(informativePanel, BorderLayout.EAST);
        tetrisPanel.add(gamePanel);
        tetrisPanel.add(emptyPanel, BorderLayout.WEST);
        tetrisPanel.setVisible(true);*/
        informativePanel = new JPanel(new BorderLayout());
        pointsLabel = new JLabel("Points: 0");
        informativePanel.add(pointsLabel, BorderLayout.NORTH);
        informativePanel.add(new JLabel("NEXT FIGURE: "), BorderLayout.CENTER);
        informativePanel.add(new Edge(0,0,0,Toolkit.getDefaultToolkit().getScreenSize().height, 0), BorderLayout.WEST);
        gamePanel = new JPanel();
        c = new GameField();
        gamePanel.add(c);
        emptyPanel = new JPanel(new BorderLayout());
        gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 25));
        gameOverLabel.setVisible(true);
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.setBounds(10,200,151,200);
        JPanel edgePanel = new JPanel(new BorderLayout());
        int space = 451;
        edgePanel.add(new Edge(space,0,space,Toolkit.getDefaultToolkit().getScreenSize().height, space), BorderLayout.EAST);
        emptyPanel.add(gameOverPanel);
        emptyPanel.add(edgePanel);
        tetrisPanel = new JPanel();
        tetrisPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1; 
        gbc.gridheight = 3;
        gbc.ipadx = 500;
        gbc.ipady = 600;
        gbc.ipadx = 189;
        tetrisPanel.add(emptyPanel, gbc);
        gbc.gridx = 1;
        tetrisPanel.add(gamePanel, gbc);
        gbc.gridx = 2;
        tetrisPanel.add(informativePanel, gbc);
        tetrisPanel.setVisible(true);
        play();
    }
    
    /**
     * Uruchamia grę. 
     */
    public void play(){
        int i = 0;
        int center = (int)c.getPreferredSize().width/2;
        center -= 20;
        do{
            center -= 1;
        }while(center % 20 != 0);
        FigureRunnable r = new FigureRunnable(c, 'a', 'd', 's', ' ', 200, center, gameOverLabel, pointsLabel);
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
        //public void set(int s){
         //   width = s;
        //}
        
        /**
         * Pobiera preferowany rozmiar komponentu zawierającego linię. 
         * @return preferowany rozmiar komponentu zawierającego linię. 
         */
        public Dimension getPreferredSize(){
            return new Dimension(space + 1, 601);
        }
    }
}
