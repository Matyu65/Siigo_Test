package co.com.siigo.userinterface.mainmenu;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class menuPage extends PageObject {
    public static final Target OPC_DINAMICA = Target.the("Opcion Principal del MAin Page") .locatedBy("//p[contains(text(),'{0}')]");
    public static final Target SELECT_TIPO = Target.the("Opcion Principal del MAin Page") .locatedBy("//p[contains(text(),'{0}')]");
}
