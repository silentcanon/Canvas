import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Canon on 2015-06-23.
 */
public class CanvasMouseAdaptor extends MouseAdapter {
    private static CanvasMouseAdaptor instance = new CanvasMouseAdaptor();

    private RectangleComponent currentRect = null;
    private int startX;
    private int startY;

    private CanvasMouseAdaptor() {
        super();
    }

    public static CanvasMouseAdaptor getInstance() {
        return instance;
    }




    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseClicked(e);
        startX = e.getX();
        startY = e.getY();
        System.out.println("Mouse Pos: "+startX+" "+startY);
        RectangleComponent rectangle = new RectangleComponent(startX,startY,1,1);
        this.currentRect = rectangle;
        JLayeredPane source = (JLayeredPane) e.getSource();

        source.add(rectangle, Setting.getLayer());
        source.repaint();
        //jFrame.update(jFrame.getGraphics());
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int x = e.getX();
        int y = e.getY();
        //System.out.println(x + " " + y);
        if(this.currentRect != null) {
            this.currentRect.compResize(x - startX, y - startY);
            ((JComponent) e.getSource()).repaint();
        }

    }

//    @Override
//    public void mouseMoved(MouseEvent e) {
//        super.mouseMoved(e);
//        int x = e.getX();
//        int y = e.getY();
//        System.out.println(x + " " + y);
//    }



}
