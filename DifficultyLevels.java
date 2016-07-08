package game;

import java.awt.*;
import javax.swing.*;
import tetris.Tetris;

/**
 * Obiekt <code>DifficultyLevels</code> reprezentuje panel pozwalający na wybór poziomów trudności gier.
 * Do wyboru easy, medium i hard. 
 */
public class DifficultyLevels {
    private final JButton easyButton = new JButton("EASY"), mediumButton = new JButton("MEDIUM"), hardButton = new JButton("HARD"), backButton = new JButton("Back");
    private final JButton[] allButtons = {easyButton, mediumButton, hardButton};
    private JPanel levelsPanel, tetrisPanel;
    private final JLabel textLabel;
    private int activeGame;
    public DifficultyLevels(){
        this.activeGame = activeGame;
        textLabel = new JLabel("Choose level of difficulty");
        levelsPanel = new JPanel();
        /*Tetris tetris = new Tetris();
        tetrisPanel = tetris.getTetrisPanel();*/ // TEN KOMENTARZ ZOSTAWIĆ BO NIE JESTEM PEWIEN (może się przydać)
        levelsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 100;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        levelsPanel.add(new JPanel(), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 1;
        levelsPanel.add(textLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        for(JButton button : allButtons){
            gbc.gridy++;
            levelsPanel.add(button, gbc);
                button.addActionListener(e -> {
                    if(NewGameChoosing.getActiveGame() == 1){
                        levelsPanel.setVisible(false);
                        tetrisPanel = new Tetris(Levels.valueOf(button.getText()).lvl).getTetrisPanel();
                    }
                });
        }
        gbc.gridy = 4;
        levelsPanel.add(backButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        levelsPanel.add(new JPanel(), gbc);
        levelsPanel.setVisible(true);
        backButton.addActionListener(e ->   {
            levelsPanel.setVisible(false);
            NewGameChoosing.setNewGameVisable();
        });
    }
    
    /**
     * Pobiera panel zawierający tekst zachęty do wyboru poziomu trudności oraz 
     * przyciski z poziomami trudności i przycisk do powrotu. 
     * @return panel wyboru poziomu trudności.
     */
    public JPanel getLevelsPanel(){
        return levelsPanel;
    }
    
    /**
     * Pobiera wszystkie przyciski wyboru trudności.
     * @return tablica z przyciskami wyboru trudności. 
     */
    public JButton[] getButtons(){
        return allButtons;
    }
    
    /**
     * Pobiera przycisk służący do powrotu do panelu wyboru gry. 
     * @return przycisk do powrotu 
     */
    public JButton getBackButton(){
        return backButton;
    }
    
    /**
     * Pobiera etykietę z tekstem zachęty do wyboru poziomu trudności. 
     * @return etykieta z tekstem zachęty. 
     */
    public JLabel getTextLabel(){
        return textLabel;
    }
}
