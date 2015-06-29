import javax.swing.*;
import java.awt.*;

/**
 * Created by Canon on 2015-06-28.
 */
public class GlassPanel extends JPanel {
    private Shape shape = null;
    private Color color = Color.GREEN;

    public GlassPanel() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(color);
        if(shape != null) {
            g2d.draw(shape);
        } else {
            g2d.draw(new Rectangle(-1,-1,getWidth()+1,getHeight()+1));
        }
    }

    public void setShape(Shape s) {
        shape = s;
        repaint();
    }


    public void drawBorder(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x,y,width-1,height-1);
        color = Color.GREEN;
        setShape(rect);
        //color = color.BLACK;
    }

    public void clear() {
        shape = null;
        repaint();
    }



}
