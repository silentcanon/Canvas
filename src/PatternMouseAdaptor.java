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

    private int clickedX = 0;
    private int clickedY = 0;
    private boolean drag;
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
            ;
        } else if(Setting.getCurrentTool() == Tool.MultiSelect) {
            ;
        } else {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drag = false;
        super.mousePressed(e);
        clickedX = e.getX();
        clickedY = e.getY();
        GlassPanel glassPanel = Environ.getGlassPanel();
        Pattern currentPattern = ((Pattern)e.getSource());
        if((Setting.getCurrentTool() != Tool.Select) && (Setting.getCurrentTool() != Tool.MultiSelect)) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
        } else if(Setting.getCurrentTool() == Tool.Select){
            if(Environ.selectedPatternsContains(currentPattern)) {
                return;
            }
            if(currentPattern.isInGroup()) {
                PatternGroup patternGroup = currentPattern.getPatternGroup();
                if(Environ.selectedGroupsContains(patternGroup)) {
                    return;
                }
                Environ.clearSelectedpatterns();
                Environ.setSelectedPatternGroup(patternGroup);

                glassPanel.setBorder(patternGroup.getX(), patternGroup.getY(),
                        patternGroup.getWidth(), patternGroup.getHeight());
            } else {//Pattern is not in a pattern group
                Environ.clearSelectedpatterns();
                Environ.setSelectedPattern(currentPattern);
                glassPanel.setBorder(currentPattern.getX(), currentPattern.getY(),
                        currentPattern.getWidth(), currentPattern.getHeight());
            }
        } else if(Setting.getCurrentTool() == Tool.MultiSelect) {
            if(currentPattern.isInGroup()) {
                PatternGroup patternGroup = currentPattern.getPatternGroup();
                Environ.addSelectedPatternGroup(patternGroup);
                glassPanel.addBorder(patternGroup.getX(),patternGroup.getY(),
                        patternGroup.getWidth(),patternGroup.getHeight());
            } else {
                Environ.addSelectedPattern(currentPattern);
                glassPanel.addBorder(currentPattern.getX(), currentPattern.getY(),
                        currentPattern.getWidth(), currentPattern.getHeight());
            }

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drag = true;
        super.mouseDragged(e);
        if(Setting.getCurrentTool() != Tool.Select && Setting.getCurrentTool() != Tool.MultiSelect) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component)e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
        } else {

            int dx = e.getX() - clickedX;
            int dy = e.getY() - clickedY;
            Pattern[] currentPatterns = Environ.getSelectedPatterns();
            PatternGroup[] currentGroups = Environ.getGroups();
            GlassPanel glassPanel = Environ.getGlassPanel();
            glassPanel.clear();
            for(Pattern p: currentPatterns) {
                p.moveDelta(dx, dy);
                p.repaint();
                glassPanel.addBorder(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }
            for(PatternGroup pg: currentGroups) {
                pg.moveDelta(dx, dy);
                glassPanel.addBorder(pg.getX(),pg.getY(),pg.getWidth(),pg.getHeight());
            }
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(Setting.getCurrentTool() != Tool.Select && Setting.getCurrentTool() != Tool.MultiSelect) {
            MouseEvent e1 = SwingUtilities.convertMouseEvent((Component) e.getSource(), e, ((Component) e.getSource()).getParent());
            ((Component) e.getSource()).getParent().dispatchEvent(e1);
        } else {
            //finishing moving
            if(drag) {
                History.addRecord();
            }
        }

    }
}
