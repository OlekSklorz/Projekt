package game;
import java.awt.EventQueue;
import javax.swing.WindowConstants;
/**
 * Klasa <code>Game</code> reprezentuje główną klasę gry.
 */
public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainMenuFrame() {{
            setTitle("Zestaw gier");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
        }});
    }
}

