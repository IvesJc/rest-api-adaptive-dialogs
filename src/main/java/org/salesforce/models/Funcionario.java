package org.salesforce.models;

public class Funcionario {

	private int id;
	private String nome;
	private String sobrenome;
	private String cargo;
	private String email;
	private String telefone;
	private double salario;
	
	
	public Funcionario() {
		super();
	}

	public Funcionario(int id, String nome, String sobrenome, String cargo, String email, String telefone, double salario) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cargo = cargo;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Funcionario{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", sobrenome='" + sobrenome + '\'' +
				", cargo='" + cargo + '\'' +
				", email='" + email + '\'' +
				", telefone='" + telefone + '\'' +
				", salario=" + salario +
				'}';
	}
}
