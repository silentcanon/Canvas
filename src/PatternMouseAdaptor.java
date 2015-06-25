import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Canon on 2015-06-24.
 */
public class PatternMouseAdaptor extends MouseAdapter {
    private Setting setting = Setting.getInstance();
    private static PatternMouseAdaptor instance = new PatternMouseAdaptor();
    private RectangleComponent currentRect = null;
    private int oldX = 0;
    private int oldY = 0;
    private int clickedX = 0;
    private int clickedY = 0;
    private PatternMouseAdaptor(){
        super();
    }

    public static PatternMouseAdaptor getInstance() {
        return instance;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        System.out.println("Mouse Clicked in pattern");
        Component source = (Component) e.getSource();
        Container parent = source.getParent();
        parent.remove(source);
        parent.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(setting.getCurrentTool() != Tool.Select) {

            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Press Send event");
        } else {
            currentRect = ((RectangleComponent)e.getSource());
            oldX = currentRect.getX();
            oldY = currentRect.getY();
            clickedX = e.getX();
            clickedY = e.getY();
            System.out.println("Mouse Pressed in pattern");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(setting.getCurrentTool() != Tool.Select) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Drag Send event");
        } else {
            int newX = oldX + (e.getX() - clickedX);
            int newY = oldY + (e.getY() - clickedY);
            oldX = newX;
            oldY = newY;
            currentRect.setLocation(newX, newY);
            currentRect.repaint();
            System.out.println("Mouse Dragged in pattern");
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        currentRect = null;

    }
}
