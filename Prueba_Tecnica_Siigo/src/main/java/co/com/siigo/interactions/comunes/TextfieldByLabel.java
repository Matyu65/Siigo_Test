package co.com.siigo.interactions.comunes;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class TextfieldByLabel {

    private TextfieldByLabel() {}

    /** Busca un siigo-textfield-web por el texto de la etiqueta (label) y retorna su <input> interno */
    public static WebElement findInputByLabel(WebDriver driver, String label, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // Espera a que al menos haya un textfield en el DOM
        wait.until(d -> !driver.findElements(By.cssSelector("siigo-textfield-web")).isEmpty());

        // 1) localizar todos los hosts del web-component
        List<WebElement> hosts = driver.findElements(By.cssSelector("siigo-textfield-web"));

        // 2) Elegir el host cuyo label dentro del shadow root coincida
        WebElement host = hosts.stream().filter(h -> {
            try {
                // Espera a que el componente esté "hidratado"
                waitHydrated(wait, h);
                WebElement lbl = h.getShadowRoot().findElement(By.cssSelector(".mdc-floating-label"));
                return lbl != null && label.equals(lbl.getText().trim());
            } catch (Exception e) {
                return false;
            }
        }).findFirst().orElseThrow(() ->
                new NoSuchElementException("No encontré siigo-textfield-web con label: " + label));

        // 3) Devolver el <input> real dentro del shadow root
        return host.getShadowRoot().findElement(By.cssSelector("input.mdc-text-field__input"));
    }

    /** Variante si quieres acotar por el título de la tarjeta (h3) */
    public static WebElement findInputByLabelInSection(WebDriver driver, String sectionTitle, String label, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // Contenedor de la sección por el H3
        WebElement section = wait.until(d -> driver.findElement(
                By.xpath("//div[contains(@class,'card')]//h3[normalize-space()='" + sectionTitle + "']/ancestor::div[contains(@class,'card')]")));

        List<WebElement> hosts = section.findElements(By.cssSelector("siigo-textfield-web"));

        WebElement host = hosts.stream().filter(h -> {
            try {
                waitHydrated(wait, h);
                WebElement lbl = h.getShadowRoot().findElement(By.cssSelector(".mdc-floating-label"));
                return lbl != null && label.equals(lbl.getText().trim());
            } catch (Exception e) {
                return false;
            }
        }).findFirst().orElseThrow(() ->
                new NoSuchElementException("No encontré siigo-textfield-web en sección '" + sectionTitle + "' con label: " + label));

        return host.getShadowRoot().findElement(By.cssSelector("input.mdc-text-field__input"));
    }

    // Ayuda: espera a que el componente tenga la clase "hydrated"
    private static void waitHydrated(WebDriverWait wait, WebElement host) {
        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                String cls = host.getAttribute("class");
                return cls != null && cls.contains("hydrated");
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }
}
