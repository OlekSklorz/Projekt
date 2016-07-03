package game;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
/**
 * Obiekt <code>MainMenuFrame</code> reprezentuje menu główne gry. 
 * Zawiera ono przyciski służace do wybrania nowej gry, załadowania zapisanej gry, ustawienia opcji,
 * obejrzenia statystyk, wyjścia z gry oraz zarządzania użytkownikami.
 */
public class MainMenuFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private JPanel panel;
    private JButton newGameButton, loadGameButton, optionsButton, statisticsButton, exitButton, signInButton, signUpButton;
    private JLabel user;
    public MainMenuFrame(){
        Font font = selectFont();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        newGameButton = makeButton("NEW GAME", font);
        loadGameButton = makeButton("LOAD GAME", font);
        optionsButton = makeButton("OPTIONS", font);
        statisticsButton = makeButton("STATISTICS", font); 
        exitButton = makeButton("EXIT GAME", font);
        signInButton = makeButton("Sign In", null);
        signUpButton = new JButton("Sign Up", null);
        user = new JLabel("Player: ");
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
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 10;
        panel.add(newGameButton, gbc);
        gbc.gridy = 4;
        panel.add(loadGameButton, gbc);
        gbc.gridy = 5;
        panel.add(optionsButton, gbc);
        gbc.gridy = 6;
        panel.add(statisticsButton, gbc);
        gbc.gridy = 7;
        panel.add(exitButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(new JPanel(), gbc);
        add(panel);
    }
    private Font selectFont(){
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String fontName : fontNames)
        {
            if(fontName.equals("Lucida Handwriting"))
                return new Font("Lucida Handwriting", Font.BOLD, 25);
        }
        return new Font("Serif", Font.BOLD, 25);
    }
    private JButton makeButton(String name, Font font){
        JButton button = new JButton(name);
        if(font != null)
            button.setFont(font);
        return button;
    }
}