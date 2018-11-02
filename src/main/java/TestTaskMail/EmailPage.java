package TestTaskMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static TestTaskMail.Utils.sleep;

public class EmailPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(EmailPage.class);
    private By toField = By.xpath("//textarea[@data-original-name='To']");
    private By subjectField = By.xpath("//input[@type='text' and @name='Subject']");
    private By body = By.id("tinymce");
    private By newMailButton = By.xpath("//a[@data-name='compose']");
    private By sendButton = By.xpath("//div[@data-name='send']");
    private By cancelButton = By.xpath("//div[@data-name='cancel']");
    private By inboxLink = By.xpath("//a[@href='/messages/inbox/']");

    public EmailPage() {
        if (!getDriver().getCurrentUrl().contains(CommonConstants.URL_MATCH_EMAIL)) {
            fail("Ошибка авторизации");
        }
        logger.info("Успешно авторизировались");
    }

    private EmailPage setTo(String mailTo) {
        logger.info("Вводим в поле 'Кому': " + mailTo);
        WebElement element = getDriver().findElement(toField);
        element.click();
        element.clear();
        element.sendKeys(mailTo);
        sleep(1);
        return this;
    }

    private EmailPage setSubject(String subject) {
        logger.info("Вводим в поле 'Тема': " + subject);
        WebElement element = getDriver().findElement(subjectField);
        element.click();
        element.clear();
        element.sendKeys(subject);
        sleep(2);
        return this;
    }

    private EmailPage setBody(String msg) {
        logger.info("Вводим текст письма: " + msg);
        WebElement iframe = null;
        List<WebElement> iframes = getDriver().findElements(By.tagName("iframe"));
        for (WebElement item : iframes) {
            try {
                String id = item.getAttribute("id");
                if (id.contains("composeEditor")) {
                    iframe = item;
                    break;
                }
            } catch (NullPointerException e) {

            }
        }
        assertTrue("Поле 'Текст письма' не найдено", iframe != null);
        getDriver().switchTo().frame(iframe);
        WebElement element = getDriver().findElement(body);
        element.sendKeys(msg);
        sleep(3);
        getDriver().switchTo().defaultContent();
        return this;
    }

    public EmailPage create(String to, String subject, String msg) {
        logger.info("Создаем письмо...");
        newMail();
        setTo(to);
        setSubject(subject);
        setBody(msg);
        return this;
    }

    private EmailPage newMail() {
        logger.info("Кликаем по кнопке 'Написать письмо'");
        getDriver().findElement(newMailButton).click();
        sleep(3);
        return this;
    }

    public EmailPage send() {
        logger.info("Кликаем по кнопке 'Отправить'");
        getDriver().findElement(sendButton).click();
        return this;
    }

    public EmailPage cancel() {
        logger.info("Кликаем по кнопке 'Отмена'");
        getDriver().findElement(cancelButton).click();
        return this;
    }

    public EmailPage inbox() {
        logger.info("Кликаем по ссылке 'Входящие'");
        getDriver().findElement(inboxLink).click();
        return this;
    }

}
