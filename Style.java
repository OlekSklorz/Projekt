package game;

import java.util.logging.*;
import javax.swing.*;

/**
 * Klasa <code>Style</code> reprezentuje styl(wygląd ramki i komponentów) okienka gry.
 * @version 1.00 2016-07-03
 * @author Konrad Stachoń
 */
public class Style {
    
    /**
     * Ustawia styl
     * @param style - styl do ustawienia. 
     */
    public Style(String style) {
        for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
            if(style.equals(info.getName()))
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Style.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
