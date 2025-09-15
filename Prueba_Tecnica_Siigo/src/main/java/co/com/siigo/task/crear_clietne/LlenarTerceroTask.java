package co.com.siigo.task.crear_clietne;

import co.com.siigo.interactions.comunes.ClickInShadow;
import co.com.siigo.interactions.comunes.EnterByLabel;
import co.com.siigo.interactions.comunes.EnterInShadow;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.By;

import java.time.Duration;

public class LlenarTerceroTask implements Task {

    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(
                EnterInShadow.value("999")
                        .withTimeout(Duration.ofSeconds(8))
                        .into(
                                By.cssSelector("siigo-textfield-web"),
                                By.cssSelector("input.mdc-text-field__input")
                        )
        );
        actor.attemptsTo(
                EnterInShadow.value("1040738550")
                        .withTimeout(Duration.ofSeconds(8))
                        .into(
                                By.cssSelector("siigo-identification-input-web"),
                                By.cssSelector("input.mdc-text-field__input")
                        )
        );
        actor.attemptsTo(
          // Ejemplo: desplegar un select en shadow
          ClickInShadow.on(
            By.cssSelector("siigo-dropdownlist-web[data-id='person-type']"),  // HOST
            By.cssSelector(".mdc-select__anchor"))// INNER
          );

       /* actor.attemptsTo(
                EnterByLabel.text("Nombres", "Carlos"),
                EnterByLabel.text("Ciudad", "Bogotá").within(Duration.ofSeconds(12))
        );*/
    }

    public static Performable con(){ return Tasks.instrumented(LlenarTerceroTask.class); }
}

