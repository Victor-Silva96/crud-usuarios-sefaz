package br.com.sefaz.constants;

public enum Constants {
	SUCESSO("Sucesso"), ERROR("Erro"), ERROTECNICO("Erro tecnico inesperado");
	
	private  final String nome;
	Constants(String nome){
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	
}
