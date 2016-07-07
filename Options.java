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
import java.awt.Component;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Michał
 * @param <S>
 */
public class Options <S>{
    private JPanel optionsPanel = new JPanel(), controlPanel;
    private JTextField musicTF = new JTextField("Music"), fullScreenTF = new JTextField("Full Screen"), controlTF = new JTextField("Controls"), 
            windowSizeTF = new JTextField("Resolution"), fontTF = new JTextField("Fonts");
    private JButton CreditsButton = new JButton("Credits"), backButton = new JButton("Back"), saveButton = new JButton("Save"), controlButton = new JButton("Controls");
    private JCheckBox fullScreenCB;
    private Choice musicChoice, windowSizeChoice, fontChoice;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private String [] windowSizeTab = new String [3];
    private String [] musicTab = new String [3];
    private String [] fontTab = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private Object [] allObject = {musicTF, fullScreenTF, controlTF, windowSizeTF, fontTF, controlButton, CreditsButton, saveButton, backButton};
    private JTextField [] allJTextField = {musicTF, fullScreenTF, controlTF, windowSizeTF, fontTF};

    /*
    Constructor generate new JPanel with new components. Changing music, type of screen, font, controls.
    */
    public Options() {
        Control control = new Control();
        controlPanel = control.getPanel();
        fullScreenCB = new JCheckBox("", null, false);
        fullScreenCB.setSelected(false);
 
        windowSizeChoice = makeChoice(windowSizeTab);
        musicChoice = makeChoice(musicTab);
        fontChoice = makeChoice(fontTab);
        
        for(JTextField textfield : allJTextField)
            textfield.setEditable(false);
            
        optionsPanel.setLayout(new GridBagLayout());
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;    
        gbc.weightx = 1;

        int up = 0;
        for(Object object : allObject) {
            gbc.gridwidth = 1;
            placeOnScreen(object, 0, up , 10);
            if(up == 5)
                placeOnScreen(object, 1, 2, 10);
            up++;
        }
        placeOnScreen(musicChoice, 1, 0, 10);
        placeOnScreen(new JPanel(), 2, 0, 10);        
        placeOnScreen(fullScreenCB, 1, 1, 10);           
        placeOnScreen(windowSizeChoice, 1, 3, 10);   
        placeOnScreen(fontChoice, 1, 4, 10);

        optionsPanel.setVisible(true);    
//------------------------------------------------------------------------------        
        saveButton.addActionListener(e -> {
            for(Object object : allObject) {
                if(object instanceof JTextField) {
                    JTextField a = (JTextField) object;
                    a.setFont(Font.decode(getActiveFont()));
                }
                if(object instanceof JButton) {
                    JButton b = (JButton) object;
                    b.setFont(Font.decode(getActiveFont()));
                } 
            }
        });
        
        backButton.addActionListener(e -> {
            optionsPanel.setVisible(false);
            MainMenuFrame.setMainWindowVisable();
        });
//----------------CONTROL        
        controlButton.addActionListener(e -> {
            optionsPanel.setVisible(false);
            controlPanel = control.getPanel();
        });
        
    }
  
    /*
    Type Choice
    Returns list of elements (any length) to choice;
    */
    public Choice makeChoice(String [] tab) {
        Choice choice = new Choice();
        for(int a = 0; a < tab.length; a++) {
            if(tab[a] == null)
                tab[a] = "BRAK DANYCH";
            choice.add(tab[a]); 
        }
            
        return choice;   
    }   
    /*
    Type void
    Places elements S (any type) on X, Y position;
    */
    public void placeOnScreen(Object S, int x, int y, int ipady) {
        gbc.ipady = ipady;
        gbc.gridx = x;
        gbc.gridy = y;  
        optionsPanel.add((Component) S, gbc);
    }   
    /*
    Type JPanel
    Returns main panel;
    */
    public JPanel getJPanel() {
        return optionsPanel;
    }   
    /*
    Type: Button
    Returns Button;
    */
    public JButton getSaveB() {
        return saveButton;
    }   
    /*
    Type: String
    Returns selected Font;
    */
    public String getActiveFont() {
        return fontChoice.getSelectedItem();
    }
    /*
    Type JButton
    Return back Button;
    */
    public JButton getControlButton() {
        return controlButton;
    }
}
