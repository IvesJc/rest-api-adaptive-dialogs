package org.salesforce.models;

public class Cliente {

	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private String tipo;
	private String idioma;
	private String pais;
	private String telefone;

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String sobrenome, String email, String tipo, String idioma, String pais, String telefone) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.tipo = tipo;
		this.idioma = idioma;
		this.pais = pais;
		this.telefone = telefone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", sobrenome='" + sobrenome + '\'' +
				", email='" + email + '\'' +
				", tipo='" + tipo + '\'' +
				", idioma='" + idioma + '\'' +
				", pais='" + pais + '\'' +
				", telefone='" + telefone + '\'' +
				'}';
	}
}
