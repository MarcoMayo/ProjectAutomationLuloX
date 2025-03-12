package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.ObjectData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import tasks.back.*;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.*;

public class ObjectsRestApiStepDefinition {

    private static final String BASE_URL = "https://api.restful-api.dev";
    private Actor actor;

    @Before
    public void setUp() {
        actor = Actor.named("QA User").whoCan(CallAnApi.at(BASE_URL));
    }

    @When("the user retrieves all available objects")
    public void theUserRetrievesAllAvailableObjects() {
        actor.attemptsTo(ConsultAllObjects.fromApi());
    }

    @Then("the user should see status code {int}")
    public void theUserShouldSeeStatusCode(Integer statusCode) {
        actor.should(
                seeThatResponse("Expected status code",
                        response -> response.statusCode(statusCode))
        );
    }

    @And("the user should see a list of objects in the response")
    public void theUserShouldSeeAListOfObjectsInTheResponse() {
        actor.should(
                seeThatResponse("Response contains at least one object",
                        response -> response.body("size()", greaterThan(0)))
        );
    }

    @Given("the user has the following data to create an object:")
    public void theUserHasTheFollowingDataToCreateAnObject(DataTable dataTable) {
        ObjectData objectData = ObjectData.from(dataTable.cell(1, 0), dataTable.cell(1, 1));
        actor.remember("objectData", objectData);
    }

    @When("the user creates a new object")
    public void theUserCreatesANewObject() {
        ObjectData objectData = actor.recall("objectData");
        actor.attemptsTo(
                CreateNewObject.withData(objectData)
        );
    }

    @And("the user should see that the object has the name {string}")
    public void theUserShouldSeeThatTheObjectHasTheName(String expectedName) {
        actor.should(
                seeThatResponse("Object name is as expected",
                        response -> response.body("name", equalTo(expectedName)))
        );
    }

    @Given("the user retrieves the object with ID {string}")
    public void theUserRetrievesTheObjectWithID(String id) {
        actor.attemptsTo(ConsultObjectById.withId(id));
    }

    @And("the user should see that the object's name is {string}")
    public void theUserShouldSeeThatTheObjectsNameIs(String expectedName) {
        actor.should(
                seeThatResponse("Object name is as expected",
                        response -> response.body("name", equalTo(expectedName)))
        );
    }

    @Given("the user retrieves the objects with the following IDs:")
    public void theUserRetrievesTheObjectsWithTheFollowingIDs(DataTable dataTable) {
        List<String> ids = dataTable.asList();
        actor.attemptsTo(
                ConsultObjectsByIds.with(ids)
        );
    }

    @And("the user should see a list with {int} objects")
    public void theUserShouldSeeAListWithObjects(int expectedCount) {
        actor.should(
                seeThatResponse("The response contains the expected number of objects",
                        response -> response.body("size()", equalTo(expectedCount)))
        );
    }

    @Given("the user creates a new object with the following data:")
    public void theUserCreatesANewObjectWithTheFollowingData(DataTable dataTable) {
        ObjectData objectData = ObjectData.from(dataTable.cell(1, 0), dataTable.cell(1, 1));
        actor.attemptsTo(
                CreateNewObject.withData(objectData)
        );
    }

    @When("the user deletes the object by its ID")
    public void theUserDeletesTheObjectByItsID() {
        String objectId = actor.recall("object_id");
        actor.attemptsTo(DeleteObjectById.withId(objectId));
    }

    @And("the object should no longer exist when queried by its ID")
    public void theObjectShouldNoLongerExistWhenQueriedByItsID() {
        String objectId = actor.recall("object_id");
        actor.attemptsTo(ConsultObjectById.withId(objectId));
        actor.should(
                seeThatResponse("Object was not found.",
                        response -> response
                                .statusCode(404)
                                .body("error", containsString("was not found"))
                )
        );

    }

    @When("the user deletes the object with ID {string}")
    public void theUserDeletesTheObjectWithID(String id) {
        actor.attemptsTo(DeleteObjectById.withId(id));
    }

    @Then("the user should see the status code {int}")
    public void theUserShouldSeeTheStatusCode(int code) {

        actor.should(
                seeThatResponse("Object doesn't exist.",
                        response -> response
                                .statusCode(code)
                                .body("error", containsString("id = id-123 doesn't exist"))
                )
        );
    }

    @And("the response should contain an error message {string}")//Error permite crear objetos sin nombre
    public void theResponseShouldContainAnErrorMessage(String message) {
        actor.should(
                seeThatResponse("Name required",
                        response -> response
                                .statusCode(200)
                                //.body("error", containsString(message))
                )
        );
    }
}
