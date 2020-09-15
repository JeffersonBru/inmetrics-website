package cadastro;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bsh.This;
import suporte.Capabilities;
import suporte.LeitorProperties;
import suporte.Usuario;

public class Cadastro extends Capabilities{
	
	HelperCadastro cad;
	Usuario usuario;
	Properties prop;
	
	@BeforeClass
	public void init() {
		prop = new LeitorProperties("datapool/datapool_login.properties").getProperties();
		htmlReporter.config().setReportName(This.class.getSimpleName());
		cad = new HelperCadastro();
		this.usuario = cad.gerarUsuarioRandom();
	}
	
	@Test
	public void ct01_camposObrigatorios() {
		test = extent.createTest("CT01", "Campos obrigatórios - Login");
		cad.validarLabels(false, prop.get("label.login").toString().split(";"));
		cad.acionarBotaoConfirmar();
		cad.validarCamposObrigatorio(prop.get("campos.obrigatorios").toString().split(";"));
	}
	
	@Test
	public void ct02_cadastroUsuarioCamposObrigatorios() {
		test = extent.createTest("CT02", "Campos obrigatórios - Cadastro");
		cad.acionarLinkCadastroTopo();
		cad.validarLabels(false, prop.get("label.cadastro").toString().split(";"));
		cad.acionarBotaoConfirmar();
		cad.validarCamposObrigatorio(prop.get("campos.obrigatorios").toString().split(";"));
	}
	
	@Test
	public void ct03_cadastroUsuarioMenorQue8Caractere() {
		test = extent.createTest("CT03", "Usuario menor que 8 caractere");
		cad.preencherCampoUsuario("aaa");
		cad.acionarBotaoConfirmar();
		cad.validarCampoMenorQue8Caracteres(prop.get("msg.minlength").toString());
	}
	
	@Test
	public void ct04_cadastroUsuario() {
		test = extent.createTest("CT04", "Cadastro Usuario");
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.preencherCampoConfirmarSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
	}
	
	@Test
	public void ct05_usuarioJaCadastrado() {
		test = extent.createTest("CT05", "Usuario já cadastrado");
		cad.acionarLinkCadastro();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.preencherCampoConfirmarSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
		cad.validarMensagemUsuarioJaCadastrado(prop.get("msg.usuario.cadastrado").toString());
	}
	
	@Test
	public void ct06_senhaInvalida() {
		test = extent.createTest("CT06", "Senha inválida");
		cad.acionarLinkLoginTopo();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha("aaa");
		cad.acionarBotaoConfirmar();
		cad.validarMensagem(prop.get("msg.usuario.senha.invalida").toString());
	}
	
	@Test
	public void ct07_acessoComSucesso() {
		test = extent.createTest("CT07", "Acesso ao sistema");
		cad.acionarLinkLoginTopo();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
		cad.validarAcessoTabelaAposLoginSucesso();
	}

}
