package co.com.siigo.stepdefinitions.crearcliente;


import co.com.siigo.interactions.menu.SelecionarOpcionMenu;
import co.com.siigo.task.crear_clietne.LlenarTerceroTask;
import co.com.siigo.task.login.LoginTask;
import co.com.siigo.task.opensite.OpenSiteTask;
import co.com.siigo.utilities.datos.CargarDatos;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.*;

public class crearClienteStepDefinitions {

    @Before
    public void initialConfiguration() {
        setTheStage(new OnlineCast());
    }

    @Dado("^que el usuario tiene toda la informacion que necesita$")
    public void queElUsuarioTieneTodaLaInformacionQueNecesita(List<Map<String, Object>> informacion)  {
        theActorCalled("Usuario").attemptsTo(CargarDatos.conLaSiguiente(informacion));
    }

    @Cuando("^realiza el login de la aplicacion$")
    public void realizaElloginDeLaAplicacion() throws Exception {
        theActorInTheSpotlight().attemptsTo(OpenSiteTask.openSitehttps());
        theActorInTheSpotlight().attemptsTo(LoginTask.authentificarUsuario());
    }
    @Y("^selecciona la opcion del menu$")
    public void seleccionaLaOpcionDelMenu() throws Exception {
        theActorInTheSpotlight().attemptsTo(SelecionarOpcionMenu.seleccionarOpcion());
    }
    @Cuando("^Ingresa la informacion del cliente$")
    public void Ingresalainformaciondelcliente() throws Exception {
        theActorInTheSpotlight().attemptsTo(LlenarTerceroTask.con());
    }

}
