package game;

import java.awt.*;
import javax.swing.*;

public class DifficultyLevels {
    private JButton easyButton, mediumButton, hardButton;
    private JPanel levelsPanel;
    public DifficultyLevels(){
        JLabel textLabel = new JLabel("Choose level of difficulty");
        easyButton = new JButton("EASY");
        mediumButton = new JButton("MEDIUM");
        hardButton = new JButton("HARD");
        levelsPanel = new JPanel();
        levelsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = gbc.weighty = 100;
        gbc.gridx = gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        levelsPanel.add(new JPanel(), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 1;
        levelsPanel.add(textLabel, gbc);
        gbc.gridy = 1;
        levelsPanel.add(easyButton, gbc);
        gbc.gridy = 2;
        levelsPanel.add(mediumButton, gbc);
        gbc.gridy = 3;
        levelsPanel.add(hardButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        levelsPanel.add(new JPanel(), gbc);
        levelsPanel.setVisible(true);
    }
    public JPanel getLevelsPanel(){
        return levelsPanel;
    }
}
