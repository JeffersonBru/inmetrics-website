package suporte;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Suporte {

	/**
	 * Aguardo o Elemento ser carregado conforme uma expectativa. Tempo definido por
	 * padrao
	 * 
	 * @param expect - ExpectedCondition<'WebElement'>
	 */
	protected WebElement aguardaElemento(ExpectedCondition<WebElement> expect) {
		return Capabilities.getWait().until(ExpectedConditions.refreshed(expect));
	}
	
	
	/**
	 * Aguardo o Elemento HTML ser carregado na tela podendo ser como entrada um
	 * ExpectedCondition<'Boolean'>. Tempo definido por padrao no CrossBrowser.
	 * 
	 * @param expect - tem que ser diferente de ExpectedCondition<'WebElement'>
	 */
	protected void aguardaElemento(Function<WebDriver, ?> expect) {
		Capabilities.getWait().until(ExpectedConditions.refreshed((ExpectedCondition<?>) expect));
	}
	
	
	/**
	 * Click no elemento
	 * 
	 * @param elemento
	 */
	protected void click(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).click();
	}
	
	/**
	 * Click no elemento
	 * 
	 * @param elemento
	 */
	protected void limparCampo(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).clear();
	}

	/**
	 * Preenche campo
	 * 
	 * @param elemento
	 * @param valor
	 */
	protected void preencheCampo(WebElement elemento, Object valor) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).sendKeys((String) valor);
	}
	
	
	/**
	 * Preenche o campo com Actions
	 * 
	 * @param elemento
	 * @param valor
	 */
	protected void preencheCampoForce(WebElement elemento, String valor) {
		 JavascriptExecutor jse = (JavascriptExecutor)Capabilities.getDriver();
		 jse.executeScript("arguments[0].value='"+valor+"';", elemento);
	}
	
	/**
	 * Verificacao geral
	 * 
	 */
	protected void verificacao(WebElement elemento, String check) {
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		assertTrue(elemento.getText().contains(check));
	}
	
	protected void verificacao(WebElement elemento, String atributo, String check) {
		aguardaElemento(ExpectedConditions.visibilityOf(elemento));
		assertEquals(elemento.getAttribute(atributo), check);
	}
	
	/**
	 * Randomizar qualquer array de string
	 * @param array
	 * @return
	 */
	public String random(String array[]) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(array.length);
		return array[randomIndex];
	}
	
	/**
	 * Obter data atual de acordo com o formato
	 * @param format
	 * @return
	 */
	public String obterData(String format) {
		DateFormat formato = new SimpleDateFormat(format);
		Date data = new Date();
		return formato.format(data).toString();
	}
	
	/**
	 * Obter um objeto do tipo select
	 * @param elemento
	 * @return
	 */
	public Select selecionarOpcao(WebElement elemento) {
		aguardaElemento(ExpectedConditions.elementToBeClickable(elemento));
		return new Select(elemento);
	}
	
	/**
	 * Metodo para simular teclas do teclado fisico
	 * @param keys
	 */
	protected void acoesTeclado(Keys... keys) {
		for (Keys key : keys) {
			new Actions(Capabilities.getDriver()).sendKeys(key).build().perform();
		}
	}
	
}
