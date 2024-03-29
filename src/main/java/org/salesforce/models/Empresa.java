package org.salesforce.models;


import java.util.Arrays;

public class Empresa {

	private int id;
	private String nome;
	private String tipoIndustria;
	private String tamanho;
	private Cliente representante;
	private String paisSede;
	private Produto[] produtosContratados;
	private Funcionario gestorSalesforce;
	
	
	
	public Empresa() {
		super();
	}

	public Empresa(int id, String nome, String tipoIndustria, String tamanho, Cliente representante, String paisSede, Produto[] produtosContratados, Funcionario gestorSalesforce) {
		this.id = id;
		this.nome = nome;
		this.tipoIndustria = tipoIndustria;
		this.tamanho = tamanho;
		this.representante = representante;
		this.paisSede = paisSede;
		this.produtosContratados = produtosContratados;
		this.gestorSalesforce = gestorSalesforce;
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
	public String getTipoIndustria() {
		return tipoIndustria;
	}
	public void setTipoIndustria(String tipoIndustria) {
		this.tipoIndustria = tipoIndustria;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public Cliente getRepresentante() {
		return representante;
	}
	public void setRepresentante(Cliente representante) {
		this.representante = representante;
	}
	public String getPaisSede() {
		return paisSede;
	}
	public void setPaisSede(String paisSede) {
		this.paisSede = paisSede;
	}
	public Produto[] getProdutosContratados() {
		return produtosContratados;
	}
	public void setProdutosContratados(Produto[] produtosContratados) {
		this.produtosContratados = produtosContratados;
	}
	public Funcionario getGestorSalesforce() {
		return gestorSalesforce;
	}
	public void setGestorSalesforce(Funcionario gestorSalesforce) {
		this.gestorSalesforce = gestorSalesforce;
	}

	@Override
	public String toString() {
		return "Empresa{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", tipoIndustria='" + tipoIndustria + '\'' +
				", tamanho='" + tamanho + '\'' +
				", representante=" + representante +
				", paisSede='" + paisSede + '\'' +
				", produtosContratados=" + Arrays.toString(produtosContratados) +
				", gestorSalesforce=" + gestorSalesforce +
				'}';
	}
}
