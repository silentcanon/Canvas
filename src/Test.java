import java.io.*;
import java.util.Date;

/**
 * Created by Canon on 2015-07-06.
 */
public class Test {
    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = new Date();
        System.out.println(date1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        byte[] byteArray = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(date1);
            oos.writeObject(date2);
            byteArray = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
        try {
            ois = new ObjectInputStream(bais);
            Date d = (Date) ois.readObject();
            while(d != null) {
                System.out.println(d);
                d = (Date) ois.readObject();
            }
        } catch (EOFException e) {
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
