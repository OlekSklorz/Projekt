package game;

import java.awt.*;
import javax.swing.*;

public class DifficultyLevels {
    private JButton easyButton = new JButton("EASY"), mediumButton = new JButton("MEDIUM"), hardButton = new JButton("HARD"), backButton = new JButton("Back");
    private JButton[] allButtons = {easyButton, mediumButton, hardButton, backButton};
    private JPanel levelsPanel;
    public DifficultyLevels(){
        JLabel textLabel = new JLabel("Choose level of difficulty");
        levelsPanel = new JPanel();
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
        for(JButton button : allButtons)
        {
            gbc.gridy++;
            levelsPanel.add(button, gbc);
        }
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
    
    public JButton[] getLevelsButton(){
        return allButtons;
    }
}
