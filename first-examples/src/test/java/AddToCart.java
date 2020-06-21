import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddToCart {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.get("https://litecart.stqa.ru/en/");
    }

    @Test
    public void addToCart() throws InterruptedException {
        // 1st
        driver.findElement(By.cssSelector(".product")).click();
        int cartItemsCount = Integer.parseInt(driver.findElement(By.cssSelector(".quantity")).getText());

        if (driver.findElements(By.cssSelector("[name = 'options[Size]']")).size() > 0) {
            driver.findElement(By.cssSelector("[name = 'options[Size]']")).click();
            driver.findElement(By.cssSelector("[value= 'Medium']")).click();
        }
        driver.findElement(By.cssSelector("[name = 'add_cart_product']")).click();
        wait.until(textToBe(By.cssSelector(".quantity"), cartItemsCount + 1 + ""));

        driver.navigate().back();

        // 2nd
        driver.findElement(By.cssSelector(".product")).click();
        cartItemsCount = Integer.parseInt(driver.findElement(By.cssSelector(".quantity")).getText());

        if (driver.findElements(By.cssSelector("[name = 'options[Size]']")).size() > 0) {
            driver.findElement(By.cssSelector("[name = 'options[Size]']")).click();
            driver.findElement(By.cssSelector("[value= 'Medium']")).click();
        }
        driver.findElement(By.cssSelector("[name = 'add_cart_product']")).click();
        wait.until(textToBe(By.cssSelector(".quantity"), cartItemsCount + 1 + ""));

        driver.navigate().back();

        // 3rd
        driver.findElement(By.cssSelector(".product")).click();
        cartItemsCount = Integer.parseInt(driver.findElement(By.cssSelector(".quantity")).getText());

        if (driver.findElements(By.cssSelector("[name = 'options[Size]']")).size() > 0) {
            driver.findElement(By.cssSelector("[name = 'options[Size]']")).click();
            driver.findElement(By.cssSelector("[value= 'Medium']")).click();
        }
        driver.findElement(By.cssSelector("[name = 'add_cart_product']")).click();
        wait.until(textToBe(By.cssSelector(".quantity"), cartItemsCount + 1 + ""));

        driver.findElement(By.cssSelector("[href $= 'checkout'].link")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("[name = 'remove_cart_item']")));

        List<WebElement> shortcuts = wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("li.shortcut")));
        Thread.sleep(1000);

        for (int i = shortcuts.size() - 1; i  >= 0 ; i--) {
            List<WebElement> itemsTable = driver.findElements(By.cssSelector(".dataTable .item"));
            if (i > 0) {
                driver.findElement(By.cssSelector("li.shortcut")).click();
            }
            wait.until(visibilityOfElementLocated(By.cssSelector("[name = 'remove_cart_item']"))).click();
            wait.until(stalenessOf(itemsTable.get(i)));
        }

        wait.until(textToBe(By.cssSelector("#content em"), "There are no items in your cart."));
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
