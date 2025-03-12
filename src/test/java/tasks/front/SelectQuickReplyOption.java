package tasks.front;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import userinterface.ChatPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectQuickReplyOption implements Task {

    private final String option;

    public SelectQuickReplyOption(String option) {
        this.option = option;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                NavigateToChat.window(),

                WaitUntil.the(ChatPage.QUICK_REPLY.of(option), isVisible()).forNoMoreThan(5).seconds(),
                JavaScriptClick.on(ChatPage.QUICK_REPLY.of(option))
        );
    }

    public static SelectQuickReplyOption withText(String option) {
        return new SelectQuickReplyOption(option);
    }
}
