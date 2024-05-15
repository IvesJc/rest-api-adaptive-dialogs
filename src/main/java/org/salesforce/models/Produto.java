package org.salesforce.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.Date;

@JsonbPropertyOrder({"id", "preco", "status", "testeGratisAte", "tipoProdutoId", "empresaId"})
public class Produto {

	private int id;
	private double preco;
	private String status;
	private Date testeGratisAte;
	private Integer tipoProdutoId;
	private Integer empresaId;

	
	
	
	public Produto() {
		super();
	}

	public Produto(int id, double preco, String status, Date testeGratisAte, Integer tipoProdutoId, Integer empresaId) {
		this.id = id;
		this.preco = preco;
		this.status = status;
		this.testeGratisAte = testeGratisAte;
		this.tipoProdutoId = tipoProdutoId;
		this.empresaId = empresaId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getTesteGratisAte() {
		return (java.sql.Date) testeGratisAte;
	}

	public void setTesteGratisAte(Date testeGratisAte) {
		this.testeGratisAte = testeGratisAte;
	}

	public Integer getTipoProdutoId() {
		return tipoProdutoId;
	}

	public void setTipoProdutoId(Integer tipoProdutoId) {
		this.tipoProdutoId = tipoProdutoId;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", preco=" + preco +
				", status='" + status + '\'' +
				", testeGratisAte=" + testeGratisAte +
				'}';
	}
}
