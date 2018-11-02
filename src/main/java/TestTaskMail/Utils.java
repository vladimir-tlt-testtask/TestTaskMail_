package TestTaskMail;

public class Utils {

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
