package TestTaskMail;

import static TestTaskMail.Utils.sleep;
import static TestTaskMail.Utils.getProperty;

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
        EmailPage email = (new HomePage()).logIn().authorize(getProperty("mail.login"), getProperty("mail.password"));
        email.create(getProperty("test.email.to"), getProperty("test.email.subject"), getProperty("test.email.msg"));
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
        EmailPage email = (new HomePage()).logIn().authorize(getProperty("mail.login"), getProperty("mail.password.err"));
        email.create(getProperty("test.email.to"), getProperty("test.email.subject"), getProperty("test.email.msg"));
        email.send();
        sleep(10);
        email.inbox();
    }

}
