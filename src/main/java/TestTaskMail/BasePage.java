package TestTaskMail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private final static WebDriver driver;
    private long implicitlyWait;

    public static WebDriver getDriver() {
        return driver;
    }

    public long getImplicitlyWait() {
        return implicitlyWait;
    }

    public void setImplicitlyWait(long implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
        driver.manage().timeouts().implicitlyWait(this.implicitlyWait, TimeUnit.SECONDS);
    }

    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public BasePage() {
        this.setImplicitlyWait(5);
    }

    public void get(String url) {
        driver.get(url);
    }

}
