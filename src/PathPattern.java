import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

/**
 * Created by noMoon on 2015-07-01.
 */
public class PathPattern extends Pattern {

    private Path2D path;

    public PathPattern(Path2D path){
        super();
        setLocation((int) path.getBounds().getX(), (int) path.getBounds().getY());
        setSize(path.getBounds().width+1,path.getBounds().height+1);
        this.path=(Path2D)path.createTransformedShape(new AffineTransform(1, 0, 0, 1, 0 - getX(), 0 - getY()));
    }

    private PathPattern() {
        super();
    }

    private void setPath(Path2D path) {
        this.path = path;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(path);
    }

    @Override
    protected BufferedImage getBufImg() {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.draw(path);
        g2d.dispose();
        return newImg;
    }

    @Override
    public Pattern clone() {
        PathPattern newP = new PathPattern();
        newP.setLocation(this.getX(), this.getY());
        newP.setSize(this.getWidth(), this.getHeight());
        newP.setPath(this.path);
        newP.moveDelta(10,10);
        return newP;
    }


}
