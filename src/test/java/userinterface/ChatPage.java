package userinterface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ChatPage {

    public static final Target TEXT_INPUT = Target.the("Chat input text")
            .located(By.xpath("(//*[@id=\"tawk-chatinput-container\"]/div/div[3]/textarea)[2]"));

    public static final Target BUTTON_BURGUER = Target.the("")
            .located(By.xpath("//*[@class='tawk-dropdown tawk-toolbar-menu']//button"));

    public static final Target BUTTON_SEND = Target.the("Send message")
            .located(By.xpath("//button[@class='tawk-chatinput-send tawk-tooltip']"));

    public static final Target TEXT_BUBBLE = Target.the("Text bubble message")
            .locatedBy("//*[contains(@class,'tawk-chat-bubble')]//*[contains(text(),'{0}')]");

    public static final Target QUICK_REPLY = Target.the("Quick reply option button")
            .locatedBy("//*[contains(@class,'tawk-text-left') and contains(text(),'{0}')]");

    public static final Target CHANGE_NAME = Target.the("Cahnge name")
            .located(By.xpath("//button[@title='Cambiar nombre']"));

    public static final Target INPUT_NAME = Target.the("Input name")
            .located(By.xpath("//input[@type='name']"));

    public static final Target BUTTON_SEND_NAME = Target.the("Button send name")
            .located(By.xpath("//button[@title='Enviar']"));

    public static final Target MESSAGE_AGENT = Target.the("Message from agent")
            .located(By.xpath("//*[@class='tawk-message-bubble']//*[contains(@class,'agent-chat-bubble')]"));

    public static final Target LAST_MESSAGE_AGENT = Target.the("Message from agent")
            .locatedBy("(//*[contains(@class,'agent-chat-bubble')])[last()]//*[contains(text(),'{0}')]");

}
