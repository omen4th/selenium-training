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

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        String productCSSLocator = ".product";
        String stickerCSSLocator = ".sticker";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(productCSSLocator)));
        List<WebElement> products = driver.findElements(By.cssSelector(productCSSLocator));

        for (WebElement webElement : products) {
            List<WebElement> stickers = webElement.findElements(By.cssSelector(stickerCSSLocator));
            assertTrue(stickers.size() == 1);
        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
