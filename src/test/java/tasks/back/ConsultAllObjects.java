package tasks.back;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultAllObjects implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/objects")
        );
    }

    public static ConsultAllObjects fromApi() {
        return instrumented(ConsultAllObjects.class);
    }
}

