package app;

import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    public static Application app;

    @BeforeEach
    public void start() {
        if (app != null) {
            return;
        }

        app = new Application();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
    }
}
