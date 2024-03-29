package org.salesforce.models;

public class PerguntasFrequentes {

	private int id;
	private TipoProduto tipoProduto;
	private String pergunta;
	private String resposta;
	

	
	public PerguntasFrequentes() {
		super();
	}

	public PerguntasFrequentes(int id, TipoProduto tipoProduto, String pergunta, String resposta) {
		this.id = id;
		this.tipoProduto = tipoProduto;
		this.pergunta = pergunta;
		this.resposta = resposta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
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

	@Override
	public String toString() {
		return "PerguntasFrequentes{" +
				"id=" + id +
				", tipoProduto=" + tipoProduto +
				", pergunta='" + pergunta + '\'' +
				", resposta='" + resposta + '\'' +
				'}';
	}
}
