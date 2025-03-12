package tasks.back;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultObjectById implements Task {

    private final String id;

    public ConsultObjectById(String id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/objects/" + id)
        );
    }

    public static ConsultObjectById withId(String id) {
        return instrumented(ConsultObjectById.class, id);
    }
}
