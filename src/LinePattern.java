import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by noMoon on 2015-06-30.
 */
public class LinePattern extends Pattern{

    int startX;
    int startY;
    int endX;
    int endY;

    public LinePattern(int x, int y, int width, int height,int startX,int startY,int endX,int endY) {
        super();
        setSize(width,height);
        setLocation(x, y);
        addMouseListener(PatternMouseAdaptor.getInstance());
        addMouseMotionListener(PatternMouseAdaptor.getInstance());
        this.startX=startX-getX();
        this.startY=startY-getY();
        this.endX=endX-getX();
        this.endY=endY-getY();

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(startX, startY, endX, endY);
    }

    @Override
    protected BufferedImage getBufImg() {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawLine(startX, startY, endX, endY);
        g2d.dispose();
        return newImg;
    }
}
