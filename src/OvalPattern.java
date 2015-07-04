import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Canon on 2015-06-26.
 */
public class OvalPattern extends Pattern{
    private int a;
    private int b;
    private int c;
    private int fx1,fy1;
    private int fx2,fy2;
    private int bx,by;
    private boolean drawHelpLine = true;


    public OvalPattern(int fx1, int fy1, int fx2, int fy2, int bx, int by) {
        super();

    }

    public OvalPattern(int x, int y, int width, int height) {
        super();
        setSize(width,height);
        setLocation(x, y);
    }

    private void init(int fx1, int fy1, int fx2, int fy2, int bx, int by) {
        setF1(fx1,fy1);
        setF2(fx2,fy2);
        setBound(bx, by);

    }

    private void updateABC() {
        double p1 = distance(fx1,fy1,bx,by);
        double p2 = distance(fx2,fy2,bx,by);
        double cf = distance(fx1,fy1,fx2,fy2);

    }

    private double distance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setF1(int x, int y) {
        fx1 = x;
        fy1 = y;
    }

    public void setF2(int x, int y) {
        fx2 = x;
        fy2 = y;
    }

    public void setBound(int x, int y) {
        bx = x;
        by = y;
    }




    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawOval(0,0,getWidth()-1,getHeight()-1);
    }

    @Override
    protected BufferedImage getBufImg() {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.dispose();
        return newImg;
    }


}
