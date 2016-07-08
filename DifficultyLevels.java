package game;

import java.awt.*;
import javax.swing.*;
import tetris.Tetris;

public class DifficultyLevels {
    private JButton easyButton = new JButton("EASY"), mediumButton = new JButton("MEDIUM"), hardButton = new JButton("HARD"), backButton = new JButton("Back");
    private JButton[] allButtons = {easyButton, mediumButton, hardButton};
    private JPanel levelsPanel, tetrisPanel;
    private JLabel textLabel;
    private int activeGame;
    public DifficultyLevels(){
        this.activeGame = activeGame;
        textLabel = new JLabel("Choose level of difficulty");
        levelsPanel = new JPanel();
        /*Tetris tetris = new Tetris();
        tetrisPanel = tetris.getTetrisPanel();*/
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
    
    public JPanel getLevelsPanel(){
        return levelsPanel;
    }
    
    
    public JButton[] getButtons(){
        return allButtons;
    }
    
    public JButton getBackButton(){
        return backButton;
    }
    
    public JLabel getTextLabel(){
        return textLabel;
    }
}

