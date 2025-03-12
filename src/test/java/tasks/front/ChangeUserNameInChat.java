package tasks.front;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import userinterface.ChatPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ChangeUserNameInChat implements Task {

    private final String name;

    public ChangeUserNameInChat(String name) {
        this.name = name;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                NavigateToChat.window(),
                JavaScriptClick.on(ChatPage.BUTTON_BURGUER),

                WaitUntil.the(ChatPage.CHANGE_NAME, isVisible()).forNoMoreThan(5).seconds(),
                JavaScriptClick.on(ChatPage.CHANGE_NAME),

                WaitUntil.the(ChatPage.INPUT_NAME, isVisible()).forNoMoreThan(5).seconds(),
                Enter.keyValues(name).into(ChatPage.INPUT_NAME),
                JavaScriptClick.on(ChatPage.BUTTON_SEND_NAME)
        );
    }

    public static ChangeUserNameInChat to(String name) {
        return new ChangeUserNameInChat(name);
    }
}
