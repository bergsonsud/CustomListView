package com.example.customlist;

public class Cliente {
	
	private String id;
	private String nome;
	private String telefone;
	
	
	Cliente(String id, String nome,String telefone){
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}




}
