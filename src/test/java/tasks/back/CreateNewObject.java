package tasks.back;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ObjectData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateNewObject implements Task {

    private final ObjectData objectData;

    public CreateNewObject(ObjectData objectData) {
        this.objectData = objectData;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String requestBody = objectData.toJson();
        actor.remember("object_name", objectData.getName());

        actor.attemptsTo(
                Post.to("/objects")
                        .with(request -> request
                                .header("Content-Type", "application/json")
                                .body(requestBody))
        );

        Response response = LastResponse.received().answeredBy(actor);
        String id = response.jsonPath().getString("id");
        actor.remember("object_id", id);
        actor.remember("last_response", response);
    }

    public static CreateNewObject withData(ObjectData data) {
        return instrumented(CreateNewObject.class, data);
    }
}

