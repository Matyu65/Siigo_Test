package co.com.siigo.runners.crearcliente;

import co.com.siigo.utilities.exceldata.BeforeRunner;
import co.com.siigo.utilities.exceldata.DatosFeature;
import co.com.siigo.utilities.runner.RunnerSiigo;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;

import java.io.IOException;

@CucumberOptions(
    features ="src/test/resources/features/e2e/crear_cliente/crear_cliente.feature",
    snippets = SnippetType.CAMELCASE,
    glue = {"co.com.siigo.stepdefinitions"},
    plugin = {"json:target/cucumber_json/cucumber.json"})
@RunWith(RunnerSiigo.class)
public class crearClienteRunner {
    @BeforeRunner
    public static void test() throws InvalidFormatException, IOException {
    DatosFeature.overrideFeatureFiles(
        "./src/test/resources/features/e2e/crear_cliente");
    }
}
