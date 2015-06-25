import javax.swing.*;
import java.awt.*;


public class RectangleComponent extends JComponent {
    private int posx = 0;
    private int posy = 0;
    private int width = 0;
    private int height = 0;

    public RectangleComponent(int posx, int posy, int width, int height) {
        super();
        this.setLocation(posx, posy);
        this.setSize(width, height);
        this.posx = posx;
        this.posy = posy;

        addMouseListener(PatternMouseAdaptor.getInstance());
        addMouseMotionListener(PatternMouseAdaptor.getInstance());

        System.out.println(posx + " "+ posy+" "+width+" "+height);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle box = new Rectangle(0, 0, this.width-1, this.height-1);
        g2.draw(box);
    }

    @Override
    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    public void compResize(int width, int height) {
        Graphics g = this.getGraphics();
        this.setSize(width, height);
        paintComponents(g);
    }



}