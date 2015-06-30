import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by noMoon on 2015-06-30.
 */
public class LinePattern extends Pattern{

    public LinePattern(int x, int y, int width, int height) {
        super();
        setSize(width,height);
        setLocation(x, y);
        addMouseListener(PatternMouseAdaptor.getInstance());
        addMouseMotionListener(PatternMouseAdaptor.getInstance());

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(0,0,getWidth()-1,getHeight()-1);
    }

    @Override
    protected BufferedImage getBufImg() {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawLine(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.dispose();
        return newImg;
    }
}
