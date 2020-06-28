package pages;

import org.openqa.selenium.By;

public class CartPage {

    public By cartLink = By.cssSelector("[href $= 'checkout'].link");
    public By removeItemButton = By.cssSelector("[name = 'remove_cart_item']");
    public By shortCut = By.cssSelector("li.shortcut");
    public By dataTable = By.cssSelector(".dataTable .item");
    public By notificationField = By.cssSelector("#content em");
    public String emptyCartText = "There are no items in your cart.";

}
