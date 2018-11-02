package TestTaskMail;

import static TestTaskMail.Utils.sleep;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    /**
     * validSendEmail
     */
    @Test
    public void validSendEmail()
    {
        EmailPage email = (new HomePage()).logIn().authorize("vladimir.tlt.testtask", "testtask123456");
        email.create("vladimir.tlt.testtask@mail.ru", "test", "Тестовое сообщение");
        email.send();
        sleep(10);
        email.inbox();
    }

    /**
     * errorSendEmail
     */
    @Test
    public void errorSendEmail()
    {
        EmailPage email = (new HomePage()).logIn().authorize("vladimir.tlt.testtask", "testtask1234567");
        email.create("vladimir.tlt.testtask@mail.ru", "test", "Тестовое сообщение");
        email.send();
        sleep(10);
        email.inbox();
    }

}
