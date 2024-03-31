package org.salesforce.models;


import java.util.Arrays;

public class TipoProduto {

	private int id;
	private String nome;
	private String descricao;
	private boolean isAddOn;
	private String nomeGrupo;
	private Integer tipoPlanoId;
	
	
	public TipoProduto() {
		super();
	}

	public TipoProduto(int id, String nome, String descricao, boolean isAddOn, String nomeGrupo, Integer tipoPlanoId) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.isAddOn = isAddOn;
		this.nomeGrupo = nomeGrupo;
		this.tipoPlanoId = tipoPlanoId;
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
	
	public boolean isAddOn() {
		return isAddOn;
	}
	public void setAddOn(boolean isAddOn) {
		this.isAddOn = isAddOn;
	}
	
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public Integer getTipoPlanoId() {
		return tipoPlanoId;
	}

	public void setTipoPlanoId(Integer tipoPlanoId) {
		this.tipoPlanoId = tipoPlanoId;
	}

	@Override
	public String toString() {
		return "TipoProduto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", isAddOn=" + isAddOn +
				", nomeGrupo='" + nomeGrupo + '\'' +
				'}';
	}
}
