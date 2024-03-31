package org.salesforce.models;


import java.util.Arrays;

public class TipoPlano {

	private int id;

	private String nome; 
	private String descricao;
	private double preco;
	private String tipoPreco;
	private int nivelPlano;
	private boolean testeGratisDisponivel;
	private Integer tipoProdutoId;
	private Integer recursosId;

	
	
	
	public TipoPlano() {
		super();
	}

	public TipoPlano(int id, String nome, String descricao, double preco, String tipoPreco, int nivelPlano, boolean testeGratisDisponivel, Integer tipoProdutoId, Integer recursosId) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.tipoPreco = tipoPreco;
		this.nivelPlano = nivelPlano;
		this.testeGratisDisponivel = testeGratisDisponivel;
		this.tipoProdutoId = tipoProdutoId;
		this.recursosId = recursosId;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getTipoPreco() {
		return tipoPreco;
	}
	public void setTipoPreco(String tipoPreco) {
		this.tipoPreco = tipoPreco;
	}
	public int getNivelPlano() {
		return nivelPlano;
	}
	public void setNivelPlano(int nivelPlano) {
		this.nivelPlano = nivelPlano;
	}
	public boolean isTesteGratisDisponivel() {
		return testeGratisDisponivel;
	}
	public void setTesteGratisDisponivel(boolean testeGratisDisponivel) {
		this.testeGratisDisponivel = testeGratisDisponivel;
	}

	public Integer getRecursosId() {
		return recursosId;
	}

	public void setRecursosId(Integer recursosId) {
		this.recursosId = recursosId;
	}

	public Integer getTipoProdutoId() {
		return tipoProdutoId;
	}

	public void setTipoProdutoId(Integer tipoProdutoId) {
		this.tipoProdutoId = tipoProdutoId;
	}

	@Override
	public String toString() {
		return "TipoPlano{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", preco=" + preco +
				", tipoPreco='" + tipoPreco + '\'' +
				", nivelPlano=" + nivelPlano +
				", testeGratisDisponivel=" + testeGratisDisponivel +
				'}';
	}
}
