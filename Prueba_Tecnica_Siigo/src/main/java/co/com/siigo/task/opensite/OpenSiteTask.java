package co.com.siigo.task.opensite;

import co.com.siigo.userinterface.homepage.LoginPage;
import co.com.siigo.utilities.properties.PropertiesProject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.io.IOException;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenSiteTask implements Task {

    LoginPage usuario = new LoginPage();

    public static OpenSiteTask openSitehttps()
    {
        return (OpenSiteTask) instrumented(OpenSiteTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String strUrl = "";
        try {
            strUrl = PropertiesProject.getUrlHttps();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!strUrl.isEmpty() && !strUrl.equals("")) {
            //Metodo Open Absoluto
            usuario.openUrl(strUrl);
        }
    }
}
