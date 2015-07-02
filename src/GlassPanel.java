import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Canon on 2015-06-28.
 */
public class GlassPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Color color = Color.GREEN;

    public GlassPanel() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(color);
        if(!shapes.isEmpty()) {
            for(Shape shape: shapes) {
                g2d.draw(shape);
            }
        } else {
            g2d.draw(new Rectangle(-1,-1,getWidth()+1,getHeight()+1));
        }
    }

    public void addShape(Shape s) {
        shapes.add(s);
        repaint();
    }

    public void setShape(Shape s) {
        clear();
        shapes.add(s);
        repaint();
    }


    public void setBorder(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x,y,width-1,height-1);
        color = Color.GREEN;
        setShape(rect);
        //color = color.BLACK;
    }

    public void addBorder(int x, int y, int width, int height) {
        Rectangle rect = new Rectangle(x,y,width-1,height-1);
        color = Color.GREEN;
        addShape(rect);
    }

    public void clear() {
        shapes.clear();
        repaint();
    }



}
