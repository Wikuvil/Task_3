package driver;

import config.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {

    private static final String YANDEX_PATH = loadYandexPath();

    public static WebDriver createDriver(BrowserType browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);

            case YANDEX:
                WebDriverManager.chromedriver()
                        .browserVersion("148")
                        .setup();
                options.setBinary(YANDEX_PATH);
                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static String loadYandexPath() {
        String path = System.getProperty("yandex.browser.path");
        if (path != null && !path.trim().isEmpty()) {
            return path.trim();
        }

        Properties props = new Properties();
        try (InputStream is = WebDriverFactory.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            props.load(is);
            path = props.getProperty("yandex.browser.path");

            if (path == null || path.trim().isEmpty()) {
                throw new RuntimeException(
                        "yandex.browser.path not set in config.properties"
                );
            }

            return path.trim();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
}