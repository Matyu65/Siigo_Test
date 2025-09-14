package co.com.siigo.utilities.constant;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConstantSIIGO {
  /** The constant HOUR_FORMAT. */
  public static final String HOUR_FORMAT = "HHmmss";
  /** The constant EMPTY. */
  public static final String EMPTY = "";
  /** The constant NUMBER_FORMAT. */
  public static final String NUMBER_FORMAT = "###,###.###";
  /** The constant TRANSACTION_TYPE_TRANSFER. */
  public static final String REPORTS_SERENITY = "reportSerenity";
  /** The constant INTERFACE_TYPE*/
  public static final String CLASE_CONSTANTE = "Clase Constante";


  /** The constant INTERFACE_OPTION_PROCESOS*/
  public static final Target OPC_PROCESOS = Target.the("opcion PRINCIPAL del menu ").located(By.xpath(" //a[@id='mProcesosBPM']"));

  private ConstantSIIGO() {}
}
