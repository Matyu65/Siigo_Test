package co.com.siigo.interactions.menu;

import co.com.siigo.interactions.comunes.Esperar;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import co.com.siigo.interactions.comunes.ClickSelectOption;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelecionarOpcionMenu implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Esperar.esperarSegundos(5000),
                ClickSelectOption.boton(),
                Esperar.esperarSegundos(5000)
        );
    }
    public static SelecionarOpcionMenu seleccionarOpcion(){
        return instrumented(SelecionarOpcionMenu.class);
    }
}
