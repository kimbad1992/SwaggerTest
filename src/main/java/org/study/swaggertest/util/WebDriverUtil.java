package org.study.swaggertest.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

@Component
public class WebDriverUtil {

    private static String WEB_DRIVER_PATH = "/driver/chromedriver.exe";

    public static WebDriver getChromeDriver() {
        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=true");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        WebDriver driver = new ChromeDriver(options);
        driver.manage()
                .timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }
}
