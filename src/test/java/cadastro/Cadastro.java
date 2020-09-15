package cadastro;

import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import suporte.Capabilities;
import suporte.LeitorProperties;
import suporte.Usuario;

public class Cadastro extends Capabilities{
	
	HelperCadastro cad;
	Usuario usuario;
	Properties prop = new LeitorProperties("datapool/datapool_login.properties").getProperties();
	
	@BeforeClass
	public void init() {
		cad = new HelperCadastro();
		this.usuario = cad.gerarUsuarioRandom();
	}
	
	@Test
	public void ct01_camposObrigatorios() {
		cad.validarLabels(false, prop.get("label.login").toString().split(";"));
		cad.acionarBotaoConfirmar();
		cad.validarCamposObrigatorio(prop.get("campos.obrigatorios").toString().split(";"));
	}
	
	@Test
	public void ct02_cadastroUsuarioCamposObrigatorios() {
		cad.acionarLinkCadastroTopo();
		cad.validarLabels(false, prop.get("label.cadastro").toString().split(";"));
		cad.acionarBotaoConfirmar();
		cad.validarCamposObrigatorio(prop.get("campos.obrigatorios").toString().split(";"));
	}
	
	@Test
	public void ct03_cadastroUsuarioMenorQue8Caractere() {
		cad.preencherCampoUsuario("aaa");
		cad.acionarBotaoConfirmar();
		cad.validarCampoMenorQue8Caracteres(prop.get("msg.minlength").toString());
	}
	
	@Test
	public void ct04_cadastroUsuario() {
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.preencherCampoConfirmarSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
	}
	
	@Test
	public void ct05_usuarioJaCadastrado() {
		cad.acionarLinkCadastro();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.preencherCampoConfirmarSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
		cad.validarMensagemUsuarioJaCadastrado(prop.get("msg.usuario.cadastrado").toString());
	}
	
	@Test
	public void ct06_senhaInvalida() {
		cad.acionarLinkLoginTopo();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha("aaa");
		cad.acionarBotaoConfirmar();
		cad.validarMensagem(prop.get("msg.usuario.senha.invalida").toString());
	}
	
	@Test
	public void ct07_acessoComSucesso() {
		cad.acionarLinkLoginTopo();
		cad.preencherCampoUsuario(usuario.usr);
		cad.preencherCampoSenha(usuario.senha);
		cad.acionarBotaoConfirmar();
		cad.validarAcessoTabelaAposLoginSucesso();
	}

}
