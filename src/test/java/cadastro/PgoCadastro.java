package cadastro;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import suporte.PgoGeneric;

public class PgoCadastro extends PgoGeneric{
	
	/**
	 * Campos de acesso/cadastro
	 */
	
	@FindBy(name="username")
	public WebElement usuario;
	
	@FindBy(name="pass")
	public WebElement senha;
	
	@FindBy(name="confirmpass")
	public WebElement confirmar_senha;
	
	@FindBy(css=".container-login100 .login100-form-btn")
	public WebElement botao_confirmar;
	
	@FindBy(css=".navbar-nav [href='/accounts/login/']")
	public WebElement link_topo_login;
	
	@FindBy(css=".container-login100 [href='/accounts/signup/']")
	public WebElement link_cadastro;
	
	@FindBy(css=".navbar-nav [href='/accounts/signup/']")
	public WebElement link_topo_cadastro;
	
	/**
	 * Informaçoes de texo
	 */
	
	@FindBy(css=".container-login100 .login100-form-title")
	public WebElement label_titulo;
	
	@FindBy(css=".container-login100 .txt1")
	public List<WebElement> label;
	
	@FindBy(css=".alert-validate input")
	public List<WebElement> campo_obrigatorio;
	
	@FindBy(css=".txt1 .container-login100-form-btn")
	public WebElement msg_error_ja_cadastrado;

	
	/**
	 * Apos login
	 */
	
	@FindBy(id="tabela")
	public WebElement tabela;
	
	
	
}
