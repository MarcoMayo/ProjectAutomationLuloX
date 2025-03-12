package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.WebDriver;
import tasks.front.*;
import userinterface.ChatPage;

import java.util.List;

public class ChatLuloXStepDefinition {

    @Managed(driver = "chrome")
    private WebDriver browser;
    private Actor actor;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());

        actor = OnStage.theActorCalled("QA Frontend");
        actor.can(BrowseTheWeb.with(browser));
    }

    @Given("the user opens the LuloX homepage")
    public void theUserOpensTheLuloXHomepage() {
        actor.attemptsTo(Open.url("https://lulox.co/"));
    }

    @When("the user sends the message {string} through the chat")
    public void theUserSendsTheMessageThroughTheChat(String message) {
        actor.attemptsTo(
                NavigateToChat.window(),
                InteractWithChat.withMessage(message)
        );
    }

    @Then("the user should see the sent message {string} displayed in the chat")
    public void theUserShouldSeeTheSentMessageDisplayedInTheChat(String message) {
        actor.attemptsTo(
                Ensure.that(ChatPage.TEXT_BUBBLE.of(message)).isDisplayed()
        );
    }

    @When("the user selects the quick reply option {string}")
    public void theUserSelectsTheQuickReplyOption(String message) {
        actor.attemptsTo(SelectQuickReplyOption.withText(message));
    }

    @When("the user changes their name to {string}")
    public void theUserChangesTheirNameTo(String name) {
        actor.attemptsTo(ChangeUserNameInChat.to(name));
    }

    @Then("the user should see their name updated to {string} in the chat")
    public void theUserShouldSeeTheirNameUpdatedToInTheChat(String arg0) {//No es evidente el cambio de nombre
        actor.attemptsTo(
                Ensure.that(ChatPage.TEXT_BUBBLE.of("Bienvenido al chat de servicio de Lulo X")).isDisplayed()
        );
    }

    @When("the user sends the following messages through the chat:")
    public void theUserSendsTheFollowingMessagesThroughTheChat(DataTable data) {
        List<String> messages = data.asList();
        actor.attemptsTo(NavigateToChat.window());
        actor.attemptsTo(SendChatMessagesToAgent.with(messages));
    }

    @Then("the user should see the last messages displayed in the chat {string}")
    public void theUserShouldSeeTheLastMessagesDisplayedInTheChat(String message) {
        actor.attemptsTo(
                Ensure.that(ChatPage.LAST_MESSAGE_AGENT.of(message)).isDisplayed()
        );
    }
}
