package TestTaskMail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    /**
     *
     * @param nameProperty
     * @return
     * @throws Exception
     */
    public static String getProperty(String nameProperty) {
        FileInputStream fis = null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src\\main\\resources\\app.properties");
            property.load(fis);
            return property.getProperty(nameProperty);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * Ожидание.
     * @param seconds   секунды.
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }
}
