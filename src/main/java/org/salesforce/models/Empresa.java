package org.salesforce.models;


import java.util.Arrays;

public class Empresa {

	private int id;
	private String nome;
	private String tipoIndustria;
	private String tamanho;
	private String paisSede;
	private Integer clienteId;
	private Integer funcionarioId;
	private Integer produtoId;

	public Empresa() {
		super();
	}

	public Empresa(int id, String nome, String tipoIndustria, String tamanho, String paisSede, Integer clienteId, Integer funcionarioId, Integer produtoId) {
		this.id = id;
		this.nome = nome;
		this.tipoIndustria = tipoIndustria;
		this.tamanho = tamanho;
		this.paisSede = paisSede;
		this.clienteId = clienteId;
		this.funcionarioId = funcionarioId;
		this.produtoId = produtoId;
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
	public String getPaisSede() {
		return paisSede;
	}
	public void setPaisSede(String paisSede) {
		this.paisSede = paisSede;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	@Override
	public String toString() {
		return "Empresa{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", tipoIndustria='" + tipoIndustria + '\'' +
				", tamanho='" + tamanho + '\'' +
				", paisSede='" + paisSede + '\'' +
				'}';
	}
}
