import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTest {

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
    public void sortingCountries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        // Get list of countries
        List<WebElement> elements = driver.findElements(By.cssSelector(".row"));
        List<String> countries = new ArrayList<String>();
        for (WebElement webElement : elements) {
            countries.add(webElement.findElement(By.cssSelector("a")).getText());
        }
        List<String> sortedCountries = new ArrayList<String>(countries);
        Collections.sort(sortedCountries);
        // Check that the list is sorted
        assertTrue(sortedCountries.equals(countries), "List of countries should be sorted alphabetically.");

        // Get map Country - Count of Zones
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (WebElement webElement : elements) {
            map.put(
                    webElement.findElement(By.cssSelector("a")).getText(),
                    Integer.parseInt(webElement.findElement(By.cssSelector("td:nth-of-type(6)")).getText())
            );
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            String key = pair.getKey();
            int countOfZones = pair.getValue();
            //Check that sorting of zone for country is correct
            if (countOfZones > 0) {
                driver.findElement(By.xpath("//a[contains(text(),'" + key + "')]")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name = status]")));
                List<WebElement> zoneElements = driver.findElements(By.cssSelector("[name *= name][name *= zones]"));
                List<String> zones = new ArrayList<String>();
                for (WebElement webElement : zoneElements) {
                    zones.add(webElement.getAttribute("value"));
                }
                List<String> sortedZones = new ArrayList<String>(zones);
                Collections.sort(sortedZones);
                assertTrue(sortedZones.equals(zones), "List of zones should be sorted alphabetically.");
                driver.navigate().back();
            }
        }
    }

    @Test
    public void sortingZones() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> elements = driver.findElements(By.cssSelector(".row"));
        List<String> countries = new ArrayList<String>();
        for (WebElement webElement : elements) {
            countries.add(webElement.findElement(By.cssSelector("a")).getText());
        }

        for (String country : countries) {
            driver.findElement(By.xpath("//a[contains(text(),'" + country + "')]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".button-set")));

            List<WebElement> zoneElements = driver.findElements(By.cssSelector("[name *= zone_code] [selected]"));
            List<String> zones = new ArrayList<String>();
            for (WebElement zone : zoneElements) {
                zones.add(zone.getText());
            }
            List<String> sortedZones = new ArrayList<String>(zones);
            Collections.sort(sortedZones);
            assertTrue(sortedZones.equals(zones), "List of zones should be sorted alphabetically.");
            driver.navigate().back();
        }
    }


    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
