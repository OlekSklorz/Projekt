package game;

import java.awt.*;
import javax.swing.*;

/**
 * Obiekt <code>GameDialogWindow</code> reprezentuje menu w grze. 
 * Dostępne opcje to: kontynuuj grę, zapisz grę, opcje oraz wyjście z gry.
*/
public class GameDialogWindow extends JPanel{
    private JDialog dialog;
    private boolean ok;
    private final JButton continueButton = new JButton("CONTINUE"), saveGameButton = new JButton("SAVE GAME"), optionsButton = new JButton("OPTIONS"), exitButton = new JButton("EXIT GAME");
    public GameDialogWindow(JPanel gamePanel){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(continueButton);
        panel.add(saveGameButton);
        panel.add(optionsButton);
        panel.add(exitButton);
        continueButton.addActionListener(e -> {
            dialog.setVisible(false);
            ok = true;
        });
        exitButton.addActionListener(e -> {
            dialog.setVisible(false);
            gamePanel.setVisible(false);
            MainMenuFrame.setMainWindowVisable();
        });
        add(panel, BorderLayout.CENTER);
    }
    /**
     * Wyświetla okno dialogowe (menu w grze) służące do: kontynuowania gry, 
     * zapisu gry, włączeniu opcji albo wyjscia z gry. 
     * @param parent komponent będący właścicielem okna dialogowego.
     * @return czy okno dialogowe (menu) zostało zamkniete.
     */
    public boolean showDialog(Component parent, int x, int y){
        ok = false;
        Frame owner = null;
        if(parent instanceof Frame) owner = (Frame) parent;
        else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        if(dialog == null || dialog.getOwner() != owner)
        {
            Frame mainMenuFrame = MainMenuFrame.getFrames()[0];
            Point mainMenuFrameLocation = mainMenuFrame.getLocation();
            dialog = new JDialog(owner, true);
            dialog.setUndecorated(true);
            dialog.setLocation(mainMenuFrameLocation.x + x, mainMenuFrameLocation.y + y);
            dialog.add(this);
            dialog.pack();
        }
        dialog.setVisible(true);
        return ok;
    }
}

