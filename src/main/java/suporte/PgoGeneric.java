package suporte;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PgoGeneric {
	
	public PgoGeneric() {
		PageFactory.initElements(Capabilities.getDriver(), this);
	}
	
	@FindBy(css=".container-message .alert")
	public WebElement msg_sistema;
	
	
	@FindBy(css=".container-message [class='close']")
	public WebElement msg_close;


}
