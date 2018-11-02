package TestTaskMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class LoginPage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private String parentWindowHandler = getDriver().getWindowHandle();

    @FindBy(xpath = "//input[@type='text' and @name='Login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@type='password' and @name='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type='checkbox' and @name='saveauth']")
    private WebElement saveauthCheckbox;

    @FindBy(xpath = "//button[@type='submit' and @data-test-id='submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='login-header' and contains(text(),'Неверное')]")
    private WebElement errorText;

    public LoginPage() {
        logger.info("Открываем форму авторизации");
        parentWindowHandler = getDriver().getWindowHandle();
        WebElement iframe = null;
        List<WebElement> iframes = getDriver().findElements(By.tagName("iframe"));
        for (WebElement item : iframes) {
            try {
                String src = item.getAttribute("src");
                if (src.startsWith("https://account.mail.ru/login")) {
                    iframe = item;
                    break;
                }
            } catch (NullPointerException e) {

            }
        }
        assertTrue("Форма авторизации не найдена", iframe != null);
        getDriver().switchTo().frame(iframe);
        PageFactory.initElements(getDriver(), this);
    }

    private LoginPage setLogin(String mail) {
        logger.info("Вводим логин: " + mail);
        loginField.click();
        loginField.clear();
        loginField.sendKeys(mail);
        return this;
    }

    private LoginPage setPassword(String password) {
        logger.info("Вводим пароль: " + password);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage setSaveauth() {
        logger.info("Устанавливаем чекбокс 'Запомнить'");
        if (!saveauthCheckbox.isSelected()) {
            saveauthCheckbox.click();
        }
        return this;
    }

    public LoginPage clearSaveauth() {
        logger.info("Сбрасываем чекбокс 'Запомнить'");
        if (saveauthCheckbox.isSelected()) {
            saveauthCheckbox.click();
        }
        return this;
    }

    private EmailPage submitLogin() {
        logger.info("Отправляем форму на сервер");
        submitButton.submit();
        getDriver().switchTo().window(parentWindowHandler);
        String errMsg = "Ошибка авторизации: " + getErrorMsg();
        assertTrue(errMsg, isError());
        return new EmailPage();
    }

    public boolean isError() {
        return getDriver().getCurrentUrl().contains("https://e.mail.ru");
    }

    public String getErrorMsg() {
        String errMsg = null;
        if (!getDriver().getCurrentUrl().contains("https://e.mail.ru")) {
            List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='login-header']"));
            if (elements.size() > 1) {
                errMsg = elements.get(0).getText();
            }
        }
        return errMsg;
    }

    public EmailPage authorize(String login, String password) {
        logger.info("Авторизация...");
        setLogin(login);
        setPassword(password);
        return submitLogin();
    }
}
