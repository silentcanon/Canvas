import javax.swing.*;
import java.awt.*;
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

    private static void save2file(List<Pattern> patterns, File f) {
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

    public static void saveAction(File f) {
        byte[] currentState = History.getCurrentState();
        ArrayList<Pattern> patterns = men2list(currentState);
        save2file(patterns, f);
    }



    public static void openAction(File f) {
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Pattern p = (Pattern) ois.readObject();
            while (p != null) {
                patterns.add(p);
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
        restoreFromList(patterns);
        History.clear();
        History.addRecord();
    }

    private static void restoreFromList(List<Pattern> patterns) {
        canvas.removeAll();
        System.out.println("remove all");
        for(Pattern p: patterns) {
            p.addMouseListener(PatternMouseAdaptor.getInstance());
            p.addMouseMotionListener(PatternMouseAdaptor.getInstance());
            canvas.add(p, Setting.getLayer());
        }
    }

    public static void restoreStateFromMem(byte[] bytes) {
        ArrayList<Pattern> patterns = men2list(bytes);
        restoreFromList(patterns);

    }

    private static ArrayList<Pattern> men2list(byte[] bytes) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Pattern p = (Pattern) ois.readObject();
            while(p != null) {
                patterns.add(p);
                p = (Pattern) ois.readObject();
            }
            bais.close();
            ois.close();
        } catch (EOFException e) {
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patterns;
    }

    public static byte[] saveStateToMem() {
        byte[] bytes = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            Component[] cs = canvas.getComponents();

            for (Component c : cs) {
                if (c instanceof Pattern) {
                    oos.writeObject((Pattern) c);
                }
            }
            bytes = baos.toByteArray();


            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
