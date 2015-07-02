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
//            Pattern currentPattern = ((Pattern)e.getSource());
//            Environ.getGlassPanel().drawBorder(currentPattern.getX(),currentPattern.getY(),
//                    currentPattern.getWidth(),currentPattern.getHeight());
            //Component source = (Component) e.getSource();
            //Container parent = source.getParent();
            //parent.remove(source);
            //parent.repaint();
        } else if(Setting.getCurrentTool() == Tool.MultiSelect) {
//            Pattern currentPattern = ((Pattern)e.getSource());
//            Environ
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if((Setting.getCurrentTool() != Tool.Select) && (Setting.getCurrentTool() != Tool.MultiSelect)) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Press Send event");
        } else if(Setting.getCurrentTool() == Tool.Select){
            Pattern currentPattern = ((Pattern)e.getSource());
            Environ.setSelectedPattern(currentPattern);
            Environ.getGlassPanel().drawBorder(currentPattern.getX(),currentPattern.getY(),
                    currentPattern.getWidth(),currentPattern.getHeight());
            oldX = currentPattern.getX();
            oldY = currentPattern.getY();
            clickedX = e.getX();
            clickedY = e.getY();
            System.out.println("Mouse Pressed in pattern");
        } else if(Setting.getCurrentTool() == Tool.MultiSelect) {
            Pattern currentPattern = ((Pattern)e.getSource());
            Environ.addSelectedPattern(currentPattern);
            GlassPanel glassPanel = Environ.getGlassPanel();

            Environ.getGlassPanel().drawBorder(currentPattern.getX(),currentPattern.getY(),
                    currentPattern.getWidth(),currentPattern.getHeight());

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(Setting.getCurrentTool() != Tool.Select && Setting.getCurrentTool() != Tool.MultiSelect) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Drag Send event");
        } else {
            int dx = e.getX() - clickedX;
            int dy = e.getY() - clickedY;
            Pattern[] currentPatterns = Environ.getSelectedPatterns();
            Pattern currentPattern = currentPatterns[0];
            currentPattern.moveDelta(dx,dy);
            currentPattern.repaint();
            Environ.getGlassPanel().setShape(new Rectangle(
                    currentPattern.getX(),currentPattern.getY(),
                    currentPattern.getWidth(),currentPattern.getHeight()
            ));
            System.out.println("Mouse Dragged in pattern");
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(Setting.getCurrentTool() != Tool.Select && Setting.getCurrentTool() != Tool.MultiSelect) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component) e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
            System.out.println("Release Send event");
        } else {
            //Pattern currentPattern = Environ.getSelectedPattern();
            //Environ.getGlassPanel().clear();
            //currentPattern = null;

        }

    }
}
