package org.salesforce.models;


import java.util.Arrays;

public class TipoProduto {

	private int id;
	private String nome;
	private String descricao;
	private TipoPlano[] planosDisponiveis;
	private boolean isAddOn;
	private String nomeGrupo;
	
	
	public TipoProduto() {
		super();
	}

	public TipoProduto(int id, String nome, String descricao, TipoPlano[] planosDisponiveis, boolean isAddOn, String nomeGrupo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.planosDisponiveis = planosDisponiveis;
		this.isAddOn = isAddOn;
		this.nomeGrupo = nomeGrupo;
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
	
	public TipoPlano[] getPlanosDisponiveis() {
		return planosDisponiveis;
	}
	public void setPlanosDisponiveis(TipoPlano[] planosDisponiveis) {
		this.planosDisponiveis = planosDisponiveis;
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

	@Override
	public String toString() {
		return "TipoProduto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", planosDisponiveis=" + Arrays.toString(planosDisponiveis) +
				", isAddOn=" + isAddOn +
				", nomeGrupo='" + nomeGrupo + '\'' +
				'}';
	}
}
