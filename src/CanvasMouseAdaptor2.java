import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Canon on 2015-06-23.
 */
public class CanvasMouseAdaptor2 extends MouseAdapter {
    private static CanvasMouseAdaptor2 instance = new CanvasMouseAdaptor2();

    private Pattern currentRect = null;
    private int startX;
    private int startY;
    private Path2D path;
    private Path2D polygon = null;

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
        startX = e.getX();
        startY = e.getY();
        if (Setting.getCurrentTool() == Tool.Select) {
            return;
        } else if (Setting.getCurrentTool() == Tool.Rectangle) {
            Rectangle rect = new Rectangle(startX, startY, 1, 1);
            GlassPanel glassPanel = Environ.getGlassPanel();
            glassPanel.setShape(rect);
        } else if (Setting.getCurrentTool() == Tool.Oval) {
            Ellipse2D oval = new Ellipse2D.Float(startX, startY, 1, 1);
            Environ.getGlassPanel().setShape(oval);
        } else if (Setting.getCurrentTool() == Tool.Line) {
            Line2D line = new Line2D.Float(startX, startY, startX, startY);
            Environ.getGlassPanel().setShape(line);
        } else if (Setting.getCurrentTool() == Tool.Pencil) {
            path = new Path2D.Double();
            path.moveTo(startX, startY);
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
        } else if (currentTool == Tool.Pencil) {
            path.lineTo(e.getX(), e.getY());
            s = path;
        }
        glassPanel.setShape(s);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Tool currentTool = Setting.getCurrentTool();
        if (currentTool == Tool.Select || currentTool == Tool.MultiSelect) {
            Environ.getGlassPanel().clear();
            Environ.clearSelectedpatterns();
            return;
        }
        if (currentTool == Tool.OpenPolygon || currentTool == Tool.ClosedPolygon) {
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
        } else if (currentTool == Tool.Pencil) {
            p = new PathPattern(path);
        }
        ((JLayeredPane) e.getSource()).add(p, Setting.getLayer());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getClickCount() == 2) {
            //Double Click
            if (Setting.getCurrentTool() == Tool.OpenPolygon || Setting.getCurrentTool() == Tool.ClosedPolygon) {
                Environ.getGlassPanel().clear();
                if(Setting.getCurrentTool()==Tool.ClosedPolygon){
                    try{
                        polygon.closePath();
                    }catch(Exception ecp){
                        System.out.println(ecp.getMessage());
                    }
                }
                Pattern p = new PolygonPattern(polygon);
                ((JLayeredPane) e.getSource()).add(p, Setting.getLayer());
                polygon = null;
            }
        } else if (e.getClickCount() == 1) {
            //Single Click
            if (Setting.getCurrentTool() == Tool.OpenPolygon || Setting.getCurrentTool() == Tool.ClosedPolygon) {
                GlassPanel glassPanel = Environ.getGlassPanel();
                if (null == polygon) {
                    polygon = new Path2D.Double();
                    startX = e.getX();
                    startY = e.getY();
                    polygon.moveTo(startX, startY);
                } else {
                    polygon.lineTo(e.getX(), e.getY());
                    glassPanel.setShape(polygon);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if ((Setting.getCurrentTool() == Tool.OpenPolygon || Setting.getCurrentTool() == Tool.ClosedPolygon) && polygon != null) {
            Path2D tempPath = (Path2D) polygon.clone();
            tempPath.lineTo(e.getX(), e.getY());
            GlassPanel glassPanel = Environ.getGlassPanel();
            glassPanel.setShape(tempPath);
        }
    }


}
