package dados;

import java.sql.Date;

public class Rede {

	private int id;
	private String nome;
	private String cidade;
	private String estado;
	private String cnpj;
	private String segmento;
	private Date data_inclusao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public Date getData_inclusao() {
		return data_inclusao;
	}
	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}
	
}
