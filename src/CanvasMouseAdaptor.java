import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Canon on 2015-06-23.
 */
public class CanvasMouseAdaptor extends MouseAdapter {
    private static CanvasMouseAdaptor instance = new CanvasMouseAdaptor();

    private Pattern currentRect = null;
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
        if (Setting.getCurrentTool() == Tool.Select) {
            return;
        } else if (Setting.getCurrentTool() == Tool.Rectangle) {
            startX = e.getX();
            startY = e.getY();
            System.out.println("Mouse Pos: " + startX + " " + startY);
            RectanglePattern rectangle = new RectanglePattern(startX, startY, 1, 1);
            this.currentRect = rectangle;
            JLayeredPane source = (JLayeredPane) e.getSource();

            source.add(rectangle, new Integer(Setting.getLayer()));
            source.repaint();
            //jFrame.update(jFrame.getGraphics());}
        } else if(Setting.getCurrentTool() == Tool.Oval) {
            startX = e.getX();
            startY = e.getY();
            System.out.println("Mouse Pos: " + startX + " " + startY);
            OvalPattern ovalPattern = new OvalPattern(startX, startY, 1, 1);
            this.currentRect = ovalPattern;
            JLayeredPane source = (JLayeredPane) e.getSource();

            source.add(ovalPattern, new Integer(Setting.getLayer()));
            source.repaint();
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (Setting.getCurrentTool() == Tool.Select) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        if(this.currentRect != null) {
            int height=y-startY;
            if(y-startY<0){
                height=startY-y;
                this.currentRect.setLocation(this.currentRect.getX(),y);
            }
            int width=x-startX;
            if(x-startX<0){
                width=startX-x;
                this.currentRect.setLocation(x,this.currentRect.getY());
            }
            currentRect.setSize(width,height);
            currentRect.repaint();
            if((x-startX<0)&&(y-startY<0)){
                this.currentRect.setLocation(x,y);
            }
            if((x-startX>0)&&(y-startY>0)){
                this.currentRect.setLocation(startX,startY);
            }

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
