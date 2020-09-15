package cadastro;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import suporte.Suporte;
import suporte.Usuario;

public class HelperCadastro extends Suporte{
	
	private PgoCadastro pgo;
	
	public HelperCadastro() {
		pgo = new PgoCadastro();
	}
	
	public Usuario gerarUsuarioRandom() {
		return new Usuario("usuario_".concat(obterData("ddMMyyyyHHss")), obterData("ddMMyyyyHHss"));
	}
	
	public void preencherCampoUsuario(String usuario) {
		limparCampo(pgo.usuario);
		preencheCampo(pgo.usuario, usuario);
	}
	
	public void preencherCampoSenha(String senha) {
		preencheCampo(pgo.senha, senha);
	}
	
	public void preencherCampoConfirmarSenha(String senha) {
		preencheCampo(pgo.confirmar_senha, senha);
	}
	
	public void acionarBotaoConfirmar() {
		click(pgo.botao_confirmar);
	}
	
	public void acionarLinkCadastro() {
		click(pgo.link_cadastro);
	}
	
	public void acionarLinkLoginTopo() {
		click(pgo.link_topo_login);
	}
	
	public void acionarLinkCadastroTopo() {
		click(pgo.link_topo_cadastro);
	}
	
	public void validarLabels(boolean isTelaCadastro, String... labels) {
		verificacao(pgo.label_titulo, labels[0]);
		verificacao(pgo.label.get(0), labels[1]);
		verificacao(pgo.label.get(1), labels[2]);
		if(isTelaCadastro)
			verificacao(pgo.label.get(2), labels[3]);
	}
	
	public void validarCamposObrigatorio(String ... cmpValida) {
		int index = 0;
		for (WebElement campo: pgo.campo_obrigatorio) {
			verificacao(campo, "name", cmpValida[index]);
			index++;
		}
	}
	
	public void validarCampoMenorQue8Caracteres(String msg) {
		verificacao(pgo.usuario, "validationMessage", msg);
	}
	
	public void validarMensagemUsuarioJaCadastrado(String msg) {
		verificacao(pgo.msg_error_ja_cadastrado, msg);
	}
	
	public void validarMensagem(String msg) {
		verificacao(pgo.msg_sistema, msg);
		click(pgo.msg_close);
	}
	
	public void validarAcessoTabelaAposLoginSucesso() {
		aguardaElemento(ExpectedConditions.visibilityOf(pgo.tabela));
		assertTrue(pgo.tabela.isDisplayed());
	}
	

}
