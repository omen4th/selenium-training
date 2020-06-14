import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class CreateAccount {

    public WebDriver driver;
    public WebDriverWait wait;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String key = timestamp.getTime() + "";
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost/litecart");
    }


    @Test
    public void createAccount() throws InterruptedException {
        String email = "email" + key + "@litecart.com";
        String password = "1qaz@WSX";

        driver.findElement(By.cssSelector("[name = 'login_form'] [href]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'tax_id']")));
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("[name = 'tax_id']")).sendKeys(key);
        driver.findElement(By.cssSelector("[name = 'company']")).sendKeys("company" + key);
        driver.findElement(By.cssSelector("[name = 'firstname']")).sendKeys("name" + key);
        driver.findElement(By.cssSelector("[name = 'lastname']")).sendKeys("lastname" + key);
        driver.findElement(By.cssSelector("[name = 'address1']")).sendKeys("address1 " + key);
        driver.findElement(By.cssSelector("[name = 'address2']")).sendKeys("address2 " + key);
        driver.findElement(By.cssSelector("[name = 'postcode']")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name = 'city']")).sendKeys("New York");

        driver.findElement(By.cssSelector(".selection")).click();
        driver.findElement(By.cssSelector("[type = 'search']")).sendKeys("United States");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("[id $= US]")).click();

        driver.findElement(By.cssSelector("[name = 'email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name = 'phone']")).sendKeys("+" + key);
        driver.findElement(By.cssSelector("[name = 'password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name = 'confirmed_password']")).sendKeys(password);

        driver.findElement(By.cssSelector("[name = 'create_account']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".notice")));

        driver.findElement(By.cssSelector(".select2")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("[name = 'zone_code'] [value = 'CO']")).click();
        driver.findElement(By.cssSelector(".select2")).click();

        driver.findElement(By.cssSelector("[name = 'password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name = 'confirmed_password']")).sendKeys(password);

        driver.findElement(By.cssSelector("[name = 'create_account']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href *= logout]")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[href *= logout]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'email']")));

        driver.findElement(By.cssSelector("[name = 'email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name = 'password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name = 'login']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href *= logout]")));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[href *= logout]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = 'email']")));
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
