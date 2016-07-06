package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Obiekt <code>MainMenuFrame</code> reprezentuje menu główne gry. 
 * Zawiera ono przyciski służace do wybrania nowej gry, załadowania zapisanej gry, ustawienia opcji,
 * obejrzenia statystyk, wyjścia z gry oraz zarządzania użytkownikami.
 */
public class MainMenuFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static JPanel newGamePanel, optionsPanel;
    private static JPanel panel;
    private final JButton newGameButton, loadGameButton, optionsButton, statisticsButton, exitButton, signInButton, signUpButton, logOutButton;
    private final JLabel user; 
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainMenuFrame(){
        Font font = GameFont.makeArtisticFont();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        NewGameChoosing newGame = new NewGameChoosing();
        newGamePanel = newGame.getJPanel();
        Options options = new Options();
        optionsPanel = options.getJPanel();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        newGameButton = makeButton("NEW GAME", font);
        loadGameButton = makeButton("LOAD GAME", font);
        optionsButton = makeButton("OPTIONS", font);
        statisticsButton = makeButton("STATISTICS", font); 
        exitButton = makeButton("EXIT GAME", font);
        signInButton = makeButton("Sign In", null);
        signUpButton = makeButton("Sign Up", null);
        logOutButton = makeButton("Log Out", null);
        user = new JLabel("Player: Anonim");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 100;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(user, gbc);
        gbc.gridy = 1;
        panel.add(signInButton, gbc);
        gbc.gridy = 2;
        panel.add(signUpButton, gbc);
        gbc.gridy = 3;
        panel.add(logOutButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        panel.add(newGameButton, gbc);
        gbc.gridy = 5;
        panel.add(loadGameButton, gbc);
        gbc.gridy = 6;
        panel.add(optionsButton, gbc);
        gbc.gridy = 7;
        panel.add(statisticsButton, gbc);
        gbc.gridy = 8;
        panel.add(exitButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 9;
        panel.add(new JPanel(), gbc);
        add(panel);
        panel.setVisible(true);
        
        exitButton.addActionListener(e -> System.exit(0));
        
        newGameButton.addActionListener(e -> {
            panel.setVisible(false);
            newGamePanel.setVisible(true);
            add(newGamePanel);
        });
        
        signInButton.addActionListener(ae -> {
            DialogWindow dialog = new DialogWindow(true);
            if(dialog.showDialog(MainMenuFrame.this, "Login"))
                user.setText("Player: " + dialog.getUsername());
        });
        
        signUpButton.addActionListener(ae -> {
            DialogWindow dialog = new DialogWindow(false);
            dialog.showDialog(MainMenuFrame.this, "Registration");
        });
        
        optionsButton.addActionListener(e -> {
            panel.setVisible(false);
            optionsPanel.setVisible(true);
            add(optionsPanel);
        });
    }
    
    private JButton makeButton(String name, Font font) {
        JButton button = new JButton(name);
        if(font != null)
            button.setFont(font);
        return button;
    }
    
    public JPanel getJPanel() {
        return this.panel;
    }
    
    public static void setMainWindowVisable() {
        panel.setVisible(true);
    }
}
