package game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *Obiekt NewGameChoosing reprezentuje nowy panel pozwalający na wybór typu rozgrywki
 *Zawiera przyciski PingPong, Tetris, Snake, Start oraz Back
 * BRAK DALSZEJ OBSLUGI PRZYCISKOW,
 * BRAK SCREENOW,
 * BRAK PODSWIETLENIA,
 * @author Łysy
 */
public final class NewGameChoosing {
    private JPanel newGamePanel;
    private int activeGame = 1;
    
    public NewGameChoosing(){
        try {
            JButton pingPong, tetris, snake, back, start;
            Font font = GameFont.makeArtisticFont();
            newGamePanel = new JPanel();
            JLabel pingPongImage;
        
            pingPongImage = getLabel("C:\\Users\\Michał\\Documents\\NetBeansProjects\\PROJEKT\\src\\images\\a.jpg", true);
            JLabel tetrisImage = getLabel("C:\\Users\\Michał\\Documents\\NetBeansProjects\\PROJEKT\\src\\images\\a.jpg", true);
            JLabel snakeImage = getLabel("C:\\Users\\Michał\\Documents\\NetBeansProjects\\PROJEKT\\src\\images\\a.jpg", true);
        
        
            tetrisImage.setText("<-");
            pingPong = makeButton("Ping Pong", font);
            tetris = makeButton("Tetris", font);
            snake = makeButton("Snake", font);
            back = makeButton("Back", font);
            start = makeButton("Start", font);
//          POSITIONS OF BUTTONS        
            GridBagConstraints gbc = new GridBagConstraints();
            newGamePanel.setLayout(new GridBagLayout());
            gbc.weightx = 100;
            gbc.weighty = 100;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 1;
            gbc.gridy = 1;
            newGamePanel.add(pingPongImage, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            newGamePanel.add(pingPong, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            newGamePanel.add(tetrisImage, gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            newGamePanel.add(tetris, gbc);

            gbc.gridx = 3;
            gbc.gridy = 1;
            newGamePanel.add(snakeImage, gbc);
            gbc.gridx = 3;
            gbc.gridy = 0;
            newGamePanel.add(snake, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            newGamePanel.add(new JPanel(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 3;
            newGamePanel.add(start, gbc);

            gbc.gridx = 2;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            newGamePanel.add(back,gbc);
            newGamePanel.setVisible(true);
//          BUTTON PINGPONG       
            pingPong.addActionListener(e -> {
                activeGame = 0;
                if(activeGame == 0) {
                    pingPongImage.setText("<-");
                    tetrisImage.setText("");
                    snakeImage.setText("");
                }
            });
//          BUTTON TETRIS        
            tetris.addActionListener(e -> {
                activeGame = 1;
                if(activeGame == 1) {
                    tetrisImage.setText("<-");
                    pingPongImage.setText("");
                    snakeImage.setText("");
                }
            });
//          BUTTON SNAKE
            snake.addActionListener(e -> {
                activeGame = 2;
                if(activeGame == 2) {
                    snakeImage.setText("<-");
                    tetrisImage.setText("");
                    pingPongImage.setText("");
                }
            });
//          BUTTON START
            start.addActionListener(e -> {
                if(activeGame == 0)
                    System.out.println("Ping pong");
                if(activeGame == 1)
                    System.out.println("Tetris");
                if(activeGame == 2)
                    System.out.println("Snake");
            });
//          BUTTON BACK
            back.addActionListener(e -> {
                newGamePanel.setVisible(false);
                MainMenuFrame.setMainWindowVisable();
            });

        } catch (IOException ex) {
            Logger.getLogger(NewGameChoosing.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    public Image getImage(String path) throws IOException {
        File file = new File(path);
        Image image = ImageIO.read(file);
        
        return image;
    }
    
    public JLabel getLabel(String path, boolean visibility) throws IOException {
        Image image = getImage(path);
        image = image.getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setVisible(visibility);
    
        return label;
    }

    public JButton makeButton(String name, Font font) {
        JButton button = new JButton(name);
        if(font != null)
            button.setFont(font);
        
        return button;
    }
    
    public JPanel getJPanel() {
        return newGamePanel;
    }

}
