/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Choice;

/**
 *
 * @author Michał
 */
public class Options {
    private JPanel optionsPanel;
    private String data;
    private String musicS, fullScreenS, controlS, windowSizeS, fontS;
    private JTextField musicTF, fullScreenTF, controlTF, windowSizeTF, fontTF;
    private JCheckBox fullScreenCB;
    private static final String creditsS = "duppa";
    private JButton creditsB, backB, saveB, controlB;
    private Choice musicChoice, windowSizeChoice, fontChoice;
    
    public Options() {
        Font font = GameFont.makeArtisticFont();
        optionsPanel = new JPanel();
        musicTF = makeTField("Music", font, false);
        fullScreenTF = makeTField("Full Screen", font, false);
        controlTF = makeTField("Controls", font, false);
        windowSizeTF = makeTField("Resolution", font, false);
        fontTF = makeTField("Font", font, false);
        
        backB = makeButton("Back", font);
        creditsB = makeButton("Credits", font);
        saveB = makeButton("Accept", font);
        controlB = makeButton("Check", font);
        
        fullScreenCB = new JCheckBox("", null, false);
        fullScreenCB.setSelected(false);
        
        windowSizeChoice = new Choice();
        windowSizeChoice.add("a");
        windowSizeChoice.add("b");
        windowSizeChoice.add("c");
        
        musicChoice = new Choice();
        musicChoice.add("1");
        musicChoice.add("2");
        musicChoice.add("3");
        
        fontChoice = new Choice();
        fontChoice.add("!");
        fontChoice.add("@");
        fontChoice.add("#");
        
        GridBagConstraints gbc = new GridBagConstraints();
        optionsPanel.setLayout(new GridBagLayout());
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.ipady = 10;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        optionsPanel.add(musicTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        optionsPanel.add(musicChoice, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        optionsPanel.add(new JPanel(), gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        optionsPanel.add(new JPanel(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        optionsPanel.add(fullScreenTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        optionsPanel.add(fullScreenCB, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        optionsPanel.add(controlTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        optionsPanel.add(controlB, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        optionsPanel.add(windowSizeTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        optionsPanel.add(windowSizeChoice, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        optionsPanel.add(fontTF, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        optionsPanel.add(fontChoice, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.ipady = 20;
        gbc.gridwidth = 3;
        optionsPanel.add(creditsB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        optionsPanel.add(saveB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        optionsPanel.add(backB, gbc);

        optionsPanel.setVisible(true);
        
        
        backB.addActionListener(e -> {
            optionsPanel.setVisible(false);
            MainMenuFrame.setMainWindowVisable();
        });
    }
    
    public JButton makeButton(String name, Font font) {
        JButton button = new JButton(name);
        if(font != null)
            button.setFont(font);
        
        return button;
    }
    
    public JTextField makeTField(String name, Font font, Boolean editable) {
        JTextField textField = new JTextField(name);
        if(font != null)
            textField.setFont(font);
        if(editable != null)
            textField.setEditable(editable);
        
        return textField;
    }
    
    
    public JPanel getJPanel() {
        return optionsPanel;
    }
    
}
