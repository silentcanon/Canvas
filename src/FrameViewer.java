import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;

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

        JButton selectButton = new JButton("Select");
        selectButton.setActionCommand("Select");
        selectButton.addActionListener(buttonClickedEvent);
        add(selectButton);
        selectButton.setSize(100, 20);
        selectButton.setLocation(20, 40);
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.setActionCommand("Rectangle");
        rectangleButton.addActionListener(buttonClickedEvent);
        add(rectangleButton);
        rectangleButton.setSize(100, 20);
        rectangleButton.setLocation(20, 60);
        canvas = new JLayeredPane();
        add(canvas);
        canvas.setSize(300, 400);
        canvas.setLocation(0, 100);

        canvas.addMouseListener(CanvasMouseAdaptor.getInstance());
        canvas.addMouseMotionListener(CanvasMouseAdaptor.getInstance());


    }



    public static void main(String[] args) {


        FrameViewer mainFrame = new FrameViewer();

        //mainFrame.addMouseListener(mouseAdaptor);
        //mainFrame.addMouseMotionListener(mouseAdaptor);
        mainFrame.setVisible(true);

    }

    class ButtonClickedEvent implements ActionListener {
        private Setting setting = Setting.getInstance();
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if(actionCommand.equals("Select")){
                //label.setText("Select");
                setting.setCurrentTool(Tool.Select);
            } else if(actionCommand.equals("Rectangle")) {
                //label.setText("Rect");
                setting.setCurrentTool(Tool.Rectangle);
            } else {
                //label.setText("Other");
                setting.setCurrentTool(Tool.Select);
            }
        }
    }
}