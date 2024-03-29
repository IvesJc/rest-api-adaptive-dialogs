package org.salesforce.models;

public class Recurso {

	private int id;
	private String nome;
	private String notasPreco;
	private String descricao;
	private String categoria;
	
	public Recurso() {
		super();
	}

	public Recurso(int id, String nome, String notasPreco, String descricao, String categoria) {
		this.id = id;
		this.nome = nome;
		this.notasPreco = notasPreco;
		this.descricao = descricao;
		this.categoria = categoria;
	}

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
	public String getNotasPreco() {
		return notasPreco;
	}
	public void setNotasPreco(String notasPreco) {
		this.notasPreco = notasPreco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Recurso{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", notasPreco='" + notasPreco + '\'' +
				", descricao='" + descricao + '\'' +
				", categoria='" + categoria + '\'' +
				'}';
	}
}
