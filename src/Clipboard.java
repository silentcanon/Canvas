import javax.swing.*;
import java.util.HashSet;

/**
 * Created by Canon on 2015-07-08.
 */
public class Clipboard {
    private static Pattern[] copiedPatterns;
    private static PatternGroup[] copiedGroups;
    private static JLayeredPane canvas;

    private Clipboard() {

    }

    public static void init(JLayeredPane c) {
        canvas = c;
    }

    public static void copy() {
        copiedGroups = Environ.getGroups();
        copiedPatterns = Environ.getSelectedPatterns();
    }

    public static void paste() {
        Environ.clearGroups();
        Environ.clearPatterns();
        GlassPanel gp = Environ.getGlassPanel();
        gp.clear();
        for(Pattern p: copiedPatterns) {
            Pattern cp = (Pattern) p.clone();
            cp.clearGroup();
            canvas.add(cp);
            Environ.addSelectedPattern(cp);
            gp.addBorder(cp.getX(),cp.getY(),cp.getWidth(),cp.getHeight());
        }

        for(PatternGroup patternGroup: copiedGroups) {
            PatternGroup newGroup = patternGroup.clone();
            Environ.addSelectedPatternGroup(newGroup);
            for(Pattern p: newGroup.getPatterns()) {
                canvas.add(p);
            }
            gp.addBorder(newGroup.getX(),newGroup.getY(),newGroup.getWidth(),newGroup.getHeight());
        }

    }
}
