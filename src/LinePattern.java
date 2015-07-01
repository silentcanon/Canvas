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
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(startX-getX(), startY-getY(), endX-getX(), endY-getY());
    }

    @Override
    protected BufferedImage getBufImg() {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        System.out.println(startX-getX());
        System.out.println(startY-getY());
        System.out.println(endX-getX());
        System.out.println(endY-getY());
        g2d.drawLine(startX-getX(), startY-getY(), endX-getX(), endY-getY());
        g2d.dispose();
        return newImg;
    }
}
