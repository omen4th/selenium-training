import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class AddProduct {

    public WebDriver driver;
    public WebDriverWait wait;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String key = timestamp.getTime() + "";

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void addProduct() throws InterruptedException {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href $= catalog]")));
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("[href $= catalog]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href $= edit_product]")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[href $= edit_product]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'status'][value = '1']")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[name = 'status'][value = '1']")).click();
        driver.findElement(By.cssSelector("[name = 'name[en]']")).sendKeys("name" + key);
        driver.findElement(By.cssSelector("[name = 'code'")).sendKeys("code" + key);
        driver.findElement(By.cssSelector("[name = 'product_groups[]'][value = '1-3']")).click();
        driver.findElement(By.cssSelector("[name = 'quantity']")).clear();
        driver.findElement(By.cssSelector("[name = 'quantity']")).sendKeys("10");

        File file = new File("./src/test/resources/image.png");
        try {
            driver.findElement(By.cssSelector("[type = 'file'")).sendKeys(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("[name = 'date_valid_from']")).sendKeys("01/01/2020");
        driver.findElement(By.cssSelector("[name = 'date_valid_to']")).sendKeys("31/12/2020");

        driver.findElement(By.cssSelector("[href = '#tab-information']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'keywords']")));
        driver.findElement(By.cssSelector("[name = 'keywords']")).sendKeys("product " + key);
        driver.findElement(By.cssSelector("[name = 'keywords']")).sendKeys("product " + key);
        driver.findElement(By.cssSelector("[name = 'short_description[en]']")).sendKeys("product");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("product");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("product created by autotest");
        driver.findElement(By.cssSelector("[name = 'head_title[en]']")).sendKeys("autotest");
        driver.findElement(By.cssSelector("[name = 'meta_description[en]']")).sendKeys("selenium");

        driver.findElement(By.cssSelector("[href = '#tab-prices']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'purchase_price']")));
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).clear();
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).sendKeys("10");
        driver.findElement(By.cssSelector("[name = 'purchase_price_currency_code']")).click();
        driver.findElement(By.cssSelector("[value = 'USD']")).click();

        driver.findElement(By.cssSelector("[name = 'save']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href $= edit_product]")));

        driver.findElement(By.xpath("//a[contains(text(),'" + key + "')]"));
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
