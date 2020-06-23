import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRageLogs {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void getProductPageLogs() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");

        String linkLocator = "(//a[contains(@title, 'Edit') and contains(@href, 'product')])";
        List<WebElement> productLinks = driver.findElements(By.xpath(linkLocator));
        for (int i = 1; i <= productLinks.size(); i++) {
            driver.findElement(By.xpath(linkLocator + "[" + i + "]")).click();
            List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();
            assertTrue(logEntries.isEmpty(), "Log contains errors on page: "
                    + driver.findElement(By.cssSelector("h1")).getText());
            driver.navigate().back();
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
