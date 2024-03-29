package org.salesforce.models;


import java.util.Arrays;

public class TipoPlano {

	private int id;

	private String nome; 
	private String descricao;
	private double preco;
	private String tipoPreco;
	private int nivelPlano;
	private Recurso[] recursos;
	private boolean testeGratisDisponivel;
	
	
	
	public TipoPlano() {
		super();
	}

	public TipoPlano(int id, String nome, String descricao, double preco, String tipoPreco, int nivelPlano, Recurso[] recursos, boolean testeGratisDisponivel) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.tipoPreco = tipoPreco;
		this.nivelPlano = nivelPlano;
		this.recursos = recursos;
		this.testeGratisDisponivel = testeGratisDisponivel;
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
	public Recurso[] getRecursos() {
		return recursos;
	}
	public void setRecursos(Recurso[] recursos) {
		this.recursos = recursos;
	}
	public boolean isTesteGratisDisponivel() {
		return testeGratisDisponivel;
	}
	public void setTesteGratisDisponivel(boolean testeGratisDisponivel) {
		this.testeGratisDisponivel = testeGratisDisponivel;
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
				", recursos=" + Arrays.toString(recursos) +
				", testeGratisDisponivel=" + testeGratisDisponivel +
				'}';
	}
}
