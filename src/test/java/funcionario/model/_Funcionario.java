package funcionario.model;

import funcionario.type.Sexo;
import funcionario.type.TipoContratacao;

public class _Funcionario {
	
	public String nome, cpf = "293.705.030-13", cargo, salario, admissao; 
	public Sexo sexo;
	public TipoContratacao contratacao;
	
	public _Funcionario(String nome, String cpf, String cargo, String salario, String admissao, Sexo sexo, TipoContratacao contratacao) {
		this.nome = nome;
		this.cargo = cargo;
		this.salario = salario;
		this.admissao = admissao;
		this.sexo = sexo;
		this.contratacao = contratacao;
	}
	
}
