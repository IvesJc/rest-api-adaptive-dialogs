package org.salesforce.models;

public class Funcionario {

	private Integer id;
	private String nome;
	private String sobrenome;
	private String cargo;
	private String email;
	private String telefone;
	private double salario;
	private Integer empresaId;
	
	
	public Funcionario() {
		super();
	}

	public Funcionario(Integer id, String nome, String sobrenome, String cargo, String email, String telefone, double salario, Integer empresaId) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cargo = cargo;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
		this.empresaId = empresaId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
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
