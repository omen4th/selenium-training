package tests;

import app.TestBase;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends TestBase {

    @Test
    public void addProductToCart() throws InterruptedException {

        app.openMainPage();

        for (int i = 0; i < 3; i++) {
            app.selectProduct();
            app.addToCart();
            app.back();
        }

        app.openCartPage();
        app.removeAllItems();
    }
}
