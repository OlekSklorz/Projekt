package game;

import java.awt.EventQueue;

/**
 * Klasa <code>Game</code> reprezentuje główną klasę gry.
 */
public class Game {
    public static void main(String[] args) {
        new Style("Nimbus");
        
        EventQueue.invokeLater(() -> new MainMenuFrame() {{
            setTitle("Zestaw gier");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }});
    }
}
