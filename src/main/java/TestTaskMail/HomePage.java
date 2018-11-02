package TestTaskMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.fail;
import static TestTaskMail.Utils.sleep;

public class HomePage extends BasePage {

    private final Logger logger = LoggerFactory.getLogger(HomePage.class);
    private By authLink = By.id("PH_authLink");
    private By logoutLink = By.id("PH_logoutLink");

    public HomePage() {
        logger.info("Открываем домашнюю страницу Mail.ru");
        get(CommonConstants.URL_MAIL_HOME);
        if (!getDriver().getTitle().equals("Mail.Ru: почта, поиск в интернете, новости, игры")) {
            fail("This is not the Mail.Ru");
        }
    }

    public LoginPage logIn() {
        WebElement logout = getDriver().findElement(logoutLink);
        if ((logout != null) && logout.isDisplayed()) {
            logger.info("Кликаем по ссылке Выход");
            logout.click();
            sleep(3);
        }
        logger.info("Кликаем по ссылке Вход");
        getDriver().findElement(authLink).click();
        return new LoginPage();
    }

    public HomePage logOut() {
        logger.info("Кликаем по ссылке Выход");
        getDriver().findElement(logoutLink).click();
        return this;
    }

}
