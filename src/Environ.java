import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Canon on 2015-06-28.
 */
public class Environ {
    private static HashSet<Pattern> selectedPatterns = new HashSet<Pattern>();
    private static GlassPanel glassPanel = null;

    public static Pattern[] getSelectedPatterns() {
        return (Pattern[])selectedPatterns.toArray(new Pattern[0]);
    }

    public static void addSelectedPattern(Pattern p) {
        selectedPatterns.add(p);
        System.out.println("Number of selected patterns: "+selectedPatterns.size());
    }

    public static void setSelectedPattern(Pattern pattern) {

        selectedPatterns.clear();
        selectedPatterns.add(pattern);
    }

    public static void clearSelectedpatterns() {
        selectedPatterns.clear();
    }

    public static boolean selectedPatternsContains(Pattern p) {
        return selectedPatterns.contains(p);
    }

    public static void setGlassPanel(GlassPanel p) {
        glassPanel = p;
    }

    public static GlassPanel getGlassPanel() {
        return glassPanel;
    }
}
