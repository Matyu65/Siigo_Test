package co.com.siigo.userinterface.homepage;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://qastaging.siigo.com/#/login")
public class LoginPage extends PageObject {
    public static final Target TXT_USER = Target.the("campo usuario").located(By.xpath("//input[contains(@id,'siigoSignInName')]"));
    public static final Target TXT_PASS = Target.the("campo contraseña").located(By.xpath("//input[contains(@id,'siigoPassword')]"));
    public static final Target BTN_INGRESAR = Target.the("buscar reserva").locatedBy("//button[contains(@id,'siigoNext')]");

}
