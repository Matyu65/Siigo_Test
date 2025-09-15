package co.com.siigo.interactions.comunes;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import java.time.Duration;

public class EnterByLabel implements Interaction {

    private final String label;
    private final String value;
    private final Duration timeout;

    private EnterByLabel(String label, String value, Duration timeout) {
        this.label = label;
        this.value = value;
        this.timeout = timeout;
    }

    public static EnterByLabel text(String label, String value) {
        return new EnterByLabel(label, value, Duration.ofSeconds(10));
    }

    public EnterByLabel within(Duration timeout) {
        return new EnterByLabel(this.label, this.value, timeout);
    }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
       // WebElement input = TextfieldByLabel.findInputByLabel(driver, label, timeout);
        WebElement input = TextfieldByLabel.findInputByLabelInSection(driver," Datos básicos", label, timeout);
        input.clear();
        input.sendKeys(value);
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));", input);
    }
}

