/**
 * Created by Canon on 2015-06-28.
 */
public class Environ {
    private static Pattern selectedPattern = null;

    public static Pattern getSelectedPattern() {
        return selectedPattern;
    }

    public static void setSelectedPattern(Pattern pattern) {
        selectedPattern = pattern;
    }
}
