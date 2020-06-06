import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

public class AdminItems {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void adminItems() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        String parentXpathLocator = "//li[@id = 'app-']";
        String childXpathLocator = "//ul[@class = 'docs']";
        By headerLocator = By.xpath("//h1");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(parentXpathLocator)));
        List<WebElement> items = driver.findElements(By.xpath(parentXpathLocator));
        int countOfItems = items.size();
        for (int i = 1; i <= countOfItems; i++) {
            By locator = By.xpath("//*[@id = 'app-'][" + i + "]");
            driver.findElement(locator).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(headerLocator));
            driver.findElement(headerLocator);
            List<WebElement> childItems = driver.findElements(By.xpath(parentXpathLocator + childXpathLocator));
            if (childItems.size() > 1) {
                int countOfChildItems = childItems.size();
                for (int j = 2; j <= countOfChildItems; j++) {
                    By childLocator = By.xpath("//*[@id = 'app-'][" + i + "//ul[@class = 'docs'][" + j + "]");
                    driver.findElement(childLocator).click();
                    driver.findElement(headerLocator);
                }
            }
        }

    }
    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
