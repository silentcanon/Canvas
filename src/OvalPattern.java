import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Canon on 2015-06-26.
 */
public class OvalPattern extends Pattern{

    public OvalPattern(int x, int y, int width, int height) {
        super();
        setSize(width,height);
        setLocation(x, y);
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

    @Override
    public Pattern clone() {
        OvalPattern newP = new OvalPattern(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        newP.moveDelta(10,10);
        return newP;
    }


}
