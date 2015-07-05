import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by Canon on 2015-06-25.
 */
public abstract class Pattern extends JComponent{
    protected PatternGroup patternGroup;

    public Pattern() {
        super();
        this.patternGroup = null;
        addMouseListener(PatternMouseAdaptor.getInstance());
        addMouseMotionListener(PatternMouseAdaptor.getInstance());
    }

    public PatternGroup getPatternGroup() {
        return this.patternGroup;
    }

    public void clearGroup() {
        this.patternGroup = null;
    }

    public boolean isInGroup() {
        return !(patternGroup == null);
    }

    public void setPatternGroup(PatternGroup patternGroup) {
        this.patternGroup = patternGroup;
    }


    public void moveDelta(int dx, int dy) {
        setLocation(getX()+dx,getY()+dy);
        repaint();
    }

    protected abstract BufferedImage getBufImg();

    public boolean isHit(int posx, int posy) {
        BufferedImage im = getBufImg();
        for(int x = posx - 3; x <= posx+3;x++) {
            if(x <0 || x >= getWidth())
                continue;
            for(int y = posy - 3;y <= posy+3; y++) {
                if(y < 0 || y >= getHeight())
                    continue;
                if(clickAlphaValue(im, x, y) > 0)// is not transparent
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(int x, int y) {
        if (super.contains(x,y)) {
            return isHit(x,y);
        }
        return false;
    }

    protected int clickAlphaValue(BufferedImage bufImg, int posX, int posY) {
        int alpha;
        alpha = (bufImg.getRGB(posX, posY) >>24) & 0x000000FF; //Gets the bit that contains alpha information
        return alpha;
    }

}
