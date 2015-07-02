import java.util.ArrayList;

/**
 * Created by Canon on 2015-06-28.
 */
public class Environ {
    private static ArrayList<Pattern> selectedPatterns = new ArrayList<Pattern>();
    private static GlassPanel glassPanel = null;

    public static Pattern[] getSelectedPatterns() {
        return (Pattern[])selectedPatterns.toArray(new Pattern[0]);
    }

    public static void addSelectedPattern(Pattern p) {
        selectedPatterns.add(p);
    }

    public static void setSelectedPattern(Pattern pattern) {
        selectedPatterns.clear();
        selectedPatterns.add(pattern);
    }

    public static void clearSelectedpatterns() {
        selectedPatterns.clear();
    }

    public static void setGlassPanel(GlassPanel p) {
        glassPanel = p;
    }

    public static GlassPanel getGlassPanel() {
        return glassPanel;
    }
}
