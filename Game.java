package game;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainMenuFrame frame = new MainMenuFrame(){{
                setTitle("Zestaw gier");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);}};
        });
    } 
}

