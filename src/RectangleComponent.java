import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RectangleComponent extends JComponent {


    public RectangleComponent(int posx, int posy, int width, int height) {
        super();
        this.setLocation(posx, posy);
        this.setSize(width, height);

        addMouseListener(PatternMouseAdaptor.getInstance());
        addMouseMotionListener(PatternMouseAdaptor.getInstance());

        System.out.println(posx + " " + posy + " " + width + " " + height);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle box = new Rectangle(0, 0, getWidth()-1, getHeight()-1);
        g2.draw(box);
    }


    private int clickAlphaValue(BufferedImage bufImg, int posX, int posY) {
        int alpha;
        alpha = (bufImg.getRGB(posX, posY) >>24) & 0x000000FF; //Gets the bit that contains alpha information
        return alpha;
    }

    private BufferedImage getBufImg()
    {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.draw(new Rectangle(0,0,getWidth()-1,getHeight()-1));
        g2d.dispose();
        return newImg;
    }

    public boolean isHit(int posx, int posy) {
        BufferedImage im = getBufImg();
        for(int x = posx - 3; x <= posx+3;x++) {
            if(x <0 || x > getWidth())
                continue;
            for(int y = posy - 3;y <= posy+3; y++) {
                if(y < 0 || y > getHeight())
                    continue;
                if(clickAlphaValue(im, x, y) > 0)// is not transparent
                    return true;
            }
        }
        return false;
    }





}