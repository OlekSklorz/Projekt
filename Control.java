package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Michał
 */
public class Control {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private KeyListener f;
    private String upName = "\u2191", leftName = "\u2190", rightName = "\u2192", downName = "\u2193", spaceName = "space";
    private JButton up = new JButton(upName), left = new JButton(leftName), right = new JButton(rightName), down = new JButton(downName), space = new JButton(spaceName), back = new JButton("BACK");
    private JButton [] allButtons = {up, left, right, down, space};
    private JRadioButton pingPongRadio = new JRadioButton("Ping Pong"), tetrisRadio = new JRadioButton("Tetris"), snakeRadio = new JRadioButton("Snake"); 
    private ButtonGroup choiceGame = new ButtonGroup();
    private JPanel controlPanel = new JPanel();
    
    public Control() {
        choiceGame.add(pingPongRadio);
        choiceGame.add(tetrisRadio);
        choiceGame.add(snakeRadio);
        
        controlPanel.setLayout(new GridBagLayout());
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 35;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(pingPongRadio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        controlPanel.add(tetrisRadio, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        controlPanel.add(snakeRadio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JPanel(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        controlPanel.add(up, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(left, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        controlPanel.add(down, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        controlPanel.add(right, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        controlPanel.add(space, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        controlPanel.add(back, gbc);
        
        controlPanel.setVisible(true);
        
        up.addActionListener(e -> {
            up.setBackground(Color.BLUE);
            
            up.setBackground(null);
        });
        
        left.addActionListener(e -> {
            left.setBackground(Color.BLUE);
        });
        
        down.addActionListener(e -> {
            down.setBackground(Color.BLUE);
        });
        
        right.addActionListener(e -> {
            right.setBackground(Color.BLUE);
        });
        
        space.addActionListener(e -> {
            space.setBackground(Color.BLUE);
        });
        
        pingPongRadio.addActionListener(e -> {
            up.setEnabled(false);
            down.setEnabled(false);
            space.setEnabled(false);
            
            left.setEnabled(true);
            right.setEnabled(true);
        });
        
        tetrisRadio.addActionListener(e -> {
            up.setEnabled(false);
            down.setEnabled(false);
            
            left.setEnabled(true);
            right.setEnabled(true);
            space.setEnabled(true);
        });
        
        snakeRadio.addActionListener(e -> {
            space.setEnabled(false);
            
            left.setEnabled(true);
            right.setEnabled(true);
            up.setEnabled(true);
            down.setEnabled(true);
        });
    }
    
    public JPanel getPanel() {
        return controlPanel;
    }
    
    public JButton getBackButton() {
        return back;
    }

    public void keyPressed(KeyEvent e, String name) {
       System.out.println(e);
    }
}
