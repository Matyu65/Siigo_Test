package co.com.siigo.task.login;

import co.com.siigo.utilities.datos.DatosPrueba;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.type.Type;

import static co.com.siigo.userinterface.homepage.LoginPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            /*Ingreso de usuario y contraseña*/
            Click.on(TXT_USER),
            Type.theValue(DatosPrueba.obtener("usuario")).into(TXT_USER),
            Click.on(TXT_PASS),
            Type.theValue(DatosPrueba.obtener("password")).into(TXT_PASS)
    );
    actor.attemptsTo(
            /*click boton ingresar*/
            Click.on(BTN_INGRESAR));
    }

    public static LoginTask authentificarUsuario()
    {
        return instrumented(LoginTask.class);
    }
}
