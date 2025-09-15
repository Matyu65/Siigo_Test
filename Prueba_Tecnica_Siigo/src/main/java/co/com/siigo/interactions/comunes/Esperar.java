package co.com.siigo.interactions.comunes;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Esperar implements Interaction {
    private static int tiempoEsperar;

    @Override
    public <T extends Actor> void performAs(T actor) {
       try{
           Thread.sleep(tiempoEsperar);
       }catch (Exception e){e.printStackTrace();}
    }

    public static Esperar esperarSegundos(int tiempo)
    {
        tiempoEsperar=tiempo;
        return instrumented(Esperar.class);
    }
}
