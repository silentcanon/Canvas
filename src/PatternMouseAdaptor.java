import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Canon on 2015-06-24.
 */
public class PatternMouseAdaptor extends MouseAdapter {
    private static PatternMouseAdaptor instance = new PatternMouseAdaptor();

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
        if(Setting.getCurrentTool() == Tool.Select) {
            System.out.println("Pattern is selected");
            //Component source = (Component) e.getSource();
            //Container parent = source.getParent();
            //parent.remove(source);
            //parent.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(Setting.getCurrentTool() != Tool.Select) {

            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Press Send event");
        } else {
            Pattern currentPattern = ((Pattern)e.getSource());
            Environ.setSelectedPattern(currentPattern);
            oldX = currentPattern.getX();
            oldY = currentPattern.getY();
            clickedX = e.getX();
            clickedY = e.getY();
            System.out.println("Mouse Pressed in pattern");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(Setting.getCurrentTool() != Tool.Select) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Drag Send event");
        } else {
            int newX = oldX + (e.getX() - clickedX);
            int newY = oldY + (e.getY() - clickedY);
            oldX = newX;
            oldY = newY;
            Pattern currentPattern = Environ.getSelectedPattern();
            currentPattern.setLocation(newX, newY);
            currentPattern.repaint();
            System.out.println("Mouse Dragged in pattern");
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Pattern currentPattern = Environ.getSelectedPattern();
        currentPattern = null;

    }
}
