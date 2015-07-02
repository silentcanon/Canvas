import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.Border;

public class FrameViewer extends JFrame{
    private JLabel label;
    private JLayeredPane canvas;
    private final int WIDTH = 300;
    private final int HEIGHT = 400;


    public FrameViewer() {
        super();

        setSize(this.WIDTH, this.HEIGHT);
        setTitle("Canvas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("Nothing");
        add(label);
        label.setSize(100, 20);
        label.setLocation(20,10);
        ButtonClickedEvent buttonClickedEvent = new ButtonClickedEvent();

        JButton selectButton = new JButton(Tool.Select.getName());
        selectButton.setActionCommand(Tool.Select.getName());
        selectButton.addActionListener(buttonClickedEvent);
        add(selectButton);
        selectButton.setSize(100, 20);
        selectButton.setLocation(20, 40);

        JButton rectangleButton = new JButton(Tool.Rectangle.getName());
        rectangleButton.setActionCommand(Tool.Rectangle.getName());
        rectangleButton.addActionListener(buttonClickedEvent);
        add(rectangleButton);
        rectangleButton.setSize(100, 20);
        rectangleButton.setLocation(20, 60);


        JButton ovalButton = new JButton(Tool.Oval.getName());
        ovalButton.setActionCommand(Tool.Oval.getName());
        ovalButton.addActionListener(buttonClickedEvent);
        add(ovalButton);
        ovalButton.setSize(100, 20);
        ovalButton.setLocation(20, 80);

        JButton lineButton = new JButton(Tool.Line.getName());
        lineButton.setActionCommand(Tool.Line.getName());
        lineButton.addActionListener(buttonClickedEvent);
        add(lineButton);
        lineButton.setSize(100, 20);
        lineButton.setLocation(20, 100);

        JButton pencilButton = new JButton(Tool.Pencil.getName());
        pencilButton.setActionCommand(Tool.Pencil.getName());
        pencilButton.addActionListener(buttonClickedEvent);
        add(pencilButton);
        pencilButton.setSize(100, 20);
        pencilButton.setLocation(20, 120);

        JButton combineButton = new JButton("Combine");
        combineButton.setActionCommand(this.getName());
        combineButton.addActionListener(buttonClickedEvent);
        add(combineButton);
        combineButton.setSize(100, 20);
        combineButton.setLocation(120, 40);

        JButton unbineButton = new JButton("Unbine");
        unbineButton.setActionCommand(this.getName());
        unbineButton.addActionListener(buttonClickedEvent);
        add(unbineButton);
        unbineButton.setSize(100, 20);
        unbineButton.setLocation(120, 60);

        canvas = new JLayeredPane();
        add(canvas);
        canvas.setSize(300, 400);
        canvas.setLocation(0, 100);

        canvas.addMouseListener(CanvasMouseAdaptor2.getInstance());
        canvas.addMouseMotionListener(CanvasMouseAdaptor2.getInstance());
        canvas.setFocusable(true);
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"), "DELETE");
        canvas.getActionMap().put("DELETE", new DeleteAction());
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control A"), "control A");
        canvas.getActionMap().put("control A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("control + A");
            }
        });

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL,InputEvent.CTRL_DOWN_MASK),"control down");
        canvas.getActionMap().put("control down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Setting.getCurrentTool() == Tool.Select) {
                    System.out.println("control down");
                    Setting.setCurrentTool(Tool.MultiSelect);
                }
            }
        });
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL,0,true),"control release");
        canvas.getActionMap().put("control release", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Setting.getCurrentTool() == Tool.MultiSelect) {
                    System.out.println("control release");
                    Setting.setCurrentTool(Tool.Select);
                }
            }
        });

        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT,InputEvent.SHIFT_DOWN_MASK),"shift down");
        canvas.getActionMap().put("shift down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setting.setShift(true);
                System.out.println("shift down");
            }
        });
        canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT,0,true),"shift release");
        canvas.getActionMap().put("shift release", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("shift release");
                Setting.setShift(false);
            }
        });

        GlassPanel glassCanvas = new GlassPanel();
        Environ.setGlassPanel(glassCanvas);
        add(glassCanvas, 0);
        glassCanvas.setSize(300,400);
        glassCanvas.setLocation(0,100);
    }



    public static void main(String[] args) {


        FrameViewer mainFrame = new FrameViewer();

        //mainFrame.addMouseListener(mouseAdaptor);
        //mainFrame.addMouseMotionListener(mouseAdaptor);
        mainFrame.setVisible(true);

    }

    class ButtonClickedEvent implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            Tool currentTool=Tool.getEnumFromValue(actionCommand);
            if(null!=currentTool){
                Setting.setCurrentTool(currentTool);
            }else{
                Setting.setCurrentTool(Tool.Select);
            }
        }
    }

    class DeleteAction extends AbstractAction {
        public DeleteAction() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Pattern[] patterns = Environ.getSelectedPatterns();
            Environ.clearSelectedpatterns();
            JComponent parent = (JComponent)e.getSource();
            Environ.getGlassPanel().clear();
            for(Pattern p: patterns)
                parent.remove(p);
            parent.repaint();
            System.out.println("Pattern has been removed");
        }
    }
}