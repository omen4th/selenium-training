package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        mainPage = new MainPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    public void openMainPage() {
        driver.get(mainPage.link);
    }

    public void selectProduct() {
        driver.findElement(mainPage.productItem).click();
    }

    public void addToCart() {
        int cartItemsCount = Integer.parseInt(driver.findElement(productPage.cartQuantity).getText());

        if (driver.findElements(productPage.sizeSelector).size() > 0) {
            driver.findElement(productPage.sizeSelector).click();
            driver.findElement(productPage.mediumValue).click();
        }
        driver.findElement(productPage.addProductButton).click();
        wait.until(textToBe(productPage.cartQuantity, cartItemsCount + 1 + ""));
    }

    public void back() {
        driver.navigate().back();
    }

    public void openCartPage() {
        driver.findElement(cartPage.cartLink).click();
        wait.until(presenceOfElementLocated(cartPage.removeItemButton));
    }

    public void removeAllItems() throws InterruptedException {
        List<WebElement> shortcuts = wait.until(presenceOfAllElementsLocatedBy(cartPage.shortCut));
        Thread.sleep(1000);

        for (int i = shortcuts.size() - 1; i >= 0; i--) {
            List<WebElement> itemsTable = driver.findElements(cartPage.dataTable);
            if (i > 0) {
                driver.findElement(cartPage.shortCut).click();
            }
            wait.until(visibilityOfElementLocated(cartPage.removeItemButton)).click();
            wait.until(stalenessOf(itemsTable.get(i)));
        }

        wait.until(textToBe(cartPage.notificationField, cartPage.emptyCartText));
    }

    public void quit() {
        driver.quit();
    }
}
