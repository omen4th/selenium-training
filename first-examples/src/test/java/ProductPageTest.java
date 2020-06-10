import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductPageTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost/litecart");
    }


    @Test
    public void productPage() {
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns .product"));

        product.getText();
        String nameOnMainPage = product.findElement(By.cssSelector(".name")).getText();
        String regularPriceOnMainPage = product.findElement(By.cssSelector(".regular-price")).getText();
        String campaignPriceOnMainPage = product.findElement(By.cssSelector(".campaign-price")).getText();


        // Check styles of regular price
        product.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line").equals("line-through");
        String regularPriceColorOnMainPage = product.findElement(By.cssSelector(".regular-price")).getCssValue("color").replaceAll("[A-Za-z-(-)- -]+", "");
        String[] rgbRegularPriceColorOnMainPage = regularPriceColorOnMainPage.split(",", 4);
        assertEquals(rgbRegularPriceColorOnMainPage[0], rgbRegularPriceColorOnMainPage[1], "the color should be gray");
        assertEquals(rgbRegularPriceColorOnMainPage[0], rgbRegularPriceColorOnMainPage[2], "the color should be gray");
        assertEquals(rgbRegularPriceColorOnMainPage[1], rgbRegularPriceColorOnMainPage[2], "the color should be gray");

        // Check styles of campaign price
        product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight").equals("700");
        String campaignPriceColorOnMainPage = product.findElement(By.cssSelector(".campaign-price")).getCssValue("color").replaceAll("[A-Za-z-(-)- -]+", "");
        String[] rgbCampaignPriceColorOnMainPage = campaignPriceColorOnMainPage.split(",", 4);
        assertEquals("0", rgbCampaignPriceColorOnMainPage[1], "the color should be red");
        assertEquals("0", rgbCampaignPriceColorOnMainPage[2], "the color should be red");

        // Check regular price is bigger than campaign
        double regularPriceFontSizeOnMainPage = Double.parseDouble(product.findElement(By.cssSelector(".regular-price")).getCssValue("font-size").replaceAll("[A-Za-z]", ""));
        double campaignPriceFontSizeOnMainPage = Double.parseDouble(product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size").replaceAll("[A-Za-z]", ""));
        assertTrue(campaignPriceFontSizeOnMainPage > regularPriceFontSizeOnMainPage, "The font-size is incorrect");

        product.click();

        // Check that name is equal on both pages
        String nameOnProductPage = driver.findElement(By.cssSelector("h1.title")).getText();
        assertEquals(nameOnMainPage, nameOnProductPage, "Name should be equals on both pages");

        // Check that regular price is equal on both pages
        String regularPriceOnProductPage = driver.findElement(By.cssSelector(".regular-price")).getText();
        assertEquals(regularPriceOnMainPage, regularPriceOnProductPage, "The regular price should be equal");

        // Check that campaign price is equal on both pages
        String campaignPriceOnProductPage = driver.findElement(By.cssSelector(".campaign-price")).getText();
        assertEquals(campaignPriceOnMainPage, campaignPriceOnProductPage, "The campaign price should be equal");

        // Check styles of regular price
        driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line").equals("line-through");
        String regularPriceColorOnProductPage = driver.findElement(By.cssSelector(".regular-price")).getCssValue("color").replaceAll("[A-Za-z-(-)- -]+", "");
        String[] rgbRegularPriceColorOnProductPage = regularPriceColorOnProductPage.split(",", 4);
        assertEquals(rgbRegularPriceColorOnProductPage[0], rgbRegularPriceColorOnProductPage[1], "the color should be gray");
        assertEquals(rgbRegularPriceColorOnProductPage[0], rgbRegularPriceColorOnProductPage[2], "the color should be gray");
        assertEquals(rgbRegularPriceColorOnProductPage[1], rgbRegularPriceColorOnProductPage[2], "the color should be gray");

        // Check styles of campaign price
        driver.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight").equals("700");
        String campaignPriceColorOnProductPage = driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color").replaceAll("[A-Za-z-(-)- -]+", "");
        String[] rgbCampaignPriceColorOnProductPage = campaignPriceColorOnProductPage.split(",", 4);
        assertEquals("0", rgbCampaignPriceColorOnProductPage[1], "the color should be red");
        assertEquals("0", rgbCampaignPriceColorOnProductPage[2], "the color should be red");
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
