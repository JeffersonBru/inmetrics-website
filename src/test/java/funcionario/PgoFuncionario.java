package funcionario;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import suporte.PgoGeneric;

public class PgoFuncionario extends PgoGeneric{
	
	/**
	 * Opcoes nav-bar
	 */
	
	@FindBy(css="#navbarSupportedContent [href='/empregados/']")
	public WebElement link_funcionarios;
	
	@FindBy(css="#navbarSupportedContent [href='/empregados/new_empregado']")
	public WebElement link_novo_funcionario;
	
	@FindBy(css="#tabela_filter input")
	public WebElement campo_filtro_funcionario;
	
	@FindBy(id="delete-btn")
	public WebElement botao_deletar_funcionario;
	
	@FindBy(css="#tabela .fa-pencil")
	public WebElement botao_editar_funcionario;
	
	/**
	 * Campos cadastro
	 */
	
	@FindBy(id="inputNome")
	public WebElement campo_nome;
	
	@FindBy(id="inputCargo")
	public WebElement campo_cargo;
	
	@FindBy(id="cpf")
	public WebElement campo_cpf;
	
	@FindBy(id="dinheiro")
	public WebElement campo_salario;
	
	@FindBy(id="slctSexo")
	public WebElement combo_Sexo;
	
	@FindBy(id="inputAdmissao")
	public WebElement campo_admissao;
	
	@FindBy(id="clt")
	public WebElement opcao_clt;
	
	@FindBy(id="pj")
	public WebElement opcao_pj;
	
	/**
	 * Botoes
	 */
	
	@FindBy(css=".cadastrar-form-btn")
	public WebElement botao_enviar;
	
	@FindBy(css=".cancelar-form-btn")
	public WebElement botao_cancelar;
	
	/**
	 * Tabela
	 */
	
	@FindBy(css=".dataTables_empty")
	public WebElement msg_nenhum_registro;
	
	@FindBy(css="#tabela tr:nth-child(1) td:not(:last-child)")
	public List<WebElement> informacoes_funcionario;
	
	@FindBy(id="tabela_next")
	public WebElement proxima_lista;
	
	@FindBy(css=".paginate_button:last-child")
	public WebElement info_ultima_pagina_lista;
	
}
