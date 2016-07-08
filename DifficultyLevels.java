package game;

import java.awt.*;
import javax.swing.*;

public class DifficultyLevels {
    private JButton easyButton, mediumButton, hardButton, backButton;
    private JPanel levelsPanel;
    public DifficultyLevels(){
        JLabel textLabel = new JLabel("Choose level of difficulty");
        easyButton = new JButton("EASY");
        mediumButton = new JButton("MEDIUM");
        hardButton = new JButton("HARD");
        backButton = new JButton("Back");
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
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        levelsPanel.add(easyButton, gbc);
        gbc.gridy = 2;
        levelsPanel.add(mediumButton, gbc);
        gbc.gridy = 3;
        levelsPanel.add(hardButton, gbc);
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
}
