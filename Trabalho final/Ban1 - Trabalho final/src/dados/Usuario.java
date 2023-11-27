package dados;

import java.sql.Date;

public class Usuario {

	private String nome;
	private String cpf;
	private Date data_nascimento;
	private String cidade;
	private String estado;
	private Date data_inclusao;
	private int id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getData_inclusao() {
		return data_inclusao;
	}
	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}