package game;

import java.awt.*;
import javax.swing.*;

/**
 * Obiekt <code>MainMenuFrame</code> reprezentuje menu główne gry. 
 * Zawiera ono przyciski służace do wybrania nowej gry, załadowania zapisanej gry, ustawienia opcji,
 * obejrzenia statystyk, wyjścia z gry oraz zarządzania użytkownikami.
 */
public class MainMenuFrame<S> extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static JPanel newGamePanel, optionsPanel, controlPanel, levelsPanel;
    private static JPanel panel;
    private final JButton newGameButton = new JButton("NEW GAME"), loadGameButton = new JButton("LOAD GAME"), optionsButton = new JButton("OPTIONS"), 
            statisticsButton = new JButton("STATISTICS"), exitButton = new JButton("EXIT"), signInButton = new JButton("Sign In"), 
            signUpButton = new JButton("Sign Up"), logOutButton = new JButton("LOG OUT");
    private final JLabel user; 
    private final GridBagConstraints gbc = new GridBagConstraints();
    private JButton [] allButton = {signInButton, signUpButton, logOutButton, newGameButton, loadGameButton, optionsButton, statisticsButton, exitButton};
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainMenuFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        NewGameChoosing newGame = new NewGameChoosing();
        newGamePanel = newGame.getJPanel();
        Options options = new Options();
        optionsPanel = options.getJPanel();
        Control control = new Control();
        controlPanel = control.getPanel();
        DifficultyLevels levels = new DifficultyLevels();
        levelsPanel = levels.getLevelsPanel();
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        user = new JLabel("Player: Anonim");
        
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 100;
        gbc.anchor = GridBagConstraints.NORTHWEST; 
        placeOnScreen(user, 0, 0, 1);
        int x = 0, y = 1, ipady = 1;
        for(JButton button : allButton) {
            placeOnScreen(button,x, y, ipady);
            y++;
            if(y > 3) {
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.weighty = 100;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                x = 1;
                ipady = 10;
            }
        }
        placeOnScreen(new JPanel(), 2, 9, 10);
        add(panel);
        panel.setVisible(true);
//------------------------------------------------------------------------------        
        JButton saveOptions = options.getSaveB();        
        exitButton.addActionListener(e -> System.exit(0));
        
        newGameButton.addActionListener(e -> {
            panel.setVisible(false);
            newGamePanel.setVisible(true);
            add(newGamePanel);
        });
        
        JButton startButton = newGame.getStart();
        startButton.addActionListener(e -> {
            panel.setVisible(false);
            levelsPanel.setVisible(true);
            add(levelsPanel);
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
        
        logOutButton.addActionListener(ae -> user.setText("Player: Anonim"));
        
        optionsButton.addActionListener(e -> {
            panel.setVisible(false);
            optionsPanel.setVisible(true);
            add(optionsPanel);
        });
//-------------OPTIONS        
        saveOptions.addActionListener(e -> {
            for(JButton button : allButton) 
                button.setFont(Font.decode(options.getActiveFont()));
            user.setFont(Font.decode(options.getActiveFont()));
            for(JButton button : levels.getLevelsButton())
                button.setFont(Font.decode(options.getActiveFont()));
        });
        
        JButton controlButton = options.getControlButton();
        controlButton.addActionListener(e ->{
            panel.setVisible(false);
            controlPanel.setVisible(true);
            add(controlPanel);
        });
//------------CONTROL        
        JButton controlBackButton = control.getBackButton();
        controlBackButton.addActionListener(e -> {
            controlPanel.setVisible(false);
            optionsPanel.setVisible(true);
        });
    }
       
    public void placeOnScreen(Object S, int positionX, int positionY, int ipady) {
        gbc.ipady = ipady;
        gbc.gridx = positionX;
        gbc.gridy = positionY;  
        panel.add((Component) S, gbc);
    }
    
    public JPanel getJPanel() {
        return this.panel;
    }
    
    public static void setMainWindowVisable() {
        panel.setVisible(true);
    }
    
}
