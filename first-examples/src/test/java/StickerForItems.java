import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StickerForItems {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void stickersTest() {
        driver.get("http://localhost/litecart");

        String productXpathLocator = "//div[contains(@class, 'image')]";
        String stickerXpathLocator = "//div[contains(@class, 'sticker')]";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productXpathLocator)));
        List<WebElement> products = driver.findElements(By.xpath(productXpathLocator));

        for (int i = 1; i <= products.size(); i++) {
            driver.findElement(By.xpath("(" + productXpathLocator + stickerXpathLocator + ")" + "[" + i + "]"));
            System.out.println("(" + productXpathLocator + stickerXpathLocator + ")" + "[" + i + "]");
        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
