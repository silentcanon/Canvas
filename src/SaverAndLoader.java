import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Canon on 2015-07-05.
 */
public class SaverAndLoader {
    private static  JLayeredPane canvas;
    private static SaverAndLoader instance;
    private SaverAndLoader(JLayeredPane canvas) {
        this.canvas = canvas;
    }

    public static void init(JLayeredPane canvas) {
        instance = new SaverAndLoader(canvas);
    }
    public static SaverAndLoader getInstance() {
        return instance;
    }

    public static void saveAction(List<Pattern> patterns, File f) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for(Pattern p: patterns) {
                oos.writeObject(p);
            }
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAction(File f) {
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Pattern p = (Pattern) ois.readObject();
            while (p != null) {
                p.addMouseListener(PatternMouseAdaptor.getInstance());
                p.addMouseMotionListener(PatternMouseAdaptor.getInstance());
                canvas.add(p, Setting.getLayer());
                p = (Pattern) ois.readObject();
            }
            ois.close();
        } catch (EOFException e) {
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
