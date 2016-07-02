package game;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class Game {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                MainMenuFrame frame = new MainMenuFrame();
                frame.setTitle("Zestaw gier");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    } 
}
