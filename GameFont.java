package game;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
/**
 * Klasa <code>GameFont</code> reprezentuje czcionkę używaną w grze. 
 * Zawiera dwa warianty czcionki - artystyczną oraz jeśli system jej nie posiada - zwykłą.
 */
public class GameFont {
    private static final Font font = new Font("Serif", Font.BOLD, 25);
    /**
     * Tworzy artystyczną, ładną czcionkę. 
     * @return artystyczna czcionka lub w razie braku - zwykła
     */
    public static Font makeArtisticFont(){
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String fontName : fontNames)
            if(fontName.equals("Lucida Handwriting"))
                return new Font("Lucida Handwriting", Font.BOLD, 25);
        return font;
    }
}
