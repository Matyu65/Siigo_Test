package co.com.siigo.interactions.comunes;

import co.com.siigo.utilities.comunes.ShadowWaits;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import java.time.Duration;

public class ClickInShadow implements Interaction {
    private final By host;
    private final By inner;
    private final Duration timeout;

    private ClickInShadow(By host, By inner, Duration timeout) {
        this.host = host; this.inner = inner; this.timeout = timeout;
    }

    public static ClickInShadow on(By host, By inner) {
        return Tasks.instrumented(ClickInShadow.class, host, inner, Duration.ofSeconds(10));
    }

    @Override public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement el = ShadowWaits.waitForElementInShadow(driver, host, inner, timeout);

        // Asegura visibilidad/scroll y hace click (por si el ripple/overlay lo tapa)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { el.click(); }
        catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el); // fallback JS
        }
    }
}
