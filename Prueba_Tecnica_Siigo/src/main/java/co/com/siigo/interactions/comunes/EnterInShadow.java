package co.com.siigo.interactions.comunes;

import co.com.siigo.utilities.comunes.ShadowWaits;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import java.time.Duration;

public class EnterInShadow implements Interaction {

    private final By host;
    private final By innerInput;
    private final String text;
    private final Duration timeout;

    // Debe existir este constructor con el mismo orden de params que usas en Tasks.instrumented
    public EnterInShadow(By host, By innerInput, String text, Duration timeout) {
        this.host = host;
        this.innerInput = innerInput;
        this.text = text;
        this.timeout = timeout;
    }

    // ==== BUILDER para API fluida ====
    public static ValueBuilder value(String text) {
        return new ValueBuilder(text);
    }

    public static class ValueBuilder {
        private final String text;
        private Duration timeout = Duration.ofSeconds(10);

        private ValueBuilder(String text) { this.text = text; }

        public ValueBuilder withTimeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public EnterInShadow into(By host, By innerInput) {
            // Importante: el orden debe coincidir con el constructor
            return Tasks.instrumented(EnterInShadow.class, host, innerInput, text, timeout);
        }
    }
    // ==== FIN BUILDER ====

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Usa tu utilidad ShadowWaits para encontrar el input dentro del shadow
        WebElement input = ShadowWaits.waitForElementInShadow(driver, host, innerInput, timeout);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        input.sendKeys(text);

        // Dispara los eventos para frameworks tipo Material/Stencil
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", input
        );
    }
}
