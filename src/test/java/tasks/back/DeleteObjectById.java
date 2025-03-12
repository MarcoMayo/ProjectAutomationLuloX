package tasks.back;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteObjectById implements Task {

    private final String objectId;

    public DeleteObjectById(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/objects/" + objectId)
        );
    }

    public static DeleteObjectById withId(String id) {
        return instrumented(DeleteObjectById.class, id);
    }
}
