package co.com.siigo.interactions.comunes;

import co.com.siigo.utilities.datos.DatosPrueba;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClickSelectOption implements Task {
    public <T extends Actor> void performAs(T actor) {
        By HEADER   = By.cssSelector("siigo-header-molecule.hydrated");
        By BTN_HOST = By.cssSelector("siigo-button-atom[data-id='header-create-button'].hydrated");
        By BTN_INNER= By.cssSelector("button.btn-element");
        By MENUHOST = By.cssSelector("siigo-header-create-button-dropdown.hydrated, [data-id='header-create-button-dropdown'].hydrated");

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement header = wait.until(ExpectedConditions.presenceOfElementLocated(HEADER));
        SearchContext srHeader = header.getShadowRoot();

        WebElement crearHost = wait.until(d -> srHeader.findElement(BTN_HOST));
        crearHost.getShadowRoot().findElement(BTN_INNER).click();

        WebElement menuHost = wait.until(d -> srHeader.findElement(MENUHOST));

        SearchContext menuRoot;
        try {
            menuRoot = menuHost.getShadowRoot();  // puede ser null
        } catch (Exception e) {
            menuRoot = null;
        }
        if (menuRoot == null) menuRoot = menuHost;

        SearchContext finalMenuRoot = menuRoot;
        WebElement item = wait.until(d -> finalMenuRoot.findElement(By.cssSelector("a[data-value='"+ DatosPrueba.obtener("opcionPrincipal")+"']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", item);
        item.click();

    }
    public static Performable boton(){ return Tasks.instrumented(ClickSelectOption.class); }
}
