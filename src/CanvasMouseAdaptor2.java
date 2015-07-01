import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Canon on 2015-06-23.
 */
public class CanvasMouseAdaptor2 extends MouseAdapter {
    private static CanvasMouseAdaptor2 instance = new CanvasMouseAdaptor2();

    private Pattern currentRect = null;
    private int startX;
    private int startY;

    private CanvasMouseAdaptor2() {
        super();
    }

    public static CanvasMouseAdaptor2 getInstance() {
        return instance;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.println("Mouse Pos: " + startX + " " + startY);
        if (Setting.getCurrentTool() == Tool.Select) {
            return;
        } else if (Setting.getCurrentTool() == Tool.Rectangle) {
            startX = e.getX();
            startY = e.getY();
            Rectangle rect = new Rectangle(startX, startY, 1, 1);
            GlassPanel glassPanel = Environ.getGlassPanel();
            glassPanel.setShape(rect);
        } else if (Setting.getCurrentTool() == Tool.Oval) {
            startX = e.getX();
            startY = e.getY();
            Ellipse2D oval = new Ellipse2D.Float(startX, startY, 1, 1);
            Environ.getGlassPanel().setShape(oval);
        } else if (Setting.getCurrentTool() == Tool.Line) {
            startX = e.getX();
            startY = e.getY();
            Line2D line = new Line2D.Float(startX, startY, startX, startY);
            Environ.getGlassPanel().setShape(line);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        Tool currentTool = Setting.getCurrentTool();
        if (currentTool == Tool.Select) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        int nx, ny, height, width;
        width = Math.abs(x - startX);
        height = Math.abs(y - startY);
        nx = startX;
        ny = startY;
        if (y < startY)
            ny = y;
        if (x < startX)
            nx = x;
        GlassPanel glassPanel = Environ.getGlassPanel();
        Shape s = null;
        if (currentTool == Tool.Rectangle) {
            if (Setting.getShiftStatus()) {
                //draw square
                int edge = width > height ? width : height;
                s = new Rectangle(nx, ny, edge - 1, edge - 1);
            } else {
                s = new Rectangle(nx, ny, width - 1, height - 1);
            }
        } else if (currentTool == Tool.Oval) {
            if (Setting.getShiftStatus()) {
                //draw circle
                int edge = width > height ? width : height;
                s = new Ellipse2D.Float(nx, ny, edge - 1, edge - 1);
            } else {
                s = new Ellipse2D.Float(nx, ny, width - 1, height - 1);
            }
        } else if (currentTool == Tool.Line) {
            if (Setting.getShiftStatus()) {
                if (height > width) {
                    s = new Line2D.Float(startX, startY, startX, y);
                } else {
                    s = new Line2D.Float(startX, startY, x, startY);
                }
            } else {
                s = new Line2D.Float(startX, startY, x, y);
            }
        }
        glassPanel.setShape(s);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Tool currentTool = Setting.getCurrentTool();
        if (currentTool == Tool.Select) {
            Environ.getGlassPanel().clear();
            Environ.setSelectedPattern(null);
            return;
        }
        int x = e.getX();
        int y = e.getY();
        int nx, ny, height, width;
        width = Math.abs(x - startX);
        height = Math.abs(y - startY);
        nx = startX;
        ny = startY;
        if (y < startY)
            ny = y;
        if (x < startX)
            nx = x;
        Pattern p = null;
        Environ.getGlassPanel().clear();
        if (currentTool == Tool.Rectangle) {
            if (Setting.getShiftStatus()) {
                //draw square
                int edge = width > height ? width : height;
                p = new RectangleComponent(nx, ny, edge, edge);
            } else {
                p = new RectangleComponent(nx, ny, width, height);
            }
        } else if (currentTool == Tool.Oval) {
            if (Setting.getShiftStatus()) {
                //draw circle
                int edge = width > height ? width : height;
                p = new OvalPattern(nx, ny, edge, edge);
            } else {
                p = new OvalPattern(nx, ny, width, height);
            }
        } else if (currentTool == Tool.Line) {
            if (Setting.getShiftStatus()) {
                if (height > width) {
                    p = new LinePattern(nx, ny, width, height, startX, startY, startX, y);
                } else {
                    p = new LinePattern(nx, ny, width, height, startX, startY, x, startY);
                }
            } else {
                p = new LinePattern(nx, ny, width, height, startX, startY, x, y);
            }
        }
        ((JLayeredPane) e.getSource()).add(p, Setting.getLayer());

    }


//    @Override
//    public void mouseMoved(MouseEvent e) {
//        super.mouseMoved(e);
//        int x = e.getX();
//        int y = e.getY();
//        System.out.println(x + " " + y);
//    }


}
