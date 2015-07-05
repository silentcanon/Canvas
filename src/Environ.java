import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Canon on 2015-06-28.
 */
public class Environ {
    private static HashSet<Pattern> selectedPatterns = new HashSet<Pattern>();
    private static HashSet<PatternGroup> selectedPatternGroups = new HashSet<PatternGroup>();
    private static GlassPanel glassPanel = null;

    public static Pattern[] getSelectedPatterns() {
        return (Pattern[])selectedPatterns.toArray(new Pattern[0]);
    }

    public static PatternGroup[] getGroups() {
        return (PatternGroup[])selectedPatternGroups.toArray(new PatternGroup[0]);
    }

    public static void addSelectedPattern(Pattern p) {
        selectedPatterns.add(p);
        System.out.println("Number of selected patterns: "+selectedPatterns.size());
    }

    private static void setSelectedPatterns(Pattern[] patterns) {
        selectedPatterns.clear();
        for(Pattern p: patterns) {
            selectedPatterns.add(p);
        }
    }

    private static void addSelectedPatterns(Pattern[] patterns) {
        for(Pattern p: patterns) {
            selectedPatterns.add(p);
        }
    }

    public static void clearPatterns() {
        selectedPatterns.clear();
    }

    public static void clearGroups() {
        selectedPatternGroups.clear();
    }

    public static void setSelectedPattern(Pattern pattern) {

        selectedPatterns.clear();
        selectedPatterns.add(pattern);
    }

    public static void addSelectedPatternGroup(PatternGroup pg) {
        selectedPatternGroups.add(pg);
    }

    public static void setSelectedPatternGroup(PatternGroup pg) {
        selectedPatternGroups.clear();
        selectedPatternGroups.add(pg);
    }

    public static void clearSelectedpatterns() {
        selectedPatterns.clear();
        selectedPatternGroups.clear();
    }

    public static void ungrouping() {
        //turn all selected groups to patterns
        if(selectedPatternGroups.isEmpty()) {
            return;
        }
        for(PatternGroup patternGroup: selectedPatternGroups) {
            Pattern[] patterns = patternGroup.getPatterns();
            patternGroup.unGroup();
            addSelectedPatterns(patterns);
        }
        selectedPatternGroups.clear();
    }


    public static void grouping() {
        ungrouping();
        Pattern[] patterns = getSelectedPatterns();
        PatternGroup patternGroup = new PatternGroup(patterns);

        setSelectedPatternGroup(patternGroup);
        for(Pattern p: patterns) {
            p.setPatternGroup(patternGroup);
        }
        selectedPatterns.clear();
    }




    public static boolean selectedPatternsContains(Pattern p) {
        return selectedPatterns.contains(p);
    }

    public static boolean selectedGroupsContains(PatternGroup pg) {
        return selectedPatternGroups.contains(pg);
    }

    public static void setGlassPanel(GlassPanel p) {
        glassPanel = p;
    }

    public static GlassPanel getGlassPanel() {
        return glassPanel;
    }
}
