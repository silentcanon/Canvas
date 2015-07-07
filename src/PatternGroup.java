import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Canon on 2015-06-30.
 */
public class PatternGroup implements Serializable{
    private HashSet<Pattern> group = new HashSet<Pattern>();
    int lx = 0;
    int ly = 0;
    int rx = 0;
    int ry = 0;

    public PatternGroup(Pattern[] patterns) {
        if(patterns.length == 0) {
            return;
        }
        Pattern p = patterns[0];
        lx = p.getX();
        ly = p.getY();
        rx = p.getX() + p.getWidth();
        ry = p.getY() + p.getHeight();
        int ptrx, ptry;
        for(Pattern pt: patterns) {
            group.add(pt);
            lx =lx<pt.getX()?lx:pt.getX();
            ly =ly<pt.getY()?ly:pt.getY();
            ptrx = pt.getX() + pt.getWidth();
            ptry = pt.getY() + pt.getHeight();
            rx = rx>ptrx?rx:ptrx;
            ry = ry>ptry?ry:ptry;
        }
    }

    public Pattern[] getPatterns() {
        if(group.isEmpty()) {
            return new Pattern[0];
        }
        return (Pattern[])group.toArray(new Pattern[0]);
    }

    public void unGroup() {
        for(Pattern pattern: group) {
            pattern.clearGroup();
        }
        group.clear();
    }

    public void addPattern(Pattern pt) {
        group.add(pt);
        lx =lx<pt.getX()?lx:pt.getX();
        ly =ly<pt.getY()?ly:pt.getY();
        int ptrx = pt.getX() + pt.getWidth();
        int ptry = pt.getY() + pt.getHeight();
        rx = rx>ptrx?rx:ptrx;
        ry = ry>ptry?ry:ptry;
    }

    public void addPatterns(Pattern[] ps) {
        for(Pattern pt: ps) {
            addPattern(pt);
        }
    }

    public void moveDelta(int dx, int dy) {
        if(group.isEmpty()){
            return;
        }
        lx += dx;
        rx += dx;
        ly += dy;
        ry += dy;
        for(Pattern p: group) {
            p.moveDelta(dx,dy);
        }
    }

    public int size() {
        return this.group.size();
    }
    
    private int getLX() {
        return lx;
    }

    private int getRX() {
        return rx;
    }

    private int getLY() {
        return ly;
    }

    private int getRY() {
        return ry;
    }

    public int getX() {
        return getLX()-1;
    }

    public int getY() {
        return getLY()-1;
    }

    public int getWidth() {
        return getRX() - getLX()+2;
    }

    public int getHeight() {
        return getRY() - getLY()+2;
    }

}
