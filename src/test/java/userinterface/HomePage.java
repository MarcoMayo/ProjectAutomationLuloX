package userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target CHAT_BUTTON = Target.the("Chat")
            .located(By.id("tawk-bubble-container"));

    public static final Target FRAME_MAIN = Target.the("iframe")
            .located(By.xpath("//*[@id='root']//iframe"));

    public static final Target FRAME_CHAT = Target.the("iframe")
            .located(By.xpath("(//iframe[@title='chat widget'])[2]"));

}
