package org.salesforce.models;

public class PerguntasFrequentes {

	private int id;
	private String pergunta;
	private String resposta;
	private Integer tipoProdutoId;

	
	public PerguntasFrequentes() {
		super();
	}

	public PerguntasFrequentes(int id, String pergunta, String resposta, Integer tipoProdutoId) {
		this.id = id;
		this.pergunta = pergunta;
		this.resposta = resposta;
		this.tipoProdutoId = tipoProdutoId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Integer getTipoProdutoId() {
		return tipoProdutoId;
	}

	public void setTipoProdutoId(Integer tipoProdutoId) {
		this.tipoProdutoId = tipoProdutoId;
	}

	@Override
	public String toString() {
		return "PerguntasFrequentes{" +
				"id=" + id +
				", pergunta='" + pergunta + '\'' +
				", resposta='" + resposta + '\'' +
				'}';
	}
}
