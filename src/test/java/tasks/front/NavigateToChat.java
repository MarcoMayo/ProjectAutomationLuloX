package tasks.front;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userinterface.HomePage;

import java.time.Duration;
import java.util.Set;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToChat implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        driver.switchTo().frame(HomePage.FRAME_MAIN.resolveFor(actor));
        driver.switchTo().frame(HomePage.FRAME_CHAT.resolveFor(actor));

        actor.attemptsTo(
                Click.on(HomePage.CHAT_BUTTON)
        );

        Set<String> windows = driver.getWindowHandles();
        String currentWindow = driver.getWindowHandle();

        for (String window : windows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));

        driver.switchTo().frame(0);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static NavigateToChat window() {
        return instrumented(NavigateToChat.class);
    }
}
