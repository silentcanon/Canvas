import java.util.HashSet;

/**
 * Created by Canon on 2015-06-30.
 */
public class PatternGroup {
    private HashSet<Pattern> group = new HashSet<Pattern>();
    int lx,ly;
    int rx,ry;

    public PatternGroup(Pattern[] patterns) {
        Pattern p = patterns[0];
        lx = p.getX();
        ly = p.getY();
        rx = p.getX() + p.getWidth();
        ry = p.getY() + p.getHeight();
        int ptrx, ptry;
        for(Pattern pt: patterns) {
            this.add(pt);
            lx =lx<pt.getX()?lx:pt.getX();
            ly =ly<pt.getY()?ly:pt.getY();
            ptrx = pt.getX() + pt.getWidth();
            ptry = pt.getY() + pt.getHeight();
            rx = rx>ptrx?rx:ptrx;
            ry = ry>ptry?ry:ptry;
        }
    }


    public void add(Pattern pattern) {
        this.group.add(pattern);
    }

    public void unGroup() {
        for(Pattern pattern: group) {
            pattern.clearGroup();
            group.clear();
        }
    }

    public int size() {
        return this.group.size();
    }
    
    public int getLX() {
        return lx;
    }
    
    public int getRX() {
        return rx;
    }

    public int getLY() {
        return ly;
    }

    public int getRY() {
        return ry;
    }

}
