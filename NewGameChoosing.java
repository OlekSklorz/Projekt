package game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *Obiekt <code>NewGameChoosing</code> reprezentuje nowy panel pozwalający na wybór typu rozgrywki
 *Zawiera przyciski PingPong, Tetris, Snake, Start oraz Back
 * BRAK DALSZEJ OBSLUGI PRZYCISKOW,
 * BRAK SCREENOW,
 * BRAK PODSWIETLENIA,
 * @author Łysy
 */
public final class NewGameChoosing {
    private static JPanel newGamePanel, levelsPanel;
    private static int activeGame = 1;
    private JButton start = new JButton("Start"), pingPong = new JButton("Ping Pong"), tetris = new JButton("Tetris"), snake = new JButton("Snake"), back = new JButton("Back");
    private JButton[] allButtons = {start, pingPong, tetris, snake, back};
    public NewGameChoosing(){
        try {
            DifficultyLevels levels = new DifficultyLevels();
            levelsPanel = levels.getLevelsPanel();
            newGamePanel = new JPanel();
            JLabel pingPongImage;
            
            pingPongImage = getLabel("C:\\Users\\Tomek\\Documents\\obr\\a.png", true);
            JLabel tetrisImage = getLabel("C:\\Users\\Tomek\\Documents\\obr\\a.png", true);
            JLabel snakeImage = getLabel("C:\\Users\\Tomek\\Documents\\obr\\a.png", true);
        
        
            tetrisImage.setText("<-");
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
                newGamePanel.setVisible(false);
                levelsPanel = levels.getLevelsPanel();
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
    
    /**
     * Pobiera panel zawierający przyciski do wyboru gry, przyciski do powrotu i rozpoczęcia gry
     * oraz obrazy ukazujące gry. 
     * @return panel wyboru gry.
     */
    public JPanel getJPanel() {
        return newGamePanel;
    }
    
    /**
     * Pobiera przycisk do rozpoczęcia wybranej gry. 
     * @return przycisk rozpoczynający grę 
     */
    public JButton getStart(){
        return start;
    }
    
    /**
     * Ustawia widoczność panelu wyboru gry. 
     */
    public static void setNewGameVisable(){
        newGamePanel.setVisible(true);
    }
    
    /**
     * Pobiera numer aktywnej gry. Ping Pong = 0, Tetris = 1, Snake = 2.
     * @return numer aktywnej gry.
     */
    public static int getActiveGame(){
        return activeGame;
    }
    
    /**
     * Pobiera wszystkie przyciski z menu do wyboru gry. 
     * @return tablica przycisków z menu do wyboru gry.
     */
    public JButton[] getButtons(){
        return allButtons;
    }
}
