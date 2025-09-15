package co.com.siigo.utilities.comunes;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShadowWaits {
    public static WebElement waitForElementInShadow(WebDriver driver, By host, By inner, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until((ExpectedCondition<WebElement>) d -> {
            WebElement h = d.findElement(host);
            SearchContext sr = h.getShadowRoot();       // Selenium 4
            return sr.findElement(inner);
        });
    }
    public static WebElement waitForHostWithShadow(WebDriver driver, By host, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(d -> {
            WebElement h = d.findElement(host);
            try { h.getShadowRoot(); return h; } catch (Exception e) { return null; }
        });
    }
}

