package funcionario;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bsh.This;
import cadastro.HelperCadastro;
import funcionario.model._Funcionario;
import suporte.Capabilities;
import suporte.GeradorCPF;
import suporte.LeitorProperties;
import suporte.Usuario;

public class Funcionario extends Capabilities{
	
	HelperFuncionario fun;
	HelperCadastro cad;
	Usuario usuario;
	_Funcionario funcionario;
	Properties prop;
	
	@BeforeClass
	public void init() {
		prop = new LeitorProperties("datapool/datapool_funcionario.properties").getProperties();
		htmlReporter.config().setReportName(This.class.getSimpleName());
		fun = new HelperFuncionario();
		cad = new HelperCadastro();
		usuario = new Usuario(prop.getProperty("usuario").toString(), prop.getProperty("senha").toString());
		funcionario = fun.gerarFuncionarioRandom();
		realizarLogin();
	}
	
	@Test
	public void ct01_nenhumRegistro() {
		test = extent.createTest("CT01", "Nenhum registro");
		fun.pesquisarFuncionario("@#$");
		fun.validarMsgSemRegistro(prop.getProperty("msg.nenhum.registro").toString());
	}
	
	@Test
	public void ct02_camposObrigatorios() {
		test = extent.createTest("CT02", "Campos obrigat�rios");
		String msg_campo = prop.getProperty("msg.campo.obrigatorio").toString();
		fun.acionarLinkNovoFuncionario();
		fun.acionarEnviar();
		fun.validarNomeObrigatorio(msg_campo);
		fun.preencherNome(funcionario.nome);
		fun.acionarEnviar();
		fun.validarcpfObrigatorio(msg_campo);
		fun.preencherCpf(funcionario.cpf);
		fun.acionarEnviar();
		fun.validarSexoObrigatorio(prop.getProperty("msg.lista.obrigatoria").toString());
		fun.selecionarSexo(funcionario.sexo);
		fun.acionarEnviar();
		fun.validarAdmissaoObrigatoria(msg_campo);
		fun.preencherDataAdmissao(funcionario.admissao);
		fun.acionarEnviar();
		fun.validarCargoObrigatorio(msg_campo);
		fun.preencherCargo(funcionario.cargo);
		fun.acionarEnviar();
		fun.validarSalarioObrigatorio(msg_campo);
		fun.preencherSalario(funcionario.salario);
		fun.acionarEnviar();
		fun.validarTipoContratacaoObrigatorio(prop.getProperty("msg.opcao.obrigatoria").toString());
	}
	
	@Test
	public void ct03_cpfInvalido() {
		test = extent.createTest("CT03", "CPF invalido");
		fun.acionarLinkNovoFuncionario();
		fun.preencherCpf("123");
		fun.acionarTabTeclado();
		fun.validarAlert(prop.getProperty("msg.cpf.invalido").toString());
	}
	
	@Test 
	public void ct04_dataAdmissaoInvalida() {
		test = extent.createTest("CT04", "Data admissao invalida");
		fun.acionarLinkNovoFuncionario();
		fun.preencherNome(funcionario.nome);
		fun.preencherCpf(funcionario.cpf);
		fun.preencherDataAdmissao("112255");
		fun.acionarEnviar();
		fun.validarAlert(prop.getProperty("msg.admissao.invalida").toString());
		fun.acionarCancelar();
	}
	
	@Test
	public void ct05_cadastarFuncionario() {
		test = extent.createTest("CT06", "Cadastrar Funcionario");
		fun.acionarLinkNovoFuncionario();
		fun.preencherNome(funcionario.nome);
		fun.preencherCpf(funcionario.cpf);
		fun.selecionarSexo(funcionario.sexo);
		fun.preencherDataAdmissao(funcionario.admissao);
		fun.preencherSalario(funcionario.salario);
		fun.preencherCargo(funcionario.cargo);
		fun.selecionarTipoContratacao(funcionario.contratacao);
		fun.acionarEnviar();
		cad.validarMensagem(prop.getProperty("msg.sucesso.cadastrado").toString());
	}
	
	@Test
	public void ct06_consultarFuncionario() {
		test = extent.createTest("CT01", "Campos obrigat�rios - Login");
		fun.pesquisarFuncionario(funcionario.nome);
		fun.validarListaFuncionario(funcionario);
	}
	
	@Test 
	public void ct07_editarFuncionario() {
		test = extent.createTest("CT07", "Editar Funcionario");
		fun.acionarLinkFuncionarcios();
		fun.pesquisarFuncionario(funcionario.cpf);
		fun.acionarEditarFuncionario();
		funcionario = fun.gerarFuncionarioRandom();
		fun.preencherNome(funcionario.nome);
		fun.preencherCpf(funcionario.cpf);
		fun.selecionarSexo(funcionario.sexo);
		fun.preencherDataAdmissao(funcionario.admissao);
		fun.preencherSalario(funcionario.salario);
		fun.preencherCargo(funcionario.cargo);
		fun.selecionarTipoContratacao(funcionario.contratacao);
		fun.acionarEnviar();
		fun.pesquisarFuncionario(new GeradorCPF().formatar(funcionario.cpf));
		fun.validarListaFuncionario(funcionario);
		cad.validarMensagem(prop.getProperty("msg.sucesso.edicao").toString());
	}
	
	@Test
	public void ct08_excluirFuncionario() {
		test = extent.createTest("CT08", "Excluir funcionario");
		fun.acionarLinkFuncionarcios();
		fun.pesquisarFuncionario(funcionario.nome);
		fun.acionarDeletarFuncionario();
		cad.validarMensagem(prop.getProperty("msg.sucesso.removido").toString());
	}
	
	private void realizarLogin() {
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
	}
	
	

}
