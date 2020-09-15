package funcionario;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import funcionario.model._Funcionario;
import funcionario.type.Sexo;
import funcionario.type.TipoContratacao;
import suporte.Capabilities;
import suporte.GeradorCPF;
import suporte.Suporte;

public class HelperFuncionario extends Suporte{
	
	private String cargos [] = new String[] {"Analista de sistemas", "Analista de Testes", "QA Engineer", "Arquiteto de Testes", "Automatizador de Testes", "Testador", "Professor"};
	
	PgoFuncionario pgo;
	
	public HelperFuncionario() {
		pgo = new PgoFuncionario();
	}
	
	public _Funcionario gerarFuncionarioRandom() {
		TipoContratacao contratacao = TipoContratacao.getRandom();
		return new _Funcionario("Funcionario_".concat(obterData("ddMMyyyyHHmmss")), new GeradorCPF().geraCPFComMask(), random(cargos), obterData("HHmmss"), obterData("dd/MM/yyyy"), Sexo.getRandom(), contratacao);
	}
	
	public void acionarLinkFuncionarcios() {
		click(pgo.link_funcionarios);
	}
	
	public void acionarLinkNovoFuncionario() {
		click(pgo.link_novo_funcionario);
	}
	
	public void pesquisarFuncionario(String dadoFuncionario) {
		limparCampo(pgo.campo_filtro_funcionario);
		preencheCampo(pgo.campo_filtro_funcionario, dadoFuncionario);
	}
	
	public void acionarDeletarFuncionario() {
		click(pgo.botao_deletar_funcionario);
	}
	
	public void acionarEditarFuncionario() {
		click(pgo.botao_editar_funcionario);
	}
	
	public void preencherNome(String nome) {
		limparCampo(pgo.campo_nome);
		preencheCampo(pgo.campo_nome, nome);
	}
	
	public void preencherCargo(String cargo) {
		limparCampo(pgo.campo_cargo);
		preencheCampo(pgo.campo_cargo, cargo);
	}
	
	public void preencherCpf(String cpf) {
		limparCampo(pgo.campo_cpf);
		preencheCampoForce(pgo.campo_cpf, cpf);
	}
	
	public void acionarTabTeclado() {
		acoesTeclado(Keys.TAB);
	}
	
	public void preencherSalario(String salario) {
		limparCampo(pgo.campo_salario);
		preencheCampo(pgo.campo_salario, salario);
	}
	
	public void selecionarSexo(Sexo sexo) {
		selecionarOpcao(pgo.combo_Sexo).selectByVisibleText(sexo.getValue());
	}
	
	public void preencherDataAdmissao(String dataAdmissao) {
		limparCampo(pgo.campo_admissao);
		preencheCampo(pgo.campo_admissao, dataAdmissao);
	}
	
	public void selecionarTipoContratacao(TipoContratacao contratacao) {
		switch (contratacao) {
		case CLT:
			click(pgo.opcao_clt);
			break;
		default:
			click(pgo.opcao_pj);
			break;
		}
	}
	
	public void acionarEnviar() {
		click(pgo.botao_enviar);
	}
	
	public void acionarCancelar() {
		click(pgo.botao_cancelar);
	}
	
	public void validarListaFuncionario(_Funcionario funcionario) {
		verificacao(pgo.informacoes_funcionario.get(0), funcionario.nome);
		verificacao(pgo.informacoes_funcionario.get(1), funcionario.cpf);
		verificacao(pgo.informacoes_funcionario.get(2), funcionario.sexo.getValue());
		verificacao(pgo.informacoes_funcionario.get(3), funcionario.cargo);
		verificacao(pgo.informacoes_funcionario.get(4), funcionario.admissao);
	}
	
	public void validarMsgSemRegistro(String msg) {
		verificacao(pgo.msg_nenhum_registro, msg);
	}

	public void validarNomeObrigatorio(String msg) {
		verificacao(pgo.campo_nome, "validationMessage", msg);
	}
	
	public void validarcpfObrigatorio(String msg) {
		verificacao(pgo.campo_cpf, "validationMessage", msg);
	}
	
	public void validarSexoObrigatorio(String msg) {
		verificacao(pgo.combo_Sexo, "validationMessage", msg);
	}
	
	public void validarAdmissaoObrigatoria(String msg) {
		verificacao(pgo.campo_admissao, "validationMessage", msg);
	}
	
	public void validarCargoObrigatorio(String msg) {
		verificacao(pgo.campo_cargo, "validationMessage", msg);
	}
	
	public void validarSalarioObrigatorio(String msg) {
		verificacao(pgo.campo_salario, "validationMessage", msg);
	}
	
	public void validarTipoContratacaoObrigatorio(String msg) {
		verificacao(pgo.opcao_clt, "validationMessage", msg);
	}
	
	public void validarAlert(String msg) {
		aguardaElemento(ExpectedConditions.alertIsPresent());
		Alert alerta = Capabilities.getDriver().switchTo().alert();
		assertEquals(alerta.getText(), msg);
		alerta.accept();
	}
	
	
}
