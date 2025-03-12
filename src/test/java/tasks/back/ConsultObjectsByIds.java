package tasks.back;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultObjectsByIds implements Task {

    private final List<String> ids;

    public ConsultObjectsByIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/objects")
                        .with(request -> request.queryParam("id", ids))
        );
    }

    public static ConsultObjectsByIds with(List<String> ids) {
        return instrumented(ConsultObjectsByIds.class, ids);
    }
}

