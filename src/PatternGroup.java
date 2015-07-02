import java.util.ArrayList;

/**
 * Created by Canon on 2015-06-30.
 */
public class PatternGroup {
    private ArrayList<Pattern> group = new ArrayList<Pattern>();

    public PatternGroup(Pattern pattern) {
        this.add(pattern);
    }


    public void add(Pattern pattern) {
        this.group.add(pattern);
    }

    public void unGroup() {
        for(Pattern pattern: group) {
            pattern.clearGroup();
        }
    }

    public int size() {
        return this.group.size();
    }

}
