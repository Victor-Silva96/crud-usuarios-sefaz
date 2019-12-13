package br.com.sefaz.session;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
	
	private static Session instance;
	
	public static Session getInstance() {
		if(instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	private Session() {
		
	}
	
	private ExternalContext currentSession() {
		if(FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
		}
		
		else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	public  void encerrarSessao() {
		currentSession().invalidateSession();
	}
	
	public Object getAttribute(String nome) {
		return currentSession().getSessionMap().get(nome);
	}
	
	public void setAttribute(String nome, Object valor) {
		currentSession().getSessionMap().put(nome, valor);
	}

}
