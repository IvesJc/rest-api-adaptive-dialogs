package org.salesforce.models;

import java.util.Date;

public class Produto {

	private int id;
	private Empresa empresa;
	private double preco; 
	private String status;
	private TipoPlano planoContratado;
	private TipoProduto tipoProduto;
	private Date testeGratisAte;
	
	
	
	public Produto() {
		super();
	}

	public Produto(int id, Empresa empresa, double preco, String status, TipoPlano planoContratado, TipoProduto tipoProduto, Date testeGratisAte) {
		this.id = id;
		this.empresa = empresa;
		this.preco = preco;
		this.status = status;
		this.planoContratado = planoContratado;
		this.tipoProduto = tipoProduto;
		this.testeGratisAte = testeGratisAte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	public TipoPlano getPlanoContratado() {
		return planoContratado;
	}
	public void setPlanoContratado(TipoPlano planoContratado) {
		this.planoContratado = planoContratado;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public java.sql.Date getTesteGratisAte() {
		return (java.sql.Date) testeGratisAte;
	}

	public void setTesteGratisAte(Date testeGratisAte) {
		this.testeGratisAte = testeGratisAte;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", empresa=" + empresa +
				", preco=" + preco +
				", status='" + status + '\'' +
				", planoContratado=" + planoContratado +
				", tipoProduto=" + tipoProduto +
				", testeGratisAte=" + testeGratisAte +
				'}';
	}
}
