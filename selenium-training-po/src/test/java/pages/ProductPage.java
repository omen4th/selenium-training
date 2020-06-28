package pages;

import org.openqa.selenium.By;

public class ProductPage {

    public By cartQuantity = By.cssSelector(".quantity");
    public By sizeSelector = By.cssSelector("[name = 'options[Size]']");
    public By mediumValue = By.cssSelector("[value= 'Medium']");
    public By addProductButton = By.cssSelector("[name = 'add_cart_product']");

}
