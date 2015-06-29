/**
 * Created by Canon on 2015-06-28.
 */
public class Environ {
    private static Pattern selectedPattern = null;
    private static GlassPanel glassPanel = null;

    public static Pattern getSelectedPattern() {
        return selectedPattern;
    }

    public static void setSelectedPattern(Pattern pattern) {
        selectedPattern = pattern;
    }

    public static void setGlassPanel(GlassPanel p) {
        glassPanel = p;
    }

    public static GlassPanel getGlassPanel() {
        return glassPanel;
    }
}
