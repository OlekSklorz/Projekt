package game;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
public class GameFont {
    private static final Font font = new Font("Serif", Font.BOLD, 25);
    public static Font makeArtisticFont(){
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(String fontName : fontNames)
        {
            if(fontName.equals("Lucida Handwriting"))
                return new Font("Lucida Handwriting", Font.BOLD, 25);
        }
        return font;
    }
}
