import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class RectanglePattern extends Pattern {


    public RectanglePattern(int posx, int posy, int width, int height) {
        super();
        this.setLocation(posx, posy);
        this.setSize(width, height);
        System.out.println("Rect: "+posx + " " + posy + " " + width + " " + height);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle box = new Rectangle(0, 0, getWidth()-1, getHeight()-1);
        g2.draw(box);
    }


    @Override
    protected BufferedImage getBufImg()
    {
        BufferedImage newImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.draw(new Rectangle(0,0,getWidth()-1,getHeight()-1));
        g2d.dispose();
        return newImg;
    }

    @Override
    public Pattern clone() {
        RectanglePattern newP = new RectanglePattern(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        newP.moveDelta(10,10);
        return newP;
    }







}