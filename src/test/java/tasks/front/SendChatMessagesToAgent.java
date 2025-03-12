package tasks.front;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import userinterface.ChatPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.awaitility.Awaitility.await;

public class SendChatMessagesToAgent implements Task {

    private final List<String> messages;

    public SendChatMessagesToAgent(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        messages.forEach(message -> {
            int previousCount = ChatPage.MESSAGE_AGENT.resolveAllFor(actor).size();
            actor.attemptsTo(
                    Enter.theValue(message).into(ChatPage.TEXT_INPUT),
                    JavaScriptClick.on(ChatPage.BUTTON_SEND)
            );
            await().atMost(30, TimeUnit.SECONDS)
                    .pollInterval(2, TimeUnit.SECONDS)
                    .until(() -> {
                        int currentCount = ChatPage.MESSAGE_AGENT.resolveAllFor(actor).size();
                        return currentCount == previousCount + 1;
                    });
        });
    }

    public static SendChatMessagesToAgent with(List<String> messages) {
        return instrumented(SendChatMessagesToAgent.class, messages);
    }
}
