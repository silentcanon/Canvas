import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Canon on 2015-07-05.
 */
public class SaveAndLoadAction extends AbstractAction {
    private JFileChooser fc;
    private JFrame parent;
    private JLayeredPane canvas;
    public SaveAndLoadAction(JFrame parent, JLayeredPane canvas) {
        super();
        fc = new JFileChooser();
        this.parent = parent;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command  = ((JButton)e.getSource()).getActionCommand();
        if("save".equals(command)) {
            Component[] cs = canvas.getComponents();
            ArrayList<Pattern> patterns = new ArrayList<>();
            for(Component c: cs) {
                if(c instanceof Pattern) {
                    patterns.add((Pattern)c);
                }
            }
            System.out.println("There are altogether "+ patterns.size()+ " patterns");
            int returnValue = fc.showSaveDialog(parent);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                SaverAndLoader.saveAction(file);
            }

        } else if("open".equals(command)) {
            int returnValue = fc.showOpenDialog(parent);
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                SaverAndLoader.openAction(file);
                canvas.repaint();
            }

        }
    }
}
