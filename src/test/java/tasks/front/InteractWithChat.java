package tasks.front;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import userinterface.ChatPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class InteractWithChat implements Task {

    private final String message;

    public InteractWithChat(String message) {
        this.message = message;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Enter.theValue(message).into(ChatPage.TEXT_INPUT),
                JavaScriptClick.on(ChatPage.BUTTON_SEND)
        );

    }

    public static InteractWithChat withMessage(String message) {
        return instrumented(InteractWithChat.class, message);
    }
}
